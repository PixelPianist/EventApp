package com.eventappucsd.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.eventappucsd.activity.EventsContract;

/**
 * The database holding all the events.
 *
 * @author Scott Miller
 * @date 11/10/15.
 * @version v1.0
 */
public class EventsDatabase extends SQLiteOpenHelper {
    /*
     * Named constants
     */
    //private static final String TAG = FriendsDatabase.class.getSimpleName();
    //name of the db
    private static final String DATABASE_NAME = "events.db";
    //for upgrades
    private static final int DATABASE_VERSION = 2;

    /*
     * Instance vars
     */
    private final Context mContext;

    //database name, interface will enforce a database name any time we build a EventsDB
    interface Tables{
        String EVENTS = "events";
    }
    /**
     * Constructor taking a context as a parameter, saves this context
     * as an instance var.
     *
     * @param context
     */
    public EventsDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }
    /*
    must implement abstract methods from SQLiteDatabase and SQLiteOpenHelper
     */

    /**
     * OnCreate will create the db, overrides parent's onCreate
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Tables.EVENTS + " ("
        + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
        + EventsContract.EventsColumns.EVENTS_NAME + " TEXT NOT NULL,"
        + EventsContract.EventsColumns.EVENTS_DESCRIPTION + " TEXT NOT NULL,"
        + EventsContract.EventsColumns.EVENTS_LOCATION + " TEXT NOT NULL,"
        + EventsContract.EventsColumns.EVENTS_DATE + " TEXT NOT NULL,"
        + EventsContract.EventsColumns.EVENTS_TIME + " TEXT NOT NULL,"
        + EventsContract.EventsColumns.EVENTS_NUM_VOTES + " TEXT NOT NULL)");
        System.out.println("EVENTSDBre ONCREATE\n\n");
    }

    /**
     * OnUpgrade will handle if the DB is already setup
     * Since Android devices enable a user to go into the settings and clear all data at any time
     * this will prevent the DB from being erased and the app to crash.
     * This will also handle the update to the new version without deleting any of their existing
     * data.
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        int version = oldVersion;
        if(version == 1){
            //Add some extra fields to the database without deleting existing
            //data if needed
            version = 2;
        }
        //delete the tables if there is some sort of problem, or if you don't want the old data
        if(version != DATABASE_VERSION){
            db.execSQL("DROP TABLE IF EXISTS " + Tables.EVENTS);
            onCreate(db);
        }
    }
    /**
     * deleteDatabase method will delete the database
     *
     * @param context
     */
    public static void deleteDataBase(Context context){
        context.deleteDatabase(DATABASE_NAME);
    }
}
