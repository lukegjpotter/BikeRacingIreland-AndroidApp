package com.lukegjpotter.bikeracingireland.listeners;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.lukegjpotter.bikeracingireland.BikeRaceDetailActivity;
import com.lukegjpotter.bikeracingireland.BikeRaceDetailFragment;

/**
 * The {@code OnClickListener} for the {@code BikeRaceListRecyclerViewAdapter}'s CardView.
 * It passes the Database PrimaryKey into the {@code BikeRaceDetailFragment} directly on a tablet,
 * or via {@code BikeRaceDetailActivity} on a smaller screen.
 */
public class BikeRaceListCardOnClickListener implements View.OnClickListener {

    FragmentManager mFragmentManager;
    private boolean mIsTwoPaneLayout;
    private long mBikeRaceDatabasePk;

    public BikeRaceListCardOnClickListener(boolean isTwoPaneLayout, FragmentManager fragmentManager, long bikeRaceDatabasePk) {
        mIsTwoPaneLayout = isTwoPaneLayout;
        mFragmentManager = fragmentManager;
        mBikeRaceDatabasePk = bikeRaceDatabasePk;
    }

    @Override
    public void onClick(View view) {
        if (mIsTwoPaneLayout) {
            Bundle arguments = new Bundle();
            arguments.putLong(BikeRaceDetailFragment.ARG_ITEM_ID, mBikeRaceDatabasePk);
            BikeRaceDetailFragment fragment = new BikeRaceDetailFragment();
            fragment.setArguments(arguments);

            mFragmentManager
                    .beginTransaction()
                    .replace(R.id.bikerace_detail_container, fragment)
                    .commit();
        } else {
            Context context = view.getContext();
            Intent intent = new Intent(context, BikeRaceDetailActivity.class);
            intent.putExtra(BikeRaceDetailFragment.ARG_ITEM_ID, mBikeRaceDatabasePk);

            context.startActivity(intent);
        }
    }
}
