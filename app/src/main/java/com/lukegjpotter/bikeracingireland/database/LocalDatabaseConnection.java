package com.lukegjpotter.bikeracingireland.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lukegjpotter.bikeracingireland.enums.RaceType;
import com.lukegjpotter.bikeracingireland.model.BikeRace;
import com.lukegjpotter.bikeracingireland.model.StageDetail;

import java.util.ArrayList;
import java.util.List;

public class LocalDatabaseConnection extends SQLiteOpenHelper implements DatabaseConnection {

    // Database Information.
    private static final String DATABASE_NAME = "BikeRacingIrelandDB";
    private static final int DATABASE_VERSION = 1;
    // Singleton Instance.
    private static LocalDatabaseConnection ourInstance;
    // TableOperation objects.
    private BikeRaceTableOperation bikeRaceTable;
    private StageDetailTableOperation stageDetailTable;

    /**
     * Simple private constructor.
     */
    private LocalDatabaseConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        bikeRaceTable = new BikeRaceTableOperation();
        stageDetailTable = new StageDetailTableOperation();
    }

    /**
     * Allows retrieval of the Singleton Instance.
     * <p/>
     * To access Database, call;
     * {@code LocalDatabaseConnection.getInstance(getApplicationContext())}
     * to get the instance.
     */
    public static LocalDatabaseConnection getInstance(Context context) {

        if (ourInstance == null)
            ourInstance = new LocalDatabaseConnection(context);

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
    private synchronized void insertBikeRace(BikeRace bikeRace, SQLiteDatabase database) {

        // Abandon if this BikeRace is a duplicate.
        if (DatabaseConnectionUtils.isBikeRaceInDatabase(getReadableDatabase(), bikeRace.getId()))
            return;

        // Proceed with the insert.
        database.insert(BikeRaceTableOperation.TABLE_NAME, null, bikeRaceTable.getContentValues(bikeRace, null));

        for (StageDetail stageDetail : bikeRace.getStageDetails()) {
            if (!DatabaseConnectionUtils.isStageDetailInDatabase(getReadableDatabase(), stageDetail.getId())) {
                // Create the new StageDetail.
                database.insert(StageDetailTableOperation.TABLE_NAME, null, stageDetailTable.getContentValues(stageDetail, bikeRace.getId()));
            } else {
                // Update the existing StageDetail.
                database.update(StageDetailTableOperation.TABLE_NAME, stageDetailTable.getContentValues(stageDetail, bikeRace.getId()), stageDetailTable.getWhereClauseForPk(), stageDetailTable.getWhereArgsForPk(stageDetail.getId()));
            }
        }
    }

    public synchronized void insertBikeRaceList(List<BikeRace> bikeRaces) {
        SQLiteDatabase database = getWritableDatabase();

        for (BikeRace bikeRaceToInsert : bikeRaces) {
            insertBikeRace(bikeRaceToInsert, database);
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
    @Override
    public synchronized List<BikeRace> retrieveBikeRacesInMonth(int monthNumber) {

        String whereClause = bikeRaceTable.getWhereClauseForMonthNumber();
        String[] whereArgs = bikeRaceTable.getWhereArgsForMonthNumber(monthNumber);

        return queryBikeRaceTable(whereClause, whereArgs);
    }

    /**
     * Get the {@code BikeRace}s with the {@code raceType} specified.
     * Race Types are A+, A1, A2, ..., Women, Vets, Youth, ParaCycling.
     *
     * @param raceType The Race Type, e.g. A+, A1, ..., Women, etc.
     * @return The List of the {@code BikeRace}s with the {@code raceType}.
     */
    public synchronized List<BikeRace> retrieveBikeRacesWithRaceType(RaceType raceType) {

        String whereClause = bikeRaceTable.getWhereClauseForRaceType(raceType.toString());
        String[] whereArgs = bikeRaceTable.getWhereArgsForRaceTypeTrue();

        return queryBikeRaceTable(whereClause, whereArgs);
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

    /**
     * Get the {@code BikeRace} with the ID number of {@code bikeRaceId}.
     *
     * @param bikeRaceId The ID number of the {@code BikeRace}.
     * @return The {@code BikeRace} for with the specified ID.
     */
    public BikeRace retrieveBikeRaceWithId(long bikeRaceId) {

        String whereClause = bikeRaceTable.getWhereClauseForPk();
        String[] whereArgs = bikeRaceTable.getWhereArgsForPk(bikeRaceId);

        return queryBikeRaceTable(whereClause, whereArgs).get(0);
    }

    // ---------- Update ---------- //
    public synchronized void updateBikeRace(BikeRace updatedBikeRace) {

        // Create the BikeRace if it doesn't exist, then exit, as there's nothing else to update.
        if (!DatabaseConnectionUtils.isBikeRaceInDatabase(getReadableDatabase(), updatedBikeRace.getId())) {
            insertBikeRace(updatedBikeRace, getWritableDatabase());
            return;
        }

        // Proceed with the update.
        SQLiteDatabase database = getWritableDatabase();
        database.update(BikeRaceTableOperation.TABLE_NAME, bikeRaceTable.getContentValues(updatedBikeRace, null), bikeRaceTable.getWhereClauseForPk(), bikeRaceTable.getWhereArgsForPk(updatedBikeRace.getId()));

        for (StageDetail stageDetail : updatedBikeRace.getStageDetails()) {
            if (DatabaseConnectionUtils.isStageDetailInDatabase(getReadableDatabase(), stageDetail.getId())) {
                // Update the existing StageDetail.
                database.update(StageDetailTableOperation.TABLE_NAME, stageDetailTable.getContentValues(stageDetail, updatedBikeRace.getId()), stageDetailTable.getWhereClauseForPk(), stageDetailTable.getWhereArgsForPk(stageDetail.getId()));
            } else {
                // Create the StageDetail.
                database.insert(StageDetailTableOperation.TABLE_NAME, null, stageDetailTable.getContentValues(stageDetail, updatedBikeRace.getId()));
            }
        }

        database.close();
    }

    // ---------- Delete ---------- //
    public synchronized void deleteBikeRace(BikeRace bikeRaceToDelete) {
        // TODO Implement this.
    }

    // -------------------------- Private Methods -------------------------- //
    private List<BikeRace> queryBikeRaceTable(String whereClause, String[] whereArgs) {

        List<BikeRace> bikeRaces = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query(BikeRaceTableOperation.TABLE_NAME, null, whereClause, whereArgs, null, null, null);

        if (cursor.moveToFirst()) {
            // Get the BikeRace from the BikeRaceTable
            bikeRaces = bikeRaceTable.populateListFromCursor(cursor);

            // Get the BikeRace's StageDetails.
            for (BikeRace bikeRace : bikeRaces) {
                bikeRace.setStageDetails(queryStageDetailsTableForBikeRaceId(bikeRace.getId(), database, cursor));
            }
        }

        // Close the connections.
        database.close();
        cursor.close();

        return bikeRaces;
    }

    private List<StageDetail> queryStageDetailsTableForBikeRaceId(long bikeRaceId, SQLiteDatabase database, Cursor cursor) {

        // HACK: Not using a LocalVariable here as it'll ruin the cursor.close() in the calling method.
        cursor = database.query(StageDetailTableOperation.TABLE_NAME, null, stageDetailTable.getWhereClauseForFk(), stageDetailTable.getWhereArgsForFk(bikeRaceId), null, null, null);

        return stageDetailTable.populateListFromCursor(cursor);
    }
}
