package com.lukegjpotter.bikeracingireland.view.activity;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.lukegjpotter.bikeracingireland.R;
import com.lukegjpotter.bikeracingireland.constant.Constants;
import com.lukegjpotter.bikeracingireland.databinding.ActivityBikeraceListBinding;
import com.lukegjpotter.bikeracingireland.model.entity.ProfileFilterEntity;
import com.lukegjpotter.bikeracingireland.model.roomdatabase.ApplicationDatabase;
import com.lukegjpotter.bikeracingireland.model.roomdatabase.util.DatabaseInitializer;
import com.lukegjpotter.bikeracingireland.repository.retrofit.BikeRaceRetrofitRepository;
import com.lukegjpotter.bikeracingireland.utils.MonthManager;
import com.lukegjpotter.bikeracingireland.view.adapter.BikeRaceListRecyclerViewAdapter;
import com.lukegjpotter.bikeracingireland.viewmodel.BikeRaceListViewModel;
import com.lukegjpotter.bikeracingireland.viewmodel.ProfileFilterViewModel;

import java.util.ArrayList;

/**
 * An activity representing a list of BikeRaces. This activity has different presentations for
 * handset and tablet-size devices. On handsets, the activity presents a list of items, which when
 * touched, lead to a {@link BikeRaceDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two vertical panes.
 */
public class BikeRaceListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private ProfileFilterViewModel profileFilterViewModel;
    private BikeRaceListViewModel bikeRaceListViewModel;
    // Whether or not the activity is in two-pane mode, i.e. running on a tablet device.
    private boolean mTwoPane;
    private ActivityBikeraceListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Android RunTimePermissions Request.
        setupAndroidRunTimePermissionsAndDependencies();

        // The detail container view will be present only in the large-screen layouts (res/values-w900dp).
        // If this view is present, then the activity should be in two-pane mode.
        if (findViewById(R.id.bikerace_detail_container) != null) mTwoPane = true;

        binding = DataBindingUtil.setContentView(this, R.layout.activity_bikerace_list);

        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitle(getTitle());

        BikeRaceListRecyclerViewAdapter recyclerViewAdapter = new BikeRaceListRecyclerViewAdapter(mTwoPane, getSupportFragmentManager(), new ArrayList<>());
        mRecyclerView = findViewById(R.id.bikerace_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(recyclerViewAdapter);

        recyclerViewAdapter.setOnBottomReachedListener(position -> {
            bikeRaceListViewModel.loadFollowingMonthsBikeRaces();
            MonthManager.reachedBottomOfListView();
        });

        // Insert Initial Data for testing the app.
        DatabaseInitializer.populateAsync(ApplicationDatabase.getInstance(getApplicationContext()));

        // Setup ProfileFilterViewModel.
        profileFilterViewModel = ViewModelProviders.of(this).get(ProfileFilterViewModel.class);
        profileFilterViewModel.getProfileFilter().observe(this, profileFilterEntity -> profileFilterEntity = new ProfileFilterEntity(profileFilterEntity));

        bikeRaceListViewModel = ViewModelProviders.of(this).get(BikeRaceListViewModel.class);
        bikeRaceListViewModel.getBikeRaces().observe(this, recyclerViewAdapter::setBikeRaces);
    }

    private void setupAndroidRunTimePermissionsAndDependencies() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
            // GRANTED: Permission to access the INTERNET, initialize Retrofit.
            BikeRaceRetrofitRepository.getInstance();
        } else {
            // REQUEST: Permission to access the INTERNET.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {
                // DENIED: State why this app needs the Internet permission.
                Snackbar.make(binding.frameLayout,
                        "Internet access is required to get Bike Race Data.",
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK", view -> {
                            // Request the permission
                            ActivityCompat.requestPermissions(this,
                                    new String[]{Manifest.permission.INTERNET},
                                    Constants.MY_PERMISSIONS_INTERNET);
                        }).show();
            } else {
                // REQUEST: First Time.
                Snackbar.make(binding.frameLayout,
                        "Permission is not available. Requesting Internet permission.",
                        Snackbar.LENGTH_SHORT)
                        .show();

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, Constants.MY_PERMISSIONS_INTERNET);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Constants.MY_PERMISSIONS_INTERNET) {
            // Request for internet permission.
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted. Initialze Retrofit.
                BikeRaceRetrofitRepository.getInstance();
            } else {
                // Permission request was denied.
                Snackbar.make(binding.frameLayout,
                        "Internet permission request was denied.",
                        Snackbar.LENGTH_SHORT)
                        .show();
            }
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

    private void profileFilterSelected(MenuItem profileFilterMenuItem) {
        if (profileFilterViewModel.isEnabled()) {
            // Disable ProfileFilter, as it is already enabled.
            profileFilterMenuItem.setIcon(R.drawable.ic_face_black_48dp);
            profileFilterViewModel.setEnabled(false);

            bikeRaceListViewModel.setBikeRacesToMonths();

        } else {
            // Enable ProfileFilter, as it is disabled.
            if (!profileFilterViewModel.isPopulated()) {
                // TODO Implement This Properly with User Set Settings via a dialog and save to database.
                ProfileFilterEntity profileFilterEntity = profileFilterViewModel.getProfileFilter().getValue();
                profileFilterViewModel.updateProfileFilter(profileFilterEntity);
            }

            profileFilterMenuItem.setIcon(R.drawable.ic_face_white_48dp);
            profileFilterViewModel.setEnabled(true);

            bikeRaceListViewModel.setBikeRacesToProfileFilterAndMonths();
        }
    }
}
