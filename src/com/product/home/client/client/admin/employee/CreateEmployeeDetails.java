package com.product.home.client.client.admin.employee;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.datepicker.client.ui.DateBoxAppended;
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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.product.home.client.MainService;
import com.product.home.client.MainServiceAsync;
import com.product.home.client.client.admin.AdminBody;

public class CreateEmployeeDetails extends AdminBody {

	MainServiceAsync mainservice=GWT.create(MainService.class);
	TextBox employeenumbertextBox = new TextBox();
	DateBoxAppended joindatedateBox = new DateBoxAppended();
	
	TextBox fristnametextBox = new TextBox();
	TextBox middletextBox = new TextBox();
	TextBox lastnametextBox = new TextBox();
	DateBoxAppended dateofbirthdateBoxAppended = new DateBoxAppended();
	ListBox genderlistbox = new ListBox();
	TextBox qualificationtextBox = new TextBox();
	ListBox employeerolecomboBox = new ListBox();
	ListBox employeesubjectcomboBox = new ListBox();
	ListBox experienceyearscomboBox = new ListBox();
	ListBox experiencemonthcomboBox = new ListBox();
	ListBox bloodgroupcomboBox = new ListBox();
	ListBox maritalcomboBox = new ListBox();
	TextBox fatherorhusbandnametextBox = new TextBox();
	TextBox nationalitytextBox = new TextBox();
	TextBox salarytextBox = new TextBox();
	TextArea address1textArea = new TextArea();
	TextArea address2textArea = new TextArea();
	TextBox citytextBox = new TextBox();
	TextBox statetextBox = new TextBox();
	TextBox pincodetextBox = new TextBox();
	TextBox countrytextBox = new TextBox();
	TextBox phone1textBox = new TextBox();
	TextBox phone2textBox = new TextBox();
	TextBox emailidtextBox = new TextBox();
	TextArea experiencedetailstextArea = new TextArea();
	Button btnSubmit = new Button("Submit");
	
	/**
	 * Designations jsonobject contains designationname and designationid
	 */
	JSONObject designationsjson;
	HashMap<String, String> subjectNameAndIdhmap=new HashMap<String,String>();
	public CreateEmployeeDetails() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setWidth("100%");
		employeesubjectcomboBox.setEnabled(false);
		FlexTable mainflexTable = new FlexTable();
		flexTable.setWidget(0, 0, mainflexTable);
		mainflexTable.setSize("800px", "395px");
		
		FlexTable flexTable1 = new FlexTable();
		flexTable1.setStyleName("none");
		mainflexTable.setWidget(0, 0, flexTable1);
		mainflexTable.getCellFormatter().setStyleName(0, 0, "flextablebg1");
		flexTable1.setSize("700px", "47px");
		
		FlexTable subflexTable1 = new FlexTable();
		flexTable1.setWidget(0, 0, subflexTable1);
		
		HTML lblEmployeeNumber = new HTML("Employee Number <font color='red'>*</font>");
		subflexTable1.setWidget(0, 0, lblEmployeeNumber);
		lblEmployeeNumber.setWidth("131px");
		
		
		subflexTable1.setWidget(0, 1, employeenumbertextBox);
		employeenumbertextBox.setWidth("179px");
		
		FlexTable subflexTable2 = new FlexTable();
		flexTable1.setWidget(0, 1, subflexTable2);
		subflexTable2.setWidth("274px");
		
		Label lblJoiningDate = new Label("Joining Date");
		subflexTable2.setWidget(0, 0, lblJoiningDate);
		
		joindatedateBox.setValue(new Date());
		joindatedateBox.setAutoClose(true);
		joindatedateBox.setReadOnly(true);
		subflexTable2.setWidget(0, 1, joindatedateBox);
		joindatedateBox.setWidth("175px");
		mainflexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		
		FlexTable flexTable_1 = new FlexTable();
		flexTable_1.setStyleName("addboarder");
		mainflexTable.setWidget(1, 0, flexTable_1);
		flexTable_1.setSize("800px", "240px");
		
