package com.lukegjpotter.bikeracingireland.service.json;

import com.lukegjpotter.bikeracingireland.model.BikeRace;
import com.lukegjpotter.bikeracingireland.model.StageDetail;
import com.lukegjpotter.bikeracingireland.testresources.TestResources;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonParsingServiceTest {

    TestResources tr = new TestResources();
    JsonParsingService jsonParsingService = new JsonParsingService();

    @Test
    public void parseInputStreamReader_SingleRace() {

        InputStreamReader inputStreamReader = null;

        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(tr.getSingleRaceFilename()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BikeRace actualBikeRace = jsonParsingService.parseInputStreamReader(inputStreamReader).get(0);
        BikeRace expectedBikeRace = tr.getSingleRace();
        compareBikeRaces(actualBikeRace, expectedBikeRace);
    }

    @Test
    public void parseInputStreamReader_MultipleRace() {

        InputStreamReader inputStreamReader = null;

        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(tr.getMultipleRaceFilename()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<BikeRace> actualBikeRaces = jsonParsingService.parseInputStreamReader(inputStreamReader);
        List<BikeRace> expectedBikeRaces = tr.getMultipleRace();

        assertTrue(actualBikeRaces.size() == expectedBikeRaces.size());

        for (int i = 0; i < actualBikeRaces.size(); i++) {
            System.out.print("Comparing BikeRaces in List Position " + i);
            compareBikeRaces(actualBikeRaces.get(i), expectedBikeRaces.get(i));
            System.out.println(" ... OK");
        }
    }


    private void compareBikeRaces(BikeRace actual, BikeRace expected) {

        // Check BikeRace POJO Specific Fields.
        assertEquals("ID", actual.getId(), expected.getId());
        assertEquals("StartDate", actual.getStartDate(), expected.getStartDate());
        assertEquals("EventName", actual.getEventName(), expected.getEventName());
        assertEquals("PromotingClub", actual.getPromotingClub(), expected.getPromotingClub());
        assertEquals("Organiser", actual.getOrganiser(), expected.getOrganiser());
        assertEquals("RegistrationLink", actual.getRegistrationLink(), expected.getRegistrationLink());
        assertEquals("BookingsOpenDate", actual.getBookingsOpenDate(), expected.getBookingsOpenDate());
        assertEquals("BookingsCloseDate", actual.getBookingsCloseDate(), expected.getBookingsCloseDate());
        assertEquals("OrganiserPhoneNumber", actual.getOrganiserPhoneNumber(), expected.getOrganiserPhoneNumber());
        assertEquals("OrganiserEmail", actual.getOrganiserEmail(), expected.getOrganiserEmail());
        assertEquals("Location", actual.getLocation(), expected.getLocation());
        assertEquals("Province", actual.getProvince(), expected.getProvince());
        assertTrue("A+ Act: " + actual.isAPlus() + ". Exp: " + expected.isAPlus() + ".", actual.isAPlus() == expected.isAPlus());
        assertTrue("A1", actual.isA1() == expected.isA1());
        assertTrue("A2", actual.isA2() == expected.isA2());
        assertTrue("A3", actual.isA3() == expected.isA3());
        assertTrue("A4", actual.isA4() == expected.isA4());
        assertTrue("Women", actual.isWoman() == expected.isWoman());
        assertTrue("Vets", actual.isVets() == expected.isVets());
        assertTrue("Junior", actual.isJunior() == expected.isJunior());
        assertTrue("Youth", actual.isYouth() == expected.isYouth());
        assertTrue("Paracycling", actual.isParacycling() == expected.isParacycling());

        // Check StageDetails of BikeRace.
        assertTrue("StageDetails Size", actual.getStageDetails().size() == expected.getStageDetails().size());

        for (int i = 0; i < actual.getStageDetails().size(); i++) {
            StageDetail actualStageDetail = actual.getStageDetails().get(i);
            StageDetail expectedStageDetail = expected.getStageDetails().get(i);

            assertEquals("Date", actualStageDetail.getDate(), expectedStageDetail.getDate());
            assertEquals("Location", actualStageDetail.getLocation(), expectedStageDetail.getLocation());
            assertEquals("RaceNumber", actualStageDetail.getRaceNumber(), expectedStageDetail.getRaceNumber());
            assertEquals("StageNumber", actualStageDetail.getStageNumber(), expectedStageDetail.getStageNumber());
            assertEquals("RaceType", actualStageDetail.getRaceType(), expectedStageDetail.getRaceType());
            assertEquals("Category", actualStageDetail.getCategory(), expectedStageDetail.getCategory());
            assertEquals("SignOnTime", actualStageDetail.getSignOnTime(), expectedStageDetail.getSignOnTime());
            assertEquals("StartTime", actualStageDetail.getStartTime(), expectedStageDetail.getStartTime());
            assertEquals("RouteLinkUrl", actualStageDetail.getRouteLinkUrl(), expectedStageDetail.getRouteLinkUrl());
            assertEquals("Kilometers", actualStageDetail.getKilometers(), expectedStageDetail.getKilometers());
            assertEquals("Miles", actualStageDetail.getMiles(), expectedStageDetail.getMiles());
        }

    }
}
