package com.lukegjpotter.bikeracingireland.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceWithStageDetails;
import com.lukegjpotter.bikeracingireland.repository.BikeRaceRepository;

/**
 * This is the ViewModel that will be used by the BikeRaceDetailFragment.
 * <p>
 * Created by lukegjpotter on 18/12/2017.
 */
public class BikeRaceDetailViewModel extends AndroidViewModel {

    private final BikeRaceRepository repository;
    private LiveData<BikeRaceWithStageDetails> bikeRace;


    public BikeRaceDetailViewModel(@NonNull Application application) {
        super(application);

        repository = BikeRaceRepository.getInstance(application.getApplicationContext());
        bikeRace = null;
    }

    public LiveData<BikeRaceWithStageDetails> getBikeRace(long bikeRaceID) {

        bikeRace = repository.findBikeRaceById(bikeRaceID);

        return bikeRace;
    }
}
