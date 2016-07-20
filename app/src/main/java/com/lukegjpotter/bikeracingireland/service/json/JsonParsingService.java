package com.lukegjpotter.bikeracingireland.service.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lukegjpotter.bikeracingireland.constant.Constants;
import com.lukegjpotter.bikeracingireland.model.BikeRace;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

class JsonParsingService {

    public List<BikeRace> parseInputStreamReader(InputStreamReader in) {

        // Parsing Logic using GSON.
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat(Constants.DATE_FORMAT);
        Gson gson = gsonBuilder.create();

        return Arrays.asList(gson.fromJson(in, BikeRace.class));
    }
}
