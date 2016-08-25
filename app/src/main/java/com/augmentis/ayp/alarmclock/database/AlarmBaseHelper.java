package com.augmentis.ayp.alarmclock.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.augmentis.ayp.alarmclock.database.AlarmDbSchema.AlarmTable;

/**
 * Created by Wilailux on 8/25/2016.
 */
public class AlarmBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;

    private static final String DATABASE_NAME = "alarmBase.db";
    private static final String TAG = "AlarmBaseHelper";

    public AlarmBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d(TAG,"Create Database");
        db.execSQL("Create table " + AlarmTable.NAME
                +"("
                +"_id integer primary key autoincrement, "
                +AlarmTable.Cols.UUID +","
                +AlarmTable.Cols.HOURS +","
                +AlarmTable.Cols.MINUTES +","
                +AlarmTable.Cols.STATUS +")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
