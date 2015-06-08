package com.justoneclickflyhi;

import android.app.PendingIntent;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.justoneclickflyhi.helper.AlarmSettings;
import com.justoneclickflyhi.helper.SessionStore;
import com.justoneclickflyhi.manager.GPSTracker;
import com.justoneclickflyhi.manager.PrintStream;
import com.justoneclickflyhi.manager.SmsDeliveryManager;


public class MessageActivity extends ActionBarActivity implements View.OnClickListener {
    Button YES,NO;
    TextView tvMessage;
    Context context;
    static GPSTracker gps;
    double latitude;
    double longitude;
    SmsDeliveryManager sms;
    SessionStore ss;
    AlarmSettings alarmSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        context = MessageActivity.this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        PrintStream.PrintLog("Calling setlisteners()");
        setlisteners();
        gps = new GPSTracker(context);
        sms = new SmsDeliveryManager();
        ss = new SessionStore();

    }
    public void setlisteners() {
        PrintStream.PrintLog("INTO setlisteners()");

        YES = (Button)findViewById(R.id.button_YES);
        YES.setOnClickListener(this);
        NO = (Button)findViewById(R.id.button_NO);
        NO.setOnClickListener(this);
        tvMessage = (TextView) findViewById(R.id.textView_MESSAGE);
        // tvMessage.setText(SessionStore.getReceivedMessage(context).toString());
        tvMessage.setText("SessionStore.getReceivedMessage(context).toString() \n"+SessionStore.getAlarm(context).toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_message, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        PrintStream.PrintLog("INTO OnCLick Implementing method");

        switch (v.getId())
        {
            case R.id.button_YES:

                PrintStream.PrintLog("Pressed YES");
                if(gps.canGetLocation())
                {
                    PrintStream.PrintLog("GPS is enabled sending lat long");
                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();
                    //ToastManager.showToast(context, String.valueOf(latitude+"\n"+longitude));
                    sms.sendGPSMessage(context,
                            "G" + String.valueOf(latitude) + "G2" + String.valueOf(longitude)+":R01");
                }
                else { PrintStream.PrintLog("Tower details Sending GPS OFF");
                    sms.sendTowerMessage(context, "CellId MCC MNC LAC " + ":R01");	}

                PrintStream.PrintLog("setting alaram session as ACTIVATED \n and updating current repeat alarrm as REPEATBG");
                ss.setAlarm("ACTIVATED", context);

                if(AlarmSettings.repeatPendingIntent==null){
                    PrintStream.PrintLog("AlarmSettings.repeatPendingIntent is NULL");

                }
                else
                {
                    PrintStream.PrintLog("AlarmSettings.repeatPendingIntent is NOT NULL");
                    alarmSettings.setRepeatingAlarm(context, PendingIntent.FLAG_UPDATE_CURRENT, "REPEATBG");
                }




                break;
            case R.id.button_NO:
                PrintStream.PrintLog("Pressed NO \n setting alaram session as ACTIVATED \n and updating current repeat alarrm as REPEATBG");



                if(gps.canGetLocation())
                { PrintStream.PrintLog("GPS is enabled sending lat long");	latitude = gps.getLatitude();
                    longitude = gps.getLongitude();
                    //ToastManager.showToast(context, String.valueOf(latitude+"\n"+longitude));
                    sms.sendGPSMessage(context,
                            "G" + String.valueOf(latitude) + "G2" + String.valueOf(longitude)+":R02");
                }
                else { PrintStream.PrintLog("Tower details Sending GPS OFF"); sms.sendTowerMessage(context, "CellId MCC MNC LAC "+"R02");	}

                PrintStream.PrintLog("setting alaram session as ACTIVATED \n and updating current repeat alarrm as REPEATBG");
                ss.setAlarm("ACTIVATED", context);

                if(AlarmSettings.repeatPendingIntent==null){
                    PrintStream.PrintLog("AlarmSettings.repeatPendingIntent is NULL");
                }
                else
                {
                    PrintStream.PrintLog("AlarmSettings.repeatPendingIntent is NOT NULL");
                    alarmSettings.setRepeatingAlarm(context, PendingIntent.FLAG_UPDATE_CURRENT, "REPEATBG");
                }

                break;
        }

    }
}