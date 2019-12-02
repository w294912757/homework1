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
                Toast.makeText(getActivity().getApplicationContext(), lesson.getName() + " is selected!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }

    private void createLessonList() {

        Lesson Lesson1 = new Lesson();
        Lesson1.setName("Monkey");
        Lesson1.setDetails("Monkeys are haplorhine (\"dry-nosed\") primates, a paraphyletic group generally possessing tails and consisting of approximately 260 known living species");

        Lesson Lesson2 = new Lesson();
        Lesson2.setName("Buffalo");
        Lesson2.setDetails("The African buffalo or Cape buffalo (Syncerus caffer) is a large African bovine");

        Lesson Lesson3 = new Lesson();
        Lesson3.setName("Donkey");
        Lesson3.setDetails("The donkey or ass is a domesticated member of the horse family, Equidae. The wild ancestor of the donkey is the African wild ass, E. africanus");

        Lesson Lesson4 = new Lesson();
        Lesson4.setName("Dog");
        Lesson4.setDetails("The domestic dog is a domesticated canid which has been selectively bred over millennia for various behaviours, sensory capabilities, and physical attributes");

        Lesson Lesson5 = new Lesson();
        Lesson5.setName("Goat");
        Lesson5.setDetails("The domestic goat is a subspecies of goat domesticated from the wild goat of southwest Asia and Eastern Europe");

        Lesson Lesson6 = new Lesson();
        Lesson6.setName("Tiger");
        Lesson6.setDetails("The tiger is the largest cat species, most recognisable for their pattern of dark vertical stripes on reddish-orange fur with a lighter underside");

        Lesson Lesson7 = new Lesson();
        Lesson7.setName("Lion");
        Lesson7.setDetails("The lion is one of the big cats in the genus Panthera and a member of the family Felidae.");

        Lesson Lesson8 = new Lesson();
        Lesson8.setName("Leopard");
        Lesson8.setDetails("The leopard is one of the five \"big cats\" in the genus Panthera");

        Lesson Lesson9 = new Lesson();
        Lesson9.setName("Cheetah");
        Lesson9.setDetails("The cheetah, also known as the hunting leopard, is a big cat that occurs mainly in eastern and southern Africa and a few parts of Iran");

        Lesson Lesson10 = new Lesson();
        Lesson10.setName("Rat");
        Lesson10.setDetails("Rats are various medium-sized, long-tailed rodents of the superfamily Muroidea");

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


