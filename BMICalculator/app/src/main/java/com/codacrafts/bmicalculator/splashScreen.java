package com.codacrafts.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        WindowManager windowManager;
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, 1000);
    }
}