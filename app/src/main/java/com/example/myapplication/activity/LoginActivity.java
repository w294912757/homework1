package com.example.myapplication.activity;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;


public class LoginActivity extends Activity {
    private Button login, signup;
    private String username, pass;
    private EditText ed1, ed2;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        ed1 = findViewById(R.id.editText);
        ed2 = findViewById(R.id.editText2);
        ed1.setText(sp.getString("username", null));
        ed2.setText(sp.getString("pass", null));

        setTitle("E-Learning");
        signup = findViewById(R.id.SignupButton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
        login = findViewById(R.id.LoginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = ed1.getText().toString();
                pass = ed2.getText().toString();
                if (username.equals("") || pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                } else {
                    SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + "/elearn-user.db", null);
                    db.execSQL("CREATE TABLE IF NOT EXISTS user (username text primary key , pass text)");
                    Cursor cursor = db.query("user", null, null, null, null, null, null);
                    boolean find = false;
                    if (cursor.moveToFirst()) {
                        for (int i = 0; i < cursor.getCount(); i++) {
                            cursor.move(i);
                            String usernamed = cursor.getString(cursor.getColumnIndex("username"));
                            String passed = cursor.getString(cursor.getColumnIndex("pass"));

                            if (usernamed.equals(username) && passed.equals(pass)) {
                                Toast.makeText(LoginActivity.this, "欢迎", Toast.LENGTH_SHORT).show();
                                find = true;

                                SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
                                sp.edit()
                                        .putString("username", username)
                                        .putString("pass", pass)
                                        .apply();

                                startActivity(new Intent(LoginActivity.this, SelectActivity.class));
                            } else if (usernamed.equals(username) && !passed.equals(pass)) {
                                Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                                find = true;
                            }
                        }
                        if (find = false) {
                            Toast.makeText(LoginActivity.this, "该用户不存在", Toast.LENGTH_SHORT).show();
                        }
                    }


                }

            }
        });


    }

}
