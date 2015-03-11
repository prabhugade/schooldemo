package com.product.home.client.client.mailbox;


import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Close;
import com.github.gwtbootstrap.client.ui.Modal;
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
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.product.home.client.Jsonkeys;
import com.product.home.client.MainService;
import com.product.home.client.MainServiceAsync;
import com.google.gwt.user.client.ui.ListBox;

public class InboxData extends Composite {
	
	MainServiceAsync mainservice=GWT.create(MainService.class); 
	FlexTable flexTable = new FlexTable();
	static ListBox typecomboBox = new ListBox();
	FlexTable inboxdataflexTable = new FlexTable();
	
	public JSONArray messagedatatemp=null;
	FlexTable listboxflexTable = new FlexTable();
	Button btnDeletemessage = new Button();
	FlexTable searchflexTable = new FlexTable();
	ScrollPanel scrollPanel=null;
	CheckBox chckbxAll = new CheckBox("All");
	String inboxorsebtbox="1";
	public InboxData() {
		
		initWidget(flexTable);
		flexTable.setSize("", "100%");
		
		flexTable.setWidget(0, 0, searchflexTable);
		searchflexTable.setSize("702px", "60px");
		
		listboxflexTable.setStyleName("addboarder");
		searchflexTable.setWidget(0, 0, listboxflexTable);
		searchflexTable.getCellFormatter().setStyleName(0, 0, "none");
		listboxflexTable.setSize("695px", "32px");
		
		chckbxAll.setValue(false);
		listboxflexTable.setWidget(0, 0, chckbxAll);
		listboxflexTable.getCellFormatter().setWidth(0, 0, "10%");
		
		typecomboBox.clear();
		typecomboBox.addItem("All");
		typecomboBox.addItem("UnRead"); 
		typecomboBox.addItem("Read");
		typecomboBox.setSelectedIndex(0);
		listboxflexTable.setWidget(0, 1, typecomboBox);
		listboxflexTable.getCellFormatter().setWidth(0, 1, "25%");
		typecomboBox.setWidth("99%");
		listboxflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		btnDeletemessage.setVisible(false);
		listboxflexTable.setWidget(0, 2, btnDeletemessage);
		
		btnDeletemessage.setIcon(IconType.TRASH);
		
	    scrollPanel = new ScrollPanel();
		flexTable.setWidget(1, 0, scrollPanel);
		scrollPanel.setSize("700px", "644px");
		
		
		scrollPanel.setWidget(inboxdataflexTable);
		inboxdataflexTable.setSize("100%", "66px");
		flexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		
		typecomboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				
				if(typecomboBox.getValue(typecomboBox.getSelectedIndex()).equalsIgnoreCase("all"))
				{
					getInboxMessagesAndSentMessage("0","1");
				}
				else if(typecomboBox.getValue(typecomboBox.getSelectedIndex()).equalsIgnoreCase("unread"))
				{
					getInboxMessagesAndSentMessage("1","1");
				}
				else if(typecomboBox.getValue(typecomboBox.getSelectedIndex()).equalsIgnoreCase("read"))
				{
					getInboxMessagesAndSentMessage("2","1");
				}
			}
		});
		
		chckbxAll.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				checkAllMessages();
			}
		});
		
		btnDeletemessage.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				deleteMessages();
			}
		});
		
	}
	/**
 	* 
 	* @param showtype 0-all,1-Unread,2-read
 	* @param inboxOrSenttype 1-inbox ,2-sentbox
 	*/
	
	public void getInboxMessagesAndSentMessage(String showtype,String inboxOrSenttype)
	{
		inboxorsebtbox=inboxOrSenttype;

		flexTable.removeAllRows();

		flexTable.setWidget(0, 0, searchflexTable);
		flexTable.setWidget(1, 0, scrollPanel);


		for(int i=0;i<messagedatatemp.size();i++)
		{
			JSONObject dataobject=messagedatatemp.get(i).isObject();

			final FlexTable messageflexTable = new FlexTable();
			messageflexTable.getElement().setId(dataobject.get("isread").isString().stringValue());

			if(showtype.equals("0"))
			{
				if(dataobject.get(Jsonkeys.isread).isString().stringValue().equalsIgnoreCase("0"))
				{
					messageflexTable.setStyleName("bannerbg1");
				}
				else if(dataobject.get("isread").isString().stringValue().equalsIgnoreCase("1"))
				{
					messageflexTable.setStyleName("bannerbg2");
				}

				messageflexTable.setSize("99.9%", "37px");

				final CheckBox checkBox = new CheckBox("");
				checkBox.getElement().setId(dataobject.get(Jsonkeys.messageid).isString().stringValue());
				checkBox.setTitle(dataobject.get(Jsonkeys.isread).isString().stringValue());
				messageflexTable.setWidget(0, 0, checkBox);
				messageflexTable.getCellFormatter().setWidth(0, 0, "5%");

				checkBox.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						if(checkBox.getValue()==true)
						{
							messageflexTable.setStyleName("bannerbg3");
						}
						else
						{
							String readorunread=checkBox.getTitle();
							if(readorunread.equalsIgnoreCase("0"))
							{
								messageflexTable.setStyleName("bannerbg1");
							}
							else if(readorunread.equalsIgnoreCase("1"))
							{
								messageflexTable.setStyleName("bannerbg2");
							}
						}
						checkTrueMessages();
					}
				});


				String name=dataobject.get(Jsonkeys.name).isString().stringValue();
				if(name.length()>15)
				{
					name=dataobject.get(Jsonkeys.name).isString().stringValue().substring(0,15).concat("....");
				}
				final Label lblsendernamevalue = new Label(name);
				lblsendernamevalue.setTitle(dataobject.get(Jsonkeys.name).isString().stringValue());
				lblsendernamevalue.setStyleName("handsymbol");
				lblsendernamevalue.getElement().setId(dataobject.get(Jsonkeys.messageid).isString().stringValue());
				messageflexTable.setWidget(0, 1, lblsendernamevalue);
				messageflexTable.getCellFormatter().setWidth(0, 1, "15%");
				lblsendernamevalue.setWidth("95%");
				messageflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

				lblsendernamevalue.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						messageReading(lblsendernamevalue.getElement().getId());
					}
				});

				String subject=dataobject.get(Jsonkeys.messagesubject).isString().stringValue();
				if(subject.length()>30)
				{
					subject=dataobject.get(Jsonkeys.messagesubject).isString().stringValue().substring(0,30).concat("....");
				}

				Label lblSubjectvalue = new Label(subject);
				lblSubjectvalue.setTitle(dataobject.get(Jsonkeys.name).isString().stringValue());
				lblSubjectvalue.setStyleName("handsymbol");
				lblSubjectvalue.setTitle(dataobject.get(Jsonkeys.messagesubject).isString().stringValue());
				messageflexTable.setWidget(0, 2, lblSubjectvalue);
				messageflexTable.getCellFormatter().setWidth(0, 2, "35%");

				lblSubjectvalue.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						messageReading(lblsendernamevalue.getElement().getId());
					}
				});

				Label lblDatevalue = new Label(dataobject.get(Jsonkeys.messagedate).isString().stringValue());
				messageflexTable.setWidget(0, 3, lblDatevalue);
				messageflexTable.getCellFormatter().setWidth(0, 3, "20%");
				messageflexTable.getCellFormatter().setHorizontalAlignment(0, 3, HasHorizontalAlignment.ALIGN_CENTER);
			}
			else if(showtype.equals("1") && dataobject.get(Jsonkeys.isread).isString().stringValue().equalsIgnoreCase("0"))
			{
				messageflexTable.setSize("99.9%", "37px");
				messageflexTable.setStyleName("bannerbg1");

				final CheckBox checkBox = new CheckBox("");
				checkBox.getElement().setId(dataobject.get(Jsonkeys.messageid).isString().stringValue());
				checkBox.setTitle(dataobject.get(Jsonkeys.isread).isString().stringValue());
				messageflexTable.setWidget(0, 0, checkBox);
				messageflexTable.getCellFormatter().setWidth(0, 0, "5%");

				checkBox.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						if(checkBox.getValue()==true)
						{
							messageflexTable.setStyleName("bannerbg3");
						}
						else
						{
							String readorunread=checkBox.getTitle();
							if(readorunread.equalsIgnoreCase("0"))
							{
								messageflexTable.setStyleName("bannerbg1");
							}
							else if(readorunread.equalsIgnoreCase("1"))
							{
								messageflexTable.setStyleName("bannerbg2");
							}
						}
						checkTrueMessages();
					}
				});

				String name=dataobject.get(Jsonkeys.name).isString().stringValue();
				if(name.length()>15)
				{
					name=dataobject.get(Jsonkeys.name).isString().stringValue().substring(0,15).concat("....");
				}
				final Label lblsendernamevalue = new Label(name);
				lblsendernamevalue.setTitle(dataobject.get(Jsonkeys.name).isString().stringValue());
				lblsendernamevalue.setStyleName("handsymbol");
				lblsendernamevalue.getElement().setId(dataobject.get(Jsonkeys.messageid).isString().stringValue());
				messageflexTable.setWidget(0, 1, lblsendernamevalue);
				messageflexTable.getCellFormatter().setWidth(0, 1, "15%");
				lblsendernamevalue.setWidth("95%");
				messageflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

				lblsendernamevalue.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						messageReading(lblsendernamevalue.getElement().getId());
					}
				});

				String subject=dataobject.get(Jsonkeys.messagesubject).isString().stringValue();
				if(subject.length()>30)
				{
					subject=dataobject.get(Jsonkeys.messagesubject).isString().stringValue().substring(0,30).concat("....");
				}

				Label lblSubjectvalue = new Label(subject);
				lblSubjectvalue.setTitle(dataobject.get(Jsonkeys.name).isString().stringValue());
				lblSubjectvalue.setStyleName("handsymbol");
				lblSubjectvalue.setTitle(dataobject.get(Jsonkeys.messagesubject).isString().stringValue());
				messageflexTable.setWidget(0, 2, lblSubjectvalue);
				messageflexTable.getCellFormatter().setWidth(0, 2, "35%");

				lblSubjectvalue.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						messageReading(lblsendernamevalue.getElement().getId());
					}
				});

				Label lblDatevalue = new Label(dataobject.get(Jsonkeys.messagedate).isString().stringValue());
				messageflexTable.setWidget(0, 3, lblDatevalue);
				messageflexTable.getCellFormatter().setWidth(0, 3, "20%");
				messageflexTable.getCellFormatter().setHorizontalAlignment(0, 3, HasHorizontalAlignment.ALIGN_CENTER);
			}
			else if(showtype.equals("2") && dataobject.get(Jsonkeys.isread).isString().stringValue().equalsIgnoreCase("1"))
			{
				messageflexTable.setSize("99.9%", "37px");
				messageflexTable.setStyleName("bannerbg2");


				final CheckBox checkBox = new CheckBox("");
				checkBox.getElement().setId(dataobject.get(Jsonkeys.messageid).isString().stringValue());
				checkBox.setTitle(dataobject.get(Jsonkeys.isread).isString().stringValue());
				messageflexTable.setWidget(0, 0, checkBox);
				messageflexTable.getCellFormatter().setWidth(0, 0, "5%");

				checkBox.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						if(checkBox.getValue()==true)
						{
							messageflexTable.setStyleName("bannerbg3");
						}
						else
						{
							String readorunread=checkBox.getTitle();
							if(readorunread.equalsIgnoreCase("0"))
							{
								messageflexTable.setStyleName("bannerbg1");
							}
							else if(readorunread.equalsIgnoreCase("1"))
							{
								messageflexTable.setStyleName("bannerbg2");
							}
						}
						checkTrueMessages();
					}
				});

				String name=dataobject.get(Jsonkeys.name).isString().stringValue();
				if(name.length()>15)
				{
					name=dataobject.get(Jsonkeys.name).isString().stringValue().substring(0,15).concat("....");
				}
				final Label lblsendernamevalue = new Label(name);
				lblsendernamevalue.setTitle(dataobject.get(Jsonkeys.name).isString().stringValue());
				lblsendernamevalue.setStyleName("handsymbol");
				lblsendernamevalue.getElement().setId(dataobject.get(Jsonkeys.messageid).isString().stringValue());
				messageflexTable.setWidget(0, 1, lblsendernamevalue);
				messageflexTable.getCellFormatter().setWidth(0, 1, "15%");
				lblsendernamevalue.setWidth("95%");
				messageflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

				lblsendernamevalue.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						messageReading(lblsendernamevalue.getElement().getId());
					}
				});

				String subject=dataobject.get(Jsonkeys.messagesubject).isString().stringValue();
				if(subject.length()>30)
				{
					subject=dataobject.get(Jsonkeys.messagesubject).isString().stringValue().substring(0,30).concat("....");
				}

				Label lblSubjectvalue = new Label(subject);
				lblSubjectvalue.setTitle(dataobject.get(Jsonkeys.name).isString().stringValue());
				lblSubjectvalue.setStyleName("handsymbol");
				lblSubjectvalue.setTitle(dataobject.get(Jsonkeys.messagesubject).isString().stringValue());
				messageflexTable.setWidget(0, 2, lblSubjectvalue);
				messageflexTable.getCellFormatter().setWidth(0, 2, "35%");

				lblSubjectvalue.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						messageReading(lblsendernamevalue.getElement().getId());
					}
				});

				Label lblDatevalue = new Label(dataobject.get(Jsonkeys.messagedate).isString().stringValue());
				messageflexTable.setWidget(0, 3, lblDatevalue);
				messageflexTable.getCellFormatter().setWidth(0, 3, "20%");
				messageflexTable.getCellFormatter().setHorizontalAlignment(0, 3, HasHorizontalAlignment.ALIGN_CENTER);
			}

			inboxdataflexTable.setWidget(i, 0, messageflexTable);
			inboxdataflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		}
	}
	
	public void messageReading(String messageId)
	{
		JSONObject messagedata=new JSONObject();
		messagedata.put(Jsonkeys.messageid,new JSONString(messageId));
		messagedata.put(Jsonkeys.role,new JSONString(Cookies.getCookie("rid")));
		messagedata.put(Jsonkeys.receiverid,new JSONString(Cookies.getCookie("stid")));
		
		mainservice.messageReading(messagedata.toString(),new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				
				JSONObject responsejson=JSONParser.parseStrict(result).isObject();
				if(responsejson.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					showMessage(responsejson.get("data").isObject());
				}
			}
		});
	}
	
	public void showMessage(JSONObject  messagedata)
	{
		final Modal messagedisplay=new Modal();
		messagedisplay.setWidth(550);
		Close close=new Close();
		messagedisplay.add(close);
		close.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				messagedisplay.hide();
			}
		});
		MessageDisplay message=new MessageDisplay();
		messagedisplay.add(message);
		message.htmlMessagebodyvalue.setText("");
		message.lblMessagesubjectvalue.setText("");
		message.htmlMessagebodyvalue.setText(messagedata.get(Jsonkeys.mail_body).isString().stringValue());
		message.lblMessagesubjectvalue.setText(messagedata.get(Jsonkeys.messagesubject).isString().stringValue());
		messagedisplay.show();
	}
	
	public void checkAllMessages()
	{
		if(inboxdataflexTable.getRowCount()!=0)
		{
			for(int i=0;i<inboxdataflexTable.getRowCount();i++)
			{
				FlexTable basetable=(FlexTable)inboxdataflexTable.getWidget(i,0);
				CheckBox checkbox=(CheckBox)basetable.getWidget(0,0);

				if(chckbxAll.getValue()==true)
				{
					checkbox.setValue(true);
					btnDeletemessage.setVisible(true);
					basetable.setStyleName("bannerbg3");
				}
				else
				{
					checkbox.setValue(false);
					btnDeletemessage.setVisible(false);
					String readorunread=checkbox.getTitle();
					if(readorunread.equalsIgnoreCase("0"))
					{
						basetable.setStyleName("bannerbg1");
					}
					else if(readorunread.equalsIgnoreCase("1"))
					{
						basetable.setStyleName("bannerbg2");
					}
				}
			}
		}
	}
	
	public void checkTrueMessages()
	{
		if(inboxdataflexTable.getRowCount()!=0)
		{
			int j=0;
			for(int i=0;i<inboxdataflexTable.getRowCount();i++)
			{
				FlexTable basetable=(FlexTable)inboxdataflexTable.getWidget(i,0);
				CheckBox checkbox=(CheckBox)basetable.getWidget(0,0);

				if(checkbox.getValue()==true)
				{
					j++;
				}
			}
			if(j>0)
			{
				btnDeletemessage.setVisible(true);
			}
			else
			{
				btnDeletemessage.setVisible(false);
			}
		}
	}
	
	public void deleteMessages()
	{
		JSONArray array=new JSONArray();
		if(inboxdataflexTable.getRowCount()!=0)
		{
			for(int i=0;i<inboxdataflexTable.getRowCount();i++)
			{
				FlexTable basetable=(FlexTable)inboxdataflexTable.getWidget(i,0);
				CheckBox checkbox=(CheckBox)basetable.getWidget(0,0);
				if(checkbox.getValue()==true)
				{
					array.set(i, new JSONString(checkbox.getElement().getId()));
				}
			}
			if(array.size()!=0)
			{
				JSONObject data=new JSONObject();
				data.put(Jsonkeys.placeholderid,new JSONString(inboxorsebtbox));
				data.put(Jsonkeys.messageid,array);
				data.put(Jsonkeys.receiverid,new JSONString(Cookies.getCookie("stid")));
				data.put(Jsonkeys.role,new JSONString(Cookies.getCookie("rid")));
				mainservice.deleteMessages(data.toString(),new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(String result) {
						
						JSONObject responsejson=JSONParser.parseStrict(result).isObject();
						if(responsejson.get("status").isString().stringValue().equalsIgnoreCase("success"))
						{
							MailBoxScreen.inboxMessageSentMessage(inboxorsebtbox);
						}
					}
				});
			}
		}
	}
	
}
