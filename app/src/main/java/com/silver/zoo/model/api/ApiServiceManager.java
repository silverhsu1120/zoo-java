package com.silver.zoo.model.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceManager {

    private static final String API_URL = "https://data.taipei/opendata/datalist/";

    private ApiHouse apiHouse;
    private ApiPlant apiPlant;

    private static class ApiServiceManagerHolder {
        private static final ApiServiceManager INSTANCE = new ApiServiceManager();
    }

    private ApiServiceManager() {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiHouse = retrofit.create(ApiHouse.class);
        apiPlant = retrofit.create(ApiPlant.class);
    }

    public static ApiServiceManager getInstance() {
        return ApiServiceManagerHolder.INSTANCE;
    }

    public ApiHouse getApiHouse() {
        return apiHouse;
    }

    public ApiPlant getApiPlant() {
        return apiPlant;
    }
}
