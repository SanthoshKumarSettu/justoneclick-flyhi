package com.justoneclickflyhi.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;


public class SessionStore {


    private static String PREF_NAME = "inbox";

    private static String P_ALARM= "p_alarm";

    private static String P_MESSAGE= "p_message";
    private static String P_MESSAGE_TYPE= "p_message_type";

    private static String P_HOUR_AWAKE= "p_hour_awake";
    private static String P_MINUTE_AWAKE= "p_minute_awake";
    private static String P_DAY_AWAKE= "p_day_awake";
    private static String P_MONTH_AWAKE= "p_month_awake";
    private static String P_YEAR_AWAKE= "p_year_awake";

    private static String P_HOUR_SLEEP= "p_hour_sleep";
    private static String P_MINUTE_SLEEP= "p_minute_sleep";
    private static String P_DAY_SLEEP= "p_day_sleep";
    private static String P_MONTH_SLEEP= "p_month_sleep";
    private static String P_YEAR_SLEEP= "p_year_sleep";

    private static String P_PING_INTERVAL= "p_ping_interval";

    ////-----------Save received Mesage--------////////
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
    ////-----------Save received Mesage--------////////


    ////-----------Save received Mesage Type--------////////
    public static boolean saveReceivedMessageType(String receivedMessage,Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putString(P_MESSAGE_TYPE, receivedMessage);
        return editor.commit();
    }
    public static String getReceivedMessageType(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(
                PREF_NAME, 0);
        return savedSession.getString(P_MESSAGE_TYPE, null);
    }////-----------Save received Mesage Type--------////////

    ////-----------Save received Mesage--------////////
    public static boolean setAlarm(String receivedMessage,Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putString(P_ALARM, receivedMessage);
        return editor.commit();
    }
    public static String getAlarm(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(
                PREF_NAME, 0);
        return savedSession.getString(P_ALARM, null);
    }
    ////-----------Save received Mesage--------////////





    ////-----------Save received Mesage Type--------////////
    public static boolean saveHourAwake(String receivedMessage,Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putString(P_HOUR_AWAKE, receivedMessage);
        return editor.commit();
    }
    public static String getHourAwake(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(
                PREF_NAME, 0);
        return savedSession.getString(P_HOUR_AWAKE, null);
    }////-----------Save received Mesage Type--------////////

    ////-----------Save received Mesage Type--------////////
    public static boolean saveMinuteAwake(String receivedMessage,Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putString(P_MINUTE_AWAKE, receivedMessage);
        return editor.commit();
    }
    public static String getMinuteAwake(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(
                PREF_NAME, 0);
        return savedSession.getString(P_MINUTE_AWAKE, null);
    }////-----------Save received Mesage Type--------////////

    ////-----------Save received Mesage Type--------////////
    public static boolean saveDayAwake(String receivedMessage,Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putString(P_DAY_AWAKE, receivedMessage);
        return editor.commit();
    }
    public static String getDayAwake(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(
                PREF_NAME, 0);
        return savedSession.getString(P_DAY_AWAKE, null);
    }////-----------Save received Mesage Type--------////////

    ////-----------Save received Mesage Type--------////////
    public static boolean saveMonthAwake(String receivedMessage,Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putString(P_MONTH_AWAKE, receivedMessage);
        return editor.commit();
    }
    public static String getMonthAwake(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(
                PREF_NAME, 0);
        return savedSession.getString(P_MONTH_AWAKE, null);
    }////-----------Save received Mesage Type--------////////

    ////-----------Save received Mesage Type--------////////
    public static boolean saveYearAwake(String receivedMessage,Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putString(P_YEAR_AWAKE, receivedMessage);
        return editor.commit();
    }
    public static String getYearAwake(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(
                PREF_NAME, 0);
        return savedSession.getString(P_YEAR_AWAKE, null);
    }////-----------Save received Mesage Type--------////////



    ////-----------Save received Mesage Type--------////////
    public static boolean saveHourSleep(String receivedMessage,Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putString(P_HOUR_SLEEP, receivedMessage);
        return editor.commit();
    }
    public static String getHourSleep(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(
                PREF_NAME, 0);
        return savedSession.getString(P_HOUR_SLEEP, null);
    }////-----------Save received Mesage Type--------////////

    ////-----------Save received Mesage Type--------////////
    public static boolean saveMinuteSleep(String receivedMessage,Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putString(P_MINUTE_SLEEP, receivedMessage);
        return editor.commit();
    }
    public static String getMinuteSleep(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(
                PREF_NAME, 0);
        return savedSession.getString(P_MINUTE_SLEEP, null);
    }////-----------Save received Mesage Type--------////////

    ////-----------Save received Mesage Type--------////////
    public static boolean saveDaySleep(String receivedMessage,Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putString(P_DAY_SLEEP, receivedMessage);
        return editor.commit();
    }
    public static String getDaySleep(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(
                PREF_NAME, 0);
        return savedSession.getString(P_DAY_SLEEP, null);
    }////-----------Save received Mesage Type--------////////

