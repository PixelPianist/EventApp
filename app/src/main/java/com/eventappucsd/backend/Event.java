package com.eventappucsd.backend;

/**
 * Created by Rachel on 10/22/2015.
 * A class to represent a single event that can be added and viewed by users.
 */

public class Event {
    private String eventName;
    private String description;
    private String location; // is this the best way to do this?
    private Date date; //can be constructed with three ints - month, date, year
    private int numVotes;

    public Event() {
        this.eventName = "";
        this.description = "";
        this.location = "";
        this.date = new Date();
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getEventName() {
        return this.eventName;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLocation() {
        return this.location;
    }

    public Date getDate() {
        return this.date;
    }

    public int getNumVotes() {
        return this.numVotes;
    }

    public void incrementNumVotes() {
        this.numVotes++;
    }

    public String toString() {
        return "Event name is: " + this.eventName +
                "\nDate is: " + this.date.toString() +
                "\nLocation is: " + this.getLocation() +
                "\nDescription is: " + this.getDescription();
    }

}
