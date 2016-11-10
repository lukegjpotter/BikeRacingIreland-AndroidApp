package com.lukegjpotter.bikeracingireland.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lukegjpotter.bikeracingireland.enums.RaceType;
import com.lukegjpotter.bikeracingireland.model.BikeRace;
import com.lukegjpotter.bikeracingireland.model.StageDetail;
import com.lukegjpotter.bikeracingireland.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
     * Month Number is starting from Zero. 0 is January, 11 is December, everything is in between.
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

        String whereClause = bikeRaceTable.getWhereClauseForRaceType(raceType.getDbText());
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
     * Mainly aimed at the ProfileFilter Feature, this will return the {@code BikeRace}s for the
     * specified {@code RaceType}s, Categories and Months.
     *
     * @param raceTypes    A Set of the RaceTypes to search for.
     *                     Race Types are A+, A1, A2, ..., Women, Vets, Youth, ParaCycling.
     * @param categories   A Set of the Categories to search for.
     *                     Categories can be Time Trial, Road, Criterium, etc.
     * @param searchMonths The Months to search, use {@code MonthManager.getMonthsInListView()}.
     * @return The List of the {@code BikeRace}s for the specified {@code RaceType}s, Categories and
     * Months.
     */
    public synchronized List<BikeRace> retrieveBikeRacesWithRaceTypeInCategoryForMonths(Set<RaceType> raceTypes, Set<String> categories, Set<Integer> searchMonths) {
        String andConstant = " AND ", orConstant = " OR ";

        /*
         * Example SQLite Query that needs to be formed:
         * SELECT *
         *   FROM BikeRace
         *  WHERE a1=1 OR a2=1
         *    AND monthNumber IN {8, 9, 10};
         *    AND id IN (SELECT DISTINCT pk_id
         *                 FROM StageDetail
         *                WHERE category='Time Trial' OR category='Road'
         */
        /* PSQL Query:
         * SELECT *
         *   FROM road_race_event_database_record
         *  WHERE (isa1=true OR isa2=true)
         *    AND road_race_event_database_record.month_number IN (8, 9, 10)
         *    AND id IN (
         *         SELECT DISTINCT event_id
         *           FROM stage_detail
         *          WHERE category='Time Trial' OR category='Road');
         */

        // Build Where Clauses and Args for RaceTypes.
        String whereClauseForRaceTypes = "";
        List<String> whereArgsForRaceTypeList = new ArrayList<>();

        for (RaceType raceType : raceTypes) {
            whereClauseForRaceTypes += bikeRaceTable.getWhereClauseForRaceType(raceType.getDbText()) + orConstant;
            whereArgsForRaceTypeList.add(bikeRaceTable.getWhereArgsForRaceTypeTrue()[0]);
        }

        // Remove the trailing "OR", for the next part of the query.
        if (whereClauseForRaceTypes.endsWith(orConstant)) {
            whereClauseForRaceTypes = Utils.removeLastOccurrenceInString(whereClauseForRaceTypes, orConstant);
        }

        // Build Where Clauses and Args for Search Months.
        String whereClauseForSearchMonths = bikeRaceTable.getWhereClauseForSearchMonths(searchMonths.size());
        List<String> whereArgsForSearchMonths = bikeRaceTable.getWhereArgsForSearchMonths(searchMonths);

        // Build Where Clauses and Args for Categories.
        String stageDetailsWhereClauseForCategory = "";
        String[] stageDetailsWhereArgsForCategoryArray = new String[categories.size()];
        int stageDetailsCategoryIndex = 0;

        for (String category : categories) {
            stageDetailsWhereClauseForCategory += stageDetailTable.getWhereClauseForCategory() + orConstant;
            stageDetailsWhereArgsForCategoryArray[stageDetailsCategoryIndex] = stageDetailTable.getWhereArgsForCategory(category);
            stageDetailsCategoryIndex++;
        }

        // Remove the trailing "OR", for the next part of the query.
        if (stageDetailsWhereClauseForCategory.endsWith(orConstant)) {
            stageDetailsWhereClauseForCategory = Utils.removeLastOccurrenceInString(stageDetailsWhereClauseForCategory, orConstant);
        }

        List<String> whereArgsForCategoryList = queryStageDetailsTableForBikeRaceIdsUsingCategory(stageDetailsWhereClauseForCategory, stageDetailsWhereArgsForCategoryArray);
        String whereClauseForCategory = bikeRaceTable.getWhereClauseForPkInRange(whereArgsForCategoryList.size());

        // Query table; the order of whereClause and whereArgs must be preserved.
        String whereClause = whereClauseForRaceTypes + andConstant + whereClauseForSearchMonths + andConstant + whereClauseForCategory;
        List<String> whereArgsList = new ArrayList<>();
        whereArgsList.addAll(whereArgsForRaceTypeList);
        whereArgsList.addAll(whereArgsForSearchMonths);
        whereArgsList.addAll(whereArgsForCategoryList);
        String[] whereArgs = (String[]) whereArgsList.toArray();

        return queryBikeRaceTable(whereClause, whereArgs);
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
                bikeRace.setStageDetails(queryStageDetailsTableUsingBikeRaceId(bikeRace.getId(), database, cursor));
            }
        }

        // Close the connections.
        database.close();
        cursor.close();

        return bikeRaces;
    }

    private List<StageDetail> queryStageDetailsTableUsingBikeRaceId(long bikeRaceId, SQLiteDatabase database, Cursor cursor) {

        // HACK: Not using a LocalVariable here as it'll ruin the cursor.close() in the calling method.
        cursor = database.query(StageDetailTableOperation.TABLE_NAME, null, stageDetailTable.getWhereClauseForFk(), stageDetailTable.getWhereArgsForFk(bikeRaceId), null, null, null);

        return stageDetailTable.populateListFromCursor(cursor);
    }

    private List<String> queryStageDetailsTableForBikeRaceIdsUsingCategory(String whereClause, String[] whereArgs) {

        List<String> bikeRaceIds = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        boolean isDistinct = true;

        Cursor cursor = database.query(isDistinct, StageDetailTableOperation.TABLE_NAME, stageDetailTable.getColumnsForFk(), whereClause, whereArgs, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                bikeRaceIds.add(((Integer) cursor.getInt(cursor.getColumnIndex(StageDetailTableOperation.FK_COLUMN_BIKERACEID))).toString());
            } while (cursor.moveToNext());
        }

        // TODO Investigate wether database and cursor variables need to be closed here.

        return bikeRaceIds;
    }
}
