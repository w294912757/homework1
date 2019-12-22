package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Main4Activity extends AppCompatActivity {
    private Button signup;
    private String username, password;
    private EditText ed1, ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        signup = findViewById(R.id.SignupButton);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                ed1 = findViewById(R.id.editText);
                ed2 = findViewById(R.id.editText2);
                username = ed1.getText().toString();
                password = ed2.getText().toString();
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(Main4Activity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        java.sql.Connection cn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/elearn", "root", "root");
                        String sql = "select * from user;";
                        try {
                            Statement st = (Statement) cn.createStatement();
                            ResultSet rs = st.executeQuery(sql);
                            List users = new ArrayList();
                            while (rs.next()) {
                                users.add(rs.getString(1));
                            }
                            if (users.contains(username)) {
                                Toast.makeText(Main4Activity.this, "该用户已存在！", Toast.LENGTH_SHORT).show();
                            } else {
                                sql = "insert into user ('" + username + "','" + password + "');";
                                st.executeUpdate(sql);
                                Toast.makeText(Main4Activity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                                finish();
                                cn.close();
                                st.close();
                                rs.close();
                            }


                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                }
            }
        });
    }
}
