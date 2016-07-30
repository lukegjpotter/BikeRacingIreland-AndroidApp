package com.lukegjpotter.bikeracingireland.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lukegjpotter.bikeracingireland.model.BikeRace;
import com.lukegjpotter.bikeracingireland.model.StageDetail;

import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection extends SQLiteOpenHelper {

    // Database Information.
    private static final String DATABASE_NAME = "BikeRacingIrelandDB";
    private static final int DATABASE_VERSION = 1;
    // Singleton Instance.
    private static DatabaseConnection ourInstance;
    // TableOperation objects.
    private BikeRaceTableOperation bikeRaceTable;
    private StageDetailTableOperation stageDetailTable;

    /**
     * Simple private constructor.
     */
    private DatabaseConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        bikeRaceTable = new BikeRaceTableOperation();
        stageDetailTable = new StageDetailTableOperation();
    }

    /**
     * Allows retrieval of the Singleton Instance.
     * <p/>
     * To access Database, call;
     * {@code DatabaseConnection.getInstance(getApplicationContext())}
     * to get the instance.
     */
    public static DatabaseConnection getInstance(Context context) {

        if (ourInstance == null)
            ourInstance = new DatabaseConnection(context);

        return ourInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(stageDetailTable.getCreateSql());
        database.execSQL(bikeRaceTable.getCreateSql());
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // Drop the older table, if there's a newer table, and create the newer table.
        if (newVersion > oldVersion) {
            database.execSQL(stageDetailTable.getDropSql());
            database.execSQL(bikeRaceTable.getDropSql());

            onCreate(database);
        }
    }

    // --------------------------- CRUD Methods --------------------------- //
    // ---------- Create ---------- //
    public synchronized void insertBikeRace(BikeRace bikeRace) {

        // Abandon if this BikeRace is a duplicate.
        if (DatabaseConnectionUtils.isBikeRaceInDatabase(getReadableDatabase(), bikeRace.getId()))
            return;

        // Proceed with the insert.
        SQLiteDatabase database = getWritableDatabase();
        database.insert(BikeRaceTableOperation.TABLE_NAME, null, bikeRaceTable.getContentValues(bikeRace, null));

        for (StageDetail stageDetail : bikeRace.getStageDetails()) {
            if (!DatabaseConnectionUtils.isStageDetailInDatabase(getReadableDatabase(), stageDetail.getId())) {
                // Create the new StageDetail.
                database.insert(StageDetailTableOperation.TABLE_NAME, null, stageDetailTable.getContentValues(stageDetail, bikeRace.getId()));
            } else {
                // Update the existing StageDetail.
                database.update(StageDetailTableOperation.TABLE_NAME, stageDetailTable.getContentValues(stageDetail, bikeRace.getId()), stageDetailTable.getWhereClause(), stageDetailTable.getWhereArgs(stageDetail.getId()));
            }
        }

        database.close();
    }

    // ---------- Retrieve -------- //

    /**
     * Get the {@code BikeRace}s with the {@code monthNumber} specified.
     * Month Number is starting from Zero. 0 is January, 11 is December, everything is inbetween.
     *
     * @param monthNumber The number of the Month to search for, e.g. 0 is Jan, 11 is Dec
     * @return The List of the {@code BikeRace}s with the {@code monthNumber}.
     */
    public synchronized List<BikeRace> retrieveBikeRacesInMonth(int monthNumber) {
        // TODO Implement this.
        return new ArrayList<>();
    }

    /**
     * Get the {@code BikeRace}s with the {@code raceType} specified.
     * Race Types are A+, A1, A2, ..., Women, Vets, Youth, ParaCycling.
     *
     * @param raceType The Race Type, e.g. A+, A1, ..., Women, etc.
     * @return The List of the {@code BikeRace}s with the {@code raceType}.
     */
    public synchronized List<BikeRace> retrieveBikeRacesWithRaceType(String raceType) {
        // TODO Implement this.
        return new ArrayList<>();
    }

    /**
     * Get the {@code BikeRace}s with the {@code category} specified.
     * Categories are in the {@code StageDetail} model.
     * Categories can be Time Trial, Road, Criterium, etc.
     * <p/>
     * To get them all connect to the {@code cyclingirelandevents} database.
     * <pre>
     *     psql cyclingirelandevents postgres
     *     select distinct category from stage_detail;
     * </pre>
     *
     * @param category The Category, e.g. Criterium, Time Trial, Road.
     * @return The List of the {@code BikeRace}s with the {@code category}.
     */
    public synchronized List<BikeRace> retrieveBikeRacesInCategory(String category) {
        // TODO Implement this.
        return new ArrayList<>();
    }

    // ---------- Update ---------- //
    public synchronized void updateBikeRace(BikeRace updatedBikeRace) {

        // Create the BikeRace if it doesn't exist, then exit, as there's nothing else to update.
        if (!DatabaseConnectionUtils.isBikeRaceInDatabase(getReadableDatabase(), updatedBikeRace.getId())) {
            insertBikeRace(updatedBikeRace);
            return;
        }

        // Proceed with the update.
        SQLiteDatabase database = getWritableDatabase();
        database.update(BikeRaceTableOperation.TABLE_NAME, bikeRaceTable.getContentValues(updatedBikeRace, null), bikeRaceTable.getWhereClause(), bikeRaceTable.getWhereArgs(updatedBikeRace.getId()));

        for (StageDetail stageDetail : updatedBikeRace.getStageDetails()) {
            if (DatabaseConnectionUtils.isStageDetailInDatabase(getReadableDatabase(), stageDetail.getId())) {
                // Update the existing StageDetail.
                database.update(StageDetailTableOperation.TABLE_NAME, stageDetailTable.getContentValues(stageDetail, updatedBikeRace.getId()), stageDetailTable.getWhereClause(), stageDetailTable.getWhereArgs(stageDetail.getId()));
            } else {
                // Create the StageDetail.
                database.insert(StageDetailTableOperation.TABLE_NAME, null, stageDetailTable.getContentValues(stageDetail, updatedBikeRace.getId()));
            }
        }

        database.close();
    }

    // ---------- Delete ---------- //
    public synchronized void deleteBikeRace(BikeRace bikeRaceToDelte) {
        // TODO Implement this.
    }
}
