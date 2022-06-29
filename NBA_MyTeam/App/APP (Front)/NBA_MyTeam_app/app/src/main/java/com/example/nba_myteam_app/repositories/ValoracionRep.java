package com.example.nba_myteam_app.repositories;

import com.example.nba_myteam_app.retrofit.callbacks.ListadoCallback;
import com.example.nba_myteam_app.retrofit.callbacks.ModifCallback;
import com.example.nba_myteam_app.retrofit.entities.Valoracion;
import com.example.nba_myteam_app.retrofit.interfaces.ValoracionInterface;
import com.example.nba_myteam_app.view_models.DetalleAlineacionVM;
import com.example.nba_myteam_app.view_models.SobresVM;

import java.util.List;

public class ValoracionRep extends BaseRep {

    //Atributos
    private ValoracionInterface valoracionInterface;
    private ModifCallback modifCallback;

    //Constructor
    public ValoracionRep() {
        valoracionInterface = getRetrofit().create(ValoracionInterface.class);
        modifCallback = new ModifCallback();
    }

    //Métodos
    //Método encargado de enviar una petición para compartir una alineación a un determinado usuario
    public void insertValoracionRep(String nickUsuario, int idAlineacion) {
        valoracionInterface.insertValoracion(nickUsuario, idAlineacion).enqueue(modifCallback);
    }
    //Método encargado de recibir la respuesta a la petición insertValoracion
    public void setFilasAfectadas(int filasAfectadas) {
        DetalleAlineacionVM.setFilasAfectadas(filasAfectadas);
    }

}
