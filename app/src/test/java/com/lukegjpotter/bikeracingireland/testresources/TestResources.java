package com.lukegjpotter.bikeracingireland.testresources;

import com.lukegjpotter.bikeracingireland.model.BikeRace;
import com.lukegjpotter.bikeracingireland.model.StageDetail;

public class TestResources {

    public BikeRace getSingleRace() {

        BikeRace bikeRace = new BikeRace();
        bikeRace.setStartDate(TestUtils.convertStringToDate("20160605"));
        bikeRace.setBookingsOpenDate(TestUtils.convertStringToDate("20160605"));
        bikeRace.setBookingsCloseDate(TestUtils.convertStringToDate("20160605"));
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
        stageDetail.setDate(TestUtils.convertStringToDate("20160605"));
        stageDetail.setRaceNumber(1);
        stageDetail.setStageNumber(1);
        stageDetail.setLocation("Glenfin Road");
        stageDetail.setRaceType("APlus,A1,A2,A3,A4,Women,Junior,Elite,Espoir,Senior,M40,M50,M60,LC");
        stageDetail.setCategory("Time Trial");
        stageDetail.setSignOnTime("18:30");
        stageDetail.setStartTime("19:00");
        stageDetail.setRouteLinkUrl("");
        stageDetail.setKilometers(16.0);
        stageDetail.setMiles(10.0);
        bikeRace.addStageDetail(stageDetail);

        return bikeRace;
    }
}
