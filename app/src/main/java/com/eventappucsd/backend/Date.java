package com.eventappucsd.backend;

import java.util.regex.Pattern;

/**
 * Created by Rachel on 10/28/2015.
 */
public class Date {
    private int year;
    private int month;
    private int day;

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public Date() {
        this.month = -1;
        this.day = -1;
        this.year = -1;
    }

    // Takes a string object in the form mm/dd/yyyy and creates the according Date object.
    // @param date: Must be in the format mm/dd/yyyy
    // If given invalid input, all three pieces will be set to -1.
    public Date(String date) {
        String[] splitStringArray = date.split(Pattern.quote("/"));
        if(splitStringArray.length != 3) {
            this.month = -1;
            this.day = -1;
            this.year = -1;
            System.out.println("String not formatted correctly");
            return;
        }
        int[] stringToInt = new int[splitStringArray.length];
        for(int i = 0; i < splitStringArray.length; i++) {
            stringToInt[i] = Integer.parseInt(splitStringArray[i]);
        }
        this.month = stringToInt[0];
        this.day = stringToInt[1];
        this.year = stringToInt[2];
    }

    public void setYear(int yearToSet) {
        this.year = yearToSet;
    }

    public void setMonth(int monthToSet) {
        this.month = monthToSet;
    }

    public void setDay(int dayToSet) {
        this.day = dayToSet;
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public String toString() {
        return Integer.toString(getMonth()) + "/" +
                Integer.toString(getDay()) + "/" +
                Integer.toString(getYear());
    }
}
