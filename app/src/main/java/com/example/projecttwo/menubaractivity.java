package com.example.projecttwo;

import android.content.Intent;
import android.os.Bundle;

import com.backendless.Backendless;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;

public class menubaractivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button b1,b2,b3;
    public static String APPID = "040F82AA-C3A8-7FF4-FFDD-52AEFD69A900";
    public static String APIKEY = "0E8CF9F5-6798-4D31-842D-99F47ADEC934";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menubaractivity);
        b1=(Button)findViewById(R.id.master);
        b2=(Button)findViewById(R.id.staff);
        b3=(Button)findViewById(R.id.student);
        Backendless.initApp(this, APPID, APIKEY);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menubaractivity.this,masterlogin.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menubaractivity.this,stafflogin.class);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menubaractivity.this,studentlcr.class);
                startActivity(intent);
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menubaractivity, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(menubaractivity.this,masterlogin.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(menubaractivity.this,stafflogin.class);
            startActivity(intent);
        } else if (id == R.id.nav_tools) {
            Intent intent = new Intent(menubaractivity.this,studentlcr.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(menubaractivity.this,aboutcollege.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(menubaractivity.this,contactus.class);
            startActivity(intent);
        } else if(id==R.id.nav_fees){
            Intent intent = new Intent(menubaractivity.this,feespayment.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
