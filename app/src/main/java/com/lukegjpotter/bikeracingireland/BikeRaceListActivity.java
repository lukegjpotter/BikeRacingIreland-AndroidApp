package com.lukegjpotter.bikeracingireland;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.lukegjpotter.bikeracingireland.initialdata.InitialData;
import com.lukegjpotter.bikeracingireland.model.BikeRace;
import com.lukegjpotter.bikeracingireland.service.BikeRaceListViewDataService;
import com.lukegjpotter.bikeracingireland.utils.MonthManager;
import com.lukegjpotter.bikeracingireland.utils.Utils;
import com.lukegjpotter.bikeracingireland.viewadapter.BikeRaceListRecyclerViewAdapter;

import java.util.List;

/**
 * An activity representing a list of BikeRaces. This activity has different presentations for
 * handset and tablet-size devices. On handsets, the activity presents a list of items, which when
 * touched, lead to a {@link BikeRaceDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two vertical panes.
 */
public class BikeRaceListActivity extends AppCompatActivity {

    BikeRaceListViewDataService mDataService;
    // Whether or not the activity is in two-pane mode, i.e. running on a tablet device.
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bikerace_list);

        // Set up the Application Context and the DataService.
        Utils.setApplicationContext(getApplicationContext());
        mDataService = new BikeRaceListViewDataService(Utils.getApplicationContext());

        // Insert Initial Data for testing the app.
        new InitialData(mDataService).insertInitialData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.bikerace_list);
        assert recyclerView != null;
        setupRecyclerView(recyclerView);

        if (findViewById(R.id.bikerace_detail_container) != null) {
            /* The detail container view will be present only in the large-screen layouts
             * (res/values-w900dp). If this view is present, then the activity should be in
             * two-pane mode.
             */
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        // TODO Make the MonthNumber Dynamically changed based on current month and scrolling of the view.
        int monthNumber = MonthManager.currentMonthNumber();
        // TODO Make a Thread out of this call ...Somewhere.
        List<BikeRace> bikeRaces = mDataService.fetchBikeRacesInMonthNumber(monthNumber);
        recyclerView.setAdapter(new BikeRaceListRecyclerViewAdapter(mTwoPane, getSupportFragmentManager(), bikeRaces));
    }
}
