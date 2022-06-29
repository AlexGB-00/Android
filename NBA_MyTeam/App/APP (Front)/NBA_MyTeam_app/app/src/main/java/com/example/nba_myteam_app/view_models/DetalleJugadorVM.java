package com.example.nba_myteam_app.view_models;

import androidx.lifecycle.MutableLiveData;

import com.example.nba_myteam_app.repositories.EquipoRep;
import com.example.nba_myteam_app.repositories.JugadorClubRep;
import com.example.nba_myteam_app.retrofit.entities.Equipo;

public class DetalleJugadorVM extends SesionVM {

    //Atributos
    private EquipoRep equipoRep;
    private JugadorClubRep jugadorClubRep;
    private static MutableLiveData<Equipo> equipoJugador;

    //Constructor
    public DetalleJugadorVM() {
        equipoRep = new EquipoRep();
        jugadorClubRep = new JugadorClubRep();
        equipoJugador = new MutableLiveData<>();
    }

    //Getters y setters
    public EquipoRep getEquipoRep() {
        return equipoRep;
    }
    public void setEquipoRep(EquipoRep equipoRep) {
        this.equipoRep = equipoRep;
    }

    public JugadorClubRep getJugadorClubRep() {
        return jugadorClubRep;
    }
    public void setJugadorClubRep(JugadorClubRep jugadorClubRep) {
        this.jugadorClubRep = jugadorClubRep;
    }

    public static MutableLiveData<Equipo> getEquipoJugador() {
        if(equipoJugador == null) {
            equipoJugador = new MutableLiveData<>();
        }
        return equipoJugador;
    }
    public static void setEquipoJugador(Equipo equipo) {
        equipoJugador.setValue(equipo);
    }

    //Métodos
    public void getEquipoVM(int id) {
        equipoRep.getEquipoRep(id);
    }

}
