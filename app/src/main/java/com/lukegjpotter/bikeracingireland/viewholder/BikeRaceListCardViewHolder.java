package com.lukegjpotter.bikeracingireland.viewholder;


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

        mCardView = (CardView) itemView.findViewById(R.id.card_view);
        mRouteMap = (ImageView) itemView.findViewById(R.id.route_map);
        mEventName = (TextView) itemView.findViewById(R.id.event_name);
        mPromotingClub = (TextView) itemView.findViewById(R.id.promoting_club);
        mLocation = (TextView) itemView.findViewById(R.id.location);
        mStartDate = (TextView) itemView.findViewById(R.id.start_date);
    }
}
