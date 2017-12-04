package com.lukegjpotter.bikeracingireland.view.holder;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lukegjpotter.bikeracingireland.R;

/**
 * View Holder for the {@code BikeRaceListRecyclerViewAdapter}.
 */
public class BikeRaceListCardViewHolder extends RecyclerView.ViewHolder {

    public CardView mCardView;
    public ImageView mRouteMap;
    public TextView mEventName, mPromotingClub, mLocation, mStartDate;

    public long mBikeRaceDatabasePk;

    public BikeRaceListCardViewHolder(View itemView) {
        super(itemView);

        mCardView = itemView.findViewById(R.id.card_view);
        mRouteMap = itemView.findViewById(R.id.route_map);
        mEventName = itemView.findViewById(R.id.event_name);
        mPromotingClub = itemView.findViewById(R.id.promoting_club);
        mLocation = itemView.findViewById(R.id.location);
        mStartDate = itemView.findViewById(R.id.start_date);
    }
}
