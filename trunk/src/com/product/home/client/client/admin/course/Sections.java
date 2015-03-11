package com.product.home.client.client.admin.course;

import java.util.ArrayList;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.TextBox;
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
import com.product.home.client.MainService;
import com.product.home.client.MainServiceAsync;
import com.product.home.client.client.MultiSelectView;
import com.product.home.client.client.admin.AdminBody;

public class Sections extends AdminBody {

	FlexTable flexTable = new FlexTable();
	FlexTable mainflextable = new FlexTable();
	FlexTable contentflextable = new FlexTable();
	
	TextBox sectionnametextBox = new TextBox();
	TextArea sectiondesctextArea = new TextArea();
	CheckBox sectionstatuscheckBox = new CheckBox();
	JSONObject sectionsjson=new JSONObject();
	Button btnSubmit = new Button("Submit");
	Button btntReset = new Button("Reset");
	ListBox sectioncomboBox = new ListBox();
	MainServiceAsync mainservice=GWT.create(MainService.class);
	private final Label subjectslbl = new Label("Subjects");
	MultiSelectView subjectslist = new MultiSelectView();
	private final Label lblErrormessage = new Label("");
	
	public Sections() {
		getSections();
		getSubjects();
		initWidget(flexTable);
		flexTable.setSize("100%", "100%");
		
		flexTable.setWidget(0, 0, mainflextable);
		mainflextable.setSize("300px", "250px");
		
		mainflextable.setWidget(0, 0, contentflextable);
		contentflextable.setSize("300px", "200px");
		
		contentflextable.setWidget(0, 1, sectioncomboBox);
		sectioncomboBox.setSize("190px", "30px");
		sectioncomboBox.addItem("New Section");
		
		Label label = new Label("Name");
		contentflextable.setWidget(1, 0, label);
		
		sectionnametextBox.getElement().setAttribute("placeholder","Enter SectionName");
		contentflextable.setWidget(1, 1, sectionnametextBox);
		sectionnametextBox.setSize("180px", "20px");
		
		contentflextable.setWidget(2, 0, subjectslbl);
		
		contentflextable.setWidget(2, 1, subjectslist);
		subjectslist.setSize("200px", "100px");
		
		Label label_2 = new Label("Description");
		contentflextable.setWidget(3, 0, label_2);
		
		sectiondesctextArea.getElement().setAttribute("placeholder", "Enter SectionDescription");
		contentflextable.setWidget(3, 1, sectiondesctextArea);
		sectiondesctextArea.setSize("180px", "50px");
		
		Label label_3 = new Label("Status");
		contentflextable.setWidget(4, 0, label_3);
		
		contentflextable.setWidget(4, 1, sectionstatuscheckBox);
		contentflextable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_LEFT);
		contentflextable.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_LEFT);
		contentflextable.getCellFormatter().setHorizontalAlignment(4, 1, HasHorizontalAlignment.ALIGN_LEFT);
		lblErrormessage.setStyleName("error-Label");
		
		mainflextable.setWidget(1, 0, lblErrormessage);
		
		FlexTable btnflextable = new FlexTable();
		mainflextable.setWidget(2, 0, btnflextable);
		btnflextable.setWidth("200px");
		btnSubmit.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				insert_updateSection();
			}
		});
		
		btnSubmit.setType(ButtonType.SUCCESS);
		btnflextable.setWidget(0, 0, btnSubmit);
		
		btntReset.setType(ButtonType.WARNING);
		btnflextable.setWidget(0, 1, btntReset);
		btnflextable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		btnflextable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		mainflextable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainflextable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		mainflextable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainflextable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		
		btntReset.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				reset();
			}
		});
		
		sectioncomboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {

				if(sectioncomboBox.getSelectedIndex()!=0)
				{
					getSectionData(sectionsjson.get(sectioncomboBox.getItemText(sectioncomboBox.getSelectedIndex()).toLowerCase()).isString().stringValue());
				}
				else
				{
					reset();
				}
			}
		});
	}
	
	public void reset()
	{
		sectionnametextBox.setText("");
		sectiondesctextArea.setText("");
		sectionstatuscheckBox.setValue(false);
		sectioncomboBox.setSelectedIndex(0);
		subjectslist.deselectCheckbox();
		lblErrormessage.setText("");
		sectionnametextBox.getElement().setId("");
	}
	
	public void refresh()
	{
		sectioncomboBox.setSelectedIndex(0);
		sectionnametextBox.setText("");
		sectiondesctextArea.setText("");
		sectionstatuscheckBox.setValue(false);
		subjectslist.deselectCheckbox();
		lblErrormessage.setText("");
		sectionnametextBox.getElement().setId("");
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
				sectioncomboBox.clear();
				sectioncomboBox.addItem("New Section");
				JSONObject responsejson=JSONParser.parseStrict(result).isObject();
				if(responsejson.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray sectionsjsonarray=responsejson.get("sections").isArray();
					sectionsjson=new JSONObject();
					JSONObject tempjson;
					for(int p=0;p<sectionsjsonarray.size();p++)
					{
						tempjson=sectionsjsonarray.get(p).isObject();
						String secname=tempjson.get("sectionname").isString().stringValue();
						sectioncomboBox.addItem(secname);
						sectionsjson.put(secname.toLowerCase(), new JSONString(tempjson.get("sectionid").isString().stringValue()));
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
				sectionnametextBox.setText("");
				sectiondesctextArea.setText("");
				sectionstatuscheckBox.setValue(false);
				subjectslist.deselectCheckbox();
				JSONObject responsejson=JSONParser.parseStrict(result).isObject();
				if(responsejson.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONObject sectiondatajson=responsejson.get("sectiondata").isObject();
					sectionnametextBox.setText(sectiondatajson.get("sectionname").isString().stringValue());
					sectionnametextBox.getElement().setId(sectiondatajson.get("sectionname").isString().stringValue());
					sectiondesctextArea.setText(sectiondatajson.get("desc").isString().stringValue());
					JSONArray sectionarray=sectiondatajson.get("subjectid").isArray();
					ArrayList<String> sectionarraylist=new ArrayList<String>();
					for(int p=0;p<sectionarray.size();p++)
					{
						sectionarraylist.add(sectionarray.get(p).isString().stringValue());
					}
					subjectslist.setSelectedCheckboxes(sectionarraylist);
					if(sectiondatajson.get("status").isString().stringValue().equalsIgnoreCase("1"))
						sectionstatuscheckBox.setValue(true);
					else
						sectionstatuscheckBox.setValue(false);
				}
			}
		});
	}
	public void insert_updateSection()
	{
		JSONArray subjectsarray=subjectslist.getSelectedCheckboxes("subjectname", "subjectid");
		if(sectionnametextBox.getText().equalsIgnoreCase(""))
		{
			lblErrormessage.setText("Enter Section Name");
			sectionnametextBox.setFocus(true);
		}
		else if(sectioncomboBox.getSelectedIndex()==0 && sectionsjson.containsKey(sectionnametextBox.getText().trim().toLowerCase()))
		{
			lblErrormessage.setText("Name Alreay Exist");
			sectionnametextBox.setFocus(true);
		}
		else if(sectioncomboBox.getSelectedIndex()!=0 && sectionsjson.containsKey(sectionnametextBox.getText().trim().toLowerCase()) && !sectionnametextBox.getText().trim().toLowerCase().equalsIgnoreCase(sectionnametextBox.getElement().getId()))
		{
			lblErrormessage.setText("Name Alreay Exist");
			sectionnametextBox.setFocus(true);
		}
		else if(subjectsarray.size()==0)
		{
			lblErrormessage.setText("Please Select Subjects");
		} else 
		{
			lblErrormessage.setText("");
			JSONObject sectiondata=new JSONObject();
			sectiondata.put("sectionname", new JSONString(sectionnametextBox.getText()));
			sectiondata.put("desc", new JSONString(sectiondesctextArea.getText()));
			if(sectionstatuscheckBox.getValue())
				sectiondata.put("status", new JSONString("1"));
			else
				sectiondata.put("status", new JSONString("0"));
			if(sectioncomboBox.getSelectedIndex()!=0)
			{
				sectiondata.put("sectionid", new JSONString(sectionsjson.get(sectioncomboBox.getItemText(sectioncomboBox.getSelectedIndex()).toLowerCase()).isString().stringValue()));
				sectiondata.put("type", new JSONString("0"));
			}else
				sectiondata.put("type", new JSONString("1"));
			sectiondata.put("subjects", subjectsarray);
			mainservice.insert_updateSection(sectiondata.toString(),new AsyncCallback<String>() 
			{
				@Override
				public void onFailure(Throwable caught) {
				}
				@Override
				public void onSuccess(String result) 
				{
					JSONObject responsejson=JSONParser.parseStrict(result).isObject();
					if(responsejson.get("status").isString().stringValue().equalsIgnoreCase("success"))
					{
						Window.alert("success");
						getSections();
						refresh();
					}else
						Window.alert(responsejson.get("status").isString().stringValue());
				}
			});
		}
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
				subjectslist.removeRows();
				if(responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray sectiondetailsarry=responseobj.get("subjects").isArray();
					subjectslist.setCheckboxes(sectiondetailsarry, "subjectname", "subjectid");
				}
			}
		});
	}
}
