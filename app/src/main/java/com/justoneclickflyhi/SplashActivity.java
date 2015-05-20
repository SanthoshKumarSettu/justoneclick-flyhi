package com.justoneclickflyhi;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TimerTask tt= new TimerTask() {

            @Override
            public void run() {
                finish();
               Intent goVal = new Intent(SplashActivity.this, HomeActivity.class);
               startActivity(goVal);

            }
        };
        Timer timer = new Timer();
        timer.schedule(tt, 1500);


    }


}