		Label lblEmployeeDetails = new Label("Employee Details");
		lblEmployeeDetails.setStyleName("subheadingfontcss");
		flexTable_1.setWidget(0, 0, lblEmployeeDetails);
		
		FlexTable flexTable_2 = new FlexTable();
		flexTable_1.setWidget(1, 0, flexTable_2);
		flexTable_2.setSize("750px", "423px");
		
		HTML html = new HTML("First Name <font color='red'>*</font>");
		html.setStyleName("fontstylecss");
		flexTable_2.setWidget(0, 0, html);
		html.setWidth("200px");
		
		HTML htmlMiddleName = new HTML("Middle Name ");
		htmlMiddleName.setStyleName("fontstylecss");
		flexTable_2.setWidget(0, 1, htmlMiddleName);
		htmlMiddleName.setWidth("200px");
		
		HTML htmlLastName = new HTML("Surname<font color='red'>*</font>");
		htmlLastName.setStyleName("fontstylecss");
		flexTable_2.setWidget(0, 2, htmlLastName);
		
		
		fristnametextBox.setText("");
		flexTable_2.setWidget(1, 0, fristnametextBox);
		fristnametextBox.setSize("200px", "20px");
		
		
		
		middletextBox.setText("");
		flexTable_2.setWidget(1, 1, middletextBox);
		middletextBox.setSize("200px", "20px");
		
		
		flexTable_2.setWidget(1, 2, lastnametextBox);
		lastnametextBox.setWidth("200px");
		
		HTML html_3 = new HTML("Date Of Birth ");
		html_3.setStyleName("fontstylecss");
		flexTable_2.setWidget(2, 0, html_3);
		html_3.setWidth("200px");
		
		HTML html_4 = new HTML("Gender <font color='red'>*</font>");
		html_4.setStyleName("fontstylecss");
		flexTable_2.setWidget(2, 1, html_4);
		html_4.setWidth("200px");
		
