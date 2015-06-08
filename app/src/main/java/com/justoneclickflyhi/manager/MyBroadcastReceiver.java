package com.justoneclickflyhi.manager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Toast;

import com.justoneclickflyhi.NotificationActivity;
import com.justoneclickflyhi.SplashActivity;
import com.justoneclickflyhi.helper.AlarmSettings;
import com.justoneclickflyhi.helper.SessionStore;

public class MyBroadcastReceiver extends BroadcastReceiver {
	Context mContext;
	static GPSTracker gps;
	double latitude;
	double longitude;
	
	@Override
	public void onReceive(Context context, Intent intent) {

		PrintStream.PrintLog("im into MyBroadCastReceiver");
		mContext = context;
		gps = new GPSTracker(context);
		Bundle extras = intent.getExtras();
	    String wrapper = extras.getString("extraMessage");
	    
	    ToastManager.showToast(context, "Wrapper is :"+wrapper);
	    
	    if(wrapper.equalsIgnoreCase("WAKEUP"))
	     {
			 AlarmSettings.setRepeatingAlarm(context,
					 PendingIntent.FLAG_UPDATE_CURRENT,"REPEATBG");

	    	PrintStream.PrintLog("im in broadcast with : "+wrapper+" wraper");
	    	if(gps.canGetLocation())
	         	{
					PrintStream.PrintLog("GPS ON GETTING LAT LONG");
					latitude = gps.getLatitude();
 	    			latitude =gps.getLongitude();
 	    			ToastManager.showToast(context, "lat/long" + String.valueOf(latitude + "\n" + longitude));
 	    			SmsDeliveryManager.sendGPSMessage(context,
							"G" + String.valueOf(latitude) + "G2" + String.valueOf(longitude));

 	    			Intent i = new Intent(context, SplashActivity.class);
 	    			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
 	    			context.startActivity(i);

					SessionStore.setAlarm("ACTIVATED", context);
					PrintStream.PrintLog("Session Change @ MyBroadcastReceiver :" + SessionStore.getAlarm(context));

 	         	} 
 	    	else
 	    		{
					PrintStream.PrintLog("GPS OFF GETTING TOWER DETAILS");

 	    			SmsDeliveryManager.sendTowerMessage(context, "CellId MCC MNC LAC ");

 	    			Intent i = new Intent(context, SplashActivity.class);
 	    			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
 	    			context.startActivity(i);

					SessionStore.setAlarm("ACTIVATED", context);
					PrintStream.PrintLog("Session Change @ MyBroadcastReceiver AT :" + SessionStore.getAlarm(context));


 	    	}
 	     }
		else if(wrapper.equalsIgnoreCase("REPEATBG"))
		{ PrintStream.PrintLog("im in broadcast with : "+wrapper+" wraper");
			if(gps.canGetLocation())
			{	latitude = gps.getLatitude();
				longitude = gps.getLongitude();
				//ToastManager.showToast(context, String.valueOf(latitude+"\n"+longitude));
				SmsDeliveryManager.sendGPSMessage(context,
						"G" + String.valueOf(latitude) + "G2" + String.valueOf(longitude));
			}
			else { SmsDeliveryManager.sendTowerMessage(context, "CellId MCC MNC LAC ");	}
		}
		else if(wrapper.equalsIgnoreCase("ACTIVITYALERT"))
		{
			PrintStream.PrintLog("im in broadcast with : " + wrapper + " wraper");
			ToastManager.showToast(context, wrapper + "  Broadcast");


			if(gps.canGetLocation())
			{
				latitude = gps.getLatitude();
				longitude = gps.getLongitude();
				SmsDeliveryManager.sendGPSMessage(context,
						"G"+String.valueOf(latitude)+"G2"+String.valueOf(longitude));
				SessionStore.setAlarm("ACTIVATED", context);
				Intent i = new Intent(context, SplashActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(i);
			}
			else
			{	SmsDeliveryManager.sendTowerMessage(context,"CellId MCC MNC LAC ");
				SessionStore.setAlarm("ACTIVATED", context);
				Intent i = new Intent(context, SplashActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(i);
			}
		}
		else if(wrapper.equalsIgnoreCase("NOTIFY")) {
			PrintStream.PrintLog("im in broadcast with : " + wrapper + " wraper");
			ToastManager.showToast(context, wrapper + "  Broadcast");

    		PrintStream.PrintLog("im in broadcast with : " + wrapper + " wraper");
			Intent i = new Intent(context, NotificationActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);

		}
	    
	    else if(wrapper.equalsIgnoreCase("REPEATACTIVITY"))
	    {
	    	PrintStream.PrintLog("im in broadcast with : " + wrapper + " wraper");
	    	
	    	if(gps.canGetLocation())
         		{
	    			latitude = gps.getLatitude();
	    			longitude = gps.getLongitude();
	    			SmsDeliveryManager.sendGPSMessage(context,
	    					"G"+String.valueOf(latitude)+"G2"+String.valueOf(longitude));
	    			Intent i = new Intent(context, SplashActivity.class);
	    			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    			context.startActivity(i);
	         	} 
	    	else
	    		{
		
	    			SmsDeliveryManager.sendTowerMessage(context,"CellId MCC MNC LAC ");
	    			Intent i = new Intent(context, SplashActivity.class);
	    			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    			context.startActivity(i);
	    			
		
	    	}
    	
	    }


	    

	    
	    else if(wrapper.equalsIgnoreCase("END"))
	    {
	    	PrintStream.PrintLog("im in broadcast with : "+wrapper+" wraper");
	    	
	    	AlarmSettings.repeatAlarm.cancel(AlarmSettings.repeatPendingIntent);
	    	    	
	    	
	    	
	    	
	    }
	}
	public void offWakeUpAlarm(){
    	
    	ToastManager.showToast(mContext, "offWakeUpAlarm ");
    	PendingIntent wakePendingIntent = AlarmSettings.wakePendingIntent;
		AlarmSettings.wakeUpAlarm.cancel(wakePendingIntent);
		ToastManager.showToast(mContext, "Alarm Cancelled");
    }
	
}

	    
	    /**

	     if(wrapper.equalsIgnoreCase("WAKEUP"))
	     {
	    	 /** send the GEO location start the activity and show the message received via sms**/
	    	 /** check the GPS | Internet | SIM card inserted if gps is on then send lat long else tower id */
	    	 /** Call the activity then pass extras that the GPS is off or the tower details not much acurate to get the lat long*/
	    	 // check if GPS enabled
	    /**
	         if(gps.canGetLocation())
	         {
	         	
	         	double latitude = gps.getLatitude();
	         	double longitude = gps.getLongitude();
	         	ToastManager.showToast(context, String.valueOf(latitude+"\n"+longitude));
	         	SmsDeliveryManager.sendGPSMessage(context);
	         	AlarmSettings.setWakeUpAlarm(context,"OFF");
		    	AlarmSettings.setRepeatingAlarm(context, "ON", "TOWER");
		    	
		    	Intent i = new Intent(context, AlarmActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //i.putExtra("STRING_I_NEED", "smscall");
                context.startActivity(i);
		    	
		    	
		    	
	         }
	         else
	         {
	         	// can't get location
	         	// GPS or Network is not enabled
	         	// Ask user to enable GPS/network in settings
	        	 SmsDeliveryManager.sendTowerMessage(context);
		    	 AlarmSettings.setWakeUpAlarm(context,"OFF");
		    	 AlarmSettings.setRepeatingAlarm(context, "ON", "TOWER");
		    	 Intent i = new Intent(context, AlarmActivity.class);
	             i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	             context.startActivity(i);
	         }
	     } 
	     
	     
	     else if (wrapper.equalsIgnoreCase("TOWER"))
	     { 
	    	 if(gps.canGetLocation())
	         {
	    		 SmsDeliveryManager.sendGPSMessage(context);
	         } 
	    	 else 
	         {
	    		 SmsDeliveryManager.sendTowerMessage(context);
	         }
	     }
	     else if (wrapper.equalsIgnoreCase("GPS"))
	     {
 	    	 if(gps.canGetLocation())
	         {
	    		 SmsDeliveryManager.sendGPSMessage(context);
	         } 
	    	 else 
	         {
	    		 SmsDeliveryManager.sendTowerMessage(context);
	         }
	     }
	}
	**/
