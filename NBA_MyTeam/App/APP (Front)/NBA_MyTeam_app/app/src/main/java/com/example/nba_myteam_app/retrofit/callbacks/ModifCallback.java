package com.example.nba_myteam_app.retrofit.callbacks;

import com.example.nba_myteam_app.repositories.AlineacionRep;
import com.example.nba_myteam_app.repositories.JugadorAlineacionRep;
import com.example.nba_myteam_app.repositories.JugadorClubRep;
import com.example.nba_myteam_app.repositories.UsuarioRep;
import com.example.nba_myteam_app.repositories.ValoracionRep;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Esta clase representa el Callback que gestiona las respuestas de la API que devuelven un objeto Integer, es decir, que han realizado algún tipo de
//modificación en la base de datos (Insert, Update, Delete).

public class ModifCallback implements Callback<Integer> {

    @Override
    public void onResponse(Call<Integer> call, Response<Integer> response) {
        int filasAfectadas;
        if(response.isSuccessful()) {
            filasAfectadas = response.body();
            String entidad = call.request().url().pathSegments().get(1);
            switch (entidad) {
                case "Alineaciones":
                    String metodo = call.request().method();
                    new AlineacionRep().setFilasAfectadas(filasAfectadas, metodo);
                    break;
                case "JugadoresAlineaciones":
                    new JugadorAlineacionRep().setFilasAfectadas(filasAfectadas);
                    break;
                case "JugadoresClubs":
                    new JugadorClubRep().setFilasAfectadas(filasAfectadas);
                    break;
                case "Usuarios":
                    new UsuarioRep().setFilasAfectadas(filasAfectadas);
                    break;
                case "Valoraciones":
                    new ValoracionRep().setFilasAfectadas(filasAfectadas);
                   break;
            }
        }
    }

    @Override
    public void onFailure(Call<Integer> call, Throwable t) {
        String mensaje = "Ha ocurrido un error al realizar la petición";
    }

}
