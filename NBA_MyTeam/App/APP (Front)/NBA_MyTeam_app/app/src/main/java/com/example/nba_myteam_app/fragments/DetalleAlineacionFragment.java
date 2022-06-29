package com.example.nba_myteam_app.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nba_myteam_app.R;
import com.example.nba_myteam_app.retrofit.entities.Alineacion;
import com.example.nba_myteam_app.retrofit.entities.Jugador;
import com.example.nba_myteam_app.retrofit.entities.JugadorAlineacion;
import com.example.nba_myteam_app.retrofit.enums.EnumPosiciones;
import com.example.nba_myteam_app.retrofit.enums.EnumSistemas;
import com.example.nba_myteam_app.utilities.DialogUtility;
import com.example.nba_myteam_app.view_models.DetalleAlineacionVM;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleAlineacionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleAlineacionFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    //Atributos
    private AutoCompleteTextView actvSistemaAlineacion;
    private TextInputLayout tilSistemaAlineacion;
    private MaterialTextView tevNombreAlineacion, tevFechaCreacionAlineacion, tevValoracionAlineacion;
    private AlertDialog deleteDialog, jugadorDialog, shareDialog;
    private DetalleAlineacionVM detalleAlineacionVM;
    private Alineacion alineacion, alineacionAux;
    private Context context;
    private ViewGroup baseScene;
    private ImageButton btnEliminar, btnCompartir, img1, img2, img3, img4, img5, imgSeleccionada;
    private View dialogLayout;
    private Jugador jugadorSeleccionado;
    private int peticionModificacion;
    private String usuarioCompartido;
    private int sistemaSeleccionado;
    private boolean shareValido;
    private TextInputLayout tilNick, tilEmail;
    private TextInputEditText edtNick, edtEmail;
    private RadioGroup radGrpBusqueda;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetalleAlineacionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleAlineacionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleAlineacionFragment newInstance(String param1, String param2) {
        DetalleAlineacionFragment fragment = new DetalleAlineacionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detalle_alineacion, container, false);
        tevNombreAlineacion = v.findViewById(R.id.tev_nombre_alineacion);
        tilSistemaAlineacion = v.findViewById(R.id.teil_sistema_alineacion);
        actvSistemaAlineacion = v.findViewById(R.id.actv_sistema_alineacion);
        tevFechaCreacionAlineacion = v.findViewById(R.id.tev_fecha_alineacion);
        tevValoracionAlineacion = v.findViewById(R.id.tev_valoracion_alineacion);
        btnEliminar = v.findViewById(R.id.btn_eliminar_alineacion);
        btnCompartir = v.findViewById(R.id.btn_compartir_alineacion);
        baseScene = v.findViewById(R.id.base_scene);
        img1 = v.findViewById(R.id.img1);
        img2 = v.findViewById(R.id.img2);
        img3 = v.findViewById(R.id.img3);
        img4 = v.findViewById(R.id.img4);
        img5 = v.findViewById(R.id.img5);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Se obtiene el contexto
        context = getActivity();
        //Se recoge la alineación seleccionada
        alineacion = (Alineacion) getActivity().getIntent().getExtras().getSerializable("alineacion");
        alineacionAux = new Alineacion(alineacion);
        //Se comprueba si la alineación está finalizada
        if(alineacion.getFinalizada()) {
            tilSistemaAlineacion.setEnabled(false);
            btnEliminar.setEnabled(false);
            img1.setEnabled(false);
            img2.setEnabled(false);
            img3.setEnabled(false);
            img4.setEnabled(false);
            img5.setEnabled(false);
        }
        //Se actualiza la variable que indica la validez del formulario a true
        shareValido = true;
        //Se obtiene el ViewModel
        detalleAlineacionVM = new ViewModelProvider(this).get(DetalleAlineacionVM.class);
        //Se cargan los datos de la alineación en la ficha que los muestra
        inicializarFichaAlineacion();
        //Se establece el sistema de la alineación de forma gráfica
        establecerSistemaGrafico(alineacion.getSistema().ordinal());
        //Se obtiene el listado de jugadores de la alineación
        detalleAlineacionVM.getListadoJugadoresAlineacionVM(alineacion.getId());
        //Se establecen los listeners
        btnEliminar.setOnClickListener(this);
        btnCompartir.setOnClickListener(this);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
        actvSistemaAlineacion.setOnItemClickListener(this);
        //Se construye el AlertDialog para la selección de jugadores
        jugadorDialog = crearJugadorDialog();
        //Se construye el AlertDialog para el borrado de la alineación
        DialogInterface.OnClickListener positiveButtonAction = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                detalleAlineacionVM.deleteAlineacionVM(alineacion.getId());
                peticionModificacion = 1;
            }};
        deleteDialog = DialogUtility.crearAlertDialog(context, getString(R.string.del_alineacion_dialog_title), getString(R.string.del_alineacion_dialog_msg), null, positiveButtonAction);
        //Se construye y se asigna el comportamiento para el AlertDialog dedicado a compartir la alineación
        View shareDialogView = requireActivity().getLayoutInflater().inflate(R.layout.share_dialog, null);
        shareDialog = DialogUtility.crearAlertDialog(context, getString(R.string.share_dialog_title), getString(R.string.share_dialog_msg), shareDialogView, null);
        inicializarFormularioShare(shareDialogView);
        asignarComportamientoShareDialog();
        //Se definen los Observers
        final Observer<Integer> filasAfectadasObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer filasAfectadas) {
                if(filasAfectadas >= 1) {
                    switch (peticionModificacion) {
                        case 0: //Update alineación
                            if (alineacionAux.getFinalizada()) {
                                alineacion.setFinalizada(true);
                                detalleAlineacionVM.insertValoracionVM(usuarioCompartido, alineacion.getId());
                                peticionModificacion = 5;
                            } else {
                                alineacion.setSistema(alineacionAux.getSistema());
                                Transition transition = new AutoTransition();
                                transition.setDuration(700);
                                transition.setInterpolator(new AccelerateDecelerateInterpolator());
                                TransitionManager.beginDelayedTransition(baseScene, transition);
                                establecerSistemaGrafico(sistemaSeleccionado);
                            }
                            break;
                        case 1: //Delete alineación
                            Toast.makeText(context, R.string.del_alin_toast, Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                            break;
                        case 2: case 3: //Insert y update jugador alineación
                            Glide.with(context).load(jugadorSeleccionado.getImagen())
                                    .error(R.drawable.error)
                                    //.placeholder(R.drawable.placeholder)
                                    .into(imgSeleccionada);
                            imgSeleccionada.setTag(jugadorSeleccionado);
                            detalleAlineacionVM.getAlineacionVM(alineacion.getId());
                            break;
                        case 4: //Delete jugador alineación
                            imgSeleccionada.setImageResource(R.drawable.add);
                            imgSeleccionada.setTag(null);
                            detalleAlineacionVM.getAlineacionVM(alineacion.getId());
                            break;
                        case 5: //Insert valoración
                            Toast.makeText(context, R.string.share_alin_toast, Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                            break;
                    }
                } else {
                   Toast.makeText(context, R.string.fail_toast, Toast.LENGTH_LONG).show();
                }
            }
        };
        DetalleAlineacionVM.getFilasAfectadas().observe(getViewLifecycleOwner(), filasAfectadasObserver);
        final Observer<List<Jugador>> listadoJugadoresObserver = new Observer<List<Jugador>>() {
            @Override
            public void onChanged(List<Jugador> listadoJugadores) {
                if(listadoJugadores != null) {
                    ArrayList<Jugador> listadoJugadoresPosicion = new ArrayList<>(listadoJugadores);
                    mostrarDialogElegirJugador(filtrarListadoJugadoresPosicion(listadoJugadoresPosicion));
                } else {
                    Toast.makeText(context, "No hay jugadores en su club de esta posición", Toast.LENGTH_SHORT).show();
                }
            }
        };
        DetalleAlineacionVM.getListadoJugadores().observe(getViewLifecycleOwner(), listadoJugadoresObserver);
        final Observer<List<Jugador>> listadoJugadoresAlineacionObserver = new Observer<List<Jugador>>() {
            @Override
            public void onChanged(List<Jugador> listadoJugadores) {
                if(listadoJugadores != null) {
                    cargarAlineacionInicial(listadoJugadores);
                }
            }
        };
        DetalleAlineacionVM.getListadoJugadoresAlineacion().observe(getViewLifecycleOwner(), listadoJugadoresAlineacionObserver);
        final Observer<Alineacion> alineacionActualizadaObserver = new Observer<Alineacion>() {
            @Override
            public void onChanged(Alineacion alineacionActualizada) {
                if(alineacionActualizada != null) {
                    byte valMedia = alineacionActualizada.getValoracionMedia();
                    alineacion.setValoracionMedia(valMedia);
                    alineacionAux.setValoracionMedia(valMedia);
                    tevValoracionAlineacion.setText(String.valueOf(valMedia));
                }
            }
        };
        DetalleAlineacionVM.getAlineacionActualizada().observe(getViewLifecycleOwner(), alineacionActualizadaObserver);
        final Observer<String> usuarioCompartidoObserver = new Observer<String>() {
            @Override
            public void onChanged(String nickUsuario) {
                if(nickUsuario != null) {
                    usuarioCompartido = nickUsuario;
                    if(!alineacion.getFinalizada()) {
                        alineacionAux.setFinalizada(true);
                        detalleAlineacionVM.updateAlineacionVM(alineacionAux);
                        peticionModificacion = 0;
                    } else {
                        detalleAlineacionVM.insertValoracionVM(usuarioCompartido, alineacion.getId());
                        peticionModificacion = 5;
                    }
                    shareDialog.dismiss();
                } else {
                    Toast.makeText(context, R.string.user_toast, Toast.LENGTH_LONG).show();
                }
            }
        };
        DetalleAlineacionVM.getNickUsuarioCompartido().observe(getViewLifecycleOwner(), usuarioCompartidoObserver);
    }

    @Override
    public void onClick(View v) {
        int vistaSeleccionada = v.getId();
        switch (vistaSeleccionada) {
            case R.id.btn_eliminar_alineacion:
                deleteDialog.show();
                break;
            case R.id.btn_compartir_alineacion:
                int numJugadores = getListadoJugadoresActualesAlineacion().size();
                if(numJugadores == 5) {
                    shareDialog.show();
                } else {
                    Toast.makeText(context, R.string.failed_share_toast, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.img1: case R.id.img2: case R.id.img3: case R.id.img4: case R.id.img5:
                EnumPosiciones posicion = null;
                imgSeleccionada = (ImageButton) v;
                switch (vistaSeleccionada) {
                    case R.id.img1:
                        posicion = EnumPosiciones.BASE;
                        break;
                    case R.id.img2:
                        posicion = EnumPosiciones.ESCOLTA;
                        break;
                    case R.id.img3:
                        posicion = EnumPosiciones.ALERO;
                        break;
                    case R.id.img4:
                        posicion = EnumPosiciones.ALA_PIVOT;
                        break;
                    case R.id.img5:
                        posicion = EnumPosiciones.PIVOT;
                        break;
                }
                String nickUsuario = DetalleAlineacionVM.getUsuarioActivo().getValue().getNick();
                detalleAlineacionVM.getListadoJugadoresClubUsuarioPorPosicionVM(nickUsuario, posicion);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String sistema = actvSistemaAlineacion.getText().toString();
        alineacionAux.setSistema(EnumSistemas.valueOf(sistema));
        detalleAlineacionVM.updateAlineacionVM(alineacionAux);
        peticionModificacion = 0;
        sistemaSeleccionado = position;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(deleteDialog != null) {
            deleteDialog.dismiss();
        }
        if(jugadorDialog != null) {
            jugadorDialog.dismiss();
        }
        if(shareDialog != null) {
            shareDialog.dismiss();
        }
    }

    //Métodos añadidos

    /* Estudio Interfaz
        Prototipo: public void establecerSistemaGrafico(int sistemaSeleccionado)
        Propósito: establecer la distribución adecuada de las imágenes respecto al sistema que se encuentra seleccionado en ese momento,
        el cual es pasado como parámetro.
        Precondiciones: el sistema seleccionado debe ser un valor del rango entre 0 y 4 (ambos incluidos).
        Entradas: el sistema seleccionado.
        Salidas: ninguna.
        Postcondiciones: quedan ubicadas las imágenes en pantalla de tal forma que formarán un sistema congruente con el sistema seleccionado en ese momento en la alineación.
     */
    public void establecerSistemaGrafico(int sistemaSeleccionado) {
        GridLayout.LayoutParams lp1 = (GridLayout.LayoutParams) img1.getLayoutParams();
        GridLayout.LayoutParams lp2 = (GridLayout.LayoutParams) img2.getLayoutParams();
        GridLayout.LayoutParams lp3 = (GridLayout.LayoutParams) img3.getLayoutParams();
        GridLayout.LayoutParams lp4 = (GridLayout.LayoutParams) img4.getLayoutParams();
        GridLayout.LayoutParams lp5 = (GridLayout.LayoutParams) img5.getLayoutParams();
        switch (sistemaSeleccionado) {
            case 0:
                lp1.rowSpec = GridLayout.spec(0);
                lp1.columnSpec = GridLayout.spec(0);
                lp2.rowSpec = GridLayout.spec(0);
                lp2.columnSpec = GridLayout.spec(2);
                lp3.rowSpec = GridLayout.spec(1);
                lp3.columnSpec = GridLayout.spec(0);
                lp4.rowSpec = GridLayout.spec(1);
                lp4.columnSpec = GridLayout.spec(2);
                lp5.rowSpec = GridLayout.spec(2);
                lp5.columnSpec = GridLayout.spec(1);
                break;
            case 1:
                lp1.rowSpec = GridLayout.spec(0);
                lp1.columnSpec = GridLayout.spec(1);
                lp2.rowSpec = GridLayout.spec(1);
                lp2.columnSpec = GridLayout.spec(0);
                lp3.rowSpec = GridLayout.spec(1);
                lp3.columnSpec = GridLayout.spec(2);
                lp4.rowSpec = GridLayout.spec(2);
                lp4.columnSpec = GridLayout.spec(0);
                lp5.rowSpec = GridLayout.spec(2);
                lp5.columnSpec = GridLayout.spec(2);
                break;
            case 2:
                lp1.rowSpec = GridLayout.spec(0);
                lp1.columnSpec = GridLayout.spec(0);
                lp2.rowSpec = GridLayout.spec(0);
                lp2.columnSpec = GridLayout.spec(2);
                lp3.rowSpec = GridLayout.spec(1);
                lp3.columnSpec = GridLayout.spec(1);
                lp4.rowSpec = GridLayout.spec(2);
                lp4.columnSpec = GridLayout.spec(0);
                lp5.rowSpec = GridLayout.spec(2);
                lp5.columnSpec = GridLayout.spec(2);
                break;
            case 3:
                lp1.rowSpec = GridLayout.spec(0);
                lp1.columnSpec = GridLayout.spec(1);
                lp2.rowSpec = GridLayout.spec(1);
                lp2.columnSpec = GridLayout.spec(0);
                lp3.rowSpec = GridLayout.spec(1);
                lp3.columnSpec = GridLayout.spec(1);
                lp4.rowSpec = GridLayout.spec(1);
                lp4.columnSpec = GridLayout.spec(2);
                lp5.rowSpec = GridLayout.spec(2);
                lp5.columnSpec = GridLayout.spec(1);
                break;
            case 4:
                lp1.rowSpec = GridLayout.spec(0);
                lp1.columnSpec = GridLayout.spec(0);
                lp2.rowSpec = GridLayout.spec(0);
                lp2.columnSpec = GridLayout.spec(1);
                lp3.rowSpec = GridLayout.spec(0);
                lp3.columnSpec = GridLayout.spec(2);
                lp4.rowSpec = GridLayout.spec(1);
                lp4.columnSpec = GridLayout.spec(1);
                lp5.rowSpec = GridLayout.spec(2);
                lp5.columnSpec = GridLayout.spec(1);
                break;
        }
        img1.setLayoutParams(lp1);
        img2.setLayoutParams(lp2);
        img3.setLayoutParams(lp3);
        img4.setLayoutParams(lp4);
        img5.setLayoutParams(lp5);
    }

    /* Estudio Interfaz
        Prototipo: public AlertDialog crearJugadorDialog()
        Propósito: crear un diálogo emergente para la selección de un jugador dentro de una determinada alineación.
        Precondiciones: ninguna.
        Entradas: ninguna.
        Salidas: el objeto de tipo AlertDialog que representa el diálogo para la selección de un jugador.
        Postcondiciones: se crea el diálogo con las características correspondientes y se devuelve el objeto AlertDialog asociado al nombre de la función.
    */
    public AlertDialog crearJugadorDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle(R.string.jugador_dialog_title);
        builder.setMessage(R.string.jugador_dialog_msg);
        dialogLayout = requireActivity().getLayoutInflater().inflate(R.layout.dialog_elegir_jugador, null);
        builder.setView(dialogLayout);
        builder.setCancelable(false);
        builder.setNegativeButton(R.string.btn_cancelar_dialog, null);
        builder.setNeutralButton(R.string.btn_jugador_dialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                detalleAlineacionVM.deleteJugadorAlineacionVM(((Jugador) imgSeleccionada.getTag()).getId(), alineacion.getId());
                peticionModificacion = 4;
            }});
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button btnVaciar = dialog.getButton(AlertDialog.BUTTON_NEUTRAL);
                if (imgSeleccionada.getTag() == null) {
                    btnVaciar.setEnabled(false);
                } else {
                    btnVaciar.setEnabled(true);
                }
            }
        });
        return dialog;
    }

    /* Estudio Interfaz
        Prototipo: public void mostrarDialogElegirJugador(ArrayList<Jugador> listadoJugadores)
        Propósito: completar y mostrar el diálogo correspondiente a la selección de un jugador dentro de una determinada alineación.
        Precondiciones: el listado de jugadores debe ser distinto de null.
        Entradas: el listado de jugadores alternativos.
        Salidas: ninguna.
        Postcondiciones: se muestra el diálogo con el listado de posibles jugadores a seleccionar.
    */
    public void mostrarDialogElegirJugador(ArrayList<Jugador> listadoJugadores) {
        ImageView imgJugadorActual = dialogLayout.findViewById(R.id.img_jugador_actual);
        MaterialTextView tevNombreJugadorActual = dialogLayout.findViewById(R.id.tev_nombre_jugador_actual);
        MaterialTextView tevPosicionJugadorActual = dialogLayout.findViewById(R.id.tev_posicion_jugador_actual);
        MaterialTextView tevValoracionJugadorActual = dialogLayout.findViewById(R.id.tev_valoracion_jugador_actual);
        RecyclerView rvJugadores = dialogLayout.findViewById(R.id.rev_jugadores);
        if(imgSeleccionada != null) {
            Jugador jugadorActual = (Jugador) imgSeleccionada.getTag();
            if (jugadorActual != null) {
                Glide.with(context).load(jugadorActual.getImagen())
                        .error(R.drawable.error)
                        //.placeholder(R.drawable.placeholder)
                        .into(imgJugadorActual);
                tevNombreJugadorActual.setText(jugadorActual.getNombre());
                tevPosicionJugadorActual.setText(jugadorActual.getPosicion().name());
                tevValoracionJugadorActual.setText(String.valueOf(jugadorActual.getValoracionGeneral()));
            } else {
                imgJugadorActual.setImageResource(R.drawable.player_image);
                tevNombreJugadorActual.setText("No existen datos");
                tevPosicionJugadorActual.setText(null);
                tevValoracionJugadorActual.setText(null);
            }
            rvJugadores.setLayoutManager(new LinearLayoutManager(context));
            rvJugadores.setAdapter(new JugadorAlineacionAdapter(listadoJugadores));
            jugadorDialog.show();
        }
    }

    /* Estudio Interfaz
        Prototipo: public void cargarAlineacionInicial(List<Jugador> listadoJugadores)
        Propósito: establecer las imágenes correspondientes a los jugadores en sus respectivas vistas (ImageButton) dentro de la interfaz de usuario.
        Además, se asignará a cada vista un tag con el identificador del jugador que ésta contiene.
        Precondiciones: el listado de jugadores debe ser distinto de null.
        Entradas: el listado de jugadores que contiene la alineación.
        Salidas: ninguna.
        Postcondiciones: quedan establecidas las imágenes en la interfaz de usuario y los identificadores asignados a cada vista.
     */
    public void cargarAlineacionInicial(List<Jugador> listadoJugadores) {
        Jugador jugador;
        ImageButton img = null;
        for(int i=0; i<listadoJugadores.size(); i++) {
            jugador = listadoJugadores.get(i);
            switch (jugador.getPosicion()) {
                case BASE:
                    img = img1;
                    break;
                case ESCOLTA:
                    img = img2;
                    break;
                case ALERO:
                    img = img3;
                    break;
                case ALA_PIVOT:
                    img = img4;
                    break;
                case PIVOT:
                    img = img5;
                    break;
            }
            if(img != null) {
                Glide.with(context).load(jugador.getImagen()).error(R.drawable.error)
                        //.placeholder(R.drawable.placeholder)
                        .into(img);
                img.setTag(jugador);
            }
        }
    }

    /* Estudio Interfaz
        Prototipo: public void inicializarFichaAlineacion()
        Propósito: establecer los valores iniciales para cada uno de los campos que componen la ficha correspondiente a la alineación actual.
        Precondiciones: ninguna.
        Entradas: ninguna.
        Salidas: ninguna.
        Postcondiciones: quedan inicializados los campos con sus respectivos valores iniciales.
     */
    public void inicializarFichaAlineacion() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, EnumSistemas.getListadoValoresString());
        actvSistemaAlineacion.setAdapter(adapter);
        actvSistemaAlineacion.setText(alineacion.getSistema().name(), false);
        tevNombreAlineacion.setText(alineacion.getNombre());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        tevFechaCreacionAlineacion.setText(alineacion.getFechaCreacion().format(formatter));
        tevValoracionAlineacion.setText(String.valueOf(alineacion.getValoracionMedia()));
    }

    /* Estudio Interfaz
        Prototipo: public void inicializarFormularioShare(View dialogLayout)
        Propósito: obtener los elementos(vistas) que componen el formulario utilizado para compartir una alineación.
        Precondiciones: el parámetro "dialogLayout" debe ser distinto de null.
        Entradas: el layout del formulario del que obtener las subvistas correspondientes (campos del formulario).
        Salidas: ninguna.
        Postcondiciones: se obtienen las distintas vistas del formulario y quedan almacenadas para ser utilizadas posteriormente.
     */
    public void inicializarFormularioShare(View dialogLayout) {
        tilNick = dialogLayout.findViewById(R.id.til_nick_share);
        tilEmail = dialogLayout.findViewById(R.id.til_email_share);
        edtNick = dialogLayout.findViewById(R.id.edt_nick_share);
        edtEmail = dialogLayout.findViewById(R.id.edt_email_share);
        radGrpBusqueda = dialogLayout.findViewById(R.id.rad_grp_busqueda);
    }

    /* Estudio Interfaz
        Prototipo: public ArrayList<Jugador> filtrarListadoJugadoresPosicion(ArrayList<Jugador> listadoJugadoresPosicion)
        Propósito: filtrar el listado de jugadores previo a mostrarlo en pantalla en el diálogo correspondiente a elegir jugador.
        Para ello, solamente se incluirán jugadores distintos a los que estén en ese momento en la alineación que se esté tratando.
        Precondiciones: el listado de jugadores recibido debe ser distinto de null.
        Entradas: el listado de jugadores original (antes de ser filtrado).
        Salidas: el nuevo listado de jugadores filtrado.
        Postcondiciones: se devuelve el listado de jugadores filtrado asociado al nombre de la función.
     */
    public ArrayList<Jugador> filtrarListadoJugadoresPosicion(ArrayList<Jugador> listadoJugadoresPosicion) {
        boolean igual;
        String nombreJugadorPosicion, nombreJugadorAlineacion;
        ArrayList<Jugador> listadoJugadoresFiltrado = new ArrayList<>();
        ArrayList<Jugador> listadoJugadoresAlineacion = getListadoJugadoresActualesAlineacion();
        for (int i=0; i<listadoJugadoresPosicion.size(); i++) {
            igual = false;
            for(int j=0; j<listadoJugadoresAlineacion.size() && igual == false; j++) {
                nombreJugadorPosicion = listadoJugadoresPosicion.get(i).getNombre();
                nombreJugadorAlineacion = listadoJugadoresAlineacion.get(j).getNombre();
                if(nombreJugadorPosicion.equals(nombreJugadorAlineacion)) {
                    igual = true;
                }
            }
            if(!igual) {
                listadoJugadoresFiltrado.add(listadoJugadoresPosicion.get(i));
            }
        }
        return listadoJugadoresFiltrado;
    }

    /* Estudio Interfaz
        Prototipo: public ArrayList<Jugador> getListadoJugadoresActualesAlineacion()
        Propósito: obtener el listado con los jugadores que se encuentran actualmente incluidos en la alineación que se está tratando.
        Precondiciones: ninguna.
        Entradas: ninguna.
        Salidas: el listado con los jugadores de la alineación actual.
        Postcondiciones: se devuelve el listado de jugadores asociado al nombre de la función.
     */
    public ArrayList<Jugador> getListadoJugadoresActualesAlineacion() {
        ArrayList<Jugador> jugadoresAlineacion = new ArrayList<>();
        if(img1.getTag() != null) { jugadoresAlineacion.add((Jugador) img1.getTag()); }
        if(img2.getTag() != null) { jugadoresAlineacion.add((Jugador) img2.getTag()); }
        if(img3.getTag() != null) { jugadoresAlineacion.add((Jugador) img3.getTag()); }
        if(img4.getTag() != null) { jugadoresAlineacion.add((Jugador) img4.getTag()); }
        if(img5.getTag() != null) { jugadoresAlineacion.add((Jugador) img5.getTag()); }
        return jugadoresAlineacion;
    }

    /* Estudio Interfaz
       Prototipo: public void asignarComportamientoShareDialog()
       Propósito: establecer un comportamiento determinado al diálogo ShareDialog, con el fin de que sea capaz de dar una respuesta adecuada a las
       interacciones del usuario con éste.
       Precondiciones: ninguna.
       Entradas: ninguna.
       Salidas: ninguna.
       Postcondiciones: queda asignado el comportamiento sobre este diálogo ante las distintas acciones del usuario.
   */
    public void asignarComportamientoShareDialog() {
        shareDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button btnPositive = shareDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button btnNegative = shareDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                radGrpBusqueda.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rad_btn_nick:
                                tilNick.setEnabled(true);
                                tilEmail.setEnabled(false);
                                tilEmail.setErrorEnabled(false);
                                break;
                            case R.id.rad_btn_email:
                                tilNick.setEnabled(false);
                                tilEmail.setEnabled(true);
                                tilNick.setErrorEnabled(false);
                                break;
                        }
                    }
                });
                btnPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String input = null, usuarioActivo = null;
                        boolean valido, metodoEmail = false;
                        int checked = radGrpBusqueda.getCheckedRadioButtonId();
                        switch (checked) {
                            case R.id.rad_btn_nick:
                                input = edtNick.getText().toString();
                                usuarioActivo = DetalleAlineacionVM.getUsuarioActivo().getValue().getNick();
                                break;
                            case R.id.rad_btn_email:
                                metodoEmail = true;
                                input = edtEmail.getText().toString();
                                usuarioActivo = DetalleAlineacionVM.getUsuarioActivo().getValue().getCorreoElectronico();
                                break;
                        }
                        if(!input.equals(usuarioActivo)) {
                            shareValido = true;
                            String error = validarFormularioShare(input, metodoEmail);
                            mostrarErroresShare(error, metodoEmail);
                            if(shareValido) {
                                detalleAlineacionVM.getNickUsuarioVM(input, metodoEmail);
                            }
                        } else {
                            Toast.makeText(context, R.string.user_act_toast, Toast.LENGTH_LONG).show();
                        }
                    }
                });
                btnNegative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        shareDialog.dismiss();
                        tilNick.setErrorEnabled(false);
                        tilEmail.setErrorEnabled(false);
                        vaciarCamposFormulario();
                    }
                });
            }
        });
    }

    /* Estudio Interfaz
        Prototipo: public String validarFormularioShare(String identificador)
        Propósito: comprobar y validar la entrada correspondiente al identificador del usuario (nick o email)
        en el formulario para compartir una alineación.
        Precondiciones: ninguna.
        Entradas: el identificador del usuario, bien sea el nick o el email.
        Salidas: un String que contenga el error a mostrar, o bien "null" en caso de que cumpla con las restricciones.
        Postcondiciones: se devuelve el String asociado al nombre de la función.
     */
    public String validarFormularioShare(String identificador, boolean metodoEmail) {
        String error = null;
        if(!metodoEmail) {
            if(identificador.isEmpty() || identificador == null || identificador.length() < 3 || identificador.length() > 20) {
                error = getString(R.string.err_nick);
                shareValido = false;
            }
        } else {
            if(identificador.isEmpty() || identificador == null || identificador.length() > 45 || !Patterns.EMAIL_ADDRESS.matcher(identificador).matches()) {
                error = getString(R.string.err_email);
                shareValido = false;
            }
        }
        return error;
    }

    /* Estudio Interfaz
        Prototipo: public void mostrarErroresShare(String error, boolean metodoEmail)
        Propósito: mostrar el mensaje de error correspondiente junto al campo de texto asociado en el formulario de compartir alineación,
        o bien, ocultar tales mensajes en caso de que se cumplan las restrcciones.
        Precondiciones: ninguna.
        Entradas: el error a mostrar y un booleano que indique el campo donde se desea mostrar el error (campo nick/email).
        Salidas: ninguna.
        Postcondiciones: se muestra en pantalla el error junto al campo correspondiente.
     */
    public void mostrarErroresShare(String error, boolean metodoEmail) {
        if(!metodoEmail) {
            tilNick.setError(error);
            tilNick.setErrorEnabled(error != null);
        } else {
            tilEmail.setError(error);
            tilEmail.setErrorEnabled(error != null);
        }
    }

    /* Estudio Interfaz
        Prototipo: public void vaciarCampoTexto()
        Propósito: vaciar los campos del formulario de compartir una alineación y retirar el foco sobre ellos.
        Precondiciones: ninguna.
        Entradas: ninguna.
        Salidas: ninguna.
        Postcondiciones: los campos del formulario quedan vacíos de contenido y libres de foco.
     */
    public void vaciarCamposFormulario() {
        edtNick.getText().clear();
        edtNick.clearFocus();
        edtEmail.getText().clear();
        edtEmail.clearFocus();
    }

    //JugadorAlineacionAdapter
    //Adaptador para el listado de jugadores que se muestra en el diálogo de elegir jugador.
    public class JugadorAlineacionAdapter extends RecyclerView.Adapter<JugadorAlineacionAdapter.ViewHolder> {

        private ArrayList<Jugador> localDataSet;

        public JugadorAlineacionAdapter(ArrayList<Jugador> dataSet) {
            this.localDataSet = new ArrayList<>(dataSet);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_dialog_elegir_jugador_item, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Jugador jugador = localDataSet.get(position);
            holder.getTevNombre().setText(jugador.getNombre());
            holder.getTevPosicion().setText(jugador.getPosicion().name());
            holder.getTevValoracion().setText(String.valueOf(jugador.getValoracionGeneral()));
            ImageView imageView = holder.getImgJugador();
            Glide.with(context).load(jugador.getImagen())
                    .error(R.drawable.error)
                    //.placeholder(R.drawable.placeholder)
                    .into(imageView);
        }

        @Override
        public int getItemCount() {
            return localDataSet.size();
        }

        //ViewHolder
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private final TextView tevNombre;
            private final TextView tevPosicion;
            private final TextView tevValoracion;
            private final ImageView imgJugador;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tevNombre = itemView.findViewById(R.id.tev_nombre_jugador_alineacion_item);
                tevPosicion = itemView.findViewById(R.id.tev_posicion_jugador_alineacion_item);
                tevValoracion = itemView.findViewById(R.id.tev_valoracion_jugador_alineacion_item);
                imgJugador = itemView.findViewById(R.id.img_jugador_alineacion_item);
                itemView.setOnClickListener(this);
            }

            public TextView getTevNombre() {
                return tevNombre;
            }

            public TextView getTevPosicion() {return tevPosicion; }

            public TextView getTevValoracion() {
                return tevValoracion;
            }

            public ImageView getImgJugador() {
                return imgJugador;
            }

            @Override
            public void onClick(View v) {
                JugadorAlineacion jugadorAlineacion;
                int position = getAbsoluteAdapterPosition();
                jugadorSeleccionado = localDataSet.get(position);
                if(imgSeleccionada.getTag() == null) {
                    jugadorAlineacion = new JugadorAlineacion(jugadorSeleccionado.getId(), alineacion.getId());
                    detalleAlineacionVM.insertJugadorAlineacionVM(jugadorAlineacion);
                    peticionModificacion = 2;
                } else {
                    jugadorAlineacion = new JugadorAlineacion(((Jugador) imgSeleccionada.getTag()).getId(), alineacion.getId());
                    detalleAlineacionVM.updateJugadorAlineacionVM(jugadorAlineacion, jugadorSeleccionado.getId());
                    peticionModificacion = 3;
                }
                jugadorDialog.dismiss();
            }

        }

    }

}