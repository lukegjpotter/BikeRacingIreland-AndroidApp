package com.lukegjpotter.bikeracingireland.service.json;

import android.content.res.Resources;

import com.lukegjpotter.bikeracingireland.R;
import com.lukegjpotter.bikeracingireland.model.BikeRace;
import com.lukegjpotter.bikeracingireland.utils.Utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class JsonRequestService {

    JsonParsingService jsonParser;

    public JsonRequestService() {
        jsonParser = new JsonParsingService();
    }

    public List<BikeRace> requestBikeRacesBetween(Date fromDate, Date toDate) {

        HttpURLConnection connection = null;
        List<BikeRace> bikeRaces = null;
        String requestUrl = Resources.getSystem().getString(R.string.request_url, Utils.convertDateToString(fromDate), Utils.convertDateToString(toDate));

        try {
            // Make HTTP GET URL Call
            connection = (HttpURLConnection) new URL(requestUrl).openConnection();
            connection.connect();

            // Assign the Response to a Stream Object
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());

            // Parse Stream in jsonParser
            bikeRaces = jsonParser.parseInputStreamReader(inputStreamReader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return bikeRaces;
    }
}