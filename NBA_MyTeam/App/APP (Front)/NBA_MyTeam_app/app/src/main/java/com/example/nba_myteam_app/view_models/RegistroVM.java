package com.example.nba_myteam_app.view_models;

import androidx.lifecycle.MutableLiveData;

import com.example.nba_myteam_app.repositories.UsuarioRep;
import com.example.nba_myteam_app.retrofit.entities.Usuario;

public class RegistroVM extends SesionVM {

    //Atributos
    private UsuarioRep usuarioRep;
    private static MutableLiveData<Integer> filasAfectadas;
    private static final boolean LLAMADA_REGISTRO = true;
    private static MutableLiveData<String> usuarioRepetido;

    //Constructor
    public RegistroVM() {
        usuarioRep = new UsuarioRep();
        filasAfectadas = new MutableLiveData<>();
        usuarioRepetido = new MutableLiveData<>();
    }

    //Getters y setters
    public UsuarioRep getUsuarioRep() {
        return usuarioRep;
    }
    public void setUsuarioRep(UsuarioRep usuarioRep) {
        this.usuarioRep = usuarioRep;
    }

    public static MutableLiveData<Integer> getFilasAfectadas() {
        if(filasAfectadas == null) {
            filasAfectadas = new MutableLiveData<>();
        }
        return filasAfectadas;
    }
    public static void setFilasAfectadas(Integer nuevasFilasAfectadas) {
        filasAfectadas.setValue(nuevasFilasAfectadas);
    }

    public static boolean getLlamadaRegistro() {
        return LLAMADA_REGISTRO;
    }

    public static MutableLiveData<String> getUsuarioRepetido() {
        if(usuarioRepetido == null) {
            usuarioRepetido = new MutableLiveData<>();
        }
        return usuarioRepetido;
    }

    public static void setUsuarioRepetido(String nuevoUsuarioRepetido) {
        usuarioRepetido.setValue(nuevoUsuarioRepetido);
    }

    //Métodos
    public void insertUsuarioVM(Usuario usuario) {
        usuarioRep.insertUsuarioRep(usuario);
    }

    public void getNickUsuarioVM(String usuario, boolean metodoCorreo) {
        usuarioRep.getNickUsuarioRep(usuario, metodoCorreo, LLAMADA_REGISTRO);
    }

}
