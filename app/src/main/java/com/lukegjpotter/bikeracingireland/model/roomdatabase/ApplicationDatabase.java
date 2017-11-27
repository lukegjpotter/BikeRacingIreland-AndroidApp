package com.lukegjpotter.bikeracingireland.model.roomdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.lukegjpotter.bikeracingireland.model.dao.BikeRaceDao;
import com.lukegjpotter.bikeracingireland.model.dao.ProfileFilterDao;
import com.lukegjpotter.bikeracingireland.model.dao.StageDetailDao;
import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceEntity;

/**
 * Created by lukegjpotter on 27/11/2017.
 */
@Database(entities = {BikeRaceEntity.class}, version = 1, exportSchema = false)
public abstract class ApplicationDatabase extends RoomDatabase {

    private static ApplicationDatabase INSTANCE;

    public static ApplicationDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ApplicationDatabase.class,
                    "BikeRacingIrelandDatabase")
                    .build();
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract BikeRaceDao bikeRaceDao();

    public abstract StageDetailDao stageDetailDao();

    public abstract ProfileFilterDao profileFilterDao();
}
