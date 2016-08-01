package com.lukegjpotter.bikeracingireland.database;

import com.lukegjpotter.bikeracingireland.model.BikeRace;

import java.util.List;

public interface DatabaseConnection {

    List<BikeRace> retrieveBikeRacesInMonth(int monthNumber);
}
