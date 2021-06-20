package com.yogeeswar.stopwatch;

import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int seconds=0;
    private boolean running=false;
    private boolean wasrunning=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null ) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasrunning = savedInstanceState.getBoolean("wasrunning");
        }
        timmer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasrunning", wasrunning);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(wasrunning)
            running = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasrunning = running;
        running=false;
    }
    public void onClickStart(View view) {
        if(!running)
            running=true;
    }
    public void onClickStop(View view) {
        if(running)
            running=false;
    }
    public void onClickReset(View view) {
        running=false;
        seconds=0;
    }
    private void timmer() {
        final TextView time = (TextView) findViewById(R.id.time);
        final Handler handler = new Handler();
        handler.post( new Runnable() {
            @Override
            public void run() {
                int hr = seconds/3600;
                int min = (seconds%3600)/60;
                int sec = seconds%60;
                String t = String.format(Locale.getDefault(), "%d:%02d:%02d",hr,min,sec);
                time.setText(t);
                if(running)
                    seconds++;
                handler.postDelayed(this,1000);
            }
        });
    }
}