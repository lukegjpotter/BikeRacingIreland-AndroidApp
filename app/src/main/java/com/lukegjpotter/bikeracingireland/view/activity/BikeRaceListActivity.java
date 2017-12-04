package com.lukegjpotter.bikeracingireland.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.lukegjpotter.bikeracingireland.R;
import com.lukegjpotter.bikeracingireland.model.BikeRace;
import com.lukegjpotter.bikeracingireland.model.entity.ProfileFilterEntity;
import com.lukegjpotter.bikeracingireland.model.roomdatabase.ApplicationDatabase;
import com.lukegjpotter.bikeracingireland.model.roomdatabase.util.DatabaseInitializer;
import com.lukegjpotter.bikeracingireland.service.BikeRaceListViewDataService;
import com.lukegjpotter.bikeracingireland.utils.MonthManager;
import com.lukegjpotter.bikeracingireland.utils.Utils;
import com.lukegjpotter.bikeracingireland.view.adapter.BikeRaceListRecyclerViewAdapter;
import com.lukegjpotter.bikeracingireland.viewmodel.ProfileFilterViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of BikeRaces. This activity has different presentations for
 * handset and tablet-size devices. On handsets, the activity presents a list of items, which when
 * touched, lead to a {@link BikeRaceDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two vertical panes.
 */
public class BikeRaceListActivity extends AppCompatActivity {

    BikeRaceListViewDataService mDataService;
    RecyclerView mRecyclerView;
    private ProfileFilterViewModel profileFilterViewModel;
    // Whether or not the activity is in two-pane mode, i.e. running on a tablet device.
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bikerace_list);

        // Insert Initial Data for testing the app.
        DatabaseInitializer.populateAsync(ApplicationDatabase.getInstance(getApplicationContext()));

        // Setup ProfileFilterViewModel.
        profileFilterViewModel = ViewModelProviders.of(this).get(ProfileFilterViewModel.class);
        profileFilterViewModel.getProfileFilter().observe(this, profileFilterEntity -> profileFilterEntity = new ProfileFilterEntity(profileFilterEntity));

        // Set up the Application Context and the DataService.
        Utils.setApplicationContext(getApplicationContext());
        mDataService = new BikeRaceListViewDataService(Utils.getApplicationContext());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        mRecyclerView = findViewById(R.id.bikerace_list);
        assert mRecyclerView != null;
        setupRecyclerView();

        if (findViewById(R.id.bikerace_detail_container) != null) {
            /* The detail container view will be present only in the large-screen layouts
             * (res/values-w900dp). If this view is present, then the activity should be in
             * two-pane mode.
             */
            mTwoPane = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_bikerace_list_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_profile_filter:
                profileFilterSelected(item);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupRecyclerView() {
        // TODO Make the MonthNumber Dynamically changed based on current month and scrolling of the view.
        int monthNumber = MonthManager.currentMonthNumber();
        // TODO Make a Thread out of this call ...Somewhere.
        List<BikeRace> bikeRaces = mDataService.fetchBikeRacesInMonthNumber(monthNumber);
        mRecyclerView.setAdapter(new BikeRaceListRecyclerViewAdapter(mTwoPane, getSupportFragmentManager(), bikeRaces));
    }

    private void profileFilterSelected(MenuItem profileFilterMenuItem) {
        if (profileFilterViewModel.isEnabled()) {
            // Disable ProfileFilter, as it is already enabled.
            profileFilterMenuItem.setIcon(R.drawable.ic_face_black_48dp);
            profileFilterViewModel.setEnabled(false);

            List<BikeRace> bikeRacesInMonths = new ArrayList<>();

            for (int monthNumber : MonthManager.getMonthsInListView()) {
                bikeRacesInMonths = mDataService.fetchBikeRacesInMonthNumber(monthNumber);
            }

            mRecyclerView.setAdapter(new BikeRaceListRecyclerViewAdapter(mTwoPane, getSupportFragmentManager(), bikeRacesInMonths));

        } else {
            // Enable ProfileFilter, as it is disabled.
            if (!profileFilterViewModel.isPopulated()) {
                setupProfileFilter();
            }

            List<BikeRace> bikeRacesForProfileFilter = mDataService.fetchBikeRacesForProfileFilter();
            mRecyclerView.setAdapter(new BikeRaceListRecyclerViewAdapter(mTwoPane, getSupportFragmentManager(), bikeRacesForProfileFilter));

            profileFilterMenuItem.setIcon(R.drawable.ic_face_white_48dp);
            profileFilterViewModel.setEnabled(true);
        }
    }

    private void setupProfileFilter() {
        // TODO Implement This Properly with User Set Settings via a dialog and save to database.
        ProfileFilterEntity profileFilterEntity = profileFilterViewModel.getProfileFilter().getValue();
        profileFilterViewModel.updateProfileFilter(profileFilterEntity);
    }
}
