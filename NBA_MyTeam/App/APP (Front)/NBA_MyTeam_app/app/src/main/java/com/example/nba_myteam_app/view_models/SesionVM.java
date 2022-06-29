package com.example.nba_myteam_app.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nba_myteam_app.retrofit.entities.Usuario;

public class SesionVM extends ViewModel {

    //Atributos
    private static MutableLiveData<Usuario> usuarioActivo;

    //Getters y setters
    public static MutableLiveData<Usuario> getUsuarioActivo() {
        if(usuarioActivo == null) {
            usuarioActivo = new MutableLiveData<>();
        }
        return usuarioActivo;
    }
    public static void setUsuarioActivo(Usuario nuevoUsuario) {
        usuarioActivo.setValue(nuevoUsuario);
    }

}
