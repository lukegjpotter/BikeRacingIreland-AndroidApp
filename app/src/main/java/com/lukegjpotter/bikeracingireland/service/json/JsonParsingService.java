package com.lukegjpotter.bikeracingireland.service.json;

import com.lukegjpotter.bikeracingireland.model.BikeRace;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class JsonParsingService {

    public List<BikeRace> parseJsonObject(JSONObject jsonObject) {

        List<BikeRace> bikeRaces = new ArrayList<>();
        BikeRace bikeRace = new BikeRace();

        // Add Parsing Logic, look into GSON.
        bikeRaces.add(bikeRace);

        return bikeRaces;
    }
}
