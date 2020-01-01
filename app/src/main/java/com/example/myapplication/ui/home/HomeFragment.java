package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;

public class HomeFragment extends Fragment {
    private int classname;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        TextView tv = getActivity().findViewById(R.id.ClassName);
        classname = Integer.parseInt(tv.getText().toString());

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                switch (classname) {
                    case 0:
                        textView.setText("数据结构课程简介");
                        break;
                    case 1:
                        textView.setText("JAVA课程简介");
                        break;
                    case 2:
                        textView.setText("数据库课程简介");
                        break;
                    case 3:
                        textView.setText("操作系统课程简介");
                        break;
                }
            }
        });
        return root;
    }
}