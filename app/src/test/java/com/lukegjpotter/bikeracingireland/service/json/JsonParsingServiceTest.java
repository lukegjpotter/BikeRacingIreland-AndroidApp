package com.lukegjpotter.bikeracingireland.service.json;

import com.lukegjpotter.bikeracingireland.model.BikeRace;
import com.lukegjpotter.bikeracingireland.testresources.TestResources;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonParsingServiceTest {

    TestResources tr = new TestResources();
    JsonParsingService jsonParsingService = new JsonParsingService();

    @Test
    public void parseInputStreamReader() {

        InputStreamReader inputStreamReader = null;

        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(tr.getSingleRaceFilename()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<BikeRace> bikeRaces = jsonParsingService.parseInputStreamReader(inputStreamReader);
        assertEquals(bikeRaces.get(0), tr.getSingleRace());
    }
}
