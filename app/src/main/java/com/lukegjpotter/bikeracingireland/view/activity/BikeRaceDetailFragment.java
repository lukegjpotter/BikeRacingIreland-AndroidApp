package com.lukegjpotter.bikeracingireland.view.activity;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lukegjpotter.bikeracingireland.R;
import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceWithStageDetails;
import com.lukegjpotter.bikeracingireland.model.entity.StageDetailEntity;
import com.lukegjpotter.bikeracingireland.viewmodel.BikeRaceDetailViewModel;

/**
 * A fragment representing a single BikeRace detail screen.
 * This fragment is either contained in a {@link BikeRaceListActivity} in two-pane mode (on tablets)
 * or a {@link BikeRaceDetailActivity} on handsets.
 */
public class BikeRaceDetailFragment extends Fragment {
    // The fragment argument representing the item ID that this fragment represents.
    public static final String ARG_ITEM_ID = "item_id";

    private BikeRaceWithStageDetails bikeRace;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment (e.g. upon
     * screen orientation changes).
     */
    public BikeRaceDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BikeRaceDetailViewModel bikeRaceDetailViewModel = ViewModelProviders.of(this).get(BikeRaceDetailViewModel.class);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            long bikeRaceId = Long.valueOf(getArguments().getString(ARG_ITEM_ID));
            bikeRaceDetailViewModel.getBikeRace(bikeRaceId).observe(this, bikeRaceWithStageDetails -> bikeRace = bikeRaceWithStageDetails);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.bikerace_detail, container, false);
        binding.setVariable(com.lukegjpotter.bikeracingireland.BR.bikeRace, bikeRace);

        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);

        if (appBarLayout != null) {
            appBarLayout.setTitle(bikeRace.bikeRaceEntity.getEventName());
        }

        setupStageDetailsLayout(binding);

        return binding.getRoot();
    }

    private void setupStageDetailsLayout(ViewDataBinding binding) {

        LinearLayout stageDetailsLayout = binding.getRoot().findViewById(R.id.stage_details_layout);

        // Do not display the views if there is nothing to show.
        if (bikeRace.stageDetails.isEmpty()) {
            stageDetailsLayout.removeAllViews();
            return;
        }

        // Remove the placeholder text.
        stageDetailsLayout.removeView(binding.getRoot().findViewById(R.id.stage_details_placeholder));

        for (StageDetailEntity stageDetail : bikeRace.stageDetails) {
            TextView stageDetailsTextView = new TextView(binding.getRoot().getContext());
            stageDetailsTextView.setText(stageDetail.toString());
            stageDetailsTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            stageDetailsLayout.addView(stageDetailsTextView);
        }
    }
}
