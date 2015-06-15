package com.justoneclickflyhi;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.justoneclickflyhi.helper.AlarmSettings;
import com.justoneclickflyhi.helper.AlarmTracker;
import com.justoneclickflyhi.helper.Application;
import com.justoneclickflyhi.helper.Constants;
import com.justoneclickflyhi.helper.DateValid;
import com.justoneclickflyhi.helper.SessionStore;

import com.justoneclickflyhi.manager.AlertDialogManager;
import com.justoneclickflyhi.manager.GPSTracker;
import com.justoneclickflyhi.manager.PrintStream;
import com.justoneclickflyhi.manager.SmsDeliveryManager;
import com.justoneclickflyhi.manager.ToastManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends ActionBarActivity {

Context context;
    String reminderStatus;
    String newString;
    TextView tt;
    SmsDeliveryManager sms;
    GPSTracker gps;
    AlertDialogManager dialog;
    SessionStore sessionStore;
    AlarmSettings alarmSettings;
    AlarmTracker alarmTracker;
    Intent goNotifyActivity;
    DateValid dateValid;

    Button gpsSettingsButton,gpsCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_home);
        // Set a toolbar to replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

       /* if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
                Log.d("newString :",newString);
            } else {
                newString= extras.getString("STRING_I_NEED");
//                Log.d("newString :",newString);
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
            if(newString != null){
                 Log.d("newString :",newString);
            }
        }*/
        tt = (TextView)findViewById(R.id.textView_status);


        Toast.makeText(HomeActivity.this, SessionStore.getAlarm(context).toString(), Toast.LENGTH_LONG).show();

         if ((SessionStore.getAlarm(context).toString()
                 == null || SessionStore.getAlarm(context).toString().equals("")))
                {
                    Toast.makeText(HomeActivity.this, "i came from splash"+"\n", Toast.LENGTH_LONG).show();
                    dialog = new AlertDialogManager();
                    dialog.showAlertDialog(HomeActivity.this, "Just One Click - Fly Hi",
                            "Welcome to Just one Click!" + "\n" + " please stay tuned for updates" + "\n" + "SESSION NULL", true);
                   try{
                       callSetText();
                   } catch (NullPointerException  n){
                       Toast.makeText(HomeActivity.this, "NullPointerException on null value "+"\n", Toast.LENGTH_LONG).show();
                   }

                }
                else
                {
                    ToastManager.showToast(context, "SESSION :" + SessionStore.getAlarm(context).toString());
                    CheckReminderStatus();
                    callSetText();
                }

    }///////////////////ON-CREATE END BRACKET



    public void CheckReminderStatus() {

        if (SessionStore.getAlarm(context).toString().equalsIgnoreCase("DEFAULT"))
        {


           /* //AlarmSettings.setWakeUpAlarm(context,"ON");
    //---------------------------------------------------------------------------------------//
            Application application = new Application();
            String BOOT_MANAGER = application.check(context).toString();

            PrintStream.PrintLog("BOOT_MANAGER "+BOOT_MANAGER);

            if(BOOT_MANAGER.equals("ACTIVATE_FUTURE_ALARAM"))
            {

                PrintStream.PrintLog("WAIT FOR ALARM ACTIVATED");
                sessionStore.getPrefConstants(context);
                sessionStore.setAlarm("WAIT", context);
                Toast.makeText(context,"WAIT FOR ALARM",Toast.LENGTH_SHORT).show();
                alarmSettings.setWakeUpAlarm(context, "ON");
                alarmSettings.setSleepAlarm(context);

            }
            else  if (BOOT_MANAGER.equalsIgnoreCase("ACTIVATE_NOW"))
            {
                sessionStore.setAlarm("ACTIVATED", context);
                alarmSettings.setRepeatingAlarm(context,PendingIntent.FLAG_UPDATE_CURRENT, "ACTIVITYALERT");

            }
            else if (BOOT_MANAGER.equals("EXIT"))
            {
                *//***DATE AND TIME OUT OF DATE SO EXIT AND IGNORE THE SESSION**//*
                sessionStore.clearPref(context);
                sessionStore.setAlarm("DEFAULT", context);
                Toast.makeText(context,"EXIT FROM SMS RECEIVER",Toast.LENGTH_LONG).show();
            }
            else if (BOOT_MANAGER.equals("SMS_ERROR"))
            {
                    *//*** reset all previous values an**//*
                    sessionStore.clearPref(context);
                    sessionStore.setAlarm("DEFAULT",context);
                    PrintStream.PrintLog("EXIT FROM SMS RECEIVER");
                    //RESET THE SESSION VALUES AND SEND HELP SMS
                    Toast.makeText(context,"EXIT FROM SMS RECEIVER",Toast.LENGTH_LONG).show();



            }
//----------------------------------------------------------------------------------------------------//*/

            dialog = new AlertDialogManager();
            dialog.showAlertDialog(HomeActivity.this, "- Fly Hi", "FLY HI " + "\n" +
                    "Welcome to Just one Click!" + "\n" +
                    " please stay tuned for updates SESSION IS DEFAULT" + "\n"
                    , true);
        }
        else if(SessionStore.getAlarm(context).toString().equalsIgnoreCase("WAIT"))
        {
           dialog = new AlertDialogManager();

            dialog.showAlertDialog(HomeActivity.this, "- Fly Hi", "FLY HI " + "\n" +
                    "Welcome to Just one Click!" + "\n" +
                    " please stay tuned shortly text \n SESSION IS WAIT  " + "\n Message Receiveed for first launch"
                    , true);
            /*alarmTracker.CheckWakeUpAlarmActivationStatus(context);*/
        }
        else if (SessionStore.getAlarm(context).toString().equalsIgnoreCase("ACTIVATED"))
        {
            //AlarmSettings.setRepeatingAlarm(context,
                    //PendingIntent.FLAG_UPDATE_CURRENT,"NOTIFY");

            dialog = new AlertDialogManager();
            dialog.showAlertDialog(HomeActivity.this, "- Fly Hi", "FLY HI " + "\n" +
                    "Welcome to Just one Click!" + "\n" +
                    SessionStore.getReceivedMessage(context).toString() + "\n"
                    , true);



        }
        else if (SessionStore.getAlarm(context).toString().equalsIgnoreCase("NOTIFY_ON"))
        {
            finish();
            goNotifyActivity = new Intent(HomeActivity.this,NotificationActivity.class);
            goNotifyActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(goNotifyActivity);
         }
        else if (SessionStore.getAlarm(context).toString().equalsIgnoreCase("MESSAGE_ON"))
        {
            finish();
            goNotifyActivity = new Intent(HomeActivity.this,MessageActivity.class);
            goNotifyActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(goNotifyActivity);
        }
        else if (SessionStore.getAlarm(context).toString().equalsIgnoreCase("END"))
        {
            //com.justoneclickflyhi.helper.SessionStore.clearPref(context);
            PrintStream.PrintLog("INTO END SESSION");
            finish();
            dialog = new AlertDialogManager();
            dialog.showAlertDialog(HomeActivity.this, "- Fly Hi", "FLY HI " + "\n" +
                    "Just one Click!" + "\n" +
                    "Thanks For using Just one Click !" + "\n Have a Good Journey!"
                    , true);
            SessionStore.setAlarm("DEFAULT",context);
        }
        else if (SessionStore.getAlarm(context).toString().equalsIgnoreCase("GPS_ON"))
        {
            // create class object
            gps = new GPSTracker(HomeActivity.this);

            // check if GPS enabled
            if(gps.canGetLocation()){

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();

                // \n is for new line
                //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                dialog = new AlertDialogManager();
                dialog.showAlertDialog(HomeActivity.this, "- Fly Hi", "FLY HI " + "\n" +
                        "Just one Click!" + "\n" +
                        SessionStore.getReceivedMessage(context).toString()
                        , true);
                sms.sendGPSMessage(context, "G" + String.valueOf(latitude) + "G2" + String.valueOf(longitude));
                SessionStore.setAlarm("DEFAULT", context);
                alarmSettings.setRepeatingAlarm(context, PendingIntent.FLAG_CANCEL_CURRENT, "REPEATBG");

            }else{
                // can't get location
                // GPS or Network is not enabled
                // Ask user to enable GPS/network in settings
                setContentView(R.layout.activity_notification);
                gpsSettingsButton = (Button)findViewById(R.id.button_NROK);
                gpsSettingsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(intent, 1);

                    }
                });
                gpsCancelButton   = (Button)findViewById(R.id.button_NRNO);
                gpsCancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog = new AlertDialogManager();
                        dialog.showAlertDialog(HomeActivity.this, "- Fly Hi", "FLY HI " + "\n" +
                                "Warning !" + "\n" +
                                "you cant ignore this please turn on gps!" + "\n you a unread message!"
                                , true);

                    }
                });
        }




        }



    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            // create class object
            gps = new GPSTracker(context);
            if(gps.canGetLocation()){

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();

                // \n is for new line
                //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                PrintStream.PrintLog("onActivityResult from settings");
                PrintStream.PrintLog("INTO END SESSION");

                dialog = new AlertDialogManager();
                dialog.showAlertDialog(HomeActivity.this, "- Fly Hi", "FLY HI " + "\n" +
                        "Just one Click!" + "\n" +
                        "Thanks For using Just one Click !" + "\n Have a Good Journey!"
                        , true);
                sms.sendGPSMessage(context, "G" + String.valueOf(latitude) + "G2" + String.valueOf(longitude));
                SessionStore.setAlarm("DEFAULT", context);
                alarmSettings.setRepeatingAlarm(context, PendingIntent.FLAG_CANCEL_CURRENT, "REPEATBG");


            }else{
                dialog = new AlertDialogManager();
                dialog.showAlertDialog(HomeActivity.this, "- Fly Hi", "FLY HI " + "\n" +
                        "Just one Click!" + "\n" +
                        "Please turn on your GPS !" + "\n You have a UnRead message!"
                        , true);
            }



        }
    }

    public void callSetText(){

        if(SessionStore.getReceivedMessage(context)==null || SessionStore.getReceivedMessage(context).equalsIgnoreCase("")){

            tt.setText("This is only for developer preview \n No Message Received Yet, Session Null");
        }
        else
        {
            SessionStore.getPrefConstants(context);
            tt.setText(
                    "This is for developer view only" + "\n"+
                            Constants.MESSAGE + "\n"+
                            Constants.MESSAGE_TYPE + "\n"+
                            Constants.REMINDER + "\n"+ "Received AWAKE Date & Time \n"+
                            Constants.AWAKE_TIME_HOUR +":"+ Constants.AWAKE_TIME_MIN + " -" + Constants.AWAKE_DATE_DAY+"/"+Constants.AWAKE_DATE_MONTH +"/"+
                            Constants.AWAKE_DATE_YEAR + "\n"+  "\n"+ "Received SLEEP Date & Time \n"+
                            Constants.SLEEP_TIME_HOUR + Constants.SLEEP_TIME_MIN + "\n"+
                            Constants.SLEEP_DATE_DAY + Constants.SLEEP_DATE_MONTH + Constants.SLEEP_DATE_YEAR + " \n Received Message \n"+
                            Constants.PING_MESSAGE +"\n"+
                            "PING TIME :"+  Constants.PING_FREQUENCY_TIME +"\n PARSED Awake Date & Time \n"+
                            Constants.HH + ":"+ Constants.MIN + " - "+
                            Constants.DAY+ "/" + Constants.MONTH+"/" +  Constants.YEAR+ "\n PING REPEAT \n"+
                            Constants.PING_REPEAT_INT

            );


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

    @Override
    public void onBackPressed() {


        super.onBackPressed();
        /*dialog = new AlertDialogManager();
        dialog.showAlertDialog(context, "- Fly Hi", "FLY HI " + "\n" +
                "Just one Click!" + "\n" +
                "Are you sure you need to exit this app" + "\n "
                , true);*/
       /* Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);*/

        ToastManager.showToast(context,"Back Pressed");
    }
}