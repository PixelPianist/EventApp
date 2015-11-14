package com.eventappucsd.activity;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Scott on 11/10/15.
 */
public class EventsContract {
    interface EventsColumns{
        String EVENTS_ID = "_id";
        String EVENTS_NAME = "events_name";
        String EVENTS_DESCRIPTION = "events_description";
        String EVENTS_LOCATION = "events_location";
        String EVENTS_DATE = "events_date";
        String EVENTS_TIME = "events_time";
        String EVENTS_NUM_VOTES = "num_votes";
    }

    public static final String CONTENT_AUTHORITY = "com.eventappucsd.activity.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final String PATH_EVENTS = "events";
    public static final Uri URI_TABLE = Uri.parse(BASE_CONTENT_URI.toString() + "/" + PATH_EVENTS);

    public static final String[] TOP_LEVEL_PATHS = {
            PATH_EVENTS
    };

    public static class Events implements EventsColumns, BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendEncodedPath(PATH_EVENTS).build();

        //defining the ability to access more than one record (list view) for the content provider
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + ".events";
        //single record
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + ".events";

        //building a valid uri for a particular record
        public static Uri buildEventUri(String eventId){
            return CONTENT_URI.buildUpon().appendEncodedPath(eventId).build();
        }

        //getter for extracting a particular id
        public static String getEventsId(Uri uri){
            return uri.getPathSegments().get(1);
        }
    }
}
