package com.lukegjpotter.bikeracingireland.service.json;

import com.lukegjpotter.bikeracingireland.model.BikeRace;
import com.lukegjpotter.bikeracingireland.testresources.TestResources;

import org.json.JSONObject;
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
    public void parseJSONObject() {

        InputStreamReader inputStreamReader = null;

        try {
            String filename = "./src/test/res/SingleRace.json";
            inputStreamReader = new InputStreamReader(new FileInputStream(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JSONObject singleBikeRaceJsonObject = new JsonInputStreamReader().readJson(inputStreamReader);
        List<BikeRace> bikeRaces = jsonParsingService.parseJsonObject(singleBikeRaceJsonObject);

        assertEquals(bikeRaces.get(0), tr.getSingleRace());
    }
}
