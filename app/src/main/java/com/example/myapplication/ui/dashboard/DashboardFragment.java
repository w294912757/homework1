package com.example.myapplication.ui.dashboard;

import android.content.Intent;
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

import com.example.myapplication.Main3Activity;
import com.example.myapplication.R;

public class DashboardFragment extends Fragment {
    private int classname;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        TextView tv = getActivity().findViewById(R.id.ClassName);
        classname = Integer.parseInt(tv.getText().toString());

        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                switch (classname) {
                    case 0:
                        textView.setText("数据结构");
                        break;
                    case 1:
                        textView.setText("JAVA");
                        break;
                    case 2:
                        textView.setText("数据库");
                        break;
                    case 3:
                        textView.setText("操作系统");
                        break;
                }
            }
        });
        return root;
    }
}