package com.example.nba_myteam_app.repositories;

import com.example.nba_myteam_app.retrofit.callbacks.EntidadCallback;
import com.example.nba_myteam_app.retrofit.entities.Equipo;
import com.example.nba_myteam_app.retrofit.interfaces.EquipoInterface;
import com.example.nba_myteam_app.view_models.DetalleJugadorVM;

public class EquipoRep extends BaseRep {

    //Atributos
    private EquipoInterface equipoInterface;

    //Constructor
    public EquipoRep() {
        equipoInterface = getRetrofit().create(EquipoInterface.class);
    }

    //Métodos
    //Método encargado de enviar una petición para obtener un determinado equipo a partir de su id
    public void getEquipoRep(int id) {
        EntidadCallback<Equipo> entidadCallback = new EntidadCallback<>();
        equipoInterface.getEquipo(id).enqueue(entidadCallback);
    }
    //Método encargado de recibir la respuesta a la petición getEquipo
    public void setEquipoRep(Equipo equipo) {
        DetalleJugadorVM.setEquipoJugador(equipo);
    }

}
