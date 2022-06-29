package com.example.nba_myteam_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.nba_myteam_app.R;
import com.example.nba_myteam_app.view_models.LogInVM;
import com.example.nba_myteam_app.view_models.SesionVM;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

}