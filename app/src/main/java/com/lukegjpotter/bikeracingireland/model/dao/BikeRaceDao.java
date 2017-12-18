package com.lukegjpotter.bikeracingireland.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceEntity;
import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceWithStageDetails;
import com.lukegjpotter.bikeracingireland.view.activity.BikeRaceDetailActivity;
import com.lukegjpotter.bikeracingireland.view.activity.BikeRaceDetailFragment;
import com.lukegjpotter.bikeracingireland.view.activity.BikeRaceListActivity;

import java.util.List;
import java.util.Set;

/**
 * Created by lukegjpotter on 27/11/2017.
 */
@Dao
public interface BikeRaceDao {

    /**
     * Useful for the {@link BikeRaceDetailActivity} and
     * {@link BikeRaceDetailFragment} for displaying full data.
     *
     * @param id The ID of the BikeRaceEntity to display
     * @return Bike race with the specified ID.
     */
    @Transaction
    @Query("SELECT * FROM bikeraceentity WHERE pkBikeRaceEntityId = :id")
    LiveData<BikeRaceWithStageDetails> findBikeRaceById(long id);

    /**
     * Useful for adding new BikeRaceEntities to the
     * {@link BikeRaceListActivity}.
     *
     * @param monthNumber The month number to search, it's January = 1 ... Dec = 12.
     * @return Bike Races in the specified month.
     */
    @Transaction
    @Query("SELECT * FROM bikeraceentity WHERE monthNumber = :monthNumber")
    LiveData<List<BikeRaceWithStageDetails>> findBikeRacesInMonth(int monthNumber);

    /**
     * Useful for when multiple months have been loaded in the RecyclerView.
     *
     * @param months The months to search.
     * @return The bike races in the specified months.
     */
    @Transaction
    @Query("SELECT * FROM bikeraceentity WHERE monthNumber IN (:months)")
    LiveData<List<BikeRaceWithStageDetails>> findBikeRacesInMonths(Set<Integer> months);

    /**
     * Useful for the ProfileFilter when
     * {@code StageDetailDao.findBikeRaceIdsByRaceTypesAndCategories(...)} has returned the
     * BikeRaceEntity IDs and the {@link com.lukegjpotter.bikeracingireland.utils.MonthManager} has
     * the months that are currently active in the
     * {@link BikeRaceListActivity}
     *
     * @param ids The IDs to search.
     * @param months The months to search
     * @return The Bike Races with the specified IDs, that occour in the specified months.
     */
    @Transaction
    @Query("SELECT * FROM bikeraceentity WHERE pkBikeRaceEntityId IN (:ids) AND monthNumber IN (:months)")
    LiveData<List<BikeRaceWithStageDetails>> findBikeRacesByIdsAndMonths(List<Long> ids, Set<Integer> months);

    /**
     * Used to determine if the Database is empty, to load Initia Data.
     *
     * @return The number of rows in the database, or the number of bike races.
     */
    @Query("SELECT COUNT(*) FROM bikeraceentity")
    int rowCount();

    @Insert
    long insertBikeRaces(BikeRaceEntity... BikeRaces);

    @Update
    void updateBikeRaces(BikeRaceEntity... BikeRaces);

    @Delete
    void deleteBikeRaces(BikeRaceEntity... BikeRaces);
}
