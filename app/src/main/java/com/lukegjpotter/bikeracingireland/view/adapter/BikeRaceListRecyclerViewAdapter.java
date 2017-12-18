package com.lukegjpotter.bikeracingireland.view.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lukegjpotter.bikeracingireland.R;
import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceWithStageDetails;
import com.lukegjpotter.bikeracingireland.utils.Utils;
import com.lukegjpotter.bikeracingireland.view.holder.BikeRaceListCardViewHolder;
import com.lukegjpotter.bikeracingireland.view.listeners.BikeRaceListCardOnClickListener;
import com.lukegjpotter.bikeracingireland.view.listeners.OnBottomReachedListener;

import java.util.List;

/**
 * The RecyclerViewAdapter for the {@code BikeRaceListActivity}.
 */
public class BikeRaceListRecyclerViewAdapter extends RecyclerView.Adapter<BikeRaceListCardViewHolder> {

    private FragmentManager mFragmentManager;
    private List<BikeRaceWithStageDetails> bikeRaces;
    private boolean mIsTwoPaneLayout;
    private OnBottomReachedListener onBottomReachedListener;

    public BikeRaceListRecyclerViewAdapter(boolean isTwoPaneLayout, FragmentManager fragmentManager, List<BikeRaceWithStageDetails> bikeRaces) {
        mIsTwoPaneLayout = isTwoPaneLayout;
        mFragmentManager = fragmentManager;
        this.bikeRaces = bikeRaces;
    }

    @Override
    public BikeRaceListCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bikerace_list_content, parent, false);
        return new BikeRaceListCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BikeRaceListCardViewHolder holder, int position) {

        if (position == bikeRaces.size() - 1) {
            onBottomReachedListener.onBottomReached(position);
        }

        holder.mBikeRaceDatabasePk = bikeRaces.get(position).bikeRaceEntity.getPkBikeRaceEntityId();
        BikeRaceListCardOnClickListener cardOnClickListener = new BikeRaceListCardOnClickListener(mIsTwoPaneLayout, mFragmentManager, holder.mBikeRaceDatabasePk);
        holder.mCardView.setOnClickListener(cardOnClickListener);

        /*holder.mRouteMap;*/ // TODO: Use the Strava API to Populate the MapView

        holder.mEventName.setText(bikeRaces.get(position).bikeRaceEntity.getEventName());
        holder.mPromotingClub.setText(bikeRaces.get(position).bikeRaceEntity.getPromotingClub());
        holder.mLocation.setText(bikeRaces.get(position).bikeRaceEntity.getLocation());
        holder.mStartDate.setText(Utils.convertDateToString(bikeRaces.get(position).bikeRaceEntity.getStartDate()));
    }

    @Override
    public int getItemCount() {
        return bikeRaces.size();
    }

    public void setBikeRaces(List<BikeRaceWithStageDetails> BikeRaces) {
        this.bikeRaces = BikeRaces;
    }

    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener;
    }
}
