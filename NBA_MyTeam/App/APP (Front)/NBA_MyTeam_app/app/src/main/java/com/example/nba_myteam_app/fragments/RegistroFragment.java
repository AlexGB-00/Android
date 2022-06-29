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
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.nba_myteam_app.R;
import com.example.nba_myteam_app.activities.HomeActivity;
import com.example.nba_myteam_app.activities.InicioActivity;
import com.example.nba_myteam_app.activities.LogInActivity;
import com.example.nba_myteam_app.retrofit.entities.Jugador;
import com.example.nba_myteam_app.retrofit.entities.Usuario;
import com.example.nba_myteam_app.view_models.DetalleAlineacionVM;
import com.example.nba_myteam_app.view_models.LogInVM;
import com.example.nba_myteam_app.view_models.RegistroVM;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroFragment extends Fragment implements View.OnClickListener {

    //Atributos
    private TextInputEditText edtNick, edtEmail, edtPass, edtPass2, edtNombre;
    private TextInputLayout tilNick, tilEmail, tilPass, tilPass2, tilNombre;
    private MaterialCheckBox ckbReg;
    private MaterialButton btnReg;
    private boolean registroValido;
    private RegistroVM registroVM;
    private Context context;
    private boolean emailComprobado;
    private String[] datosUsuario;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegistroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistroFragment newInstance(String param1, String param2) {
        RegistroFragment fragment = new RegistroFragment();
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
        View v = inflater.inflate(R.layout.fragment_registro, container, false);
        edtNick = v.findViewById(R.id.edt_nick_reg);
        edtEmail = v.findViewById(R.id.edt_email_reg);
        edtPass = v.findViewById(R.id.edt_pass_reg);
        edtPass2 = v.findViewById(R.id.edt_pass2_reg);
        edtNombre = v.findViewById(R.id.edt_nombre_reg);
        tilNick = v.findViewById(R.id.til_nick_reg);
        tilEmail = v.findViewById(R.id.til_email_reg);
        tilPass = v.findViewById(R.id.til_pass_reg);
        tilPass2 = v.findViewById(R.id.til_pass2_reg);
        tilNombre = v.findViewById(R.id.til_nombre_reg);
        ckbReg = v.findViewById(R.id.ckb_reg);
        btnReg = v.findViewById(R.id.btn_reg);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Se obtiene el contexto
        context = getActivity();
        //Se obtiene el ViewModel
        registroVM = new ViewModelProvider(this).get(RegistroVM.class);
        //Se actualiza la variable que indica la validez del formulario a true
        registroValido = true;
        //Se actualiza la variable que indica si el campo "email" se ha comprobado a false
        emailComprobado = false;
        //Se establecen los listeners
        btnReg.setOnClickListener(this);
        //Se definen los Observers
        final Observer<Integer> filasAfectadasObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer filasAfectadas) {
                if(filasAfectadas > 0) {
                    Toast.makeText(context, R.string.user_post, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, LogInActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    Toast.makeText(context, R.string.err_pet, Toast.LENGTH_LONG).show();
                }
            }
        };
        RegistroVM.getFilasAfectadas().observe(getViewLifecycleOwner(), filasAfectadasObserver);
        final Observer<String> usuarioEncontradoObserver = new Observer<String>() {
            @Override
            public void onChanged(String usuario) {
                if(emailComprobado) {
                    if(usuario == null) {
                        Usuario nuevoUsuario = new Usuario(datosUsuario[0], datosUsuario[1], datosUsuario[2], datosUsuario[4], "");
                        registroVM.insertUsuarioVM(nuevoUsuario);
                    } else {
                        Toast.makeText(context, R.string.err_email_rep, Toast.LENGTH_LONG).show();
                        emailComprobado = false;
                    }
                } else {
                    if(usuario == null) {
                        registroVM.getNickUsuarioVM(datosUsuario[1], true);
                        emailComprobado = true;
                    } else {
                        Toast.makeText(context, R.string.err_nick_rep, Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
        RegistroVM.getUsuarioRepetido().observe(getViewLifecycleOwner(), usuarioEncontradoObserver);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_reg) {
            datosUsuario = cargarDatosUsuario();
            registroValido = true;
            String[] errores = validarFormularioRegistro(datosUsuario);
            TextInputLayout[] arrayTil = cargarArrayTil();
            mostrarErroresRegistro(errores, arrayTil);
            if(registroValido) {
                if (datosUsuario[2].equals(datosUsuario[3])) {
                    if(ckbReg.isChecked()) {
                        registroVM.getNickUsuarioVM(datosUsuario[0], false);
                    } else {
                        Toast.makeText(context, R.string.err_ckb_reg, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context, R.string.err_dif_pass, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    //Métodos añadidos

    /* Estudio Interfaz
        Prototipo: public String[] cargarDatosUsuario()
        Propósito: cargar en un array los distintos inputs del formulario de registro.
        Precondiciones: ninguna.
        Entradas: ninguna.
        Salidas: el array de String con los inputs correspondientes.
        Postcondiciones: se devuelve el array asociado al nombre de la función.
     */
    public String[] cargarDatosUsuario() {
        String[] datosUsuario = new String[5];
        datosUsuario[0] = edtNick.getText().toString();
        datosUsuario[1] = edtEmail.getText().toString();
        datosUsuario[2] = edtPass.getText().toString();
        datosUsuario[3] = edtPass2.getText().toString();
        datosUsuario[4] = edtNombre.getText().toString();
        return datosUsuario;
    }

    /* Estudio Interfaz
        Prototipo: public String[] validarFormularioRegistro(String[] datosUsuario)
        Propósito: comprobar y validar las entradas correspondientes a los datos del usuario introducidos en el formulario de registro.
        Precondiciones: el array "datosUsuario" debe ser distinto de null.
        Entradas: el array que contiene los datos del usuario a validar.
        Salidas: un array de String con los errores correspondientes a cada dato del usuario introducido.
        Postcondiciones: quedan validadas las entradas del formulario de registro y se devuelve el array con los errores asociado al nombre de la función.
     */
    public String[] validarFormularioRegistro(String[] datosUsuario) {
        String[] errores = new String[5];
        String nick = datosUsuario[0], email = datosUsuario[1], pass = datosUsuario[2], pass2 = datosUsuario[3], nombre = datosUsuario[4];
        if(nick.isEmpty() || nick == null || nick.length() < 3 || nick.length() > 20) {
            errores[0] = getString(R.string.err_nick);
            registroValido = false;
        }
        if(email.isEmpty() || email == null || email.length() > 45 || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errores[1] = getString(R.string.err_email);
            registroValido = false;
        }
        if(pass.isEmpty() || pass == null || pass.length() < 8 || pass.length() > 32) {
            errores[2] = getString(R.string.err_pass);
            registroValido = false;
        }
        if(pass2.isEmpty() || pass2 == null || pass2.length() < 8 || pass2.length() > 32) {
            errores[3] = getString(R.string.err_pass);
            registroValido = false;
        }
        if(!nombre.isEmpty() && nombre != null && nombre.length() > 40) {
            errores[4] = getString(R.string.err_nombre);
            registroValido = false;
        }
        return errores;
    }

    /* Estudio Interfaz
        Prototipo: public TextInputLayout[] cargarArrayTil()
        Propósito: cargar en un array los "TextInputLayout" correspondientes a cada campo de texto del formulario de registro.
        Precondiciones: ninguna.
        Entradas: ninguna.
        Salidas: el array cargado correspondiente.
        Postcondiciones: se devuelve el array asociado al nombre de la función.
     */
    public TextInputLayout[] cargarArrayTil() {
        TextInputLayout[] arrayTil = new TextInputLayout[5];
        arrayTil[0] = tilNick;
        arrayTil[1] = tilEmail;
        arrayTil[2] = tilPass;
        arrayTil[3] = tilPass2;
        arrayTil[4] = tilNombre;
        return arrayTil;
    }

    /* Estudio Interfaz
        Prototipo: public void mostrarErroresRegistro(String[] errores, TextInputLayout[] arrayTil)
        Propósito: mostrar los errores correspondientes a cada entrada junto al campo de texto asociado en el formulario de registro,
        o bien, ocultar tales mensajes en caso de que se cumplan las restrcciones.
        Precondiciones: por un lado, el array de String debe ser distinto de null, y por otro lado, el array de "TextInputLayout"
        debe ser distinto de null y además no debe estar vacío.
        Entradas: el array que contiene los errores a mostrar y el array que contiene los campos de texto donde mostrar el mensaje.
        Salidas: ninguna.
        Postcondiciones: se muestran los errores junto a sus respectivos campos.
     */
    public void mostrarErroresRegistro(String[] errores, TextInputLayout[] arrayTil) {
        for(int i=0; i<errores.length; i++) {
            arrayTil[i].setError(errores[i]);
            arrayTil[i].setErrorEnabled(errores[i] != null);
        }
    }

}




