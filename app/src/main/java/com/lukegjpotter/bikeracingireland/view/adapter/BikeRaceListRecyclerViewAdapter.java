package com.lukegjpotter.bikeracingireland.view.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lukegjpotter.bikeracingireland.R;
import com.lukegjpotter.bikeracingireland.model.BikeRace;
import com.lukegjpotter.bikeracingireland.utils.Utils;
import com.lukegjpotter.bikeracingireland.view.holder.BikeRaceListCardViewHolder;
import com.lukegjpotter.bikeracingireland.view.listeners.BikeRaceListCardOnClickListener;

import java.util.List;

/**
 * The RecyclerViewAdapter for the {@code BikeRaceListActivity}.
 */
public class BikeRaceListRecyclerViewAdapter extends RecyclerView.Adapter<BikeRaceListCardViewHolder> {

    private FragmentManager mFragmentManager;
    private List<BikeRace> mBikeRaces;
    private boolean mIsTwoPaneLayout;

    public BikeRaceListRecyclerViewAdapter(boolean isTwoPaneLayout, FragmentManager fragmentManager, List<BikeRace> bikeRaces) {
        mIsTwoPaneLayout = isTwoPaneLayout;
        mFragmentManager = fragmentManager;
        mBikeRaces = bikeRaces;
    }

    @Override
    public BikeRaceListCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bikerace_list_content, parent, false);
        return new BikeRaceListCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BikeRaceListCardViewHolder holder, int position) {
        holder.mBikeRaceDatabasePk = mBikeRaces.get(position).getId();
        BikeRaceListCardOnClickListener cardOnClickListener = new BikeRaceListCardOnClickListener(mIsTwoPaneLayout, mFragmentManager, holder.mBikeRaceDatabasePk);
        holder.mCardView.setOnClickListener(cardOnClickListener);

        /*holder.mRouteMap;*/ // TODO: Use the Strava API to Populate the MapView

        holder.mEventName.setText(mBikeRaces.get(position).getEventName());
        holder.mPromotingClub.setText(mBikeRaces.get(position).getPromotingClub());
        holder.mLocation.setText(mBikeRaces.get(position).getLocation());
        holder.mStartDate.setText(Utils.convertDateToString(mBikeRaces.get(position).getStartDate()));
    }

    @Override
    public int getItemCount() {
        return mBikeRaces.size();
    }
}
