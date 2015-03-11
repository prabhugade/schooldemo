package com.product.home.client.client.admin.student.marks;

import java.util.HashMap;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.product.home.client.MainService;
import com.product.home.client.MainServiceAsync;
import com.product.home.client.client.admin.AdminBody;

public class CreateExamsNamesScreen extends AdminBody{

	MainServiceAsync mainservice=GWT.create(MainService.class); 
	
	ListBox examNamescomboBox = new ListBox();
	
	TextBox examNametextBox = new TextBox();
	TextArea examDescriptiontextArea = new TextArea();
	
	ListBox coursecomboBox = new ListBox();
	ListBox sectioncomboBox = new ListBox();
	
	Button btnSubmit = new Button("Submit");
	Button btnReset = new Button("Reset");
	CheckBox activeCheckBox = new CheckBox("");
	
	HashMap<String,String> examnameAndIdhmap=new HashMap<String, String>();
	HashMap<String,String> coursenameAndIdhmap=new HashMap<String, String>();
	HashMap<String,String> tempsectionnamesAndIdhmap=new HashMap<String, String>();
	HashMap<String,String> sectionnamesAndIdhmap=new HashMap<String, String>();
	
	Label lblErrormessage = new Label("");
	
	/**
	 * This variable is used to select the section when get examdetails
	 */
	String selectedCourseSectionid="";
	public CreateExamsNamesScreen() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setWidth("100%");
		
		FlexTable mainflextable = new FlexTable();
		flexTable.setWidget(0, 0, mainflextable);
		mainflextable.setSize("100%", "351px");
		
		FlexTable datacomboxflexTable = new FlexTable();
		datacomboxflexTable.setStyleName("addboarder");
		mainflextable.setWidget(0, 0, datacomboxflexTable);
		datacomboxflexTable.setSize("70%", "357px");
		
		datacomboxflexTable.setWidget(0, 0, examNamescomboBox);
		examNamescomboBox.setWidth("235px");
		datacomboxflexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		
		FlexTable dataflexTable = new FlexTable();
		datacomboxflexTable.setWidget(1, 0, dataflexTable);
		dataflexTable.setSize("322px", "257px");
		
		Label lblExamname = new Label("ExamName");
		dataflexTable.setWidget(0, 0, lblExamname);
		
		dataflexTable.setWidget(0, 1, examNametextBox);
		examNametextBox.setWidth("195px");
		
		Label lblDescription = new Label("Description");
		dataflexTable.setWidget(1, 0, lblDescription);
		
		dataflexTable.setWidget(1, 1, examDescriptiontextArea);
		
		Label lblCourse = new Label("Course");
		dataflexTable.setWidget(2, 0, lblCourse);
		
		dataflexTable.setWidget(2, 1, coursecomboBox);
		
		Label lblSection = new Label("Section");
		dataflexTable.setWidget(3, 0, lblSection);
		
		dataflexTable.setWidget(3, 1, sectioncomboBox);
		
		Label lblActive = new Label("Active");
		dataflexTable.setWidget(4, 0, lblActive);
		
		
		dataflexTable.setWidget(4, 1, activeCheckBox);
		dataflexTable.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		datacomboxflexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		lblErrormessage.setStyleName("error-Label");
		
		
		datacomboxflexTable.setWidget(2, 0, lblErrormessage);
		lblErrormessage.setHeight("15px");
		
		FlexTable btnflexTable = new FlexTable();
		datacomboxflexTable.setWidget(3, 0, btnflexTable);
		btnflexTable.setSize("226px", "44px");
		
		btnSubmit.setType(ButtonType.SUCCESS);
		btnflexTable.setWidget(0, 0, btnSubmit);
		btnSubmit.setWidth("75px");
		
