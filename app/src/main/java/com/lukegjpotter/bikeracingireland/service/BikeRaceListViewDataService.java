package com.lukegjpotter.bikeracingireland.service;


import android.content.Context;

import com.lukegjpotter.bikeracingireland.database.BikeRace;
import com.lukegjpotter.bikeracingireland.database.DatabaseConnection;
import com.lukegjpotter.bikeracingireland.database.LocalDatabaseConnection;
import com.lukegjpotter.bikeracingireland.database.RemoteDatabaseConnection;
import com.lukegjpotter.bikeracingireland.model.entity.ProfileFilterEntity;
import com.lukegjpotter.bikeracingireland.model.roomdatabase.ApplicationDatabase;
import com.lukegjpotter.bikeracingireland.utils.MonthManager;
import com.lukegjpotter.bikeracingireland.utils.Utils;

import java.util.List;

public class BikeRaceListViewDataService {

    private DatabaseConnection remoteDatabaseConnection, localDatabaseConnection;

    public BikeRaceListViewDataService(Context context) {
        remoteDatabaseConnection = new RemoteDatabaseConnection();
        localDatabaseConnection = LocalDatabaseConnection.getInstance(context);
    }

    public List<BikeRace> fetchBikeRacesInMonthNumber(int monthNumber) {

        try { // TODO: This Sleep is just for testing
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
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

    public BikeRace fetchBikeRaceByPk(long databasePk) {
        return ((LocalDatabaseConnection) localDatabaseConnection).retrieveBikeRaceWithId(databasePk);
    }

    public void insertBikeRaces(List<BikeRace> bikeRaces) {
        ((LocalDatabaseConnection) localDatabaseConnection).insertBikeRaceList(bikeRaces);
    }

    public List<BikeRace> fetchBikeRacesForProfileFilter() {
        ProfileFilterEntity profileFilter = ApplicationDatabase.getInstance(Utils.getApplicationContext()).profileFilterDao().findProfileFilter().getValue();

        // Bike Races in the current month view.
        List<BikeRace> bikeRaces = ((LocalDatabaseConnection) localDatabaseConnection).retrieveBikeRacesWithRaceTypeInCategoryForMonths(profileFilter.getRaceTypes(), profileFilter.getCategories(), MonthManager.getMonthsInListView());

        if (bikeRaces.isEmpty()) {
            // Bike Races in the rest of the year.
            bikeRaces = ((LocalDatabaseConnection) localDatabaseConnection).retrieveBikeRacesWithRaceTypeInCategoryForMonths(profileFilter.getRaceTypes(), profileFilter.getCategories(), MonthManager.getRemainingMonthsInYear());
        }
        // Might need to get the races from the RemoteDatabaseConnection...

        return bikeRaces;
    }
}
