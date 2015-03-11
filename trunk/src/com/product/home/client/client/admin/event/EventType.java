package com.product.home.client.client.admin.event;

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
import com.product.home.client.client.admin.AdminBody;

public class EventType extends AdminBody {

	MainServiceAsync mainservice=GWT.create(MainService.class);
	FlexTable flexTable = new FlexTable();
	FlexTable mainflextable = new FlexTable();
	FlexTable contentflextable = new FlexTable();
	
	TextBox eventnametextBox = new TextBox();
	TextArea eventdesctextArea = new TextArea();
	CheckBox eventstatuscheckBox = new CheckBox();
	JSONObject sectionsjson=new JSONObject();
	Button btnSubmit = new Button("Submit");
	Button btntReset = new Button("Reset");
	ListBox eventscomboBox = new ListBox();
	private final Label lblErrormessage = new Label("");
	
	JSONObject eventTypejson=null;
	public EventType() {
		getEvents();
		initWidget(flexTable);
		flexTable.setSize("100%", "100%");
		flexTable.setWidget(0, 0, mainflextable);
		mainflextable.setSize("300px", "250px");
		
		mainflextable.setWidget(0, 0, contentflextable);
		contentflextable.setSize("300px", "200px");
		
		contentflextable.setWidget(0, 1, eventscomboBox);
		eventscomboBox.setSize("190px", "30px");
		eventscomboBox.addItem("Select Event Type");
		
		Label label = new Label("Name");
		contentflextable.setWidget(1, 0, label);
		
		eventnametextBox.getElement().setAttribute("placeholder","Enter Name");
		contentflextable.setWidget(1, 1, eventnametextBox);
		eventnametextBox.setSize("180px", "20px");
		
		Label label_2 = new Label("Description");
		contentflextable.setWidget(2, 0, label_2);
		
		eventdesctextArea.getElement().setAttribute("placeholder", "Enter Description");
		contentflextable.setWidget(2, 1, eventdesctextArea);
		eventdesctextArea.setSize("180px", "50px");
		
		Label label_3 = new Label("Status");
		contentflextable.setWidget(3, 0, label_3);
		
		contentflextable.setWidget(3, 1, eventstatuscheckBox);
		contentflextable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_LEFT);
		contentflextable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_LEFT);
		contentflextable.getCellFormatter().setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		mainflextable.setWidget(1, 0, lblErrormessage);
		
		
		FlexTable btnflextable = new FlexTable();
		mainflextable.setWidget(2, 0, btnflextable);
		btnflextable.setWidth("200px");
		
		btnSubmit.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				insert_updateEvent();
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
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		
		btntReset.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				reset();
			}
		});
		
		eventscomboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {

				if(eventscomboBox.getSelectedIndex()!=0)
				{
					getEventTypeData(eventTypejson.get(eventscomboBox.getItemText(eventscomboBox.getSelectedIndex()).toLowerCase()).isString().stringValue());
				}
				else
				{
					reset();
				}
			}
		});
	}

	protected void insert_updateEvent() 
	{
		if(eventnametextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Enter Event Type Name");
			eventnametextBox.setFocus(true);
		}
		else
			if((eventscomboBox.getSelectedIndex()==0)&&(eventTypejson.containsKey(eventnametextBox.getText().toLowerCase().trim())))
			{
				lblErrormessage.setText("Name Alreay Exist");
				eventnametextBox.setFocus(true);
			}
			else
				if((eventscomboBox.getSelectedIndex()!=0)&&(!eventscomboBox.getItemText(eventscomboBox.getSelectedIndex()).equalsIgnoreCase(eventnametextBox.getText().trim())&&eventTypejson.containsKey(eventnametextBox.getText().toLowerCase().trim())))
				{
					lblErrormessage.setText("Name Alreay Exist");
					eventnametextBox.setFocus(true);
				}
			else 
				if(eventdesctextArea.getText().trim().length()==0)
				{
					lblErrormessage.setText("Enter Event Type Decription");
					eventdesctextArea.setFocus(true);
				}
				else
				{
					JSONObject eventjson=new JSONObject();
					eventjson.put("eventname",new JSONString(eventnametextBox.getText().trim()));
					eventjson.put("eventdesc",new JSONString(eventdesctextArea.getText().trim()));
					if(eventstatuscheckBox.getValue())
						eventjson.put("status",new JSONString("1"));
					else
						eventjson.put("status",new JSONString("0"));
					if(eventscomboBox.getSelectedIndex()==0)
						eventjson.put("eventid",new JSONString("0"));
					else
						eventjson.put("eventid",new JSONString(eventTypejson.get(eventscomboBox.getItemText(eventscomboBox.getSelectedIndex()).toLowerCase()).isString().stringValue()));

					mainservice.insert_updateEventType(eventjson.toString(),new AsyncCallback<String>() 
							{

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onSuccess(String result) {
							JSONObject respObj = JSONParser.parseStrict(result).isObject();
							if(respObj.get("status").isString().stringValue().equalsIgnoreCase("success"))
							{
								Window.alert("success");
								reset();
								getEvents();
							}
						}

							});
				}
	}

	public void reset() {
		// TODO Auto-generated method stub
		eventscomboBox.setSelectedIndex(0);
		eventnametextBox.setText("");
		eventdesctextArea.setText("");
		eventstatuscheckBox.setValue(false);
		lblErrormessage.setText("");
	}
	
	public void getEvents()
	{
		mainservice.getEventTypes(new AsyncCallback<String>()
		{

			@Override
			public void onFailure(Throwable caught)
			{
				
			}

			@Override
			public void onSuccess(String result) 
			{
				eventTypejson=new JSONObject();
				eventscomboBox.clear();
				eventscomboBox.addItem("Select Event Type");
				JSONObject respObj = JSONParser.parseStrict(result.trim()).isObject();
				if(respObj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray respArray = respObj.get("events").isArray();
					for(int i=0;i<respArray.size();i++)
					{
						JSONObject eventObj=respArray.get(i).isObject();
						eventTypejson.put(eventObj.get("eventname").isString().stringValue().toLowerCase(), eventObj.get("eventtypeid"));
						eventscomboBox.addItem(eventObj.get("eventname").isString().stringValue());
					}
				}
			}
			
		});
	}
	
	private void getEventTypeData(String EventTypeId) 
	{
		mainservice.getEventTypeDetails(EventTypeId,new AsyncCallback<String>()
		{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				
				JSONObject respObj = JSONParser.parseStrict(result.trim()).isObject();
				if(respObj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONObject eventdataObj = respObj.get("eventdata").isObject();
					eventnametextBox.setText(eventdataObj.get("eventname").isString().stringValue());
					eventdesctextArea.setText(eventdataObj.get("eventdesc").isString().stringValue());
					if(eventdataObj.get("status").isString().stringValue().equals("0"))
						eventstatuscheckBox.setValue(false);
					else
						eventstatuscheckBox.setValue(true);
				}
				else
				{
					reset();
				}
				
			}
			
		});
	}
}
