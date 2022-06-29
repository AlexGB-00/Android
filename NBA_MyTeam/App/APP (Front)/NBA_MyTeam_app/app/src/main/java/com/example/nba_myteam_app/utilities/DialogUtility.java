package com.example.nba_myteam_app.utilities;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.nba_myteam_app.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DialogUtility {

    /* Estudio Interfaz
        Prototipo: public static AlertDialog crearAlertDialog(Context context, String titulo, String msg, View layout, DialogInterface.OnClickListener positiveButtonAction)
        Propósito: crear un diálogo de alerta genérico el cual tenga un layout específico y esté compuesto por un título, un mensaje y dos botones ("Aceptar" y "Cancelar")
        que activen diferentes acciones.
        Precondiciones: tanto el contexto como el título y el mensaje y el comportamiento para el botón positivo deben ser distintos de null.
        Entradas: el contexto de la actividad donde se desea mostrar el diálogo, el título y el mensaje, el layout y el comportamiento para el botón "Aceptar".
        (El botón "Cancelar" simplemente ejecutará la acción de cerrar el diálogo).
        Salidas: el objeto de tipo AlertDialog que representa el diálogo creado.
        Postcondiciones: se crea el diálogo con las características correspondientes y se devuelve el objeto AlertDialog asociado al nombre de la función.
     */
    public static AlertDialog crearAlertDialog(Context context, String titulo, String msg, View layout, DialogInterface.OnClickListener positiveButtonAction) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle(titulo);
        builder.setMessage(msg);
        builder.setView(layout);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.btn_aceptar_dialog, positiveButtonAction);
        builder.setNegativeButton(R.string.btn_cancelar_dialog, null);
        return builder.create();
    }

}
