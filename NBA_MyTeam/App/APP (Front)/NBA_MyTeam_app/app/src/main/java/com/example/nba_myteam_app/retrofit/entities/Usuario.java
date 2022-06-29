package com.example.nba_myteam_app.retrofit.entities;

import com.google.gson.annotations.SerializedName;

public class Usuario {

    //Atributos
    @SerializedName("Nick")
    private String nick;
    @SerializedName("CorreoElectronico")
    private String correoElectronico;
    @SerializedName("Contrasenha")
    private String contrasenha;
    @SerializedName("NombreCompleto")
    private String nombreCompleto;
    @SerializedName("Imagen")
    private String imagen;

    //Constructor con parámetros
    public Usuario(String nick, String correoElectronico, String contrasenha, String nombreCompleto, String imagen) {
        if(nick != null && !nick.isEmpty() && nick.length() >= 3 && nick.length() <= 20) { this.nick = nick; } else { this.nick = "default"; }
        if(correoElectronico != null && !correoElectronico.isEmpty() && correoElectronico.length() <= 45) { this.correoElectronico = correoElectronico; } else { this.correoElectronico = "default@gmail.com"; }
        if(contrasenha != null && !contrasenha.isEmpty() && contrasenha.length() >= 8 && contrasenha.length() <= 32) { this.contrasenha = contrasenha; } else { this.contrasenha = "Default123"; }
        if(nombreCompleto == null || nombreCompleto.isEmpty()) { this.nombreCompleto = nombreCompleto; } else if(nombreCompleto.length() <= 40) { this.nombreCompleto = nombreCompleto; }
        if(imagen == null || imagen.isEmpty()) { this.imagen = imagen; } else if(imagen.length() <= 150) { this.imagen = imagen; }
    }

    //Getters y setters
    public String getNick() {
        return nick;
    }
    public void setNick(String nick) {
        if(nick != null && !nick.isEmpty() && nick.length() >= 3 && nick.length() <= 20) { this.nick = nick; } else { this.nick = "default"; }
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }
    public void setCorreoElectronico(String correoElectronico) {
        if(correoElectronico != null && !correoElectronico.isEmpty() && correoElectronico.length() <= 45) { this.correoElectronico = correoElectronico; } else { this.correoElectronico = "default@gmail.com"; }
    }

    public String getContrasenha() {
        return contrasenha;
    }
    public void setContrasenha(String contrasenha) {
        if(contrasenha != null && !contrasenha.isEmpty() && contrasenha.length() >= 8 && contrasenha.length() <= 32) { this.contrasenha = contrasenha; } else { this.contrasenha = "Default123"; }
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        if(nombreCompleto == null || nombreCompleto.isEmpty()) { this.nombreCompleto = nombreCompleto; } else if(nombreCompleto.length() <= 40) { this.nombreCompleto = nombreCompleto; }
    }

    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        if(imagen == null || imagen.isEmpty()) { this.imagen = imagen; } else if(imagen.length() <= 150) { this.imagen = imagen; }
    }

}
