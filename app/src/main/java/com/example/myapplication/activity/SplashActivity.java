package com.example.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.myapplication.R;


/**
 * Created by Pinger on 2016/9/15.
 */
public class SplashActivity extends Activity {

    private SharedPreferences mSp;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mSp = getSharedPreferences("config", MODE_PRIVATE);

        // 默认进入主页
        boolean firstEnter = mSp.getBoolean("guide", true);
        if (firstEnter) {
            // 进入引导页
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, WelcomeGuideActivity.class));
                    finish();
                }
            }, 3000);
        } else {
            // 进入主页
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, WelcomeGuideActivity.class));
                    finish();
                }
            }, 3000);

        }
    }
}

