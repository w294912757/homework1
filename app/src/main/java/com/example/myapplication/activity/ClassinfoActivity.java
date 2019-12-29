package com.example.myapplication.activity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;


import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class ClassinfoActivity extends AppCompatActivity {
    private int classname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Intent it = getIntent();
        classname =it.getIntExtra("classname",0);
        String name = Integer.toString(classname);
        TextView tv = findViewById(R.id.ClassName);
        tv.setText(name);
        tv.setVisibility(View.INVISIBLE);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}
