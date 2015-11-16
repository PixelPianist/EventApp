package com.eventappucsd.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;


/**
 * Created by Michael on 11/5/2015.
 */
public class ViewEventActivity extends FragmentActivity {

    private TextView nameTextView, dateTextView, timeTextView, descriptionTextView, locationTextView;
    //only needed for querying the DB
    private ContentResolver mContentResolver;

    protected void onCreate(Bundle savedInstanceState) {
        // Magic android stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);

        //getting the textViews
        nameTextView = (TextView) findViewById(R.id.viewEventName);
        dateTextView = (TextView) findViewById(R.id.viewEventDate);
        timeTextView = (TextView) findViewById(R.id.viewEventTime);
        descriptionTextView = (TextView) findViewById(R.id.viewEventDescription);
        locationTextView = (TextView) findViewById(R.id.viewEventLocation);

        //get the intent data from the EventsCustomAdapter class
        Intent intent = getIntent();
        final String _id = intent.getStringExtra(EventsContract.EventsColumns.EVENTS_ID);
        String eventName = intent.getStringExtra(EventsContract.EventsColumns.EVENTS_NAME);
        String eventDate = intent.getStringExtra(EventsContract.EventsColumns.EVENTS_DATE);
        String eventTime = intent.getStringExtra(EventsContract.EventsColumns.EVENTS_TIME);
        String eventDescription = intent.getStringExtra(EventsContract.EventsColumns.EVENTS_DESCRIPTION);
        String eventLocation = intent.getStringExtra(EventsContract.EventsColumns.EVENTS_LOCATION);

        //set the textViews to the data from the EventsCustomAdapter class
        nameTextView.setText(eventName);
        dateTextView.setText(eventDate);
        timeTextView.setText(eventTime);
        descriptionTextView.setText(eventDescription);
        locationTextView.setText(eventLocation);
    }
}
