package com.justoneclickflyhi.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.R.integer;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.justoneclickflyhi.SplashActivity;


public class SmsReceiver extends BroadcastReceiver implements LocationListener{


    Handler handler=new Handler();
    String msgBody="";
    int A_Hour,A_Min,A_Day,A_Month,A_Year;
    int S_Hour,S_Min,S_Day,S_Month,S_Year;


    //Constants constants;
    @Override
    public void onReceive(final Context context, Intent intent) {
        //context = context1;
        //System.out.println("INTO SMS ON RECEIVE");
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){

            String message=intent.getExtras().getString("");

            Bundle bundle = intent.getExtras();


            SmsMessage[] msgs = null;
            String msg_from;
            if (bundle != null){
                //---retrieve the SMS message received---
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i1=0; i1<msgs.length; i1++){
                        msgs[i1] = SmsMessage.createFromPdu((byte[])pdus[i1]);
                        msg_from = msgs[i1].getOriginatingAddress();
                        msgBody = msgs[i1].getMessageBody();

                        Log.d("Compete Message",""+ msgBody.substring(0,msgBody.length()));



                        if(msg_from.equals(Constants.NUMBER)){
                            System.out.println("INTO SMS IF LOOP");

                            ///check for message type its GT or Ping type P030

                            if(msgBody.substring(0, 2).equals("GT")){
                                System.out.println("INTO SMS IF GT LOOP");


                                ParseString();
                                formatAwake();
                                fomatSleep();
                                Application app = new Application();
                                String BOOT_MANAGER = app.check().toString();






//                		 Calendar calendar = Calendar.getInstance();
//
//               	      calendar.set(Calendar.MONTH, 5);
//               	      calendar.set(Calendar.YEAR, 2015);
//               	      calendar.set(Calendar.DAY_OF_MONTH, 18);
//
//               	      calendar.set(Calendar.HOUR_OF_DAY, 11);
//               	      calendar.set(Calendar.MINUTE, 03);
//               	      calendar.set(Calendar.SECOND, 0);
//               	      //calendar.set(Calendar.AM_PM,Calendar.AM);
//
//
//                		 Intent i22 = new Intent(context, SplashScreenActivity.class);
//                         context.startService(i22);
//
//                         AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
//                         PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,i22,PendingIntent.FLAG_UPDATE_CURRENT);
//                         am.set(AlarmManager.RTC
//                         		, calendar.getTimeInMillis() ,pendingIntent);
//                         System.out.println(" calendar.getTimeInMillis() : "+ calendar.getTimeInMillis());
//                         Toast.makeText(context,"AFTER THE SET ALARM CODE",Toast.LENGTH_LONG).show();


                                if(BOOT_MANAGER.equals("ACTIVATE_FUTURE_ALARAM")){

                                    System.out.println("ACTIVATE FOR FUTURE");
                                    Toast.makeText(context,"ACTIVATE FOR FUTURE",Toast.LENGTH_LONG).show();

                                    /***
                                     *
                                     * insist the uers to thanks for using the or no need
                                     * Set the background alarm here depending on the awake time date and sleep date time
                                     *
                                     **/
                                    //AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);






                                }
                                else if (BOOT_MANAGER.equals("ACTIVATE_NOW")){
                                    Intent i	=	new Intent(context,SplashActivity.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    i.putExtra("SMSCALL", "SMSCALL");
                                    context.startActivity(i);

//                                    LocationManager locationManager=(LocationManager)context.getSystemService(context.LOCATION_SERVICE);
//                                    boolean enabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//                                    if(enabled) {
//                                        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                                        SmsManager smsManager=SmsManager.getDefault();
//
//                                        smsManager.sendTextMessage(Constants.NUMBER, "", "G"+location.getLatitude()+ ":"+"G"+location.getLongitude(), null, null);
//                                    }else{
//
//                                        Toast.makeText(context,"Disabled",Toast.LENGTH_LONG).show();
//
//
//                                    }

                                    final String finalMsg_from = msg_from;
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            deleteSMS(context, msgBody, finalMsg_from);
                                        }
                                    },5000);
                                    System.out.println("ACTIVATE NOw FROM SMS RECEIVER");
                                    Toast.makeText(context,"ACTIVATE NOw FROM SMS RECEIVER",Toast.LENGTH_LONG).show();

                                } else if (BOOT_MANAGER.equals("EXIT")){
                                    /**
                                     * reset all previous values an
                                     * **/
                                    System.out.println("EXIT FROM SMS RECEIVER");
                                    Toast.makeText(context,"EXIT FROM SMS RECEIVER",Toast.LENGTH_LONG).show();
                                }


                            }

                            else if (msgBody.substring(0, 1).equals("P")){

                                Toast.makeText(context,"PING MESSAGE ",Toast.LENGTH_LONG).show();
                                /**
                                 * set the repeat value to the alarm
                                 *
                                 * ***/


                            }




                        }
                    }
                }catch(Exception e){
                    Log.d("Exception caught",e.getMessage());
                }
            }

        }
    }






    private void fomatSleep() {
        System.out.println("INTO fomatSleep");
        String SMS_SLEEP_SMS =Constants.SLEEP_TIME;

        Constants.SLEEP_DATE_DAY = SMS_SLEEP_SMS.substring(4, 6);
        Constants.SLEEP_DATE_MONTH = SMS_SLEEP_SMS.substring(6, 8);
        Constants.SLEEP_DATE_YEAR = "20"+SMS_SLEEP_SMS.substring(8, SMS_SLEEP_SMS.length()).trim();

        Constants.SLEEP_TIME_HOUR = SMS_SLEEP_SMS.substring(0, 2);
        Constants.SLEEP_TIME_MIN = SMS_SLEEP_SMS.substring(2, 4);
        //Constants.SLEEP_TIME_SECOND = "00";

        Constants.DATE_STOP = Constants.SLEEP_DATE_DAY+Constants.SLEEP_DATE_MONTH+Constants.SLEEP_DATE_YEAR
                +Constants.SLEEP_TIME_HOUR+Constants.SLEEP_TIME_MIN;



    }
    private void formatAwake() {
        System.out.println("INTO fomatAWAKE");
        String SMS_AWAKE_SMS =Constants.AWAKE_TIME;

        Constants.AWAKE_DATE_DAY = SMS_AWAKE_SMS.substring(4, 6);
        Constants.AWAKE_DATE_MONTH = SMS_AWAKE_SMS.substring(6, 8);
        Constants.AWAKE_DATE_YEAR = "20"+SMS_AWAKE_SMS.substring(8, SMS_AWAKE_SMS.length()).trim();

        Constants.AWAKE_TIME_HOUR = SMS_AWAKE_SMS.substring(0, 2);
        Constants.AWAKE_TIME_MIN = SMS_AWAKE_SMS.substring(2, 4);
        //Constants.AWAKE_TIME_SECOND = "00";

        Constants.DATE_START = Constants.AWAKE_DATE_DAY + Constants.AWAKE_DATE_MONTH + Constants.AWAKE_DATE_YEAR
                + Constants.AWAKE_TIME_HOUR +Constants.AWAKE_TIME_MIN;

    }
    private void ParseString() {
        System.out.println("INTO ParseString");

        //reading the message received and setting to variables
        Constants.MESSAGE 				= msgBody.substring(0,msgBody.length());
        Constants.AWAKE_INDEX 			= msgBody.substring(0,2);
        Constants.AWAKE_TIME 			= msgBody.substring(2,12);
        Constants.SLEEP_TIME 			= msgBody.substring(12, 22);
        Constants.PING_FREQUENCY_TIME 	= msgBody.substring(23, 26);
        Constants.PING_MESSAGE 			= msgBody.substring(26, msgBody.length());

    }

    public void deleteSMS(Context context, String message, String number) {
        try {
            // mLogger.logInfo("Deleting SMS from inbox");


            Uri uriSms = Uri.parse("content://sms/inbox");


            Cursor c = context.getContentResolver().query(uriSms,
                    new String[] { "_id", "thread_id", "address",
                            "person", "date", "body" }, null, null, null);

            if (c != null && c.moveToFirst()) {
                do {
                    long id = c.getLong(0);
                    long threadId = c.getLong(1);
                    String address = c.getString(2);
                    String body = c.getString(5);

                    if (address.equals(number))
                    {

                        // mLogger.logInfo("Deleting SMS with id: " + threadId);
                        context.getContentResolver().delete(
                                Uri.parse("content://sms/conversations/" + threadId ), null, null);
                        Log.e("log", "" + id +address +threadId);
                    }
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("log", "" +e.getMessage());
            // mLogger.logError("Could not delete SMS from inbox: " + e.getMessage());
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {



    }


    private String getCurrentTimeFormat(String timeFormat){
        String time = "";
        SimpleDateFormat df = new SimpleDateFormat(timeFormat);
        Calendar c = Calendar.getInstance();
        time = df.format(c.getTime());

        return time;
    }

}

