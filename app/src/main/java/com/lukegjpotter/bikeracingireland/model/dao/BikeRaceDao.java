package com.lukegjpotter.bikeracingireland.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceEntity;

/**
 * Created by lukegjpotter on 27/11/2017.
 */
@Dao
public interface BikeRaceDao {

    @Insert
    void insertBikeRaces(BikeRaceEntity... BikeRaces);

    @Update
    void updateBikeRaces(BikeRaceEntity... BikeRaces);

    @Delete
    void deleteBikeRaces(BikeRaceEntity... BikeRaces);
}
