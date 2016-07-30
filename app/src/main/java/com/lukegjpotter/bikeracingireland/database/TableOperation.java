package com.lukegjpotter.bikeracingireland.database;

import android.content.ContentValues;

interface TableOperation<T> {

    String getCreateSql();

    String getDropSql();

    ContentValues getContentValues(T t, Long bikeRaceId);

    String getWhereClause();

    String[] getWhereArgs(long bikeRaceId);
}
