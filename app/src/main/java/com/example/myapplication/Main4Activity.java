package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;

import java.io.OutputStreamWriter;


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
                        FileOutputStream fos = Main4Activity.this.openFileOutput("appfile.txt", Context.MODE_PRIVATE);
                        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                        osw.write(username + "#" + password);
                        osw.flush();
                        fos.flush();  //输出缓冲区中所有的内容
                        osw.close();
                        fos.close();
                        Toast.makeText(Main4Activity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                        finish();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                }


            }
        });
    }
}
