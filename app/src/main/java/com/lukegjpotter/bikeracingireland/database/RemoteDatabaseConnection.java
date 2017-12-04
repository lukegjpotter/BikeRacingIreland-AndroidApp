package com.lukegjpotter.bikeracingireland.database;

import com.lukegjpotter.bikeracingireland.R;
import com.lukegjpotter.bikeracingireland.service.json.JsonParsingService;
import com.lukegjpotter.bikeracingireland.utils.Utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RemoteDatabaseConnection implements DatabaseConnection {

    JsonParsingService jsonParsingService;

    public RemoteDatabaseConnection() {
        jsonParsingService = new JsonParsingService();
    }

    /**
     * Get the {@code BikeRace}s with the {@code monthNumber} specified.
     * Month Number is starting from Zero. 0 is January, 11 is December, everything is inbetween.
     *
     * @param monthNumber The number of the Month to search for, e.g. 0 is Jan, 11 is Dec
     * @return The List of the {@code BikeRace}s with the {@code monthNumber}.
     */
    @Override
    public List<BikeRace> retrieveBikeRacesInMonth(final int monthNumber) {

        final List<BikeRace> bikeRaces = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;

                // TODO: Future problem. This string resource is not loading: android.content.res.Resources$NotFoundException
                String requestUrl = Utils.getApplicationContext().getString(R.string.request_url_month_number, String.valueOf(monthNumber));

                try {
                    // Make HTTP GET URL Call
                    connection = (HttpURLConnection) new URL(requestUrl).openConnection();
                    connection.connect();

                    // Assign the Response to a Stream Object
                    InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());

                    // Parse Stream in jsonParsingService
                    bikeRaces.addAll(jsonParsingService.parseInputStreamReader(inputStreamReader));
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }

            }
        }).start();

        return bikeRaces;
    }
}
