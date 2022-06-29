package com.example.nba_myteam_app.retrofit.interfaces;

import com.example.nba_myteam_app.retrofit.entities.Jugador;
import com.example.nba_myteam_app.retrofit.enums.EnumPosiciones;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JugadorInterface {

    //Listado de jugadores a partir de un nombre
    @GET("api/Jugadores")
    Call<List<Jugador>> getListadoJugadoresNombre(@Query("nombre") String nombre);

    //Listado de jugadores a partir de una posición
    @GET("api/Jugadores")
    Call<List<Jugador>> getListadoJugadoresPosicion(@Query("posicion") EnumPosiciones posicion);

    //Listado de jugadores de un rango de valoración
    @GET("api/Jugadores")
    Call<List<Jugador>> getListadoJugadoresValoracion(@Query("valoracionMinima") int valoracionMinima, @Query("valoracionMaxima") int valoracionMaxima);

    //Jugador a partir de su id
    @GET("api/Jugadores/{id}")
    Call<Jugador> getJugador(@Path("id") int id);

}
