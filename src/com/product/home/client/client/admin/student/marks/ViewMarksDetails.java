package com.product.home.client.client.admin.student.marks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
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
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.product.home.client.MainService;
import com.product.home.client.MainServiceAsync;
import com.product.home.client.client.admin.AdminBody;
import com.product.home.client.client.admin.attendance.ConstantsVariables;
import com.product.home.client.client.admin.attendance.ShowPopUp;
import com.product.home.shared.DataUtilities;


public class ViewMarksDetails extends AdminBody {
	
	MainServiceAsync mainservice=GWT.create(MainService.class);
	
	ListBox coursecomboBox = new ListBox();
	ListBox sectioncomboBox = new ListBox();
	ListBox examcomboBox = new ListBox();
	
	Button btnSearchbox = new Button();
	Button entermarks = new Button("Enter Marks");
	
	HashMap<String,String> coursenameAndIdhmap=new HashMap<String, String>();
	
	HashMap<String,String> tempsectionnamesAndIdhmap=new HashMap<String, String>();
	HashMap<String,String> sectionnamesAndIdhmap=new HashMap<String, String>();
	
	HashMap<String,String> examnameAndIdhmap=new HashMap<String, String>();
	
	Label lblErrormessage = new Label();
	private final FlexTable showdataflexTable = new FlexTable();
	private final Label lblNewLabel = new Label("View Marks Report");
	
	public ViewMarksDetails() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setWidth("100%");
		
		FlexTable topbtnflexTable = new FlexTable();
		flexTable.setWidget(0, 0, topbtnflexTable);
		topbtnflexTable.setSize("850px", "42px");
		lblNewLabel.setStyleName("subheadingfontcss");
		
		topbtnflexTable.setWidget(0, 0, lblNewLabel);
		entermarks.setText("Enter Marks");
		
		entermarks.setType(ButtonType.PRIMARY);
		
		if(!Cookies.getCookie("rid").equals("3") && !Cookies.getCookie("rid").equals("4"))
		{
			topbtnflexTable.setWidget(0, 1, entermarks);
			topbtnflexTable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		}
				
		FlexTable mainflexTable = new FlexTable();
		mainflexTable.setStyleName("addboarder");
		flexTable.setWidget(1, 0, mainflexTable);
		mainflexTable.setSize("700px", "394px");
		
		FlexTable listboxflexTable = new FlexTable();
		mainflexTable.setWidget(0, 0, listboxflexTable);
		listboxflexTable.setSize("750px", "51px");
		
		
		listboxflexTable.setWidget(0, 0, coursecomboBox);
		
	
		listboxflexTable.setWidget(0, 1, sectioncomboBox);
		
		
		listboxflexTable.setWidget(0, 2, examcomboBox);
		
