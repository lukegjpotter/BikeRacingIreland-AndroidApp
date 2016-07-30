package com.lukegjpotter.bikeracingireland.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

class DatabaseConnectionUtils {

    public static boolean isBikeRaceInDatabase(SQLiteDatabase database, long bikeRaceId) {

        BikeRaceTableOperation bikeRaceTable = new BikeRaceTableOperation();

        String whereClause = bikeRaceTable.getWhereClause();
        String[] whereArgs = bikeRaceTable.getWhereArgs(bikeRaceId);
        String limit = String.valueOf(1);

        Cursor cursor = database.query(BikeRaceTableOperation.TABLE_NAME, null, whereClause, whereArgs, null, null, null, limit);
        boolean isBikeRaceInDatabase = cursor.moveToFirst();

        database.close();
        cursor.close();

        return isBikeRaceInDatabase;
    }
}
