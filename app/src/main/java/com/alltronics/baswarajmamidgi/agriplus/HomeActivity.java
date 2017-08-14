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
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{

    Button soil,crop,rain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);
        soil= (Button) findViewById(R.id.soil);
        crop= (Button) findViewById(R.id.crop);
        rain= (Button) findViewById(R.id.rain);
        soil.setOnClickListener(this);
        crop.setOnClickListener(this);
        rain.setOnClickListener(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        return false;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.soil){
            Intent i=new Intent(HomeActivity.this,MainActivity.class);
            i.putExtra("currenttab",0);
            startActivity(i);
        }

        if(view.getId()==R.id.rain){
            Intent i=new Intent(HomeActivity.this,MainActivity.class);
            i.putExtra("currenttab",1);
            startActivity(i);
        }
        if(view.getId()==R.id.crop){
            Intent i=new Intent(HomeActivity.this,MainActivity.class);
            i.putExtra("currenttab",2);
            startActivity(i);
        }

    }
}
