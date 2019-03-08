package com.example.samyuktha.timesheetskeep.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

/**
 * Created by samyuktha on 9/1/2018.
 */

public class SqLiteDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =3;
    private static final String DATABASE_NAME = "timeSheets.db";
    public SQLiteDatabase mDatabase;
    Context mContext;

    public SqLiteDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SheetsData.CREATE_TABLE_SHEETS_DATA);
        sqLiteDatabase.execSQL(CheckInOutTimings.CREATE_TABLE_CHECKINOUT_TIMINGS);
        sqLiteDatabase.execSQL(AddressInformation.CREATE_TABLE_ADDRESS_INFORMATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public synchronized SQLiteDatabase openDatabase() {
        mDatabase = this.getWritableDatabase();
        return mDatabase;
    }

    public int insertCheckInOutValues(long check_in, long check_out) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CheckInOutTimings.CHECK_IN, check_in);
        values.put(CheckInOutTimings.CHECK_OUT, check_out);
        int row_id=(int)db.insert(CheckInOutTimings.CHECK_TIMINGS_TABLE, null, values);
        db.close();
        return row_id;
    }

    public int insertSheetsValues(String weekStart, String weekEnd, Float hours) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SheetsData.COLUMN_WEEK_START, weekStart);
        values.put(SheetsData.COLUMN_WEEK_END, weekEnd);
        values.put(SheetsData.COLUMN_HOURS, hours);
        int row_id=(int)db.insert(SheetsData.SHEETS_TABLE, null, values);
        db.close();
        return row_id;
    }

    public int insertAddressInfoValues(String Address, Double latitude, Double longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AddressInformation.ADDRESS, Address);
        values.put(AddressInformation.LATITUDE, latitude);
        values.put(AddressInformation.LONGITUDE, longitude);
        int row_id=(int)db.insert(AddressInformation.ADDRESS_INFO, null, values);
        db.close();
        return row_id;
    }

    public int insertWeekValues(String weekday, float totalHours) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WeekData.DATE, weekday);
        values.put(WeekData.HOURS, totalHours);
        int row_id=(int)db.insert(WeekData.WEEK_TIMINGS, null, values);
        db.close();
        return row_id;
    }

    public AddressInformation fetchAddressGeo(String address) {
        Log.d("TAG", "fetch Address Geo Locations from Database");
        AddressInformation addressInformation = null;
        if(address != null) {
            Cursor dbCursor = retrieveAddressInfo();
            while(dbCursor.moveToNext()) {
              if (dbCursor.getString(dbCursor.getColumnIndex(AddressInformation.ADDRESS)).equals(address)){
                  addressInformation.setAddress(dbCursor.getString(dbCursor.getColumnIndex(AddressInformation.ADDRESS)));
                  addressInformation.setLatitude(dbCursor.getDouble(dbCursor.getColumnIndex(AddressInformation.LATITUDE)));
                  addressInformation.setLongitude(dbCursor.getDouble(dbCursor.getColumnIndex(AddressInformation.LONGITUDE)));
                  return addressInformation;
              }
            }
            Log.d("AddressInfoDB","address not found in database");
        } else {
            Log.d("AddressInfoDB", "address is null");
        }
        return null;
    }

    public AddressInformation getLastSavedAddressInfo() {
        Log.d("TAG", "getLastSavedAddressInfo");
        Cursor dbCursor = retrieveAddressInfo();
        AddressInformation lastSavedAddress = new AddressInformation();
        if(dbCursor != null && dbCursor.getCount() >0) {
            dbCursor.moveToPosition(dbCursor.getCount() - 1);
            lastSavedAddress.setAddress(dbCursor.getString(dbCursor.getColumnIndex(AddressInformation.ADDRESS)));
            lastSavedAddress.setLatitude(dbCursor.getDouble(dbCursor.getColumnIndex(AddressInformation.LATITUDE)));
            lastSavedAddress.setLongitude(dbCursor.getDouble(dbCursor.getColumnIndex(AddressInformation.LONGITUDE)));
        } else {
            Log.d("AddressInfoDB", "database is empty");
        }
        return lastSavedAddress;
    }

    public Cursor retrieveAddressInfo() {
        String selectQuery = "SELECT  * FROM " + AddressInformation.ADDRESS_INFO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }
}
