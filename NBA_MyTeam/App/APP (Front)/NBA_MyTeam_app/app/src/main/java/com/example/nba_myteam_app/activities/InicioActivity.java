package com.example.nba_myteam_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.nba_myteam_app.R;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    //Se sobreescribe este método para evitar que se pueda volver a HomeActivity una vez cerrada la sesión del usuario
    @Override
    public void onBackPressed() {
        Toast.makeText(this, R.string.back_fail, Toast.LENGTH_LONG).show();
    }

}