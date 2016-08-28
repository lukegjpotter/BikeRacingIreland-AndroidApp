package com.lukegjpotter.bikeracingireland.service;


import android.content.Context;

import com.lukegjpotter.bikeracingireland.database.DatabaseConnection;
import com.lukegjpotter.bikeracingireland.database.LocalDatabaseConnection;
import com.lukegjpotter.bikeracingireland.database.RemoteDatabaseConnection;
import com.lukegjpotter.bikeracingireland.model.BikeRace;

import java.util.List;

public class BikeRaceListViewDataService {

    private DatabaseConnection remoteDatabaseConnection, localDatabaseConnection;

    public BikeRaceListViewDataService(Context context) {
        remoteDatabaseConnection = new RemoteDatabaseConnection();
        localDatabaseConnection = LocalDatabaseConnection.getInstance(context);
    }

    public List<BikeRace> fetchBikeRacesInMonthNumber(int monthNumber) {

        List<BikeRace> bikeRacesInMonth = localDatabaseConnection.retrieveBikeRacesInMonth(monthNumber);

        if (bikeRacesInMonth.isEmpty()) {
            // There's no local data, so get it from the RemoteService, and store it locally
            // TODO Use a Threading type here that will not depend on the Activity calling it.
            bikeRacesInMonth = remoteDatabaseConnection.retrieveBikeRacesInMonth(monthNumber);
            // TODO Kick off a thread to do this non-UI work.
            ((LocalDatabaseConnection) localDatabaseConnection).insertBikeRaceList(bikeRacesInMonth);
        }

        return bikeRacesInMonth;
    }
}