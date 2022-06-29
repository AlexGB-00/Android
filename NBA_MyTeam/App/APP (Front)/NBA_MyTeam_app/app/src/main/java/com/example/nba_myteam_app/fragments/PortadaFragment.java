package com.example.nba_myteam_app.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nba_myteam_app.R;
import com.example.nba_myteam_app.activities.HomeActivity;
import com.example.nba_myteam_app.activities.InicioActivity;
import com.example.nba_myteam_app.handlers.SesionHandler;
import com.example.nba_myteam_app.retrofit.entities.Usuario;
import com.example.nba_myteam_app.view_models.LogInVM;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PortadaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PortadaFragment extends Fragment implements View.OnClickListener {

    //Atributos
    private Button btnPortada;
    private LogInVM logInVM;
    private Context context;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PortadaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PortadaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PortadaFragment newInstance(String param1, String param2) {
        PortadaFragment fragment = new PortadaFragment();
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
        View v = inflater.inflate(R.layout.fragment_portada, container, false);
        btnPortada = v.findViewById(R.id.btn_portada);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Se obtiene el contexto
        context = getActivity();
        //Se obtiene el ViewModel
        logInVM = new ViewModelProvider(requireActivity()).get(LogInVM.class);
        //Se establecen los listeners
        btnPortada.setOnClickListener(this);
        //Se definen los Observers
        final Observer<Usuario> usuarioSesionObserver = new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuarioObtenido) {
                Intent intent;
                if(usuarioObtenido != null) {
                    intent = new Intent(context, HomeActivity.class);
                } else {
                    intent = new Intent(context, InicioActivity.class);
                }
                startActivity(intent);
            }
        };
        LogInVM.getUsuarioActivo().observe(getViewLifecycleOwner(), usuarioSesionObserver);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_portada) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("fichero_sesion", Context.MODE_PRIVATE);
            SesionHandler.setSharedPrefSesion(sharedPreferences);
            Usuario usuarioActivo = SesionHandler.obtenerSesionActiva();
            if(usuarioActivo == null) {
                Intent intent = new Intent(context, InicioActivity.class);
                startActivity(intent);
            } else {
                logInVM.comprobarUsuarioExistenteVM(usuarioActivo.getNick(), usuarioActivo.getContrasenha(), false);
            }
        }
    }

}