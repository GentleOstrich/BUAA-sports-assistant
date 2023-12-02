package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.ui.sport.BadmintonActivity;
import com.example.myapplication.ui.sport.BasketballActivity;
import com.example.myapplication.ui.sport.FootballActivity;
import com.example.myapplication.ui.sport.FrisbeeActivity;
import com.example.myapplication.ui.sport.GymActivity;
import com.example.myapplication.ui.sport.MoreActivity;
import com.example.myapplication.ui.sport.RunActivity;
import com.example.myapplication.ui.sport.SwimActivity;
import com.example.myapplication.ui.sport.TableTennisAvtivity;
import com.example.myapplication.ui.sport.TdActivity;
import com.example.myapplication.ui.sport.TennisActivity;
import com.example.myapplication.ui.sport.VolleyballActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        findViewById(R.id.btn_run).setOnClickListener(this);
        findViewById(R.id.btn_basketball).setOnClickListener(this);
        findViewById(R.id.btn_football).setOnClickListener(this);
        findViewById(R.id.btn_volleyball).setOnClickListener(this);
        findViewById(R.id.btn_badminton).setOnClickListener(this);
        findViewById(R.id.btn_swim).setOnClickListener(this);
        findViewById(R.id.btn_gym).setOnClickListener(this);
        findViewById(R.id.btn_tabletennis).setOnClickListener(this);
        findViewById(R.id.btn_tennis).setOnClickListener(this);
        findViewById(R.id.btn_frisbee).setOnClickListener(this);
        findViewById(R.id.btn_td).setOnClickListener(this);
        findViewById(R.id.btn_more).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        if (view.getId() == R.id.btn_run) {
            intent = new Intent(this, RunActivity.class);
        } else if (view.getId() == R.id.btn_basketball) {
            intent = new Intent(this, BasketballActivity.class);
        } else if (view.getId() == R.id.btn_football) {
            intent = new Intent(this, FootballActivity.class);
        } else if (view.getId() == R.id.btn_volleyball) {
            intent = new Intent(this, VolleyballActivity.class);
        } else if (view.getId() == R.id.btn_badminton) {
            intent = new Intent(this, BadmintonActivity.class);
        } else if (view.getId() == R.id.btn_swim) {
            intent = new Intent(this, SwimActivity.class);
        } else if (view.getId() == R.id.btn_gym) {
            intent = new Intent(this, GymActivity.class);
        } else if (view.getId() == R.id.btn_tabletennis) {
            intent = new Intent(this, TableTennisAvtivity.class);
        } else if (view.getId() == R.id.btn_tennis) {
            intent = new Intent(this, TennisActivity.class);
        } else if (view.getId() == R.id.btn_frisbee) {
            intent = new Intent(this, FrisbeeActivity.class);
        } else if (view.getId() == R.id.btn_td) {
            intent = new Intent(this, TdActivity.class);
        } else if (view.getId() == R.id.btn_more) {
            intent = new Intent(this, MoreActivity.class);
        }
        startActivity(intent);
    }
}