package com.augmentis.ayp.alarmclock;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Wilailux on 8/24/2016.
 */
public class TimePickerDialog extends DialogFragment implements DialogInterface.OnClickListener{

    private static final String EXTRA_TIME = "TimePickerDialog";
    private CallBack callBack;

    public static TimePickerDialog newInstance() {
        TimePickerDialog td = new TimePickerDialog();
        return td;
    }

//    public static TimePickerDialog newInstance(UUID uuid) {
//        TimePickerDialog td = new TimePickerDialog();
//        Bundle args = new Bundle();
//        args.putSerializable("UUID" , uuid);
//        td.setArguments(args);
//
//        return td;
//    }

    TimePicker timePicker;
    Date time;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        callBack = (CallBack) getActivity();

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time_picker,null);

        timePicker = (TimePicker) v.findViewById(R.id.time_picker_in_dialog);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);
        builder.setTitle("Time Picker");
        builder.setPositiveButton(android.R.string.ok, this);
        return builder.create();
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(DialogInterface dialog, int which) {
        int hour =  timePicker.getHour();
        int minute =  timePicker.getMinute();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR,hour);
        c.set(Calendar.MINUTE,minute);

        Date date = c.getTime();

        Clock clock = new Clock();
        clock.setHours(hour);
        clock.setMinutes(minute);
        AlarmLab.getInstance(getActivity()).addAlarm(clock);
        callBack.sendData(date);
    }


}
