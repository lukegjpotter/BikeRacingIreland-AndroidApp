package com.lukegjpotter.bikeracingireland.database;

import android.content.ContentValues;

import com.lukegjpotter.bikeracingireland.model.StageDetail;

class StageDetailTableOperation implements TableOperation<StageDetail> {

    private String tableName = "StageDetail";

    // Column Names.
    private String id = "id";
    private String bikeRaceId = "bikeRaceId"; // Foreign Key.
    private String date = "date";
    private String raceNumber = "raceNumber";
    private String stageNumber = "stageNumber";
    private String location = "location";
    private String raceType = "raceType";
    private String category = "category";
    private String signOnTime = "signOnTime";
    private String startTime = "startTime";
    private String routeLinkUrl = "routeLinkUrl";
    private String kilometers = "kilometers";
    private String miles = "miles";

    @Override
    public String getCreateSql() {
        return "CREATE TABLE " + tableName + " ("
                + id + " INTEGER PRIMARY KEY, "
                + bikeRaceId + " INTEGER, "
                + date + " TEXT, "
                + raceNumber + " INTEGER, "
                + stageNumber + " INTEGER, "
                + location + " TEXT, "
                + raceType + " TEXT, "
                + category + " TEXT, "
                + signOnTime + " TEXT, "
                + startTime + " TEXT, "
                + routeLinkUrl + " TEXT, "
                + kilometers + " REAL, "
                + miles + " REAL, "
                + " FOREIGN KEY (" + bikeRaceId + ") REFERENCES "
                + BikeRaceTableOperation.TABLE_NAME + " (" + BikeRaceTableOperation.PK_COLUMN + ")"
                + ");";
    }

    @Override
    public String getDropSql() {
        return "DROP TABLE IF EXISTS " + tableName;
    }

    @Override
    public void create(StageDetail stageDetail) {

    }

    @Override
    public ContentValues getInsertContentValues(StageDetail stageDetail) {
        return null;
    }
}
