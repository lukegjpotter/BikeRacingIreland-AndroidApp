package com.lukegjpotter.bikeracingireland.repository.retrofit;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lukegjpotter.bikeracingireland.constant.Constants;
import com.lukegjpotter.bikeracingireland.model.entity.BikeRaceWithStageDetails;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The Retrofit Repository.
 * <p>
 * Created by lukegjpotter on 19/12/2017.
 */

public class BikeRaceRetrofitRepository {

    private static BikeRaceRetrofitRepository INSTANCE;
    private BikeRaceRetrofitClient bikeRaceRetrofitClient;

    private BikeRaceRetrofitRepository() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat(Constants.DATE_FORMAT)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.REST_BASE_URL_LOCAL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        bikeRaceRetrofitClient = retrofit.create(BikeRaceRetrofitClient.class);
    }

    public static BikeRaceRetrofitRepository getInstance() {
        if (INSTANCE == null) INSTANCE = new BikeRaceRetrofitRepository();

        return INSTANCE;
    }

    public List<BikeRaceWithStageDetails> bikeRacesInMonthNumber(int monthNumber) {

        Call<List<BikeRaceWithStageDetails>> call = bikeRaceRetrofitClient.bikeRacesForMonthNumber(monthNumber);

        call.enqueue(new Callback<List<BikeRaceWithStageDetails>>() {
            @Override
            public void onResponse(@NonNull Call<List<BikeRaceWithStageDetails>> call, @NonNull Response<List<BikeRaceWithStageDetails>> response) {
            }

            @Override
            public void onFailure(@NonNull Call<List<BikeRaceWithStageDetails>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
