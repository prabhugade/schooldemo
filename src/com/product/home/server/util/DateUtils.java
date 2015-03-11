package com.product.home.server.util;

import java.util.Calendar;
import java.util.HashMap;

public class DateUtils {

	public static HashMap<String,Integer> getMonthAndDay(int year,int month)
	{
		HashMap<String,Integer> dayshasmp=new HashMap<String,Integer>();
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, 1);
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		dayshasmp.put("numberofday",days);
		dayshasmp.put("monthstartday",calendar.get(Calendar.DAY_OF_WEEK));
		return dayshasmp;
	}
	
	public static HashMap<String,String> getMonthStartEndDate(int year,int month)
	{
		HashMap<String,String> monHmap = new HashMap<String,String>();
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, 1);
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		month+=1;
		String mon=month<10?"0"+month:""+month;
		monHmap.put("numberofday", days+"");
		monHmap.put("monthstartday",calendar.get(Calendar.DAY_OF_WEEK)+"");
		monHmap.put("startdate",year+"-"+mon+"-01");
		monHmap.put("enddate", year+"-"+mon+"-"+days);
		return monHmap;
	}
}
