package com.example.nba_myteam_app.retrofit.entities;

import com.google.gson.annotations.SerializedName;

public class Valoracion {

    //Atributos
    @SerializedName("NickUsuario")
    private String nickUsuario;
    @SerializedName("IdAlineacion")
    private int idAlineacion;
    @SerializedName("Rating")
    private byte rating;
    @SerializedName("Descripcion")
    private String descripcion;

    //Constructor con parámetros
    public Valoracion(String nickUsuario, int idAlineacion, byte rating, String descripcion) {
        if(nickUsuario != null && !nickUsuario.isEmpty() && nickUsuario.length() <= 20) { this.nickUsuario = nickUsuario; } else { this.nickUsuario = "default"; }
        if(idAlineacion > 0) { this.idAlineacion = idAlineacion; } else { this.idAlineacion = 1; }
        if(rating >= 0 && rating <= 10) { this.rating = rating; } else { this.rating = 0; }
        if(descripcion == null || descripcion.isEmpty()) { this.descripcion = descripcion; } else if(descripcion.length() <= 250) { this.descripcion = descripcion; }
    }

    //Getters y setters
    public String getNickUsuario() {
        return nickUsuario;
    }
    public void setNickUsuario(String nickUsuario) {
        if(nickUsuario != null && !nickUsuario.isEmpty() && nickUsuario.length() <= 20) { this.nickUsuario = nickUsuario; } else { this.nickUsuario = "default"; }
    }

    public int getIdAlineacion() {
        return idAlineacion;
    }
    public void setIdAlineacion(int idAlineacion) {
        if(idAlineacion > 0) { this.idAlineacion = idAlineacion; } else { this.idAlineacion = 1; }
    }

    public int getRating() {
        return rating;
    }
    public void setRating(byte rating) {
        if(rating >= 0 && rating <= 10) { this.rating = rating; } else { this.rating = 0; }
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        if(descripcion == null || descripcion.isEmpty()) { this.descripcion = descripcion; } else if(descripcion.length() <= 250) { this.descripcion = descripcion; }
    }

}
