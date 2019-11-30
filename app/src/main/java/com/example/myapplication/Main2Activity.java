package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] strs = {"数据结构", "JAVA", "数据库", "操作系统"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toast.makeText(Main2Activity.this, "欢迎！", Toast.LENGTH_SHORT).show();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_expandable_list_item_1, strs);
        ListView ClassList = (ListView) findViewById(R.id.ClassList);
        ClassList.setAdapter(adapter);
        ClassList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent it = new Intent(Main2Activity.this, Main3Activity.class);
        it.putExtra("classname", position);
        startActivity(it);
    }
}