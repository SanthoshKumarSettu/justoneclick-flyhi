package com.justoneclickflyhi;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.justoneclickflyhi.helper.Application;
import com.justoneclickflyhi.helper.Constants;
import com.justoneclickflyhi.manager.AlertDialogManager;

import java.text.ParseException;

public class HomeActivity extends ActionBarActivity {

    Application app;
    String statusreceived;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Set a toolbar to replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        app = new Application();

       try {
           statusreceived = app.check().toString();
           if(statusreceived==null)
           {

               Toast.makeText(HomeActivity.this, "NULL : == :"+statusreceived, Toast.LENGTH_LONG).show();

           }
           if (statusreceived.equalsIgnoreCase("ACTIVATE_FUTURE_ALARAM"))

           {
               Toast.makeText(HomeActivity.this, "ACTIVATE_FUTURE_ALARAM : == :"+statusreceived, Toast.LENGTH_LONG).show();


           }
           if(statusreceived.equals("ACTIVATE_NOW"))
           {
               Toast.makeText(HomeActivity.this, "ACTIVATE_NOW : == :"+statusreceived, Toast.LENGTH_LONG).show();
           }
           if(statusreceived.equals("EXIT"))
           {
               Toast.makeText(HomeActivity.this, "EXIT : == :"+statusreceived, Toast.LENGTH_LONG).show();
           }
           if(statusreceived.equals("DEFAULT"))
           {
               //Toast.makeText(HomeActivity.this, "EXIT : == :"+statusreceived, Toast.LENGTH_LONG).show();

               AlertDialogManager dialog = new AlertDialogManager();
               dialog.showAlertDialog(HomeActivity.this,"Just One Click - Fly Hi","Welcome to Just one Click!"+"\n"+" please stay tuned for updates",true);

           }





        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
           Toast.makeText(HomeActivity.this, "ParseException", Toast.LENGTH_LONG).show();
        }

       // Toast.makeText(HomeActivity.this, "Value : == :"+statusreceived, Toast.LENGTH_LONG).show();
        TextView tt = (TextView)findViewById(R.id.textView_status);
        tt.setText("MESSAGE : " + Constants.MESSAGE + "\n" +
                        "AWAKE INDEX : " + Constants.AWAKE_INDEX + "\n" +
                        "AWAKE TIME : " + Constants.AWAKE_TIME + "\n" +
                        "SLEEP TIME : " + Constants.SLEEP_TIME + "\n" +
                        "PING FREQUENCY :" + Constants.PING_FREQUENCY_TIME + "\n" +
                        "PING MESSAGE :" + Constants.PING_MESSAGE + "\n" +
                        "DATE START : " + Constants.DATE_START + "\n" +
                        "DATE STOP : " + Constants.DATE_STOP + "\n" +
                        "COUNDOWN TOTAL : " + Constants.COUNDOWN_TOTAL + "\n" +
                        "COUNDOWN INTERVAL : " + Constants.COUNDOWN_PING_INTERVAL + "\n"
                        + statusreceived + "\n" + "reminder" + Constants.REMINDER
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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


            Toast.makeText(this,"Help!",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
