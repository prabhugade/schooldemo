package com.product.home.client.client.admin.student.marks;

import java.util.HashMap;

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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.product.home.client.MainService;
import com.product.home.client.MainServiceAsync;
import com.product.home.client.client.admin.AdminBody;
public class EnterMarksScreen extends AdminBody {
	
	MainServiceAsync mainservice=GWT.create(MainService.class);
	
	ListBox coursecomboBox = new ListBox();
	ListBox sectioncomboBox = new ListBox();
	ListBox subjectscomboBox = new ListBox();
	ListBox examscomboBox = new ListBox();
	
	Button btnSubmit = new Button("Submit");
	Button btnReset = new Button("Reset");
	
	HashMap<String,String> coursenameAndIdhmap=new HashMap<String, String>();
	
	HashMap<String,String> tempsectionnamesAndIdhmap=new HashMap<String, String>();
	HashMap<String,String> sectionnamesAndIdhmap=new HashMap<String, String>();
	
	HashMap<String,String> examnameAndIdhmap=new HashMap<String, String>();
	HashMap<String,String> tempsubjectnameAndIdhmap=new HashMap<String, String>();
	
	HashMap<String,String> subjectnameAndIdhmap=new HashMap<String, String>();
	
	FlexTable scrolltopflexTable = new FlexTable();
	
	Button search = new Button();
	
	Label lblErrormessage = new Label("");
	private final Label lblNewLabel = new Label("Enter Marks");
	private final FlexTable bannerflexTable = new FlexTable();
	private final Label lblstudent = new Label("StudentName");
	private final Label lblMarks = new Label("Marks");
	public EnterMarksScreen() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setWidth("100%");
		
		FlexTable mainflexTable = new FlexTable();
		flexTable.setWidget(0, 0, mainflexTable);
		mainflexTable.setSize("100%", "461px");
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		lblNewLabel.setStyleName("subheadingfontcss");
		
		mainflexTable.setWidget(0, 0, lblNewLabel);
		lblNewLabel.setWidth("930px");
		
		FlexTable dataflexTable = new FlexTable();
		dataflexTable.setStyleName("addboarder");
		mainflexTable.setWidget(1, 0, dataflexTable);
		dataflexTable.setSize("930px", "481px");
		
		FlexTable comboboxflexTable = new FlexTable();
		dataflexTable.setWidget(0, 0, comboboxflexTable);
		comboboxflexTable.setSize("900px", "47px");
		
		comboboxflexTable.setWidget(0, 0, coursecomboBox);
		coursecomboBox.setWidth("200px");
		
		comboboxflexTable.setWidget(0, 1, sectioncomboBox);
		sectioncomboBox.setWidth("200px");
		
		comboboxflexTable.setWidget(0, 2, subjectscomboBox);
		subjectscomboBox.setWidth("200px");
		
		comboboxflexTable.setWidget(0, 3, examscomboBox);
		
		search.setIcon(IconType.SEARCH);
		
		comboboxflexTable.setWidget(0, 4, search);
		dataflexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		dataflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		
		lblErrormessage.setStyleName("error-Label");
		dataflexTable.setWidget(1, 0, lblErrormessage);
		lblErrormessage.setWidth("691px");
		
		dataflexTable.setWidget(2, 0, bannerflexTable);
		bannerflexTable.setSize("350px", "28px");
		lblstudent.setStyleName("subheadingfontcss");
		
		bannerflexTable.setWidget(0, 0, lblstudent);
		lblMarks.setStyleName("subheadingfontcss");
		
		bannerflexTable.setWidget(0, 1, lblMarks);
		
		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.setStyleName("none");
		dataflexTable.setWidget(3, 0, scrollPanel);
		scrollPanel.setSize("350px", "343px");
		
		
		
