package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.myapplication.GlideImageLoader;
import com.example.myapplication.R;
import com.youth.banner.Banner;


import java.util.ArrayList;
import java.util.List;

public class StudyActivity extends AppCompatActivity {
    private Banner banner;
    private VideoView videoview;
    List<String> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        Intent it = getIntent();
        String classname = it.getStringExtra("classname");
        TextView infoview = findViewById(R.id.InfoView);
        infoview.setText(classname);
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578726067&di=d50b711450cabc3de36d45790e886390&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.sccnn.com%2Fbimg%2F338%2F50474.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578131346609&di=63e4410fe1e071f9a2c5b9581965e6d7&imgtype=0&src=http%3A%2F%2Fimg.mp.sohu.com%2Fupload%2F20170715%2Fb469f824d1594f44873598ed4b83c35f_th.png");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578131346608&di=b8313854f64713372234cfd210225368&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_70%2Cc_zoom%2Cw_640%2Fimages%2F20180723%2F43a3502fabbe41528e33132ff6b18036.jpg");
        banner = findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());   //设置图片加载器
        banner.setImages(images);  //设置banner中显示图片
        banner.start();  //设置完毕后调用

        videoview = findViewById(R.id.videoView);
        String uri = "https://v-cdn.zjol.com.cn/277003.mp4";
        videoview.setVideoURI(Uri.parse(uri));
        MediaController mediaController = new MediaController(this);
        mediaController.setMediaPlayer(videoview);
        videoview.setMediaController(mediaController);


        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
