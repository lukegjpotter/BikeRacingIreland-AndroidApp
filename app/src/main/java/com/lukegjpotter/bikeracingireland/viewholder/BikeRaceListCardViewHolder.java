package com.lukegjpotter.bikeracingireland.viewholder;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * View Holder for the {@code BikeRaceListRecyclerViewAdapter}.
 */
public class BikeRaceListCardViewHolder extends RecyclerView.ViewHolder {

    public CardView mCardView;
    public long mBikeRaceDatabasePk;

    public BikeRaceListCardViewHolder(View itemView) {
        super(itemView);

        mCardView = (CardView) itemView.findViewById(R.id.card_view);
    }
}
