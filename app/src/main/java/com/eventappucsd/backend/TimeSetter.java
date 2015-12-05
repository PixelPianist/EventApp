package com.eventappucsd.backend;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * @author Rachel
 * @date 11/29/2015
 * @version v1.0
 */
public class TimeSetter implements View.OnFocusChangeListener, TimePickerDialog.OnTimeSetListener {
    /*
     * Instance Vars
     */
    private EditText editText;
    private Calendar myCalendar;
    private Context ctx;

    /**
     * Constructor taking an EditText field and a Context
     *
     * @param editText
     * @param ctx
     */
    public TimeSetter(EditText editText, Context ctx){
        this.editText = editText;
        this.editText.setOnFocusChangeListener(this);
        this.myCalendar = Calendar.getInstance();
        this.ctx = ctx;
    }

    /**
     * Called when the focus changes.
     *
     * @since v1.0
     * @param v
     * @param hasFocus
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        // TODO Auto-generated method stub
        if(hasFocus){
            int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = myCalendar.get(Calendar.MINUTE);
            new TimePickerDialog(ctx, this, hour, minute, false).show();
        }
    }

    /**
     * Called when the time is set
     *
     * @since v1.0
     * @param view
     * @param hourOfDay
     * @param minute
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // TODO Auto-generated method stub
        //this.editText.setText( hourOfDay + ":" + minute);

        String am_pm = "";

        Calendar datetime = Calendar.getInstance();
        datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        datetime.set(Calendar.MINUTE, minute);

        if (datetime.get(Calendar.AM_PM) == Calendar.AM)
            am_pm = "AM";
        else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
            am_pm = "PM";

        String strHrsToShow = (datetime.get(Calendar.HOUR) == 0) ?"12":datetime.get(Calendar.HOUR)+"";
        this.editText.setText(strHrsToShow + ":" + minute + " " + am_pm);




    }

}
