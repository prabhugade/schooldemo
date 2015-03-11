package com.product.home.client.client.mailbox;

import java.util.HashMap;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Close;
import com.github.gwtbootstrap.client.ui.Modal;
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
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.product.home.client.Jsonkeys;
import com.product.home.client.MainService;
import com.product.home.client.MainServiceAsync;
import com.product.home.client.client.admin.AdminBody;
import com.product.home.client.client.admin.student.marks.CreateExamsNamesScreen;
import com.product.home.client.client.mailbox.richtextbox.RichTextToolbar;
import com.product.home.shared.DataUtilities;
public class MailBoxScreen extends AdminBody {

	static MainServiceAsync mainservice=GWT.create(MainService.class); 

	ListBox typecomboBox = new ListBox();
	ListBox classcomboBox = new ListBox();
	ListBox sectioncomboBox = new ListBox();

	Button btnComposemail = new Button("Compose");
	Button btnSent = new Button("Sent");
	Button btnInbox = new Button("Inbox");	
	Button btnSend = new Button("Send");

	CheckBox chckbxStdent = new CheckBox("Student");
	CheckBox chckbxParent = new CheckBox("Parent");

	FlexTable studentlistflexTable = new FlexTable();
	RichTextArea messagebox = new RichTextArea();

	HashMap<String, String> typehashmap=new HashMap<String,String>();

	FlexTable mainflexTable = new FlexTable();
	static FlexTable dataflexTable = new FlexTable();

	HashMap<String,String> sectionnameandid=new HashMap<String,String>();
	HashMap<String,String> coursedetailshmap=new HashMap<String,String>();

	CheckBox selectallchckbx = new CheckBox("");
	private final FlexTable flexTable_2 = new FlexTable();
	private final Label lblSubject = new Label("Subject");
	private final TextBox subjectmailtextBox = new TextBox();

