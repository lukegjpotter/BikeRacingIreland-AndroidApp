package com.lukegjpotter.bikeracingireland.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lukegjpotter.bikeracingireland.model.BikeRace;
import com.lukegjpotter.bikeracingireland.model.StageDetail;

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

    public synchronized void insertBikeRace(BikeRace bikeRace) {

        // Abandon if this BikeRace is a duplicate.
        if (DatabaseConnectionUtils.isBikeRaceInDatabase(getReadableDatabase(), bikeRace.getId()))
            return;

        // Proceed with the insert.
        SQLiteDatabase database = getWritableDatabase();
        database.insert(BikeRaceTableOperation.TABLE_NAME, null, bikeRaceTable.getInsertContentValues(bikeRace, null));
        for (StageDetail stageDetail : bikeRace.getStageDetails()) {
            database.insert(StageDetailTableOperation.TABLE_NAME, null, stageDetailTable.getInsertContentValues(stageDetail, bikeRace.getId()));
        }
        database.close();
    }
}
