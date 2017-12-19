package com.lukegjpotter.bikeracingireland.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.lukegjpotter.bikeracingireland.model.dao.BikeRaceDao;
import com.lukegjpotter.bikeracingireland.model.dao.StageDetailDao;
import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceEntity;
import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceWithStageDetails;
import com.lukegjpotter.bikeracingireland.model.entity.StageDetailEntity;
import com.lukegjpotter.bikeracingireland.model.roomdatabase.ApplicationDatabase;
import com.lukegjpotter.bikeracingireland.repository.retrofit.BikeRaceRetrofitRepository;

import java.util.List;

/**
 * This Repository will handle the loading of data from local and remote data sources.
 * <p>
 * Created by lukegjpotter on 18/12/2017.
 */
public class BikeRaceRepository {

    private static BikeRaceRepository INSTANCE;
    private BikeRaceDao bikeRaceDao;
    private StageDetailDao stageDetailDao;

    private BikeRaceRepository(Context context) {
        this.bikeRaceDao = ApplicationDatabase.getInstance(context).bikeRaceDao();
        this.stageDetailDao = ApplicationDatabase.getInstance(context).stageDetailDao();
    }

    public static BikeRaceRepository getInstance(Context context) {

        if (INSTANCE == null) INSTANCE = new BikeRaceRepository(context);

        return INSTANCE;
    }

    public LiveData<List<BikeRaceWithStageDetails>> getBikeRacesInMonthNumber(int monthNumber) {

        // Check Local Database for the month's data.
        LiveData<List<BikeRaceWithStageDetails>> bikeRaces = bikeRaceDao.findBikeRacesInMonth(monthNumber);

        // If it's not present in the local database, use the REST Service.
        if (bikeRaces.getValue().isEmpty()) {
            List<BikeRaceWithStageDetails> bikeRacesInMonth = BikeRaceRetrofitRepository.getInstance().bikeRacesInMonthNumber(monthNumber);
            bikeRaces.getValue().addAll(bikeRacesInMonth);

            // Save the Downloaded Bike Races, Retrofit will have populated the data.
            saveBikeRaces(bikeRaces.getValue());
        }

        return bikeRaces;
    }

    private void saveBikeRaces(List<BikeRaceWithStageDetails> bikeRaces) {
        for (BikeRaceWithStageDetails bikeRaceWithStageDetails : bikeRaces) {
            BikeRaceEntity bikeRaceEntity = bikeRaceWithStageDetails.bikeRaceEntity;
            List<StageDetailEntity> stageDetailEntities = bikeRaceWithStageDetails.stageDetails;

            long fkBikeRaceId = bikeRaceDao.insertBikeRaces(bikeRaceEntity).get(0);
            stageDetailEntities.forEach(stageDetailEntity -> stageDetailEntity.setFkBikeRaceEntityId(fkBikeRaceId));
            stageDetailDao.insertStageDetails(stageDetailEntities.toArray(new StageDetailEntity[stageDetailEntities.size()]));
        }
    }
}
