package com.justoneclickflyhi;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.justoneclickflyhi.helper.SessionStore;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends Activity {
    String newString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= "splash";
                Log.d("newString :", newString);
            } else {
                newString= extras.getString("STRING_I_NEED");
                Log.d("newString :",newString);
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
            Log.d("newString :",newString);
        }


        TimerTask tt= new TimerTask() {

            @Override
            public void run() {
                finish();
               //SessionStore.saveInstallationStatus("SplashScreeen", SplashActivity.this);
               Intent goVal = new Intent(SplashActivity.this, HomeActivity.class);
               goVal.putExtra("STRING_I_NEED", newString);
               startActivity(goVal);

            }
        };
        Timer timer = new Timer();
        timer.schedule(tt, 1500);


    }


}
