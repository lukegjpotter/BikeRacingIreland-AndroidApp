package com.lukegjpotter.bikeracingireland.viewadapter;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lukegjpotter.bikeracingireland.R;
import com.lukegjpotter.bikeracingireland.listeners.BikeRaceListCardOnClickListener;
import com.lukegjpotter.bikeracingireland.model.BikeRace;
import com.lukegjpotter.bikeracingireland.viewholder.BikeRaceListCardViewHolder;

import java.util.List;

/**
 * The RecyclerViewAdapter for the {@code BikeRaceListActivity}.
 */
public class BikeRaceListRecyclerViewAdapter extends RecyclerView.Adapter<BikeRaceListCardViewHolder> {

    FragmentManager mFragmentManager;
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
        // Populate the View Holder...
        holder.mBikeRaceDatabasePk = mBikeRaces.get(position).getId();

        BikeRaceListCardOnClickListener cardOnClickListener = new BikeRaceListCardOnClickListener(mIsTwoPaneLayout, mFragmentManager, holder.mBikeRaceDatabasePk);
        holder.mCardView.setOnClickListener(cardOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mBikeRaces.size();
    }
}
