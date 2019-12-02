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

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Lesson;
import com.example.myapplication.LessonAdapter;
import com.example.myapplication.Main3Activity;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {
    private int classname;
    private NotificationsViewModel notificationsViewModel;
    private RecyclerView recyclerView;
    private LessonAdapter adapter;
    private List<Lesson> lessonList = new ArrayList<>();
    private TextView tv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        tv = getActivity().findViewById(R.id.ClassName);
        classname = Integer.parseInt(tv.getText().toString());


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

        Lesson Lesson1 = new Lesson();
        Lesson1.setName("1");
        Lesson1.setDetails("11");

        Lesson Lesson2 = new Lesson();
        Lesson2.setName("2");
        Lesson2.setDetails("22");

        Lesson Lesson3 = new Lesson();
        Lesson3.setName("3");
        Lesson3.setDetails("33");

        Lesson Lesson4 = new Lesson();
        Lesson4.setName("4");
        Lesson4.setDetails("44");

        Lesson Lesson5 = new Lesson();
        Lesson5.setName("5");
        Lesson5.setDetails("55");

        Lesson Lesson6 = new Lesson();
        Lesson6.setName("6");
        Lesson6.setDetails("66");

        Lesson Lesson7 = new Lesson();
        Lesson7.setName("7");
        Lesson7.setDetails("77");

        Lesson Lesson8 = new Lesson();
        Lesson8.setName("8");
        Lesson8.setDetails("88");

        Lesson Lesson9 = new Lesson();
        Lesson9.setName("9");
        Lesson9.setDetails("99");

        Lesson Lesson10 = new Lesson();
        Lesson10.setName("10");
        Lesson10.setDetails("100");

        lessonList.add(Lesson1);
        lessonList.add(Lesson2);
        lessonList.add(Lesson3);
        lessonList.add(Lesson4);
        lessonList.add(Lesson5);
        lessonList.add(Lesson6);
        lessonList.add(Lesson7);
        lessonList.add(Lesson8);
        lessonList.add(Lesson9);
        lessonList.add(Lesson10);
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


