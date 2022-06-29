package com.example.nba_myteam_app.repositories;

import com.example.nba_myteam_app.adapters.LocalDateAdapter;
import com.example.nba_myteam_app.adapters.LocalDateTimeAdapter;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRep {

    //Atributos
    private static final String BASE_URL = "https://nbamyteamapi.azurewebsites.net/";
    private static Retrofit retrofit;

    //Getters y setters
    public static Retrofit getRetrofit() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create()))
                    .build();
        }
        return retrofit;
    }

}
