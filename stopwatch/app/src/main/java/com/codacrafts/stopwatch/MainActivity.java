package com.codacrafts.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    Handler handler;
    TextView textView;
    boolean isRunning = false;
    Button startButton, stopButton, resetButton;
    int milliseconds = 0;
    long startTime; // Declare startTime here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startbtn);
        stopButton = findViewById(R.id.stopbtn);
        resetButton = findViewById(R.id.resetbtn);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = true;
                startTime = System.currentTimeMillis(); // Initialize startTime here
                setTextView();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
                milliseconds = 0;
                textView.setText("00:00:00:00:000"); // Reset the display
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove any remaining callbacks to avoid potential memory leaks
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public void setTextView() {
        textView = findViewById(R.id.reding);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    long currentTime = System.currentTimeMillis();
                    long elapsedTime = currentTime - startTime;
                    int days = (int) (elapsedTime / (1000 * 60 * 60 * 24));
                    int hours = (int) ((elapsedTime / (1000 * 60 * 60)) % 24);
                    int minutes = (int) ((elapsedTime / (1000 * 60)) % 60);
                    int seconds = (int) ((elapsedTime / 1000) % 60);
                    int milliseconds = (int) (elapsedTime % 1000);

                    String formatString = String.format(Locale.getDefault(), "%02d:%02d:%02d:%02d:%03d", days, hours, minutes, seconds, milliseconds);
                    textView.setText(formatString);
                }
                handler.postDelayed(this, 10); // Delayed by 10 milliseconds
            }
        });
    }
}
