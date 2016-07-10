package com.lukegjpotter.bikeracingireland.database;

import com.lukegjpotter.bikeracingireland.model.BikeRace;

class BikeRaceTableOperation implements TableOperation<BikeRace> {

    static String TABLE_NAME = "BikeRace";

    // Column Names.
    static String PK_COLUMN = "id";
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
    public void create(BikeRace bikeRace) {

    }
}
