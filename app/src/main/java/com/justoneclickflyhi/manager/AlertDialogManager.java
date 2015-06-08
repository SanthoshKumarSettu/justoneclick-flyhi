package com.justoneclickflyhi.manager;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;

import com.justoneclickflyhi.R;
import com.justoneclickflyhi.helper.AlarmSettings;
import com.justoneclickflyhi.helper.SessionStore;


/**
 * Created by user on 19-05-2015.
 */
public class AlertDialogManager {

    static GPSTracker gps;


    public void showAlertDialog(Context context, String title, String message,
                                Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        if(status != null)
            // Setting alert dialog icon
            alertDialog.setIcon((status) ? R.drawable.logo : R.drawable.logo);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

   public void showAlertDialogWithTwoButtons(final Context context, String title, String message){

       AlertDialog alertDialog2 = new AlertDialog.Builder(context).create();

    // Setting Dialog Title
       alertDialog2.setTitle(title);

       // Setting Dialog Message
       alertDialog2.setMessage(message);
       // Setting alert dialog icon
       alertDialog2.setIcon(R.drawable.logo);

       // Setting OK Button
       alertDialog2.setButton("SEND NRNO", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int which) {
               SessionStore.saveNotificationResponse("NRNO", context);
               AlarmSettings.setRepeatingAlarm(context,
                       PendingIntent.FLAG_UPDATE_CURRENT, "NOTIFY");

           }
       });


       // Showing Alert Message
       alertDialog2.show();

   }


}
