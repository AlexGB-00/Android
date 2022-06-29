package com.example.nba_myteam_app.handlers;

import android.content.SharedPreferences;
import com.example.nba_myteam_app.retrofit.entities.Usuario;
import com.google.gson.Gson;

public class SesionHandler {

    //Atributos
    private static SharedPreferences sharedPrefSesion;

    //Getters y setters
    public static SharedPreferences getSharedPrefSesion() {
        return sharedPrefSesion;
    }
    public static void setSharedPrefSesion(SharedPreferences newSharedPrefSesion) {
        if(newSharedPrefSesion != null) {
            sharedPrefSesion = newSharedPrefSesion;
        }
    }

    //Métodos

    /* Estudio Interfaz
        Propósito: leer y obtener los datos de la sesión activa del usuario almacenados en un fichero SharedPreferences.
        Prototipo: public static Usuario obtenerSesionActiva()
        Precondiciones: ninguna.
        Entradas: ninguna.
        Salidas: el usuario junto con sus datos en caso de que existan, o null, en caso de que no existan datos.
        Postcondiciones: se devuelve el usuario asociado al nombre de la función.
     */
     public static Usuario obtenerSesionActiva() {
         String json = sharedPrefSesion.getString("usuario", null);
         Usuario usuario = new Gson().fromJson(json, Usuario.class);
         return usuario;
     }

     /* Estudio Interfaz
         Propósito: escribir los datos del usuario correspondientes a la nueva sesión activa en un fichero SharedPreferences.
         Prototipo: public static void actualizarSesionActiva(Usuario nuevoUsuario)
         Precondiciones: el usuario debe ser distinto de null y sus campos deben ser válidos.
         Entradas: el usuario con sus credenciales.
         Salidas: ninguna.
         Postcondiciones: se actualiza el fichero con los datos del nuevo usuario logueado.
     */
     public static void actualizarSesionActiva(Usuario nuevoUsuario) {
         SharedPreferences.Editor editor = sharedPrefSesion.edit();
         String json = new Gson().toJson(nuevoUsuario);
         editor.putString("usuario", json);
         editor.apply();
     }

     /* Estudio Interfaz
         Propósito: eliminar el contenido del fichero SharedPreferences.
         Prototipo: public static void eliminarSesionActiva()
         Precondiciones: ninguna.
         Entradas: ninguna.
         Salidas: ninguna.
         Postcondiciones: el fichero se actualiza quedando vacío de contenido.
     */
     public static void eliminarSesionActiva() {
         SharedPreferences.Editor editor = sharedPrefSesion.edit();
         editor.clear().apply();
     }

}
