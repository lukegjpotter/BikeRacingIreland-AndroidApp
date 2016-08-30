package com.lukegjpotter.bikeracingireland;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lukegjpotter.bikeracingireland.model.BikeRace;
import com.lukegjpotter.bikeracingireland.service.BikeRaceListViewDataService;
import com.lukegjpotter.bikeracingireland.utils.Utils;

/**
 * A fragment representing a single BikeRace detail screen.
 * This fragment is either contained in a {@link BikeRaceListActivity} in two-pane mode (on tablets)
 * or a {@link BikeRaceDetailActivity} on handsets.
 */
public class BikeRaceDetailFragment extends Fragment {
    // The fragment argument representing the item ID that this fragment represents.
    public static final String ARG_ITEM_ID = "item_id";

    private BikeRace mBikeRace;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BikeRaceDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            long databasePk = getArguments().getLong(ARG_ITEM_ID);
            // TODO In a real-world scenario, use a Loader to load content from a content provider.
            BikeRaceListViewDataService dataService = new BikeRaceListViewDataService(Utils.getApplicationContext());
            mBikeRace = dataService.fetchBikeRaceByPk(databasePk);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mBikeRace.getEventName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bikerace_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mBikeRace != null) {
            ((TextView) rootView.findViewById(R.id.bikerace_detail)).setText(mBikeRace.toString());
        }

        return rootView;
    }
}
