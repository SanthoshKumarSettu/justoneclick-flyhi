package com.justoneclickflyhi.helper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by user on 26-05-2015.
 */
public class AlarmSettings {


    AlarmSettings(){


    }






    public static void setFirstAlarm(Context context){

        Calendar received = Calendar.getInstance();

        received.set(Calendar.YEAR, Constants.YEAR);
        received.set(Calendar.MONTH, Constants.MONTH-1);
        received.set(Calendar.DAY_OF_MONTH, Constants.DAY);

        received.set(Calendar.HOUR_OF_DAY, Constants.HH);
        received.set(Calendar.MINUTE, Constants.MIN);

        //received.set(2015, 05, 01);

        // format the output with leading zeros for days and month
        SimpleDateFormat R_date_format = new SimpleDateFormat("HH:mm ddMMyyyy");

        System.out.println(R_date_format.format(received.getTime()));
        Toast.makeText(context, "DATE :"+R_date_format.format(received.getTime()), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context, SmsSender.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, received.getTimeInMillis(), pendingIntent);

    }


    public static void repeatAlarm(Context context){
        Toast.makeText(context, "REPEATING ALARM SET", Toast.LENGTH_LONG).show();
        Calendar received = Calendar.getInstance();

        received.set(Calendar.YEAR, Constants.YEAR);
        received.set(Calendar.MONTH, Constants.MONTH-1);
        received.set(Calendar.DAY_OF_MONTH, Constants.DAY);

        received.set(Calendar.HOUR_OF_DAY, Constants.HH);
        received.set(Calendar.MINUTE, Constants.MIN);

        //received.set(2015, 05, 01);

        // format the output with leading zeros for days and month
        SimpleDateFormat R_date_format = new SimpleDateFormat("HH:mm ddMMyyyy");

        System.out.println(R_date_format.format(received.getTime()));
        Toast.makeText(context, "DATE :"+R_date_format.format(received.getTime()), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context, SmsSender.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
        //alarmManager.set(AlarmManager.RTC_WAKEUP, received.getTimeInMillis(), pendingIntent);

        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(),
                30000, pendingIntent);




    }

    public static void startRepeatingAlarm(Context context) {
        Toast.makeText(context, "Start Repeating Alarm", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context, SmsSender.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, 0);

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        //int interval = 1000 * 60 * 20;

        /* Set the alarm to start at 10:30 AM */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.YEAR, Constants.YEAR);
        calendar.set(Calendar.MONTH, Constants.MONTH-1);
        calendar.set(Calendar.DAY_OF_MONTH, Constants.DAY);

        calendar.set(Calendar.HOUR_OF_DAY, Constants.HH);
        calendar.set(Calendar.MINUTE, Constants.MIN);

        /* Repeating on every 10 minutes interval */
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 2, pendingIntent);
    }


}
