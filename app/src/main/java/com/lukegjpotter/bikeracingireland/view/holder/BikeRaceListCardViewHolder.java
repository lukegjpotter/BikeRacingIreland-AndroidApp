package com.lukegjpotter.bikeracingireland.view.holder;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lukegjpotter.bikeracingireland.databinding.BikeraceListContentBinding;

/**
 * View Holder for the {@code BikeRaceListRecyclerViewAdapter}.
 */
public class BikeRaceListCardViewHolder extends RecyclerView.ViewHolder {

    public CardView cardView;
    public ImageView routeMap;
    public TextView eventName, promotingClub, location, startDate;

    public long mBikeRaceDatabasePk;

    public BikeRaceListCardViewHolder(BikeraceListContentBinding binding) {
        super(binding.getRoot());

        cardView = binding.cardView;
        routeMap = binding.routeMap;
        eventName = binding.eventName;
        promotingClub = binding.promotingClub;
        location = binding.location;
        startDate = binding.startDate;
    }
}
