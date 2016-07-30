package com.lukegjpotter.bikeracingireland.service.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lukegjpotter.bikeracingireland.constant.Constants;
import com.lukegjpotter.bikeracingireland.model.BikeRace;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class JsonParsingService {

    public List<BikeRace> parseInputStreamReader(InputStreamReader in) {

        // Parsing Logic using GSON.
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat(Constants.DATE_FORMAT);
        Gson gson = gsonBuilder.create();

        Type collectionType = new TypeToken<Collection<BikeRace>>() {
        }.getType();
        Collection<BikeRace> bikeRacesCollection = gson.fromJson(in, collectionType);

        return new ArrayList<>(bikeRacesCollection);
    }
}
