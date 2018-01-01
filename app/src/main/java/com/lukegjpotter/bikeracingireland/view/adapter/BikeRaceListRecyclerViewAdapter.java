package com.lukegjpotter.bikeracingireland.view.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lukegjpotter.bikeracingireland.databinding.BikeraceListContentBinding;
import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceEntity;
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
        return new BikeRaceListCardViewHolder(
                BikeraceListContentBinding.inflate(
                        LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(BikeRaceListCardViewHolder holder, int position) {

        if (position == bikeRaces.size() - 1) {
            onBottomReachedListener.onBottomReached(position);
        }

        BikeRaceEntity bikeRaceEntity = bikeRaces.get(position).bikeRaceEntity;

        holder.mBikeRaceDatabasePk = bikeRaceEntity.getPkBikeRaceEntityId();
        holder.cardView.setOnClickListener(
                new BikeRaceListCardOnClickListener(
                        mIsTwoPaneLayout,
                        mFragmentManager,
                        holder.mBikeRaceDatabasePk));

        /*holder.mRouteMap;*/ // TODO: Use the Strava API to Populate the MapView

        holder.eventName.setText(bikeRaceEntity.getEventName());
        holder.promotingClub.setText(bikeRaceEntity.getPromotingClub());
        holder.location.setText(bikeRaceEntity.getLocation());
        holder.startDate.setText(Utils.convertDateToString(bikeRaceEntity.getStartDate()));
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
