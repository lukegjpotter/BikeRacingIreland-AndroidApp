package com.lukegjpotter.bikeracingireland.database;

interface TableOperation<T> {

    String getCreateSql();

    String getDropSql();

    void create(T t);
}
