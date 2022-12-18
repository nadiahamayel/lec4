package com.example.lec4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Animation top,bottom;
    private TextView textView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        top= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        textView=findViewById(R.id.textView);
        imageView=findViewById(R.id.imageView);
        textView.setAnimation(bottom);
        imageView.setAnimation(top);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(MainActivity.this,meunActivaty.class);
                startActivity(intent);
                finish();


            }
        },5000);


    }
}