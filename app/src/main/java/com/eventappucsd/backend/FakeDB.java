package com.eventappucsd.backend;

import com.eventappucsd.activity.MainActivity;

import java.util.ArrayList;

/**
 * Created by Pete on 10/31/15.
 *
 * A fake DB for testing purposes.
 */
public class FakeDB extends MainActivity {

    private static ArrayList<Event> eventCheat = new ArrayList<>();
    static int dbSize = 0;

    public static void add(Event currentEvent){
        eventCheat.add(currentEvent);
    }

    public static void printEvent(int index){
        //Just printing the event name for now because I'm lazy.
        System.out.println(eventCheat.get(index).getEventName());
    }

    public static String getEventName(int index){
        return eventCheat.get(index).getEventName();
    }

    public static Event getEvent(int position){
        if(position < eventCheat.size() && position >= 0){
            return eventCheat.get(position);
        }
        else{
            System.err.println(Debug.CRITICAL_ERROR + "position queried from FakeDB is out "
                + "of scope!");
            return null;
        }
    }
}
