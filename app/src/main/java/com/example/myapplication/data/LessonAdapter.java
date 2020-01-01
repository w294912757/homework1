package com.example.myapplication.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {
    private List<Lesson> lessons;
    private int rowLayout;

    public LessonAdapter(List<Lesson> lessons, int rowLayout) {
        this.lessons = lessons;
        this.rowLayout = rowLayout;
    }


    public LessonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new LessonViewHolder(view);
    }

    public void onBindViewHolder(LessonViewHolder holder, final int position) {
        holder.name.setText(lessons.get(position).getName());
        holder.details.setText(lessons.get(position).getDetails());
    }


    public int getItemCount() {
        return lessons.size();
    }

    public static class LessonViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView details;

        public LessonViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            details = (TextView) v.findViewById(R.id.details);
        }
    }
}




