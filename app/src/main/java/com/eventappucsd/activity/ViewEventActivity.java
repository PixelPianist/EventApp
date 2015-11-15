package com.eventappucsd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.eventappucsd.backend.Debug;
import com.eventappucsd.backend.Event;

/**
 * Created by Michael on 11/5/2015.
 */
public class ViewEventActivity extends FragmentActivity{

    protected void onCreate(Bundle savedInstanceState){
        // Magic android stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
    }

    protected void onStart(){
        super.onStart();

        // Get the current intent for extra Strings
        Intent intent = getIntent();

        /*
         * TextViews
         */
        // Name TextView
        TextView name = (TextView) findViewById(R.id.viewEventName);
        name.setText("\n" + intent.getStringExtra(Event.ID_NAME) + "\n");
        // Location TextView
        TextView location = (TextView) findViewById(R.id.viewEventLocation);
        location.setText(intent.getStringExtra(Event.ID_LOCATION));
        // Date TextView
        int dateDay = intent.getIntExtra(Event.ID_DATE_DAY, 16);
        int dateMonth = intent.getIntExtra(Event.ID_DATE_MONTH, 2);
        int dateYear = intent.getIntExtra(Event.ID_DATE_YEAR, 1991);
        TextView date = (TextView) findViewById(R.id.viewEventDate);
        date.setText(dateMonth + " / " + dateDay + " / " + dateYear);
        // Time TextView
        TextView time = (TextView) findViewById(R.id.viewEventTime);
        time.setText(intent.getStringExtra(Event.ID_TIME));
        // Time TextView
        TextView description = (TextView) findViewById(R.id.viewEventDescription);
        description.setText("- Description: " + intent.getStringExtra(Event.ID_DESCRIPTION));
    }
}