		btnSearchbox.setIcon(IconType.SEARCH);
		listboxflexTable.setWidget(0, 3, btnSearchbox);
		listboxflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		listboxflexTable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		listboxflexTable.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_CENTER);
		mainflexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		mainflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		lblErrormessage.setStyleName("error-Label");
		
		mainflexTable.setWidget(1, 0, lblErrormessage);
		
		ScrollPanel scrollPanel = new ScrollPanel();
		mainflexTable.setWidget(2, 0, scrollPanel);
		scrollPanel.setSize("700px", "337px");
		
		scrollPanel.setWidget(showdataflexTable);
		showdataflexTable.setSize("100%", "50px");
		mainflexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainflexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	
		entermarks.setVisible(true);
		getCourses();
		getSections();
		getExamNames();
		refreshdata();
		
		btnSearchbox.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				validation();
			}
		});
		
		entermarks.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				ConstantsVariables.showPopup=new ShowPopUp();
				ConstantsVariables.showPopup.lblHeadername.setText("Enter Marks");
				ConstantsVariables.showPopup.setGlassEnabled(true);
				EnterMarksScreen entermarks=new EnterMarksScreen();
				ConstantsVariables.showPopup.flexTable.setWidget(1, 0, entermarks);
				ConstantsVariables.showPopup.center();
				ConstantsVariables.showPopup.show();
			}
		});
		
		coursecomboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				
				showdataflexTable.removeAllRows();
				if(coursecomboBox.getSelectedIndex()!=0)
				{
					String courseid=coursenameAndIdhmap.get(coursecomboBox.getValue(coursecomboBox.getSelectedIndex()));
					getSectionBasedOnCourseId(courseid);
				}
				else
				{
					refreshdata();
					examcomboBox.setSelectedIndex(0);
				}
			}
		});
		
		sectioncomboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				showdataflexTable.removeAllRows();				
			}
		});
		
		examcomboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				showdataflexTable.removeAllRows();
			}
		});
		
	}

	public void resetData()
	{
		coursecomboBox.setSelectedIndex(0);
		sectioncomboBox.setSelectedIndex(0);
		examcomboBox.setSelectedIndex(0);
		showdataflexTable.removeAllRows();
	}
	
	public void refreshdata()
	{
		sectioncomboBox.clear();
		sectioncomboBox.addItem("Select Section");
		examcomboBox.setSelectedIndex(0);
	}
	
	public void validation()
	{
		if(coursecomboBox.getItemCount()==0 || coursecomboBox.getSelectedIndex()==0)
		{
			lblErrormessage.setText("Please Select CourseName");
			showdataflexTable.removeAllRows();
		}
		else if(sectioncomboBox.getItemCount()==0 || sectioncomboBox.getSelectedIndex()==0)
		{
			lblErrormessage.setText("Please Select SectionName");
			showdataflexTable.removeAllRows();
		}
		else if(examcomboBox.getItemCount()==0 || examcomboBox.getSelectedIndex()==0)
		{
			lblErrormessage.setText("Please Select ExamName");
			showdataflexTable.removeAllRows();
		}
		else
		{
			lblErrormessage.setText("");
			
			JSONObject getmarksdata=new JSONObject();
			getmarksdata.put("courseid", new JSONString(coursenameAndIdhmap.get(coursecomboBox.getValue(coursecomboBox.getSelectedIndex()))));
			getmarksdata.put("sectionid", new JSONString(sectionnamesAndIdhmap.get(sectioncomboBox.getValue(sectioncomboBox.getSelectedIndex()))));
			getmarksdata.put("examid", new JSONString(examnameAndIdhmap.get(examcomboBox.getValue(examcomboBox.getSelectedIndex()))));
			
			getmarksdata.put("studentid", new JSONString("all"));
			if(Cookies.getCookie("rid").equals("3"))
			{
				getmarksdata.put("studentid", new JSONString(Cookies.getCookie("stid")));
			}
			
			getMarksReport(getmarksdata);
		}
	}
	
	
	public void getMarksReport(JSONObject jsonobject)
	{
		mainservice.getMarksReport(jsonobject.toString(),new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				
				JSONObject response=JSONParser.parseStrict(result).isObject();
				if(response!=null && response.size()!=0 && response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					showMarkReport(response.get("subjects").isObject(),response.get("marks").isArray());
				}
			}
		});
	}
	
	
	public void showMarkReport(JSONObject subjectsobj,JSONArray marksarray)
	{
		showdataflexTable.removeAllRows();
		if(subjectsobj.size()!=0)
		{
			Label lblStudentName = new Label("Student Name");
			lblStudentName.setStyleName("subheadingfontcss");
			showdataflexTable.setWidget(0, 0, lblStudentName);
			
			Set<String>  set=subjectsobj.keySet();
			Iterator<String> it=set.iterator();
			int i=1;
			while(it.hasNext())
			{
				String subjectName=it.next();
				Label lblsubjectname = new Label(subjectName);
				lblsubjectname.setStyleName("subheadingfontcss");
				showdataflexTable.setWidget(0, i, lblsubjectname);
				i++;
			}
			
			if(marksarray.size()!=0)
			{
				for(int j=0;j<marksarray.size();j++)
				{
					JSONObject studentobject=marksarray.get(j).isObject();
					String fullname=studentobject.get("lname").isString().stringValue()+" "+studentobject.get("fname").isString().stringValue()+" "+studentobject.get("mname").isString().stringValue();
					
					Label lblStudentNamedata = new Label(fullname);
					lblStudentName.setStyleName("subheadingfontcss");
					showdataflexTable.setWidget(j+1,0, lblStudentNamedata);
					
					JSONArray markdataarray=studentobject.get("marksdata").isArray();
					if(markdataarray.size()!=0)
					{
						for(int z=0;z<markdataarray.size();z++)
						{
							JSONObject subjectobj=markdataarray.get(z).isObject();
							Label subjectmarks = new Label(subjectobj.get("marks").isString().stringValue());
							showdataflexTable.setWidget(j+1,z+1,subjectmarks);
						}
					}
				}
			}
		}	
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
				coursecomboBox.setEnabled(true);
				coursecomboBox.clear();
				coursenameAndIdhmap.clear();
				coursecomboBox.addItem("Select Course");
				if(responseobj.size()!=0 && responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray coursesarray=responseobj.get("courses").isArray();
					if(coursesarray.size()!=0)
					{
						for(int i=0;i<coursesarray.size();i++)
						{
							JSONObject coursedetails=coursesarray.get(i).isObject();
							coursecomboBox.addItem(coursedetails.get("coursename").isString().stringValue());
							coursenameAndIdhmap.put(coursedetails.get("coursename").isString().stringValue(), coursedetails.get("courseid").isString().stringValue());
						}
						
						if(Cookies.getCookie("rid").equals("3"))
						{
							entermarks.setVisible(false);
							String coursename=DataUtilities.getHashMapKey(coursenameAndIdhmap, Cookies.getCookie("cid"));
							if(coursename!=null)
							{
								DataUtilities.selcomboBoxValue(coursecomboBox, coursename);
								coursecomboBox.setEnabled(false);
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
				tempsectionnamesAndIdhmap.clear();
				if(responseobj.size()!=0 && responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray sectiondetailsarry=responseobj.get("sections").isArray();
					for(int i=0;i<sectiondetailsarry.size();i++)
					{
						JSONObject sectionjsonobj=sectiondetailsarry.get(i).isObject();
						tempsectionnamesAndIdhmap.put(sectionjsonobj.get("sectionname").isString().stringValue(), sectionjsonobj.get("sectionid").isString().stringValue());
					}
				}
			}
		});
	}
	
	public void getSectionBasedOnCourseId(String courseId)
	{
		mainservice.getCourseDetails(courseId,new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String jsonobj) {
				
				JSONValue value=JSONParser.parseStrict(jsonobj);
				JSONObject responseobj=value.isObject();
				sectioncomboBox.setEnabled(true);
				sectioncomboBox.clear();
				sectioncomboBox.addItem("Select Section");
				sectionnamesAndIdhmap.clear();
				if(responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONObject coursedetails=responseobj.get("coursedata").isObject();
					JSONArray sectionarray=coursedetails.get("sectionid").isArray();
					for(int p=0;p<sectionarray.size();p++)
					{
						String sectionname=CreateExamsNamesScreen.getHashMapKey(tempsectionnamesAndIdhmap, sectionarray.get(p).isString().stringValue());
						sectioncomboBox.addItem(sectionname);
						sectionnamesAndIdhmap.put(sectionname,sectionarray.get(p).isString().stringValue());
					}
					if(Cookies.getCookie("rid").equals("3"))
					{
						String sectionname=DataUtilities.getHashMapKey(sectionnamesAndIdhmap, Cookies.getCookie("sid"));
						if(sectionname!=null)
						{
							DataUtilities.selcomboBoxValue(sectioncomboBox, sectionname);
							sectioncomboBox.setEnabled(false);
						}
					}
				}
			}
		});
	}
	
	
	public void getExamNames()
	{
		mainservice.getExamNames(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				
				JSONObject response=JSONParser.parseStrict(result).isObject();
				examcomboBox.clear();
				examnameAndIdhmap.clear();
				if(response!=null && response.size()!=0 && response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					examcomboBox.addItem("Select ExamName");
					JSONArray examsarray=response.get("exams").isArray();
					if(examsarray.size()!=0)
					{
						for(int i=0;i<examsarray.size();i++)
						{
							JSONObject examdate=examsarray.get(i).isObject();
							examcomboBox.addItem(examdate.get("examname").isString().stringValue());
							examnameAndIdhmap.put(examdate.get("examname").isString().stringValue(), examdate.get("examid").isString().stringValue());
						}
						examcomboBox.setSelectedIndex(0);
					}
				}
			}
		});
	}

	@Override
	public void reset() {
		
		getCourses();
		getSections();
		getExamNames();
	}
}
