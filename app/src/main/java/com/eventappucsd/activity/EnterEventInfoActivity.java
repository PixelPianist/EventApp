package com.eventappucsd.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eventappucsd.backend.Date;
import com.eventappucsd.backend.Event;


public class EnterEventInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_event_info);
    }

    /**
     * Called when the user clicks the Add Event button.
     * Eventually, this will add the event to the database and check for duplicates.
     * Right now, it just creates the event object.
     * @param view The view that was clicked
     */
    public void addEventToDatabase(View view) {
        Event currentEvent = new Event();
        EditText editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        EditText editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        EditText editTextTime = (EditText) findViewById(R.id.editTextTime);
        EditText editTextEventName = (EditText) findViewById(R.id.editTextEventName);
        EditText editTextDate = (EditText) findViewById(R.id.editTextDate);

        currentEvent.setEventName(editTextEventName.getText().toString());
        currentEvent.setDescription(editTextDescription.getText().toString());
        Date date = new Date(editTextDate.getText().toString());
        currentEvent.setDate(date);
        currentEvent.setLocation(editTextLocation.getText().toString());
        currentEvent.setTime(editTextTime.getText().toString());

        System.out.println("Printing out final state of the event: " +
                           currentEvent.toString());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}