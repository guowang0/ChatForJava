package com.qq.Tools;

import java.util.Calendar;

public class Time {

public static String ViewTime() {
		
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int data = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		return hour + ":" + minute
				+ ":" + second;
	}
	
}
