package com.eventappucsd.activity;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Log;

/**
 * ContentProvider for the DB
 * @author Scott Miller
 * @date 11/10/15
 * @version v1.0
 */
public class EventsProvider extends ContentProvider{
    /*
     * Named Constants
     */
    // Each command needs a unique number, Events is for multiple friends
    private static final int EVENTS = 100;
    // events_id is for a single event
    private static final int EVENTS_ID = 101;

    /*
     * Static vars
     */
    //name of the provider class
    private static String TAG = EventsProvider.class.getSimpleName();
    /*This looks at the content provider string (Uri) and deciphers it to decide if it is valid and
    what to do if it is valid
    (A URI is a Uniform Resource Identifier
     */
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    /*
     * Instance Vars
     */
    //need a copy of the DB
    private EventsDatabase mOpenHelper;

    /**
     * UriMatcher will define the list of valid actions for our contentProvider
     *
     * @since v1.0
     * @return UriMatcher A UriMatcher that is built in this method
     */
    private static UriMatcher buildUriMatcher(){
        // Get the UriMatcher
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = EventsContract.CONTENT_AUTHORITY;
        // Add fields to UriMatcher
        matcher.addURI(authority, "events", EVENTS);
        matcher.addURI(authority, "events/*", EVENTS_ID);
        // Return
        return matcher;
    }

    /**
     * onCreate will create the current DB
     *
     * @since v1.0
     * @return boolean Always returns true
     */
    @Override
    public boolean onCreate() {
       //pass the current context to the DB
        mOpenHelper = new EventsDatabase(getContext());
        return true;
    }

    /**
     * Method for deleting the DB, and creating a new empty one
     */
    private void deleteDatabase(){
        mOpenHelper.close();
        EventsDatabase.deleteDataBase(getContext());
        mOpenHelper = new EventsDatabase(getContext());
    }

    /**
     * getType will look to see if the Uri is a valid Uri
     *
     * @since v1.0
     * @return String Either the Event's Type or Item Type
     */
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        // Determines which string to return
        switch (match){
            case EVENTS:
                return EventsContract.Events.CONTENT_TYPE;
            case EVENTS_ID:
                return EventsContract.Events.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
    }

    /**
     * query method for querying the db
     *
     * @since v1.0
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return A cursor that was queried
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        //open the db, ready to retrieve the db
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        //find out what was passed to this method (define what to do)
        final int match = sUriMatcher.match(uri);

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(EventsDatabase.Tables.EVENTS);

        //if match is events or events_id, do stuff
        switch (match){
            case EVENTS:
                //do nothing
                break;
            case EVENTS_ID:
                String id = EventsContract.Events.getEventsId(uri);
                queryBuilder.appendWhere(BaseColumns._ID + "=" + id);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    /**
     * Insert Method
     *
     * @param uri Uri to insert in
     * @param values Values to insert
     * @return Returns a Uri
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        //output what we are trying to insert
        Log.v(TAG, "insert(uri=" + uri + ", values" + values.toString());
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match){
            case EVENTS:
                long recordId = db.insertOrThrow(EventsDatabase.Tables.EVENTS, null, values);
                return EventsContract.Events.buildEventUri(String.valueOf(recordId));
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
    }

    /**
     * Update method.
     *
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return Returns int returned from db.update()
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        //output what we are trying to update
        Log.v(TAG, "update(uri=" + uri + ", values" + values.toString());
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);

        String selectionCriteria = selection;
        switch(match){
            case EVENTS:
                //do nothing
                break;
            case EVENTS_ID:
                String id = EventsContract.Events.getEventsId(uri);
                selectionCriteria = BaseColumns._ID + "=" + id
                        + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ")" : "");
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
        return db.update(EventsDatabase.Tables.EVENTS, values, selectionCriteria, selectionArgs);
    }

    /**
     * Delete method.
     *
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return Returns the int returned from db.delete()
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.v(TAG, "delete(uri=" + uri);

        if(uri.equals(EventsContract.URI_TABLE)){
            deleteDatabase();
            return 0;
        }

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match){
            case EVENTS_ID:
                String id = EventsContract.Events.getEventsId(uri);
                String selectionCriteria = BaseColumns._ID + "=" + id
                        + (!TextUtils.isEmpty(selection) ? "AND (" + selection + ")" : "");
                return db.delete(EventsDatabase.Tables.EVENTS, selectionCriteria, selectionArgs);
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
    }
}
