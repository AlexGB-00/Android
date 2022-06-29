package com.example.nba_myteam_app.retrofit.callbacks;

import com.example.nba_myteam_app.repositories.AlineacionRep;
import com.example.nba_myteam_app.repositories.EquipoRep;
import com.example.nba_myteam_app.repositories.JugadorRep;
import com.example.nba_myteam_app.repositories.UsuarioRep;
import com.example.nba_myteam_app.retrofit.entities.Alineacion;
import com.example.nba_myteam_app.retrofit.entities.Equipo;
import com.example.nba_myteam_app.retrofit.entities.Jugador;
import com.example.nba_myteam_app.retrofit.entities.Usuario;

import java.time.LocalDateTime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Esta clase representa el Callback que gestiona las respuestas de la API que devuelven un solo objeto de cualquier tipo definido como entidad
//en la aplicación (Alineacion, Equipo, Jugador, JugadorAlineacion, JugadorClub, Usuario, Valoracion).

public class EntidadCallback<T> implements Callback<T> {
    
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        T entidad = response.body();
        String ctller = call.request().url().pathSegments().get(1);
        if(response.isSuccessful()) {
            switch (ctller) {
                case "Usuarios":
                    UsuarioRep usuarioRep = new UsuarioRep();
                    String fstParam = call.request().url().queryParameterName(0);
                    switch (fstParam) {
                        case "nombreUsuario":
                            usuarioRep.setUsuarioRep((Usuario) entidad);
                            break;
                        case "nickUsuario":
                            usuarioRep.setUltimaFechaIncorporacionUsuarioRep((LocalDateTime) entidad);
                            break;
                        case "usuario":
                            usuarioRep.setNickUsuarioRep((String) entidad);
                            break;
                    }
                    break;
                case "Equipos":
                    new EquipoRep().setEquipoRep((Equipo) entidad);
                    break;
                case "Alineaciones":
                    new AlineacionRep().setAlineacionRep((Alineacion) entidad);
                    break;
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        String mensaje = "Ha ocurrido un error al realizar la petición";
    }

}
