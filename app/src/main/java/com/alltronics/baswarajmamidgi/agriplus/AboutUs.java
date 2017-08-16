package com.alltronics.baswarajmamidgi.agriplus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class AboutUs extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_about_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       toolbar.setTitle("About Us");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.home:
            {
                break;
            }
            case R.id.soil_testing:
            {
                Intent i=new Intent(AboutUs.this,MainActivity.class);
                i.putExtra("currenttab",1);
                startActivity(i);
                break;
            }
            case R.id.crop_rotation:
            {
                Intent i=new Intent(AboutUs.this,MainActivity.class);
                i.putExtra("currenttab",1);
                startActivity(i);
                break;
            }
            case R.id.rain_analysis:
            {
                Intent i=new Intent(AboutUs.this,MainActivity.class);
                i.putExtra("currenttab",1);
                startActivity(i);
                break;
            }
            case R.id.aboutus:
            {
                Intent i=new Intent(AboutUs.this,AboutUs.class);
                startActivity(i);
                break;
            }
            case R.id.service:
            {
                Intent i=new Intent(AboutUs.this,Services.class);
                startActivity(i);
                break;
            }
            case R.id.profile:
            {
                Intent i=new Intent(AboutUs.this,Profile.class);
                startActivity(i);
                break;
            }
            case R.id.sign_in:
            {
                Intent i=new Intent(AboutUs.this,SignIn.class);
                startActivity(i);
                break;
            }

        }
        return true;
    }


}
