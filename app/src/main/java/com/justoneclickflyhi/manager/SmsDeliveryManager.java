package com.justoneclickflyhi.manager;


import android.content.Context;
import android.content.pm.PackageInstaller;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.justoneclickflyhi.helper.SessionStore;

public class SmsDeliveryManager 
{
	static GPSTracker gps;
	static int smsCount=0;

	public SmsDeliveryManager() 
	{
		super();
		// TODO Auto-generated constructor stub
	}
	public static void sendGPSMessage(Context context,String message)
	{
		smsCount =smsCount+1;
		SmsManager smsManager=SmsManager.getDefault();
		smsManager.sendTextMessage("+919600413993", "",message.toString(), null, null);
		ToastManager.showToast(context, "GPS Mesage Sent :" + "\n" + message.toString() + "\n My Session "
				+ SessionStore.getAlarm(context));
		PrintStream.PrintLog("GPS Mesage Sent :" + "\t" + message.toString() + "\n My Session "
				+ SessionStore.getAlarm(context));
		PrintStream.PrintLog("Session in BROADCAST B4 : \t" + SessionStore.getAlarm(context) + "\t smsCount :" + smsCount);
		PrintStream.PrintLog("---------------------------------------------------------------------------------------");
	}
	
	public static void sendTowerMessage(Context context,String message)
	{
		smsCount =smsCount+1;

    	SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage("+919600413993", "",message.toString(), null, null);

		ToastManager.showToast(context, "Tower Mesage Sent :"+"\n"+ message.toString());
		PrintStream.PrintLog("Tower Mesage Sent :" + "\t" + message.toString() + "\n My Session "
				+ SessionStore.getAlarm(context));
		PrintStream.PrintLog("Session : Broadcast \t" + SessionStore.getAlarm(context) + "\t smsCount " + smsCount);
		PrintStream.PrintLog("---------------------------------------------------------------------------------------");
	}
}
