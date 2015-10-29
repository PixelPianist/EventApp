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

    private static Event currentEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_event_info);

        currentEvent = new Event();
        EditText editTextEventName = (EditText) findViewById(R.id.editTextEventName);
        editTextEventName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER ||
                        actionId == EditorInfo.IME_ACTION_NEXT ||
                        actionId == EditorInfo.IME_ACTION_NONE) {
                    handled = true;
                    String eventName = v.getText().toString();
                    System.out.println("IME_ACTION_NEXT HAPPENED");
                    currentEvent.setEventName(eventName);
                    System.out.println("In first action id: " + currentEvent.toString());
                }
                return handled;
            }
        });

        EditText editTextDate = (EditText) findViewById(R.id.editTextDate);
        editTextEventName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    handled = true;
                    System.out.println("IME_ACTION_NEXT HAPPENED IN DATE");
                    String eventDateInString = v.getText().toString();
                    Date date = new Date(eventDateInString);
                    currentEvent.setDate(date);
                    System.out.println("After setting date: " + currentEvent.toString());
                }
                return handled;
            }
        });

        EditText editTextTime = (EditText) findViewById(R.id.editTextTime);
        editTextEventName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    handled = true;
                    String eventTimeInString = v.getText().toString();
                    System.out.println("IME_ACTION_NEXT HAPPENED IN TIME");
                }
                return handled;
            }
        });

        EditText editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        editTextEventName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    handled = true;
                    String eventLocation = v.getText().toString();
                    currentEvent.setLocation(eventLocation);
                }
                return handled;
            }
        });

        EditText editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        editTextEventName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    handled = true;
                    String eventDescription = v.getText().toString();
                    currentEvent.setDescription(eventDescription);
                }
                return handled;
            }
        });


    }

    /**
     * Called when the user clicks the Add Event button.
     * Eventually, this will add the event to the database and check for duplicates.
     * @param view The view that was clicked
     */
    public void addEventToDatabase(View view) {
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

        System.out.println("Printing out final state of the event: " +
                           currentEvent.toString());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}