package com.example.nba_myteam_app.repositories;

import com.example.nba_myteam_app.retrofit.callbacks.ListadoCallback;
import com.example.nba_myteam_app.retrofit.callbacks.ModifCallback;
import com.example.nba_myteam_app.retrofit.entities.Alineacion;
import com.example.nba_myteam_app.retrofit.entities.Jugador;
import com.example.nba_myteam_app.retrofit.entities.JugadorAlineacion;
import com.example.nba_myteam_app.retrofit.interfaces.JugadorAlineacionInterface;
import com.example.nba_myteam_app.view_models.DetalleAlineacionVM;
import com.example.nba_myteam_app.view_models.SobresVM;

import java.util.List;

public class JugadorAlineacionRep extends BaseRep {

    //Atributos
    private JugadorAlineacionInterface jugadorAlineacionInterface;
    private ModifCallback modifCallback;

    //Constructor
    public JugadorAlineacionRep() {
        jugadorAlineacionInterface = getRetrofit().create(JugadorAlineacionInterface.class);
        modifCallback = new ModifCallback();
    }

    //Métodos
    //Método encargado de enviar una petición para obtener un listado de jugadores pertenecientes a una determinada alineación
    public void getListadoJugadoresAlineacionRep(int idAlineacion) {
        ListadoCallback<Jugador> listadoCallback = new ListadoCallback<>();
        jugadorAlineacionInterface.getListadoJugadoresAlineacion(idAlineacion).enqueue(listadoCallback);
    }
    //Método encargado de recibir la respuesta a la petición getListadoJugadoresAlineacion
    public void setListadoJugadoresAlineacionRep(List<Jugador> listadoJugadores) {
        DetalleAlineacionVM.setListadoJugadoresAlineacion(listadoJugadores);
    }

    //Método encargado de enviar una petición para insertar un determinado jugador en una determinada alineación
    public void insertJugadorAlineacionRep(JugadorAlineacion jugadorAlineacion) {
        jugadorAlineacionInterface.insertJugadorAlineacion(jugadorAlineacion).enqueue(modifCallback);
    }

    //Método encargado de enviar una petición para sustituir un determinado jugador por otro en una determinada alineación
    public void updateJugadorAlineacionRep(JugadorAlineacion jugadorAlineacion, int idJugadorSustituto) {
        jugadorAlineacionInterface.updateJugadorAlineacion(jugadorAlineacion, idJugadorSustituto).enqueue(modifCallback);
    }

    //Método encargado de enviar una petición para eliminar un determinado jugador de una determinada alineación
    public void deleteJugadorAlineacionRep(int idJugador, int idAlineacion) {
        jugadorAlineacionInterface.deleteJugadorAlineacion(idJugador, idAlineacion).enqueue(modifCallback);
    }

    //Método encargado de recibir la respuesta a la petición insert/update/deleteJugadorAlineacion
    public void setFilasAfectadas(int filasAfectadas) {
        DetalleAlineacionVM.setFilasAfectadas(filasAfectadas);
    }

}
