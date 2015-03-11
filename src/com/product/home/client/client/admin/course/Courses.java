package com.product.home.client.client.admin.course;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Close;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.NavHeader;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.constants.BackdropType;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.google.gwt.core.client.GWT;
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
import com.product.home.client.MainService;
import com.product.home.client.MainServiceAsync;
import com.product.home.client.client.MultiSelectView;
import com.product.home.client.client.admin.AdminBody;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ChangeEvent;

public class Courses extends AdminBody {

	MainServiceAsync mainservice=GWT.create(MainService.class);
	
	FlexTable flexTable = new FlexTable();
	FlexTable mainflextable = new FlexTable();
	FlexTable contentflextable = new FlexTable();
	
	TextBox coursenametextBox = new TextBox();
	//ListBox sectionnamecomboBox = new ListBox();
	TextArea coursedesctextArea = new TextArea();
	CheckBox coursestatuscheckbox = new CheckBox();
	MultiSelectView sectionslistview=new MultiSelectView();
	
	Button btnSubmit = new Button("Submit");
	Button btnReset = new Button("Reset");
	Button btnAddSession = new Button("Add Section");
	ListBox coursecomboBox = new ListBox();
	HashMap<String,String> coursedetailshmap=new HashMap<String,String>();
	private final Label lblErrormessage = new Label("");
	//HashMap<String,String> sectiondetailshmp=new HashMap<String,String>();
	public Courses() {
		getCourses();
		getSections();
		initWidget(flexTable);
		flexTable.setSize("100%", "100%");
		mainflextable.setStyleName("addboarder");

		flexTable.setWidget(0, 0, mainflextable);
		flexTable.getCellFormatter().setWidth(0, 0, "900px");
		mainflextable.setSize("702px", "275px");

		mainflextable.setWidget(0, 0, contentflextable);
		contentflextable.setSize("300px", "274px");
		coursecomboBox.addChangeHandler(new ChangeHandler() 
		{
			public void onChange(ChangeEvent event) 
			{
				if(coursecomboBox.getSelectedIndex()!=0)
				{
					getCourseDetails(coursedetailshmap.get(coursecomboBox.getItemText(coursecomboBox.getSelectedIndex()).toLowerCase()));
				}
				else
				{
					refresh();
				}
			}
		});

		contentflextable.setWidget(0, 1, coursecomboBox);
		coursecomboBox.setSize("200px", "30px");

		Label lblName = new Label("Name");
		contentflextable.setWidget(1, 0, lblName);

		coursenametextBox.getElement().setAttribute("placeholder","Enter CourseName");
		contentflextable.setWidget(1, 1, coursenametextBox);
		contentflextable.getCellFormatter().setHeight(1, 1, "25px");
		contentflextable.getCellFormatter().setWidth(1, 1, "80px");
		coursenametextBox.setSize("190px", "20px");

		Label lblSession = new Label("Section");
		contentflextable.setWidget(2, 0, lblSession);

		contentflextable.setWidget(2, 1, sectionslistview);
		sectionslistview.setSize("200px", "100px");

		Label lblDescription = new Label("Description");
		contentflextable.setWidget(3, 0, lblDescription);

		coursedesctextArea.getElement().setAttribute("placeholder", "Enter CourseDescription");
		contentflextable.setWidget(3, 1, coursedesctextArea);
		contentflextable.getCellFormatter().setWidth(3, 1, "80px");
		coursedesctextArea.setSize("190px", "50px");

		Label lblStatus = new Label("Status");
		contentflextable.setWidget(4, 0, lblStatus);

		contentflextable.setWidget(4, 1, coursestatuscheckbox);
		contentflextable.getCellFormatter().setWidth(4, 1, "");
		coursestatuscheckbox.setSize("", "");
		contentflextable.getCellFormatter().setHorizontalAlignment(4, 1, HasHorizontalAlignment.ALIGN_LEFT);
		contentflextable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_LEFT);
		contentflextable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT);
		contentflextable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_LEFT);
		contentflextable.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_LEFT);
		contentflextable.getCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_LEFT);
		mainflextable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		btnAddSession.setType(ButtonType.PRIMARY);
		mainflextable.setWidget(0, 1, btnAddSession);
		lblErrormessage.setStyleName("error-Label");
		
		mainflextable.setWidget(1, 0, lblErrormessage);
		lblErrormessage.setHeight("");

		FlexTable btnflextable = new FlexTable();
		mainflextable.setWidget(2, 0, btnflextable);
		btnflextable.setWidth("200px");
		btnSubmit.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				insert_updateCourse();
			}
		});

		btnSubmit.setType(ButtonType.SUCCESS);
		btnflextable.setWidget(0, 0, btnSubmit);

		btnReset.setType(ButtonType.WARNING);
		btnflextable.setWidget(0, 1, btnReset);
		btnflextable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		btnflextable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		mainflextable.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
		mainflextable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		mainflextable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		mainflextable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainflextable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);

		btnReset.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				reset();
			}
		});

		btnAddSession.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				final Modal sectionpopup=new Modal();
				sectionpopup.setWidth(400);
				Close close=new Close();
				sectionpopup.add(close);
				close.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						sectionpopup.hide();
					}
				});
				NavHeader sectionheader=new NavHeader("Create Section");
				sectionpopup.add(sectionheader);
				Sections sectionobj=new Sections();
				sectionpopup.add(sectionobj);
				sectionpopup.setBackdrop(BackdropType.STATIC);
				sectionpopup.show();
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
				coursedetailshmap.clear();
				coursecomboBox.addItem("New CourseName");
				if(responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray coursesarray=responseobj.get("courses").isArray();
					if(coursesarray.size()!=0)
					{
						for(int i=0;i<coursesarray.size();i++)
						{
							JSONObject coursedetails=coursesarray.get(i).isObject();
							coursecomboBox.addItem(coursedetails.get("coursename").isString().stringValue());
							coursedetailshmap.put(coursedetails.get("coursename").isString().stringValue().toLowerCase(), coursedetails.get("courseid").isString().stringValue());
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
	
	/**
	 * 
	 * @param courseId
	 */
	
	public void getCourseDetails(String courseId)
	{
		mainservice.getCourseDetails(courseId,new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String jsonobj) {
				
				JSONValue value=JSONParser.parseStrict(jsonobj);
				JSONObject responseobj=value.isObject();
				coursenametextBox.setText("");
				coursedesctextArea.setText("");
				coursestatuscheckbox.setValue(false);
				sectionslistview.deselectCheckbox();
				if(responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONObject coursedetails=responseobj.get("coursedata").isObject();
					coursenametextBox.setText(coursedetails.get("coursename").isString().stringValue());
					coursenametextBox.getElement().setId(coursedetails.get("coursename").isString().stringValue());
					coursedesctextArea.setText(coursedetails.get("desc").isString().stringValue());
					JSONArray sectionarray=coursedetails.get("sectionid").isArray();
					ArrayList<String> sectionarraylist=new ArrayList<String>();
					for(int p=0;p<sectionarray.size();p++)
					{
						sectionarraylist.add(sectionarray.get(p).isString().stringValue());
					}
					sectionslistview.setSelectedCheckboxes(sectionarraylist);
					if(coursedetails.get("status").isString().stringValue().equals("0"))
					{
						coursestatuscheckbox.setValue(false);
					}
					else if(coursedetails.get("status").isString().stringValue().equals("1"))
					{
						coursestatuscheckbox.setValue(true);
					}
				}
			}
		});
	}
	/**
	 * Insert and updation Course
	 */
	public void insert_updateCourse()
	{
		JSONArray sectionsarray=sectionslistview.getSelectedCheckboxes("sectionname", "sectionid");
		
		if(coursenametextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please Enter Course Name");
			coursenametextBox.setFocus(true);
		}
		else if(coursecomboBox.getSelectedIndex()==0 && coursedetailshmap.containsKey(coursenametextBox.getText().trim().toLowerCase()))
		{
			lblErrormessage.setText("Name Already Exist");
			coursenametextBox.setFocus(true);
		}
		else if(coursecomboBox.getSelectedIndex()!=0 && coursedetailshmap.containsKey(coursenametextBox.getText().trim().toLowerCase()) && !coursenametextBox.getText().trim().toLowerCase().equalsIgnoreCase(coursenametextBox.getElement().getId()))
		{
			lblErrormessage.setText("Name Already Exist");
			coursenametextBox.setFocus(true);
		}
		else if(sectionsarray.size()==0)
		{
			lblErrormessage.setText("Please Select Section Name");
		}
		else if(coursedesctextArea.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please Enter Course Description");
		}
		else
		{
			lblErrormessage.setText("");
			JSONObject coursedetails=new JSONObject();
			
			coursedetails.put("type",new JSONString("0"));
			coursedetails.put("coursename",new JSONString(coursenametextBox.getText()));
			coursedetails.put("sectionid",sectionsarray);
			coursedetails.put("desc",new JSONString(coursedesctextArea.getText()));
			
			if(coursestatuscheckbox.getValue()==true)
			{
				coursedetails.put("status",new JSONString("1"));
			}
			else
			{
				coursedetails.put("status",new JSONString("0"));
			}
			if(coursecomboBox.getSelectedIndex()!=0)
			{
				coursedetails.put("courseid", new JSONString(coursedetailshmap.get(coursecomboBox.getItemText(coursecomboBox.getSelectedIndex()).toLowerCase())));
				coursedetails.put("type",new JSONString("1"));
			}
			mainservice.insert_updateCourse(coursedetails.toString(),new AsyncCallback<String>() {

				@Override
				public void onFailure(Throwable caught) {
					
				}

				@Override
				public void onSuccess(String jsonobj) {
					JSONValue value=JSONParser.parseStrict(jsonobj);
					JSONObject responseobj=value.isObject();
					if(responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
					{
						if(coursecomboBox.getSelectedIndex()==0)
						{
							Window.alert("Successfully Inserted");
						}
						else
						{
							Window.alert("Successfully Updated");
						}
						refresh();
						getCourses();
					}
					else
					{
						Window.alert("Failed");
					}
				}
			});
		}
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
				sectionslistview.removeRows();
				if(responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray sectiondetailsarry=responseobj.get("sections").isArray();
					sectionslistview.setCheckboxes(sectiondetailsarry, "sectionname", "sectionid");
				}
			}
		});
	}
	
	
	/**
	 * reset all Fields
	 * 
	 * Session Name,
	 * Session ListBox,
	 * Session Description,
	 * Session Status,
	 */
	public void reset()
	{
		coursenametextBox.setText("");
		coursecomboBox.setSelectedIndex(0);
		sectionslistview.deselectCheckbox();
		coursedesctextArea.setText("");
		coursestatuscheckbox.setValue(false);
		coursenametextBox.getElement().setId("");
		lblErrormessage.setText("");
	}
	
	public void refresh()
	{
		coursecomboBox.setSelectedIndex(0);
		coursenametextBox.setText("");
		coursecomboBox.setSelectedIndex(0);
		sectionslistview.deselectCheckbox();
		coursedesctextArea.setText("");
		coursestatuscheckbox.setValue(false);
		coursenametextBox.getElement().setId("");
		lblErrormessage.setText("");
	}
}
