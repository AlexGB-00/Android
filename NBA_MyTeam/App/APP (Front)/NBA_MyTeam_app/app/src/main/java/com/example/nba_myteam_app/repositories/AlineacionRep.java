package com.example.nba_myteam_app.repositories;

import com.example.nba_myteam_app.retrofit.callbacks.EntidadCallback;
import com.example.nba_myteam_app.retrofit.callbacks.ListadoCallback;
import com.example.nba_myteam_app.retrofit.callbacks.ModifCallback;
import com.example.nba_myteam_app.retrofit.entities.Alineacion;
import com.example.nba_myteam_app.retrofit.entities.Equipo;
import com.example.nba_myteam_app.retrofit.interfaces.AlineacionInterface;
import com.example.nba_myteam_app.view_models.AlineacionesVM;
import com.example.nba_myteam_app.view_models.DetalleAlineacionVM;
import com.example.nba_myteam_app.view_models.SobresVM;

import java.util.List;

public class AlineacionRep extends BaseRep {

    //Atributos
    private AlineacionInterface alineacionInterface;
    private ModifCallback modifCallback;

    //Constructor
    public AlineacionRep() {
        alineacionInterface = getRetrofit().create(AlineacionInterface.class);
        modifCallback = new ModifCallback();
    }

    //Métodos
    //Método encargado de enviar una petición para obtener el listado de alineaciones pertenecientes a un determinado usuario
    public void getListadoAlineacionesUsuarioRep(String nickUsuario) {
        ListadoCallback<Alineacion> listadoCallback = new ListadoCallback<>();
        alineacionInterface.getListadoAlineacionesUsuario(nickUsuario).enqueue(listadoCallback);
    }
    //Método encargado de recibir la respuesta a la petición getListadoAlineacionesUsuario
    public void setListadoAlineacionesUsuarioRep(List<Alineacion> listadoAlineaciones) {
        AlineacionesVM.setListadoAlineaciones(listadoAlineaciones);
    }

    //Método encargado de enviar una petición para obtener una determinada alineación a partir de su id
    public void getAlineacionRep(int id) {
        EntidadCallback<Alineacion> entidadCallback = new EntidadCallback<>();
        alineacionInterface.getAlineacion(id).enqueue(entidadCallback);
    }
    //Método encargado de recibir la respuesta a la petición getAlineacion
    public void setAlineacionRep(Alineacion alineacion) {
        DetalleAlineacionVM.setAlineacionActualizada(alineacion);
    }

    //Método encargado de enviar una petición para insertar una nueva alineación
    public void insertAlineacionRep(Alineacion alineacion) {
        alineacionInterface.insertAlineacion(alineacion).enqueue(modifCallback);
    }

    //Método encargado de enviar una petición para actualizar una determinada alineación
    public void updateAlineacionRep(Alineacion alineacion) {
        alineacionInterface.updateAlineacion(alineacion).enqueue(modifCallback);
    }

    //Método encargado de enviar una petición para eliminar una determinada alineación
    public void deleteAlineacionRep(int id) {
        alineacionInterface.deleteAlineaciones(id).enqueue(modifCallback);
    }

    //Método encargado de recibir la respuesta a la petición insert/update/deleteAlineacion
    public void setFilasAfectadas(int filasAfectadas, String metodo) {
        switch (metodo) {
            case "POST":
                AlineacionesVM.setFilasAfectadas(filasAfectadas);
                break;
            case "PUT": case "DELETE":
                DetalleAlineacionVM.setFilasAfectadas(filasAfectadas);
                break;
        }
    }

}
