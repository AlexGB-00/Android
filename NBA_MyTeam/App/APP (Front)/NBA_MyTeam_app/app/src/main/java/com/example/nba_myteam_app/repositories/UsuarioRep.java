package com.example.nba_myteam_app.repositories;

import com.example.nba_myteam_app.retrofit.callbacks.EntidadCallback;
import com.example.nba_myteam_app.retrofit.callbacks.ModifCallback;
import com.example.nba_myteam_app.retrofit.entities.Usuario;
import com.example.nba_myteam_app.retrofit.interfaces.UsuarioInterface;
import com.example.nba_myteam_app.view_models.DetalleAlineacionVM;
import com.example.nba_myteam_app.view_models.RegistroVM;
import com.example.nba_myteam_app.view_models.SesionVM;
import com.example.nba_myteam_app.view_models.SobresVM;

import java.time.LocalDateTime;

public class UsuarioRep extends BaseRep {

    //Atributos
    private ModifCallback modifCallback;
    private UsuarioInterface usuarioInterface;
    private static boolean llamadaRegistroVM;

    //Constructor
    public UsuarioRep() {
        modifCallback = new ModifCallback();
        usuarioInterface = getRetrofit().create(UsuarioInterface.class);
    }

    //Métodos
    //Método encargado de enviar una petición para comprobar si existe un determinado usuario registrado
    public void getUsuarioRep(String usuario, String contrasenha, boolean loginCorreo) {
        EntidadCallback<Usuario> entidadCallback = new EntidadCallback<>();
        usuarioInterface.getUsuario(usuario, contrasenha, loginCorreo).enqueue(entidadCallback);
    }
    //Método encargado de recibir la respuesta a la petición getUsuario
    public void setUsuarioRep(Usuario usuario) {
        SesionVM.setUsuarioActivo(usuario);
    }

    //Método encargado de enviar una petición para comprobar si existe un determinado usuario a partir de su nick o correo electrónico
    public void getNickUsuarioRep(String usuario, boolean metodoCorreo, boolean llamadaRegistro) {
        llamadaRegistroVM = llamadaRegistro;
        EntidadCallback<String> entidadCallback = new EntidadCallback<>();
        usuarioInterface.getNickUsuario(usuario, metodoCorreo).enqueue(entidadCallback);
    }
    //Método encargado de recibir la respuesta a la petición getNickUsuario
    public void setNickUsuarioRep(String nickUsuario) {
        if(llamadaRegistroVM) {
            RegistroVM.setUsuarioRepetido(nickUsuario);
        } else {
            DetalleAlineacionVM.setNickUsuarioCompartido(nickUsuario);
        }
    }

    //Método encargado de enviar una petición para obtener la última fecha de apertura de un sobre por parte de un determinado usuario
    public void getUltimaFechaIncorporacionUsuarioRep(String nickUsuario) {
        EntidadCallback<LocalDateTime> entidadCallback = new EntidadCallback<>();
        usuarioInterface.getUltimaFechaIncorporacionUsuario(nickUsuario).enqueue(entidadCallback);
    }
    //Método encargado de recibir la respuesta a la petición getUltimaFechaIncorporacionUsuario
    public void setUltimaFechaIncorporacionUsuarioRep(LocalDateTime ultimaFecha) {
        SobresVM.setUltimafechaIncorporacion(ultimaFecha);
    }

    //Método encargado de enviar una petición para insertar un nuevo usuario.
    public void insertUsuarioRep(Usuario usuario) {
        usuarioInterface.insertUsuario(usuario).enqueue(modifCallback);
    }

    //Método encargado de recibir la respuesta a la petición insertUsuario
    public void setFilasAfectadas(int filasAfectadas) {
        RegistroVM.setFilasAfectadas(filasAfectadas);
    }

}
