package com.lukegjpotter.bikeracingireland.database;

import java.util.List;

public interface DatabaseConnection {

    List<BikeRace> retrieveBikeRacesInMonth(int monthNumber);
}
