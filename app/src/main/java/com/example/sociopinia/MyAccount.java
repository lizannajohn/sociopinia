package com.example.sociopinia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class MyAccount extends AppCompatActivity {

    //Initializing variables
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        //Assign variables
        drawerLayout = findViewById(R.id.drawerlayout);
    }

    public void ClickMenu(View view)
    {
        //Open Drawer
        NavigationDrawer.openDrawer(drawerLayout);

    }

    public void ClickLogo(View view)
    {
        //Closer Drawer
        NavigationDrawer.closeDrawer(drawerLayout);
    }

    public void ClickDashboard(View view)
    {
        //Redirect Activity to Dashboard
        NavigationDrawer.redirectActivity(this, NavigationDrawer.class);

    }
    public void ClickMyAcct(View view)
    {
        //Recreate My Account Activity
        recreate();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Close Drawer
        NavigationDrawer.closeDrawer(drawerLayout);
    }

}
