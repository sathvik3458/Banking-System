package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class SplashScreenActivity extends AppCompatActivity {

//  Variables
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView projectName, developer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image=findViewById(R.id.bankImage);
        projectName=findViewById(R.id.projectName);
        developer=findViewById(R.id.developer);

        image.setAnimation(topAnim);
        projectName.setAnimation(bottomAnim);
        developer.setAnimation(bottomAnim);

        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(5000);

                    Intent newIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(newIntent);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        myThread.start();
    }
}