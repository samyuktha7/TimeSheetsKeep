package com.example.samyuktha.timesheetskeep.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.samyuktha.timesheetskeep.Controller;
import com.example.samyuktha.timesheetskeep.Database.AddressInformation;
import com.example.samyuktha.timesheetskeep.Database.SqLiteDatabaseHelper;
import com.example.samyuktha.timesheetskeep.HttpDataHandler;
import com.example.samyuktha.timesheetskeep.R;
import com.example.samyuktha.timesheetskeep.Receivers.AlarmManagerReceiver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ProfileActivity extends AppCompatActivity {

    private EditText addressField;
    private Button save;
    private String mAddress;
    public Double[] latLong = new Double[2];
    SqLiteDatabaseHelper dbHelper = new SqLiteDatabaseHelper(this, "AddressCommit", null, 1);
    private Context mContext;
    AddressInformation mAddressInformation = new AddressInformation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mContext = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        addressField = (EditText)findViewById(R.id.addressField);
        mAddressInformation = dbHelper.getLastSavedAddressInfo();
        if(mAddressInformation != null) {
            mAddress = mAddressInformation.getAddress();
            addressField.setText(mAddressInformation.getAddress());

        }

        save = (Button)findViewById(R.id.Enter);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(save.getText().toString() == "SAVE") {
                    save.setText("EDIT");
                    addressField.setFocusable(false);
                    mAddress = addressField.getText().toString();
                    if(mAddress != null) {
                        AddressInformation addressInformation = dbHelper.fetchAddressGeo(mAddress);
                        if(addressInformation == null) {
                            new getGeoCodingData().execute(mAddress);
                        }
                    }
                } else {
                    save.setText("SAVE");
                    addressField.setFocusable(true);
                }

            }
        });

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        calendar.set(Calendar.MINUTE, 47);
        calendar.set(Calendar.SECOND, 00);

        // create an Intent and set the Broadcast Receiver which will execute when Alarm
        // triggers, here we have given AlarmManagerReciever in the Intent, the onRecieve()
        // method of this class will execute when alarm triggers
        Intent intentAlarm = new Intent(mContext, AlarmManagerReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 1, intentAlarm, PendingIntent.FLAG_CANCEL_CURRENT );

        // create the object
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //set the alarm for particular time
        alarmManager.setRepeating(alarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }



    public class getGeoCodingData extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String response = new String();
            try {
                String address = strings[0];
                HttpDataHandler http = new HttpDataHandler();
                String url =String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s",address);
                response = http.getHTTPData(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                Log.d("xxx", "address " +mAddress);
                JSONObject jsonObject = new JSONObject(s);
                Log.d("ProfileActivity", "jsonObject "+jsonObject);
                if(jsonObject.length() > 0) {
                    latLong[0] = (Double) ((JSONArray) jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lat");
                    latLong[1] = (Double) ((JSONArray) jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lng");
                    Log.d("TAG", "latitude " + latLong[0] + " longitude " + latLong[1] + " address " + mAddress);
                    int row_id = dbHelper.insertAddressInfoValues(mAddress, latLong[0], latLong[1]);
                    Log.d("TAG", "row_id " + row_id);
                    Log.d("TAG", "alarm manager init");
                } else {
                    Log.d("ProfileActivity", "json object is null");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
