package com.mycompany.mikesapplication;

import java.util.GregorianCalendar;

/**
 * Created by Rachel on 10/22/2015.
 * A class to represent a single event that can be added and viewed by users.
 */

public class Event {
    private String event_name;
    private String description;
    private String location; // is this the best way to do this?
    private GregorianCalendar date; //can be constructed with three ints - month, date, year
    private int numVotes;

    public Event(String event_name, String description, String location, GregorianCalendar date) {
        this.event_name = event_name;
        this.description = description;
        this.location = location;
        this.date = date;
        this.numVotes = 0;
    }

    public String getEventName() {
        return this.event_name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLocation() {
        return this.location;
    }

    public GregorianCalendar getDate() {
        return this.date;
    }

    public int getNumVotes() {
        return this.numVotes;
    }

    public void incrementNumVotes() {
        this.numVotes++;
    }

}
