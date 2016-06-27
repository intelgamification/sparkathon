package com.gamification.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getStringFormattedDate(Date date) {
		final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		fmt.format(date);
		return fmt.format(date);
	}

	public static Date getDateFormString(String date){
		final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date2=null;
		try {
			date2 = fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return date2;
	}
}
