package com.eventappucsd.backend;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.util.UUID;

/**
 * Created by Donquixote Dude on 10/26/2015.
 * A class to get the ID of the user. Simply call the method getID() and a unique identifier
 * should be returned that the phone received at first system boot.
 *
 * May return null if used on a tablet!!
 */
public class ID {
    static Context context;

    public static String getID(Context context, ContentResolver content){
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(content, android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String id = deviceUuid.toString();

        if(id == null){
            System.err.println(Debug.CRITICAL_ERROR + "Android unique identifier returned null!");
        }

        return id;
    }
}