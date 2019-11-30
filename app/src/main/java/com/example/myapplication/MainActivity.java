package com.example.myapplication;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class MainActivity extends Activity {
    private Button login, signup;
    private String username, password;
    private EditText ed1, ed2;
    private boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("E-Learning");
        login = findViewById(R.id.LoginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ed1 = findViewById(R.id.editText);
                ed2 = findViewById(R.id.editText2);
                username = ed1.getText().toString();
                password = ed2.getText().toString();
                String[] s;

                FileInputStream fis;
                try {
                    fis = MainActivity.this.openFileInput("appfile.txt");
                    InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                    BufferedReader br = new BufferedReader(isr);
                    String line = br.readLine();
                    while (line != null) {
                        s = line.split("#");
                        if (username.equals(s[0]) && password.equals(s[1])) {
                            check = true;
                            startActivity(new Intent(MainActivity.this, Main2Activity.class));
                        }
                        line = br.readLine();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (check == false) {
                    Toast.makeText(MainActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signup = findViewById(R.id.SignupButton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main4Activity.class));
            }
        });
    }

}
