package com.example.nba_myteam_app.retrofit.entities;

import com.example.nba_myteam_app.retrofit.enums.EnumPosiciones;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;

public class Jugador implements Serializable {

    //Atributos
    @SerializedName("Id")
    private int id;
    @SerializedName("Nombre")
    private String nombre;
    @SerializedName("Edad")
    private byte edad;
    @SerializedName("Nacionalidad")
    private String nacionalidad;
    @SerializedName("Altura")
    private BigDecimal altura;
    @SerializedName("Peso")
    private short peso;
    @SerializedName("Dorsal")
    private byte dorsal;
    @SerializedName("Posicion")
    private EnumPosiciones posicion;
    @SerializedName("ValoracionGeneral")
    private byte valoracionGeneral;
    @SerializedName("Imagen")
    private String imagen;
    @SerializedName("IdEquipo")
    private int idEquipo;

    //Constructor con parámetros
    public Jugador(String nombre, byte edad, String nacionalidad, BigDecimal altura, short peso, byte dorsal, EnumPosiciones posicion, byte valoracionGeneral, String imagen, int idEquipo) {
        if(nombre != null && !nombre.isEmpty() && nombre.length() <= 40) { this.nombre = nombre; } else { this.nombre = "default"; }
        if(edad >= 19) { this.edad = edad; } else { this.edad = 19; }
        if(nacionalidad == null || nacionalidad.isEmpty()) { this.nacionalidad = nacionalidad; } else if(nacionalidad.length() <= 30) { this.nacionalidad = nacionalidad; }
        if(altura.compareTo(new BigDecimal(1.50)) > 0) { this.altura = altura; } else { this.altura = new BigDecimal(1.50); }
        if(peso >= 50) { this.peso = peso; } else { this.peso = 50; }
        if(dorsal >= 0 && dorsal <= 99) { this.dorsal = dorsal; } else { this.dorsal = 0; }
        this.posicion = posicion;
        if(valoracionGeneral >= 70 && valoracionGeneral <= 99) { this.valoracionGeneral = valoracionGeneral; } else { this.valoracionGeneral = 70; }
        if(imagen != null && !imagen.isEmpty() && imagen.length() <= 150) { this.imagen = imagen; } else { this.imagen = "default"; }
        if(idEquipo > 0) { this.idEquipo = idEquipo; } else { this.idEquipo = 1; }
    }

    //Getters y setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        if(id > 0) { this.id = id; }
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        if(nombre != null && !nombre.isEmpty() && nombre.length() <= 40) { this.nombre = nombre; } else { this.nombre = "default"; }
    }

    public int getEdad() {
        return edad;
    }
    public void setEdad(byte edad) {
        if(edad >= 19) { this.edad = edad; } else { this.edad = 19; }
    }

    public String getNacionalidad() {
        return nacionalidad;
    }
    public void setNacionalidad(String nacionalidad) {
        if(nacionalidad == null || nacionalidad.isEmpty()) { this.nacionalidad = nacionalidad; } else if(nacionalidad.length() <= 30) { this.nacionalidad = nacionalidad; }
    }

    public BigDecimal getAltura() {
        return altura;
    }
    public void setAltura(BigDecimal altura) {
        if(altura.compareTo(new BigDecimal(1.50)) > 0) { this.altura = altura; } else { this.altura = new BigDecimal(1.50); }
    }

    public int getPeso() {
        return peso;
    }
    public void setPeso(short peso) {
        if(peso >= 50) { this.peso = peso; } else { this.peso = 50; }
    }

    public int getDorsal() {
        return dorsal;
    }
    public void setDorsal(byte dorsal) {
        if(dorsal >= 0 && dorsal <= 99) { this.dorsal = dorsal; } else { this.dorsal = 0; }
    }

    public EnumPosiciones getPosicion() {
        return posicion;
    }
    public void setPosicion(EnumPosiciones posicion) {
        this.posicion = posicion;
    }

    public int getValoracionGeneral() {
        return valoracionGeneral;
    }
    public void setValoracionGeneral(byte valoracionGeneral) {
        if(valoracionGeneral >= 70 && valoracionGeneral <= 99) { this.valoracionGeneral = valoracionGeneral; } else { this.valoracionGeneral = 70; }
    }

    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        if(imagen != null && !imagen.isEmpty() && imagen.length() <= 150) { this.imagen = imagen; } else { this.imagen = "default"; }
    }

    public int getIdEquipo() {
        return idEquipo;
    }
    public void setIdEquipo(int idEquipo) {
        if(idEquipo > 0) { this.idEquipo = idEquipo; } else { this.idEquipo = 1; }
    }

}

