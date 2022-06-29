package com.example.nba_myteam_app.retrofit.interfaces;

import com.example.nba_myteam_app.retrofit.entities.Jugador;
import com.example.nba_myteam_app.retrofit.entities.JugadorClub;
import com.example.nba_myteam_app.retrofit.enums.EnumPosiciones;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JugadorClubInterface {

    //Listado de jugadores de un determinado usuario
    @GET("api/JugadoresClubs")
    Call<List<Jugador>> getListadoJugadoresClubUsuario(@Query("nickUsuario") String nickUsuario);

    //Listado de jugadores de un determinado usuario y posición
    @GET("api/JugadoresClubs")
    Call<List<Jugador>> getListadoJugadoresClubUsuarioPorPosicion(@Query("nickUsuario") String nickUsuario, @Query("posicion") EnumPosiciones posicion);

    //Listado de jugadores de un determinado equipo
    @GET("api/JugadoresClubs")
    Call<List<Jugador>> getListadoJugadoresEquipo(@Query("idEquipo") int idEquipo);

    //Insertar jugador en el club de un determinado usuario
    @POST("api/JugadoresClubs")
    Call<Integer> insertJugadorClub(@Body JugadorClub jugadorClub);

    //Eliminar jugador del club de un determinado usuario
    @DELETE("api/JugadoresClubs")
    Call<Integer> deleteJugadorClub(@Query("idJugador") int idJugador, @Query("nickUsuario") String nickUsuario);

}
