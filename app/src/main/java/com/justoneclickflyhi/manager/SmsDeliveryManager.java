package com.justoneclickflyhi.manager;


import android.content.Context;
import android.content.pm.PackageInstaller;
import android.widget.Toast;

import com.justoneclickflyhi.helper.SessionStore;

public class SmsDeliveryManager 
{
	static GPSTracker gps;

	public SmsDeliveryManager() 
	{
		super();
		// TODO Auto-generated constructor stub
	}
	public static void sendGPSMessage(Context context,String message)
	{	       	
        	/**
        	SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage("", "",
            "G"+latitude+ ":"+"G"+longitude, null, null);
            **/
		ToastManager.showToast(context, "GPS SMS Sent Success fully from SmsDeliveryManager"+"\n"+ message+"\n My Session "
				+SessionStore.getAlarm(context));
		PrintStream.PrintLog("Session in BROADCAST B4 : " + SessionStore.getAlarm(context));


		
	}
	
	public static void sendTowerMessage(Context context,String message)
	{
		/**
    	SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage("", "",
        "G"+latitude+ ":"+"G"+longitude, null, null);
        **/
		ToastManager.showToast(context, "Tower SMS Sent Success fully from SmsDeliveryManager"+"\n"+ message);
		PrintStream.PrintLog("Session : Broadcast" + SessionStore.getAlarm(context));
	}
}