		btnReset.setType(ButtonType.WARNING);
		btnflexTable.setWidget(0, 1, btnReset);
		btnReset.setWidth("75px");
		btnflexTable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		btnflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		datacomboxflexTable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		datacomboxflexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		datacomboxflexTable.getCellFormatter().setVerticalAlignment(3, 0, HasVerticalAlignment.ALIGN_TOP);
		datacomboxflexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		datacomboxflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainflextable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		
	/*	examNametextBox.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				
				if(examNametextBox.getText().trim().length()!=0)
				{
					if(examnameAndIdhmap.containsKey(examNametextBox.getText().trim()))
					{
						Window.alert("ExamName Already Exist");
						examNametextBox.setText("");
					}
				}
			}
		});*/
		
		examNamescomboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				
				if(examNamescomboBox.getSelectedIndex()!=0)
				{
					getExamDetails();
				}
				else
				{
					resetData();
				}
			}
		} );
		
		coursecomboBox.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				selectedCourseSectionid="";
				if(coursecomboBox.getSelectedIndex()!=0)
				{
					getSectionBasedOnCourseId(coursenameAndIdhmap.get(coursecomboBox.getValue(coursecomboBox.getSelectedIndex())));
					
				}
				else
				{
					sectioncomboBox.clear();
					sectioncomboBox.addItem("Select Section");
				}
			}
		});
		
		btnSubmit.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				validation();
			}
		});
		
		btnReset.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				resetData();
			}
		});
		
		getExamNames();
		getCourses();
		getSections();
	}
	
	public void resetData()
	{
		examNamescomboBox.setSelectedIndex(0);
		examNametextBox.setText("");
		examDescriptiontextArea.setText("");
		coursecomboBox.setSelectedIndex(0);
		sectioncomboBox.setSelectedIndex(0);
		activeCheckBox.setValue(false);
		lblErrormessage.setText("");
	}
	
	public void reset()
	{
		examNametextBox.setText("");
		examDescriptiontextArea.setText("");
		coursecomboBox.setSelectedIndex(0);
		sectioncomboBox.setSelectedIndex(0);
		activeCheckBox.setValue(false);
		lblErrormessage.setText("");
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
				examNamescomboBox.clear();
				examnameAndIdhmap.clear();
				if(response!=null && response.size()!=0 && response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					examNamescomboBox.addItem("New ExamName");
					JSONArray examsarray=response.get("exams").isArray();
					if(examsarray.size()!=0)
					{
						for(int i=0;i<examsarray.size();i++)
						{
							JSONObject examdate=examsarray.get(i).isObject();
							examNamescomboBox.addItem(examdate.get("examname").isString().stringValue());
							examnameAndIdhmap.put(examdate.get("examname").isString().stringValue().toLowerCase(), examdate.get("examid").isString().stringValue());
						}
						examNamescomboBox.setSelectedIndex(0);
					}
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
				sectioncomboBox.clear();
				sectioncomboBox.addItem("Select Section");
				if(responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONObject coursedetails=responseobj.get("coursedata").isObject();
					JSONArray sectionarray=coursedetails.get("sectionid").isArray();
					for(int p=0;p<sectionarray.size();p++)
					{
						String sectionname=getHashMapKey(tempsectionnamesAndIdhmap, sectionarray.get(p).isString().stringValue());
						sectioncomboBox.addItem(sectionname);
						sectionnamesAndIdhmap.put(sectionname.toLowerCase(),sectionarray.get(p).isString().stringValue());
					}
					if(selectedCourseSectionid.length()!=0)
						selcomboBoxValue(sectioncomboBox,getHashMapKey(sectionnamesAndIdhmap,selectedCourseSectionid));
				}
			}
		});
	}
	
	public void validation()
	{
		if(examNametextBox==null || examNametextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please Enter ExamName");
			examNametextBox.setFocus(true);
		}
		else if(examNamescomboBox.getSelectedIndex()==0 && examnameAndIdhmap.containsKey(examNametextBox.getText().trim().toLowerCase()))
		{
			lblErrormessage.setText("Name Already Exist");
			examNametextBox.setFocus(true);
		}
		else if(examNamescomboBox.getSelectedIndex()!=0 && examnameAndIdhmap.containsKey(examNametextBox.getText().trim().toLowerCase()) && !examNametextBox.getText().trim().toLowerCase().equalsIgnoreCase(examNametextBox.getElement().getId().trim()))
		{
			lblErrormessage.setText("Name Already Exist");
			examNametextBox.setFocus(true);
		}
		else if(examDescriptiontextArea==null || examDescriptiontextArea.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please Enter ExamDescription");
			examDescriptiontextArea.setFocus(true);
		}
		else if(coursecomboBox.getSelectedIndex()==0)
		{
			lblErrormessage.setText("Please Select CourseName");
			coursecomboBox.setFocus(true);
		}
		else if(sectioncomboBox.getSelectedIndex()==0)
		{
			lblErrormessage.setText("Please Select SectionName");
			sectioncomboBox.setFocus(true);
		}
		else
		{
			lblErrormessage.setText("");
			JSONObject examdata=new JSONObject();
			
			if(examNamescomboBox.getSelectedIndex()==0)
			{
				examdata.put("type",new JSONString("0"));
			}
			else
			{
				examdata.put("type",new JSONString("1"));
				examdata.put("examid",new JSONString(examnameAndIdhmap.get(examNamescomboBox.getValue(examNamescomboBox.getSelectedIndex()).toLowerCase())));
			}
			
			examdata.put("examname",new JSONString(examNametextBox.getText().trim()));
			examdata.put("desc",new JSONString(examDescriptiontextArea.getText().trim()));
			examdata.put("courseid",new JSONString(coursenameAndIdhmap.get(coursecomboBox.getValue(coursecomboBox.getSelectedIndex()))));
			examdata.put("sectionid",new JSONString(sectionnamesAndIdhmap.get(sectioncomboBox.getValue(sectioncomboBox.getSelectedIndex()))));
			examdata.put("status",new JSONString("0"));
			
			if(activeCheckBox.getValue()==true)
				examdata.put("status",new JSONString("1"));
			
			insertAndUpdationExams(examdata);
			
		}
	}
	
	public void insertAndUpdationExams(JSONObject examdata)
	{
		mainservice.insertAndUpdationExams(examdata.toString(),new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				
				JSONObject response=JSONParser.parseStrict(result).isObject();
				if(response!=null && response.size()!=0 && response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					if(examNamescomboBox.getSelectedIndex()==0)
					{
						Window.alert("Successfully Inseted");
					}
					else
					{
						Window.alert("Successfully Updated");
					}
					resetData();
					getExamNames();
				}
				else
				{
					Window.alert("Failed");
				}
			}
		});
		
	}
	public void getExamDetails()
	{
		String examId=examnameAndIdhmap.get(examNamescomboBox.getValue(examNamescomboBox.getSelectedIndex()).toLowerCase());
		
		mainservice.getExamDetails(examId,new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				
				JSONObject response=JSONParser.parseStrict(result).isObject();
				reset();
				if(response!=null && response.size()!=0 && response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONObject examdata=response.get("examdata").isObject();
					examNametextBox.setText(examdata.get("examname").isString().stringValue());
					examNametextBox.getElement().setId(examdata.get("examname").isString().stringValue());
					examDescriptiontextArea.setText(examdata.get("desc").isString().stringValue());
					
					selcomboBoxValue(coursecomboBox,getHashMapKey(coursenameAndIdhmap,examdata.get("courseid").isString().stringValue()));
					getSectionBasedOnCourseId(examdata.get("courseid").isString().stringValue());
					
					if(sectionnamesAndIdhmap.size()!=0)
					{
						selectedCourseSectionid=examdata.get("sectionid").isString().stringValue();
					}
					
					activeCheckBox.setValue(true);
					if(examdata.get("status").isString().stringValue().equals("0"))
						activeCheckBox.setValue(false);
					
				}
			}
		});
	}
	
	public static  void selcomboBoxValue(ListBox object,String hvalue)
	{
		for(int s=0;s<object.getItemCount();s++)
		{
			if(hvalue.equals(object.getItemText(s)))
			{
				object.setSelectedIndex(s);
			}
		}
	}
	
	public static String getHashMapKey(HashMap<String,String> hmap,String value)
	{
		  for(String s : hmap.keySet()){
		        if(hmap.get(s).equals(value)) return s;
		    }
		 return null;
	}
}
