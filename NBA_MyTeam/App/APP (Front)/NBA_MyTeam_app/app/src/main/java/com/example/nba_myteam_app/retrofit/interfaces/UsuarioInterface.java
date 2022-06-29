package com.example.nba_myteam_app.retrofit.interfaces;

import com.example.nba_myteam_app.retrofit.entities.Usuario;

import java.time.LocalDateTime;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsuarioInterface {

    //Usuario a partir de sus credenciales
    @GET("api/Usuarios")
    Call<Usuario> getUsuario(@Query("nombreUsuario") String nombreUsuario, @Query("contrasenha") String contrasenha, @Query("loginCorreo") boolean loginCorreo);

    //Nick de usuario a partir del nick o correo electrónico
    @GET("api/Usuarios")
    Call<String> getNickUsuario(@Query("usuario") String usuario, @Query("metodoCorreo") boolean metodoCorreo);

    //Última fecha apertura de sobre de un determinado usuario
    @GET("api/Usuarios")
    Call<LocalDateTime> getUltimaFechaIncorporacionUsuario(@Query("nickUsuario") String nickUsuario);

    //Insertar usuario
    @POST("api/Usuarios")
    Call<Integer> insertUsuario(@Body Usuario usuario);

    //Actualizar usuario
    @PUT("api/Usuarios")
    Call<Integer> updateUsuario(@Body Usuario usuario);

    //Eliminar usuario
    @DELETE("api/Usuarios/{nick}")
    Call<Integer> deleteUsuario(@Path("nick") String nick);

}
