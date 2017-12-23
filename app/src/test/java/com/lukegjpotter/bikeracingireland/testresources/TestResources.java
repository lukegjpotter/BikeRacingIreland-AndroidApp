package com.lukegjpotter.bikeracingireland.testresources;

import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceEntity;
import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceWithStageDetails;
import com.lukegjpotter.bikeracingireland.model.entity.StageDetailEntity;

import java.util.ArrayList;
import java.util.List;

public class TestResources {

    public BikeRaceWithStageDetails getSingleRace() {

        BikeRaceEntity bikeRace = new BikeRaceEntity();
        bikeRace.setPkBikeRaceEntityId(130L);
        bikeRace.setStartDate(TestUtils.convertStringToDate("20160605"));
        bikeRace.setMonthNumber(6);
        bikeRace.setBookingsOpenDate(TestUtils.convertStringToDate("20160605"));
        bikeRace.setBookingsCloseDate(TestUtils.convertStringToDate("20160605"));
        bikeRace.setEventName("Finn Wheeler's Time Trial");
        bikeRace.setPromotingClub("Finn Wheelers");
        bikeRace.setOrganiser("Tony Brogan");
        bikeRace.setRegistrationLink("");
        bikeRace.setOrganiserPhoneNumber("+353877932000");
        bikeRace.setOrganiserEmail("tonybrog@email.com");
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

        BikeRaceWithStageDetails bikeRaceWithStageDetails = new BikeRaceWithStageDetails();
        bikeRaceWithStageDetails.bikeRaceEntity = bikeRace;
        bikeRaceWithStageDetails.stageDetails.add(stageDetail);

        return bikeRaceWithStageDetails;
    }

    public List<BikeRaceWithStageDetails> getMultipleRace() {
        List<BikeRaceWithStageDetails> bikeRaces = new ArrayList<>();

        bikeRaces.add(getSingleRace());
        bikeRaces.add(getOtherSingleRace());

        return bikeRaces;
    }

    private BikeRaceWithStageDetails getOtherSingleRace() {

        BikeRaceEntity bikeRace = new BikeRaceEntity();
        bikeRace.setPkBikeRaceEntityId(131L);
        bikeRace.setStartDate(TestUtils.convertStringToDate("20160606"));
        bikeRace.setMonthNumber(6);
        bikeRace.setBookingsOpenDate(TestUtils.convertStringToDate("20160606"));
        bikeRace.setBookingsCloseDate(TestUtils.convertStringToDate("20160608"));
        bikeRace.setEventName("Temple");
        bikeRace.setPromotingClub("Maryland Wheelers");
        bikeRace.setOrganiser("Phil Holland");
        bikeRace.setRegistrationLink("");
        bikeRace.setOrganiserPhoneNumber("+447766655000");
        bikeRace.setOrganiserEmail("phil.holland4@email.com");
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

        BikeRaceWithStageDetails bikeRaceWithStageDetails = new BikeRaceWithStageDetails();
        bikeRaceWithStageDetails.bikeRaceEntity = bikeRace;
        bikeRaceWithStageDetails.stageDetails.add(stageDetail);

        return bikeRaceWithStageDetails;
    }
}
