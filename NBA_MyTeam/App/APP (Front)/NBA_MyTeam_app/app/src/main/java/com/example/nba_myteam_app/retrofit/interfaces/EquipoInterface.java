package com.example.nba_myteam_app.retrofit.interfaces;

import com.example.nba_myteam_app.retrofit.entities.Equipo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EquipoInterface {

    //Equipo a partir de su id
    @GET("api/Equipos/{id}")
    Call<Equipo> getEquipo(@Path("id") int id);

}
