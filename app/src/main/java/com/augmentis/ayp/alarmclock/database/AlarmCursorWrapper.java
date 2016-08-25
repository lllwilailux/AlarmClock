package com.augmentis.ayp.alarmclock.database;

import android.database.Cursor;
import android.database.CursorWrapper;


import com.augmentis.ayp.alarmclock.Clock;
import com.augmentis.ayp.alarmclock.database.AlarmDbSchema.AlarmTable;

import java.util.UUID;


/**
 * Created by Wilailux on 8/25/2016.
 */
public class AlarmCursorWrapper extends CursorWrapper {

    public AlarmCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    public Clock getAlarm() {

        String uuidString = getString(getColumnIndex(AlarmTable.Cols.UUID));
        int hours = getInt(getColumnIndex(AlarmTable.Cols.HOURS));
        int minutes = getInt(getColumnIndex(AlarmTable.Cols.MINUTES));
        int status = getInt(getColumnIndex(AlarmTable.Cols.STATUS));

        Clock alarmClock = new Clock(UUID.fromString(uuidString));
        alarmClock.setHours(hours);
        alarmClock.setMinutes(minutes);
        alarmClock.setStatus(status != 0);

        return alarmClock;
    }
}
