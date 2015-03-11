package com.product.home.shared;

import java.util.Date;

import com.github.gwtbootstrap.datepicker.client.ui.DateBoxAppended;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.product.home.client.MainService;
import com.product.home.client.MainServiceAsync;
import com.product.home.client.client.admin.attendance.ConstantsVariables;

public class DateUtil 
{
	static MainServiceAsync mainservice=GWT.create(MainService.class); 
	/**
	 * 
	 * @param date passing Date Object
	 * @return 
	 */
	public static String convertdates(Date date) 
	{
		// date format Conversion from date to String of format(dd-MM-yyyy) like 05-01-2013
		String reqdate="";
		DateTimeFormat format=null;
		try 
		{
			format=DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss");
			reqdate=format.format(date);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return reqdate;
	}

	/**
	 * 
	 * @param obj1 DateBox object your DateBoxAppended Object
	 *  
	 * @Condition
	 * 0-means
	 * 	obj1 equalto currentDate ,
	 * 
	 * 1-means
	 * obj1 greaterthan currentDate ,
	 * 
	 * -1-means
	 * 'obj1 lessthan currentDate' 
	 * 	
	 * @return false or true
	 */
	public static int checkCurrentDate(DateBoxAppended obj1)
	{
		int check=0;
		
		String checkdate=convertdates(obj1.getValue(),"yyyy-MM-dd");
		
		if(Integer.parseInt(checkdate.replace("-",""))>Integer.parseInt(ConstantsVariables.serverDate.replace("-","")))
		{
			check=1;
		}
		else if(Integer.parseInt(checkdate.replace("-",""))<Integer.parseInt(ConstantsVariables.serverDate.replace("-","")))
		{
			check=-1;
		}
		
		return check;
	}
	
	public static String convertdates(Date date,String reqformat) 
	{
		// date format Conversion from date to String of format(dd-MM-yyyy) like 05-01-2013
		String reqdate="";
		DateTimeFormat format=null;
		try 
		{
			format=DateTimeFormat.getFormat(reqformat);
			reqdate=format.format(date);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return reqdate;
	}
	
	
	
	public static void getSeverDate()
	{
		mainservice.getServerDate(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				ConstantsVariables.serverDate=result;
			}
		});
	}
	
	
}
