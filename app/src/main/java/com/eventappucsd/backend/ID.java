package com.eventappucsd.backend;

import android.content.Context;
import android.provider.Settings;

/**
 * Created by Donquixote Dude on 10/26/2015.
 * A class to get the ID of the user. Simply call the method getID() and a unique identifier
 * should be returned that the phone received at first system boot.
 */
public class ID {
    static Context context;

    public static String getID(){
        String ID = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        if(ID == null){
            System.err.println(Debug.CRITICAL_ERROR + "Android unique identifier returned null!");
        }

        return ID;
    }
}
