package com.example.nba_myteam_app.view_models;

import androidx.lifecycle.MutableLiveData;

import com.example.nba_myteam_app.repositories.JugadorClubRep;
import com.example.nba_myteam_app.retrofit.entities.Jugador;

import java.util.List;

public class ClubVM extends SesionVM {

    //Atributos
    private JugadorClubRep jugadorClubRep;
    private static MutableLiveData<List<Jugador>> listadoJugadores;
    private MutableLiveData<Boolean> btnNavSobresSeleccionado;

    //Constructor
    public ClubVM() {
        jugadorClubRep = new JugadorClubRep();
        listadoJugadores = new MutableLiveData<>();
        btnNavSobresSeleccionado = new MutableLiveData<>();
    }

    //Getters y setters
    public JugadorClubRep getJugadorClubRep() {
        return jugadorClubRep;
    }
    public void setJugadorClubRep(JugadorClubRep jugadorClubRep) {
        this.jugadorClubRep = jugadorClubRep;
    }

    public MutableLiveData<Boolean> getBtnNavSobresSeleccionado() {
        if(btnNavSobresSeleccionado == null) {
            btnNavSobresSeleccionado = new MutableLiveData<>();
        }
        return btnNavSobresSeleccionado;
    }
    public void setBtnNavSobresSeleccionado(MutableLiveData<Boolean> btnNavSobresSeleccionado) {
        this.btnNavSobresSeleccionado = btnNavSobresSeleccionado;
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

    //Métodos
    public void getListadoJugadoresClubUsuarioVM(String nickUsuario) {
        jugadorClubRep.getListadoJugadoresClubUsuarioRep(nickUsuario);
    }

}
