package com.lukegjpotter.bikeracingireland.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceWithStageDetails;
import com.lukegjpotter.bikeracingireland.repository.BikeRaceRepository;
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

    private final BikeRaceRepository repository;
    private MutableLiveData<List<BikeRaceWithStageDetails>> bikeRacesMutableLiveData;


    public BikeRaceListViewModel(@NonNull Application application) {
        super(application);

        bikeRacesMutableLiveData = new MutableLiveData<>();
        repository = BikeRaceRepository.getInstance(application.getApplicationContext());

        bikeRacesMutableLiveData.setValue(
                repository.findBikeRacesInMonths(MonthManager.currentMonthNumber()).getValue());
    }

    public LiveData<List<BikeRaceWithStageDetails>> getBikeRaces() {
        return bikeRacesMutableLiveData;
    }

    public void setBikeRacesToProfileFilterAndMonths() {
        bikeRacesMutableLiveData.setValue(
                repository.findBikeRacesForProfileFilterAndMonths().getValue());
    }

    public void setBikeRacesToMonths() {
        bikeRacesMutableLiveData.setValue(
                repository.findBikeRacesInMonths(
                        MonthManager
                                .getMonthsInListView()
                                .toArray(new Integer[MonthManager.getMonthsInListView().size()]))
                        .getValue());
    }

    public void loadFollowingMonthsBikeRaces() {
        // TODO: Is ProFileFilterActive.
        LiveData<List<BikeRaceWithStageDetails>> nextMonthsBikeRacesLiveData =
                repository.findBikeRacesInMonths(MonthManager.nextMonthNumberForListView());

        List<BikeRaceWithStageDetails> existingBikeRaces = bikeRacesMutableLiveData.getValue();
        existingBikeRaces.addAll(nextMonthsBikeRacesLiveData.getValue());

        bikeRacesMutableLiveData.setValue(existingBikeRaces);
    }
}
