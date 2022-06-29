package com.example.nba_myteam_app.retrofit.interfaces;

import com.example.nba_myteam_app.retrofit.entities.Alineacion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AlineacionInterface {

    //Listado de alineaciones de un determinado usuario
    @GET("api/Alineaciones")
    Call<List<Alineacion>> getListadoAlineacionesUsuario(@Query("nickUsuario") String nickUsuario);

    //Alineación a partir de su id
    @GET("api/Alineaciones/{id}")
    Call<Alineacion> getAlineacion(@Path("id") int id);

    //Insertar alineación
    @POST("api/Alineaciones")
    Call<Integer> insertAlineacion(@Body Alineacion alineacion);

    //Actualizar alineación
    @PUT("api/Alineaciones")
    Call<Integer> updateAlineacion(@Body Alineacion alineacion);

    //Eliminar alineación
    @DELETE("api/Alineaciones/{id}")
    Call<Integer> deleteAlineaciones(@Path("id") int id);

}