    ////-----------Save received Mesage Type--------////////
    public static boolean saveMonthSleep(String receivedMessage,Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putString(P_MONTH_SLEEP, receivedMessage);
        return editor.commit();
    }
    public static String getMonthSleep(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(
                PREF_NAME, 0);
        return savedSession.getString(P_MONTH_SLEEP, null);
    }////-----------Save received Mesage Type--------////////

    ////-----------Save received Mesage Type--------////////
    public static boolean saveYearSleep(String receivedMessage,Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putString(P_YEAR_SLEEP, receivedMessage);
        return editor.commit();
    }
    public static String getYearSleep(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(
                PREF_NAME, 0);
        return savedSession.getString(P_YEAR_SLEEP, null);
    }////-----------Save received Mesage Type--------////////

    ////-----------Save received Mesage Type--------////////
    public static boolean savePingInterval(String receivedMessage,Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putString(P_PING_INTERVAL, receivedMessage);
        return editor.commit();
    }
    public static String getPingInterval(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(
                PREF_NAME, 0);
        return savedSession.getString(P_PING_INTERVAL, null);
    }////-----------Save received Mesage Type--------////////

    ////-----------Save received Mesage Type--------////////
    public static boolean savePingMessage(String receivedMessage,Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putString(P_MESSAGE, receivedMessage);
        return editor.commit();
    }
    public static String getPingMessage(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(
                PREF_NAME, 0);
        return savedSession.getString(P_MESSAGE, null);
    }////-----------Save received Mesage Type--------////////

    public static void clearPref(Context context) {

        // Shared Preferences
        SharedPreferences pref;

        // Editor for Shared preferences
        SharedPreferences.Editor editor;

        pref = context.getSharedPreferences("inbox", 0);
        editor = pref.edit();

        editor.clear();
        editor.commit();
    }

    public static void setPref(Context context,String Message){

        String msgBody;
        msgBody = Message;
        SessionStore.saveReceivedMessage(msgBody.toString(), context);
        SessionStore.saveReceivedMessageType(msgBody.substring(0, 2).toString(), context);

        SessionStore.setAlarm("ACTIVATE_FUTURE_ALARAM", context);

        SessionStore.saveHourAwake(msgBody.substring(2, 4).toString(), context);
        SessionStore.saveMinuteAwake(msgBody.substring(4, 6).toString(), context);
        SessionStore.saveDayAwake(msgBody.substring(6, 8).toString(), context);
        SessionStore.saveMonthAwake(msgBody.substring(8, 10).toString(), context);
        SessionStore.saveYearAwake("20" + msgBody.substring(10, 12).toString(), context);

        SessionStore.saveHourSleep(msgBody.substring(12, 14), context);
        SessionStore.saveMinuteSleep(msgBody.substring(14, 16), context);
        SessionStore.saveDaySleep(msgBody.substring(16, 18), context);
        SessionStore.saveMonthSleep(msgBody.substring(18, 20), context);
        SessionStore.saveYearSleep("20" + msgBody.substring(20, 22), context);

        SessionStore.savePingInterval(msgBody.substring(23, 26), context);

        SessionStore.savePingMessage(msgBody.substring(27, msgBody.length()), context);

        Constants.MESSAGE = SessionStore.getReceivedMessage(context);
        Constants.MESSAGE_TYPE = SessionStore.getReceivedMessageType(context);

        Constants.REMINDER = SessionStore.getAlarm(context);

        Constants.AWAKE_TIME_HOUR = SessionStore.getHourAwake(context);
        Constants.AWAKE_TIME_MIN = SessionStore.getMinuteAwake(context);

        Constants.AWAKE_DATE_DAY = SessionStore.getDayAwake(context);
        Constants.AWAKE_DATE_MONTH = SessionStore.getMonthAwake(context);
        Constants.AWAKE_DATE_YEAR = SessionStore.getYearAwake(context);

        Constants.SLEEP_TIME_HOUR = SessionStore.getHourSleep(context);
        Constants.SLEEP_TIME_MIN = SessionStore.getMinuteSleep(context);
        Constants.SLEEP_DATE_DAY = SessionStore.getDaySleep(context);
        Constants.SLEEP_DATE_MONTH = SessionStore.getMonthSleep(context);
        Constants.SLEEP_DATE_YEAR = SessionStore.getYearSleep(context);
        Constants.PING_MESSAGE = SessionStore.getPingMessage(context);
        Constants.PING_FREQUENCY_TIME =SessionStore.getPingInterval(context);

        Constants.HH = Integer.parseInt(SessionStore.getHourAwake(context));
        Constants.MIN = Integer.parseInt(SessionStore.getMinuteAwake(context));

        Constants.DAY= Integer.parseInt(SessionStore.getDayAwake(context));
        Constants.MONTH=Integer.parseInt(SessionStore.getMonthAwake(context));
        Constants.YEAR=Integer.parseInt(SessionStore.getYearAwake(context));
        Constants.PING_REPEAT_INT = Integer.parseInt(SessionStore.getPingInterval(context));


        //if(s.equalsIgnoreCase("SMS_ERROR")){

           // Toast.makeText(context, "SMS_ERROR", Toast.LENGTH_LONG).show();
        //}

        //AlarmSettings.setFirstAlarm(context);
    }

}


