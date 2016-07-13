package com.lukegjpotter.bikeracingireland.database;

import android.content.ContentValues;

import com.lukegjpotter.bikeracingireland.model.BikeRace;
import com.lukegjpotter.bikeracingireland.utils.Utils;

class BikeRaceTableOperation implements TableOperation<BikeRace> {

    static final String TABLE_NAME = "BikeRace";

    // Column Names.
    static final String PK_COLUMN = "id";
    private String startDate = "startDate";
    private String bookingsOpenDate = "bookingsOpenDate";
    private String bookingsCloseDate = "bookingsCloseDate";
    private String eventName = "eventName";
    private String promotingClub = "promotingClub";
    private String organiser = "organiser";
    private String registrationLink = "registrationLink";
    private String organiserPhoneNumber = "organiserPhoneNumber";
    private String organiserEmail = "organiserEmail";
    private String location = "location";
    private String province = "province";
    private String isAPlus = "isAPlus";
    private String isA1 = "isA1";
    private String isA2 = "isA2";
    private String isA3 = "isA3";
    private String isA4 = "isA4";
    private String isVets = "isVets";
    private String isWoman = "isWoman";
    private String isJunior = "isJunior";
    private String isYouth = "isYouth";
    private String isParacycling = "isParacycling";

    @Override
    public String getCreateSql() {
        return "CREATE TABLE " + TABLE_NAME + " ("
                + PK_COLUMN + " INTEGER PRIMARY KEY, "
                + startDate + " TEXT, "
                + bookingsOpenDate + " TEXT, "
                + bookingsCloseDate + " TEXT, "
                + eventName + " TEXT, "
                + promotingClub + " TEXT, "
                + organiser + " TEXT, "
                + registrationLink + " TEXT, "
                + organiserPhoneNumber + " TEXT, "
                + organiserEmail + " TEXT, "
                + location + " TEXT, "
                + province + " TEXT, "
                + isAPlus + " INTEGER, "
                + isA1 + " INTEGER, "
                + isA2 + " INTEGER, "
                + isA3 + " INTEGER, "
                + isA4 + " INTEGER, "
                + isVets + " INTEGER, "
                + isWoman + " INTEGER, "
                + isJunior + " INTEGER, "
                + isYouth + " INTEGER, "
                + isParacycling + " INTEGER, "
                + ");";
    }

    @Override
    public String getDropSql() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    @Override
    public ContentValues getInsertContentValues(BikeRace bikeRace, Long bikeRaceId) {

        ContentValues cv = new ContentValues();
        cv.put(PK_COLUMN, bikeRace.getId());
        cv.put(startDate, Utils.convertDateToString(bikeRace.getStartDate()));
        cv.put(bookingsOpenDate, Utils.convertDateToString(bikeRace.getBookingsOpenDate()));
        cv.put(bookingsCloseDate, Utils.convertDateToString(bikeRace.getBookingsCloseDate()));
        cv.put(eventName, bikeRace.getEventName());
        cv.put(promotingClub, bikeRace.getPromotingClub());
        cv.put(organiser, bikeRace.getOrganiser());
        cv.put(registrationLink, bikeRace.getRegistrationLink());
        cv.put(organiserPhoneNumber, bikeRace.getOrganiserPhoneNumber());
        cv.put(organiserEmail, bikeRace.getOrganiserEmail());
        cv.put(location, bikeRace.getLocation());
        cv.put(province, bikeRace.getProvince());
        cv.put(isAPlus, Utils.convertBooleanToInteger(bikeRace.isAPlus()));
        cv.put(isA1, Utils.convertBooleanToInteger(bikeRace.isA1()));
        cv.put(isA2, Utils.convertBooleanToInteger(bikeRace.isA2()));
        cv.put(isA3, Utils.convertBooleanToInteger(bikeRace.isA3()));
        cv.put(isA4, Utils.convertBooleanToInteger(bikeRace.isA4()));
        cv.put(isVets, Utils.convertBooleanToInteger(bikeRace.isVets()));
        cv.put(isWoman, Utils.convertBooleanToInteger(bikeRace.isWoman()));
        cv.put(isJunior, Utils.convertBooleanToInteger(bikeRace.isJunior()));
        cv.put(isYouth, Utils.convertBooleanToInteger(bikeRace.isYouth()));
        cv.put(isParacycling, Utils.convertBooleanToInteger(bikeRace.isParacycling()));

        return cv;
    }
}
