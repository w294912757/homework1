package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;


public class SignupActivity extends AppCompatActivity {
    private Button signup, returnbutton;
    private String username, pass;
    private EditText ed1, ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        signup = findViewById(R.id.SignupButton);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                ed1 = findViewById(R.id.editText);
                ed2 = findViewById(R.id.editText2);
                username = ed1.getText().toString();
                pass = ed2.getText().toString();
                if (username.equals("") || pass.equals("")) {
                    Toast.makeText(SignupActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + "/elearn-user.db", null);
                    db.execSQL("CREATE TABLE IF NOT EXISTS user (username text primary key , pass text)");
                    Cursor cursor = db.query("user", null, null, null, null, null, null);
                    boolean find = false;

                    if (cursor.moveToFirst()) {
                        for (int i = 0; i < cursor.getCount(); i++) {
                            cursor.move(i);
                            String usernamed = cursor.getString(cursor.getColumnIndex("username"));
                            if (usernamed.equals(username)) {
                                Toast.makeText(SignupActivity.this, "该用户已存在", Toast.LENGTH_SHORT).show();
                                find = true;
                            }
                        }
                    }
                    if (find == false) {
                        db.execSQL("insert into user values('" + username + "','" + pass + "');");
                        Toast.makeText(SignupActivity.this, "注册成功，欢迎登录", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
        returnbutton = findViewById(R.id.ReturnButton);
        returnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
