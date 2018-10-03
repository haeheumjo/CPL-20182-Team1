package com.example.juhyeon.waygo;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public static String FRAGMENT_TAG;

    @Override
    public void onTimeSet(TimePicker view,int hourofDay,int minute) {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar mCalender=Calendar.getInstance();
        int hour=mCalender.get(Calendar.HOUR_OF_DAY);
        int min=mCalender.get(Calendar.MINUTE);
        TimePickerDialog mTimePickerDialog=new TimePickerDialog(
                getContext(),this,hour,min,false);
        return  mTimePickerDialog;
    }
}
