package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class Main4Activity extends AppCompatActivity {
    private Button signup;
    private String username, pass;
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
                pass = ed2.getText().toString();
                if (username.equals("") || pass.equals("")) {
                    Toast.makeText(Main4Activity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int result = Database.signup(username,pass);
                            if (result==0){
                                Toast.makeText(Main4Activity.this, "注册成功!", Toast.LENGTH_SHORT).show();
                                finish();
                            }else if (result==1){
                                Toast.makeText(Main4Activity.this, "该用户已存在!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).start();
                }
            }
        });
    }
}
