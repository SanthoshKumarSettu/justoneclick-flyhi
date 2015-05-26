package com.justoneclickflyhi;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.justoneclickflyhi.helper.AlarmSettings;
import com.justoneclickflyhi.helper.Application;
import com.justoneclickflyhi.helper.Constants;
import com.justoneclickflyhi.helper.SessionStore;
import com.justoneclickflyhi.helper.SmsSender;
import com.justoneclickflyhi.manager.AlertDialogManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeActivity extends ActionBarActivity {

Context context;
    String reminderStatus;
    String newString;
    TextView tt;
    AlertDialogManager dialog;
    Application app = new Application();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_home);
        // Set a toolbar to replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
                Log.d("newString :",newString);
            } else {
                newString= extras.getString("STRING_I_NEED");
                Log.d("newString :",newString);
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
            Log.d("newString :",newString);
        }
        tt = (TextView)findViewById(R.id.textView_status);

        app = new Application();

        Toast.makeText(HomeActivity.this, "newString :"+newString+"\n"+Constants.REMINDER, Toast.LENGTH_LONG).show();

        Constants.REMINDER = SessionStore.getAlarm(context);
        reminderStatus = Constants.REMINDER;
        if(newString.equals("splash")){



            Toast.makeText(HomeActivity.this, "i came from splash", Toast.LENGTH_LONG).show();


                if ((SessionStore.getAlarm(context) == null || SessionStore
                    .getAlarm(context).equals(""))){
                dialog = new AlertDialogManager();
                dialog.showAlertDialog(HomeActivity.this, "Just One Click - Fly Hi", "Welcome to Just one Click!" + "\n" +

                        " please stay tuned for updates" + "\n"+"SessionStore.getAlarm is null"

                        , true);
                Toast.makeText(HomeActivity.this, "reminderStatus : "+reminderStatus, Toast.LENGTH_LONG).show();
                } else {

                Toast.makeText(HomeActivity.this, "SessionStore.getAlarm is NOT NULL : "+reminderStatus, Toast.LENGTH_LONG).show();
                    CheckReminderStatus();



                }
        }else if(newString.equals("smscall"))
        {

            CheckReminderStatus();

        }
    }///////////////////ON-CREATE END BRACKET

    private void CheckReminderStatus() {



        if(Constants.REMINDER.equalsIgnoreCase("ACTIVATE_FUTURE_ALARAM")){
            // Toast.makeText(HomeActivity.this, "i came from sms broadcast", Toast.LENGTH_LONG).show();
            dialog = new AlertDialogManager();
            dialog.showAlertDialog(HomeActivity.this, "Just One Click - Fly Hi", "ACTIVATE_FUTURE_ALARAM" + "\n" +

                    "Welcome to Just one Click!" + "\n" +
                    " please stay tuned for updates" + "\n" +
                    "Constants.PING_REPEAT_INT" +Constants.PING_REPEAT_INT

                    , true);

            AlarmSettings.setFirstAlarm(context);
            AlarmSettings.startRepeatingAlarm(context);

        } else if(Constants.REMINDER.equalsIgnoreCase("ACTIVATE_NOW")){
            dialog = new AlertDialogManager();
            dialog.showAlertDialog(HomeActivity.this, "Just One Click - Fly Hi", "!" + "\n" + Constants.PING_MESSAGE, true);
        } else if(Constants.REMINDER.equalsIgnoreCase("EXIT")){
            dialog = new AlertDialogManager();
            dialog.showAlertDialog(HomeActivity.this, "Just One Click - Fly Hi", "Welcome to Just one Click!" + "\n" + " please stay tuned for updates", true);
        } else if(Constants.REMINDER.equalsIgnoreCase("ACTIVATED")){
            dialog = new AlertDialogManager();
            dialog.showAlertDialog(HomeActivity.this, "Just One Click - Fly Hi", "!" + "\n" + Constants.PING_MESSAGE, true);
        } else if (Constants.REMINDER.equalsIgnoreCase("SMS_ERROR")){
            ///SMS PARSING ERROR SEND THAT SOME THING WENT WRONG
            //Toast.makeText(HomeActivity.this, "SessionStore.getAlarm"+reminderStatus, Toast.LENGTH_LONG).show();

            dialog = new AlertDialogManager();
            dialog.showAlertDialog(HomeActivity.this, "Just One Click - Fly Hi", "Welcome to Just one Click!" + "\n" + " please stay tuned for updates"+"\n"+"SMS_ERROR", true);
            //clearPref();
            Toast.makeText(HomeActivity.this, "SessionStore.getAlarm "+Constants.REMINDER, Toast.LENGTH_LONG).show();


        }



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            Toast.makeText(this,"Help!",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}