package com.lukegjpotter.bikeracingireland.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.lukegjpotter.bikeracingireland.model.entity.ProfileFilterEntity;

/**
 * Created by lukegjpotter on 27/11/2017.
 */
@Dao
public interface ProfileFilterDao {

    @Insert
    void insertProfileFilters(ProfileFilterEntity... ProfileFilters);

    @Update
    void updateProfileFilters(ProfileFilterEntity... ProfileFilters);

    @Delete
    void deleteProfileFilters(ProfileFilterEntity... ProfileFilters);
}
