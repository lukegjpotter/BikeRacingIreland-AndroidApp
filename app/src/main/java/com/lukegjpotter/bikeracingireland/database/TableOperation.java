package com.lukegjpotter.bikeracingireland.database;

import android.content.ContentValues;

interface TableOperation<T> {

    String getCreateSql();

    String getDropSql();

    void create(T t);

    ContentValues getInsertContentValues(T t);
}
