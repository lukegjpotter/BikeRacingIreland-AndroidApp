package com.lukegjpotter.bikeracingireland.initialdata;

import com.lukegjpotter.bikeracingireland.model.BikeRace;
import com.lukegjpotter.bikeracingireland.model.StageDetail;
import com.lukegjpotter.bikeracingireland.service.BikeRaceListViewDataService;
import com.lukegjpotter.bikeracingireland.utils.MonthManager;
import com.lukegjpotter.bikeracingireland.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for loading Initial Data for the Application's Testing.
 */
public class InitialData {

    BikeRaceListViewDataService mDataService;

    public InitialData(BikeRaceListViewDataService bikeRaceListViewDataService) {
        mDataService = bikeRaceListViewDataService;
    }

    /**
     * A threaded method that will insert two {@code BikeRace} objects for the current Month into the Database.
     */
    public void insertInitialData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                
                final List<BikeRace> bikeRaces = new ArrayList<>();
                bikeRaces.add(getFinnWheelersTTRace());
                bikeRaces.add(getMarylandWheelersRace());

                // Insert the Bike Races into the Database
                mDataService.insertBikeRaces(bikeRaces);
            }
        }).start();
    }

    private BikeRace getFinnWheelersTTRace() {

        BikeRace bikeRace = new BikeRace();
        bikeRace.setId(130L);
        bikeRace.setStartDate(Utils.convertStringToDate("20160605"));
        bikeRace.setMonthNumber(MonthManager.currentMonthNumber());
        bikeRace.setBookingsOpenDate(Utils.convertStringToDate("20160605"));
        bikeRace.setBookingsCloseDate(Utils.convertStringToDate("20160605"));
        bikeRace.setEventName("Finn Wheeler's Time Trial");
        bikeRace.setPromotingClub("Finn Wheelers");
        bikeRace.setOrganiser("Tony Brogan");
        bikeRace.setRegistrationLink("");
        bikeRace.setOrganiserPhoneNumber("+353877932118");
        bikeRace.setOrganiserEmail("tonybrog@gmail.com");
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

        StageDetail stageDetail = new StageDetail();
        stageDetail.setId(434L);
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
        bikeRace.addStageDetail(stageDetail);

        return bikeRace;
    }

    private BikeRace getMarylandWheelersRace() {

        BikeRace bikeRace = new BikeRace();
        bikeRace.setId(131L);
        bikeRace.setStartDate(Utils.convertStringToDate("20160606"));
        bikeRace.setMonthNumber(MonthManager.currentMonthNumber());
        bikeRace.setBookingsOpenDate(Utils.convertStringToDate("20160606"));
        bikeRace.setBookingsCloseDate(Utils.convertStringToDate("20160608"));
        bikeRace.setEventName("Temple");
        bikeRace.setPromotingClub("Maryland Wheelers");
        bikeRace.setOrganiser("Phil Holland");
        bikeRace.setRegistrationLink("");
        bikeRace.setOrganiserPhoneNumber("+447766655644");
        bikeRace.setOrganiserEmail("phil.holland4@btopenworld.com");
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

        StageDetail stageDetail = new StageDetail();
        stageDetail.setId(435L);
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
        bikeRace.addStageDetail(stageDetail);

        return bikeRace;
    }
}
