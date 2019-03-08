package com.example.samyuktha.timesheetskeep.Database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by samyuktha on 9/1/2018.
 */


public class CheckInOutTimings {

    public CheckInOutTimings() {}

    public static final String CHECK_TIMINGS_TABLE = "INOUTTIMINGS";
    public static final String CHECK_IN= "CHECK_IN";
    public static final String CHECK_OUT = "CHECK_OUT";


    public static final String CREATE_TABLE_CHECKINOUT_TIMINGS = "CREATE TABLE "
        + CHECK_TIMINGS_TABLE + "(" + CHECK_IN + " INTEGER," + CHECK_OUT
        + " INTEGER" + ")";

    private long check_in;
    private long check_out;

    public CheckInOutTimings(long check_in, long check_out) {
        this.check_in = check_in;
        this.check_out = check_out;
    }

    public long getCheck_in() {
        return check_in;
    }

    public long getCheck_out() {
        return check_out;
    }

    public void setCheck_in(long check_in) {
        this.check_in = check_in;
    }

    public void setCheck_out(long check_out) {
        this.check_out = check_out;
    }

}
