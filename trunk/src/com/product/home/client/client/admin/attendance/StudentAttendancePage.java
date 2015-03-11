package com.product.home.client.client.admin.attendance;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Image;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.product.home.client.Jsonkeys;
import com.product.home.client.MainService;
import com.product.home.client.MainServiceAsync;
import com.product.home.client.client.admin.AdminBody;
import com.product.home.client.client.admin.student.marks.CreateExamsNamesScreen;
import com.product.home.shared.DataUtilities;

public class StudentAttendancePage extends AdminBody {

	MainServiceAsync mainservice=GWT.create(MainService.class);
	
	//backward and forward buttons using the display month and year
	Image btnbackwardmonth = new Image();
	Image btnforwardmonth = new Image();
	HTML lblmonthnameandyear = new HTML("");
	
	//displaying the student attendance
	FlexTable attendanceshowflextable = new FlexTable();
	
	//monthvalue and monthnames key number and value name
	HashMap<String,String> monthhashmap=new HashMap<String,String>();
	
	//dayvalues and daynames key number and value name
	HashMap<String,String> dayshashmap=new HashMap<String,String>();
	
	
	ListBox coursenamecomboBox = new ListBox();
	HashMap<String,String> coursedetailshmap=new HashMap<String,String>();
	HashMap<String,String> sectionnamesAndIdhmap=new HashMap<String,String>();
	HashMap<String,String> subjectnameAndIdhmap=new HashMap<String, String>();
	ListBox sectionnamecomboBox = new ListBox();
	JSONObject sectionsjson=new JSONObject();
	
	ListBox subjectnamecomboBox = new ListBox();
	
