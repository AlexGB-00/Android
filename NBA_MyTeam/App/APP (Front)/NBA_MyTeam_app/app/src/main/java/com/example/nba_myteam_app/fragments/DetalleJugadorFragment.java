package com.example.nba_myteam_app.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.nba_myteam_app.R;
import com.example.nba_myteam_app.retrofit.entities.Equipo;
import com.example.nba_myteam_app.retrofit.entities.Jugador;
import com.example.nba_myteam_app.view_models.DetalleJugadorVM;
import com.google.android.material.textview.MaterialTextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleJugadorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleJugadorFragment extends Fragment {

    //Atributos
    private ImageView imgJugador;
    private MaterialTextView tevNombre, tevEdad, tevNacionalidad, tevAltura, tevPeso, tevDorsal, tevPosicion, tevValoracion, tevEquipo;
    private DetalleJugadorVM detalleJugadorVM;
    private Jugador jugador;
    private Context context;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetalleJugadorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleJugadorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleJugadorFragment newInstance(String param1, String param2) {
        DetalleJugadorFragment fragment = new DetalleJugadorFragment();
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
        View v = inflater.inflate(R.layout.fragment_detalle_jugador, container, false);
        imgJugador = v.findViewById(R.id.img_jugador);
        tevNombre = v.findViewById(R.id.tev_valor_nombre);
        tevEdad = v.findViewById(R.id.tev_valor_edad);
        tevNacionalidad = v.findViewById(R.id.tev_valor_nacionalidad);
        tevAltura = v.findViewById(R.id.tev_valor_altura);
        tevPeso = v.findViewById(R.id.tev_valor_peso);
        tevDorsal = v.findViewById(R.id.tev_valor_dorsal);
        tevPosicion = v.findViewById(R.id.tev_valor_posicion);
        tevValoracion = v.findViewById(R.id.tev_valor_valoracion);
        tevEquipo = v.findViewById(R.id.tev_valor_equipo);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Se obtiene el contexto
        context = getActivity();
        //Se recoge el jugador seleccionado
        jugador = (Jugador) getActivity().getIntent().getExtras().getSerializable("jugador");
        //Se obtiene el ViewModel
        detalleJugadorVM = new ViewModelProvider(this).get(DetalleJugadorVM.class);
        //Se obtiene el equipo al que pertenece el jugador
        detalleJugadorVM.getEquipoVM(jugador.getIdEquipo());
        //Se definen los Observers
        final Observer<Equipo> equipoJugadorObserver = new Observer<Equipo>() {
            @Override
            public void onChanged(Equipo equipoJugador) {
                if(equipoJugador != null) {
                    tevNombre.setText(jugador.getNombre());
                    tevEdad.setText(String.valueOf(jugador.getEdad()));
                    tevNacionalidad.setText(jugador.getNacionalidad());
                    tevAltura.setText(String.valueOf(jugador.getAltura()));
                    tevPeso.setText(String.valueOf(jugador.getPeso()));
                    tevDorsal.setText(String.valueOf(jugador.getDorsal()));
                    tevPosicion.setText(jugador.getPosicion().toString());
                    tevValoracion.setText(String.valueOf(jugador.getValoracionGeneral()));
                    tevEquipo.setText(equipoJugador.getNombre());
                    Glide.with(context).load(jugador.getImagen())
                            .error(R.drawable.error)
                            //.placeholder(R.drawable.placeholder)
                            .into(imgJugador);
                }
            }
        };
        DetalleJugadorVM.getEquipoJugador().observe(getViewLifecycleOwner(), equipoJugadorObserver);
    }

}