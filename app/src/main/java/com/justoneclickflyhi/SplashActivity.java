package com.justoneclickflyhi;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.justoneclickflyhi.helper.AlarmSettings;
import com.justoneclickflyhi.helper.Application;
import com.justoneclickflyhi.helper.Constants;
import com.justoneclickflyhi.helper.SessionStore;
import com.justoneclickflyhi.manager.PrintStream;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends Activity {
    String newString;
    Context context;
    Timer timer;
    TimerTask tt;
    SessionStore SessionStore;
    Application application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_splash);

        /*if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= "splash";
                Log.d("newString :", newString);
            } else {
                newString= extras.getString("STRING_I_NEED");
                if(newString != null) {
                    Log.d("newString :", newString);
                }
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
            Log.d("newString :", newString);
        }
*/

        tt = new TimerTask() {

            @Override
            public void run() {

                PrintStream.PrintLog("---------------------------------------------------------------------------------------");
                        PrintStream.PrintLog("Before If Loop Session is : "+SessionStore.getAlarm(context));

                        if ((SessionStore.getAlarm(context) == null || SessionStore.getAlarm(context).equals("")))
                        {
                            PrintStream.PrintLog("Setting Default Session ");
                            SessionStore.setAlarm("DEFAULT", context);
                            finish();
                            Intent goHome = new Intent(SplashActivity.this, HomeActivity.class);
                            //goHome.putExtra("STRING_I_NEED", newString);
                            startActivity(goHome);
                        }
                        else
                        {
                            //String msgBody = "GT13421106151345110615F999:AIgot activated";
                            //String msgBody = "GT45678910112313141F002:AIgot activated on 08:30-11-06-15";
                            //SessionStore.setPref(context, msgBody);

                            PrintStream.PrintLog("INTO ELSE");
                            finish();
                            Intent goHome = new Intent(SplashActivity.this, HomeActivity.class);
                            //goHome.putExtra("STRING_I_NEED", newString);
                            startActivity(goHome);
                            //PrintStream.PrintLog("ReceivedMessage :" + SessionStore.getReceivedMessage(context).toString());
                        }


           }


        };
        timer = new Timer();
        timer.schedule(tt, 1000);


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
