package com.jombeja.beat;

import android.provider.BaseColumns;

public final class AttendanceDatabaseContract {

    public AttendanceDatabaseContract() { }

    public static final class DetailsEntry implements BaseColumns {
        public static final String TABLE_NAME = "attendees";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IDENTIFICATION_NUMBER = "identification_number";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_DEVICE_NAME = "device_name";
        public static final String COLUMN_MAC_ADDRESS = "mac_address";
        public static final String COLUMN_ATTENDANCE_TIME = "attendance_time";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + _ID
                + " INTEGER PRIMARY KEY, " + COLUMN_NAME + " TEXT, "
                + COLUMN_IDENTIFICATION_NUMBER + " TEXT, " + COLUMN_EMAIL +
                " TEXT, " + COLUMN_DEVICE_NAME + " TEXT, " + COLUMN_MAC_ADDRESS +
                " TEXT, " + COLUMN_ATTENDANCE_TIME + " TEXT) ";
    }

}
