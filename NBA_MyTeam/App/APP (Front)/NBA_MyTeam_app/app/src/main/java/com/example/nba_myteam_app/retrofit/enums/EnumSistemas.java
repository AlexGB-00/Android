package com.example.nba_myteam_app.retrofit.enums;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public enum EnumSistemas {

    @SerializedName("0") DOS_DOS_UNO, @SerializedName("1") UNO_DOS_DOS, @SerializedName("2") DOS_UNO_DOS, @SerializedName("3") UNO_TRES_UNO,
    @SerializedName("4") TRES_UNO_UNO;

    /* Estudio Interfaz
        Prototipo: public static ArrayList<String> getListadoValoresString()
        Propósito: obtener un listado con los valores definidos en EnumSistemas en formato String.
        Precondiciones: ninguna.
        Entradas: ninguna.
        Salidas: un objeto ArrayList con los valores en formato String.
        Postcondiciones: se devuelve el ArrayList asociado al nombre de la función.
     */
    public static ArrayList<String> getListadoValoresString() {
        ArrayList<String> listado = new ArrayList<>();
        for (Enum valor : EnumSistemas.values()) {
            listado.add(valor.name());
        }
        return listado;
    }

}
