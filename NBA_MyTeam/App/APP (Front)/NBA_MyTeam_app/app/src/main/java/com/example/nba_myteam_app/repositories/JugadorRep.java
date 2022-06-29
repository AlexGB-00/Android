package com.example.nba_myteam_app.repositories;

import androidx.lifecycle.ViewModelProvider;

import com.example.nba_myteam_app.retrofit.callbacks.EntidadCallback;
import com.example.nba_myteam_app.retrofit.callbacks.ListadoCallback;
import com.example.nba_myteam_app.retrofit.entities.Jugador;
import com.example.nba_myteam_app.retrofit.enums.EnumPosiciones;
import com.example.nba_myteam_app.retrofit.interfaces.JugadorInterface;
import com.example.nba_myteam_app.view_models.SobresVM;

import java.util.List;

public class JugadorRep extends BaseRep {

    //Atributos
    private ListadoCallback<Jugador> listadoCallback;
    private JugadorInterface jugadorInterface;

    //Constructor
    public JugadorRep() {
        listadoCallback = new ListadoCallback<>();
        jugadorInterface = getRetrofit().create(JugadorInterface.class);
    }

    //Métodos
    //Método encargado de enviar una petición para obtener un listado de jugadores cuya valoración se encuentre en el rango establecido por los parámetros
    public void getListadoJugadoresValoracionRep(int valoracionMin, int valoracionMax) {
        jugadorInterface.getListadoJugadoresValoracion(valoracionMin, valoracionMax).enqueue(listadoCallback);
    }
    //Método encargado de recibir la respuesta a la petición getListadoJugadoresValoracion
    public void setListadoJugadoresValoracionRep(List<Jugador> listadoJugadores) {
        SobresVM.setListadoJugadores(listadoJugadores);
    }

}
