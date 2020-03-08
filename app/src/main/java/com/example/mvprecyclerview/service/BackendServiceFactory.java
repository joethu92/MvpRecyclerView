package com.example.mvprecyclerview.service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BackendServiceFactory {
    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";

    public static BackendService getBackEndService() {
        return new Retrofit.Builder()
                .client(new OkHttpClient.Builder()
                        .readTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BackendService.class);
    }

}