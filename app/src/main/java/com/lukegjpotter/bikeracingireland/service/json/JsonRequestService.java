package com.lukegjpotter.bikeracingireland.service.json;

import android.content.res.Resources;

import com.lukegjpotter.bikeracingireland.R;
import com.lukegjpotter.bikeracingireland.model.BikeRace;
import com.lukegjpotter.bikeracingireland.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
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
        BufferedReader bufferedReader = null;

        try {
            String requestUrl = Resources.getSystem().getString(R.string.request_url, Utils.convertDateToString(fromDate), Utils.convertDateToString(toDate));

            // Make HTTP GET URL Call
            connection = (HttpURLConnection) new URL(requestUrl).openConnection();
            connection.connect();

            // Assign the Response to a Stream Object
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null)
                sb.append(line);

            JSONObject parentBikeRacesJsonObject = new JSONObject(sb.toString());

            // Parse Stream in jsonParser
            return jsonParser.parseInputStream(parentBikeRacesJsonObject);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.disconnect();
            try {
                if (bufferedReader != null) bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
