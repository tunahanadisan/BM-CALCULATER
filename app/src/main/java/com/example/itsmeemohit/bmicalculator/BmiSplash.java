package com.example.itsmeemohit.bmicalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class BmiSplash extends AppCompatActivity {

    Animation animation;
    ImageView ivLogo;
    TextView tvAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_splash);

        ivLogo= (ImageView) findViewById(R.id.ivLogo);
        tvAppName= (TextView) findViewById(R.id.tvAppName);
        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.custom);

        tvAppName.startAnimation(animation);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        }).start();

    }
}
