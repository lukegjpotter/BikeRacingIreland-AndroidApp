package com.lukegjpotter.bikeracingireland.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.lukegjpotter.bikeracingireland.enums.RaceType;
import com.lukegjpotter.bikeracingireland.model.entity.StageDetailEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by lukegjpotter on 27/11/2017.
 */
@Dao
public interface StageDetailDao {

    @Query("SELECT * FROM stagedetailentity WHERE id = :id")
    LiveData<StageDetailEntity> findStageDetailById(long id);

    @Query("SELECT * FROm stagedetailentity WHERE fkBikeRaceEntityId = :fkBikeRaceEntityId")
    LiveData<List<StageDetailEntity>> findStageDetailsByBikeRaceId(long fkBikeRaceEntityId);

    @Query("SELECT DISTINCT fkBikeRaceEntityId FROM stagedetailentity WHERE raceType IN (:raceTypes) AND category IN (:categories)")
    LiveData<List<Long>> findBikeRaceIdsByRaceTypesAndCategories(Set<RaceType> raceTypes, Set<String> categories);

    @Query("SELECT * FROM stagedetailentity WHERE raceType IN (:raceTypes) AND category IN (:categories)")
    LiveData<List<StageDetailEntity>> findStageDetailsByRaceTypesAndCategories(Set<RaceType> raceTypes, Set<String> categories);

    @Insert
    void insertStageDetails(StageDetailEntity... StageDetails);

    @Update
    void updateStageDetails(StageDetailEntity... StageDetails);

    @Delete
    void deleteStageDetails(StageDetailEntity... StageDetails);
}
