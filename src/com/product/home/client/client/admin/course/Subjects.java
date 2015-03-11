package com.product.home.client.client.admin.course;

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
public class Subjects extends AdminBody {

	MainServiceAsync mainservice=GWT.create(MainService.class);
	
	ListBox subjectscomboBox = new ListBox();
	
	Button btnSubmit = new Button("Submit");
	Button btnReset = new Button("Reset");
	
	TextBox subjectnametextBox = new TextBox();
	TextArea subjectdescriptiontextArea = new TextArea();
	Label lblErrormessage = new Label("");
	HashMap<String,String> subjectNameAndIdhmap=new HashMap<String,String>();
	
	CheckBox activecheckBox = new CheckBox("");
	public Subjects() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setWidth("100%");
		
		FlexTable mainflexTable = new FlexTable();
		mainflexTable.setStyleName("addboarder");
		flexTable.setWidget(0, 0, mainflexTable);
		mainflexTable.setSize("694px", "396px");
		
		FlexTable middleflexTable = new FlexTable();
		mainflexTable.setWidget(0, 0, middleflexTable);
		middleflexTable.setSize("294px", "261px");
		
		
		middleflexTable.setWidget(0, 0, subjectscomboBox);
		middleflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		middleflexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		
		FlexTable textboxesflexTable = new FlexTable();
		middleflexTable.setWidget(1, 0, textboxesflexTable);
		textboxesflexTable.setSize("280px", "178px");
		
		Label lblSubjectname = new Label("SubjectName");
		textboxesflexTable.setWidget(0, 0, lblSubjectname);
		
		
		textboxesflexTable.setWidget(0, 1, subjectnametextBox);
		subjectnametextBox.setWidth("153px");
		
		Label lblDescription = new Label("Description");
		textboxesflexTable.setWidget(1, 0, lblDescription);
		
		
		textboxesflexTable.setWidget(1, 1, subjectdescriptiontextArea);
		subjectdescriptiontextArea.setWidth("163px");
		
		Label lblActive = new Label("Active");
		textboxesflexTable.setWidget(2, 0, lblActive);
		
		
		textboxesflexTable.setWidget(2, 1, activecheckBox);
		middleflexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		middleflexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		
		
		lblErrormessage.setStyleName("error-Label");
		middleflexTable.setWidget(2, 0, lblErrormessage);
		
		FlexTable btnflexTable = new FlexTable();
		middleflexTable.setWidget(3, 0, btnflexTable);
		btnflexTable.setSize("227px", "46px");
		
		btnSubmit.setType(ButtonType.SUCCESS);
		btnflexTable.setWidget(0, 0, btnSubmit);
		btnSubmit.setWidth("80px");
		
		btnReset.setType(ButtonType.WARNING);
		btnflexTable.setWidget(0, 1, btnReset);
		btnReset.setWidth("80px");
		btnflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		btnflexTable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		middleflexTable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		middleflexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainflexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		getSubjects();
		
		subjectscomboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {

				if(subjectscomboBox.getSelectedIndex()!=0)
				{
					String subjectname=subjectscomboBox.getValue(subjectscomboBox.getSelectedIndex());
					getSubjectDetails(subjectNameAndIdhmap.get(subjectname.toLowerCase()));
				}
				else
				{
					resetdata();
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
				refresh();
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
				subjectNameAndIdhmap.clear();
				subjectscomboBox.clear();
				subjectscomboBox.addItem("New Subject");
				if(responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray sectiondetailsarry=responseobj.get("subjects").isArray();
					for(int i=0;i<sectiondetailsarry.size();i++)
					{
						JSONObject subjectdata=sectiondetailsarry.get(i).isObject();
						subjectscomboBox.addItem(subjectdata.get("subjectname").isString().stringValue());
						subjectNameAndIdhmap.put(subjectdata.get("subjectname").isString().stringValue().toLowerCase(), subjectdata.get("subjectid").isString().stringValue());
					}
				}
			}
		});
	}
	
	public void validation()
	{
		if(subjectnametextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please Enter Subject Name");
			subjectnametextBox.setFocus(true);
		}
		else if(subjectscomboBox.getSelectedIndex()==0 && subjectNameAndIdhmap.containsKey(subjectnametextBox.getText().trim().toLowerCase()))
		{
			lblErrormessage.setText("Name Already Exist");
			subjectnametextBox.setFocus(true);
		}
		else if(subjectscomboBox.getSelectedIndex()!=0 && subjectNameAndIdhmap.containsKey(subjectnametextBox.getText().trim().toLowerCase()) && !subjectnametextBox.getText().trim().toLowerCase().equalsIgnoreCase(subjectnametextBox.getElement().getId()))
		{
			lblErrormessage.setText("Name Already Exist");
			subjectnametextBox.setFocus(true);
		}
		else if(subjectdescriptiontextArea.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please Enter Subject Description");
		}
		else
		{
			lblErrormessage.setText("");
			JSONObject subjectdata=new JSONObject();
			subjectdata.put("subjectname",new JSONString(subjectnametextBox.getText().trim()));
			subjectdata.put("desc",new JSONString(subjectdescriptiontextArea.getText().trim()));
			
			if(activecheckBox.getValue()==true)
			{
				subjectdata.put("status",new JSONString("1"));
			}
			else
			{
				subjectdata.put("status",new JSONString("0"));
			}
			subjectdata.put("type",new JSONString("1"));
			if(subjectscomboBox.getSelectedIndex()!=0)
			{
				subjectdata.put("type",new JSONString("0"));
				subjectdata.put("subjectid",new JSONString(subjectNameAndIdhmap.get(subjectscomboBox.getValue(subjectscomboBox.getSelectedIndex()).toLowerCase())));
			}
			
			insertAndUpdateSubject(subjectdata);
		}
	}
	
	public void insertAndUpdateSubject(JSONObject subjectdata)
	{
		mainservice.insertAndUpdateSubject(subjectdata.toString(),new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				JSONObject response=JSONParser.parseStrict(result).isObject();
				
				if(response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					if(subjectscomboBox.getSelectedIndex()==0)
					{
						Window.alert("Successfully Inserted");
					}
					else
					{
						Window.alert("Successfully Updated");
					}
					refresh();
					getSubjects();
				}
			}
		});
	}
	
	public void getSubjectDetails(String subectId)
	{
		mainservice.getSubjectDetails(subectId,new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				JSONObject response=JSONParser.parseStrict(result).isObject();
				resetdata();
				if(response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONObject subjectdatajson=response.get("subjectdata").isObject();
					subjectnametextBox.setText("");
					subjectdescriptiontextArea.setText("");
					activecheckBox.setValue(false);
					
					subjectnametextBox.setText(subjectdatajson.get("subjectname").isString().stringValue());
					subjectnametextBox.getElement().setId(subjectdatajson.get("subjectname").isString().stringValue());
					subjectdescriptiontextArea.setText(subjectdatajson.get("desc").isString().stringValue());
					
					if(subjectdatajson.get("status").isString().stringValue().equals("1"))
					{
						activecheckBox.setValue(true);
					}
				}
			}
		});
	}
	
	public void resetdata()
	{
		subjectnametextBox.setText("");
		subjectdescriptiontextArea.setText("");
		activecheckBox.setValue(false);
		lblErrormessage.setText("");
		subjectnametextBox.getElement().setId("");
	}
	public void refresh()
	{
		subjectscomboBox.setSelectedIndex(0);
		subjectnametextBox.setText("");
		subjectdescriptiontextArea.setText("");
		activecheckBox.setValue(false);
		lblErrormessage.setText("");
		subjectnametextBox.getElement().setId("");
	}

	@Override
	public void reset() {
		getSubjects();
		resetdata();
	}
	
}
