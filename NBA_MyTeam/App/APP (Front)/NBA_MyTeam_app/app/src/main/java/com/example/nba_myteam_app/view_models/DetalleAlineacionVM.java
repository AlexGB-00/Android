package com.example.nba_myteam_app.view_models;

import androidx.lifecycle.MutableLiveData;

import com.example.nba_myteam_app.repositories.AlineacionRep;
import com.example.nba_myteam_app.repositories.JugadorAlineacionRep;
import com.example.nba_myteam_app.repositories.JugadorClubRep;
import com.example.nba_myteam_app.repositories.UsuarioRep;
import com.example.nba_myteam_app.repositories.ValoracionRep;
import com.example.nba_myteam_app.retrofit.entities.Alineacion;
import com.example.nba_myteam_app.retrofit.entities.Jugador;
import com.example.nba_myteam_app.retrofit.entities.JugadorAlineacion;
import com.example.nba_myteam_app.retrofit.enums.EnumPosiciones;

import java.util.List;

public class DetalleAlineacionVM extends SesionVM {

    //Atributos
    private AlineacionRep alineacionRep;
    private JugadorClubRep jugadorClubRep;
    private JugadorAlineacionRep jugadorAlineacionRep;
    private UsuarioRep usuarioRep;
    private ValoracionRep valoracionRep;
    private static MutableLiveData<Integer> filasAfectadas;
    private static MutableLiveData<List<Jugador>> listadoJugadores;
    private static MutableLiveData<List<Jugador>> listadoJugadoresAlineacion;
    private static MutableLiveData<Alineacion> alineacionActualizada;
    private static MutableLiveData<String> nickUsuarioCompartido;
    private static final boolean LLAMADA_REGISTRO = false;

    //Constructor
    public DetalleAlineacionVM() {
        alineacionRep = new AlineacionRep();
        jugadorClubRep = new JugadorClubRep();
        jugadorAlineacionRep = new JugadorAlineacionRep();
        usuarioRep = new UsuarioRep();
        valoracionRep = new ValoracionRep();
        filasAfectadas = new MutableLiveData<>();
        listadoJugadores = new MutableLiveData<>();
        alineacionActualizada = new MutableLiveData<>();
        listadoJugadoresAlineacion = new MutableLiveData<>();
        nickUsuarioCompartido = new MutableLiveData<>();
    }

    //Getters y setters
    public AlineacionRep getAlineacionRep() {
        return alineacionRep;
    }
    public void setAlineacionRep(AlineacionRep alineacionRep) {
        this.alineacionRep = alineacionRep;
    }

    public JugadorClubRep getJugadorClubRep() {
        return jugadorClubRep;
    }
    public void setJugadorClubRep(JugadorClubRep jugadorClubRep) {
        this.jugadorClubRep = jugadorClubRep;
    }

    public JugadorAlineacionRep getJugadorAlineacionRep() {
        return jugadorAlineacionRep;
    }
    public void setJugadorAlineacionRep(JugadorAlineacionRep jugadorAlineacionRep) {
        this.jugadorAlineacionRep = jugadorAlineacionRep;
    }

    public UsuarioRep getUsuarioRep() {
        return usuarioRep;
    }
    public void setUsuarioRep(UsuarioRep usuarioRep) {
        this.usuarioRep = usuarioRep;
    }

    public ValoracionRep getValoracionRep() {
        return valoracionRep;
    }
    public void setValoracionRep(ValoracionRep valoracionRep) {
        this.valoracionRep = valoracionRep;
    }

    public static MutableLiveData<Integer> getFilasAfectadas() {
        if(filasAfectadas == null) {
            filasAfectadas = new MutableLiveData<>();
        }
        return filasAfectadas;
    }
    public static void setFilasAfectadas(int nuevasFilasAfectadas) {
        filasAfectadas.setValue(nuevasFilasAfectadas);
    }

    public static MutableLiveData<List<Jugador>> getListadoJugadores() {
        if(listadoJugadores == null) {
            listadoJugadores = new MutableLiveData<>();
        }
        return listadoJugadores;
    }
    public static void setListadoJugadores(List<Jugador> newListadoJugadores) {
        listadoJugadores.setValue(newListadoJugadores);
    }

    public static MutableLiveData<List<Jugador>> getListadoJugadoresAlineacion() {
        if(listadoJugadoresAlineacion == null) {
            listadoJugadoresAlineacion = new MutableLiveData<>();
        }
        return listadoJugadoresAlineacion;
    }
    public static void setListadoJugadoresAlineacion(List<Jugador> nuevoListadoJugadoresAlineacion) {
       listadoJugadoresAlineacion.setValue(nuevoListadoJugadoresAlineacion);
    }

    public static MutableLiveData<Alineacion> getAlineacionActualizada() {
        if(alineacionActualizada == null) {
            alineacionActualizada = new MutableLiveData<>();
        }
        return alineacionActualizada;
    }

    public static void setAlineacionActualizada(Alineacion nuevaAlineacion) {
        alineacionActualizada.setValue(nuevaAlineacion);
    }

    public static MutableLiveData<String> getNickUsuarioCompartido() {
        if(nickUsuarioCompartido == null) {
            nickUsuarioCompartido = new MutableLiveData<>();
        }
        return nickUsuarioCompartido;
    }
    public static void setNickUsuarioCompartido(String nuevoNickUsuarioCompartido) {
        nickUsuarioCompartido.setValue(nuevoNickUsuarioCompartido);
    }

    public static boolean getLlamadaRegistro() {
        return LLAMADA_REGISTRO;
    }

    //Métodos
    public void getListadoJugadoresClubUsuarioPorPosicionVM(String nickUsuario, EnumPosiciones posicion) {
        jugadorClubRep.getListadoJugadoresClubUsuarioPorPosicionRep(nickUsuario, posicion);
    }

    public void getListadoJugadoresAlineacionVM(int idAlineacion) {
        jugadorAlineacionRep.getListadoJugadoresAlineacionRep(idAlineacion);
    }

    public void getNickUsuarioVM(String usuario, boolean metodoCorreo) {
        usuarioRep.getNickUsuarioRep(usuario, metodoCorreo, LLAMADA_REGISTRO);
    }

    public void getAlineacionVM(int id) {
        alineacionRep.getAlineacionRep(id);
    }

    public void updateAlineacionVM(Alineacion alineacion) {
        alineacionRep.updateAlineacionRep(alineacion);
    }

    public void deleteAlineacionVM(int id) {
        alineacionRep.deleteAlineacionRep(id);
    }

    public void insertJugadorAlineacionVM(JugadorAlineacion jugadorAlineacion) {
        jugadorAlineacionRep.insertJugadorAlineacionRep(jugadorAlineacion);
    }

    public void updateJugadorAlineacionVM(JugadorAlineacion jugadorAlineacion, int idJugadorSustituto) {
        jugadorAlineacionRep.updateJugadorAlineacionRep(jugadorAlineacion, idJugadorSustituto);
    }

    public void deleteJugadorAlineacionVM(int idJugador, int idAlineacion) {
        jugadorAlineacionRep.deleteJugadorAlineacionRep(idJugador, idAlineacion);
    }

    public void insertValoracionVM(String nickUsuario, int idAlineacion) {
        valoracionRep.insertValoracionRep(nickUsuario, idAlineacion);
    }
    
}
