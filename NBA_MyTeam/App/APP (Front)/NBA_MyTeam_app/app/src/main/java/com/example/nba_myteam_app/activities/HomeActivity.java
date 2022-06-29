package com.example.nba_myteam_app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.nba_myteam_app.R;
import com.example.nba_myteam_app.fragments.AlineacionesFragment;
import com.example.nba_myteam_app.fragments.ClubFragment;
import com.example.nba_myteam_app.fragments.SobresFragment;
import com.example.nba_myteam_app.handlers.SesionHandler;
import com.example.nba_myteam_app.retrofit.entities.Usuario;
import com.example.nba_myteam_app.utilities.DialogUtility;
import com.example.nba_myteam_app.view_models.ClubVM;
import com.example.nba_myteam_app.view_models.SesionVM;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    //Atributos
    private DrawerLayout drawer;
    private AlertDialog sesionDialog;
    private BottomNavigationView botNavView;
    private Intent intentNavMenu;
    private Toolbar toolbar;
    private final String SELECTED = "Item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Se recogen las vistas del layout
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_menu);
        botNavView = findViewById(R.id.bottom_navigation);
        //Se integra el menú del DrawerLayout con la Toolbar para que de esta manera aparezca el menú al pulsar el botón "hamburguer"
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        //Se establecen los listeners
        navView.setNavigationItemSelectedListener(this);
        botNavView.setOnNavigationItemSelectedListener(this);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //Se carga la ficha del perfil de usuario
        View perfilLayout = navView.getHeaderView(0);
        Usuario usuarioActivo = SesionVM.getUsuarioActivo().getValue();
        cargarFichaUsuario(perfilLayout, usuarioActivo);
        //Se carga el fragment inicial
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.frg_home, ClubFragment.class, null)
                    .addToBackStack(null)
                    .commit();
            toolbar.setTitle("Club");
        } else {
            botNavView.setSelectedItemId(savedInstanceState.getInt(SELECTED));
        }
        //Se construye el AlertDialog para el Logout
        DialogInterface.OnClickListener positiveButtonAction = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SesionHandler.eliminarSesionActiva();
                SesionVM.setUsuarioActivo(null);
                intentNavMenu = new Intent(getApplicationContext(), InicioActivity.class);
                startActivity(intentNavMenu);
                finish();
            }};
        sesionDialog = DialogUtility.crearAlertDialog(this, getString(R.string.sesion_dialog_title), getString(R.string.sesion_dialog_msg), null, positiveButtonAction);
        //Se definen los Observers
        final Observer<Boolean> btnNavSobresObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean btnNavSobres) {
                if(btnNavSobres) {
                    replaceFragment(new SobresFragment());
                    botNavView.setSelectedItemId(R.id.pag_sobres);
                    toolbar.setTitle("Sobres");
                }
            }
        };
        ClubVM clubVM = new ViewModelProvider(this).get(ClubVM.class);
        clubVM.getBtnNavSobresSeleccionado().observe(this, btnNavSobresObserver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(sesionDialog != null) {
            sesionDialog.dismiss();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED, botNavView.getSelectedItemId());
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(botNavView.getSelectedItemId() != R.id.pag_club) {
            botNavView.setSelectedItemId(R.id.pag_club);
        } else {
            moveTaskToBack(true);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.nav_log_out || itemId == R.id.nav_info) {
            switch (itemId) {
                case R.id.nav_log_out:
                    sesionDialog.show();
                    break;
                case R.id.nav_info:
                    intentNavMenu = new Intent(this, HelpActivity.class);
                    startActivity(intentNavMenu);
                    break;
            }
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Fragment fragment = null;
            switch (itemId) {
                case R.id.pag_club:
                    toolbar.setTitle("Club");
                    fragment = new ClubFragment();
                    break;
                case R.id.pag_alineaciones:
                    toolbar.setTitle("Alineaciones");
                    fragment = new AlineacionesFragment();
                    break;
                case R.id.pag_sobres:
                    toolbar.setTitle("Sobres");
                    fragment = new SobresFragment();
                    break;
            }
            if(fragment != null) {
                replaceFragment(fragment);
            }
        }
        return true;
    }
    
    //Método añadidos

    /* Estudio Interfaz
        Prototipo: public void replaceFragment(Fragment newFragment)
        Propósito: reemplazar el fragment visualmente activo por otro, el cual será determinado por el parámetro "newFragment".
        Precondiciones: "newFragment" debe ser distinto de null.
        Entradas: el nuevo fragment que se desea mostrar.
        Salidas: ninguna.
        Postcondiciones: se reemplaza el fragment anterior por el nuevo y se actualiza la vista con el nuevo contenido.
    */
    public void replaceFragment(Fragment newFragment) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frg_home, newFragment, null)
                .addToBackStack(null)
                .commit();
    }

    /* Estudio Interfaz
        Prototipo: public void cargarFichaUsuario(View layout, Usuario datosUsuario)
        Propósito: cargar y mostrar los datos del usuario activo en la parte superior del menú de navegación (menú "hamburguer").
        Precondiciones: tanto "layout" como "datosUsuario" deben ser distintos de null.
        Entradas: el layout correspondiente a la ficha del perfil de usuario (header del navigation view) y los datos del usuario a mostrar.
        Salidas: ninguna.
        Postcondiciones: quedan actualizados los campos de la ficha del menú de navegación con los datos del usuario en cuestión.
     */
    public void cargarFichaUsuario(View layout, Usuario datosUsuario) {
        TextView tevNick = layout.findViewById(R.id.tev_nick_nav);
        TextView tevEmail = layout.findViewById(R.id.tev_email_nav);
        tevNick.setText(datosUsuario.getNick());
        tevEmail.setText(datosUsuario.getCorreoElectronico());
    }

}