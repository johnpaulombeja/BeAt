package com.jombeja.beat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

import static com.jombeja.beat.AttendanceDatabaseContract.DetailsEntry.*;

public class AttendanceOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Beatrecords.db";
    public static final int DATABASE_VERSION = 1;
    private ViewAdapter mViewAdapter;
    private SQLiteDatabase mDatabase;
    private int mRecord;

    public AttendanceOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String idnumber, String emailaddress, String devicename,
                            String macaddress, String attendancetime) {
        mDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,name);
        contentValues.put(COLUMN_IDENTIFICATION_NUMBER,idnumber);
        contentValues.put(COLUMN_EMAIL,emailaddress);
        contentValues.put(COLUMN_DEVICE_NAME,devicename);
        contentValues.put(COLUMN_MAC_ADDRESS,macaddress);
        contentValues.put(COLUMN_ATTENDANCE_TIME,attendancetime);
        mRecord = (int) mDatabase.insert(TABLE_NAME,null ,contentValues);
        if(mRecord == -1)
            return false;
        else
            return true;
    }

    /**
     * delete record
     **/
    public void deleteRecord(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, _ID + " = ? ", new String[]{Long.toString(Long.parseLong(id))});
    }

    /**Query records, give options to filter results**/
    public List<ModelClass> peopleList(String filter) {
        String query;
        if(filter.equals("")){
            //regular query
            query = "SELECT  * FROM " + TABLE_NAME;
        }else{
            //filter results by filter option provided
            query = "SELECT  * FROM " + TABLE_NAME + " ORDER BY "+ filter;
        }

        List<ModelClass> DetailsLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ModelClass modelClass;

        if (cursor.moveToFirst()) {
            do {
                modelClass = new ModelClass();
                modelClass.setId(cursor.getString(cursor.getColumnIndex(_ID)));
                modelClass.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                modelClass.setIdNumber(cursor.getString(cursor.getColumnIndex(COLUMN_IDENTIFICATION_NUMBER)));
                modelClass.setEmailAddress(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                modelClass.setDeviceName(cursor.getString(cursor.getColumnIndex(COLUMN_DEVICE_NAME)));
                modelClass.setMacAddress(cursor.getString(cursor.getColumnIndex(COLUMN_MAC_ADDRESS)));
                modelClass.setTime(cursor.getString(cursor.getColumnIndex(COLUMN_ATTENDANCE_TIME)));
                DetailsLinkedList.add(modelClass);
            } while (cursor.moveToNext());
        }
        return DetailsLinkedList;
    }
}
