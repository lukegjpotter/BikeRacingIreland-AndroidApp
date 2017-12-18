package com.lukegjpotter.bikeracingireland.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.lukegjpotter.bikeracingireland.model.dao.BikeRaceDao;
import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceWithStageDetails;
import com.lukegjpotter.bikeracingireland.model.roomdatabase.ApplicationDatabase;

/**
 * This is the ViewModel that will be used by the BikeRaceDetailFragment.
 * <p>
 * Created by lukegjpotter on 18/12/2017.
 */
public class BikeRaceDetailViewModel extends AndroidViewModel {

    private LiveData<BikeRaceWithStageDetails> bikeRace;
    private BikeRaceDao bikeRaceDao;


    public BikeRaceDetailViewModel(@NonNull Application application) {
        super(application);

        bikeRaceDao = ApplicationDatabase.getInstance(application.getApplicationContext()).bikeRaceDao();
        bikeRace = null;
    }

    public LiveData<BikeRaceWithStageDetails> getBikeRace(long bikeRaceID) {

        bikeRace = bikeRaceDao.findBikeRaceById(bikeRaceID);

        return bikeRace;
    }
}