		scrollPanel.setWidget(scrolltopflexTable);
		scrolltopflexTable.setSize("99%", "55px");
		scrolltopflexTable.removeAllRows();
		dataflexTable.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);
		dataflexTable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		dataflexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		dataflexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		dataflexTable.getCellFormatter().setVerticalAlignment(3, 0, HasVerticalAlignment.ALIGN_TOP);
		
		
		FlexTable btnflexTable = new FlexTable();
		dataflexTable.setWidget(4, 0, btnflexTable);
		btnflexTable.setSize("260px", "41px");
		
		btnSubmit.setType(ButtonType.SUCCESS);
		btnflexTable.setWidget(0, 0, btnSubmit);
		btnSubmit.setWidth("80px");
		
		btnReset.setType(ButtonType.WARNING);
		btnflexTable.setWidget(0, 1, btnReset);
		btnReset.setWidth("80px");
		btnflexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		btnflexTable.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
		btnflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		btnflexTable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		btnReset.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				resetData();
			}
		});
		
		btnSubmit.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				if(scrolltopflexTable.getRowCount()!=0)
				{
					insertAndUpdateMarks();
				}
				else
				{
					Window.alert("Sorry Student Records Not Available");
				}
			}
		});
		mainflexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainflexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		mainflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		refreshdata();
		
		getCourses();
		getSections();
		getSubjects();
		getExamNames();
		
		coursecomboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				
				scrolltopflexTable.removeAllRows();
				if(coursecomboBox.getSelectedIndex()!=0)
				{
					String courseid=coursenameAndIdhmap.get(coursecomboBox.getValue(coursecomboBox.getSelectedIndex()));
					getSectionBasedOnCourseId(courseid);
				}
				else
				{
					refreshdata();
					
					examscomboBox.setSelectedIndex(0);
				}
			}
		});
		
		sectioncomboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				
				scrolltopflexTable.removeAllRows();
				if(sectioncomboBox.getSelectedIndex()!=0)
				{
					String sectionId=sectionnamesAndIdhmap.get(sectioncomboBox.getValue(sectioncomboBox.getSelectedIndex()));
					getSectionData(sectionId);
				}
				else
				{
					subjectscomboBox.clear();
					subjectscomboBox.addItem("Select Subject");
					
					examscomboBox.setSelectedIndex(0);
				}
			}
		});
		
		search.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				validation();				
			}
		});
		
		subjectscomboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				scrolltopflexTable.removeAllRows();
			}
		});
		
		examscomboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				scrolltopflexTable.removeAllRows();
			}
		});
	}
	
	public void resetData()
	{
		coursecomboBox.setSelectedIndex(0);
		
		refreshdata();
		examscomboBox.setSelectedIndex(0);
		scrolltopflexTable.removeAllRows();
		lblErrormessage.setText("");
	}
	
	public void refreshdata()
	{
		sectioncomboBox.clear();
		sectioncomboBox.addItem("Select Section");
		
		subjectscomboBox.clear();
		subjectscomboBox.addItem("Select Subject");
	}
	
	public void insertAndUpdateMarks()
	{
		JSONArray finalMarksDat=new JSONArray();
		for(int i=0;i<scrolltopflexTable.getRowCount();i++)
		{
			FlexTable marksdataflxtable=(FlexTable)scrolltopflexTable.getWidget(i, 0);
			JSONObject markobject=JSONParser.parseStrict(marksdataflxtable.getElement().getId()).isObject();
			
			markobject.put("subjectid",new JSONString(subjectnameAndIdhmap.get(subjectscomboBox.getValue(subjectscomboBox.getSelectedIndex()))));
			markobject.put("examid",new JSONString(examnameAndIdhmap.get(examscomboBox.getValue(examscomboBox.getSelectedIndex()))));
			
			TextBox markstextbox=(TextBox)marksdataflxtable.getWidget(0, 1);
			if(markstextbox.getText().trim().length()==0)
			{
				markobject.put("marks",new JSONString("0"));
			}
			else
			{
				markobject.put("marks",new JSONString(markstextbox.getText().trim()));
			}
			finalMarksDat.set(i,markobject);
		}
		
		mainservice.insertAndUpdateMarks(finalMarksDat.toString(),new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				
				JSONObject response=JSONParser.parseStrict(result).isObject();
				if(response!=null && response.size()!=0 && response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					Window.alert("Successfully Inserted");
					resetData();
					
				}
			}
		});
	}
	
	public void validation()
	{
		if(coursecomboBox.getItemCount()==0 || coursecomboBox.getSelectedIndex()==0)
		{
			lblErrormessage.setText("Please Select CourseName");
			scrolltopflexTable.removeAllRows();
		}
		else if(sectioncomboBox.getItemCount()==0 || sectioncomboBox.getSelectedIndex()==0)
		{
			lblErrormessage.setText("Please Select SectionName");
			scrolltopflexTable.removeAllRows();
		}
		else if(subjectscomboBox.getItemCount()==0 || subjectscomboBox.getSelectedIndex()==0)
		{
			lblErrormessage.setText("Please Select SubjectName");
			scrolltopflexTable.removeAllRows();
		}
		else if(examscomboBox.getItemCount()==0 || examscomboBox.getSelectedIndex()==0)
		{
			lblErrormessage.setText("Please Select ExamName");
			scrolltopflexTable.removeAllRows();
		}
		else
		{
			lblErrormessage.setText("");
			JSONObject datajson=new JSONObject();
			datajson.put("courseid", new JSONString(coursenameAndIdhmap.get(coursecomboBox.getValue(coursecomboBox.getSelectedIndex()))));
			datajson.put("sectionid", new JSONString(sectionnamesAndIdhmap.get(sectioncomboBox.getValue(sectioncomboBox.getSelectedIndex()))));
			datajson.put("subjectid", new JSONString(subjectnameAndIdhmap.get(subjectscomboBox.getValue(subjectscomboBox.getSelectedIndex()))));
			datajson.put("examid", new JSONString(examnameAndIdhmap.get(examscomboBox.getValue(examscomboBox.getSelectedIndex()))));
			getMarksDetails(datajson);
		}
	}
	
	public void getMarksDetails(JSONObject searchdata)
	{
		mainservice.getMarksDetails(searchdata.toString(),new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {

				JSONObject response=JSONParser.parseStrict(result).isObject();
				scrolltopflexTable.removeAllRows();
				
				if(response!=null && response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					if(response.size()!=0)
					{
						JSONArray marksarray=response.get("exams").isArray();
						showMarksDetails(marksarray);
					}
					else
					{
						Window.alert("No Records");
					}
				}
			}
		});
	}
	
	public void showMarksDetails(JSONArray dataarray)
	{
		for(int i=0;i<dataarray.size();i++)
		{
			JSONObject studentmarksdata=dataarray.get(i).isObject();
			
			String fullname=studentmarksdata.get("lname").isString().stringValue()+" "+studentmarksdata.get("fname").isString().stringValue()+" "+studentmarksdata.get("mname").isString().stringValue();
			
			FlexTable studentdataflexTable = new FlexTable();
			studentdataflexTable.getElement().setId(""+studentmarksdata);
			studentdataflexTable.setStyleName("flextablebg1");
			scrolltopflexTable.setWidget(i, 0, studentdataflexTable);
			studentdataflexTable.setSize("100%", "36px");

			Label studentnamelabel = new Label(fullname);
			studentdataflexTable.setWidget(0, 0, studentnamelabel);
			studentnamelabel.setWidth("221px");

			TextBox markstextBox = new TextBox();
			markstextBox.setText(studentmarksdata.get("marks").isString().stringValue());
			studentdataflexTable.setWidget(0, 1, markstextBox);
			markstextBox.setWidth("89px");
			studentdataflexTable.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_MIDDLE);
			scrolltopflexTable.getCellFormatter().setHorizontalAlignment(i, 0, HasHorizontalAlignment.ALIGN_CENTER);
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
						String sectionname=CreateExamsNamesScreen.getHashMapKey(tempsectionnamesAndIdhmap, sectionarray.get(p).isString().stringValue());
						sectioncomboBox.addItem(sectionname);
						sectionnamesAndIdhmap.put(sectionname,sectionarray.get(p).isString().stringValue());
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
				examscomboBox.clear();
				examnameAndIdhmap.clear();
				if(response!=null && response.size()!=0 && response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					examscomboBox.addItem("Select ExamName");
					JSONArray examsarray=response.get("exams").isArray();
					if(examsarray.size()!=0)
					{
						for(int i=0;i<examsarray.size();i++)
						{
							JSONObject examdate=examsarray.get(i).isObject();
							examscomboBox.addItem(examdate.get("examname").isString().stringValue());
							examnameAndIdhmap.put(examdate.get("examname").isString().stringValue(), examdate.get("examid").isString().stringValue());
						}
						examscomboBox.setSelectedIndex(0);
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
				tempsubjectnameAndIdhmap.clear();
				if(responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray subjectarray=responseobj.get("subjects").isArray();
					for(int i=0;i<subjectarray.size();i++)
					{
						JSONObject subjectdata=subjectarray.get(i).isObject();
						tempsubjectnameAndIdhmap.put(subjectdata.get("subjectname").isString().stringValue(),subjectdata.get("subjectid").isString().stringValue());
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
				subjectscomboBox.clear();
				subjectnameAndIdhmap.clear();
				JSONObject responsejson=JSONParser.parseStrict(result).isObject();
				if(responsejson.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					subjectscomboBox.addItem("Select Subject");
					JSONObject sectiondatajson=responsejson.get("sectiondata").isObject();
					JSONArray subjectarray=sectiondatajson.get("subjectid").isArray();
					for(int p=0;p<subjectarray.size();p++)
					{
						String subjectname=CreateExamsNamesScreen.getHashMapKey(tempsubjectnameAndIdhmap, subjectarray.get(p).isString().stringValue());
						subjectscomboBox.addItem(subjectname);
						subjectnameAndIdhmap.put(subjectname,subjectarray.get(p).isString().stringValue());
					}
				}
			}
		});
	}

	@Override
	public void reset() {
		getCourses();
		getSections();
		getSubjects();
		getExamNames();
	}
}
