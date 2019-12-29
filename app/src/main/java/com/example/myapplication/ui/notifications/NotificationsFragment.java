package com.example.myapplication.ui.notifications;


import android.content.Context;
import android.os.Bundle;

import android.view.GestureDetector;
import android.view.LayoutInflater;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.data.Lesson;
import com.example.myapplication.data.LessonAdapter;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {
    private int classno;
    private NotificationsViewModel notificationsViewModel;
    private RecyclerView recyclerView;
    private LessonAdapter adapter;
    private List<Lesson> lessonList = new ArrayList<>();
    private TextView tv;
    private String classname;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        tv = getActivity().findViewById(R.id.ClassName);
        classno = Integer.parseInt(tv.getText().toString());

        switch (classno) {
            case 0:
                classname = "数据结构";
                break;
            case 1:
                classname = "JAVA";
                break;
            case 2:
                classname = "数据库";
                break;
            case 3:
                classname = "操作系统";
                break;
        }

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        recyclerView = getView().findViewById(R.id.aListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        createLessonList();
        adapter = new LessonAdapter(lessonList, R.layout.list_item);

        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new NotificationsFragment.RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new NotificationsFragment.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Lesson lesson = lessonList.get(position);
                Toast.makeText(getActivity().getApplicationContext(), lesson.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }

    private void createLessonList() {


        for (int i = 1; i < 11; i++) {
            Lesson lesson = new Lesson();
            lesson.setName(classname + "学习资源" + i);
            lesson.setDetails(classname + "学习资源详情" + i);
            lessonList.add(lesson);
        }

    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }


}


