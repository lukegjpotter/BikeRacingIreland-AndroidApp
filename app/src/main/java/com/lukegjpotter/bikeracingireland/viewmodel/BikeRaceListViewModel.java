package com.lukegjpotter.bikeracingireland.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceEntity;
import com.lukegjpotter.bikeracingireland.model.roomdatabase.ApplicationDatabase;
import com.lukegjpotter.bikeracingireland.utils.MonthManager;

import java.util.List;

/**
 * Created by lukegjpotter on 29/11/2017.
 */

public class BikeRaceListViewModel extends AndroidViewModel {

    private final LiveData<List<BikeRaceEntity>> bikeRaces;

    public BikeRaceListViewModel(@NonNull Application application) {
        super(application);

        bikeRaces = ApplicationDatabase.getInstance(application.getApplicationContext())
                .bikeRaceDao()
                .findBikeRacesInMonth(MonthManager.currentMonthNumber());
    }

    public LiveData<List<BikeRaceEntity>> getBikeRaces() {
        return bikeRaces;
    }
}
