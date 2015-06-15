package com.justoneclickflyhi.manager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Toast;

import com.justoneclickflyhi.MessageActivity;
import com.justoneclickflyhi.NotificationActivity;
import com.justoneclickflyhi.SplashActivity;
import com.justoneclickflyhi.helper.AlarmSettings;
import com.justoneclickflyhi.helper.SessionStore;

public class MyBroadcastReceiver extends BroadcastReceiver {
	Context mContext;
	static GPSTracker gps;
	double latitude;
	double longitude;
	AlarmSettings alarmSettings;
	SmsDeliveryManager smsDeliveryManager;
	TowerTracker towerTracker;
	PrintStream log;
	Bundle extras;
	SessionStore SessionStore;
	
	@Override
	public void onReceive(Context context, Intent intent) {

		log.PrintLog("im into MyBroadCastReceiver");

		mContext = context;
		gps = new GPSTracker(mContext);
		towerTracker = new TowerTracker(mContext);

		extras = intent.getExtras();
	    String wrapper = extras.getString("extraMessage");
		log.PrintLog("Get Extra Wrapper is "+wrapper);


	    if(wrapper.equalsIgnoreCase("WAKEUP"))
	     {  log.PrintLog("MyBroadcast with : "+wrapper+" wraper");
	    	if(gps.canGetLocation())
	         	{
					latitude = gps.getLatitude();
 	    			latitude =gps.getLongitude();
 	    			smsDeliveryManager.sendGPSMessage(mContext, "G" + String.valueOf(latitude) + ":G" + String.valueOf(longitude));
					SessionStore.setAlarm("ACTIVATED", mContext);
					log.PrintLog("GPS ON | Message Delivered | Session set ACTIVATED");
					GoToSplash(context);

				}
 	    	else
 	    		{
					log.PrintLog("GPS OFF GETTING TOWER DETAILS");
 	    			smsDeliveryManager.sendTowerMessage(context, towerTracker.getTower().toString());
					SessionStore.setAlarm("ACTIVATED", mContext);
					GoToSplash(mContext);
					log.PrintLog("GPS OFF | Message Delivered | Session set ACTIVATED");
				}
			 alarmSettings.setRepeatingAlarm(mContext, PendingIntent.FLAG_UPDATE_CURRENT,"REPEATBG");

 	     }
		else if(wrapper.equalsIgnoreCase("REPEATBG"))
		{ log.PrintLog("MyBroadcast with : "+wrapper+" Wrapper");
			if(gps.canGetLocation())
			{	latitude = gps.getLatitude();
				longitude = gps.getLongitude();
				smsDeliveryManager.sendGPSMessage(mContext,"G" + String.valueOf(latitude) + ":G" + String.valueOf(longitude));
			}
			else { smsDeliveryManager.sendTowerMessage(mContext, towerTracker.getTower().toString());	}
		}
		else if(wrapper.equalsIgnoreCase("ACTIVITYALERT"))
		{ log.PrintLog("MyBroadcast with : " + wrapper + " wraper");

			if(gps.canGetLocation())
			{
				latitude = gps.getLatitude();
				longitude = gps.getLongitude();
				smsDeliveryManager.sendGPSMessage(context,
						"G"+String.valueOf(latitude) +":G"+String.valueOf(longitude));
				SessionStore.setAlarm("ACTIVATED", context);
				GoToSplash(context);
			}
			else
			{
				smsDeliveryManager.sendTowerMessage(context,towerTracker.getTower().toString());
				SessionStore.setAlarm("ACTIVATED", context);
				GoToSplash(context);
			}
			alarmSettings.setRepeatingAlarm(context, PendingIntent.FLAG_UPDATE_CURRENT, "REPEATBG");
		}
		else if (wrapper.equalsIgnoreCase("NOTIFY"))
		{log.PrintLog("MyBroadcast with : " + wrapper + " wraper");
			GoToNotify(context);
		}
		else if (wrapper.equalsIgnoreCase("MESSAGE_ON"))
		{log.PrintLog("MyBroadcast with : " + wrapper + " wraper");
			GoToMessage(context);
		}
	   else if(wrapper.equalsIgnoreCase("END"))
	    {
			log.PrintLog("im in broadcast with : " + wrapper + " wraper");

			SessionStore.setAlarm("END", context);
			alarmSettings.setRepeatingAlarm(mContext, PendingIntent.FLAG_CANCEL_CURRENT, "END");
			/*
			alarmSettings.repeatAlarm.cancel(alarmSettings.repeatPendingIntent);
			ToastManager.showToast(context, "repeatAlarm GOT OFF");
			SessionStore.clearPref(context);
			SessionStore.setAlarm("END", context);
			PrintStream.PrintLog("SessionStore :" + SessionStore.getAlarm(context));
			GoToSplash(context);*/


	    }
	}

	public void GoToSplash(Context context){
		Intent i = new Intent(context, SplashActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);

	}

	public void GoToMessage(Context context){
		Intent i = new Intent(context, MessageActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}
	public void GoToNotify(Context context){
		Intent i = new Intent(context, NotificationActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}

	
}

