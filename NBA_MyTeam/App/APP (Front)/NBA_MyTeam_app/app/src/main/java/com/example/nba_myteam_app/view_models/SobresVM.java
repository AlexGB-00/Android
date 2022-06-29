package com.example.nba_myteam_app.view_models;

import androidx.lifecycle.MutableLiveData;

import com.example.nba_myteam_app.repositories.JugadorClubRep;
import com.example.nba_myteam_app.repositories.JugadorRep;
import com.example.nba_myteam_app.repositories.UsuarioRep;
import com.example.nba_myteam_app.retrofit.entities.Jugador;
import com.example.nba_myteam_app.retrofit.entities.JugadorClub;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SobresVM extends SesionVM {

    //Atributos
    private JugadorRep jugadorRep;
    private JugadorClubRep jugadorClubRep;
    private UsuarioRep usuarioRep;
    private static MutableLiveData<List<Jugador>> listadoJugadores;
    private static MutableLiveData<Integer> filasAfectadas;
    private static MutableLiveData<LocalDateTime> ultimafechaIncorporacion;

    //Constructor
    public SobresVM() {
        jugadorRep = new JugadorRep();
        jugadorClubRep = new JugadorClubRep();
        usuarioRep = new UsuarioRep();
        listadoJugadores = new MutableLiveData<>();
        filasAfectadas = new MutableLiveData<>();
        ultimafechaIncorporacion = new MutableLiveData<>();
    }

    //Getters y setters
    public JugadorRep getJugadorRep() {
        return jugadorRep;
    }
    public void setJugadorRep(JugadorRep jugadorRep) {
        this.jugadorRep = jugadorRep;
    }

    public JugadorClubRep getJugadorClubRep() {
        return jugadorClubRep;
    }
    public void setJugadorClubRep(JugadorClubRep jugadorClubRep) {
        this.jugadorClubRep = jugadorClubRep;
    }

    public UsuarioRep getUsuarioRep() {
        return usuarioRep;
    }
    public void setUsuarioRep(UsuarioRep usuarioRep) {
        this.usuarioRep = usuarioRep;
    }

    public static MutableLiveData<List<Jugador>> getListadoJugadores() {
        if(listadoJugadores == null) {
            listadoJugadores = new MutableLiveData<>();
        }
        return listadoJugadores;
    }
    public static void setListadoJugadores(List<Jugador> nuevoListadoJugadores) {
        listadoJugadores.setValue(nuevoListadoJugadores);
    }

    public static MutableLiveData<Integer> getFilasAfectadas() {
        if(filasAfectadas == null) {
            filasAfectadas = new MutableLiveData<>();
        }
        return filasAfectadas;
    }
    public static void setFilasAfectadas(int newfilasAfectadas) {
        filasAfectadas.setValue(new Integer(newfilasAfectadas));
    }

    public static MutableLiveData<LocalDateTime> getUltimafechaIncorporacion() {
        if(ultimafechaIncorporacion == null) {
            ultimafechaIncorporacion = new MutableLiveData<>();
        }
        return ultimafechaIncorporacion;
    }
    public static void setUltimafechaIncorporacion(LocalDateTime nuevaUltimafechaIncorporacion) {
        ultimafechaIncorporacion.setValue(nuevaUltimafechaIncorporacion);
    }

    //Métodos
    public void getListadoJugadoresValoracionVM(int valoracionMin, int valoracionMax) {
        jugadorRep.getListadoJugadoresValoracionRep(valoracionMin, valoracionMax);
    }

    public void getUltimaFechaIncorporacionUsuarioVM(String nickUsuario) {
        usuarioRep.getUltimaFechaIncorporacionUsuarioRep(nickUsuario);
    }

    public void insertJugadorClubVM(JugadorClub jugadorClub) {
        jugadorClubRep.insertJugadorClubRep(jugadorClub);
    }

}
