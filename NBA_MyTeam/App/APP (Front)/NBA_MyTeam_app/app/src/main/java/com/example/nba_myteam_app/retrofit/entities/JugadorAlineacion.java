package com.example.nba_myteam_app.retrofit.entities;

import com.google.gson.annotations.SerializedName;

public class JugadorAlineacion {

    //Atributos
    @SerializedName("IdJugador")
    private int idJugador;
    @SerializedName("IdAlineacion")
    private int idAlineacion;

    //Constructor con parámetros
    public JugadorAlineacion(int idJugador, int idAlineacion) {
        if(idJugador > 0) { this.idJugador = idJugador; } else { this.idJugador = 1; }
        if(idAlineacion > 0) { this.idAlineacion = idAlineacion; } else { this.idAlineacion = 1; }
    }

    //Getters y setters
    public int getIdJugador() {
        return idJugador;
    }
    public void setIdJugador(int idJugador) {
        if(idJugador > 0) { this.idJugador = idJugador; } else { this.idJugador = 1; }
    }

    public int getIdAlineacion() {
        return idAlineacion;
    }
    public void setIdAlineacion(int idAlineacion) {
        if(idAlineacion > 0) { this.idAlineacion = idAlineacion; } else { this.idAlineacion = 1; }
    }

}
