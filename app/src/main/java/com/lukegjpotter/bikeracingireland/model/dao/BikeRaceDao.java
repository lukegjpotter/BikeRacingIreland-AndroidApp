package com.lukegjpotter.bikeracingireland.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by lukegjpotter on 27/11/2017.
 */
@Dao
public interface BikeRaceDao {

    /**
     * Useful for the {@link com.lukegjpotter.bikeracingireland.BikeRaceDetailActivity} and
     * {@link com.lukegjpotter.bikeracingireland.BikeRaceDetailFragment} for displaying full data.
     *
     * @param id The ID of the BikeRaceEntity to display
     * @return
     */
    @Transaction
    @Query("SELECT * FROM bikeraceentity WHERE id = :id")
    LiveData<BikeRaceEntity> findBikeRaceById(long id);

    /**
     * Useful for adding new BikeRaceEntities to the
     * {@link com.lukegjpotter.bikeracingireland.BikeRaceListActivity}.
     *
     * @param monthNumber The month number to search, it's January = 1 ... Dec = 12.
     * @return
     */
    @Transaction
    @Query("SELECT * FROM bikeraceentity WHERE monthNumber = :monthNumber")
    LiveData<List<BikeRaceEntity>> findBikeRacesInMonth(int monthNumber);

    /**
     * Useful for the ProfileFilter when
     * {@code StageDetailDao.findBikeRaceIdsByRaceTypesAndCategories(...)} has returned the
     * BikeRaceEntity IDs and the {@link com.lukegjpotter.bikeracingireland.utils.MonthManager} has
     * the months that are currently active in the
     * {@link com.lukegjpotter.bikeracingireland.BikeRaceListActivity}
     *
     * @param ids
     * @param months
     * @return
     */
    @Transaction
    @Query("SELECT * FROM bikeraceentity WHERE id IN (:ids) AND monthNumber in (:months)")
    LiveData<List<BikeRaceEntity>> findBikeRacesByIdsAndMonths(Set<Long> ids, Set<Integer> months);

    /**
     * Used to determine if the Database is empty, to load Initia Data.
     *
     * @return
     */
    @Query("SELECT COUNT(*) FROM bikeraceentity")
    int rowCount();

    @Insert
    void insertBikeRaces(BikeRaceEntity... BikeRaces);

    @Update
    void updateBikeRaces(BikeRaceEntity... BikeRaces);

    @Delete
    void deleteBikeRaces(BikeRaceEntity... BikeRaces);
}
