package com.lukegjpotter.bikeracingireland.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.lukegjpotter.bikeracingireland.model.entity.StageDetailEntity;

/**
 * Created by lukegjpotter on 27/11/2017.
 */
@Dao
public interface StageDetailDao {

    @Insert
    void insertStageDetails(StageDetailEntity... StageDetails);

    @Update
    void updateStageDetails(StageDetailEntity... StageDetails);

    @Delete
    void deleteStageDetails(StageDetailEntity... StageDetails);
}
