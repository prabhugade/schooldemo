package com.product.home.client.client.admin.event;

import java.util.Date;
import java.util.HashMap;

import com.github.gwtbootstrap.client.ui.Label;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.product.home.client.MainService;
import com.product.home.client.MainServiceAsync;
import com.product.home.client.client.admin.AdminBody;

public class EventCalender extends AdminBody
{
	
	MainServiceAsync mainservice=GWT.create(MainService.class);

	//backward and forward buttons using the display month and year
	Image btnbackwardmonth = new Image();
	Image btnforwardmonth = new Image();
	HTML lblmonthnameandyear = new HTML("");

	//monthvalue and monthnames key number and value name
	HashMap<String,String> monthhashmap=new HashMap<String,String>();

	//dayvalues and daynames key number and value name
	HashMap<String,String> dayshashmap=new HashMap<String,String>();
	
	//displaying the calendar
	FlexTable calendarshowflextable = new FlexTable();

	//present month name value based on hashmap
	int month=0;
	int year=0;	
	int weekcount=1;
	int days=0;
	int rowcount =0;
	String currentDate="";

	@SuppressWarnings("deprecation")
	public EventCalender() {

		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setWidth("100%");

		FlexTable mainflextable = new FlexTable();
		flexTable.setWidget(0, 0, mainflextable);
		mainflextable.setSize("800px", "600px");

		FlexTable dataflextable = new FlexTable();
		mainflextable.setWidget(0, 0, dataflextable);
		dataflextable.setSize("793px", "93px");

		FlexTable listboxfletable = new FlexTable();
		dataflextable.setWidget(0, 0, listboxfletable);
		listboxfletable.setSize("100%", "102px");

		FlexTable changemonthflextable = new FlexTable();
		listboxfletable.setWidget(0, 0, changemonthflextable);
		changemonthflextable.setWidth("200px");
		btnbackwardmonth.setStyleName("handsymbol");
		btnbackwardmonth.setUrl("images/atndback_arrow-l.png");

		changemonthflextable.setWidget(0, 0, btnbackwardmonth);
		lblmonthnameandyear.setStyleName("btnforwardandbackward");

		changemonthflextable.setWidget(0, 1, lblmonthnameandyear);
		lblmonthnameandyear.setSize("150px", "");
		btnforwardmonth.setStyleName("handsymbol");
		btnforwardmonth.setUrl("images/atndforward_arrow.png");

		changemonthflextable.setWidget(0, 2, btnforwardmonth);
		changemonthflextable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		changemonthflextable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_BOTTOM);
		changemonthflextable.getCellFormatter().setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_BOTTOM);
		changemonthflextable.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		listboxfletable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		listboxfletable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		listboxfletable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		dataflextable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);

		ScrollPanel scrollPanel = new ScrollPanel();
		dataflextable.setWidget(1, 0, scrollPanel);
		scrollPanel.setSize("900px", "472px");

		Date date=new Date();
		month=date.getMonth();
		year=date.getYear()+1900;
		String currentmonth=""+(month+1);
		if((month+1)<=9)
			currentmonth="0"+(month+1);

		int daydate=date.getDate();
		String daydatevalue=""+daydate;
		if(daydate<=9)
			daydatevalue="0"+daydate;

		currentDate=year+"-"+currentmonth+"-"+daydatevalue;

		scrollPanel.setWidget(calendarshowflextable);
		calendarshowflextable.setSize("99.5%", "45px");
		dataflextable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		mainflextable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		mainflextable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);	

		monthhashmap.clear();
		monthhashmap.put("0","JANUARY");
		monthhashmap.put("1","FEBRUARY");
		monthhashmap.put("2","MARCH");
		monthhashmap.put("3","APRIL");
		monthhashmap.put("4","MAY");
		monthhashmap.put("5","JUNE");
		monthhashmap.put("6","JULY");
		monthhashmap.put("7","AUGUST");
		monthhashmap.put("8","SEPTEMBER");
		monthhashmap.put("9","OCTOBER");
		monthhashmap.put("10","NOVEMBER");
		monthhashmap.put("11","DECEMBER");

		dayshashmap.clear();
		dayshashmap.put("1","SUNDAY");
		dayshashmap.put("2","MONDAY");
		dayshashmap.put("3","TUESDAY");
		dayshashmap.put("4","WEDNESDAY");
		dayshashmap.put("5","THURSDAY");
		dayshashmap.put("6","FRIDAY");
		dayshashmap.put("7","SATURDAY");

		btnbackwardmonth.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				if(month==0)
				{
					month=11;
					year=year-1;
				}
				else
				{
					month=month-1;
				}
				showingMonthAndYear("0",monthhashmap.get(""+month),""+year);//0 represent backward
			}
		});

		btnforwardmonth.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				if(month==11)
				{
					year=year+1;
					month=0;
				}
				else
				{
					month=month+1;
				}
				showingMonthAndYear("1",monthhashmap.get(""+month),""+year);//1 represent forward
			}
		});
		
		showingMonthAndYear("0",monthhashmap.get(""+month),""+year);//0 represent backward
	}
	
	public void showingMonthAndYear(String buttonclick, String month,String year)
	{
		if(buttonclick.equals("0"))
		{
			lblmonthnameandyear.setHTML(""+month+"   "+year);
		}
		else if(buttonclick.equals("1"))
		{
			lblmonthnameandyear.setText(""+month+"    "+year);
		}
		getEventsData();
	}
	
	public void getEventsData()
	{
		mainservice.getEventsData(year,month,Cookies.getCookie("rid"),new AsyncCallback<String>() 
		{

			@Override
			public void onFailure(Throwable caught) 
			{
				
			}

			@Override
			public void onSuccess(String result) 
			{
				if(result!=null&&result.trim().length()!=0)
				{
					JSONObject respObj = JSONParser.parseStrict(result).isObject();
					if(respObj.get("status").isString().stringValue().equalsIgnoreCase("success"))
					{
						 weekcount=Integer.parseInt(respObj.get("monthstartday").isString().stringValue());
						 days=Integer.parseInt(respObj.get("numberofday").isString().stringValue());
						 JSONObject eventdataObj = respObj.get("eventdata").isObject();
						 showEventCalendar(eventdataObj);
					}
				}
			}
			
		});
	}
	public void showEventCalendar(JSONObject eventdataObj)
	{
		calendarshowflextable.removeAllRows();
		calendarshowflextable.getCellFormatter().setStyleName(0, 0,"attendanceflextable");
		for(int d=0,col=0;d<7;d++,col++)
		{
			HTML dayname=new HTML();
			dayname.setSize("20x","40px");
			if(weekcount==8)
			{
				weekcount=1;
				dayname.setHTML("<font color='red'>"+dayshashmap.get(""+weekcount)+"</font>");
				calendarshowflextable.setWidget(0,col,dayname);
			}
			else
			{
				dayname.setHTML("<font color='red'>"+dayshashmap.get(""+weekcount)+"</font>");
				calendarshowflextable.setWidget(0,col,dayname);
			}
			calendarshowflextable.getCellFormatter().setHorizontalAlignment(0, col, HasHorizontalAlignment.ALIGN_CENTER);
			calendarshowflextable.getCellFormatter().setStyleName(0,col,"attendanceflextable");
			weekcount++;
		}
		int i;
		int row=1,col=0;
		weekcount=0;
		int Month=month+1;
		String mon=Month<10?"0"+Month:Month+"";
		String date=year+"-"+mon+"-";
		for(i=1;i<=days;i++)
		{
			String day = i<10?"0"+i:i+"";
			String today = date+day;
			final FlexTable dateTable = new FlexTable();
			HTML dayname=new HTML();
			dayname.setSize("20x","40px");
			if(weekcount==7)
			{
				col=0;
				weekcount=0;
				row++;
				dayname.setHTML("<font color=''>"+i+"</font>");
				dateTable.setWidget(0, 0,dayname);
				if(eventdataObj.containsKey(today))
				{
					JSONArray data_array = eventdataObj.get(today).isArray();
					for(int d=0,r=1;d<data_array.size();d++,r++)
					{
						JSONObject eventObj = data_array.get(d).isObject();
						Label lab = new Label();
						lab.setText(eventObj.get("eventtype").isString().stringValue());
						lab.getElement().setId(eventObj.get("eventid").isString().stringValue());
						lab.setTitle(eventObj.get("eventtype").isString().stringValue()+"\n"+eventObj.get("message").isString().stringValue()+"\n"+eventObj.get("desc").isString().stringValue()+"\n"+eventObj.get("starttime")+"\n"+eventObj.get("endtime"));
						dateTable.setWidget(r, 0,lab);
					}
				}
				calendarshowflextable.setWidget(row,col,dateTable);
			}
			else
			{
				dayname.setHTML("<font color=''>"+i+"</font>");
				dateTable.setWidget(0, 0,dayname);
				if(eventdataObj.containsKey(today))
				{
					JSONArray data_array = eventdataObj.get(today).isArray();
					for(int d=0,r=1;d<data_array.size();d++,r++)
					{
						JSONObject eventObj = data_array.get(d).isObject();
						Label lab = new Label();
						lab.setText(eventObj.get("eventtype").isString().stringValue());
						lab.getElement().setId(eventObj.get("eventid").isString().stringValue());
						lab.setTitle(eventObj.get("eventtype").isString().stringValue()+"\n"+eventObj.get("message").isString().stringValue()+"\n"+eventObj.get("desc").isString().stringValue()+"\n"+eventObj.get("starttime")+"\n"+eventObj.get("endtime"));
						dateTable.setWidget(r, 0,lab);
					}
				}
				calendarshowflextable.setWidget(row,col,dateTable);
			}
			calendarshowflextable.getCellFormatter().setHorizontalAlignment(row,col, HasHorizontalAlignment.ALIGN_CENTER);
			calendarshowflextable.getCellFormatter().setStyleName(row,col,"attendanceflextable");
			weekcount++;
			col++;
			
			dateTable.addClickHandler(new ClickHandler() 
			{
				@Override
				public void onClick(ClickEvent event) 
				{
					int colIndex = dateTable.getCellForEvent(event).getCellIndex();
					int rowIndex = dateTable.getCellForEvent(event).getRowIndex();
					Window.alert(rowIndex+" "+colIndex);
				}
			});
		}
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
