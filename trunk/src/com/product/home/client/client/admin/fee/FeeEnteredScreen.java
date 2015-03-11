package com.product.home.client.client.admin.fee;

import java.util.HashMap;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
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
import com.product.home.client.client.admin.student.marks.CreateExamsNamesScreen;

public class FeeEnteredScreen extends AdminBody {
	MainServiceAsync mainservice=GWT.create(MainService.class); 
	
	
	ListBox coursecomboBox = new ListBox();
	ListBox sectioncomboBox = new ListBox();
	
	Button btnSubmit = new Button("Submit");
	Button btnReset = new Button("Reset");
	Button btnSearch = new Button();
	
	FlexTable dataflextableflexTable = new FlexTable();
	
	HashMap<String,String> sectionnameandid=new HashMap<String,String>();
	HashMap<String,String> coursedetailshmap=new HashMap<String,String>();

	public FeeEnteredScreen() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setWidth("100%");
		flexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		
		FlexTable mainflexTable = new FlexTable();
		mainflexTable.setStyleName("addboarder");
		flexTable.setWidget(1, 0, mainflexTable);
		mainflexTable.setSize("800px", "135px");
		
		FlexTable listboxdflexTable = new FlexTable();
		mainflexTable.setWidget(0, 0, listboxdflexTable);
		listboxdflexTable.setSize("502px", "69px");
		
		listboxdflexTable.setWidget(0, 0, coursecomboBox);
		coursecomboBox.setWidth("200px");
		
		listboxdflexTable.setWidget(0, 1, sectioncomboBox);
		sectioncomboBox.setWidth("200px");
		listboxdflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		listboxdflexTable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		mainflexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		
		FlexTable bannerflexTable = new FlexTable();
		bannerflexTable.setStyleName("googlevizivals");
		mainflexTable.setWidget(1, 0, bannerflexTable);
		bannerflexTable.setSize("500px", "31px");
		
		Label lblSectionName = new Label("Section Name");
		bannerflexTable.setWidget(0, 0, lblSectionName);
		
		Label lblSectionFee = new Label("Section Fee");
		bannerflexTable.setWidget(0, 1, lblSectionFee);
		mainflexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		
		ScrollPanel scrollPanel = new ScrollPanel();
		mainflexTable.setWidget(2, 0, scrollPanel);
		scrollPanel.setSize("500px", "339px");
		
		
		scrollPanel.setWidget(dataflextableflexTable);
		dataflextableflexTable.setSize("99%", "48px");
		
		mainflexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainflexTable.getCellFormatter().setVerticalAlignment(2, 0, HasVerticalAlignment.ALIGN_TOP);
		
		FlexTable btnflexTable = new FlexTable();
		mainflexTable.setWidget(3, 0, btnflexTable);
		btnflexTable.setWidth("319px");
		
		btnSubmit.setType(ButtonType.SUCCESS);
		btnflexTable.setWidget(0, 0, btnSubmit);
		btnSubmit.setWidth("80px");
		
		btnReset.setType(ButtonType.WARNING);
		btnflexTable.setWidget(0, 1, btnReset);
		btnReset.setWidth("80px");
		
		sectioncomboBox.addItem("Select Section");
		