		Label lblQualification = new Label("Qualification");
		lblQualification.setStyleName("fontstylecss");
		flexTable_2.setWidget(2, 2, lblQualification);
		lblQualification.setWidth("200px");
		
		
		dateofbirthdateBoxAppended.setReadOnly(true);
		dateofbirthdateBoxAppended.setAutoClose(true);
		flexTable_2.setWidget(3, 0, dateofbirthdateBoxAppended);
		dateofbirthdateBoxAppended.setWidth("191px");
		
		
		genderlistbox.clear();
		genderlistbox.addItem("Select Gender");
		genderlistbox.addItem("Male");
		genderlistbox.addItem("Female");
		genderlistbox.setSelectedIndex(0);
		flexTable_2.setWidget(3, 1, genderlistbox);
		genderlistbox.setSize("210px", "30px");
		
		
		flexTable_2.setWidget(3, 2, qualificationtextBox);
		qualificationtextBox.setWidth("200px");
		flexTable_2.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(1, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setVerticalAlignment(1, 2, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setVerticalAlignment(2, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setVerticalAlignment(3, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setVerticalAlignment(2, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setVerticalAlignment(3, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(2, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setVerticalAlignment(2, 2, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setVerticalAlignment(3, 2, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(3, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setVerticalAlignment(4, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(4, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		HTML lblEmployeeRole_1 = new HTML("Employee Role <font color='red'>*</font>");
		flexTable_2.setWidget(4, 0, lblEmployeeRole_1);
		
		HTML lblEmployeeRole = new HTML("Total Experience<font color='red'>*</font>");
		lblEmployeeRole.setStyleName("fontstylecss");
		flexTable_2.setWidget(4, 1, lblEmployeeRole);
		lblEmployeeRole.setWidth("200px");
		flexTable_2.getCellFormatter().setHorizontalAlignment(5, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		Label label_1 = new Label("Blood Group");
		label_1.setStyleName("fontstylecss");
		flexTable_2.setWidget(4, 2, label_1);
		label_1.setWidth("200px");
		
		FlexTable flexTable_4 = new FlexTable();
		flexTable_2.setWidget(5, 0, flexTable_4);
		flexTable_4.setSize("150px", "30px");
		employeerolecomboBox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				if(employeerolecomboBox.getSelectedIndex()==0)
				{
					employeesubjectcomboBox.setSelectedIndex(0);
					employeesubjectcomboBox.setEnabled(false);
				}else if(designationsjson.get(employeerolecomboBox.getItemText(employeerolecomboBox.getSelectedIndex())).isString().stringValue().equalsIgnoreCase("2"))
				{
					employeesubjectcomboBox.setSelectedIndex(0);
					employeesubjectcomboBox.setEnabled(true);
				}else
				{
					employeesubjectcomboBox.setSelectedIndex(0);
					employeesubjectcomboBox.setEnabled(false);
				}
			}
		});
		
		
		
		flexTable_4.setWidget(0, 0, employeerolecomboBox);
		employeerolecomboBox.setWidth("120px");
		
		
		
		flexTable_4.setWidget(0, 1, employeesubjectcomboBox);
		employeesubjectcomboBox.setWidth("120px");
		
		FlexTable flexTable_3 = new FlexTable();
		flexTable_2.setWidget(5, 1, flexTable_3);
		flexTable_3.setSize("193px", "30px");
		
		experienceyearscomboBox.clear();
		experienceyearscomboBox.addItem("Year");
		for(int i=0;i<=20;i++)
		{
			experienceyearscomboBox.addItem(""+i);
		}
		
		flexTable_3.setWidget(0, 0, experienceyearscomboBox);
		experienceyearscomboBox.setWidth("78px");
		
		
		experiencemonthcomboBox.clear();
		experiencemonthcomboBox.addItem("Month");
		for(int i=0;i<=12;i++)
		{
			experiencemonthcomboBox.addItem(""+i);
		}
		
		flexTable_3.setWidget(0, 1, experiencemonthcomboBox);
		experiencemonthcomboBox.setWidth("67px");
		flexTable_3.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_3.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(4, 2, HasHorizontalAlignment.ALIGN_LEFT);
		
		
		bloodgroupcomboBox.clear();
		bloodgroupcomboBox.addItem("Select BloodGroup");
		bloodgroupcomboBox.addItem("A+");
		bloodgroupcomboBox.addItem("A-");
		bloodgroupcomboBox.addItem("B+");
		bloodgroupcomboBox.addItem("B-");
		bloodgroupcomboBox.addItem("O+");
		bloodgroupcomboBox.addItem("O-");
		bloodgroupcomboBox.addItem("AB+");
		bloodgroupcomboBox.addItem("AB-");
		
		flexTable_2.setWidget(5, 2, bloodgroupcomboBox);
		bloodgroupcomboBox.setWidth("210px");
		flexTable_2.getCellFormatter().setHorizontalAlignment(5, 2, HasHorizontalAlignment.ALIGN_LEFT);
		
		Label lblMaritalStatus = new Label("Marital Status");
		flexTable_2.setWidget(6, 0, lblMaritalStatus);
		flexTable_2.getCellFormatter().setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_LEFT);
		
		Label lblFatherName = new Label("Father Name\\Husband Name");
		flexTable_2.setWidget(6, 1, lblFatherName);
		
		Label lblNationality = new Label("Nationality");
		flexTable_2.setWidget(6, 2, lblNationality);
		
	
		
		maritalcomboBox.clear();
		maritalcomboBox.addItem("Single");
		maritalcomboBox.addItem("Married");
		
		flexTable_2.setWidget(7, 0, maritalcomboBox);
		maritalcomboBox.setWidth("158px");
		flexTable_2.getCellFormatter().setVerticalAlignment(7, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(7, 0, HasHorizontalAlignment.ALIGN_LEFT);
		
		
		
		flexTable_2.setWidget(7, 1, fatherorhusbandnametextBox);
		flexTable_2.getCellFormatter().setVerticalAlignment(6, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(6, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setVerticalAlignment(7, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(7, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setVerticalAlignment(6, 2, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(6, 2, HasHorizontalAlignment.ALIGN_LEFT);
		
		
		nationalitytextBox.setText("Indian");
		flexTable_2.setWidget(7, 2, nationalitytextBox);
		nationalitytextBox.setWidth("200px");
		flexTable_2.getCellFormatter().setVerticalAlignment(7, 2, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(7, 2, HasHorizontalAlignment.ALIGN_LEFT);
		
		Label lblSalary = new Label("Salary");
		flexTable_2.setWidget(8, 0, lblSalary);
		flexTable_2.getCellFormatter().setHorizontalAlignment(8, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setVerticalAlignment(8, 0, HasVerticalAlignment.ALIGN_TOP);
		
		Label lblExperienceDetail = new Label("Experience Detail ");
		flexTable_2.setWidget(8, 1, lblExperienceDetail);
		
		
		flexTable_2.setWidget(9, 0, salarytextBox);
		flexTable_2.getCellFormatter().setVerticalAlignment(9, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setVerticalAlignment(8, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(8, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		
		flexTable_2.setWidget(9, 1, experiencedetailstextArea);
		experiencedetailstextArea.setSize("243px", "99px");
		flexTable_2.getCellFormatter().setVerticalAlignment(9, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(9, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_1.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_1.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_1.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable_1.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		
		FlexTable flexTable_5 = new FlexTable();
		flexTable_5.setStyleName("addboarder");
		mainflexTable.setWidget(2, 0, flexTable_5);
		flexTable_5.setWidth("800px");
		
		Label label_2 = new Label("Current Place Details");
		label_2.setStyleName("subheadingfontcss");
		flexTable_5.setWidget(0, 0, label_2);
		label_2.setHeight("21px");
		
		FlexTable flexTable_6 = new FlexTable();
		flexTable_5.setWidget(1, 0, flexTable_6);
		flexTable_6.setWidth("750px");
		
		HTML html_1 = new HTML("Address Line1 <font color='red'>*</font>");
		html_1.setStyleName("fontstylecss");
		flexTable_6.setWidget(0, 0, html_1);
		html_1.setWidth("200px");
		
		HTML html_2 = new HTML("Address Line2");
		html_2.setStyleName("fontstylecss");
		flexTable_6.setWidget(0, 1, html_2);
		html_2.setWidth("200px");
		
		HTML html_5 = new HTML("City <font color='red'>*</font>");
		html_5.setStyleName("fontstylecss");
		flexTable_6.setWidget(0, 2, html_5);
		html_5.setWidth("200px");
		
		
		address1textArea.setText("");
		flexTable_6.setWidget(1, 0, address1textArea);
		address1textArea.setSize("200px", "20px");
		
		
		address2textArea.setText("");
		flexTable_6.setWidget(1, 1, address2textArea);
		address2textArea.setSize("200px", "20px");
		
		
		citytextBox.setText("");
		flexTable_6.setWidget(1, 2, citytextBox);
		citytextBox.setSize("200px", "20px");
		
		HTML html_6 = new HTML("State <font color='red'>*</font>");
		html_6.setStyleName("fontstylecss");
		flexTable_6.setWidget(2, 0, html_6);
		html_6.setWidth("200px");
		
		HTML html_7 = new HTML("PinCode ");
		html_7.setStyleName("fontstylecss");
		flexTable_6.setWidget(2, 1, html_7);
		html_7.setWidth("200px");
		
		HTML html_8 = new HTML("Country <font color='red'>*</font>");
		html_8.setStyleName("fontstylecss");
		flexTable_6.setWidget(2, 2, html_8);
		html_8.setWidth("200px");
		
		
		statetextBox.setText("");
		flexTable_6.setWidget(3, 0, statetextBox);
		statetextBox.setSize("200px", "20px");
		
		
		pincodetextBox.setText("");
		flexTable_6.setWidget(3, 1, pincodetextBox);
		pincodetextBox.setSize("200px", "20px");
		
		
		countrytextBox.setText("India");
		flexTable_6.setWidget(3, 2, countrytextBox);
		countrytextBox.setSize("200px", "20px");
		
		HTML html_9 = new HTML("Phone 1 <font color='red'>*</font>");
		html_9.setStyleName("fontstylecss");
		flexTable_6.setWidget(4, 0, html_9);
		html_9.setWidth("200px");
		
		Label label_3 = new Label("Phone 2");
		label_3.setStyleName("fontstylecss");
		flexTable_6.setWidget(4, 1, label_3);
		label_3.setWidth("200px");
		
		HTML html_10 = new HTML("Email");
		html_10.setStyleName("fontstylecss");
		flexTable_6.setWidget(4, 2, html_10);
		html_10.setSize("200px", "14px");
		
		
		phone1textBox.setText("");
		flexTable_6.setWidget(5, 0, phone1textBox);
		phone1textBox.setSize("200px", "20px");
		
		
		phone2textBox.setText("");
		flexTable_6.setWidget(5, 1, phone2textBox);
		phone2textBox.setSize("200px", "20px");
		
		
		emailidtextBox.setText("");
		flexTable_6.setWidget(5, 2, emailidtextBox);
		emailidtextBox.setSize("200px", "20px");
		flexTable_6.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_6.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_6.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_6.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_6.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_6.getCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_6.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_6.getCellFormatter().setVerticalAlignment(1, 2, HasVerticalAlignment.ALIGN_TOP);
		flexTable_6.getCellFormatter().setHorizontalAlignment(1, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_6.getCellFormatter().setVerticalAlignment(2, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_6.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_6.getCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_6.getCellFormatter().setVerticalAlignment(3, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_6.getCellFormatter().setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_6.getCellFormatter().setVerticalAlignment(3, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_6.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_6.getCellFormatter().setHorizontalAlignment(2, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_6.getCellFormatter().setHorizontalAlignment(3, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_6.getCellFormatter().setVerticalAlignment(4, 2, HasVerticalAlignment.ALIGN_TOP);
		flexTable_6.getCellFormatter().setHorizontalAlignment(4, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_6.getCellFormatter().setVerticalAlignment(5, 2, HasVerticalAlignment.ALIGN_TOP);
		flexTable_6.getCellFormatter().setHorizontalAlignment(5, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_6.getCellFormatter().setVerticalAlignment(4, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_6.getCellFormatter().setHorizontalAlignment(4, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_6.getCellFormatter().setVerticalAlignment(5, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_6.getCellFormatter().setHorizontalAlignment(5, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_6.getCellFormatter().setVerticalAlignment(4, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_6.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_6.getCellFormatter().setVerticalAlignment(5, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_6.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_5.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_5.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		
		FlexTable flexTable_7 = new FlexTable();
		flexTable_5.setWidget(2, 0, flexTable_7);
		btnSubmit.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				submitData();
			}
		});
		flexTable_7.setWidget(0, 0, btnSubmit);
		
		btnSubmit.setType(ButtonType.SUCCESS);
		btnSubmit.setWidth("120px");
		flexTable_5.getCellFormatter().setVerticalAlignment(2, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_5.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_5.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable_5.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		mainflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		resetData();
	}
	@Override
	public void reset() {
		
		resetData();
	}
	public void getDesignations()
	{
		mainservice.getDesignation(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				JSONObject resultjson=JSONParser.parseStrict(result).isObject();
				if(resultjson.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					employeerolecomboBox.clear();
					employeerolecomboBox.addItem("Select Role");
					JSONObject datajson=resultjson.get("data").isObject();
					designationsjson=datajson;
					Iterator<String> it=datajson.keySet().iterator();
					while(it.hasNext())
					{
						String designation=it.next();
						employeerolecomboBox.addItem(designation);
					}
				}else
					Window.alert(resultjson.get("status").isString().stringValue());
			}
		});
	}
	
	public void submitData()
	{
		String empno=employeenumbertextBox.getText();
		String joiningDate=com.product.home.shared.DateUtil.convertdates(joindatedateBox.getValue());
		String fname=fristnametextBox.getText();
		String lname=lastnametextBox.getText();
		String dateofbirth=com.product.home.shared.DateUtil.convertdates(dateofbirthdateBoxAppended.getValue());
		if(empno.length()==0)
		{
			Window.alert("Enter Employee Number");
		}else if(joindatedateBox.getValue()==null||joiningDate.length()==0)
		{
			Window.alert("Enter Joining Date");
		}else if(fname.length()==0)
		{
			Window.alert("Enter First name");
		}else if(lname.length()==0)
		{
			Window.alert("Enter Last name");
		}else if(genderlistbox.getSelectedIndex()==0)
		{
			Window.alert("Select Gender");
		}else if(employeerolecomboBox.getSelectedIndex()==0)
		{
			Window.alert("Select Role");
		}else if(experienceyearscomboBox.getSelectedIndex()==0||experiencemonthcomboBox.getSelectedIndex()==0)
		{
			Window.alert("Select Experience");
		}else if(address1textArea==null || address1textArea.getText().trim().length()==0)
		{
			Window.alert("Please enter Addrees Line1");
		}
		else if(citytextBox==null || citytextBox.getText().trim().length()==0)
		{
			Window.alert("Please enter City Name");
		}
		else if(statetextBox==null || statetextBox.getText().trim().length()==0)
		{
			Window.alert("Please enter State Name");
		}
		else if(pincodetextBox.getText().length()!=0&&!pincodetextBox.getText().matches("^[0-9]{1,10}$"))
		{
			Window.alert("Please enter Numbers Only in Current Pincode");
		}
		else if(countrytextBox==null || countrytextBox.getText().trim().length()==0)
		{
			Window.alert("Please enter Country Name");
		}
		else if(phone1textBox==null || phone1textBox.getText().trim().length()==0)
		{
			Window.alert("Please enter Phone Number");
		}
		else if(!phone1textBox.getText().matches("^[0-9]{1,10}$"))
		{
			Window.alert("Please enter Valid Phone Number ");
		}
		else if(emailidtextBox.getText().length()!=0&&!emailidtextBox.getText().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
		{
			Window.alert("Please enter valid Email ID");
		}else
		{
			String designationid=designationsjson.get(employeerolecomboBox.getItemText(employeerolecomboBox.getSelectedIndex())).isString().stringValue();
			boolean check=true;
			if(designationid.equalsIgnoreCase("2"))
			{
				if(employeesubjectcomboBox.getSelectedIndex()==0)
				{
					Window.alert("Select Subject");
					check=false;
				}
			}
			if(check)
			{
				JSONObject employeeinfo=new JSONObject();
				employeeinfo.put("employeeno",new JSONString(empno));
				employeeinfo.put("joiningdate",new JSONString(joiningDate));

				employeeinfo.put("fname",new JSONString(fname));

				if(middletextBox.getText().trim().length()==0)
					employeeinfo.put("mname",new JSONString("-"));
				else
					employeeinfo.put("mname",new JSONString(middletextBox.getText()));

				employeeinfo.put("lname",new JSONString(lastnametextBox.getText()));

				employeeinfo.put("designationid",new JSONString(designationid));
				if(designationid.equalsIgnoreCase("2"))
					employeeinfo.put("subjectid",new JSONString(subjectNameAndIdhmap.get(employeesubjectcomboBox.getItemText(employeesubjectcomboBox.getSelectedIndex()))));
				else
					employeeinfo.put("subjectid", new JSONString("0"));
				employeeinfo.put("dob",new JSONString(dateofbirth.split(" ")[0]));

				if(genderlistbox.getItemText(genderlistbox.getSelectedIndex()).equalsIgnoreCase("female"))
					employeeinfo.put("gender",new JSONString("0"));
				else
					employeeinfo.put("gender",new JSONString("1"));

				if(bloodgroupcomboBox.getSelectedIndex()==0)
					employeeinfo.put("bloodgroup",new JSONString("-"));
				else
					employeeinfo.put("bloodgroup",new JSONString(bloodgroupcomboBox.getItemText(bloodgroupcomboBox.getSelectedIndex())));
				employeeinfo.put("maritalstatus",new JSONString(maritalcomboBox.getItemText(maritalcomboBox.getSelectedIndex())));

				employeeinfo.put("fathername",new JSONString(fatherorhusbandnametextBox.getText().trim()));

				employeeinfo.put("nationality",new JSONString(nationalitytextBox.getText()));
				employeeinfo.put("salary",new JSONString(salarytextBox.getText()));
				employeeinfo.put("expdesc",new JSONString(experiencedetailstextArea.getText()));
				employeeinfo.put("photoid",new JSONString("-"));
				employeeinfo.put("address1",new JSONString(address1textArea.getText().trim()));
				if(address2textArea.getText().trim().length()==0)
					employeeinfo.put("address2",new JSONString("-"));
				else
					employeeinfo.put("address2",new JSONString(address2textArea.getText().trim()));
				employeeinfo.put("city",new JSONString(citytextBox.getText().trim()));
				employeeinfo.put("state",new JSONString(statetextBox.getText().trim()));
				if(pincodetextBox.getText().length()==0)
					employeeinfo.put("pincode",new JSONString("000000"));
				else
					employeeinfo.put("pincode",new JSONString(pincodetextBox.getText().trim()));
				employeeinfo.put("country",new JSONString(countrytextBox.getText().trim()));
				employeeinfo.put("phone1",new JSONString(phone1textBox.getText().trim()));
				if(phone2textBox.getText().trim().length()==0)
					employeeinfo.put("phone2",new JSONString("-"));
				else
					employeeinfo.put("phone2",new JSONString(phone2textBox.getText().trim()));
				if(emailidtextBox.getText().length()==0)
					employeeinfo.put("email",new JSONString("-"));
				else
					employeeinfo.put("email",new JSONString(emailidtextBox.getText().trim()));
				employeeinfo.put("qualification", new JSONString(qualificationtextBox.getText()));
				String exp=experienceyearscomboBox.getItemText(experienceyearscomboBox.getSelectedIndex())+"-"+experiencemonthcomboBox.getItemText(experiencemonthcomboBox.getSelectedIndex());
				employeeinfo.put("experiance", new JSONString(exp));
				employeeinfo.put("employeeid",new JSONString("0"));
				mainservice.addEmployeeData(employeeinfo.toString(),new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
					}
					@Override
					public void onSuccess(String result) {
						JSONObject resultjson=JSONParser.parseStrict(result).isObject();
						Window.alert(resultjson.get("status").isString().stringValue());
					}
				});
			}
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
				subjectNameAndIdhmap.clear();
				employeesubjectcomboBox.clear();
				employeesubjectcomboBox.addItem("Select Subject");
				if(responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray sectiondetailsarry=responseobj.get("subjects").isArray();
					for(int i=0;i<sectiondetailsarry.size();i++)
					{
						JSONObject subjectdata=sectiondetailsarry.get(i).isObject();
						employeesubjectcomboBox.addItem(subjectdata.get("subjectname").isString().stringValue());
						subjectNameAndIdhmap.put(subjectdata.get("subjectname").isString().stringValue(), subjectdata.get("subjectid").isString().stringValue());
					}
				}
			}
		});
	}
	
	public void resetData()
	{
		getDesignations();
		getSubjects();
		
		employeenumbertextBox.setText("");
		fristnametextBox.setText("");
		middletextBox.setText("");
		lastnametextBox.setText("");
		genderlistbox.setSelectedIndex(0);
		qualificationtextBox.setText("");
		employeerolecomboBox.setSelectedIndex(0);
		employeenumbertextBox.setText("");
		employeesubjectcomboBox.setSelectedIndex(0);
		bloodgroupcomboBox.setSelectedIndex(0);
		maritalcomboBox.setSelectedIndex(0);
		fatherorhusbandnametextBox.setText("");
		nationalitytextBox.setText("Indian");
		salarytextBox.setText("");
		experiencedetailstextArea.setText("");
		
		address1textArea.setText("");
		address2textArea.setText("");
		citytextBox.setText("");
		statetextBox.setText("");
		pincodetextBox.setText("");
		countrytextBox.setText("");
		phone1textBox.setText("");
		phone2textBox.setText("");
		emailidtextBox.setText("");

	}

}
