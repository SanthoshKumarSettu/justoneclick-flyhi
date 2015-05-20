package com.justoneclickflyhi.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Application {
	//String Status="";
	static String REMINDER;
	public String check()
    {
		try{

			//get current date time with Date()
			Date date = new Date();
			date.getTime();


			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			DateFormat timeFormat = new SimpleDateFormat("HH:mm");
			String DD =Constants.AWAKE_DATE_DAY;
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
			Date receivedDate = dateFormat.parse(RAD);
			/** RECEIVED AWAKE TIME  **/
			Date receivedtime = timeFormat.parse(RAT);

			/***RECEIVED SLEEP DATE*/
			Date receivedDateSLEEP = dateFormat.parse(RSD);
			/***RECEIVED SLEEP TIME*/
			Date receivedTIMESLEEP = timeFormat.parse(RST);




			/*** CURRENT DATE */
			String ParseCurrentDate = dateFormat.format(date.getTime());
			Date currentDate = dateFormat.parse(ParseCurrentDate);

			/*** CURRENT TIME */
			String ParseReceivedTime = timeFormat.format(date.getTime());
			Date currentTime = timeFormat.parse(ParseReceivedTime);




			System.out.println("R TIME : "+timeFormat.format(receivedtime));
			System.out.println("C TIME : "+timeFormat.format(date.getTime()));


			System.out.println("R date : "+dateFormat.format(receivedDate));
			System.out.println("C date : "+dateFormat.format(date.getTime()));

			////AFTER DATE >>>>>>>>>> GREATER >>>>>>>>>>>
			if(receivedDate.after(currentDate)){
				System.out.println(0);
				REMINDER="ACTIVATE_FUTURE_ALARAM";
				System.out.println(REMINDER+"correct format Set Alaram with sms Timing and Date ");
			}


			//EQUALS DATE=========== EQUALS =================SET ALARAM
			if(receivedDate.equals(currentDate))
			{
				System.out.println(8);
				System.out.println("Its Today ");
				///////////////RECEIVED TIME
				if(receivedtime.after(currentTime)){
					System.out.println(9);
					REMINDER="ACTIVATE_FUTURE_ALARAM";
					System.out.println(REMINDER+"received time & current time is after set alaram today as received in sms");
				}
				///////////////RECEIVED TIME EXPIRED
				if(receivedtime.before(currentTime)){
					System.out.println(10);
					REMINDER="ACTIVATE_NOW";
					System.out.println(REMINDER+"received time is before to current time boot the application right now ");
				}
				if(receivedtime.equals(currentTime)){
					System.out.println(11);
					REMINDER="ACTIVATE_NOW";
					System.out.println(REMINDER+"recived time is equal to current time boot app now ");
				}
			}



			//BEFORE DATE<<<<<<<<< LESSER <<<<<<<<<<<<
			if(receivedDate.before(currentDate)){
				System.out.println(4);
				System.out.println("Error in received date send SMS ");
				if(receivedDateSLEEP.equals(currentDate))
				{
					System.out.println(5);
					REMINDER="ACTIVATE_NOW";
					System.out.println(REMINDER+"SMS Expires today ");
				}
				if(receivedDateSLEEP.before(currentDate))
				{
					System.out.println(6);
					REMINDER="EXIT";
					System.out.println(REMINDER+"received date sleep is before curreent date quit the app");
				}
				if(receivedDateSLEEP.after(currentDate))
				{

					System.out.println(7);
					//System.out.println("received date sleep is equal to today send the alam with frequency");
					System.out.println("checking with received sleep date with current date");
					if(receivedTIMESLEEP.after(currentTime))
					{

						REMINDER="ACTIVATE_NOW";

						System.out.println(REMINDER+"received time sleep is ater current time boot app");
					}
					if(receivedTIMESLEEP.before(currentTime))
					{
						REMINDER="EXIT";
						//System.out.println("receivedDateSLEEP :"+receivedDateSLEEP);
						//System.out.println("currentTime :"+currentTime);

						//System.out.println(REMINDER+"received time sleep EXPIRED quit");
					}
					if(receivedTIMESLEEP.equals(currentTime))
					{
						REMINDER="ACTIVATE_NOW";
						System.out.println(REMINDER+"Alert the user ");
					}
				}
			}

		} catch (Exception e){



			return Constants.REMINDER = "DEFAULT";


		}

    	
		return Constants.REMINDER = REMINDER;
     }







}

