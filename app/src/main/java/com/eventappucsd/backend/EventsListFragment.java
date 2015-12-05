package com.eventappucsd.backend;


import android.content.ContentResolver;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.eventappucsd.activity.EventsContract;
import com.eventappucsd.activity.EventsCustomAdapter;
import com.eventappucsd.activity.EventsListLoader;

import java.util.List;

/**
 * @author Scott
 * @date 11/13/15
 * @version v1.0
 */
public class EventsListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<Event>>{
    /*
     * Named Constants
     */
    private static final String LOG_TAG = EventsListFragment.class.getSimpleName();
    private static final int LOADER_ID = 1;

    /*
     * Instance Vars
     */
    private EventsCustomAdapter mAdapter;
    private ContentResolver mContentResolver;
    public List<Event> mEvents;

    /**
     * Called when the activity is created
     *
     * @since v1.0
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mContentResolver = getActivity().getContentResolver();
        //this will put together the fragments on the screen
        mAdapter = new EventsCustomAdapter(getActivity(),getChildFragmentManager());

        //default for an empty list
        setEmptyText("No Events");

        setListAdapter(mAdapter);
        setListShown(false);
        //use this class to send us the results
        getLoaderManager().initLoader(LOADER_ID, null, this);

    }

    /**
     * Called when this class is created.
     *
     * @since v1.0
     * @param id
     * @param args
     * @return
     */
    @Override
    public Loader<List<Event>> onCreateLoader(int id, Bundle args) {
        mContentResolver = getActivity().getContentResolver();
        return new EventsListLoader(getActivity(), EventsContract.URI_TABLE, mContentResolver);
    }

    /**
     * Called when the load is finished
     *
     * @since v1.0
     * @param loader
     * @param events
     */
    @Override
    public void onLoadFinished(Loader<List<Event>> loader, List<Event> events) {
        mAdapter.setData(events);
        mEvents = events;
        if(isResumed()){
            setListShown(true);
        }else{
            setListShownNoAnimation(true);
        }

    }

    /**
     * Called when the loader is reset
     *
     * @since v1.0
     * @param loader
     */
    @Override
    public void onLoaderReset(Loader<List<Event>> loader) {
        mAdapter.setData(null);
    }
}
