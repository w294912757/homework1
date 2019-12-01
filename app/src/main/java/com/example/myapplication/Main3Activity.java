package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    private int classname;
    private static final String TAG = MainActivity.class.getSimpleName();

    RecyclerView recyclerView;
    LessonAdapter adapter;
    List<Lesson> lessonList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent it = getIntent();
        classname =it.getIntExtra("classname",0);
        String name = Integer.toString(classname);
        TextView tv = (TextView) findViewById(R.id.ClassName);
        tv.setText(name);

        recyclerView = (RecyclerView) findViewById(R.id.aListView);

        createLessonList(); // Create the data.

        //This is to show data for first time when we run the app.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LessonAdapter(lessonList, R.layout.list_item);
        recyclerView.setAdapter(adapter);

        //Add OnItemTouchListener in RecyclerView
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Lesson lesson = lessonList.get(position);
                Toast.makeText(getApplicationContext(), lesson.getName() + " is selected!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int layoutId = R.layout.list_item;
        int spanCount = 4;
        switch (item.getItemId()) {
            case R.id.linearVertical:  // Vertical scrollable using LinearLayoutManager.
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.linearHorizontal:  // Horizontal scrollable using LinearLayoutManager.
                layoutId = R.layout.list_item_horizontal;
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                break;
            case R.id.gridviewVertical:  // Vertical scrollable using GridLayoutManager.
                recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));
                break;
            case R.id.gridviewHorizontal:  // Horizontal scrollable using GridLayoutManager.
                layoutId = R.layout.list_item_horizontal;
                recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount, LinearLayoutManager.HORIZONTAL, false));
                break;
            case R.id.staggeredGridviewVertical:  // Vertical scrollable using StaggeredGridLayoutManager.
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL));
                break;
            case R.id.staggeredGridviewHorizontal:  // Horizontal scrollable using StaggeredGridLayoutManager.
                layoutId = R.layout.list_item_horizontal;
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.HORIZONTAL));
                break;
        }
        adapter = new LessonAdapter(lessonList, layoutId);
        recyclerView.setAdapter(adapter);

        return super.onOptionsItemSelected(item);
    }


    private void createLessonList() {

        // Create data...

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
