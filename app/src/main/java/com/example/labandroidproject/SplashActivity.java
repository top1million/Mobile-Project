package com.example.labandroidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // create a new handler to start the main activity after 3 seconds
        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // start the main activity
                        Intent intent = new android.content.Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        // close the current activity
                        finish();
                    }
                },
                3000);
    }
}