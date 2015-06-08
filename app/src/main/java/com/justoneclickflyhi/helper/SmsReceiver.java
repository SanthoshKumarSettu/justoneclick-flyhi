package com.justoneclickflyhi.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.justoneclickflyhi.manager.PrintStream;
import com.justoneclickflyhi.manager.ToastManager;


public class SmsReceiver extends BroadcastReceiver {

    Handler handler = new Handler();
    String msgBody =" ";
    Application app;
    AlarmSettings alarmSettings;
    SessionStore sessionStore;
    String BOOT_MANAGER;
    @Override
    public void onReceive(final Context context, Intent intent) {
        Toast.makeText(context," Receiving SMS ",Toast.LENGTH_LONG).show();
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
                        Log.d("Compete Message",""+ msgBody.substring(0,msgBody.length()));
                    if(msg_from.equals("+919600413993")){
                        ToastManager.showToast(context,"Received SMS");

                            if(msgBody.substring(0, 2).equals("GT"))
                            {
                                ToastManager.showToast(context, "Received SMS" + "GT");
                                sessionStore.setPref(context, msgBody);

                                app = new Application();
                                BOOT_MANAGER = app.check(context).toString();
                                PrintStream.PrintLog("BOOT_MANAGER :"+BOOT_MANAGER);
                                ToastManager.showToast(context, "BOOT_MANAGER :" + BOOT_MANAGER);

                                if(BOOT_MANAGER.equals("ACTIVATE_FUTURE_ALARAM"))
                                {
                                    sessionStore.getPrefConstants(context);
                                    sessionStore.setAlarm("WAIT", context);
                                    Toast.makeText(context,"WAIT FOR ALARM",Toast.LENGTH_SHORT).show();
                                    alarmSettings.setWakeUpAlarm(context, "ON");
                                }
                                else if (BOOT_MANAGER.equals("ACTIVATE_NOW"))
                                {
                                final String finalMsg_from = msg_from;
                                    try
                                        {
                                            sessionStore.setAlarm("ACTIVATED", context);
                                            alarmSettings.setRepeatingAlarm(context,
                                                    PendingIntent.FLAG_UPDATE_CURRENT, "ACTIVITYALERT");
                                        }
                                    catch (Exception e)
                                        {
                                        PrintStream.PrintLog("Exception "+e);

                                        }

                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            deleteSMS(context, msgBody, finalMsg_from);
                                            Toast.makeText(context, "Deleting sms", Toast.LENGTH_LONG).show();
                                        }
                                    }, 5000);
                                }
                                else if (BOOT_MANAGER.equals("EXIT"))
                                {
                                    /***DATE AND TIME OUT OF DATE SO EXIT AND IGNORE THE SESSION**/
                                    sessionStore.clearPref(context);
                                    sessionStore.setAlarm("DEFAULT", context);
                                    Toast.makeText(context,"EXIT FROM SMS RECEIVER",Toast.LENGTH_LONG).show();
                                }
                                else if (BOOT_MANAGER.equals("SMS_ERROR"))
                                {
                                    /*** reset all previous values an**/
                                    sessionStore.clearPref(context);
                                    sessionStore.setAlarm("DEFAULT",context);
                                    PrintStream.PrintLog("EXIT FROM SMS RECEIVER");
                                    //RESET THE SESSION VALUES AND SEND HELP SMS
                                    Toast.makeText(context,"EXIT FROM SMS RECEIVER",Toast.LENGTH_LONG).show();
                                }
                            }
                            //******P INDEX SMS AFTER ACTIVTION*******//
                            else if (msgBody.substring(0, 1).equalsIgnoreCase("F"))
                            {
                                Toast.makeText(context,"PING MESSAGE ",Toast.LENGTH_LONG).show();
                                /*** set the repeat value to the alarm*****/
                                String ping_time = msgBody.substring(1,4);
                                String ping_type = msgBody.substring(4,6);
                                String ping_message = msgBody.substring(6,msgBody.length());

                                sessionStore.saveReceivedMessageType(ping_type, context);
                                sessionStore.savePingMessage(ping_message, context);
                                sessionStore.savePingInterval(ping_time, context);

                                if(ping_type.equalsIgnoreCase("A"))
                                {
                                    sessionStore.setAlarm("ACTIVATED", context);

                                    ToastManager.showToast(context,"A:Constants.PING_REPEAT_INT : " +
                                            SessionStore.getPingInterval(context).toString());
                                    alarmSettings.setRepeatingAlarm(context,
                                            PendingIntent.FLAG_CANCEL_CURRENT, "ACTIVITYALERT");

                                }
                                else if (ping_type.equalsIgnoreCase("N"))
                                {
                                    sessionStore.setAlarm("NOTIFY_ON", context);
                                    alarmSettings.setRepeatingAlarm(context,PendingIntent.FLAG_CANCEL_CURRENT,"NOTIFY");
                                }
                                else if(ping_type.equalsIgnoreCase("R"))
                                {
                                    sessionStore.setAlarm("MESSAGE_ON", context);
                                    alarmSettings.setRepeatingAlarm(context, PendingIntent.FLAG_CANCEL_CURRENT, "NOTIFY");
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