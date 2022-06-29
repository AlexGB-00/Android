package com.example.nba_myteam_app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nba_myteam_app.R;
import com.example.nba_myteam_app.activities.JugadorActivity;
import com.example.nba_myteam_app.retrofit.entities.Jugador;
import com.example.nba_myteam_app.view_models.ClubVM;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClubFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClubFragment extends Fragment implements View.OnClickListener {

    //Atributos
    private RecyclerView revClub;
    private ClubVM clubVM;
    private LinearLayout linEmpty;
    private MaterialButton btnNavSobres;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClubFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClubFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClubFragment newInstance(String param1, String param2) {
        ClubFragment fragment = new ClubFragment();
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
        View v = inflater.inflate(R.layout.fragment_club, container, false);
        revClub = v.findViewById(R.id.rev_club);
        linEmpty = v.findViewById(R.id.lin_empty_club);
        btnNavSobres = v.findViewById(R.id.btn_nav_sobres);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Se obtiene el ViewModel compartido con HomeActivity
        clubVM = new ViewModelProvider(requireActivity()).get(ClubVM.class);
        //Se establecen los listeners
        btnNavSobres.setOnClickListener(this);
        //Se definen los Observers
        final Observer<List<Jugador>> listadoJugadoresClubObserver = new Observer<List<Jugador>>() {
            @Override
            public void onChanged(List<Jugador> listadoJugadores) {
                if(listadoJugadores != null) {
                    linEmpty.setVisibility(View.GONE);
                    revClub.setVisibility(View.VISIBLE);
                    revClub.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                    ArrayList<Jugador> dataSet = new ArrayList<>(listadoJugadores);
                    revClub.setAdapter(new ClubAdapter(dataSet));
                } else {
                   linEmpty.setVisibility(View.VISIBLE);
                   revClub.setVisibility(View.GONE);
                }
            }
        };
        ClubVM.getListadoJugadores().observe(getViewLifecycleOwner(), listadoJugadoresClubObserver);
    }

    @Override
    public void onResume() {
        super.onResume();
        clubVM.getListadoJugadoresClubUsuarioVM(ClubVM.getUsuarioActivo().getValue().getNick());
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_nav_sobres) {
            clubVM.getBtnNavSobresSeleccionado().setValue(true);
        }
    }

    //ClubAdapter
    //Adaptador para el listado de jugadores del usuario
    public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ViewHolder> {

        private ArrayList<Jugador> localDataSet;

        public ClubAdapter(ArrayList<Jugador> dataSet) {
            this.localDataSet = new ArrayList<>(dataSet);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_club_item, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Jugador jugador = localDataSet.get(position);
            holder.getTevNombre().setText(jugador.getNombre());
            holder.getTevValoracion().setText(String.valueOf(jugador.getValoracionGeneral()));
            ImageView imageView = holder.getImgJugador();
            Glide.with(getActivity().getApplicationContext()).load(jugador.getImagen())
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
            private final TextView tevValoracion;
            private final ImageView imgJugador;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tevNombre = itemView.findViewById(R.id.tev_nombre_jugador);
                tevValoracion = itemView.findViewById(R.id.tev_valoracion_jugador);
                imgJugador = itemView.findViewById(R.id.img_jugador);
                itemView.setOnClickListener(this);
            }

            public TextView getTevNombre() {
                return tevNombre;
            }

            public TextView getTevValoracion() {
                return tevValoracion;
            }

            public ImageView getImgJugador() {
                return imgJugador;
            }

            @Override
            public void onClick(View v) {
                int position = getAbsoluteAdapterPosition();
                Jugador jugador = localDataSet.get(position);
                Intent intent = new Intent(getActivity(), JugadorActivity.class);
                intent.putExtra("jugador", jugador);
                startActivity(intent);
            }

        }

    }

}