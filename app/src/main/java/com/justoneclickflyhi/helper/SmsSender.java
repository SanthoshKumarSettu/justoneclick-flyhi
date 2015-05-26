package com.justoneclickflyhi.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by user on 26-05-2015.
 */
public class SmsSender extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"Sms SENT Using Alarm",Toast.LENGTH_LONG).show();
        //SmsManager smsManager=SmsManager.getDefault();
        //smsManager.sendTextMessage(Constants.NUMBER, "", "G1"+ "G2", null, null);


    }
}
