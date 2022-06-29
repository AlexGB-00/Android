package com.example.nba_myteam_app.retrofit.interfaces;

import com.example.nba_myteam_app.retrofit.entities.Valoracion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ValoracionInterface {

    //Listado de valoraciones de una determinada alineacion
    @GET("api/Valoraciones")
    Call<List<Valoracion>> getListadoValoracionesAlineacion(@Query("idAlineacion") int idAlineacion);

    //Insertar valoración
    @POST("api/Valoraciones")
    Call<Integer> insertValoracion(@Query("nickUsuario") String nickUsuario, @Query("idAlineacion") int idAlineacion);

    //Actualizar valoración
    @PUT("api/Valoraciones")
    Call<Integer> updateValoracion(@Body Valoracion valoracion);

    //Eliminar valoración
    @DELETE("api/Valoraciones")
    Call<Integer> deleteValoracion(@Query("idAlineacion") int idAlineacion, @Query("nickUsuario") String nickUsuario);

}
