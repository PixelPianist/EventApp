package com.eventappucsd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.eventappucsd.backend.EventsListFragment;

//import com.eventappucsd.backend.EventsListFragment;

/**
 * Created by Scott on 11/13/15.
 */
public class NewMainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.findFragmentById(android.R.id.content) == null){
            EventsListFragment eventsListFragment = new EventsListFragment();
            fragmentManager.beginTransaction().add(android.R.id.content, eventsListFragment).commit();
        }
    }


}
