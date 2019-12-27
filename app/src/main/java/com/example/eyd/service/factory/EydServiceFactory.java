package com.example.eyd.service.factory;

import com.example.eyd.service.EydService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class EydServiceFactory {
    private static EydService eydService = null;
    private static final String BASE_URL = "https://sismul-tugas.herokuapp.com";

    public static EydService getInstance(){
        return (eydService == null) ?
                new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                        .create(EydService.class) : eydService;
    }
}
