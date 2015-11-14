package com.eventappucsd.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eventappucsd.backend.Date;
import com.eventappucsd.backend.Event;

import java.util.List;

/**
 * Created by user1 on 11/13/15.
 */
public class EventsCustomAdapter extends ArrayAdapter<Event> {

    private LayoutInflater mLayoutInflater;
    private static FragmentManager sFragmentManager;

    public EventsCustomAdapter(Context context, FragmentManager fragmentManager){

        super(context, android.R.layout.simple_list_item_2);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sFragmentManager = fragmentManager;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view;
        if(convertView == null) {
            //// TODO: 11/13/15 create custom layout
            view = mLayoutInflater.inflate(R.layout.custom_event, parent, false);
        } else {
            view = convertView;
        }
        final Event event = getItem(position);
        //final int _id = event.getId();
        final String name = event.getEventName();
        final String date = event.getDate();
        final String location = event.getLocation();

        ((TextView) view.findViewById(R.id.event_name)).setText(name);
        ((TextView) view.findViewById(R.id.event_date)).setText(date);
        ((TextView) view.findViewById(R.id.event_location)).setText(location);
        return view;    
    }
    public void setData(List<Event> events){
        clear();
        if(events != null){
            for(Event event : events){
                add(event);
            }
        }
    }
}
