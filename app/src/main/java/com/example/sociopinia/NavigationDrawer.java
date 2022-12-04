package com.example.sociopinia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.sociopinia.util.DebounceTextWatcher;

import java.util.Locale;

public class NavigationDrawer extends AppCompatActivity {

    public static final long INPUT_FIELD_DELAY = 2000; // 2 seconds

    //Initializing variables
    DrawerLayout drawerLayout;
    private EditText inputText;
    private EditText polarityOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        //Assign variables
        drawerLayout = findViewById(R.id.drawerlayout);
        inputText = findViewById(R.id.userinputtext);
        polarityOutput = findViewById(R.id.polarityoutput);

        inputText.addTextChangedListener(new DebounceTextWatcher(() -> {
            ApiRepository.getInstance().analyzeComments(inputText.getText().toString(), analyzeResult -> {

                if(analyzeResult != null) {
                    polarityOutput.setText(String.format(
                            Locale.US,"%s (%.2f%%)",
                            analyzeResult.getType(),
                            analyzeResult.getTypePercentage()
                    ));
                }
            });
        }, INPUT_FIELD_DELAY));

    }

    public void ClickMenu (View view)
    {
        //Open Drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout)
    {
        //Open DrawerLayout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view)
    {
        //Close Drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout)
    {
        //Close DrawerLayout
        //Check condition
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            //When Drawer is OPEN
            //Close Drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view)
    {
        //Recreate activity

    }

    public void ClickDashboard(View view)
    {
        //Recreate Dashboard Activity
        recreate();
    }

    public void ClickMyAcct(View view)
    {
        //Redirect activity to My Account
        redirectActivity(this, MyAccount.class);
    }

    public void ClickLogout(View view)
    {
        //Close app
        logout(this);
    }

    public static void logout(Activity activity)
    {
        //Initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        //Set title
        builder.setTitle("Logout");

        //Set message
        builder.setMessage("Are you sure you want to Logout?");

        //Positive YES Button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Finish activity
                activity.finishAffinity();

                //Exit App
                System.exit(0);
            }
        });

        //Negative NO Button
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dismiss Dialog
                dialog.dismiss();
            }
        });

        //Show dialog
        builder.show();
    }

    public static void redirectActivity(Activity activity, Class aClass)
    {
        //Initializing intent
        Intent intent = new Intent(activity, aClass);

        //Set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Start Activity
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Close Drawer
        closeDrawer(drawerLayout);
    }
}