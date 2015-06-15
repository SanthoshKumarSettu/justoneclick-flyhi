package com.justoneclickflyhi.helper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;


import com.justoneclickflyhi.manager.MyBroadcastReceiver;
import com.justoneclickflyhi.manager.PrintStream;
import com.justoneclickflyhi.manager.ToastManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AlarmSettings  {

    public static AlarmManager wakeUpAlarm;
    static Intent wakeUpIntent;
    public static PendingIntent wakePendingIntent;

    public static AlarmManager sleepAlarm;
    static Intent sleepIntent;
    public static PendingIntent sleepPendingIntent;


    public static AlarmManager repeatAlarm;
    static Intent repeatIntent;
    public static PendingIntent repeatPendingIntent;

    public static PrintStream printStream;
    static ToastManager ToastManager;

    public AlarmSettings()
    {
        super();
    }

    public static void setWakeUpAlarm(Context context,String ONOFF)
    {

        if(ONOFF.equalsIgnoreCase("ON"))
        {
            printStream.PrintLog("Wake up alarm got ON");
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm ddMMyyyy");
            int HH = Integer.parseInt(SessionStore.getHourAwake(context).toString());
            int  MIN = Integer.parseInt(SessionStore.getMinuteAwake(context).toString());
            int DAY= Integer.parseInt(SessionStore.getDayAwake(context).toString());
            int MONTH=Integer.parseInt(SessionStore.getMonthAwake(context).toString());
            int YEAR=Integer.parseInt(SessionStore.getYearAwake(context).toString());

            printStream.PrintLog("HH:MM D:M:Y = " + HH + ":" + MIN + " " + DAY + ":" + MONTH + ":" + YEAR);

            Calendar received = Calendar.getInstance();
            received.setTimeInMillis(System.currentTimeMillis());
            received.set(Calendar.YEAR, YEAR);
            received.set(Calendar.MONTH, MONTH-1);
            received.set(Calendar.DAY_OF_MONTH, DAY);
            received.set(Calendar.HOUR_OF_DAY, HH);
            received.set(Calendar.MINUTE, MIN);

            printStream.PrintLog("Calendar received " + dateFormat.format(received.getTime()));

            wakeUpAlarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            wakeUpIntent = new Intent(context, MyBroadcastReceiver.class);
            wakeUpIntent.putExtra("extraMessage", "WAKEUP");
            wakePendingIntent = PendingIntent.getBroadcast(  context.getApplicationContext(), 256987, wakeUpIntent,PendingIntent.FLAG_ONE_SHOT);

            wakeUpAlarm.set(AlarmManager.RTC_WAKEUP, received.getTimeInMillis(), wakePendingIntent);

            printStream.PrintLog(String.valueOf(received.getTimeInMillis()));

            ToastManager.showToast(context,"Alaram set on: "+dateFormat.format(received.getTime()) );

        }
        else if (ONOFF.equalsIgnoreCase("OFF"))
        {
            wakeUpAlarm.cancel(wakePendingIntent);
            ToastManager.showToast(context, "Wake up alarm got OFF");
            printStream.PrintLog("Wake up alarm got OFF");
        }
        else if (ONOFF.equalsIgnoreCase("")) { ToastManager.showToast(context, "ONOFF == NULL "); }
        else {ToastManager.showToast(context, "ONOFF wrong value"); }
       }

    public static void setRepeatingAlarm(Context context, int i,String extra)
    {
        if(extra.equals("END"))
        {
            printStream.PrintLog("Repeat Alarm OFF");
            repeatAlarm.cancel(repeatPendingIntent);
            ToastManager.showToast(context, "Repeat Alarm OFF with : "+extra.toString());
        }
        else
        {

            printStream.PrintLog("Repeat Alarm ON :--> " + extra.toString());
            Constants.PING_REPEAT_INT = Integer.parseInt(SessionStore.getPingInterval(context).toString());
            printStream.PrintLog("PING INTERVAL : "+Constants.PING_REPEAT_INT);
            repeatAlarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            repeatIntent = new Intent(context, MyBroadcastReceiver.class);
            repeatIntent.putExtra("extraMessage", extra);
            repeatPendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 0, repeatIntent, i);
            repeatAlarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), Constants.PING_REPEAT_INT * 60 * 1000, repeatPendingIntent);

            printStream.PrintLog("Constants.PING_REPEAT_INT : "+Constants.PING_REPEAT_INT
                    +" * 60* 1000 == "+String.valueOf(Constants.PING_REPEAT_INT*60*1000));

            ToastManager.showToast(context, "Repeat Alarm ON with :"+extra.toString()+"\n"+Constants.PING_REPEAT_INT+"\t minutes");
        }
    }


    public static void setSleepAlarm(Context context)
    {

        printStream.PrintLog("set Sleep Alarm ON");

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm ddMMyyyy");

        int HH = Integer.parseInt(SessionStore.getHourSleep(context).toString());
        int  MIN = Integer.parseInt(SessionStore.getMinuteSleep(context).toString());
        int DAY= Integer.parseInt(SessionStore.getDaySleep(context).toString());
        int MONTH=Integer.parseInt(SessionStore.getMonthSleep(context).toString());
        int YEAR=Integer.parseInt(SessionStore.getYearSleep(context).toString());

        printStream.PrintLog("Sleep time : \t HH:MM D:M:Y = " + HH + ":" + MIN + " " + DAY + ":" + MONTH + ":" + YEAR);

        Calendar received = Calendar.getInstance();
        received.setTimeInMillis(System.currentTimeMillis());
        received.set(Calendar.YEAR, YEAR);
        received.set(Calendar.MONTH, MONTH-1);
        received.set(Calendar.DAY_OF_MONTH, DAY);
        received.set(Calendar.HOUR_OF_DAY, HH);
        received.set(Calendar.MINUTE, MIN);

        printStream.PrintLog("Calendar received " + dateFormat.format(received.getTime()));

        sleepAlarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        sleepIntent = new Intent(context, MyBroadcastReceiver.class);
        sleepIntent.putExtra("extraMessage", "END");
        sleepPendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 356987, sleepIntent,PendingIntent.FLAG_ONE_SHOT);

        sleepAlarm.set(AlarmManager.RTC_WAKEUP, received.getTimeInMillis(), sleepPendingIntent);

        printStream.PrintLog(String.valueOf(received.getTimeInMillis()));

        ToastManager.showToast(context,"Sleep Alaram set on: "+dateFormat.format(received.getTime()) );


    }
}
