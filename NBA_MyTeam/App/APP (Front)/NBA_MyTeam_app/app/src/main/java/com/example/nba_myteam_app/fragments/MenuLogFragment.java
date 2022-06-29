package com.example.nba_myteam_app.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nba_myteam_app.R;
import com.example.nba_myteam_app.activities.InicioActivity;
import com.example.nba_myteam_app.activities.LogInActivity;
import com.example.nba_myteam_app.activities.RegistroActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuLogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuLogFragment extends Fragment implements View.OnClickListener {

    //Atributos
    private Button btnLogin, btnReg;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuLogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuLogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuLogFragment newInstance(String param1, String param2) {
        MenuLogFragment fragment = new MenuLogFragment();
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
        View v = inflater.inflate(R.layout.fragment_menu_log, container, false);
        btnLogin = v.findViewById(R.id.btn_login_menu_log);
        btnReg = v.findViewById(R.id.btn_reg_menu_log);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Se establecen los listeners
        btnLogin.setOnClickListener(this);
        btnReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        Context context = getActivity().getApplicationContext();
        switch (v.getId()) {
            case R.id.btn_reg_menu_log:
                //Lanzamos un intent a la siguiente actividad (RegistroActivity)
                intent = new Intent(context, RegistroActivity.class);
                break;
            case R.id.btn_login_menu_log:
                //Lanzamos un intent a la siguiente actividad (LogInActivity)
                intent = new Intent(context, LogInActivity.class);
                break;
        }
        startActivity(intent);
    }

}