package com.example.sociopinia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserGetStarted extends AppCompatActivity {

    public void onclicksignup (View view)
    {
        Intent signup = new Intent(UserGetStarted.this, SignUp.class);
        startActivity(signup);
    }

    public void onclicklogin (View view)
    {
        Intent login = new Intent(UserGetStarted.this, Login.class);
        startActivity(login);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_get_started);

    }
}