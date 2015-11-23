package com.eventappucsd.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.eventappucsd.backend.EventsListFragment;

/**
 * Created by Scott on 11/13/15.
 */
public class MainActivity extends FragmentActivity {

    /*
     * Named Constants
     */
    private static final int VOTE_SORT  = 1;
    private static final int DATE_SORT  = 2;
    private static final int ALPHA_SORT = 3;
    /*
     * Static Variables
     */
    public static int globalSortSet = VOTE_SORT;
    /*
     * Instance Variables
     */
    private ContentResolver mContentResolver;

    /*
    Fragment manager used for launching fragment activities.
    Use this for launching new fragments
    TODO viewEvent, peanutButton
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.findFragmentById(android.R.id.content) == null){
            EventsListFragment eventsListFragment = new EventsListFragment();
            fragmentManager.beginTransaction().add(android.R.id.content, eventsListFragment).commit();
        }        System.out.println("MAIN ACTIVITY ONCREATE\n\n");
    }

    /*
    launch the menu layout menu_main
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
    adding the event button is implemented from here, implement other
    option buttons from here.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, as long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.addRecord:
                Intent intentRec = new Intent(MainActivity.this, EnterEventInfoActivity.class);
                startActivity(intentRec);
                break;
            case R.id.sortByPopularity:
                this.globalSortSet = VOTE_SORT;
                Intent intentVote = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intentVote);
                finish();
                break;
            case R.id.sortByDate:
                this.globalSortSet = DATE_SORT;
                Intent intentDate = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intentDate);
                finish();
                break;
            case R.id.sortByName:
                this.globalSortSet = ALPHA_SORT;
                Intent intentAlpha = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intentAlpha);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Returns the current desired sorting scheme.
    public static int getSortType() {
        return globalSortSet;
    }
}