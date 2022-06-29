package com.example.nba_myteam_app.repositories;

import com.example.nba_myteam_app.retrofit.callbacks.ListadoCallback;
import com.example.nba_myteam_app.retrofit.callbacks.ModifCallback;
import com.example.nba_myteam_app.retrofit.entities.Jugador;
import com.example.nba_myteam_app.retrofit.entities.JugadorClub;
import com.example.nba_myteam_app.retrofit.enums.EnumPosiciones;
import com.example.nba_myteam_app.retrofit.interfaces.JugadorClubInterface;
import com.example.nba_myteam_app.view_models.ClubVM;
import com.example.nba_myteam_app.view_models.DetalleAlineacionVM;
import com.example.nba_myteam_app.view_models.DetalleJugadorVM;
import com.example.nba_myteam_app.view_models.SobresVM;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class JugadorClubRep extends BaseRep {

    //Atributos
    private JugadorClubInterface jugadorClubInterface;
    private ListadoCallback<Jugador> listadoCallback;
    private ModifCallback modifCallback;

    //Constructor
    public JugadorClubRep() {
        jugadorClubInterface = getRetrofit().create(JugadorClubInterface.class);
        listadoCallback = new ListadoCallback<>();
        modifCallback = new ModifCallback();
    }

    //Métodos
    //Método encargado de enviar una petición para obtener el listado de jugadores pertenecientes a un determinado usuario
    public void getListadoJugadoresClubUsuarioRep(String nickUsuario) {
        jugadorClubInterface.getListadoJugadoresClubUsuario(nickUsuario).enqueue(listadoCallback);
    }
    //Método encargado de recibir la respuesta a la petición getListadoJugadoresClubUsuario
    public void setListadoJugadoresClubUsuarioRep(List<Jugador> listadoJugadores) {
        ClubVM.setListadoJugadores(listadoJugadores);
    }

    //Método encargado de enviar una petición para obtener el listado de jugadores pertenecientes a un determinado usuario y a una determinada posición
    public void getListadoJugadoresClubUsuarioPorPosicionRep(String nickUsuario, EnumPosiciones posicion) {
        jugadorClubInterface.getListadoJugadoresClubUsuarioPorPosicion(nickUsuario, posicion).enqueue(listadoCallback);
    }
    //Método encargado de recibir la respuesta a la petición getListadoJugadoresClubUsuarioPorPosicion
    public void setListadoJugadoresClubUsuarioPorPosicionRep(List<Jugador> listadoJugadores) {
        DetalleAlineacionVM.setListadoJugadores(listadoJugadores);
    }

    //Método encargado de enviar una petición para insertar un determinado jugador en el club de un determinado usuario
    public void insertJugadorClubRep(JugadorClub jugadorClub) {
        jugadorClubInterface.insertJugadorClub(jugadorClub).enqueue(modifCallback);
    }

    //Método encargado de recibir la respuesta a la petición insertJugadorClub
    public void setFilasAfectadas(int filasAfectadas) {
        SobresVM.setFilasAfectadas(filasAfectadas);
    }

}
