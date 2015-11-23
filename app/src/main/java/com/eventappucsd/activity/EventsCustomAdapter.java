package com.eventappucsd.activity;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.eventappucsd.backend.Date;
import com.eventappucsd.backend.Event;
import com.eventappucsd.backend.EventsListFragment;

import java.util.List;

/**
 * Created by Scott on 11/13/15.
 */
public class EventsCustomAdapter extends ArrayAdapter<Event>  {

    private LayoutInflater mLayoutInflater;
    private static FragmentManager sFragmentManager;
    private ContentResolver mContentResolver;
    private final String LOG_TAG = EventsCustomAdapter.class.getSimpleName();
    View view2;

    public EventsCustomAdapter(Context context, FragmentManager fragmentManager){

        super(context, android.R.layout.simple_list_item_2);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sFragmentManager = fragmentManager;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null) {
            //// TODO: 11/13/15 create custom layout
            view = mLayoutInflater.inflate(R.layout.custom_event, parent, false);
        } else {
            view = convertView;
        }
        final Event event = getItem(position);
        final int _id = event.getId();
        final String name = event.getEventName();
        final String date = event.getDate();
        final String time = event.getTime();
        final String location = event.getLocation();
        final String description = event.getDescription();
        final int numVotes = event.getNumVotes();  //TODO make it a

        ((TextView) view.findViewById(R.id.event_name)).setText(name);
        ((TextView) view.findViewById(R.id.event_date)).setText(date);
        ((TextView) view.findViewById(R.id.event_location)).setText(location);
        ((TextView) view.findViewById(R.id.event_numVotes)).setText(numVotes + " Votes");

        //get the context so that the object called is not null for db updates
        mContentResolver = getContext().getContentResolver();

        /*
        make the event clickable and transition into the ViewActivity
         */
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //setting up the data needed to be made available by the ViewEventActivity.class
                //Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
                Intent eventView = new Intent(getContext(), ViewEventActivity.class);
                eventView.putExtra(EventsContract.EventsColumns.EVENTS_ID, String.valueOf(_id));
                eventView.putExtra(EventsContract.EventsColumns.EVENTS_NAME, name);
                eventView.putExtra(EventsContract.EventsColumns.EVENTS_DATE, date);
                eventView.putExtra(EventsContract.EventsColumns.EVENTS_TIME, time);
                eventView.putExtra(EventsContract.EventsColumns.EVENTS_LOCATION, location);
                eventView.putExtra(EventsContract.EventsColumns.EVENTS_DESCRIPTION,description);

                getContext().startActivity(eventView);

            }
        });

        final ToggleButton upvoteButton = (ToggleButton) view.findViewById(R.id.upbtn);
        // Needed in order to have both the button and the list item clickable
        upvoteButton.setFocusable(false);
        upvoteButton.setFocusableInTouchMode(false);
        upvoteButton.setClickable(true);
        upvoteButton.setTag(position);
        upvoteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: How to get from the data base for which event is clicked?
                    //Event event = FakeDB.getEvent((Integer) view.getTag()); // gets position stored
                    //TODO: Make it so that one phone can only upvote an event once.

                    // not voted yet
                    if(upvoteButton.isChecked()) {
                        Toast.makeText(getContext(), "Thank you for voting ", Toast.LENGTH_SHORT).show();
                        //TODO: event.incrementNumVotes();
                        int newVotes = numVotes;
                        ++newVotes;

                        ContentValues values = new ContentValues();
                        values.put(EventsContract.EventsColumns.EVENTS_NUM_VOTES, String.valueOf(newVotes));
                        Uri uri = EventsContract.Events.buildEventUri(String.valueOf(_id));
                        int recordsUpdated = mContentResolver.update(uri, values, null, null);

                        Log.d(LOG_TAG, "number of records updated = " + recordsUpdated + "newVotes: " + newVotes);

                        //TODO: display the new vote;

                    } else {
                        //TODO: decrease vote
                    }
                }
            });

        return view;
    }
    public void setData(List<Event> events){
        clear();
        if(events != null){
            for(Event event : events){
                add(event);
            }
        }
    }
}
