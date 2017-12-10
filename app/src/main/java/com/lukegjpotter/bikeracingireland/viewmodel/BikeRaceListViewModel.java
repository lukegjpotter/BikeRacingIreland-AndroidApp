package com.lukegjpotter.bikeracingireland.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.lukegjpotter.bikeracingireland.enums.RaceType;
import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceWithStageDetails;
import com.lukegjpotter.bikeracingireland.model.entity.ProfileFilterEntity;
import com.lukegjpotter.bikeracingireland.model.roomdatabase.ApplicationDatabase;
import com.lukegjpotter.bikeracingireland.utils.MonthManager;

import java.util.List;

/**
 * Created by lukegjpotter on 29/11/2017.
 */

public class BikeRaceListViewModel extends AndroidViewModel {

    private final ApplicationDatabase database;
    private LiveData<List<BikeRaceWithStageDetails>> bikeRaces;


    public BikeRaceListViewModel(@NonNull Application application) {
        super(application);

        this.database = ApplicationDatabase.getInstance(application.getApplicationContext());

        bikeRaces = database.bikeRaceDao().findBikeRacesInMonth(MonthManager.currentMonthNumber());
    }

    public LiveData<List<BikeRaceWithStageDetails>> getBikeRaces() {
        return bikeRaces;
    }

    public void setBikeRacesToProfileFilterAndMonths() {
        ProfileFilterEntity profileFilterEntity = database.profileFilterDao().findProfileFilter().getValue();

        if (profileFilterEntity == null) {
            setBikeRacesToMonths();
            return;
        }

        String[] raceTypes = new String[profileFilterEntity.getRaceTypes().size()];
        int i = 0;
        for (RaceType raceType : profileFilterEntity.getRaceTypes()) {
            raceTypes[i] = raceType.toString();
            i++;
        }

        String[] categories = profileFilterEntity.getCategories().toArray(new String[profileFilterEntity.getCategories().size()]);

        List<Long> ids = database.stageDetailDao().findBikeRaceIdsByRaceTypesAndCategories(raceTypes, categories).getValue();

        bikeRaces = database.bikeRaceDao().findBikeRacesByIdsAndMonths(ids, MonthManager.getMonthsInListView());
    }

    public void setBikeRacesToMonths() {
        bikeRaces = database.bikeRaceDao().findBikeRacesInMonths(MonthManager.getMonthsInListView());
    }
}
