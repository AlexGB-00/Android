package com.example.nba_myteam_app.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.nba_myteam_app.R;
import com.example.nba_myteam_app.retrofit.entities.Jugador;
import com.example.nba_myteam_app.retrofit.entities.JugadorClub;
import com.example.nba_myteam_app.view_models.SobresVM;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textview.MaterialTextView;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SobresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SobresFragment extends Fragment implements View.OnClickListener {

    //Atributos
    private ImageView imgBrozeCard, imgSilverCard, imgGoldCard;
    private SobresVM sobresVM;
    private AlertDialog sobreDialog, timerDialog;
    private View dialogLayoutSobres, dialogLayoutTimer;
    private Jugador jugadorObtenido;
    private Context context;
    private RelativeLayout linRootSobres;
    private LocalDateTime ultFechaInc;
    private ImageButton btnTimer;
    private Timer timer;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SobresFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SobresFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SobresFragment newInstance(String param1, String param2) {
        SobresFragment fragment = new SobresFragment();
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
        View v = inflater.inflate(R.layout.fragment_sobres, container, false);
        imgBrozeCard = v.findViewById(R.id.img_bronze_card);
        imgSilverCard = v.findViewById(R.id.img_silver_card);
        imgGoldCard = v.findViewById(R.id.img_gold_card);
        linRootSobres = v.findViewById(R.id.lin_root_sobres);
        btnTimer = v.findViewById(R.id.btn_timer);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Se obtiene el contexto
        context = getActivity();
        //Se establecen los listeners
        imgBrozeCard.setOnClickListener(this);
        imgSilverCard.setOnClickListener(this);
        imgGoldCard.setOnClickListener(this);
        btnTimer.setOnClickListener(this);
        //Se obtiene el ViewModel
        sobresVM = new ViewModelProvider(this).get(SobresVM.class);
        //Se obtiene la última fecha de apertura de un sobre del usuario
        sobresVM.getUltimaFechaIncorporacionUsuarioVM(SobresVM.getUsuarioActivo().getValue().getNick());
        //Se construyen los diálogos para la apertura del sobre y para mostrar el contador de tiempo
        dialogLayoutSobres = requireActivity().getLayoutInflater().inflate(R.layout.dialog_sobre, null);
        dialogLayoutTimer = requireActivity().getLayoutInflater().inflate(R.layout.dialog_timer, null);
        sobreDialog = crearAlertDialogSobres();
        timerDialog = crearAlertDialogTimer();
        //Se definen los Observers
        final Observer<List<Jugador>> listadoJugadoresObserver = new Observer<List<Jugador>>() {
            @Override
            public void onChanged(List<Jugador> listadoJugadores) {
                if(listadoJugadores != null && listadoJugadores.size() > 0) {
                    ArrayList<Jugador> listadoJugadoresValoracion = new ArrayList<>(listadoJugadores);
                    abrirSobre(listadoJugadoresValoracion);
                }
            }
        };
        SobresVM.getListadoJugadores().observe(getViewLifecycleOwner(), listadoJugadoresObserver);
        final Observer<Integer> filasAfectadasObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer filasAfectadas) {
                if(filasAfectadas == 1) {
                    Toast.makeText(context, "Jugador añadido", Toast.LENGTH_SHORT).show();
                    sobresVM.getUltimaFechaIncorporacionUsuarioVM(SobresVM.getUsuarioActivo().getValue().getNick());
                }
            }
        };
        SobresVM.getFilasAfectadas().observe(getViewLifecycleOwner(), filasAfectadasObserver);
        final Observer<LocalDateTime> fechaIncorporacionObserver = new Observer<LocalDateTime>() {
            @Override
            public void onChanged(LocalDateTime fechaIncorporacion) {
                ultFechaInc = fechaIncorporacion;
                if(ultFechaInc == null) {
                    btnTimer.setEnabled(false);
                } else {
                    btnTimer.setEnabled(true);
                }
            }
        };
        SobresVM.getUltimafechaIncorporacion().observe(getViewLifecycleOwner(), fechaIncorporacionObserver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(sobreDialog != null) {
            sobreDialog.dismiss();
        }
        if(timerDialog != null) {
            timerDialog.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        boolean valido = false;
        int valoracionMin = 0;

        if(v.getId() == R.id.img_bronze_card || v.getId() == R.id.img_silver_card || v.getId() == R.id.img_gold_card) {
            switch (v.getId()) {
                case R.id.img_bronze_card:
                    valoracionMin = 70;
                    break;
                case R.id.img_silver_card:
                    valoracionMin = 80;
                    break;
                case R.id.img_gold_card:
                    valoracionMin = 85;
                    break;
            }
            if(ultFechaInc != null) {
                long horas  = ChronoUnit.HOURS.between(ultFechaInc, LocalDateTime.now());
                valido = comprobarAbrirSobre(horas, valoracionMin);
            }
            if(ultFechaInc == null || valido) {
                sobresVM.getListadoJugadoresValoracionVM(valoracionMin, 99);
            } else {
                Toast.makeText(context, R.string.sobres_toast_msg, Toast.LENGTH_LONG).show();
            }
        } else if(v.getId() == R.id.btn_timer) {
            mostrarDialogTimer();
        }
    }

    //Métodos añadidos

    /* Estudio Interfaz
        Prototipo: public void abrirSobre(ArrayList<Jugador> listadoJugadores)
        Propósito: mostrar un diálogo en el que aparezca, de forma aleatoria, el jugador obtenido al abrir el sobre.
        Además, previamente se añadirá el jugador obtenido al club de dicho usuario.
        Precondiciones: el listado debe ser distinto de null y su tamaño debe ser mayor que 0.
        Entradas: el listado de jugadores posibles.
        Salidas: ninguna.
        Postcondiciones: se muestra en pantalla un diálogo con los datos del jugador obtenido.
    */
    public void abrirSobre(ArrayList<Jugador> listadoJugadores) {
        Random rd = new Random();
        int numRandom = rd.nextInt(listadoJugadores.size());
        String nickUsuario = SobresVM.getUsuarioActivo().getValue().getNick();
        LocalDateTime fechaActual = LocalDateTime.now();
        jugadorObtenido = listadoJugadores.get(numRandom);
        sobresVM.insertJugadorClubVM(new JugadorClub(jugadorObtenido.getId(), nickUsuario, fechaActual));
        mostrarDialogSobre(jugadorObtenido.getNombre(), jugadorObtenido.getImagen(), jugadorObtenido.getValoracionGeneral());
    }

    /* Estudio Interfaz
        Prototipo: public void mostrarDialogSobre(String nombreJugador, String urlImgJugador, int valoracion)
        Propósito: completar y mostrar el diálogo correspondiente a la apertura del sobre con los datos recibidos por parámetro.
        Precondiciones: tanto el nombre del jugador como la url correspondiente a la imagen, deben ser distintos de null y no deben estar vacías. La valoración
        debe estar entre 70 y 99 (ambos incluidos).
        Entradas: el nombre del jugador, la url de la imagen y la valoración.
        Salidas: ninguna.
        Postcondiciones: se muestra el diálogo con los datos del jugador obtenido.
    */
    public void mostrarDialogSobre(String nombreJugador, String urlImgJugador, int valoracion) {
        TextView tevNombreJugador = dialogLayoutSobres.findViewById(R.id.tev_nombre_dialog_sobre);
        ImageView imgJugador = dialogLayoutSobres.findViewById(R.id.img_dialog_sobre);
        TextView tevValoracionJugador = dialogLayoutSobres.findViewById(R.id.tev_valoracion_dialog_sobre);
        tevNombreJugador.setText(nombreJugador);
        Glide.with(context.getApplicationContext()).load(urlImgJugador)
                .error(R.drawable.error)
                .transition(DrawableTransitionOptions.withCrossFade())
                //.placeholder(R.drawable.placeholder)
                .into(imgJugador);
        tevValoracionJugador.setText("Valoración: " + valoracion);
        sobreDialog.show();
    }

    /* Estudio Interfaz
        Prototipo: public void mostrarDialogTimer()
        Propósito: completar y mostrar el diálogo correspondiente al contador del tiempo transcurrido desde la última
        apertura de sobre (contador en tiempo real).
        Precondiciones: ninguna.
        Entradas: ninguna.
        Salidas: ninguna.
        Postcondiciones: se muestra en pantalla el diálogo correspondiente con el contador en tiempo real.
    */
    public void mostrarDialogTimer() {
        MaterialTextView tevContador = dialogLayoutTimer.findViewById(R.id.tev_contador);
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if(getActivity() != null) {
                        getActivity().runOnUiThread(new TimerTask() {
                            @Override
                            public void run() {
                                long tiempoTrans = ChronoUnit.MILLIS.between(ultFechaInc, LocalDateTime.now());
                                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(tiempoTrans),
                                        TimeUnit.MILLISECONDS.toMinutes(tiempoTrans) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(tiempoTrans)),
                                        TimeUnit.MILLISECONDS.toSeconds(tiempoTrans) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(tiempoTrans)));
                                tevContador.setText(hms);
                            }
                        });
                    }
                }
            };
            timer = new Timer();
            timer.scheduleAtFixedRate(timerTask, 0, 1000);
            timerDialog.show();
    }

    /* Estudio Interfaz
        Prototipo: public boolean comprobarAbrirSobre(long horasTranscurridas, int valoracionMin)
        Propósito: comprobar si ha transcurrido el tiempo necesario para abrir un determinado sobre (seleccionado por el usuario).
        (Tiempo mínimo) Sobre bronce --> 8h
                        Sobre plata --> 16h
                        Sobre oro --> 24h
        Precondiciones: las horas transcurridas deben ser un valor mayor o igual que 0 y la valoración mínima debe ser un valor de los siguientes: 70, 80, 85.
        Entradas: el número de horas transcurridas y la valoración mínima que indicará el sobre que ha seleccionado el usuario.
        Salidas: un valor booleano que indique si se dan las condiciones (valido = true) o no (valido = false) para que se pueda abrir el sobre.
        Postcondiciones: se devuelve el valor booleano asociado al nombre de la función.   
     */
    public boolean comprobarAbrirSobre(long horasTranscurridas, int valoracionMin) {
        boolean valido = false;
        if(horasTranscurridas >= 24) {
            valido = true;
        } else if(horasTranscurridas >= 16 && valoracionMin <= 80) {
            valido = true;
        } else if(horasTranscurridas >= 8 && valoracionMin == 70) {
            valido = true;
        }
        return valido;
    }

    /* Estudio Interfaz
        Prototipo: public AlertDialog crearAlertDialogSobres()
        Propósito: crear un diálogo de alerta con la finalidad de que se muestre el jugador generado aleatoriamente tras seleccionar una imagen de las
        que aparecen en la pantalla con forma de sobre.
        Precondiciones: ninguna.
        Entradas: ninguna.
        Salidas: el objeto AlertDialog correspondiente.
        Postcondiciones: se devuelve el objeto AlertDialog asociado al nombre de la función.
     */
    public AlertDialog crearAlertDialogSobres() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle(R.string.sobre_dialog_title);
        builder.setMessage(R.string.sobre_dialog_msg);
        builder.setView(dialogLayoutSobres);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.btn_aceptar_dialog,null);
        return builder.create();
    }

    /* Estudio Interfaz
        Prototipo: public AlertDialog crearAlertDialogTimer()
        Propósito: crear un diálogo de alerta con la finalidad de que se muestre un contador de tiempo que indique el tiempo transcurrido en tiempo real
        desde la última apertura de un sobre por parte de dicho usuario.
        Precondiciones: ninguna.
        Entradas: ninguna.
        Salidas: el objeto AlertDialog correspondiente.
        Postcondiciones: se devuelve el objeto AlertDialog asociado al nombre de la función.
    */
    public AlertDialog crearAlertDialogTimer() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle(R.string.timer_dialog_title);
        builder.setMessage(R.string.timer_dialog_msg);
        builder.setView(dialogLayoutTimer);
        builder.setCancelable(false);
        builder.setNeutralButton(R.string.btn_timer_dialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                timer.cancel();
            }
        });
        return builder.create();
    }

}