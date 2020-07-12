package com.example.projecttwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;

import com.backendless.Backendless;

public class activityintro extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    public static String APPID = "040F82AA-C3A8-7FF4-FFDD-52AEFD69A900";
    public static String APIKEY = "0E8CF9F5-6798-4D31-842D-99F47ADEC934";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityintro);
        Backendless.initApp(this, APPID, APIKEY);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        Intent intent = new Intent(getApplicationContext(), menubaractivity.class);
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(activityintro.this, "ERROR", Toast.LENGTH_LONG).show();
                    }
                }
            },SPLASH_TIME_OUT);
    }
}
