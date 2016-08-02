package com.lukegjpotter.bikeracingireland.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.lukegjpotter.bikeracingireland.model.StageDetail;
import com.lukegjpotter.bikeracingireland.utils.Utils;

import java.util.ArrayList;
import java.util.List;

class StageDetailTableOperation implements TableOperation<StageDetail> {

    static final String TABLE_NAME = "StageDetail";

    // Column Names.
    private String pk_id = "pk_id";
    private String fk_bikeRaceId = "fk_bikeRaceId"; // Foreign Key.
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
        return "CREATE TABLE " + TABLE_NAME + " ("
                + pk_id + " INTEGER PRIMARY KEY, "
                + fk_bikeRaceId + " INTEGER, "
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
                + " FOREIGN KEY (" + fk_bikeRaceId + ") REFERENCES "
                + BikeRaceTableOperation.TABLE_NAME + " (" + BikeRaceTableOperation.PK_COLUMN + ")"
                + ");";
    }

    @Override
    public String getDropSql() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    @Override
    public ContentValues getContentValues(StageDetail stageDetail, Long bikeRaceId) {

        ContentValues cv = new ContentValues();
        cv.put(pk_id, stageDetail.getId());
        cv.put(fk_bikeRaceId, bikeRaceId);
        cv.put(date, Utils.convertDateToString(stageDetail.getDate()));
        cv.put(raceNumber, stageDetail.getRaceNumber());
        cv.put(stageNumber, stageDetail.getStageNumber());
        cv.put(location, stageDetail.getLocation());
        cv.put(raceType, stageDetail.getRaceType());
        cv.put(category, stageDetail.getCategory());
        cv.put(signOnTime, stageDetail.getSignOnTime());
        cv.put(startTime, stageDetail.getStartTime());
        cv.put(routeLinkUrl, stageDetail.getRouteLinkUrl());
        cv.put(kilometers, stageDetail.getKilometers());
        cv.put(miles, stageDetail.getMiles());

        return cv;
    }

    @Override
    public String getWhereClauseForPk() {
        return pk_id + "=?";
    }

    @Override
    public String[] getWhereArgsForPk(long stageDetailId) {
        return new String[]{String.valueOf(stageDetailId)};
    }

    @Override
    public List<StageDetail> populateListFromCursor(Cursor cursor) {

        List<StageDetail> stageDetails = new ArrayList<>();

        if (!cursor.moveToFirst()) return stageDetails;

        do {
            StageDetail stageDetail = new StageDetail();

            stageDetail.setDate(Utils.convertStringToDate(cursor.getString(cursor.getColumnIndex(date))));
            stageDetail.setLocation(cursor.getString(cursor.getColumnIndex(location)));
            stageDetail.setRaceNumber(cursor.getInt(cursor.getColumnIndex(raceNumber)));
            stageDetail.setStageNumber(cursor.getInt(cursor.getColumnIndex(stageNumber)));
            stageDetail.setRaceType(cursor.getString(cursor.getColumnIndex(raceType)));
            stageDetail.setKilometers(cursor.getDouble(cursor.getColumnIndex(kilometers)));
            stageDetail.setMiles(cursor.getDouble(cursor.getColumnIndex(miles)));
            stageDetail.setCategory(cursor.getString(cursor.getColumnIndex(category)));
            stageDetail.setSignOnTime(cursor.getString(cursor.getColumnIndex(signOnTime)));
            stageDetail.setStartTime(cursor.getString(cursor.getColumnIndex(startTime)));
            stageDetail.setRouteLinkUrl(cursor.getString(cursor.getColumnIndex(routeLinkUrl)));

            stageDetails.add(stageDetail);
        } while (cursor.moveToNext());

        return stageDetails;
    }

    public String getWhereClauseForFk() {
        return fk_bikeRaceId + "=?";
    }
}
