package com.example.nba_myteam_app.retrofit.entities;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class JugadorClub {

    //Atributos
    @SerializedName("IdJugador")
    private int idJugador;
    @SerializedName("NickUsuario")
    private String nickUsuario;
    @SerializedName("FechaIncorporacion")
    private LocalDateTime fechaIncorporacion;

    //Constructor con parámetros
    public JugadorClub(int idJugador, String nickUsuario, LocalDateTime fechaIncorporacion) {
        if(idJugador > 0) { this.idJugador = idJugador; } else { this.idJugador = 1; }
        if(nickUsuario != null && !nickUsuario.isEmpty() && nickUsuario.length() <= 20) { this.nickUsuario = nickUsuario; } else { this.nickUsuario = "default"; }
        if(fechaIncorporacion.compareTo(LocalDateTime.now()) <= 0) { this.fechaIncorporacion = fechaIncorporacion; } else { this.fechaIncorporacion = LocalDateTime.now(); }
    }

    //Getters y setters
    public int getIdJugador() {
        return idJugador;
    }
    public void setIdJugador(int idJugador) {
        if(idJugador > 0) { this.idJugador = idJugador; } else { this.idJugador = 1; }
    }

    public String getNickUsuario() {
        return nickUsuario;
    }
    public void setNickUsuario(String nickUsuario) {
        if(nickUsuario != null && !nickUsuario.isEmpty() && nickUsuario.length() <= 20) { this.nickUsuario = nickUsuario; } else { this.nickUsuario = "default"; }
    }

    public LocalDateTime getFechaIncorporacion() {
        return fechaIncorporacion;
    }
    public void setFechaIncorporacion(LocalDateTime fechaIncorporacion) {
        if(fechaIncorporacion.compareTo(LocalDateTime.now()) <= 0) { this.fechaIncorporacion = fechaIncorporacion; } else { this.fechaIncorporacion = LocalDateTime.now(); }
    }

}
