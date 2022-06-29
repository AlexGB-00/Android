package com.example.nba_myteam_app.retrofit.interfaces;

import com.example.nba_myteam_app.retrofit.entities.Jugador;
import com.example.nba_myteam_app.retrofit.entities.JugadorAlineacion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface JugadorAlineacionInterface {

    //Listado de jugadores de una determinada alineación
    @GET("api/JugadoresAlineaciones")
    Call<List<Jugador>> getListadoJugadoresAlineacion(@Query("idAlineacion") int idAlineacion);

    //Insertar jugador en una alineación
    @POST("api/JugadoresAlineaciones")
    Call<Integer> insertJugadorAlineacion(@Body JugadorAlineacion jugadorAlineacion);

    //Actualizar jugador en una alineación
    @PUT("api/JugadoresAlineaciones")
    Call<Integer> updateJugadorAlineacion(@Body JugadorAlineacion jugadorAlineacion, @Query("idJugadorSustituto") int idJugadorSustituto);

    //Eliminar jugador de una alineación
    @DELETE("api/JugadoresAlineaciones")
    Call<Integer> deleteJugadorAlineacion(@Query("idJugador") int idJugador, @Query("idAlineacion") int idAlineacion);

}
