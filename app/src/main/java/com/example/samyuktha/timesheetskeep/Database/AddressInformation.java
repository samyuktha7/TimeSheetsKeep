package com.example.samyuktha.timesheetskeep.Database;


/**
 * Created by samyuktha on 9/1/2018.
 */

public class AddressInformation {

    public static final String ADDRESS_INFO = "ADDRESS_INFO";
    public static final String ADDRESS = "ADDRESS";
    public static final String LATITUDE = "LATITUDE";
    public static final String LONGITUDE = "LONGITUDE";

    public String address;
    public Double latitude;
    public Double longitude;

    public static final String CREATE_TABLE_ADDRESS_INFORMATION = "CREATE TABLE "
            + ADDRESS_INFO+ "(" + ADDRESS + " TEXT," + LATITUDE
            + " REAL," + LONGITUDE + " REAL" + ")";

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
