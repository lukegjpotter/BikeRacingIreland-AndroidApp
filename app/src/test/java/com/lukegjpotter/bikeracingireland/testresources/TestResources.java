package com.lukegjpotter.bikeracingireland.testresources;

import com.lukegjpotter.bikeracingireland.model.BikeRace;
import com.lukegjpotter.bikeracingireland.model.StageDetail;

import java.util.ArrayList;
import java.util.List;

public class TestResources {

    public String getSingleRaceFilename() {
        return "./src/test/res/SingleRace.json";
    }

    public String getMultipleRaceFilename() {
        return "./src/test/res/MultipleRaces.json";
    }

    public BikeRace getSingleRace() {

        BikeRace bikeRace = new BikeRace();
        bikeRace.setId(130L);
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
        stageDetail.setId(434L);
        stageDetail.setDate(TestUtils.convertStringToDate("20160605"));
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

    public List<BikeRace> getMultipleRace() {
        List<BikeRace> bikeRaces = new ArrayList<>();

        bikeRaces.add(getSingleRace());
        bikeRaces.add(getOtherSingleRace());

        return bikeRaces;
    }

    private BikeRace getOtherSingleRace() {

        BikeRace bikeRace = new BikeRace();
        bikeRace.setId(131L);
        bikeRace.setStartDate(TestUtils.convertStringToDate("20160606"));
        bikeRace.setBookingsOpenDate(TestUtils.convertStringToDate("20160606"));
        bikeRace.setBookingsCloseDate(TestUtils.convertStringToDate("20160608"));
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
        stageDetail.setDate(TestUtils.convertStringToDate("20160606"));
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
