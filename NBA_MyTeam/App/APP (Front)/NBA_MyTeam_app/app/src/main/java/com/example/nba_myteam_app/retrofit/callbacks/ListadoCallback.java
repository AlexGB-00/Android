package com.example.nba_myteam_app.retrofit.callbacks;

import androidx.lifecycle.LiveData;

import com.example.nba_myteam_app.repositories.AlineacionRep;
import com.example.nba_myteam_app.repositories.JugadorAlineacionRep;
import com.example.nba_myteam_app.repositories.JugadorClubRep;
import com.example.nba_myteam_app.repositories.JugadorRep;
import com.example.nba_myteam_app.repositories.ValoracionRep;
import com.example.nba_myteam_app.retrofit.entities.Alineacion;
import com.example.nba_myteam_app.retrofit.entities.Jugador;
import com.example.nba_myteam_app.retrofit.entities.Valoracion;
import com.example.nba_myteam_app.view_models.SobresVM;

import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Esta clase representa el Callback que gestiona las respuestas de la API que devuelven un listado de objetos.

public class ListadoCallback<T> implements Callback<List<T>> {

    @Override
    public void onResponse(Call<List<T>> call, Response<List<T>> response) {
        List<T> listado = response.body();
        String ctller = call.request().url().pathSegments().get(1);
        if(response.isSuccessful()) {
            switch (ctller) {
                case "Alineaciones":
                    new AlineacionRep().setListadoAlineacionesUsuarioRep((List<Alineacion>) listado);
                    break;
                case "JugadoresAlineaciones":
                    new JugadorAlineacionRep().setListadoJugadoresAlineacionRep((List<Jugador>) listado);
                    break;
                case "JugadoresClubs":
                    JugadorClubRep jugadorClubRep = new JugadorClubRep();
                    String sndParam = call.request().url().queryParameter("posicion");
                    if(sndParam == null) {
                        jugadorClubRep.setListadoJugadoresClubUsuarioRep((List<Jugador>) listado);
                    } else {
                        jugadorClubRep.setListadoJugadoresClubUsuarioPorPosicionRep((List<Jugador>) listado);
                    }
                    break;
                case "Jugadores":
                    JugadorRep jugadorRep = new JugadorRep();
                    List<Jugador> listadoJugadores = (List<Jugador>) listado;
                    jugadorRep.setListadoJugadoresValoracionRep(listadoJugadores);
                    break;
            }
        }
    }

    @Override
    public void onFailure(Call<List<T>> call, Throwable t) {
        String mensaje = "Ha ocurrido un error al realizar la petición";
    }

}
