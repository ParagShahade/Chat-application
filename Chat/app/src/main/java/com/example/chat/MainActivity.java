package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ProgressBar progBar;
    Timer t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progBar=(ProgressBar) findViewById(R.id.pBar);
        t=new Timer();
        t.schedule(new TimerTask(){
            @Override
            public void run(){
                Intent i;
                i = new Intent(MainActivity.this,LoginApps.class);
                startActivity(i);
                finish();
            }
        }, 1000);


    }
}
