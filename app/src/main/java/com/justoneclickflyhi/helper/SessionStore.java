package com.justoneclickflyhi.helper;

import android.content.Context;
import android.content.SharedPreferences;


public class SessionStore {

    private static String PREF_NAME = "inbox";
    private static String P_INSTALLATION = "installed";
    private static String P_MESSAGE= "p_message";


    public static boolean saveReceivedMessage(String receivedMessage,Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putString(P_MESSAGE, receivedMessage);

        return editor.commit();

    }

    public static String getReceivedMessage(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(
                PREF_NAME, 0);
        return savedSession.getString(P_MESSAGE, null);
    }

    public static boolean saveInstallationStatus(String Message,Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putString(P_INSTALLATION, Message);

        return editor.commit();

    }

    public static String getInstallationStatus(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(
                PREF_NAME, 0);
        return savedSession.getString(P_INSTALLATION, null);
    }




}


