package com.lukegjpotter.bikeracingireland.repository.retrofit;

/**
 * The Retrofit Repository.
 * <p>
 * Created by lukegjpotter on 19/12/2017.
 */

public class BikeRaceRetrofitRepository {

    private static BikeRaceRetrofitRepository INSTANCE;

    private BikeRaceRetrofitRepository() {
        // TODO: initialize Retrofit.
    }

    public static BikeRaceRetrofitRepository getInstance() {
        if (INSTANCE == null) INSTANCE = new BikeRaceRetrofitRepository();

        return INSTANCE;
    }
}
