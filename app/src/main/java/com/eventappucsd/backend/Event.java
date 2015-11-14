package com.eventappucsd.backend;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rachel on 10/22/2015.
 * A class to represent a single event that can be added and viewed by users.
 */

public class Event{
    /*
     * Named Constants
     */
    public static final String ID_NAME = "eventName";
    public static final String ID_DESCRIPTION = "description";
    public static final String ID_LOCATION = "location";
    public static final String ID_DATE_MONTH = "date.month";
    public static final String ID_DATE_DAY = "date.day";
    public static final String ID_DATE_YEAR = "date.year";
    public static final String ID_TIME = "time";
    public static final String ID_NUM_VOTES = "numVotes";

    /*
     * Instance Variables
     */
    private int _id;
    private String eventName;
    private String description;
    private String location; // is this the best way to do this?
    private String date; //can be constructed with three ints - month, date, year
    //private Date date   TODO refactor date formatting
    private String time;
    private int numVotes;

//    public Event() {
//        this._id = '';
//        this.eventName = "";
//        this.description = "";
//        this.location = "";
//        this.date = new Date();
//        this.time = "";
//    }

    public Event(int _id, String eventName, String description, String location,
                 String date, String time, int numVotes){
        this._id = _id;
        this.eventName = eventName;
        this.description = description;
        this.location = location;
        this.date = date;
        //this.date = new Date();
        this.time = time;
        this.numVotes = numVotes;

    }
    public void setId(int _id){ this._id = _id; }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) { this.date = date;}

    public void setTime(String time) {
        this.time = time;
    }

    public int getId(){ return _id; }

    public String getEventName() {
        return this.eventName;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDate() { return this.date; }

    public String getTime() {
        return this.time;
    }

    public String getLocation() { return this.location;}

    public int getNumVotes() {
        return this.numVotes;
    }

    public void incrementNumVotes() {
        this.numVotes++;
    }
}
//    public String toString() {
//        return "Event name is: " + this.eventName +
//                " Date is: " + this.date.toString() +
//                " Time is: " + this.getTime() +
//                " Location is: " + this.getLocation() +
//                " Description is: " + this.getDescription() +
//                " id is: " + this.get_id() + "\n";
//    }


