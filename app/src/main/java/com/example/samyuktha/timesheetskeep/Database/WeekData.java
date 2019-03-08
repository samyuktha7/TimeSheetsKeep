package com.example.samyuktha.timesheetskeep.Database;

import java.util.Date;

/**
 * Created by samyuktha on 9/1/2018.
 */

public class WeekData {

    public static final String WEEK_TIMINGS = "WEEKTIMINGS";
    public static final String DATE= "DATE";
    public static final String HOURS = "TOTAL_HOURS";


    public static final String CREATE_TABLE_WEEK_TIMINGS = "CREATE TABLE "
            + WEEK_TIMINGS + "(" + DATE + " TEXT," + HOURS
            + " REAL," + ")";

    public String getWeek_date() {
        return week_date;
    }

    public void setWeek_date(String week_date) {
        this.week_date = week_date;
    }

    public float getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(float totalHours) {
        this.totalHours = totalHours;
    }

    private String week_date;
    private float totalHours;


}
