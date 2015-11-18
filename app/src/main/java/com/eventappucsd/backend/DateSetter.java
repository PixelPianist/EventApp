package com.eventappucsd.backend;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/** DateSetter - a class that uses the date picker widget to set the text of the EditText textbox.
 */

public class DateSetter implements View.OnFocusChangeListener, DatePickerDialog.OnDateSetListener {

    private EditText editText;
    private Calendar myCalendar;
    private Context ctx;


    /* @param editText - the textbox that we want to altar with the date */
    public DateSetter(EditText editText, Context ctx){
        this.editText = editText;
        this.editText.setOnFocusChangeListener(this);
        this.ctx = ctx;
        // use the Calendar to get the current date and such.
        myCalendar = Calendar.getInstance();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)     {
        // need to add 1 to the month since it's 0 based.
        editText.setText(String.format("%d/%d/%d", monthOfYear + 1, dayOfMonth, year));
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus){
            new DatePickerDialog(ctx, this, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }

}