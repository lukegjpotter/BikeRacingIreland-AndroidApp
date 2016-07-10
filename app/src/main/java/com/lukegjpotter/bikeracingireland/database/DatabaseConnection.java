package com.lukegjpotter.bikeracingireland.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}