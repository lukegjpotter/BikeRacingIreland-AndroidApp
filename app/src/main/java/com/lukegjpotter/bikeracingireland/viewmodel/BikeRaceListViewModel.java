package com.lukegjpotter.bikeracingireland.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceWithStageDetails;
import com.lukegjpotter.bikeracingireland.model.entity.ProfileFilterEntity;
import com.lukegjpotter.bikeracingireland.model.enums.RaceType;
import com.lukegjpotter.bikeracingireland.model.roomdatabase.ApplicationDatabase;
import com.lukegjpotter.bikeracingireland.utils.MonthManager;

import java.util.List;

/**
 * This is the ViewModel for the BikeRaceListActivity.
 *
 * It will manipulate the contents of the List of BikeRaces to suit the needs of the RecyclerView.
 * This will change depending on the months that are active and the status of the ProfileFilter.
 *
 * Created by lukegjpotter on 29/11/2017.
 */

public class BikeRaceListViewModel extends AndroidViewModel {

    private final ApplicationDatabase database;
    private MediatorLiveData<List<BikeRaceWithStageDetails>> bikeRacesMediatorLiveData;


    public BikeRaceListViewModel(@NonNull Application application) {
        super(application);

        this.database = ApplicationDatabase.getInstance(application.getApplicationContext());
        LiveData<List<BikeRaceWithStageDetails>> bikeRacesLiveData = database.bikeRaceDao().findBikeRacesInMonth(MonthManager.currentMonthNumber());

        bikeRacesMediatorLiveData.addSource(bikeRacesLiveData, value -> bikeRacesMediatorLiveData.setValue(value));
    }

    public LiveData<List<BikeRaceWithStageDetails>> getBikeRaces() {
        return bikeRacesMediatorLiveData;
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
        LiveData<List<BikeRaceWithStageDetails>> bikeRacesLiveData = database.bikeRaceDao().findBikeRacesByIdsAndMonths(ids, MonthManager.getMonthsInListView());

        bikeRacesMediatorLiveData.addSource(bikeRacesLiveData, value -> bikeRacesMediatorLiveData.setValue(value));
    }

    public void setBikeRacesToMonths() {
        LiveData<List<BikeRaceWithStageDetails>> bikeRacesLiveData = database.bikeRaceDao().findBikeRacesInMonths(MonthManager.getMonthsInListView());
        bikeRacesMediatorLiveData.addSource(bikeRacesLiveData, value -> bikeRacesMediatorLiveData.setValue(value));
    }

    public void loadFollowingMonthsBikeRaces() {
        LiveData<List<BikeRaceWithStageDetails>> bikeRacesLiveData = database.bikeRaceDao().findBikeRacesInMonth(MonthManager.nextMonthNumberForListView());
        bikeRacesMediatorLiveData.addSource(bikeRacesLiveData, value -> bikeRacesMediatorLiveData.setValue(value));
    }
}
