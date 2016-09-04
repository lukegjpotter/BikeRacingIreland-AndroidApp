package com.lukegjpotter.bikeracingireland.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.lukegjpotter.bikeracingireland.model.BikeRace;
import com.lukegjpotter.bikeracingireland.utils.Utils;

import java.util.ArrayList;
import java.util.List;

class BikeRaceTableOperation implements TableOperation<BikeRace> {

    static final String TABLE_NAME = "BikeRace";

    // Column Names.
    static final String PK_COLUMN = "id";
    private String startDate = "startDate";
    private String monthNumber = "monthNumber";
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
                + monthNumber + " INTEGER, "
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
                + isParacycling + " INTEGER);";
    }

    @Override
    public String getDropSql() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    @Override
    public ContentValues getContentValues(BikeRace bikeRace, Long bikeRaceId) {

        ContentValues cv = new ContentValues();
        cv.put(PK_COLUMN, bikeRace.getId());
        cv.put(startDate, Utils.convertDateToString(bikeRace.getStartDate()));
        cv.put(monthNumber, bikeRace.getMonthNumber());
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

    @Override
    public List<BikeRace> populateListFromCursor(Cursor cursor) {

        List<BikeRace> bikeRaces = new ArrayList<>();

        if (!cursor.moveToFirst()) return bikeRaces;

        do {

            BikeRace bikeRace = new BikeRace();

            bikeRace.setId(cursor.getLong(cursor.getColumnIndex(PK_COLUMN)));
            bikeRace.setStartDate(Utils.convertStringToDate(cursor.getString(cursor.getColumnIndex(startDate))));
            bikeRace.setMonthNumber(cursor.getInt(cursor.getColumnIndex(monthNumber)));
            bikeRace.setBookingsOpenDate(Utils.convertStringToDate(cursor.getString(cursor.getColumnIndex(bookingsOpenDate))));
            bikeRace.setBookingsCloseDate(Utils.convertStringToDate(cursor.getString(cursor.getColumnIndex(bookingsCloseDate))));
            bikeRace.setEventName(cursor.getString(cursor.getColumnIndex(eventName)));
            bikeRace.setPromotingClub(cursor.getString(cursor.getColumnIndex(promotingClub)));
            bikeRace.setOrganiser(cursor.getString(cursor.getColumnIndex(organiser)));
            bikeRace.setRegistrationLink(cursor.getString(cursor.getColumnIndex(registrationLink)));
            bikeRace.setOrganiserPhoneNumber(cursor.getString(cursor.getColumnIndex(organiserPhoneNumber)));
            bikeRace.setOrganiserEmail(cursor.getString(cursor.getColumnIndex(organiserEmail)));
            bikeRace.setLocation(cursor.getString(cursor.getColumnIndex(location)));
            bikeRace.setProvince(cursor.getString(cursor.getColumnIndex(province)));
            bikeRace.setAPlus(Utils.convertIntegerToBoolean(cursor.getInt(cursor.getColumnIndex(isAPlus))));
            bikeRace.setA1(Utils.convertIntegerToBoolean(cursor.getInt(cursor.getColumnIndex(isA1))));
            bikeRace.setA2(Utils.convertIntegerToBoolean(cursor.getInt(cursor.getColumnIndex(isA2))));
            bikeRace.setA3(Utils.convertIntegerToBoolean(cursor.getInt(cursor.getColumnIndex(isA3))));
            bikeRace.setA4(Utils.convertIntegerToBoolean(cursor.getInt(cursor.getColumnIndex(isA4))));
            bikeRace.setVets(Utils.convertIntegerToBoolean(cursor.getInt(cursor.getColumnIndex(isVets))));
            bikeRace.setWoman(Utils.convertIntegerToBoolean(cursor.getInt(cursor.getColumnIndex(isWoman))));
            bikeRace.setJunior(Utils.convertIntegerToBoolean(cursor.getInt(cursor.getColumnIndex(isJunior))));
            bikeRace.setYouth(Utils.convertIntegerToBoolean(cursor.getInt(cursor.getColumnIndex(isYouth))));
            bikeRace.setParacycling(Utils.convertIntegerToBoolean(cursor.getInt(cursor.getColumnIndex(isParacycling))));

            bikeRaces.add(bikeRace);
        } while (cursor.moveToNext());

        return bikeRaces;
    }

    @Override
    public String getWhereClauseForPk() {
        return PK_COLUMN + "=?";
    }

    @Override
    public String[] getWhereArgsForPk(long bikeRaceId) {
        return new String[]{String.valueOf(bikeRaceId)};
    }

    public String getWhereClauseForRaceType(String raceType) {
        return raceType + "=?";
    }

    public String[] getWhereArgsForRaceTypeTrue() {
        return new String[]{String.valueOf(1)};
    }

    public String getWhereClauseForMonthNumber() {
        return monthNumber + "=?";
    }

    public String[] getWhereArgsForMonthNumber(int monthNumber) {
        return new String[]{String.valueOf(monthNumber)};
    }
}
