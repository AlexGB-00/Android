package com.example.nba_myteam_app.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nba_myteam_app.R;
import com.example.nba_myteam_app.activities.AlineacionActivity;
import com.example.nba_myteam_app.retrofit.entities.Alineacion;
import com.example.nba_myteam_app.retrofit.enums.EnumSistemas;
import com.example.nba_myteam_app.utilities.DialogUtility;
import com.example.nba_myteam_app.view_models.AlineacionesVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlineacionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlineacionesFragment extends Fragment implements View.OnClickListener {

    //Atributos
    private RecyclerView revAlineaciones;
    private FloatingActionButton fabAdd;
    private AlineacionesVM alineacionesVM;
    private AlertDialog alineacionDialog;
    private Context context;
    private TextInputEditText edtNombre;
    private AutoCompleteTextView actvSistema;
    private TextInputLayout tilNombre, tilSistema;
    private MaterialTextView tevEmpty;
    private String usuarioActivo;
    private RelativeLayout relDatos;
    private boolean formularioValido;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AlineacionesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlineacionesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlineacionesFragment newInstance(String param1, String param2) {
        AlineacionesFragment fragment = new AlineacionesFragment();
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
        View v = inflater.inflate(R.layout.fragment_alineaciones, container, false);
        revAlineaciones = v.findViewById(R.id.rev_alineaciones);
        fabAdd = v.findViewById(R.id.fab_add);
        tevEmpty = v.findViewById(R.id.tev_empty_alineaciones);
        relDatos = v.findViewById(R.id.rel_datos_alineaciones);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Se obtiene el contexto
        context = getActivity();
        //Se actualiza la variable que indica la validez del formulario a true
        formularioValido = true;
        //Se obtiene el usuario activo
        usuarioActivo = AlineacionesVM.getUsuarioActivo().getValue().getNick();
        //Se obtiene el ViewModel
        alineacionesVM = new ViewModelProvider(this).get(AlineacionesVM.class);
        //Se establecen los listeners
        fabAdd.setOnClickListener(this);
        //Se construye y se establece el comportamiento del AlertDialog para la creación de una nueva alineación
        View dialogLayout = requireActivity().getLayoutInflater().inflate(R.layout.dialog_alineacion, null);
        alineacionDialog = DialogUtility.crearAlertDialog(context, getString(R.string.alineacion_dialog_title), getString(R.string.alineacion_dialog_msg), dialogLayout, null);
        inicializarFormularioAlineacion(dialogLayout);
        asignarComportamientoAlineacionDialog();
        //Se definen los Observers
        final Observer<List<Alineacion>> listadoAlineacionesObserver = new Observer<List<Alineacion>>() {
            @Override
            public void onChanged(List<Alineacion> listadoAlineaciones) {
                if(listadoAlineaciones != null) {
                    tevEmpty.setVisibility(View.GONE);
                    revAlineaciones.setVisibility(View.VISIBLE);
                    relDatos.setVisibility(View.VISIBLE);
                    revAlineaciones.setLayoutManager(new LinearLayoutManager(context));
                    ArrayList<Alineacion> dataSet = ordenarListadoAlineaciones((ArrayList<Alineacion>) listadoAlineaciones);
                    revAlineaciones.setAdapter(new AlineacionesAdapter(dataSet));
                } else {
                    tevEmpty.setVisibility(View.VISIBLE);
                    revAlineaciones.setVisibility(View.GONE);
                    relDatos.setVisibility(View.GONE);
                }
            }
        };
        AlineacionesVM.getListadoAlineaciones().observe(getViewLifecycleOwner(), listadoAlineacionesObserver);
        final Observer<Integer> filasAfectadasObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer filasAfectadas) {
                if(filasAfectadas == 1) {
                    Toast.makeText(getActivity(), "Alineación creada con éxito", Toast.LENGTH_SHORT).show();
                    alineacionesVM.getListadoAlineacionesUsuarioVM(usuarioActivo);
                }
            }
        };
        AlineacionesVM.getFilasAfectadas().observe(getViewLifecycleOwner(), filasAfectadasObserver);
    }

    @Override
    public void onResume() {
        super.onResume();
        alineacionesVM.getListadoAlineacionesUsuarioVM(usuarioActivo);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(alineacionDialog != null) {
            alineacionDialog.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fab_add) {
            alineacionDialog.show();
        }
    }

    //Métodos añadidos

    /* Estudio Interfaz
       Prototipo: public void asignarComportamientoAlineacionDialog()
       Propósito: establecer un comportamiento a cada botón ("Aceptar" y "Cancelar") del diálogo que conforma el formulario de creación de una alineación.
       Precondiciones: ninguna.
       Entradas: ninguna.
       Salidas: ninguna.
       Postcondiciones: se asigna el comportamiento oportuno a cada botón del diálogo para cuando se seleccione alguna de dichas opciones.
   */
    public void asignarComportamientoAlineacionDialog() {
        alineacionDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button btnPositive = alineacionDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button btnNegative = alineacionDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                btnPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        formularioValido = true;
                        String nombre = edtNombre.getText().toString();
                        String sistema = actvSistema.getText().toString();
                        String[] errores = validarFormularioAlineacion(nombre, sistema);
                        mostrarErroresAlineacion(errores);
                        if(formularioValido) {
                            EnumSistemas enumSistema = EnumSistemas.valueOf(sistema);
                            Alineacion nuevaAlineacion = new Alineacion(nombre, enumSistema, (byte) 0, LocalDate.now(), false, usuarioActivo);
                            alineacionesVM.insertAlineacionVM(nuevaAlineacion);
                            alineacionDialog.dismiss();
                            vaciarCamposFormulario();
                        }
                    }
                });
                btnNegative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mostrarErroresAlineacion(new String[]{null, null});
                        vaciarCamposFormulario();
                        alineacionDialog.dismiss();
                    }
                });
            }
        });
    }

    /* Estudio Interfaz
        Prototipo: public String[] validarFormularioAlineacion(String nombre, String sistema)
        Propósito: comprobar y validar las entradas correspondientes a los datos de la alineación
        introducidos en el formulario de creación.
        Precondiciones: ninguna.
        Entradas: el nombre de la alineación y el sistema inicial.
        Salidas: un array de String que contenga los errores respectivos a cada entrada.
        Postcondiciones: se devuelve el array con los errores asociado al nombre de la función.
     */
    public String[] validarFormularioAlineacion(String nombre, String sistema) {
        String[] errores = new String[2];
        if(nombre.isEmpty() || nombre == null || nombre.length() > 20) {
            errores[0] = getString(R.string.err_nombre_alin);
            formularioValido = false;
        }
        if(sistema.isEmpty() || sistema == null) {
            errores[1] = getString(R.string.err_sistema);
            formularioValido = false;
        }
        return errores;
    }

    /* Estudio Interfaz
       Prototipo: public void mostrarErroresAlineacion(String[] errores)
       Propósito: mostrar los mensajes de error correspondientes a cada entrada junto a los campos del formulario,
       o bien, ocultar tales mensajes en caso de que se cumplan las restrcciones.
       Precondiciones: el array debe ser distinto de null.
       Entradas: el array de String que contiene los errores a mostrar.
       Salidas: ninguna.
       Postcondiciones: se muestran en pantalla los errores correspondientes junto a los campos de texto.
    */
    public void mostrarErroresAlineacion(String[] errores) {
        tilNombre.setError(errores[0]);
        tilNombre.setErrorEnabled(errores[0] != null);
        tilSistema.setError(errores[1]);
        tilSistema.setErrorEnabled(errores[1] != null);
    }

    /* Estudio Interfaz
        Prototipo: public void vaciarCamposFormulario()
        Propósito: vaciar los campos del formulario correspondiente a la creación de una nueva alineación y retirar el foco sobre ellos.
        Precondiciones: ninguna.
        Entradas: ninguna.
        Salidas: ninguna.
        Postcondiciones: los campos del formulario quedan vacíos de contenido y libres de foco.
     */
    public void vaciarCamposFormulario() {
        edtNombre.getText().clear();
        edtNombre.clearFocus();
        actvSistema.getText().clear();
        actvSistema.clearFocus();
    }

    /* Estudio Interfaz
        Prototipo: public void inicializarFormularioAlineacion(View dialogLayout)
        Propósito: establecer el contenido inicial de los campos que componen el formulario correspondiente a la creación de una nueva alineación.
        Precondiciones: el parámetro "dialogLayout" debe ser distinto de null.
        Entradas: el layout del formulario del que obtener las subvistas correspondientes (campos del formulario).
        Salidas: ninguna.
        Postcondiciones: quedan poblados de información los campos del formulario para que estén disponibles una vez se muestren en pantalla.
     */
    public void inicializarFormularioAlineacion(View dialogLayout) {
        tilNombre = dialogLayout.findViewById(R.id.til_nombre_alineacion);
        tilSistema = dialogLayout.findViewById(R.id.til_sistema_alineacion);
        edtNombre = dialogLayout.findViewById(R.id.edt_nombre_alineacion);
        actvSistema = dialogLayout.findViewById(R.id.actev_sistema_alineacion);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, EnumSistemas.getListadoValoresString());
        actvSistema.setAdapter(adapter);
    }

    /* Estudio Interfaz
        Prototipo: public ArrayList<Alineacion> ordenarListadoAlineaciones(ArrayList<Alineacion> listadoAlineaciones)
        Propósito: ordenar el listado de alineaciones en base al criterio de si están compartidas o no.
        Es decir, en primer lugar irán las alineaciones no compartidas (creadas por el propio usuario) y, seguidamente, las alineaciones compartidas
        por otros usuarios.
        Precondiciones: el listado de alineaciones debe ser distinto de null y no debe estar vacío.
        Entradas: el listado de alineaciones por ordenar.
        Salidas: el listado de alineaciones una vez ordenado.
        Postcondiciones: se devuelve el listado de alineaciones ordenado asociado al nombre de la función.
     */
    public ArrayList<Alineacion> ordenarListadoAlineaciones(ArrayList<Alineacion> listadoAlineaciones) {
        ArrayList<Alineacion> listadoOrdenado = new ArrayList<>(), listadoCompartidas = new ArrayList<>();
        for (Alineacion alineacion: listadoAlineaciones) {
            if(alineacion.getNickUsuario().equals(usuarioActivo)) {
                listadoOrdenado.add(alineacion);
            } else {
                listadoCompartidas.add(alineacion);
            }
        }
        listadoOrdenado.addAll(listadoCompartidas);
        return listadoOrdenado;
    }

    //AlineacionesAdapter
    //Adaptador para el listado de alineaciones del usuario
    public class AlineacionesAdapter extends RecyclerView.Adapter<AlineacionesAdapter.ViewHolder> {

        private ArrayList<Alineacion> localDataSet;

        public AlineacionesAdapter(ArrayList<Alineacion> dataSet) {
            this.localDataSet = new ArrayList<>(dataSet);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_alineaciones_item, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Alineacion alineacion = localDataSet.get(position);
            String msgFinalizada, nickUsuario = alineacion.getNickUsuario();
            if(!nickUsuario.equals(usuarioActivo)) {
                holder.getRootView().setBackgroundColor(getResources().getColor(R.color.white_purple));
            }
            holder.getTevNombre().setText(alineacion.getNombre());
            if(alineacion.getFinalizada()) {
                msgFinalizada = getString(R.string.msg_finalizada);
            } else {
                msgFinalizada = getString(R.string.msg_no_finalizada);
            }
            holder.getTevFinalizada().setText(msgFinalizada);
            holder.getTevPropietario().setText(nickUsuario);
        }

        @Override
        public int getItemCount() {
            return localDataSet.size();
        }

        //ViewHolder
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private final RelativeLayout rootView;
            private final TextView tevNombre, tevFinalizada, tevPropietario;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                rootView = itemView.findViewById(R.id.root_item_alineaciones);
                tevNombre = itemView.findViewById(R.id.tev_nombre);
                tevFinalizada = itemView.findViewById(R.id.tev_finalizada);
                tevPropietario = itemView.findViewById(R.id.tev_propietario);
                itemView.setOnClickListener(this);
            }

            public RelativeLayout getRootView() {
                return rootView;
            }

            public TextView getTevNombre() {
                return tevNombre;
            }

            public TextView getTevFinalizada() {
                return tevFinalizada;
            }

            public TextView getTevPropietario() {
                return tevPropietario;
            }

            @Override
            public void onClick(View v) {
                int position = getAbsoluteAdapterPosition();
                Alineacion alineacion = localDataSet.get(position);
                Intent intent = new Intent(context, AlineacionActivity.class);
                intent.putExtra("alineacion", alineacion);
                startActivity(intent);
            }

        }

    }

}