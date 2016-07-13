package com.lukegjpotter.bikeracingireland.database;

import android.content.ContentValues;

interface TableOperation<T> {

    String getCreateSql();

    String getDropSql();

    ContentValues getInsertContentValues(T t, Long bikeRaceId);
}
