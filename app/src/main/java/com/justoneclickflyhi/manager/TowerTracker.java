package com.justoneclickflyhi.manager;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;


public class TowerTracker {
    Context mContext;
    TelephonyManager telephonyManager;
    String towerDetails;
    String networkOperator;
    GsmCellLocation cellLocation;
    //int mcc; int mnc;
    String mcc,mnc;
        int cid,lac;
    public TowerTracker(Context context) {
        this.mContext = context;
        getTower();
    }

    public String getTower() {

        try {
             telephonyManager = (TelephonyManager)mContext.getSystemService(Context.TELEPHONY_SERVICE);
             cellLocation = (GsmCellLocation)telephonyManager.getCellLocation();
             networkOperator = telephonyManager.getNetworkOperator();
             mcc = networkOperator.substring(0, 3);
             mnc = networkOperator.substring(3);
             cid = cellLocation.getCid();
             lac = cellLocation.getLac();
            towerDetails =mcc+":"+mnc+":"+cid+":"+lac;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            PrintStream.PrintLog("towerDetails : " + "Error");
            ToastManager.showToast(mContext,"towerDetails : Error");
            return towerDetails = "Error";

        }

        //PrintStream.PrintLog("return towerDetails : "+towerDetails);
        return towerDetails;
    }



}
