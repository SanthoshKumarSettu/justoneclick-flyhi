package com.justoneclickflyhi.helper;

import android.telephony.SmsManager;

import java.util.logging.Handler;

/**
 * Created by user on 26-05-2015.
 */
public class SendSMS {

    public SendSMS() {
    }


    public void geoSMS(long g1, long g2) {

        SmsManager smsManager=SmsManager.getDefault();

        smsManager.sendTextMessage(Constants.NUMBER, "", "G1"+g1+ ":"+"G2"+g2, null, null);


    }

    public void towerSMS() {

    }




}
