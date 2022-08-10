package com.example.servicios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.health.TimerStat;

import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask timerTask =new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(ActivitySplash.this,ActivityLogin.class);
                  startActivity(i);
                  finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,2000);

    }
}