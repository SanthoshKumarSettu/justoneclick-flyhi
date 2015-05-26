package com.justoneclickflyhi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.justoneclickflyhi.helper.Constants;
import com.justoneclickflyhi.helper.SessionStore;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends Activity {
    String newString;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
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
                String msgBody = "GT18582605151736270515F030:AYouHaveAFlightAt18:00HRS:";
                   SessionStore.setPref(SplashActivity.this,msgBody);
                   Intent goVal = new Intent(SplashActivity.this, HomeActivity.class);
                    goVal.putExtra("STRING_I_NEED", newString);
                    startActivity(goVal);




            }


        };
        Timer timer = new Timer();
        timer.schedule(tt, 1500);


    }





    /**
     *
     public String substring (int start, int end)
     Added in API level 1
     Returns a string containing the given subsequence of this string. The returned string shares this string's backing array.
     Parameters
     start
     the start offset.
     end
     the end+1 offset.
     Throws
     IndexOutOfBoundsException
     if start < 0, start > end or end > length(
     *
     * **/


}
