package com.eventappucsd.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.eventappucsd.backend.DateSetter;
import com.eventappucsd.backend.TimeSetter;


/**
 * The activity where the user inputs all the information to create a new
 * event to be added to the database.
 *
 */
public class EnterEventInfoActivity extends FragmentActivity {
    /*
     * Instance Variables
     */
    private final String LOG_TAG = EnterEventInfoActivity.class.getSimpleName();
    private EditText editTextDescription, editTextLocation, editTextTime, editTextEventName,
            editTextDate;
    private Button mButton;
    private ContentResolver mContentResolver;

    /**
     * Overrides the top classes onCreate method and creates all the inputs, widgets, and
     * buttons that will be needed to be displayed in this activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Meta
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_event_info);

        // Create EditText fields and a date and time widget.
        editTextEventName = (EditText) findViewById(R.id.editTextEventName);
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        editTextTime = (EditText) findViewById(R.id.editTextTime);
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        DateSetter fromDate = new DateSetter(editTextDate, this);
        TimeSetter fromTIme = new TimeSetter(editTextTime, this);

        // Content Resolver to be used for database insertion
        mContentResolver = EnterEventInfoActivity.this.getContentResolver();
        // Create the Add Event button
        mButton = (Button) findViewById(R.id.addRecord);
        // Create an onclick listener, which will essentially add all the information to the
        // database.
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Only reach this state if the user has entered valid data, e.g. nothing is null
                if (isValid()) {
                    // Add all the values to the database
                    ContentValues values = new ContentValues();
                    values.put(EventsContract.EventsColumns.EVENTS_NAME, editTextEventName.getText().toString());
                    values.put(EventsContract.EventsColumns.EVENTS_DESCRIPTION, editTextDescription.getText().toString());
                    values.put(EventsContract.EventsColumns.EVENTS_LOCATION, editTextLocation.getText().toString());
                    values.put(EventsContract.EventsColumns.EVENTS_DATE, editTextTime.getText().toString());
                    values.put(EventsContract.EventsColumns.EVENTS_TIME, editTextDate.getText().toString());
                    values.put(EventsContract.EventsColumns.EVENTS_NUM_VOTES, "0");
                    Uri returned = mContentResolver.insert(EventsContract.URI_TABLE, values);
                    Log.d(LOG_TAG,"record id returned is " + returned.toString() );
                    // Go back to the main screen
                    Intent intent = new Intent(EnterEventInfoActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Enter some valid event data.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Checks if all the information the user entered is valid and returns a boolean
     * signifying the answer.
     *
     * @return
     */
    private boolean isValid(){
        if(editTextEventName.getText().length() == 0 ||
                editTextDescription.getText().length() ==0 ||
                editTextLocation.getText().toString().length() == 0 ||
                editTextTime.getText().toString().length() == 0 ||
                editTextDate.getText().toString().length() == 0){
            return false;
        }else
            return true;
    }

    /**
     * When the user presses the back button to cancel the adding of a new
     * event, this method will be called to return the user to the main screen.
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        setResult(0, intent);
        finish();
    }
}