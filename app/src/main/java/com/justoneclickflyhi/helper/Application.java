package com.justoneclickflyhi.helper;

import android.content.Context;

import com.justoneclickflyhi.manager.PrintStream;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Application {

	static String REMINDER;
	SessionStore sessionStore;

	Date receivedDate;
	Date receivedtime;

	Date receivedDateSLEEP;
	Date receivedTIMESLEEP;

	Date currentDate;
	PrintStream PrintStream;

	public String check(Context context)
    {
		com.justoneclickflyhi.manager.PrintStream.PrintLog("into APPLICATION CLASS");
		try{
			//get current date time with Date()

			sessionStore.getPrefConstants(context);
			Date date = new Date();
			date.getTime();

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			DateFormat timeFormat = new SimpleDateFormat("HH:mm");

			String DD  = Constants.AWAKE_DATE_DAY;
			String MM = Constants.AWAKE_DATE_MONTH;
			String YY = Constants.AWAKE_DATE_YEAR;

			String HH = Constants.AWAKE_TIME_HOUR;
			String MIN = Constants.AWAKE_TIME_MIN;


			String S_DD = Constants.SLEEP_DATE_DAY;
			String S_MM = Constants.SLEEP_DATE_MONTH;
			String S_YY = Constants.SLEEP_DATE_YEAR;

			String S_HH = Constants.SLEEP_TIME_HOUR;
			String S_MIN = Constants.SLEEP_TIME_MIN;

			String RAD = YY+"/"+MM+"/"+DD;
			String RAT = HH+":"+MIN;


			String RSD = S_YY+"/"+S_MM+"/"+S_DD;
			String RST = S_HH+":"+S_MIN;

			/***RECEIVED AWAKE DATE*/
			//Date receivedDate = dateFormat.parse("2015/05/20");
			/** RECEIVED AWAKE TIME  **/
			//Date receivedtime = timeFormat.parse("12:30");

			/***RECEIVED SLEEP DATE*/
			//Date receivedDateSLEEP = dateFormat.parse("2015/05/22");
			/***RECEIVED SLEEP TIME*/
			//Date receivedTIMESLEEP = timeFormat.parse("23:30");

			/***RECEIVED AWAKE DATE*/
			receivedDate = dateFormat.parse(RAD);
			/** RECEIVED AWAKE TIME  **/
			receivedtime = timeFormat.parse(RAT);

			/***RECEIVED SLEEP DATE*/
			receivedDateSLEEP = dateFormat.parse(RSD);
			/***RECEIVED SLEEP TIME*/
			receivedTIMESLEEP = timeFormat.parse(RST);

			/*** CURRENT DATE */
			String ParseCurrentDate = dateFormat.format(date.getTime());
			currentDate = dateFormat.parse(ParseCurrentDate);

			/*** CURRENT TIME */
			String ParseReceivedTime = timeFormat.format(date.getTime());
			Date currentTime = timeFormat.parse(ParseReceivedTime);

			PrintStream.PrintLog("R TIME : " + timeFormat.format(receivedtime));
			PrintStream.PrintLog("C TIME : " + timeFormat.format(date.getTime()));


			PrintStream.PrintLog("R date : " + dateFormat.format(receivedDate));
			PrintStream.PrintLog("C date : " + dateFormat.format(date.getTime()));

			////AFTER DATE >>>>>>>>>> GREATER >>>>>>>>>>>
			if(receivedDate.after(currentDate)){
				REMINDER="ACTIVATE_FUTURE_ALARAM";
			}


			//EQUALS DATE=========== EQUALS =================SET ALARAM
			if(receivedDate.equals(currentDate))
			{ PrintStream.PrintLog("Its Today ");
				///////////////RECEIVED TIME
				if(receivedtime.after(currentTime)){PrintStream.PrintLog(REMINDER + "\t received time & current time is after set alaram today as received in sms");
					 REMINDER="ACTIVATE_FUTURE_ALARAM";
				}
				///////////////RECEIVED TIME EXPIRED
				if(receivedtime.before(currentTime)){
					 REMINDER="ACTIVATE_NOW"; PrintStream.PrintLog(REMINDER + "\t received time is before to current time boot the application right now ");
				}
				if(receivedtime.equals(currentTime)){
					 REMINDER="ACTIVATE_NOW"; PrintStream.PrintLog(REMINDER + "\t recived time is equal to current time boot app now ");
				}
			}
			//BEFORE DATE<<<<<<<<< LESSER <<<<<<<<<<<<
			if(receivedDate.before(currentDate)){ PrintStream.PrintLog("Error in received date send SMS");
				if(receivedDateSLEEP.equals(currentDate))
				{  REMINDER="ACTIVATE_NOW"; PrintStream.PrintLog(REMINDER+"SMS Expires today ");
				}
				if(receivedDateSLEEP.before(currentDate))
				{
					 REMINDER="EXIT"; PrintStream.PrintLog(REMINDER + "\t received date sleep is before curreent date quit the app");
				}
				if(receivedDateSLEEP.after(currentDate))
				{ PrintStream.PrintLog("checking with received sleep date with current date");
					if(receivedTIMESLEEP.after(currentTime))
					{
						 REMINDER="ACTIVATE_NOW"; PrintStream.PrintLog(REMINDER + "\t received time sleep is ater current time boot app");
					}
					if(receivedTIMESLEEP.before(currentTime))
					{
						 REMINDER="EXIT"; PrintStream.PrintLog(REMINDER + "Exit app");
					}
					if(receivedTIMESLEEP.equals(currentTime))
					{
						 REMINDER="ACTIVATE_NOW"; PrintStream.PrintLog(REMINDER + "\t Alert the user ");
					}
				}
			}
		}
		catch (Exception e){
			return REMINDER="SMS_ERROR";
		}
		return REMINDER;
     }









}

