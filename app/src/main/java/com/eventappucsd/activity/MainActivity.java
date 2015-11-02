package com.eventappucsd.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.eventappucsd.backend.Event;
import com.eventappucsd.backend.FakeDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        list.add("Hackathon \n11/10/15");
        list.add("Super Awesome UCSD Party 1\n11/11/15");
        list.add("Sun God or whatever its called... 1\n11/20/15");
        list.add("Free Thanksgiving food\n11/26/15");
        list.add("Super Awesome UCSD Party 2\n11/10/15");
        list.add("Super Awesome UCSD Party 3\n11/10/15");
        list.add("Pre-finals bi*$h fest\n12/01/15");
        list.add("Super Awesome UCSD Party 4\n12/10/15");
        list.add("Super Awesome UCSD Party 5\n12/11/15");
        list.add("Super Awesome UCSD Party 6\n12/12/15");
        list.add("Super Awesome UCSD Party 7\n12/13/15");
        list.add("HO HO HO MEEEERY CHRISTMAS!\n12/25/15");
        list.add("Happy New Years!!!!!\n01/01/16");


        /** Code used to implement list **/
        final ListView listview = (ListView) findViewById(R.id.event_list);

        StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        listview.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                addEvent(view);
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when the user clicks the Add Event button
     *
     * @param view The view that was clicked
     */
    public void addEvent(View view) {
        Intent intent = new Intent(this, EnterEventInfoActivity.class);
        startActivityForResult(intent, 1);
    }

    public class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {

            super(context, textViewResourceId, objects);

            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
