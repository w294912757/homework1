package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.ui.Main2Activity;


public class MainActivity extends Activity implements View.OnClickListener {
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.LoginButton);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, Main2Activity.class));
    }
}
