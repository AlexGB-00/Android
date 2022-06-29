package com.example.nba_myteam_app.view_models;

import com.example.nba_myteam_app.repositories.UsuarioRep;
import com.example.nba_myteam_app.retrofit.entities.Usuario;

public class LogInVM extends SesionVM {

    //Atributos
    private UsuarioRep usuarioRep;

    //Constructor
    public LogInVM() {
        usuarioRep = new UsuarioRep();
    }

    //Getters y setters
    public UsuarioRep getUsuarioRep() {
        return usuarioRep;
    }

    public void setUsuarioRep(UsuarioRep usuarioRep) {
        if(usuarioRep != null) {
            this.usuarioRep = usuarioRep;
        }
    }

    //Métodos
    public void comprobarUsuarioExistenteVM(String usuario, String contrasenha, boolean loginCorreo) {
        usuarioRep.getUsuarioRep(usuario, contrasenha, loginCorreo);
    }

}
