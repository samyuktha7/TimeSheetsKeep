package com.example.samyuktha.timesheetskeep;

/**
 * Created by samyuktha on 8/30/2018.
 */

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {

    String[] latLong = new String[2];
    SQLiteOpenHelper openHelper;

    public Long getSystemTime() {
        return System.currentTimeMillis();
    }

    public String getTodaysDate() {
        DateFormat dateFormat = new SimpleDateFormat("EEE , MMM dd yyyy");
        Date date = new Date();
        return dateFormat.format(date).toString();
    }

}