	//present month name value based on hashmap
	int month=0;
	int year=0;	
	int weekcount=1;
	int days=0;
	int rowcount =0;
	String currentDate="";
	JSONObject editattedancedatajsonobj;
	private final Button btnSearch = new Button();
	@SuppressWarnings("deprecation")
	public StudentAttendancePage() {
		
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
		
		FlexTable listflexTable = new FlexTable();
		listboxfletable.setWidget(0, 0, listflexTable);
		listflexTable.setWidth("761px");
		
		
		listflexTable.setWidget(0, 0, coursenamecomboBox);
		
		
		listflexTable.setWidget(0, 1, sectionnamecomboBox);
		
		
		listflexTable.setWidget(0, 2, subjectnamecomboBox);
		
		btnSearch.setIcon(IconType.SEARCH);
		listflexTable.setWidget(0, 3, btnSearch);
		
		FlexTable changemonthflextable = new FlexTable();
		listboxfletable.setWidget(1, 0, changemonthflextable);
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
		
		scrollPanel.setWidget(attendanceshowflextable);
		attendanceshowflextable.setSize("99.5%", "45px");
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
		dayshashmap.put("1","Su");
		dayshashmap.put("2","M");
		dayshashmap.put("3","Tu");
		dayshashmap.put("4","W");
		dayshashmap.put("5","Th");
		dayshashmap.put("6","F");
		dayshashmap.put("7","Sa");
		
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
		
		coursenamecomboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				
				if(coursenamecomboBox.getSelectedIndex()!=0)
				{
					getSectionBasedOnCourseId(coursedetailshmap.get(coursenamecomboBox.getValue(coursenamecomboBox.getSelectedIndex())));
				}
				else
				{
					sectionnamecomboBox.clear();
					sectionnamecomboBox.addItem("Select Section");
					sectionnamecomboBox.setSelectedIndex(0);
					
					subjectnamecomboBox.clear();
					subjectnamecomboBox.addItem("Select Subject");
					subjectnamecomboBox.setSelectedIndex(0);
					attendanceshowflextable.removeAllRows();
				}
			}
		});
		
		sectionnamecomboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				
				
				if(sectionnamecomboBox.getSelectedIndex()!=0)
				{
					getSectionData(sectionnamesAndIdhmap.get(sectionnamecomboBox.getValue(sectionnamecomboBox.getSelectedIndex())));
				}
				else
				{	
					subjectnamecomboBox.clear();
					subjectnamecomboBox.addItem("Select Subject");
					subjectnamecomboBox.setSelectedIndex(0);
					attendanceshowflextable.removeAllRows();
				}
			}
		});
		
		btnSearch.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				if(coursenamecomboBox.getSelectedIndex()!=0)
				{
					if(sectionnamecomboBox.getSelectedIndex()!=0)
					{
						if(subjectnamecomboBox.getSelectedIndex()!=0)
						{
							getDaysAndStartingDayName();
						}
						else
						{
							Window.alert("Please Select SubjectName");
						}
					}
					else
					{
						Window.alert("Please Select SectionName");
					}
				}
				else
				{
					Window.alert("Please Select CourseName");
				}
			}
		});
		
		
		//getDaysAndStartingDayName();
		showingMonthAndYear("0",monthhashmap.get(""+month),""+year);//0 represent backward
		getCourses();
		getSections();
		getSubjects();
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
		getDaysAndStartingDayName();
	}
	
	public void showStudentAttendace()
	{
		attendanceshowflextable.removeAllRows();
		attendanceshowflextable.setWidget(0,0,new Label("Name"));
		attendanceshowflextable.getCellFormatter().setStyleName(0, 0,"attendanceflextable");
		int i;
		for(i=1;i<=days;i++)
		{
			HTML dayname=new HTML();
			dayname.setSize("20x","40px");
			if(weekcount==8)
			{
				weekcount=1;
				dayname.setHTML("<font color='red'>"+dayshashmap.get(""+weekcount)+"</font><br><font color=''>"+i+"</font>");
				attendanceshowflextable.setWidget(0,i,dayname);
			}
			else
			{
				dayname.setHTML("<font color='red'>"+dayshashmap.get(""+weekcount)+"</font><br><font color=''>"+i+"</font>");
				attendanceshowflextable.setWidget(0,i,dayname);
			}
			attendanceshowflextable.getCellFormatter().setHorizontalAlignment(0, i, HasHorizontalAlignment.ALIGN_CENTER);
			attendanceshowflextable.getCellFormatter().setStyleName(0, i,"attendanceflextable");
			weekcount++;
		}
		
		JSONObject seachdata=new JSONObject();
		seachdata.put("studentid",new JSONString("All"));
		if(Cookies.getCookie("rid").equals("3"))
		{
			seachdata.put("studentid",new JSONString(Cookies.getCookie("stid")));
		}
		String month1=(month+1)+"";
		if(month1.length()==1)
			month1="0"+month1;
		seachdata.put("fromdate",new JSONString(year+"-"+month1+"-01"));
		seachdata.put("todate",new JSONString(year+"-"+month1+"-31"));
		if(coursenamecomboBox.getSelectedIndex()!=0 && sectionnamecomboBox.getSelectedIndex()!=0 && subjectnamecomboBox.getSelectedIndex()!=0)
		{
			seachdata.put("courseid",new JSONString(coursedetailshmap.get(coursenamecomboBox.getValue(coursenamecomboBox.getSelectedIndex()))));
			seachdata.put("sectionid",new JSONString(sectionnamesAndIdhmap.get(sectionnamecomboBox.getValue(sectionnamecomboBox.getSelectedIndex()))));
			seachdata.put("subjectid",new JSONString(subjectnameAndIdhmap.get(subjectnamecomboBox.getValue(subjectnamecomboBox.getSelectedIndex()))));
			getStudentAttendance(seachdata.toString());
		}
		else
		{
			attendanceshowflextable.removeAllRows();
		}
	}
	
	private void getStudentAttendance(String attendacedata)
	{
		mainservice.getStudentAttendanceData(attendacedata,new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String jsonobj) {
				JSONValue value=JSONParser.parseStrict(jsonobj);
				JSONObject responseobj=value.isObject();
				if(responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray attendancearray=responseobj.get("data").isArray();
					showstudentData(attendancearray);
				}
			}
		});
		
	}

	public void showstudentData(JSONArray attendancearray)
	{
		if(attendancearray.size()!=0)
		{
			//TODO
			String rid=Cookies.getCookie("rid");
			ArrayList<String> studentids=new ArrayList<String>();
			if(rid.equalsIgnoreCase("4"))
			{
				JSONArray array=JSONParser.parseStrict(Cookies.getCookie(Jsonkeys.studentdata)).isArray();
				for(int p=0;p<array.size();p++)
				{
					JSONObject tempjson=array.get(p).isObject();
					studentids.add(tempjson.get(Jsonkeys.studentid).isString().stringValue());
				}
			}
			rowcount=0;
			rowcount =attendanceshowflextable.getRowCount();
			for(int j=0;j<attendancearray.size();j++)
			{
				JSONObject studentjsonobj=attendancearray.get(j).isObject();
				if((rid.equalsIgnoreCase("4")&&studentids.contains(studentjsonobj.get("studentid").isString().stringValue()))||!(rid.equalsIgnoreCase("4")))
				{
					attendanceshowflextable.setWidget(rowcount,0,new Label(studentjsonobj.get("studentname").isString().stringValue()));
					attendanceshowflextable.getCellFormatter().setStyleName(rowcount, 0,"attendanceflextable");
					JSONArray absentdataarray=studentjsonobj.get("absentdates").isArray();
					HashMap<String,JSONObject> datehashmap=new HashMap<String,JSONObject>();
					if(absentdataarray.size()!=0)
					{
						for(int z=0;z<absentdataarray.size();z++)
						{
							JSONObject absentdatajsoobj=absentdataarray.get(z).isObject();
							String datearray=absentdatajsoobj.get("datetime").isString().stringValue();
							datehashmap.put(datearray,absentdatajsoobj);
						}
					}
					for(int i=1;i<=days;i++)
					{
						final Label dayname=new Label();

						String daymonthvalue=""+(month+1);
						if((month+1)<=9)
							daymonthvalue="0"+daymonthvalue;

						String datevalue=year+"-"+daymonthvalue+"-"+i;
						if(i<=9)
							datevalue=year+"-"+daymonthvalue+"-0"+i;

						JSONObject data=new JSONObject();
						data.put("studentid",new JSONString(""+studentjsonobj.get("studentid").isString().stringValue()));
						data.put("courseid",new JSONString(coursedetailshmap.get(coursenamecomboBox.getValue(coursenamecomboBox.getSelectedIndex()))));
						data.put("sectionid",new JSONString(sectionnamesAndIdhmap.get(sectionnamecomboBox.getValue(sectionnamecomboBox.getSelectedIndex()))));
						data.put("subjectid",new JSONString(subjectnameAndIdhmap.get(subjectnamecomboBox.getValue(subjectnamecomboBox.getSelectedIndex()))));
						data.put("reason",new JSONString("-"));
						data.put("type",new JSONString("-"));
						data.put("attendenceid",new JSONString("0"));
						data.put("datetime",new JSONString(datevalue));
						dayname.setStyleName("whitecolorcss");
						dayname.setText("X");
						dayname.setTitle(datevalue);
						if(datehashmap.size()!=0)
						{
							if(datehashmap.containsKey(datevalue))
							{
								dayname.setStyleName("redcolorcss");
								dayname.setText("X");
								JSONObject previosedata=datehashmap.get(datevalue);
								data.put("reason",new JSONString(previosedata.get("reason").isString().stringValue()));
								data.put("type",new JSONString(previosedata.get("type").isString().stringValue()));
								data.put("datetime",new JSONString(previosedata.get("datetime").isString().stringValue()));
								data.put("attendenceid",new JSONString(previosedata.get("attendenceid").isString().stringValue()));
							}
						}
						attendanceshowflextable.setWidget(rowcount,i,dayname);
						attendanceshowflextable.getCellFormatter().setHorizontalAlignment(rowcount, i, HasHorizontalAlignment.ALIGN_CENTER);
						attendanceshowflextable.getCellFormatter().setStyleName(rowcount, i,"attendanceflextable");
						dayname.getElement().setId(data.toString());
						dayname.addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								String testingdate=dayname.getTitle();
								if(Integer.parseInt(currentDate.replaceAll("-",""))>=Integer.parseInt(testingdate.replaceAll("-","")))
								{
									showEditPopup(dayname.getElement().getId());
								}
								else
								{
									Window.alert("Not Allowed ,Please Check Your Selecting Date And Current Date");
								}

							}
						});
					}
					rowcount++;
				}
			}
		}
		else
		{
			Window.alert("No Records");
		}
	}
	
	public void getDaysAndStartingDayName()
	{
		mainservice.getDaysAndStartingDayName(year,month,new AsyncCallback<HashMap<String,Integer>>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(HashMap<String, Integer> result) {
				
				if(result!=null && result.size()!=0)
				{
					 weekcount=result.get("monthstartday");
					 days=result.get("numberofday");
					 showStudentAttendace();
				}
			}
		});
	}
	
	public void getCourses()
	{
		mainservice.getCourses(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String jsonobj) {
				
				JSONValue value=JSONParser.parseStrict(jsonobj);
				JSONObject responseobj=value.isObject();
				coursenamecomboBox.setEnabled(true);
				coursenamecomboBox.clear();
				coursedetailshmap.clear();
				coursenamecomboBox.addItem("Select CourseName");
				if(responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray coursesarray=responseobj.get("courses").isArray();
					if(coursesarray.size()!=0)
					{
						String rid=Cookies.getCookie("rid");
						ArrayList<String> courseids=new ArrayList<String>();
						if(rid.equalsIgnoreCase("4"))
						{
							JSONArray array=JSONParser.parseStrict(Cookies.getCookie(Jsonkeys.studentdata)).isArray();
							for(int p=0;p<array.size();p++)
							{
								JSONObject tempjson=array.get(p).isObject();
								courseids.add(tempjson.get("courseid").isString().stringValue());
							}
						}
						for(int i=0;i<coursesarray.size();i++)
						{
							JSONObject coursedetails=coursesarray.get(i).isObject();
							if(rid.equalsIgnoreCase("4"))
							{
								if(courseids.contains(coursedetails.get("courseid").isString().stringValue()))
									coursenamecomboBox.addItem(coursedetails.get("coursename").isString().stringValue());
							}else
							{
								coursenamecomboBox.addItem(coursedetails.get("coursename").isString().stringValue());
							}
							coursedetailshmap.put(coursedetails.get("coursename").isString().stringValue(), coursedetails.get("courseid").isString().stringValue());
						}
						
						if(rid.equals("3"))
						{
							String coursename=DataUtilities.getHashMapKey(coursedetailshmap, Cookies.getCookie("cid"));
							if(coursename!=null)
							{
								DataUtilities.selcomboBoxValue(coursenamecomboBox, coursename);
								coursenamecomboBox.setEnabled(false);
								getSectionBasedOnCourseId(Cookies.getCookie("cid"));
							}
						}
					}
				}
				else
				{
					Window.alert("Sorry No Courses");
				}
			}
		});
	}
	
	public void getSections()
	{
		mainservice.getSections(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String jsonobj) {
				JSONValue value=JSONParser.parseStrict(jsonobj);
				JSONObject responseobj=value.isObject();
				sectionnamesAndIdhmap.clear();
				if(responseobj.size()!=0 && responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray sectiondetailsarry=responseobj.get("sections").isArray();
					for(int i=0;i<sectiondetailsarry.size();i++)
					{
						JSONObject sectionjsonobj=sectiondetailsarry.get(i).isObject();
						sectionnamesAndIdhmap.put(sectionjsonobj.get("sectionname").isString().stringValue(), sectionjsonobj.get("sectionid").isString().stringValue());
					}
				}
			}
		});
	}
	
	public void getSubjects()
	{
		mainservice.getSubjects(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String jsonobj) {
				JSONValue value=JSONParser.parseStrict(jsonobj);
				JSONObject responseobj=value.isObject();
				subjectnameAndIdhmap.clear();
				if(responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray subjectarray=responseobj.get("subjects").isArray();
					for(int i=0;i<subjectarray.size();i++)
					{
						JSONObject subjectdata=subjectarray.get(i).isObject();
						subjectnameAndIdhmap.put(subjectdata.get("subjectname").isString().stringValue(),subjectdata.get("subjectid").isString().stringValue());
					}
				}
			}
		});
	}
	
	
	
	public void getSectionBasedOnCourseId(final String courseId)
	{
		mainservice.getCourseDetails(courseId,new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String jsonobj) {
				
				JSONValue value=JSONParser.parseStrict(jsonobj);
				JSONObject responseobj=value.isObject();
				sectionnamecomboBox.setEnabled(true);
				sectionnamecomboBox.clear();
				sectionnamecomboBox.addItem("Select Section");
				if(responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					//TODO
					String rid=Cookies.getCookie("rid");
					ArrayList<String> sectionids=new ArrayList<String>();
					if(rid.equalsIgnoreCase("4"))
					{
						JSONArray array=JSONParser.parseStrict(Cookies.getCookie(Jsonkeys.studentdata)).isArray();
						for(int p=0;p<array.size();p++)
						{
							JSONObject tempjson=array.get(p).isObject();
							if(courseId.equalsIgnoreCase(tempjson.get("courseid").isString().stringValue()))
								sectionids.add(tempjson.get("sectionid").isString().stringValue());
						}
					}
					JSONObject coursedetails=responseobj.get("coursedata").isObject();
					JSONArray sectionarray=coursedetails.get("sectionid").isArray();
					for(int p=0;p<sectionarray.size();p++)
					{
						if(rid.equalsIgnoreCase("4"))
						{
							if(sectionids.contains(sectionarray.get(p).isString().stringValue()))
							{
								String sectionname=DataUtilities.getHashMapKey(sectionnamesAndIdhmap, sectionarray.get(p).isString().stringValue());
								sectionnamecomboBox.addItem(sectionname);
							}
						}else
						{
							String sectionname=DataUtilities.getHashMapKey(sectionnamesAndIdhmap, sectionarray.get(p).isString().stringValue());
							sectionnamecomboBox.addItem(sectionname);
						}
					}
					
					if(Cookies.getCookie("rid").equals("3"))
					{
						String sectionname=DataUtilities.getHashMapKey(sectionnamesAndIdhmap, Cookies.getCookie("sid"));
						if(sectionname!=null)
						{
							DataUtilities.selcomboBoxValue(sectionnamecomboBox, sectionname);
							sectionnamecomboBox.setEnabled(false);
							getSectionData(Cookies.getCookie("sid"));
						}
					}
				}
			}
		});
	}
	
	public void getSectionData(String sectionid)
	{
		mainservice.getSectionDetails(sectionid,new AsyncCallback<String>() 
		{
			@Override
			public void onFailure(Throwable caught) {
			}
			@Override
			public void onSuccess(String result) 
			{
				subjectnamecomboBox.clear();
				JSONObject responsejson=JSONParser.parseStrict(result).isObject();
				if(responsejson.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					subjectnamecomboBox.addItem("Select Subject");
					JSONObject sectiondatajson=responsejson.get("sectiondata").isObject();
					JSONArray subjectarray=sectiondatajson.get("subjectid").isArray();
					for(int p=0;p<subjectarray.size();p++)
					{
						String subjectname=CreateExamsNamesScreen.getHashMapKey(subjectnameAndIdhmap, subjectarray.get(p).isString().stringValue());
						subjectnamecomboBox.addItem(subjectname);
					}
				}
			}
		});
	}
	
	public void showEditPopup(String jsonObject)
	{
		ConstantsVariables.showPopup=new ShowPopUp();
		ConstantsVariables.showPopup.lblHeadername.setText("Attendance Edit");
		ConstantsVariables.showPopup.setGlassEnabled(true);
		final EditAttendanceScreen editattendance=new EditAttendanceScreen();
		ConstantsVariables.showPopup.flexTable.setWidget(1, 0, editattendance);
		ConstantsVariables.showPopup.center();
		ConstantsVariables.showPopup.show();
		
		editattendance.btnDelete.setVisible(true);
		editattendance.btnSave.setVisible(true);
		
		editattedancedatajsonobj=JSONParser.parseStrict(jsonObject).isObject();
		editattendance.leaveTypetextBox.setText("");
		editattendance.reasontextBox.setText("");
		editattendance.lblDate.setText("");
		editattendance.lblErrormessage.setText("");
		
		editattendance.lblDate.setText(editattedancedatajsonobj.get("datetime").isString().stringValue());
		if(editattedancedatajsonobj.get("type").isString().stringValue().equalsIgnoreCase("-"))
		{
			editattendance.btnDelete.setVisible(false);
			editattendance.leaveTypetextBox.setText("");
			editattendance.reasontextBox.setText("");
		}
		else
		{
			editattendance.leaveTypetextBox.setText(editattedancedatajsonobj.get("type").isString().stringValue());
			editattendance.reasontextBox.setText(editattedancedatajsonobj.get("reason").isString().stringValue());
			
			editattendance.btnDelete.setVisible(true);
		}
		
		if(Cookies.getCookie("rid").equals("3"))
		{
			editattendance.btnDelete.setVisible(false);
			editattendance.btnSave.setVisible(false);
		}
								
		
		editattendance.btnSave.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			
				//{"studentid":"2", "courseid":"2", "sectionid":"2", "subjectid":"2", "reason":"-", "type":"-", "datetime":"2013-12-08"}
				if(editattendance.leaveTypetextBox.getText().trim().length()==0)
				{
					editattendance.lblErrormessage.setText("Please Enter LeaveType");
				}
				else if(editattendance.reasontextBox.getText().trim().length()==0)
				{
					editattendance.lblErrormessage.setText("Please Enter Reason");
				}
				else
				{
					editattendance.lblErrormessage.setText("");
					editattedancedatajsonobj.put("type", new JSONString(editattendance.leaveTypetextBox.getText().trim()));
					editattedancedatajsonobj.put("reason", new JSONString(editattendance.reasontextBox.getText().trim()));
					saveeditattendance(editattedancedatajsonobj);
				}
			}
		});
		
		editattendance.btnDelete.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				delectattendancedata(editattedancedatajsonobj);
			}
		});
	}
	
	//saveedit attendance
	public void saveeditattendance(JSONObject modifiedattendance )
	{
		
		mainservice.saveeditattendance(modifiedattendance.toString(),new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
			
				JSONObject response=JSONParser.parseStrict(result).isObject();
				if(response!=null && response.size()!=0 && response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					showingMonthAndYear("0",monthhashmap.get(""+month),""+year);//0 represent backward
					ConstantsVariables.showPopup.hide();
				}
			}
		});
		
	}
	
	////delete attendance
	public void delectattendancedata(JSONObject deletedattendance)
	{
		
		mainservice.delectattendancedata(deletedattendance.toString(),new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
			
				JSONObject response=JSONParser.parseStrict(result).isObject();
				if(response!=null && response.size()!=0 && response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					showingMonthAndYear("0",monthhashmap.get(""+month),""+year);//0 represent backward
					ConstantsVariables.showPopup.hide();
				}
			}
		});
	}
	
	@Override
	public void reset() {
		getCourses();
		getSections();
		getSubjects();
	}
}
