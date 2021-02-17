package com.example.expandablelistviewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = (ProgressBar)findViewById(R.id.progressbar_id);
        Thread thread = new Thread() {
            @Override
            public void run() {
                doWork();
                startApp();
            }
        };
        thread.start();
    }

    public  void  doWork(){
        for (progresses = 1; progresses<=100; progresses=progresses+1){
            try {
                Thread.sleep(40);
                progressBar.setProgress(progresses);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void startApp(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}