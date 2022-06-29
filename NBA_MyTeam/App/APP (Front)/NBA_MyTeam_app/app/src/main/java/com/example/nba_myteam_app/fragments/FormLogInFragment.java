package com.example.nba_myteam_app.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nba_myteam_app.R;
import com.example.nba_myteam_app.activities.HomeActivity;
import com.example.nba_myteam_app.handlers.SesionHandler;
import com.example.nba_myteam_app.retrofit.entities.Usuario;
import com.example.nba_myteam_app.view_models.LogInVM;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FormLogInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormLogInFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    //Atributos
    private RadioGroup rgpMetodo;
    private TextInputLayout tilNick, tilEmail, tilPass;
    private TextInputEditText edtNick, edtEmail, edtPass;
    private Button btnLogin;
    private LogInVM logInVM;
    private boolean loginValido, comprobacionLogin;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FormLogInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FormLogInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FormLogInFragment newInstance(String param1, String param2) {
        FormLogInFragment fragment = new FormLogInFragment();
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
        View v = inflater.inflate(R.layout.fragment_form_log_in, container, false);
        rgpMetodo = v.findViewById(R.id.rgp_met_login);
        tilNick = v.findViewById(R.id.til_nick_login);
        tilEmail = v.findViewById(R.id.til_email_login);
        tilPass = v.findViewById(R.id.til_pass_login);
        edtNick = v.findViewById(R.id.edt_nick_login);
        edtEmail = v.findViewById(R.id.edt_email_login);
        edtPass = v.findViewById(R.id.edt_pass_login);
        btnLogin = v.findViewById(R.id.btn_login);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Se obtiene el ViewModel
        logInVM = new ViewModelProvider(this).get(LogInVM.class);
        //Se actualiza la variable que indica la validez del formulario a true
        loginValido = true;
        //Se actualiza la variable que indica si se ha comprobado el login a false
        comprobacionLogin = false;
        //Se establecen los listeners
        rgpMetodo.setOnCheckedChangeListener(this);
        btnLogin.setOnClickListener(this);
        //Se define los Observers
        final Observer<Usuario> usuarioSesionObserver = new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuarioObtenido) {
                if(comprobacionLogin) {
                    Context context = getActivity();
                    if (usuarioObtenido != null) {
                        SesionHandler.actualizarSesionActiva(usuarioObtenido);
                        Toast.makeText(context, R.string.login_ok, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, HomeActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        Toast.makeText(context, R.string.login_fail, Toast.LENGTH_LONG).show();
                    }
                    comprobacionLogin = false;
                }
            }
        };
        LogInVM.getUsuarioActivo().observe(getViewLifecycleOwner(), usuarioSesionObserver);
    }
  
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbt_nick:
                    tilNick.setEnabled(true);
                    tilEmail.setEnabled(false);
                    tilEmail.setErrorEnabled(false);
                break;
            case R.id.rbt_email:
                    tilNick.setEnabled(false);
                    tilEmail.setEnabled(true);
                    tilNick.setErrorEnabled(false);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int rbSeleccionado = rgpMetodo.getCheckedRadioButtonId();
        String nick = null, email = null, pass = edtPass.getText().toString();
        String[] errores = new String[2];
        boolean metodoCorreo = false;
        loginValido = true;
        switch (rbSeleccionado) {
            case R.id.rbt_nick:
                nick = edtNick.getText().toString();
                metodoCorreo = false;
                errores = validarFormularioLogin(nick, pass, metodoCorreo);
                break;
            case R.id.rbt_email:
                email = edtEmail.getText().toString();
                metodoCorreo = true;
                errores = validarFormularioLogin(email, pass, metodoCorreo);
                break;
        }
        mostrarErroresLogin(errores, metodoCorreo);
        if(loginValido) {
            if(!metodoCorreo) {
                logInVM.comprobarUsuarioExistenteVM(nick, pass, metodoCorreo);
            } else {
                logInVM.comprobarUsuarioExistenteVM(email, pass, metodoCorreo);
            }
            comprobacionLogin = true;
        }
    }

    //Métodos añadidos

    /* Estudio Interfaz
        Prototipo: public String[] validarFormularioLogin(String identificador, String pass, boolean metodoCorreo)
        Propósito: comprobar y validar las entradas correspondientes a los datos del usuario introducidos en el formulario de login.
        Precondiciones: ninguna.
        Entradas: el identificador del usuario (nick o correo electrónico), la contraseña de dicho usuario
        y un booleano que indique el método de login (mediante nick o correo).
        Salidas: un array de String que contenga los errores correspondientes a cada dato del usuario introducido.
        Postcondiciones: quedan validadas las entradas del formulario de login y se devuelve el array con los errores asociado al nombre de la función.
     */
    public String[] validarFormularioLogin(String identificador, String pass, boolean metodoCorreo) {
        String[] errores = new String[2];
        if(!metodoCorreo) {
            if(identificador.isEmpty() || identificador == null || identificador.length() < 3 || identificador.length() > 20) {
                errores[0] = getString(R.string.err_nick);
                loginValido = false;
            }
        } else {
            if(identificador.isEmpty() || identificador == null || identificador.length() > 45 || !Patterns.EMAIL_ADDRESS.matcher(identificador).matches()) {
                errores[0] = getString(R.string.err_email);
                loginValido = false;
            }
        }
        if(pass.isEmpty() || pass == null || pass.length() < 8 || pass.length() > 32) {
            errores[1] = getString(R.string.err_pass);
            loginValido = false;
        }
        return errores;
    }

    /* Estudio Interfaz
        Prototipo: public void mostrarErroresLogin(String[] errores, boolean metodoCorreo)
        Propósito: mostrar los errores correspondientes a cada entrada junto al campo de texto asociado en el formulario de login,
        o bien, ocultar tales mensajes en caso de que se cumplan las restrcciones.
        Precondiciones: el array de String debe ser distinto de null.
        Entradas: el array que contiene los errores a mostrar y un booleano que indica a qué campo corresponde el primer error (nick o correo).
        Salidas: ninguna.
        Postcondiciones: se muestran los errores junto a sus respectivos campos.
     */
    public void mostrarErroresLogin(String[] errores, boolean metodoCorreo) {
        if(!metodoCorreo) {
            tilNick.setError(errores[0]);
            tilNick.setErrorEnabled(errores[0] != null);
        } else {
            tilEmail.setError(errores[0]);
            tilEmail.setErrorEnabled(errores[0] != null);
        }
        tilPass.setError(errores[1]);
        tilPass.setErrorEnabled(errores[1] != null);
    }

}