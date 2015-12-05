package com.eventappucsd.backend;

import com.eventappucsd.activity.MainActivity;

import java.util.ArrayList;

/**
 * A fake DB for testing purposes
 *
 * @author Pete
 * @date 10/31/15
 * @version v1.0
 */
public class FakeDB extends MainActivity {
    /*
     * Static vars
     */
    private static ArrayList<Event> eventCheat = new ArrayList<>();
    static int dbSize = 0;

    /**
     * Adds an event to the DB
     *
     * @param currentEvent
     */
    public static void add(Event currentEvent){
        eventCheat.add(currentEvent);
    }

    /**
     * Prints out an event at the index
     *
     * @since v1.0
     * @param index
     */
    public static void printEvent(int index){
        //Just printing the event name for now because I'm lazy.
        System.out.println(eventCheat.get(index).getEventName());
    }

    /**
     * Getter
     *
     * @since v1.0
     * @param index
     * @return
     */
    public static String getEventName(int index){
        return eventCheat.get(index).getEventName();
    }

    /**
     * Getter
     *
     * @since v1.0
     * @param position
     * @return
     */
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
