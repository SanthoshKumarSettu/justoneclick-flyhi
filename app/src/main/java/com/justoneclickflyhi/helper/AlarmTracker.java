package com.justoneclickflyhi.helper;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.justoneclickflyhi.manager.PrintStream;

/**
 * Created by user on 11-06-2015.
 */
public class AlarmTracker {




    public static boolean CheckWakeUpAlarmActivationStatus(Context context) {
      boolean alarmUp = (PendingIntent.getBroadcast(context, 0,
                new Intent("com.justoneclickflyhi.manager.MyBroadcastReceiver"),
                PendingIntent.FLAG_NO_CREATE) != null);
        if (alarmUp == true)
        {
           PrintStream.PrintLog("Alarm is already active");
            return true;
        }
        PrintStream.PrintLog("Alarm is NOT active");
        return false;
    }

    public static void resetApp(Context context)
    {



    }
}


