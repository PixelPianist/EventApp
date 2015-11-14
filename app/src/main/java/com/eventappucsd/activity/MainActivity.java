package com.eventappucsd.activity;

import android.content.Intent;
import android.graphics.Color;
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
public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.findFragmentById(android.R.id.content) == null){
            EventsListFragment eventsListFragment = new EventsListFragment();
            fragmentManager.beginTransaction().add(android.R.id.content, eventsListFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, as long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.addRecord:
                Intent intent = new Intent(MainActivity.this, EnterEventInfoActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

//package com.eventappucsd.activity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.support.v7.app.ActionBarActivity;
//import android.view.KeyEvent;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.LayoutInflater;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.TextView;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.eventappucsd.backend.Event;
//import com.eventappucsd.backend.FakeDB;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//
//    public ArrayList<String> list = new ArrayList<>();
//    StableArrayAdapter adapter;
//    private ListView listview;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        /** Code used to implement list **/
//        listview = (ListView) findViewById(R.id.event_list);
//        adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
//        listview.setAdapter(adapter);
//
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                System.err.println("Clicking Item at position: " + position);
//                adapter.getView(position, view, parent);
//                viewEvent(view, FakeDB.getEvent(position));
//            }
//        });
//        // Action button to add events
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addEvent(view);
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    /**
//     * Called when the user clicks the Add Event button
//     *
//     * @param view The view that was clicked
//     */
//    public void addEvent(View view) {
//        Intent intent = new Intent(this, EnterEventInfoActivity.class);
//        startActivityForResult(intent, 1);
//    }
//
//    public void viewEvent(View view, Event event){
//        Intent intent = new Intent(this, ViewEventActivity.class);
//        // Add all the Strings necessary for displaying the event's info
//        intent.putExtra(Event.ID_NAME, event.getEventName());
//        intent.putExtra(Event.ID_DESCRIPTION, event.getDescription());
//        intent.putExtra(Event.ID_LOCATION, event.getLocation());
//        intent.putExtra(Event.ID_TIME, event.getTime());
//        intent.putExtra(Event.ID_DATE_DAY, event.getDate().getDay());
//        intent.putExtra(Event.ID_DATE_MONTH, event.getDate().getMonth());
//        intent.putExtra(Event.ID_DATE_YEAR, event.getDate().getYear());
//        intent.putExtra(Event.ID_NUM_VOTES, event.getNumVotes());
//        startActivity(intent);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == 1) {
//            list.add(data.getStringExtra("event"));
//            listview = (ListView) findViewById(R.id.event_list);
//            adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
//            listview.setAdapter(adapter);
//        }
//        else{
//            //do nothing
//            System.out.println("Back button pressed on add event");
//        }
//    }
//
//    public class StableArrayAdapter extends ArrayAdapter<String> {
//
//        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
//        public StableArrayAdapter(Context context, int textViewResourceId,
//                                  List<String> objects) {
//
//            super(context, textViewResourceId, objects);
//            for (int i = 0; i < objects.size(); ++i) {
//                mIdMap.put(objects.get(i), i);
//            }
//        }
//
//        @Override
//        public long getItemId(int position) {
//            String item = getItem(position);
//            return mIdMap.get(item);
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return true;
//        }
//
//        public View getView(int position, View convertView, ViewGroup parent) {
//            // Get the data item for this position
//            String item = getItem(position);
//
//            // Check if an existing view is being reused, otherwise inflate the view
//            if (convertView == null) {
//                convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_item_list, parent, false);
//            }
//            // Lookup view for data population
//            TextView itemName = (TextView) convertView.findViewById(R.id.itemName);
//
//            // Populate the data into the template view using the data object
//            itemName.setText(item);
//
//            ImageButton upvoteButton = (ImageButton) convertView.findViewById(R.id.upbtn);
//            // Needed in order to have both the button and the list item clickable
//            upvoteButton.setFocusable(false);
//            upvoteButton.setFocusableInTouchMode(false);
//            upvoteButton.setClickable(true);
//
//            // Store the event's position in the tag to be used when it's clicked.
//            upvoteButton.setTag(position);
//            upvoteButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Event event = FakeDB.getEvent((Integer) view.getTag()); // gets position stored
//                    //TODO: Make it so that one phone can only upvote an event once.
//                    event.incrementNumVotes();
//                    Toast.makeText(getContext(), "Event pos: " + view.getTag() + " num votes: " + event.getNumVotes(),
//                            Toast.LENGTH_SHORT).show();
//                }
//            });
//            // Return the completed view to render on screen
//            return convertView;
//        }
//    }
//}
