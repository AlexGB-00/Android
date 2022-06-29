package com.example.nba_myteam_app.retrofit.entities;

import com.example.nba_myteam_app.retrofit.enums.EnumConferencias;
import com.google.gson.annotations.SerializedName;
import java.util.Calendar;

public class Equipo {

    //Atributos
    @SerializedName("Id")
    private int id;
    @SerializedName("Nombre")
    private String nombre;
    @SerializedName("Conferencia")
    private EnumConferencias conferencia;
    @SerializedName("Ciudad")
    private String ciudad;
    @SerializedName("AnhoFundacion")
    private short anhoFundacion;
    @SerializedName("NumeroTitulos")
    private byte numeroTitulos;
    @SerializedName("Estadio")
    private String estadio;
    @SerializedName("NombreEntrenador")
    private String nombreEntrenador;
    @SerializedName("Imagen")
    private String imagen;

    //Constructor con parámetros
    public Equipo(String nombre, EnumConferencias conferencia, String ciudad, short anhoFundacion, byte numeroTitulos, String estadio, String nombreEntrenador, String imagen) {
        if(nombre != null && !nombre.isEmpty() && nombre.length() <= 40) { this.nombre = nombre; } else { this.nombre = "default"; }
        this.conferencia = conferencia;
        if(ciudad == null || ciudad.isEmpty()) { this.ciudad = ciudad; } else if(ciudad.length() <= 30) { this.ciudad = ciudad; }
        if(anhoFundacion >= 1946 && anhoFundacion <= Calendar.getInstance().get(Calendar.YEAR)) { this.anhoFundacion = anhoFundacion; } else { this.anhoFundacion = 1946; }
        if(numeroTitulos >= 0) { this.numeroTitulos = numeroTitulos; } else { this.numeroTitulos = 0; }
        if(estadio == null || estadio.isEmpty()) { this.estadio = estadio; } else if(estadio.length() <= 30) { this.estadio = estadio; }
        if(nombreEntrenador == null || nombreEntrenador.isEmpty()) { this.nombreEntrenador = nombreEntrenador; } else if(nombreEntrenador.length() <= 25) { this.nombreEntrenador = nombreEntrenador; }
        if(imagen != null && !imagen.isEmpty() && imagen.length() <= 150) { this.imagen = imagen; } else { this.imagen = "default"; }
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
        if(nombre != null && !nombre.isEmpty() && nombre.length() <= 40) { this.nombre = nombre; } else { this.nombre = "default"; }
    }

    public EnumConferencias getConferencia() {
        return conferencia;
    }
    public void setConferencia(EnumConferencias conferencia) {
        this.conferencia = conferencia;
    }

    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        if(ciudad == null || ciudad.isEmpty()) { this.ciudad = ciudad; } else if(ciudad.length() <= 30) { this.ciudad = ciudad; }
    }

    public int getAnhoFundacion() {
        return anhoFundacion;
    }
    public void setAnhoFundacion(short anhoFundacion) {
        if(anhoFundacion >= 1946 && anhoFundacion <= Calendar.getInstance().get(Calendar.YEAR)) { this.anhoFundacion = anhoFundacion; } else { this.anhoFundacion = 1946; }
    }

    public int getNumeroTitulos() {
        return numeroTitulos;
    }
    public void setNumeroTitulos(byte numeroTitulos) {
        if(numeroTitulos >= 0) { this.numeroTitulos = numeroTitulos; } else { this.numeroTitulos = 0; }
    }

    public String getEstadio() {
        return estadio;
    }
    public void setEstadio(String estadio) {
        if(estadio == null || estadio.isEmpty()) { this.estadio = estadio; } else if(estadio.length() <= 30) { this.estadio = estadio; }
    }

    public String getNombreEntrenador() {
        return nombreEntrenador;
    }
    public void setNombreEntrenador(String nombreEntrenador) {
        if(nombreEntrenador == null || nombreEntrenador.isEmpty()) { this.nombreEntrenador = nombreEntrenador; } else if(nombreEntrenador.length() <= 25) { this.nombreEntrenador = nombreEntrenador; }
    }

    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        if(imagen != null && !imagen.isEmpty() && imagen.length() <= 150) { this.imagen = imagen; } else { this.imagen = "default"; }
    }

}
