package com.lukegjpotter.bikeracingireland.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseConnectionUtils {

    public static boolean isBikeRaceInDatabase(SQLiteDatabase database, long id) {

        String selection = BikeRaceTableOperation.PK_COLUMN + "=?";
        String[] selectionArgs = new String[]{String.valueOf(id)};
        String limit = String.valueOf(1);

        Cursor cursor = database.query(BikeRaceTableOperation.TABLE_NAME, null, selection, selectionArgs, null, null, null, limit);
        boolean isBikeRaceInDatabase = cursor.moveToFirst();

        database.close();
        cursor.close();

        return isBikeRaceInDatabase;
    }
}
