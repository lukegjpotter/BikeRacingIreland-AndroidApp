package com.lukegjpotter.bikeracingireland.repository.retrofit;

import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceWithStageDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * The Client for Retrofit.
 * <p>
 * This will define the calls that we can make.
 * It will map the EndPoints too.
 * <p>
 * Created by lukegjpotter on 19/12/2017.
 */

public interface BikeRaceRetrofitClient {

    @GET("/roadraces/month/{monthNumber}")
    Call<List<BikeRaceWithStageDetails>> bikeRacesForMonthNumber(@Path("monthNumber") int monthNumber);
}
