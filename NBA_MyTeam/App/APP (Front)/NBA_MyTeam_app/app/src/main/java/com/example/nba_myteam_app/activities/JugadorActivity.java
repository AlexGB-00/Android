package com.example.nba_myteam_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.nba_myteam_app.R;
import com.example.nba_myteam_app.retrofit.entities.Jugador;

public class JugadorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugador);
    }
}