		btnSearch.setIcon(IconType.SEARCH);
		listboxdflexTable.setWidget(0, 2, btnSearch);
		mainflexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainflexTable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		coursecomboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				
				if(coursecomboBox.getSelectedIndex()!=0)
				{
					String courseid=coursedetailshmap.get(coursecomboBox.getValue(coursecomboBox.getSelectedIndex()));
					getSectionBasedOnCourseId(courseid);
				}
				else
				{
					sectioncomboBox.clear();
					sectioncomboBox.addItem("Select Section");
				}
			}
		});
		
		btnSearch.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				if(coursecomboBox.getSelectedIndex()!=0)
				{
					if(sectioncomboBox.getSelectedIndex()!=0)
					{
						getFeeDetails();
					}
					else
					{
						Window.alert("Please Select Section");
					}
				}
				else
				{
					Window.alert("Please Select Course");
				}
			}
		});
		
		btnSubmit.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				InsertFeeData();
			}
		});
		
		btnReset.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				refresh();
			}
		});
		
		getSections();
		getCourses();
	}
	
	public void getFeeDetails()
	{
		String courseid=coursedetailshmap.get(coursecomboBox.getValue(coursecomboBox.getSelectedIndex()));
		String sectionid="All";
		if(sectioncomboBox.getSelectedIndex()!=0 && sectioncomboBox.getSelectedIndex()!=1)
			sectionid=sectionnameandid.get(sectioncomboBox.getValue(sectioncomboBox.getSelectedIndex()));
		
		mainservice.getFeeDetails(courseid,sectionid,new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				
				JSONObject response=JSONParser.parseStrict(result).isObject();
				if(response!=null && response.size()!=0)
				{
					if(response.get("status").isString().stringValue().equalsIgnoreCase("success"))
					{
						JSONArray dataarray=response.get("data").isArray();
						getCourseIDAndSectionIdBasedFeeDetails(dataarray);
					}
				}
			}
		});
	}
	
	public void getCourseIDAndSectionIdBasedFeeDetails(JSONArray jsonarray)
	{
		dataflextableflexTable.removeAllRows();
		for(int i=0;i<jsonarray.size();i++)
		{
			JSONObject dataobj=jsonarray.get(i).isObject();
			FlexTable datamapulationflexTable = new FlexTable();
			datamapulationflexTable.setStyleName("googlevizivalss");
			datamapulationflexTable.setSize("100%", "31px");

			Label label = new Label(dataobj.get("sectionname").isString().stringValue());
			label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
			datamapulationflexTable.setWidget(0, 0, label);
			label.setWidth("166px");

			final TextBox feetextBox = new TextBox();
			feetextBox.setText(dataobj.get("fee").isString().stringValue());
			feetextBox.getElement().setId(dataobj.get("sectionid").isString().stringValue());
			datamapulationflexTable.setWidget(0, 1, feetextBox);
			feetextBox.setWidth("140px");

			dataflextableflexTable.setWidget(i, 0, datamapulationflexTable);
			feetextBox.addKeyUpHandler(new KeyUpHandler() {
				
				@Override
				public void onKeyUp(KeyUpEvent event) {
					if(feetextBox.getText().trim().length()!=0 && !feetextBox.getText().trim().matches("^[0-9]{1,50}$"))
					{
						Window.alert("Only 0-9 Numbers Allowed");
						feetextBox.setText("0");
					}
				}
			});
		}
	}
	public void InsertFeeData()
	{
		if(dataflextableflexTable.getRowCount()!=0)
		{
			JSONArray feedataarray=new JSONArray();
			for(int i=0;i<dataflextableflexTable.getRowCount();i++)
			{
				FlexTable dataflextable=(FlexTable)dataflextableflexTable.getWidget(i,0);
				TextBox feetextbox=(TextBox)dataflextable.getWidget(0,1);

				JSONObject feedataobj=new JSONObject();
				feedataobj.put("courseid", new JSONString(coursedetailshmap.get(coursecomboBox.getValue(coursecomboBox.getSelectedIndex()))));
				feedataobj.put("sectionid", new JSONString(feetextbox.getElement().getId()));

				if(feetextbox.getText().trim().length()==0)
				{
					feedataobj.put("fee", new JSONString("0"));
				}
				else
				{
					feedataobj.put("fee", new JSONString(feetextbox.getText()));
				}
				feedataarray.set(i, feedataobj);
			}
			if(feedataarray.size()!=0)
			{
				mainservice.insert_updateFee(feedataarray.toString(),new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
					}
					@Override
					public void onSuccess(String result) {
						JSONObject responsejson=JSONParser.parseStrict(result).isObject();
						if(responsejson.get("status").isString().stringValue().equalsIgnoreCase("success"))
						{
							Window.alert("Successfully Inserted");
						}
						else
						{
							Window.alert("Insert Failed");
						}
					}
				});
			}
		}
		else
		{
			Window.alert("Sorry NO Records");
		}
	}
	
	public void getSections()
	{
		mainservice.getSections(new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
			}
			@Override
			public void onSuccess(String result) 
			{
				sectionnameandid.clear();
				JSONObject responsejson=JSONParser.parseStrict(result).isObject();
				if(responsejson.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray sectionsjsonarray=responsejson.get("sections").isArray();
					JSONObject tempjson;
					for(int p=0;p<sectionsjsonarray.size();p++)
					{
						tempjson=sectionsjsonarray.get(p).isObject();
						String secname=tempjson.get("sectionname").isString().stringValue();
						sectionnameandid.put(secname,tempjson.get("sectionid").isString().stringValue());
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
				coursedetailshmap.clear();
				coursecomboBox.addItem("Select CourseName");
				if(responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray coursesarray=responseobj.get("courses").isArray();
					if(coursesarray.size()!=0)
					{
						for(int i=0;i<coursesarray.size();i++)
						{
							JSONObject coursedetails=coursesarray.get(i).isObject();
							coursecomboBox.addItem(coursedetails.get("coursename").isString().stringValue());
							coursedetailshmap.put(coursedetails.get("coursename").isString().stringValue(), coursedetails.get("courseid").isString().stringValue());
						}
						if(coursecomboBox.getItemCount()>0)
						{
							coursecomboBox.setSelectedIndex(0);
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
				sectioncomboBox.addItem("All");
				if(responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONObject coursedetails=responseobj.get("coursedata").isObject();
					JSONArray sectionarray=coursedetails.get("sectionid").isArray();
					for(int p=0;p<sectionarray.size();p++)
					{
						String sectionname=CreateExamsNamesScreen.getHashMapKey(sectionnameandid, sectionarray.get(p).isString().stringValue());
						sectioncomboBox.addItem(sectionname);
					}
				}
			}
		});
	}

	public void refresh()
	{
		dataflextableflexTable.removeAllRows();
		coursecomboBox.setSelectedIndex(0);
		sectioncomboBox.clear();
		sectioncomboBox.addItem("Select Section");
	}
	
	@Override
	public void reset() {
		
		getSections();
		getCourses();		
	}
	
}
