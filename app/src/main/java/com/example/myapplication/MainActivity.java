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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
        signup = findViewById(R.id.SignupButton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main4Activity.class));
            }
        });

        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection cn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/elearn", "root", "root");


            login = findViewById(R.id.LoginButton);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ed1 = findViewById(R.id.editText);
                    ed2 = findViewById(R.id.editText2);
                    username = ed1.getText().toString();
                    password = ed2.getText().toString();
                    String sql = "select * from user;";
                    try {
                        Statement st = (Statement) cn.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        while (rs.next()) {
                            String user = rs.getString("username");
                            String pass = rs.getString("password");
                            if (user.equals(username) && pass.equals(password)) {
                                startActivity(new Intent(MainActivity.this, Main2Activity.class));
                            }
                        }
                        Toast.makeText(MainActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                        cn.close();
                        st.close();
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                }
            });


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
