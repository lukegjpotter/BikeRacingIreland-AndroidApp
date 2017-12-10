package com.lukegjpotter.bikeracingireland.model.roomdatabase.util;

import android.os.AsyncTask;

import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceEntity;
import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceWithStageDetails;
import com.lukegjpotter.bikeracingireland.model.entity.StageDetailEntity;
import com.lukegjpotter.bikeracingireland.model.roomdatabase.ApplicationDatabase;
import com.lukegjpotter.bikeracingireland.utils.MonthManager;
import com.lukegjpotter.bikeracingireland.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * To be used for Testing Purposes.
 * It loads in dummy data.
 * <p>
 * Created by lukegjpotter on 28/11/2017.
 */

public class DatabaseInitializer {

    public static void populateAsync(final ApplicationDatabase database) {
        new PopulateDbAsync(database).execute();
    }

    private static BikeRaceWithStageDetails getFinnWheelersTTRace() {

        BikeRaceEntity bikeRace = new BikeRaceEntity();
        bikeRace.setPkBikeRaceEntityId(130L);
        bikeRace.setStartDate(Utils.convertStringToDate("20160605"));
        bikeRace.setMonthNumber(MonthManager.currentMonthNumber());
        bikeRace.setBookingsOpenDate(Utils.convertStringToDate("20160605"));
        bikeRace.setBookingsCloseDate(Utils.convertStringToDate("20160605"));
        bikeRace.setEventName("Finn Wheeler's Time Trial");
        bikeRace.setPromotingClub("Finn Wheelers");
        bikeRace.setOrganiser("Tony Brogan");
        bikeRace.setRegistrationLink("");
        bikeRace.setOrganiserPhoneNumber("+353877932000");
        bikeRace.setOrganiserEmail("tony@race.com");
        bikeRace.setLocation("Glenfin Road, Ballybofey");
        bikeRace.setProvince("");

        bikeRace.setParacycling(false);
        bikeRace.setAPlus(true);
        bikeRace.setA1(true);
        bikeRace.setA2(true);
        bikeRace.setA3(true);
        bikeRace.setA4(true);
        bikeRace.setVets(true);
        bikeRace.setWoman(true);
        bikeRace.setJunior(true);
        bikeRace.setYouth(false);

        StageDetailEntity stageDetail = new StageDetailEntity();
        stageDetail.setPkStageDetailEntityId(434L);
        stageDetail.setFkBikeRaceEntityId(bikeRace.getPkBikeRaceEntityId());
        stageDetail.setDate(Utils.convertStringToDate("20160605"));
        stageDetail.setRaceNumber(1);
        stageDetail.setStageNumber(1);
        stageDetail.setLocation("Glenfin Road");
        stageDetail.setRaceType("APlus,A1,A2,A3,A4,Women,Junior,Elite,Espoir,Senior,M40,M50,M60,LC");
        stageDetail.setCategory("Time Trial");
        stageDetail.setSignOnTime("18:30");
        stageDetail.setStartTime("19:00");
        stageDetail.setRouteLinkUrl("");
        stageDetail.setKilometers((double) 16);
        stageDetail.setMiles((double) 10);

        BikeRaceWithStageDetails brsd = new BikeRaceWithStageDetails();
        brsd.bikeRaceEntity = bikeRace;
        brsd.stageDetails.add(stageDetail);

        return brsd;
    }

    private static BikeRaceWithStageDetails getMarylandWheelersRace() {

        BikeRaceEntity bikeRace = new BikeRaceEntity();
        bikeRace.setPkBikeRaceEntityId(131L);
        bikeRace.setStartDate(Utils.convertStringToDate("20160606"));
        bikeRace.setMonthNumber(MonthManager.currentMonthNumber());
        bikeRace.setBookingsOpenDate(Utils.convertStringToDate("20160606"));
        bikeRace.setBookingsCloseDate(Utils.convertStringToDate("20160608"));
        bikeRace.setEventName("Temple");
        bikeRace.setPromotingClub("Maryland Wheelers");
        bikeRace.setOrganiser("Phil Holland");
        bikeRace.setRegistrationLink("");
        bikeRace.setOrganiserPhoneNumber("+447766655000");
        bikeRace.setOrganiserEmail("phil@race.com");
        bikeRace.setLocation("Carr Road, Lisburn");
        bikeRace.setProvince("");

        bikeRace.setParacycling(false);
        bikeRace.setAPlus(false);
        bikeRace.setA1(true);
        bikeRace.setA2(true);
        bikeRace.setA3(true);
        bikeRace.setA4(true);
        bikeRace.setVets(true);
        bikeRace.setWoman(true);
        bikeRace.setJunior(true);
        bikeRace.setYouth(false);

        StageDetailEntity stageDetail = new StageDetailEntity();
        stageDetail.setPkStageDetailEntityId(435L);
        stageDetail.setFkBikeRaceEntityId(bikeRace.getPkBikeRaceEntityId());
        stageDetail.setDate(Utils.convertStringToDate("20160606"));
        stageDetail.setRaceNumber(1);
        stageDetail.setStageNumber(1);
        stageDetail.setLocation("Carr Road");
        stageDetail.setRaceType("A1,A2,A3,A4,Women,Vets,Junior");
        stageDetail.setCategory("Road");
        stageDetail.setSignOnTime("18:00");
        stageDetail.setStartTime("18:45");
        stageDetail.setRouteLinkUrl("");
        stageDetail.setKilometers(48.3);
        stageDetail.setMiles((double) 30);

        BikeRaceWithStageDetails brsd = new BikeRaceWithStageDetails();
        brsd.bikeRaceEntity = bikeRace;
        brsd.stageDetails.add(stageDetail);

        return brsd;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ApplicationDatabase database;

        PopulateDbAsync(ApplicationDatabase database) {
            this.database = database;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // If the Database is empty, add the initial data.
            if (database.bikeRaceDao().rowCount() == 0) {
                List<BikeRaceWithStageDetails> bikeRaces = new ArrayList<>();
                bikeRaces.add(getFinnWheelersTTRace());
                bikeRaces.add(getMarylandWheelersRace());

                for (BikeRaceWithStageDetails brsd : bikeRaces) {
                    database.bikeRaceDao().insertBikeRaces(brsd.bikeRaceEntity);
                    database.stageDetailDao().insertStageDetails(brsd.stageDetails.toArray(new StageDetailEntity[brsd.stageDetails.size()]));
                }
            }

            return null;
        }
    }
}
