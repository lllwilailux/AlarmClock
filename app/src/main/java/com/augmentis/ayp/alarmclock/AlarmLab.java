package com.augmentis.ayp.alarmclock;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.augmentis.ayp.alarmclock.database.AlarmBaseHelper;
import com.augmentis.ayp.alarmclock.database.AlarmCursorWrapper;
import com.augmentis.ayp.alarmclock.database.AlarmDbSchema.AlarmTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Wilailux on 8/25/2016.
 */
public class AlarmLab {

    private static AlarmLab instance;
    private Context context;
    private SQLiteDatabase database;


    public static AlarmLab getInstance(Context context) {
        if (instance == null) {
            instance = new AlarmLab(context);
        }
        return instance;
    }

    public static ContentValues getContentValues(Clock clock) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AlarmTable.Cols.UUID, clock.getId().toString());
        contentValues.put(AlarmTable.Cols.HOURS, clock.getHours());
        contentValues.put(AlarmTable.Cols.MINUTES, clock.getMinutes());
        contentValues.put(AlarmTable.Cols.STATUS, (clock.isStatus()) ? 1:0);
        return contentValues;
    }

    public AlarmLab(Context context) {
        this.context = context.getApplicationContext();
        AlarmBaseHelper alarmBaseHelper = new AlarmBaseHelper(context);
        database = alarmBaseHelper.getWritableDatabase();
    }

    public Clock getClockById(UUID uuid) {

        AlarmCursorWrapper cursor = queryAlarm(AlarmTable.Cols.UUID + " = ?", new String[]{uuid.toString()});

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getAlarm();
        } finally {
            cursor.close();
        }
    }

    public AlarmCursorWrapper queryAlarm (String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(AlarmTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new AlarmCursorWrapper(cursor);
    }

    public List<Clock> getAlarm() {
        List<Clock> alarmClocks = new ArrayList<>();

        AlarmCursorWrapper cursor = queryAlarm(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                alarmClocks.add(cursor.getAlarm());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return alarmClocks;
    }

    public void addAlarm(Clock alarmClock) {
        ContentValues contentValues = getContentValues(alarmClock);
        database.insert(AlarmTable.NAME,null,contentValues);
    }
}