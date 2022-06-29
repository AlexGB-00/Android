package com.example.nba_myteam_app.view_models;

import androidx.lifecycle.MutableLiveData;

import com.example.nba_myteam_app.repositories.AlineacionRep;
import com.example.nba_myteam_app.retrofit.entities.Alineacion;

import java.util.List;

public class AlineacionesVM extends SesionVM {

    //Atributos
    private AlineacionRep alineacionRep;
    private static MutableLiveData<List<Alineacion>> listadoAlineaciones;
    private static MutableLiveData<Integer> filasAfectadas;

    //Constructor
    public AlineacionesVM() {
        alineacionRep = new AlineacionRep();
        listadoAlineaciones = new MutableLiveData<>();
        filasAfectadas = new MutableLiveData<>();
    }

    //Getters y setters
    public AlineacionRep getAlineacionRep() {
        return alineacionRep;
    }
    public void setAlineacionRep(AlineacionRep alineacionRep) {
        this.alineacionRep = alineacionRep;
    }

    public static MutableLiveData<List<Alineacion>> getListadoAlineaciones() {
        return listadoAlineaciones;
    }
    public static void setListadoAlineaciones(List<Alineacion> nuevoListadoAlineaciones) {
        listadoAlineaciones.setValue(nuevoListadoAlineaciones);
    }

    public static MutableLiveData<Integer> getFilasAfectadas() {
        return filasAfectadas;
    }
    public static void setFilasAfectadas(int newFilasAfectadas) {
        filasAfectadas.setValue(newFilasAfectadas);
    }

    //Métodos
    public void getListadoAlineacionesUsuarioVM(String nickUsuario) {
        alineacionRep.getListadoAlineacionesUsuarioRep(nickUsuario);
    }

    public void insertAlineacionVM(Alineacion alineacion) {
        alineacionRep.insertAlineacionRep(alineacion);
    }

}
