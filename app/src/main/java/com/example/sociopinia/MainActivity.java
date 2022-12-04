package com.example.sociopinia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //SPLASH SCREEN
    private static int SPLASHSCREEN = 7000;

    //Variables
    Animation topAnim, bottomAnim;
    ImageView sologo, txtbbl, phoneimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Invoke Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.topanim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottomanim);

        //Hooks
        sologo = findViewById(R.id.SOLogo);
        txtbbl = findViewById(R.id.txtbbl);
        phoneimg = findViewById(R.id.phoneimg);

        sologo.setAnimation(topAnim);
        phoneimg.setAnimation(bottomAnim);
        txtbbl.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASHSCREEN);

    }
}