	public MailBoxScreen() {

		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("100%", "100%");

		mainflexTable.setStyleName("none");
		flexTable.setWidget(0, 0, mainflexTable);
		mainflexTable.setSize("890px", "669px");

		FlexTable leftflexTable = new FlexTable();
		leftflexTable.setStyleName("none");
		mainflexTable.setWidget(0, 0, leftflexTable);
		mainflexTable.getCellFormatter().setStyleName(0, 0, "addboarder");
		leftflexTable.setSize("164px", "161px");

		btnComposemail.setType(ButtonType.INFO);
		leftflexTable.setWidget(0, 0, btnComposemail);
		btnComposemail.setWidth("80px");

		btnInbox.setType(ButtonType.INFO);
		leftflexTable.setWidget(1, 0, btnInbox);
		btnInbox.setWidth("80px");


		btnSent.setType(ButtonType.INFO);
		leftflexTable.setWidget(2, 0, btnSent);
		btnSent.setWidth("80px");

		leftflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		leftflexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		leftflexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainflexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		dataflexTable.setStyleName("none");


		mainflexTable.setWidget(0, 1,dataflexTable);
		mainflexTable.getCellFormatter().setStyleName(0, 1, "addboarder");
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		typehashmap.clear();
		typehashmap.put("All","0");
		typehashmap.put("Admin","1");
		typehashmap.put("Teacher","2");
		typehashmap.put("Student/Parent","3");
		typehashmap.put("Student","4");

		btnComposemail.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				btnComposemail.setType(ButtonType.INFO);
				btnInbox.setType(ButtonType.INFO);
				btnSent.setType(ButtonType.INFO);
				mailCcomposer();
			}
		});

		btnInbox.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				btnComposemail.setType(ButtonType.INFO);
				btnInbox.setType(ButtonType.INFO);
				btnSent.setType(ButtonType.INFO);
				InboxData.typecomboBox.setVisible(true);
				inboxMessageSentMessage("1");
			}
		});

		btnSent.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				btnComposemail.setType(ButtonType.INFO);
				btnInbox.setType(ButtonType.INFO);
				btnSent.setType(ButtonType.INFO);
				InboxData.typecomboBox.setVisible(false);
				inboxMessageSentMessage("2");
			}
		});

		classcomboBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				if(classcomboBox.getSelectedIndex()>1)
				{
					getSectionBasedOnCourseId();
				}
				else if(classcomboBox.getSelectedIndex()==1)
				{
					sectioncomboBox.clear();
					studentlistflexTable.removeAllRows();
					sectioncomboBox.addItem("Select Section");
					sectioncomboBox.addItem("All");
				}
				else
				{
					sectioncomboBox.clear();
					sectioncomboBox.addItem("Select Section");
				}
			}
		});

		sectioncomboBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				if(sectioncomboBox.getSelectedIndex()!=0)
				{
					studentsNamesBasedOnCourseidAndSectionId();
				}
				else
				{
					studentlistflexTable.removeAllRows();
				}
			}
		});

		selectallchckbx.setEnabled(false);
		typecomboBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				if(typecomboBox.getSelectedIndex()==0)
				{
					classcomboBox.setVisible(false);
					sectioncomboBox.setVisible(false);
					chckbxParent.setValue(false);
					chckbxStdent.setValue(true);

					chckbxParent.setVisible(false);
					chckbxStdent.setVisible(false);
					btnSend.setVisible(false);
					selectallchckbx.setEnabled(false);
					selectallchckbx.setValue(false);
					studentlistflexTable.removeAllRows();
				}
				else 
				{
					typeComboBox();
				}
			}
		});

		selectallchckbx.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				selectAllAndDeselectcheckbox();
			}
		});

		btnSend.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				sendMessages();
			}
		});



		mailCcomposer();
		resetdata();
	}

	public void typeComboBox()
	{

		subjectmailtextBox.setText("");
		messagebox.setText("");
		if(typehashmap.get(typecomboBox.getValue(typecomboBox.getSelectedIndex())).equalsIgnoreCase("0"))
		{

			classcomboBox.setVisible(false);
			sectioncomboBox.setVisible(false);
			chckbxParent.setValue(false);
			chckbxStdent.setValue(true);

			chckbxParent.setVisible(false);
			chckbxStdent.setVisible(false);
			studentlistflexTable.removeAllRows();
			btnSend.setVisible(true);
			selectallchckbx.setEnabled(false);
			selectallchckbx.setValue(false);

		}
		else if(typehashmap.get(typecomboBox.getValue(typecomboBox.getSelectedIndex())).equalsIgnoreCase("1"))
		{
			//admin
			classcomboBox.setVisible(false);
			sectioncomboBox.setVisible(false);
			chckbxParent.setValue(false);
			chckbxStdent.setValue(true);

			chckbxParent.setVisible(false);
			chckbxStdent.setVisible(false);
			studentlistflexTable.removeAllRows();
			btnSend.setVisible(true);
			selectallchckbx.setEnabled(true);
			selectallchckbx.setValue(false);

			JSONObject dataobj=new JSONObject();
			dataobj.put("type",new JSONString("1"));
			getEmplooyees_Adminsters(dataobj);
		}
		else if(typehashmap.get(typecomboBox.getValue(typecomboBox.getSelectedIndex())).equalsIgnoreCase("2"))
		{
			classcomboBox.setVisible(false);
			sectioncomboBox.setVisible(false);
			chckbxParent.setValue(false);
			chckbxStdent.setValue(true);

			chckbxParent.setVisible(false);
			chckbxStdent.setVisible(false);
			selectallchckbx.setEnabled(true);
			selectallchckbx.setValue(false);
			studentlistflexTable.removeAllRows();
			btnSend.setVisible(true);

			JSONObject dataobj=new JSONObject();
			dataobj.put("type",new JSONString("2"));
			getEmplooyees_Adminsters(dataobj);
		}
		else if(typehashmap.get(typecomboBox.getValue(typecomboBox.getSelectedIndex())).equalsIgnoreCase("3"))
		{
			classcomboBox.setVisible(true);
			sectioncomboBox.setVisible(true);
			chckbxParent.setValue(false);
			chckbxStdent.setValue(true);

			chckbxParent.setVisible(true);
			chckbxStdent.setVisible(true);
			studentlistflexTable.removeAllRows();
			btnSend.setVisible(true);
			selectallchckbx.setEnabled(true);
			selectallchckbx.setValue(false);

			getCourses();
			getSections();
		}
		else if(typehashmap.get(typecomboBox.getValue(typecomboBox.getSelectedIndex())).equalsIgnoreCase("4"))
		{
			classcomboBox.setVisible(true);
			sectioncomboBox.setVisible(true);
			chckbxParent.setValue(false);
			chckbxStdent.setValue(true);

			chckbxParent.setVisible(true);
			chckbxStdent.setVisible(true);
			studentlistflexTable.removeAllRows();
			btnSend.setVisible(true);
			selectallchckbx.setEnabled(true);
			selectallchckbx.setValue(false);
		}
	}

	public void mailCcomposer()
	{
		dataflexTable.removeAllRows();
		dataflexTable.setSize("704px", "644px");

		FlexTable flexTabletop = new FlexTable();
		flexTabletop.setStyleName("none");
		dataflexTable.setWidget(0, 0, flexTabletop);
		flexTabletop.setSize("700px", "87px");

		FlexTable listboxflexTable = new FlexTable();
		flexTabletop.setWidget(0, 0, listboxflexTable);
		listboxflexTable.setSize("674px", "49px");

		typecomboBox.clear();

		if(Cookies.getCookie("rid").equals("1"))
		{
			//admin
			typecomboBox.addItem("Type");
			typecomboBox.addItem("All");
			typecomboBox.addItem("Teacher");
			typecomboBox.addItem("Student/Parent");

			classcomboBox.setVisible(true);
			sectioncomboBox.setVisible(true);
			chckbxParent.setValue(false);
			chckbxStdent.setValue(true);

			chckbxParent.setVisible(true);
			chckbxStdent.setVisible(true);
		}
		else if(Cookies.getCookie("rid").equals("2"))
		{
			//Teacher
			typecomboBox.addItem("Type");
			typecomboBox.addItem("Admin");
			typecomboBox.addItem("Student");

			classcomboBox.setVisible(true);
			sectioncomboBox.setVisible(true);
			chckbxParent.setValue(false);
			chckbxStdent.setValue(true);

			chckbxParent.setVisible(false);
			chckbxStdent.setVisible(true);
		}
		else if(Cookies.getCookie("rid").equals("3"))
		{
			//student
			typecomboBox.addItem("Type");
			typecomboBox.addItem("Admin");
			typecomboBox.addItem("Teacher");

			classcomboBox.setVisible(false);
			sectioncomboBox.setVisible(false);
			chckbxParent.setValue(false);
			chckbxStdent.setValue(true);

			chckbxParent.setVisible(false);
			chckbxStdent.setVisible(false);
		}
		else if(Cookies.getCookie("rid").equals("4"))
		{
			//parent
			typecomboBox.addItem("Type");
			typecomboBox.addItem("Admin");

			classcomboBox.setVisible(false);
			sectioncomboBox.setVisible(false);
			chckbxParent.setValue(false);
			chckbxStdent.setValue(false);

			chckbxParent.setVisible(false);
			chckbxStdent.setVisible(false);
		}

		listboxflexTable.setWidget(0, 0, typecomboBox);
		typecomboBox.setWidth("160px");

		listboxflexTable.setWidget(0, 1, classcomboBox);
		classcomboBox.setWidth("160px");

		sectioncomboBox.clear();
		listboxflexTable.setWidget(0, 2, sectioncomboBox);
		sectioncomboBox.setWidth("160px");
		sectioncomboBox.addItem("Select Section");

		listboxflexTable.setWidget(0, 3, chckbxStdent);
		chckbxStdent.setWidth("77px");


		listboxflexTable.setWidget(0, 4, chckbxParent);
		flexTabletop.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		FlexTable AllselectflexTablestudentlist = new FlexTable();
		AllselectflexTablestudentlist.setStyleName("none");
		flexTabletop.setWidget(1, 0, AllselectflexTablestudentlist);
		AllselectflexTablestudentlist.setSize("700px", "215px");

		FlexTable flexTable_3 = new FlexTable();
		AllselectflexTablestudentlist.setWidget(0, 0, flexTable_3);
		flexTable_3.setWidth("45px");


		flexTable_3.setWidget(0, 0, selectallchckbx);

		Label lblNewLabel_1 = new Label("All");
		flexTable_3.setWidget(0, 1, lblNewLabel_1);

		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.setStyleName("addboarder");
		AllselectflexTablestudentlist.setWidget(1, 0, scrollPanel);
		scrollPanel.setSize("400px", "200px");


		scrollPanel.setWidget(studentlistflexTable);
		studentlistflexTable.setSize("99%", "29px");

		AllselectflexTablestudentlist.setWidget(2, 0, flexTable_2);
		flexTable_2.setSize("66px", "41px");

		flexTable_2.setWidget(0, 0, lblSubject);
		lblSubject.setSize("57px", "27px");

		flexTable_2.setWidget(0, 1, subjectmailtextBox);
		subjectmailtextBox.setSize("450px", "25px");

		dataflexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);

		VerticalPanel MessageflexTable = new VerticalPanel();
		MessageflexTable.setStyleName("none");
		dataflexTable.setWidget(1, 0, MessageflexTable);
		MessageflexTable.setSize("700px", "312px");

		RichTextToolbar tootlbar=new RichTextToolbar(messagebox);
		MessageflexTable.add(tootlbar);
		tootlbar.setWidth("691px");
		messagebox.setStyleName("whitebg");
		MessageflexTable.add(messagebox);
		messagebox.setSize("700px", "250px");


		dataflexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);

		FlexTable flexTable_1 = new FlexTable();
		dataflexTable.setWidget(2, 0, flexTable_1);
		flexTable_1.setSize("644px", "49px");

		btnSend.setType(ButtonType.PRIMARY);
		flexTable_1.setWidget(0, 0, btnSend);
		btnSend.setWidth("80px");
		flexTable_1.getCellFormatter().setWidth(0, 0, "");
		flexTable_1.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		dataflexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);

		mainflexTable.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
		mainflexTable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);

		resetdata();
	}

	public void sendMessages()
	{
		if(!typehashmap.get(typecomboBox.getValue(typecomboBox.getSelectedIndex())).equalsIgnoreCase("0"))
		{
			if(subjectmailtextBox.getText().trim().length()==0)
			{
				Window.alert("Please Enter Email Subject");
			}
			else if(messagebox.getText().trim().length()==0)
			{
				Window.alert("Please Enter Message");
			}
			else 
			{
				JSONObject dataobj=new JSONObject();
				dataobj.put(Jsonkeys.mail_senderid,new JSONString(Cookies.getCookie("stid")));
				dataobj.put(Jsonkeys.mail_senderrole,new JSONString(Cookies.getCookie("rid")));
				dataobj.put(Jsonkeys.mail_subject,new JSONString(subjectmailtextBox.getText()));
				dataobj.put(Jsonkeys.mail_body,new JSONString(messagebox.getText()));
				if(typecomboBox.getValue(typecomboBox.getSelectedIndex()).equalsIgnoreCase("All"))
				{
					dataobj.put(Jsonkeys.receiverid, new JSONString("all"));
					storeMails(dataobj);
				}
				else
				{
					setMessage(dataobj);
				}
			}
		}
	}

	private void setMessage(JSONObject dataobj)
	{
		JSONArray selectedUserarray=new JSONArray();
		if(studentlistflexTable.getRowCount()!=0)
		{
			int j=0;
			for(int i=0;i<studentlistflexTable.getRowCount();i++)
			{
				FlexTable basetable=(FlexTable)studentlistflexTable.getWidget(i,0);
				CheckBox checkbox=(CheckBox)basetable.getWidget(0,0);
				if(checkbox.getValue()==true)
				{
					if(typehashmap.get(typecomboBox.getValue(typecomboBox.getSelectedIndex())).equalsIgnoreCase("3"))
					{
						JSONObject studentparentids=JSONParser.parseStrict(checkbox.getElement().getId()).isObject();
						if(chckbxStdent.getValue())
						{
							JSONObject datajosnobj=new JSONObject();
							datajosnobj.put(Jsonkeys.receiverid, new JSONString(studentparentids.get("studentid").isString().stringValue()));
							datajosnobj.put(Jsonkeys.receiverrole, new JSONString("3"));
							selectedUserarray.set(j, datajosnobj);
							j++;
						}
						if(chckbxParent.getValue()==true)
						{
							JSONObject datajosnobj1=new JSONObject();
							datajosnobj1.put(Jsonkeys.receiverid, new JSONString(studentparentids.get("parentid").isString().stringValue()));
							datajosnobj1.put(Jsonkeys.receiverrole, new JSONString("4"));
							selectedUserarray.set(j, datajosnobj1);
							j++;
						}
					}
					else
					{
						JSONObject datajosnobj=new JSONObject();
						datajosnobj.put(Jsonkeys.receiverid, new JSONString(checkbox.getElement().getId()));
						datajosnobj.put(Jsonkeys.receiverrole, new JSONString(typehashmap.get(typecomboBox.getValue(typecomboBox.getSelectedIndex()))));
						selectedUserarray.set(j, datajosnobj);
						j++;
					}
				}
			}
			dataobj.put(Jsonkeys.data,selectedUserarray);

			if(selectedUserarray.size()!=0)
			{
				storeMails(dataobj);
			}
			else
			{
				if(typehashmap.get(typecomboBox.getValue(typecomboBox.getSelectedIndex())).equalsIgnoreCase("1"))
				{
					Window.alert("Please Select Atleast One Admin Name");
				}
				else if(typehashmap.get(typecomboBox.getValue(typecomboBox.getSelectedIndex())).equalsIgnoreCase("2"))
				{
					Window.alert("Please Select Atleast One Teacher Name");
				}
				else if(typehashmap.get(typecomboBox.getValue(typecomboBox.getSelectedIndex())).equalsIgnoreCase("3"))
				{
					Window.alert("Please Select Atleast One Student/Parent Name");
				}
				else if(typehashmap.get(typecomboBox.getValue(typecomboBox.getSelectedIndex())).equalsIgnoreCase("4"))
				{
					Window.alert("Please Select Atleast One Student Name");
				}
			}
		}
	}

	public void storeMails(JSONObject messagedata)
	{
		mainservice.storeMails(messagedata.toString(),new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}
			@Override
			public void onSuccess(String result) {

				JSONObject responsejson=JSONParser.parseStrict(result).isObject();
				if(responsejson.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					Window.alert("Successfully Sent ....");
					afterResetData();
				}
			}
		});
	}

	public void afterResetData()
	{
		typeComboBox();
	}

	public void showStudentlistAndTeacherlist(JSONArray dataarray)
	{
		studentlistflexTable.removeAllRows();

		for(int i=0;i<dataarray.size();i++)
		{
			JSONObject dataobj=dataarray.get(i).isObject();

			FlexTable datatable=new FlexTable();
			datatable.setWidth("99%");

			CheckBox studentidchckbox = new CheckBox("");
			Label studentnamevalue = new Label();

			if(typehashmap.get(typecomboBox.getValue(typecomboBox.getSelectedIndex())).equalsIgnoreCase("1"))
			{
				//admin login
				studentidchckbox.getElement().setId(dataobj.get("id").isString().stringValue());
				studentnamevalue.setText(dataobj.get("name").isString().stringValue());

			}
			else if(typehashmap.get(typecomboBox.getValue(typecomboBox.getSelectedIndex())).equalsIgnoreCase("2"))
			{
				//teacher login
				studentidchckbox.getElement().setId(dataobj.get("id").isString().stringValue());
				studentnamevalue.setText(dataobj.get("name").isString().stringValue());

			}
			else if(typehashmap.get(typecomboBox.getValue(typecomboBox.getSelectedIndex())).equalsIgnoreCase("3"))
			{
				//Student/Parent login
				JSONObject studentparentids=new JSONObject();
				studentparentids.put("studentid", new JSONString(dataobj.get("studentid").isString().stringValue()));
				studentparentids.put("parentid", new JSONString(dataobj.get("parentid").isString().stringValue()));
				studentidchckbox.getElement().setId(studentparentids.toString());
				studentnamevalue.setText(dataobj.get("name").isString().stringValue());

			}
			else if(typehashmap.get(typecomboBox.getValue(typecomboBox.getSelectedIndex())).equalsIgnoreCase("4"))
			{
				//Student login
				studentidchckbox.getElement().setId(dataobj.get("studentid").isString().stringValue());
				studentnamevalue.setText(dataobj.get("name").isString().stringValue());
			}

			datatable.setWidget(0, 0, studentidchckbox);
			studentidchckbox.setSize("37px", "22px");
			studentnamevalue.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
			datatable.setWidget(0, 1, studentnamevalue);
			studentnamevalue.setWidth("350px");
			datatable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);

			datatable.setStyleName("googlevizivalss");
			studentlistflexTable.setWidget(i, 0, datatable);
		}
	}

	public void selectAllAndDeselectcheckbox()
	{
		if(studentlistflexTable.getRowCount()!=0)
		{
			for(int i=0;i<studentlistflexTable.getRowCount();i++)
			{
				FlexTable basetable=(FlexTable)studentlistflexTable.getWidget(i,0);
				CheckBox checkbox=(CheckBox)basetable.getWidget(0,0);

				if(selectallchckbx.getValue()==true)
				{
					checkbox.setValue(true);
				}
				else
				{
					checkbox.setValue(false);
				}
			}
		}
	}

	public void resetdata()
	{
		classcomboBox.setVisible(false);
		sectioncomboBox.setVisible(false);
		chckbxParent.setValue(false);
		chckbxStdent.setValue(true);
		studentlistflexTable.removeAllRows();
		chckbxParent.setVisible(false);
		chckbxStdent.setVisible(false);
		btnSend.setVisible(false);

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
				studentlistflexTable.removeAllRows();
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
				classcomboBox.clear();
				coursedetailshmap.clear();
				classcomboBox.addItem("Select Class");
				classcomboBox.addItem("All");
				studentlistflexTable.removeAllRows();
				if(responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray coursesarray=responseobj.get("courses").isArray();
					if(coursesarray.size()!=0)
					{
						for(int i=0;i<coursesarray.size();i++)
						{
							JSONObject coursedetails=coursesarray.get(i).isObject();
							classcomboBox.addItem(coursedetails.get("coursename").isString().stringValue());
							coursedetailshmap.put(coursedetails.get("coursename").isString().stringValue(), coursedetails.get("courseid").isString().stringValue());
						}
						if(classcomboBox.getItemCount()>0)
						{
							classcomboBox.setSelectedIndex(0);
						}
						if(Cookies.getCookie("rid").equals("3"))
						{
							String coursename=DataUtilities.getHashMapKey(coursedetailshmap, Cookies.getCookie("cid"));
							if(coursename!=null)
							{
								DataUtilities.selcomboBoxValue(classcomboBox, coursename);
								classcomboBox.setEnabled(false);
								getSectionBasedOnCourseId();
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
	public void getSectionBasedOnCourseId()
	{
		String courseid=coursedetailshmap.get(classcomboBox.getValue(classcomboBox.getSelectedIndex()));
		mainservice.getCourseDetails(courseid,new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String jsonobj) {

				JSONValue value=JSONParser.parseStrict(jsonobj);
				JSONObject responseobj=value.isObject();
				sectioncomboBox.clear();
				studentlistflexTable.removeAllRows();
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

					if(Cookies.getCookie("rid").equals("3"))
					{
						String sectionname=DataUtilities.getHashMapKey(sectionnameandid, Cookies.getCookie("sid"));
						if(sectionname!=null)
						{
							DataUtilities.selcomboBoxValue(sectioncomboBox, sectionname);
							sectioncomboBox.setEnabled(false);
							studentsNamesBasedOnCourseidAndSectionId();
						}
					}
				}
			}
		});
	}

	public void studentsNamesBasedOnCourseidAndSectionId()
	{
		String courseId=null;
		String sectionId=null;
		if(classcomboBox.getSelectedIndex()==1)
		{
			courseId="0";
			if(sectioncomboBox.getSelectedIndex()==1)
			{
				sectionId="0";
			}
			else
			{
				sectionId=sectionnameandid.get(sectioncomboBox.getValue(sectioncomboBox.getSelectedIndex()));
			}
		}
		else
		{
			if(sectioncomboBox.getSelectedIndex()==1)
			{
				sectionId="0";
			}
			else
			{
				sectionId=sectionnameandid.get(sectioncomboBox.getValue(sectioncomboBox.getSelectedIndex()));
			}
			courseId=coursedetailshmap.get(classcomboBox.getValue(classcomboBox.getSelectedIndex()));
		}

		mainservice.studentsNamesBasedOnCourseidAndSectionId(courseId,sectionId,new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}
			@Override
			public void onSuccess(String result) {

				JSONObject response=JSONParser.parseStrict(result).isObject();
				if(response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray dataarray=response.get("data").isArray();
					showStudentlistAndTeacherlist(dataarray);
				}
			}
		});
	}

	public void  getEmplooyees_Adminsters(JSONObject jsonobj)
	{
		mainservice.getEmplooyees_Adminsters(jsonobj.toString(),new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}
			@Override
			public void onSuccess(String result) {

				JSONObject response=JSONParser.parseStrict(result).isObject();
				if(response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray dataarray=response.get("data").isArray();
					showStudentlistAndTeacherlist(dataarray);
				}
			}
		});
	}

	public static void inboxMessageSentMessage(final String inboxorsenttype)
	{
		dataflexTable.removeAllRows();
		dataflexTable.setSize("704px", "644px");

		JSONObject inboxdatajsonobj=new JSONObject();
		inboxdatajsonobj.put(Jsonkeys.id, new JSONString(Cookies.getCookie("stid")));
		inboxdatajsonobj.put(Jsonkeys.role, new JSONString(Cookies.getCookie("rid")));

		inboxdatajsonobj.put(Jsonkeys.placeholderid, new JSONString(inboxorsenttype));

		mainservice.getMailslist(inboxdatajsonobj.toString(),new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {

				JSONObject responsejson=JSONParser.parseStrict(result).isObject();
				if(responsejson.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					getInboxMessagesAndSentMessage(responsejson.get("data").isArray(),inboxorsenttype);
				}
			}
		});
	}

	public static void getInboxMessagesAndSentMessage(JSONArray messages,String inboxOrsent)
	{
		dataflexTable.removeAllRows();
		dataflexTable.setSize("704px", "644px");
		InboxData inboxdata=new InboxData();
		inboxdata.messagedatatemp=new JSONArray();
		inboxdata.messagedatatemp=messages;
		inboxdata.setSize("700px","644px");
		dataflexTable.setWidget(0, 0,inboxdata);
		inboxdata.getInboxMessagesAndSentMessage("0",inboxOrsent);
	}
	@Override
	public void reset() {
	}
}
