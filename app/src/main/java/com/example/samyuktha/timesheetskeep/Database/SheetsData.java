package com.example.samyuktha.timesheetskeep.Database;

import java.util.Date;

/**
 * Created by samyuktha on 9/1/2018.
 */

public class SheetsData {

    public static final String SHEETS_TABLE = "SHEETSDATA";
    public static final String COLUMN_WEEK_START = "WEEK_START";
    public static final String COLUMN_WEEK_END = "WEEK_END";
    public static final String COLUMN_HOURS = "HOURS";

    public static final String CREATE_TABLE_SHEETS_DATA = "CREATE TABLE "
            + SHEETS_TABLE + "(" + COLUMN_WEEK_START + " TEXT," + COLUMN_WEEK_END
            + " TEXT," + COLUMN_HOURS + " REAL" + ")";

    public void setWeekStart(String weekStart) {
        this.weekStart = weekStart;
    }

    public void setWeekEnd(String weekEnd) {
        this.weekEnd = weekEnd;
    }

    public String weekStart;
    public String weekEnd;
    public float hours;

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public String getWeekStart() {
        return  weekStart;
    }

    public String getWeekEnd() {
        return  weekEnd;
    }
}
