package com.example.sociopinia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import static com.example.sociopinia.R.id.btnGS;
import static com.example.sociopinia.R.id.scrviewpager;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introVPA;

    TabLayout tabIndicator;

    Button btnNext;

    int position = 0;

    Button btnGetStarted;

    Animation btnAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Make the Activity Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //When this activity is about to be launched, we need to check if it is already opened before or not
        /*if (restorePrefData())
        {
            Intent userGS = new Intent(getApplicationContext(), UserGetStarted.class);
            startActivity(userGS);
            finish();
        }*/

        setContentView(R.layout.activity_intro);

        //ini Views
        btnNext = findViewById(R.id.btnNEXT);
        btnGetStarted = findViewById(R.id.btnGS);
        tabIndicator = findViewById(R.id.tabindicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.btnanimation);

        //Fill List Screen
        List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("SociOpinia", "An app designed for understanding sentiment polarity of a text, based on the principles of Artificial Intelligence, Machine Learning, Data Mining and Natural Language Processing.", R.drawable.ainplml));
        mList.add(new ScreenItem("Social Media", "Social Media responses often contain sentiments difficult to decipher. SociOpinia is here to help!", R.drawable.socialmediacontent));
        mList.add(new ScreenItem("Opinion Mining", "Decipher myriad of responses based on the opinions and their polarity.          Join SociOpinia today!", R.drawable.opinion));

        //Set up ViewPager
        screenPager = findViewById(R.id.scrviewpager);
        introVPA = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introVPA);

        //Set up TabLayout with ViewPager
        tabIndicator.setupWithViewPager(screenPager);

        //Next Button ClickListener
        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                position = screenPager.getCurrentItem();

                if(position < mList.size())
                {
                    position++;
                    screenPager.setCurrentItem(position);
                }

                if(position == mList.size()-1) //When reaching last screen
                {
                    //TODO: show the GETSTARTED Button and hide indicator and the next button
                    loadLastScreen();
                }
            }
        });

        //TabLayout Add Change Listener
        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == mList.size()-1)
                {
                    loadLastScreen();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //Get Started Button ClickListener

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Open UserGetStarted Page
                Intent userGS = new Intent(getApplicationContext(), UserGetStarted.class);
                startActivity(userGS);

                //Boolean Value to store for next time run of app; Already checked into intro screen activity;
                //shared preferences used to process

                /*savePrefsData();
                finish();*/

            }
        });
    }

    /*private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = pref.getBoolean("isIntroOpened", false);
        return isIntroActivityOpenedBefore;
    }*/

    /*private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();

    }*/

    //Show the GETSTARTED Button and hide indicator and the next button
    private void loadLastScreen() {

        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);

        //TODO: ADD an animation to GetStarted button
            //Set up animation
                btnGetStarted.setAnimation(btnAnim);


    }
}