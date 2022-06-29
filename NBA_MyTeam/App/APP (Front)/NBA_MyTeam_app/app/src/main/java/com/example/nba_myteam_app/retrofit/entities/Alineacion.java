package com.example.nba_myteam_app.retrofit.entities;

import com.example.nba_myteam_app.retrofit.enums.EnumSistemas;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;

public class Alineacion implements Serializable {

    //Atributos
    @SerializedName("Id")
    private int id;
    @SerializedName("Nombre")
    private String nombre;
    @SerializedName("Sistema")
    private EnumSistemas sistema;
    @SerializedName("ValoracionMedia")
    private byte valoracionMedia;
    @SerializedName("FechaCreacion")
    private LocalDate fechaCreacion;
    @SerializedName("Finalizada")
    private boolean finalizada;
    @SerializedName("NickUsuario")
    private String nickUsuario;

    //Constructor con parámetros
    public Alineacion(int id, String nombre, EnumSistemas sistema, byte valoracionMedia, LocalDate fechaCreacion, boolean finalizada, String nickUsuario) {
        if(id > 0) { this.id = id; }
        if(nombre != null && !nombre.isEmpty() && nombre.length() <= 20) { this.nombre = nombre; } else { this.nombre = "default"; }
        this.sistema = sistema;
        if(valoracionMedia >= 0 && valoracionMedia <= 99) { this.valoracionMedia = valoracionMedia; } else { this.valoracionMedia = 0; }
        if(fechaCreacion.compareTo(LocalDate.now()) <= 0) { this.fechaCreacion = fechaCreacion; } else { this.fechaCreacion = LocalDate.now(); }
        this.finalizada = finalizada;
        if(nickUsuario != null && !nickUsuario.isEmpty() && nickUsuario.length() >= 3 && nickUsuario.length() <= 20) { this.nickUsuario = nickUsuario; } else { this.nickUsuario = "default"; }
    }

    public Alineacion(String nombre, EnumSistemas sistema, byte valoracionMedia, LocalDate fechaCreacion, boolean finalizada, String nickUsuario) {
        if(nombre != null && !nombre.isEmpty() && nombre.length() <= 20) { this.nombre = nombre; } else { this.nombre = "default"; }
        this.sistema = sistema;
        if(valoracionMedia >= 0 && valoracionMedia <= 99) { this.valoracionMedia = valoracionMedia; } else { this.valoracionMedia = 0; }
        if(fechaCreacion.compareTo(LocalDate.now()) <= 0) { this.fechaCreacion = fechaCreacion; } else { this.fechaCreacion = LocalDate.now(); }
        this.finalizada = finalizada;
        if(nickUsuario != null && !nickUsuario.isEmpty() && nickUsuario.length() >= 3 && nickUsuario.length() <= 20) { this.nickUsuario = nickUsuario; } else { this.nickUsuario = "default"; }
    }

    //Constructor de copia
    public Alineacion(Alineacion alineacion) {
        this.id = alineacion.getId();
        this.nombre = alineacion.getNombre();
        this.sistema = alineacion.getSistema();
        this.valoracionMedia = alineacion.getValoracionMedia();
        this.fechaCreacion = alineacion.getFechaCreacion();
        this.finalizada = alineacion.getFinalizada();
        this.nickUsuario = alineacion.getNickUsuario();
    }

    //Getters y setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        if (id > 0) { this.id = id; }
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        if(nombre != null && !nombre.isEmpty() && nombre.length() <= 20) { this.nombre = nombre; } else { this.nombre = "default"; }
    }

    public EnumSistemas getSistema() {
        return sistema;
    }
    public void setSistema(EnumSistemas sistema) {
        this.sistema = sistema;
    }

    public byte getValoracionMedia() {
        return valoracionMedia;
    }
    public void setValoracionMedia(byte valoracionMedia) {
        if(valoracionMedia >= 0 && valoracionMedia <= 99) { this.valoracionMedia = valoracionMedia; } else { this.valoracionMedia = 0; }
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(LocalDate fechaCreacion) {
        if(fechaCreacion.compareTo(LocalDate.now()) <= 0) { this.fechaCreacion = fechaCreacion; } else { this.fechaCreacion = LocalDate.now(); }
    }

    public boolean getFinalizada() {
        return finalizada;
    }
    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    public String getNickUsuario() {
        return nickUsuario;
    }
    public void setNickUsuario(String nickUsuario) {
        if(nickUsuario != null && !nickUsuario.isEmpty() && nickUsuario.length() >= 3 && nickUsuario.length() <= 20) { this.nickUsuario = nickUsuario; } else { this.nickUsuario = "default"; }
    }

}
