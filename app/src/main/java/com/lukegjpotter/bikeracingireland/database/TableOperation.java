package com.lukegjpotter.bikeracingireland.database;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

interface TableOperation<T> {

    String getCreateSql();

    String getDropSql();

    ContentValues getContentValues(T t, Long bikeRaceId);

    String getWhereClauseForPk();

    String[] getWhereArgsForPk(long bikeRaceId);

    List<T> populateListFromCursor(Cursor cursor);
}
