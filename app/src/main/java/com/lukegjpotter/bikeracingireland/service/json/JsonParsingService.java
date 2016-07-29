package com.lukegjpotter.bikeracingireland.service.json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lukegjpotter.bikeracingireland.constant.Constants;
import com.lukegjpotter.bikeracingireland.model.BikeRace;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class JsonParsingService {

    public List<BikeRace> parseInputStreamReader(InputStreamReader in) {

        // Parsing Logic using GSON.
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat(Constants.DATE_FORMAT);
        Gson gson = gsonBuilder.create();

        try { // For JSON Object - Multiple BikeRaces.
            Type collectionType = new TypeToken<Collection<BikeRace>>() {
            }.getType();
            Collection<BikeRace> bikeRacesCollection = gson.fromJson(in, collectionType);

            return new ArrayList<>(bikeRacesCollection);

        } catch (JsonSyntaxException e) { // For JSON Array - Single BikeRace.
            // TODO Throwing NullPointerException.
            return Arrays.asList(gson.fromJson(in, BikeRace.class));
        }
    }
}
