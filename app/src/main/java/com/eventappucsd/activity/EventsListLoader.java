package com.eventappucsd.activity;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.eventappucsd.backend.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scott on 11/13/15.
 */
public class EventsListLoader extends AsyncTaskLoader<List<Event>> {
    private static final String LOG_TAG = EventsListLoader.class.getSimpleName();
    private ContentResolver mContentResolver;
    private Cursor mCursor;

    private List<Event> mEvents;
    //EventsListLoader Constructor
    public EventsListLoader(Context context, Uri uri, ContentResolver contentResolver){
        super(context);
        mContentResolver = contentResolver;
    }
    @Override
    public List<Event> loadInBackground() {
        String[]projection = {
                BaseColumns._ID,
                EventsContract.EventsColumns.EVENTS_NAME,
                EventsContract.EventsColumns.EVENTS_DESCRIPTION,
                EventsContract.EventsColumns.EVENTS_DATE,
                EventsContract.EventsColumns.EVENTS_TIME,
                EventsContract.EventsColumns.EVENTS_LOCATION,
                EventsContract.EventsColumns.EVENTS_NUM_VOTES
        };
        //list of entries in the DB
        List<Event> entries = new ArrayList<Event>();

        //initialize the cursor to the query of all the data from the DB
        mCursor = mContentResolver.query(EventsContract.URI_TABLE, projection, null, null, null);
        if(mCursor != null){
            //loop through and retrieve all the records
            if(mCursor.moveToFirst()){
                do{
                    int _id = mCursor.getInt(mCursor.getColumnIndex(BaseColumns._ID));
                    String name = mCursor.getString(mCursor.getColumnIndex(EventsContract.EventsColumns.EVENTS_NAME));
                    String description = mCursor.getString(mCursor.getColumnIndex(EventsContract.EventsColumns.EVENTS_DESCRIPTION));
                    String date = mCursor.getString(mCursor.getColumnIndex(EventsContract.EventsColumns.EVENTS_DATE));
                    String time = mCursor.getString(mCursor.getColumnIndex(EventsContract.EventsColumns.EVENTS_TIME));
                    String location = mCursor.getString(mCursor.getColumnIndex(EventsContract.EventsColumns.EVENTS_LOCATION));
                    int numVotes = mCursor.getInt(mCursor.getColumnIndex(EventsContract.EventsColumns.EVENTS_NUM_VOTES));
                    Event event = new Event(_id, name, description, date, time, location, numVotes);
                    entries.add(event);
                }while(mCursor.moveToNext()); //increment the cursor to the next position
            }
        }
        return entries;
    }

    @Override
    public void deliverResult(List<Event> events) {
        //while we were processing, we want to stop, close, and let the new process continue
        if(isReset()){
            if(events != null){
                mCursor.close();
            }
        }
        //temp copy of the list
        List<Event> oldEventsList = mEvents;
        if(mEvents == null || mEvents.size() == 0){
            //debug message
            Log.d(LOG_TAG, "++++++++ No Data returned");
        }
        mEvents = events;
        //isStarted will check to see if the load has been started
        if(isStarted()){
            //deliver the data, super refers to the parents method
            super.deliverResult(events);
        }
        if(oldEventsList != null && oldEventsList != events){
            mCursor.close();
        }
    }

    @Override
    protected void onStartLoading() {
        if(mEvents != null){
            deliverResult(mEvents);
        }
        if(takeContentChanged() || mEvents == null){
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        onStopLoading();;
        if(mCursor != null){
            mCursor.close();
        }
        mEvents = null;
    }

    @Override
    public void onCanceled(List<Event> events) {
        super.onCanceled(events);
        if(mCursor != null){
            mCursor.close();
        }
    }

    @Override
    public void forceLoad() {
        super.forceLoad();
    }
}
