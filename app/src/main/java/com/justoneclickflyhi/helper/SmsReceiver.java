package com.justoneclickflyhi.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.justoneclickflyhi.MessageActivity;
import com.justoneclickflyhi.NotificationActivity;
import com.justoneclickflyhi.SplashActivity;
import com.justoneclickflyhi.manager.GPSTracker;
import com.justoneclickflyhi.manager.PrintStream;
import com.justoneclickflyhi.manager.SmsDeliveryManager;
import com.justoneclickflyhi.manager.ToastManager;
import com.justoneclickflyhi.manager.TowerTracker;


public class SmsReceiver extends BroadcastReceiver {

    Handler handler = new Handler();
    String msgBody =" ";
    Application app;
    AlarmSettings alarmSettings;
    SessionStore sessionStore;
    String BOOT_MANAGER;
    ToastManager toastManager;
    PrintStream PrintStream;
    SmsDeliveryManager sms;
    GPSTracker gps;
    TowerTracker towerTracker;
    @Override
    public void onReceive(final Context context, Intent intent) {
        Toast.makeText(context," Receiving SMS ",Toast.LENGTH_LONG).show();
        alarmSettings = new AlarmSettings();
        sms = new SmsDeliveryManager();
        gps = new GPSTracker(context);
        towerTracker = new TowerTracker(context);
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
        {
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
                        PrintStream.PrintLog("FULL MESSAGE" + msgBody.substring(0, msgBody.length()));
                    if(msg_from.equals("+918861273211")){

                        ToastManager.showToast(context,"Received SMS : 8861273211");
                        PrintStream.PrintLog("Received SMS : 8861273211");

                            if(msgBody.substring(0, 2).equals("GT"))
                            {
                                SessionStore.setPref(context,msgBody);
                            ToastManager.showToast(context, "Received SMS" + "GT");
                                PrintStream.PrintLog("Setting up the Preference");
                                app = new Application();
                                BOOT_MANAGER = app.check(context).toString();
                                PrintStream.PrintLog("BOOT_MANAGER :" + BOOT_MANAGER);
                                toastManager.showToast(context, "BOOT_MANAGER :" + BOOT_MANAGER);

                                if(BOOT_MANAGER.equals("ACTIVATE_FUTURE_ALARAM"))
                                {
                                    sessionStore.getPrefConstants(context);
                                    sessionStore.setAlarm("WAIT", context);
                                    alarmSettings.setWakeUpAlarm(context, "ON");
                                    alarmSettings.setSleepAlarm(context);
                                }
                                else if (BOOT_MANAGER.equals("ACTIVATE_NOW"))
                                {
                                    sessionStore.setAlarm("ACTIVATED", context);
                                    alarmSettings.setRepeatingAlarm(context, PendingIntent.FLAG_UPDATE_CURRENT, "ACTIVITYALERT");
                                    alarmSettings.setSleepAlarm(context);


                                    Intent i = new Intent(context, SplashActivity.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(i);



                                    final String finalMsg_from = msg_from;
                                    try
                                        {

                                        }
                                    catch (Exception e)
                                        {
                                        PrintStream.PrintLog("Exception "+e);
                                        }
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //deleteSMS(context, msgBody, finalMsg_from);
                                            //Toast.makeText(context, "Deleting sms called", Toast.LENGTH_LONG).show();
                                        }
                                    }, 5000);
                                }
                                else if (BOOT_MANAGER.equals("EXIT"))
                                {
                                    /***DATE AND TIME OUT OF DATE SO EXIT AND IGNORE THE SESSION**/
                                    sessionStore.clearPref(context);
                                    sessionStore.setAlarm("DEFAULT", context);
                                    toastManager.showToast(context, "BOOT_MANAGER :" + BOOT_MANAGER);
                                }
                                else if (BOOT_MANAGER.equals("SMS_ERROR"))
                                {
                                    /*** reset all previous values an**/
                                    sessionStore.clearPref(context);
                                    sessionStore.setAlarm("DEFAULT",context);
                                    PrintStream.PrintLog("EXIT FROM SMS RECEIVER");
                                    //RESET THE SESSION VALUES AND SEND HELP SMS
                                    toastManager.showToast(context, "BOOT_MANAGER :" + BOOT_MANAGER);
                                }
                            }
                            //******P INDEX SMS AFTER ACTIVTION*******//
                            else if (msgBody.substring(0, 1).equalsIgnoreCase("F"))
                            {
                                Toast.makeText(context,"PING MESSAGE Received ",Toast.LENGTH_LONG).show();
                                PrintStream.PrintLog("Ping msg received :" + msgBody);

                                /** set the repeat value to the alarm****/

String ping_time = msgBody.substring(1,4);
String ping_type = msgBody.substring(5,6);
String ping_message = msgBody.substring(6,msgBody.length());

sessionStore.saveReceivedMessageType(ping_type, context);
        sessionStore.savePingMessage(ping_message, context);
        sessionStore.savePingInterval(ping_time, context);

        PrintStream.PrintLog("ping_time :" + ping_time);
        PrintStream.PrintLog("ping_type :"+ping_type);
        PrintStream.PrintLog("ping_message :" + ping_message);
        if(ping_type.equalsIgnoreCase("A"))
        {
            toastManager.showToast(context, "Alert Message received \t with" + SessionStore.getPingInterval(context).toString() + "\t interval");
            // check if GPS enabled
            if(gps.canGetLocation())
            {
                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
                sms.sendGPSMessage(context, "G" + String.valueOf(latitude) + ":G" + String.valueOf(longitude));
            }
            else
            {
                sms.sendGPSMessage(context, towerTracker.getTower().toString());
            }
            sessionStore.setAlarm("ACTIVATED", context);
            Intent i = new Intent(context, SplashActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            alarmSettings.setRepeatingAlarm(context, PendingIntent.FLAG_UPDATE_CURRENT, "REPEATBG");


        }
        else if (ping_type.equalsIgnoreCase("N"))
        {
            Intent i = new Intent(context, NotificationActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        sessionStore.setAlarm("NOTIFY_ON", context);
        toastManager.showToast(context, "Notification Message received \t with" + SessionStore.getPingInterval(context).toString() + "\t interval");
        alarmSettings.setRepeatingAlarm(context,PendingIntent.FLAG_CANCEL_CURRENT,"NOTIFY");
        }
        else if(ping_type.equalsIgnoreCase("R"))
        {
            Intent i = new Intent(context, MessageActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        sessionStore.setAlarm("MESSAGE_ON", context);
        toastManager.showToast(context, "Message received \t with" + SessionStore.getPingInterval(context).toString() + "\t interval");
        alarmSettings.setRepeatingAlarm(context, PendingIntent.FLAG_CANCEL_CURRENT, "NOTIFY");
        }
        else if(ping_type.equalsIgnoreCase("G"))
        {
            Intent i = new Intent(context, SplashActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            sessionStore.setAlarm("GPS_ON", context);
            toastManager.showToast(context, "Message received \t with" + SessionStore.getPingInterval(context).toString() + "\t interval");
            //alarmSettings.setRepeatingAlarm(context, PendingIntent.FLAG_CANCEL_CURRENT, "NOTIFY");
        }

                            }
                       }
                    }
                }catch(Exception e){
                    Log.d("Exception caught",e.getMessage());
                }
            }

        }
    }

    public void deleteSMS(Context context, String message, String number) {
        try {
             Uri uriSms = Uri.parse("content://sms/inbox");
             Cursor c = context.getContentResolver().query(uriSms,
                    new String[] { "_id", "thread_id", "address",
                            "person", "date", "body" }, null, null, null);
            if (c != null && c.moveToFirst())
            {
                do
                {
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
}



///--------------------------------

/*** set the repeat value to the alarm*****//*

String ping_time = msgBody.substring(1,4);
String ping_type = msgBody.substring(5,6);
String ping_message = msgBody.substring(6,msgBody.length());

sessionStore.saveReceivedMessageType(ping_type, context);
        sessionStore.savePingMessage(ping_message, context);
        sessionStore.savePingInterval(ping_time, context);

        PrintStream.PrintLog("ping_time :" + ping_time);
        PrintStream.PrintLog("ping_type :"+ping_type);
        PrintStream.PrintLog("ping_message :" + ping_message);
        if(ping_type.equalsIgnoreCase("A"))
        {
        sessionStore.setAlarm("ACTIVATED", context);
        toastManager.showToast(context, "Alert Message received \t with" + SessionStore.getPingInterval(context).toString() + "\t interval");
        alarmSettings.setRepeatingAlarm(context, PendingIntent.FLAG_CANCEL_CURRENT, "ACTIVITYALERT");

        }
        else if (ping_type.equalsIgnoreCase("N"))
        {
        sessionStore.setAlarm("NOTIFY_ON", context);
        toastManager.showToast(context, "Notification Message received \t with" + SessionStore.getPingInterval(context).toString() + "\t interval");
        alarmSettings.setRepeatingAlarm(context,PendingIntent.FLAG_CANCEL_CURRENT,"NOTIFY");
        }
        else if(ping_type.equalsIgnoreCase("R"))
        {
        sessionStore.setAlarm("MESSAGE_ON", context);
        toastManager.showToast(context, "Message received \t with" + SessionStore.getPingInterval(context).toString() + "\t interval");
        alarmSettings.setRepeatingAlarm(context, PendingIntent.FLAG_CANCEL_CURRENT, "NOTIFY");
        }*/
