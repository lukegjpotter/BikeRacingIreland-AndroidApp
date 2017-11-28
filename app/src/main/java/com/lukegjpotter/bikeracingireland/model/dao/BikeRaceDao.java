package com.lukegjpotter.bikeracingireland.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.lukegjpotter.bikeracingireland.enums.RaceType;
import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by lukegjpotter on 27/11/2017.
 */
@Dao
public interface BikeRaceDao {

    @Query("SELECT * FROM bikeraceentity WHERE id = :id")
    LiveData<BikeRaceEntity> findBikeRaceById(long id);

    @Query("SELECT * FROM bikeraceentity WHERE monthNumber = :monthNumber")
    LiveData<List<BikeRaceEntity>> findBikeRacesInMonth(int monthNumber);

    @Query("SELECT * FROM bikeraceentity, stagedetailentity WHERE raceType IN (:raceTypes) AND category IN (:categories) AND monthNumber IN (:searchMonths)")
    LiveData<List<BikeRaceEntity>> findBikeRacesWithRaceTypeInCategoryForMonths(Set<RaceType> raceTypes, Set<String> categories, Set<Integer> searchMonths);

    @Query("SELECT COUNT(*) FROM bikeraceentity")
    int rowCount();

    @Insert
    void insertBikeRaces(BikeRaceEntity... BikeRaces);

    @Update
    void updateBikeRaces(BikeRaceEntity... BikeRaces);

    @Delete
    void deleteBikeRaces(BikeRaceEntity... BikeRaces);
}
