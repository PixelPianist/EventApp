package com.eventappucsd.activity;

import com.eventappucsd.activity.MainActivity;
import com.eventappucsd.backend.Event;
import com.eventappucsd.backend.EventsListFragment;
import com.eventappucsd.backend.TimeSetter;

import android.app.Activity;
import android.app.ListFragment;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.DataInteraction;
import android.test.TouchUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.EditText;
import junit.framework.TestCase;
import java.io.*;

import static org.hamcrest.Matchers.anything;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.equalTo;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;

import java.util.Map;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;

import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Pete on 12/2/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2 {


    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        Activity activity = getActivity();
        assertNotNull(activity);
    }

    public void testAddEvent() {
        Activity main = getActivity();

        //Start adding the event.
//        onView(withId(R.id.addRecord)).perform(click());
//
//        onView(ViewMatchers.withId(R.id.editTextEventName)).perform(typeText("A Random Event"), closeSoftKeyboard());
//
//        onView(ViewMatchers.withId(R.id.editTextDate)).perform(click());
//        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2016, 1, 17));
//        onView(withText("OK")).perform(click());
//
//        onView(ViewMatchers.withId(R.id.editTextTime)).perform(click());
//        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(18, 00));
//        onView(withText("OK")).perform(click());
//
//
//        onView(ViewMatchers.withId(R.id.editTextDescription)).perform(typeText("A random event for testing purposes"), closeSoftKeyboard());
//
//        onView(ViewMatchers.withId(R.id.editTextLocation)).perform(typeText("UCSD"), closeSoftKeyboard());
//
//        onView(withId(R.id.addRecord)).perform(click());
//        //Event added by thispoint.
//
//        //Test different sorts.
//        onView(withId(R.id.sortByDate)).perform(click());
//        onView(withId(R.id.sortByName)).perform(click());
//        onView(withId(R.id.sortByPopularity)).perform(click());


        onData(ViewMatchers.withId(R.id.event_list)).atPosition(0).perform(click());


    }
}