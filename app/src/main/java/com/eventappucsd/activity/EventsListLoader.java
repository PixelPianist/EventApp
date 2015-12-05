package com.eventappucsd.activity;


import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.eventappucsd.backend.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * A class used in loading a list of events
 *
 * @author Scott Miller
 * @date 11/13/15.
 * @version v1.0
 */
public class EventsListLoader extends AsyncTaskLoader<List<Event>> {
    /*
     * Named Constants
     */
    private static final String LOG_TAG = EventsListLoader.class.getSimpleName();
    private static final int VOTE_SORT  = 1;
    private static final int DATE_SORT  = 2;

    /*
     * Instance vars
     */
    private ContentResolver mContentResolver;
    private Cursor mCursor;
    // The actual list
    private List<Event> mEvents;

    /**
     * EventsListLoader Constructor taking a context, uri, and contentResolver
     *
     * @param context
     * @param uri
     * @param contentResolver
     */
    public EventsListLoader(Context context, Uri uri, ContentResolver contentResolver){
        super(context);
        mContentResolver = contentResolver;
    }

    /**
     * Loads the list of events and returns it.
     *
     * @return A populated list of events (if it exists)
     */
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

        String sortType = "";

        //Retrieve the current desired sorting scheme.
        switch(MainActivity.getSortType()){
            case VOTE_SORT:
                sortType = EventsContract.EventsColumns.EVENTS_NUM_VOTES + " DESC";
                break;
            case DATE_SORT:
                sortType = EventsContract.EventsColumns.EVENTS_DATE + " DESC";
                break;
            default:
                sortType = EventsContract.EventsColumns.EVENTS_NAME;
        }

        //initialize the cursor to the query of all the data from the DB
        mCursor = mContentResolver.query(EventsContract.URI_TABLE, projection, null, null, sortType, null);
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

    /**
     * Called in onStartLoading to deliver events in parent class.
     *
     * @param events A loaded list of events
     */
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

    /**
     * Calls different loading methods depending on the situation
     */
    @Override
    protected void onStartLoading() {
        if(mEvents != null){
            deliverResult(mEvents);
        }
        if(takeContentChanged() || mEvents == null){
            forceLoad();
        }
    }

    /**
     * Delegation method for cancelLoad
     */
    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    /**
     * Called on reset
     */
    @Override
    protected void onReset() {
        onStopLoading();
        if(mCursor != null){
            mCursor.close();
        }
        mEvents = null;
    }

    /**
     * Called when cancelled
     *
     * @param events
     */
    @Override
    public void onCanceled(List<Event> events) {
        super.onCanceled(events);
        if(mCursor != null){
            mCursor.close();
        }
    }

    /**
     * Delegation for forcing loads using super.forceLoad
     */
    @Override
    public void forceLoad() {
        super.forceLoad();
    }
}
