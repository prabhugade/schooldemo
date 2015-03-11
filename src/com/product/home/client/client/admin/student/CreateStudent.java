package com.product.home.client.client.admin.student;

import java.util.Date;
import java.util.HashMap;

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
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.product.home.client.Jsonkeys;
import com.product.home.client.MainService;
import com.product.home.client.MainServiceAsync;
import com.product.home.client.client.admin.AdminBody;
import com.product.home.client.client.admin.student.marks.CreateExamsNamesScreen;
import com.product.home.shared.DateUtil;

public class CreateStudent extends AdminBody 
{
	MainServiceAsync mainservice=GWT.create(MainService.class); 
	
	DateBoxAppended admissiondateBox = new DateBoxAppended();
	
	TextBox firstnametextBox = new TextBox();
	TextBox middlenametextBox = new TextBox();
	TextBox lastNametextBox = new TextBox();
	
	ListBox coursecomboBox = new ListBox();
	DateBoxAppended datofbirthdateBox = new DateBoxAppended();
	ListBox gendercomboBox = new ListBox();
	
	ListBox bloodgroupcomboBox = new ListBox();
	TextBox birthplacetextBox = new TextBox();
	TextBox nationalitytextBox = new TextBox();
	
	TextBox languagetextBox = new TextBox();
	TextBox religiontextBox = new TextBox(); 
	ListBox sectioncomboBox = new ListBox();
	TextBox feetextBox = new TextBox();
	
	TextArea addressline1textBox = new TextArea();
	TextArea addressline2textBox = new TextArea();
	TextBox citytextBox = new TextBox();
	
	TextBox statetextBox = new TextBox();
	TextBox pincodetextBox = new TextBox();
	TextBox countrytextBox = new TextBox();
	
	TextBox phone1textBox = new TextBox();
	TextBox phone2textBox = new TextBox();
	TextBox emailtextBox = new TextBox();
	
	//parent details
	TextBox parentfirstnametextBox = new TextBox();
	TextBox parentlastnametextBox = new TextBox();
	ListBox relationlistbox = new ListBox();
	DateBoxAppended parentdateofbirthdateBoxAppended = new DateBoxAppended();
	ListBox parentgenderlistbox = new ListBox();
	TextBox parenteducationtextBox = new TextBox();
	TextBox parentoccupationtextBox = new TextBox();
	TextBox parentincometextBox = new TextBox();
		
	CheckBox chckbxSameAsCurrentDetails = new CheckBox("Same As Current Details");
	
	TextArea parentaddress1textArea = new TextArea();
	TextArea parentaddress2textArea = new TextArea();
	TextBox parentcitytextBox = new TextBox();
	TextBox parentstatetextBox = new TextBox();
	TextBox parentpincodetextBox = new TextBox();
	TextBox parentcountrytextBox = new TextBox();
	TextBox parentphone1textBox = new TextBox();
	TextBox parentphone2textBox = new TextBox();
	TextBox parentemailidtextBox = new TextBox();
	
	
	Button btnParentDetails = new Button("Submit");
	
	Label lblErrormessage = new Label("");
	
	HashMap<String,String> sectionnameandid=new HashMap<String,String>();
	HashMap<String,String> coursedetailshmap=new HashMap<String,String>();
	TextBox admissionnumbertextBox = new TextBox();
	public CreateStudent() 
	{
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setWidth("100%");
		
		FlexTable mainfrlextable = new FlexTable();
		flexTable.setWidget(0, 0, mainfrlextable);
		mainfrlextable.setSize("800px", "");
		
		FlexTable flextable1 = new FlexTable();
		mainfrlextable.setWidget(0, 0, flextable1);
		flextable1.setSize("800px", "65px");
		
		HTML lblFieldsWith = new HTML("Fields with <font color='red'>*</font> are required.");
		lblFieldsWith.setStyleName("fontstylecss");
		flextable1.setWidget(0, 0, lblFieldsWith);
		lblFieldsWith.setWidth("200px");
		flextable1.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		
		FlexTable admissiondateflextableflexTable_1 = new FlexTable();
		admissiondateflextableflexTable_1.setStyleName("none");
		flextable1.setWidget(1, 0, admissiondateflextableflexTable_1);
		flextable1.getCellFormatter().setStyleName(1, 0, "flextablebg1");
		admissiondateflextableflexTable_1.setSize("790px", "80px");
		
		Label lblNewLabel = new Label("Adminission No.");
		lblNewLabel.setStyleName("fontstylecss");
		admissiondateflextableflexTable_1.setWidget(0, 0, lblNewLabel);
		lblNewLabel.setSize("120px", "28px");
		admissionnumbertextBox.setReadOnly(true);
		
		
		admissiondateflextableflexTable_1.setWidget(0, 1, admissionnumbertextBox);
		admissionnumbertextBox.setWidth("180px");
		
		HTML lblAdmissionDate = new HTML("Admission Date<font color='red'>*</font>");
		lblAdmissionDate.setStyleName("fontstylecss");
		admissiondateflextableflexTable_1.setWidget(0, 2, lblAdmissionDate);
		lblAdmissionDate.setWidth("132px");
		
		admissiondateBox.setValue(new Date());
		admissiondateBox.setAutoClose(true);
		admissiondateBox.setReadOnly(true);
		admissiondateBox.setSize("", "20px");
		admissiondateflextableflexTable_1.setWidget(0, 3, admissiondateBox);
		admissiondateflextableflexTable_1.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		admissiondateflextableflexTable_1.getCellFormatter().setHorizontalAlignment(0, 3, HasHorizontalAlignment.ALIGN_LEFT);
		admissiondateflextableflexTable_1.getCellFormatter().setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_MIDDLE);
		admissiondateflextableflexTable_1.getCellFormatter().setVerticalAlignment(0, 3, HasVerticalAlignment.ALIGN_MIDDLE);
		admissiondateflextableflexTable_1.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		admissiondateflextableflexTable_1.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		admissiondateflextableflexTable_1.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		admissiondateflextableflexTable_1.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		flextable1.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flextable1.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		mainfrlextable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		
		FlexTable flexTable2 = new FlexTable();
		flexTable2.setStyleName("addboarder");
		mainfrlextable.setWidget(1, 0, flexTable2);
		flexTable2.setSize("800px", "289px");
		
		Label lblPersonalDetails = new Label("Student Details");
		lblPersonalDetails.setStyleName("subheadingfontcss");
		flexTable2.setWidget(0, 0, lblPersonalDetails);
		flexTable2.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		
		FlexTable persionaldetailsflextable = new FlexTable();
		flexTable2.setWidget(1, 0, persionaldetailsflextable);
		persionaldetailsflextable.setSize("750px", "258px");
		
		HTML lblFirstName = new HTML("First Name <font color='red'>*</font>");
		lblFirstName.setStyleName("fontstylecss");
		persionaldetailsflextable.setWidget(0, 0, lblFirstName);
		lblFirstName.setWidth("200px");
		
		Label lblMiddleName = new Label("Middle Name");
		lblMiddleName.setStyleName("fontstylecss");
		persionaldetailsflextable.setWidget(0, 1, lblMiddleName);
		lblMiddleName.setWidth("200px");
		
		HTML lblLastName = new HTML("Surname <font color='red'>*</font>");
		lblLastName.setStyleName("fontstylecss");
		persionaldetailsflextable.setWidget(0, 2, lblLastName);
		lblLastName.setWidth("200px");
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_BOTTOM);
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_LEFT);
		
		
		persionaldetailsflextable.setWidget(1, 0, firstnametextBox);
		firstnametextBox.setSize("200px", "20px");
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_LEFT);
		
		
		
		persionaldetailsflextable.setWidget(1, 1, middlenametextBox);
		middlenametextBox.setSize("200px", "20px");
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		
		persionaldetailsflextable.setWidget(1, 2, lastNametextBox);
		lastNametextBox.setSize("200px", "20px");
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(1, 2, HasHorizontalAlignment.ALIGN_LEFT);
		
		Label lblBatch = new Label("Student Course");
		lblBatch.setStyleName("fontstylecss");
		persionaldetailsflextable.setWidget(2, 0, lblBatch);
		lblBatch.setWidth("200px");
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT);
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_TOP);
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_BOTTOM);
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(1, 2, HasVerticalAlignment.ALIGN_TOP);
		
		Label lblStudentCourse = new Label("Section");
		lblStudentCourse.setStyleName("fontstylecss");
		persionaldetailsflextable.setWidget(2, 1, lblStudentCourse);
		lblStudentCourse.setWidth("200px");
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(8, 0, HasVerticalAlignment.ALIGN_BOTTOM);
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(8, 0, HasHorizontalAlignment.ALIGN_LEFT);
		
		HTML lblFee = new HTML("Fee<font color='red'>*</font>");
		persionaldetailsflextable.setWidget(2, 2, lblFee);
		lblFee.setWidth("200px");
		
		persionaldetailsflextable.setWidget(3, 0, coursecomboBox);
		coursecomboBox.setSize("210px", "30px");
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_LEFT);
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(3, 0, HasVerticalAlignment.ALIGN_TOP);
		
		sectioncomboBox.clear();
		sectioncomboBox.addItem("Select Section");
		persionaldetailsflextable.setWidget(3, 1, sectioncomboBox);
		sectioncomboBox.setWidth("210px");
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(9, 0, HasVerticalAlignment.ALIGN_TOP);
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_LEFT);
		
		
		persionaldetailsflextable.setWidget(3, 2, feetextBox);
		feetextBox.setSize("200px", "20px");
		
		Label lblBloodGroup = new Label("Blood Group");
		lblBloodGroup.setStyleName("fontstylecss");
		persionaldetailsflextable.setWidget(4, 0, lblBloodGroup);
		lblBloodGroup.setWidth("200px");
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(4, 0, HasVerticalAlignment.ALIGN_BOTTOM);
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_LEFT);
		
		Label lblBirthPlace = new Label("Birth Place");
		lblBirthPlace.setStyleName("fontstylecss");
		persionaldetailsflextable.setWidget(4, 1, lblBirthPlace);
		lblBirthPlace.setWidth("200px");
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(4, 1, HasVerticalAlignment.ALIGN_BOTTOM);
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(4, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		HTML lblNationality = new HTML("Nationality <font color='red'>*</font>");
		lblNationality.setStyleName("fontstylecss");
		persionaldetailsflextable.setWidget(4, 2, lblNationality);
		lblNationality.setWidth("200px");
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(4, 2, HasVerticalAlignment.ALIGN_BOTTOM);
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(4, 2, HasHorizontalAlignment.ALIGN_LEFT);
		
			
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
		
		persionaldetailsflextable.setWidget(5, 0, bloodgroupcomboBox);
		bloodgroupcomboBox.setWidth("210px");
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_LEFT);
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(5, 0, HasVerticalAlignment.ALIGN_TOP);
		
		
		persionaldetailsflextable.setWidget(5, 1, birthplacetextBox);
		birthplacetextBox.setSize("200px", "20px");
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(5, 1, HasVerticalAlignment.ALIGN_TOP);
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(5, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		nationalitytextBox.setText("Indian");
		persionaldetailsflextable.setWidget(5, 2, nationalitytextBox);
		nationalitytextBox.setSize("200px", "20px");
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(5, 2, HasVerticalAlignment.ALIGN_TOP);
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(5, 2, HasHorizontalAlignment.ALIGN_LEFT);
		
		Label lblLanguage = new Label("Language");
		lblLanguage.setStyleName("fontstylecss");
		persionaldetailsflextable.setWidget(6, 0, lblLanguage);
		lblLanguage.setWidth("200px");
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(6, 0, HasVerticalAlignment.ALIGN_BOTTOM);
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_LEFT);
		
		Label lblReligion = new Label("Religion");
		lblReligion.setStyleName("fontstylecss");
		persionaldetailsflextable.setWidget(6, 1, lblReligion);
		lblReligion.setWidth("200px");
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(6, 1, HasVerticalAlignment.ALIGN_BOTTOM);
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(6, 1, HasHorizontalAlignment.ALIGN_LEFT);
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(2, 1, HasVerticalAlignment.ALIGN_BOTTOM);
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		HTML lblDateOfBirth = new HTML("Date Of Birth <font color='red'>*</font>");
		persionaldetailsflextable.setWidget(6, 2, lblDateOfBirth);
		lblDateOfBirth.setStyleName("fontstylecss");
		lblDateOfBirth.setWidth("200px");
		
		
		persionaldetailsflextable.setWidget(7, 0, languagetextBox);
		languagetextBox.setSize("200px", "20px");
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(7, 0, HasVerticalAlignment.ALIGN_TOP);
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(7, 0, HasHorizontalAlignment.ALIGN_LEFT);
		
		
		persionaldetailsflextable.setWidget(7, 1, religiontextBox);
		religiontextBox.setSize("200px", "20px");
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(7, 1, HasVerticalAlignment.ALIGN_TOP);
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(7, 1, HasHorizontalAlignment.ALIGN_LEFT);
		persionaldetailsflextable.getCellFormatter().setVerticalAlignment(3, 1, HasVerticalAlignment.ALIGN_TOP);
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		
		datofbirthdateBox.setValue(new Date());
		datofbirthdateBox.setAutoClose(true);
		datofbirthdateBox.setReadOnly(true);
		persionaldetailsflextable.setWidget(7, 2, datofbirthdateBox);
		datofbirthdateBox.setWidth("200px");
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(7, 2, HasHorizontalAlignment.ALIGN_LEFT);
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(6, 2, HasHorizontalAlignment.ALIGN_LEFT);
		
		HTML lblGender = new HTML("Gender <font color='red'>*</font>");
		lblGender.setStyleName("fontstylecss");
		persionaldetailsflextable.setWidget(8, 0, lblGender);
		lblGender.setWidth("200px");
		
		Label lblImageUpload = new Label("Photo Upload");
		lblImageUpload.setStylePrimaryName("fontstylecss");
		persionaldetailsflextable.setWidget(8, 1, lblImageUpload);
		
		gendercomboBox.clear();
		gendercomboBox.addItem("Select Gender");
		gendercomboBox.addItem("Male");
		gendercomboBox.addItem("Female");
		persionaldetailsflextable.setWidget(9, 0, gendercomboBox);
		gendercomboBox.setSize("210px", "30px");
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(2, 2, HasHorizontalAlignment.ALIGN_LEFT);
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(3, 2, HasHorizontalAlignment.ALIGN_LEFT);
		
		Image image = new Image("images/studentimage.png");
		image.setTitle("Photo Upload");
		persionaldetailsflextable.setWidget(9, 1, image);
		image.setSize("43px", "32px");
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(9, 1, HasHorizontalAlignment.ALIGN_LEFT);
		persionaldetailsflextable.getCellFormatter().setHorizontalAlignment(8, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable2.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable2.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		mainfrlextable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		mainfrlextable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_LEFT);
		
		FlexTable flexTable_2 = new FlexTable();
		flexTable_2.setStyleName("addboarder");
		mainfrlextable.setWidget(2, 0, flexTable_2);
		flexTable_2.setSize("800px", "164px");
		
		Label lblParentPersonal = new Label("Parent  Details");
		lblParentPersonal.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(0, 0, lblParentPersonal);
		
		FlexTable flexTable_3 = new FlexTable();
		flexTable_2.setWidget(1, 0, flexTable_3);
		flexTable_3.setSize("750px", "140px");
		
		HTML html = new HTML("First Name <font color='red'>*</font>");
		html.setStyleName("fontstylecss");
		flexTable_3.setWidget(0, 0, html);
		html.setWidth("200px");
		
		HTML htmlSurname = new HTML("Surname<font color='red'>*</font>");
		htmlSurname.setStyleName("fontstylecss");
		flexTable_3.setWidget(0, 1, htmlSurname);
		htmlSurname.setWidth("200px");
		
		HTML lblRelation = new HTML("Relation <font color='red'>*</font>");
		lblRelation.setStyleName("fontstylecss");
		flexTable_3.setWidget(0, 2, lblRelation);
		
		
		parentfirstnametextBox.setText("");
		flexTable_3.setWidget(1, 0, parentfirstnametextBox);
		parentfirstnametextBox.setSize("200px", "20px");
		
		
		parentlastnametextBox.setText("");
		flexTable_3.setWidget(1, 1, parentlastnametextBox);
		parentlastnametextBox.setSize("200px", "20px");
		
		relationlistbox.clear();
		relationlistbox.addItem("Select Relationship");
		relationlistbox.addItem("Father");
		relationlistbox.addItem("Mother");
		relationlistbox.addItem("Uncle");
		relationlistbox.addItem("Brother");
		relationlistbox.addItem("Sister");
		flexTable_3.setWidget(1, 2, relationlistbox);
		
		HTML parentdatabirthday = new HTML("Date Of Birth ");
		parentdatabirthday.setStyleName("fontstylecss");
		flexTable_3.setWidget(2, 0, parentdatabirthday);
		parentdatabirthday.setWidth("200px");
		
		HTML html_5 = new HTML("Gender <font color='red'>*</font>");
		html_5.setStyleName("fontstylecss");
		flexTable_3.setWidget(2, 1, html_5);
		html_5.setWidth("200px");
		
		Label lblEducation = new Label("Education");
		lblEducation.setStyleName("fontstylecss");
		flexTable_3.setWidget(2, 2, lblEducation);
		lblEducation.setWidth("200px");
		
		
		parentdateofbirthdateBoxAppended.setReadOnly(true);
		parentdateofbirthdateBoxAppended.setAutoClose(true);
		flexTable_3.setWidget(3, 0, parentdateofbirthdateBoxAppended);
		parentdateofbirthdateBoxAppended.setWidth("191px");
		flexTable_3.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_3.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_3.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_3.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_3.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_3.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_3.getCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_3.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_3.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT);
		
		parentgenderlistbox.clear();
		parentgenderlistbox.addItem("Select Gender");
		parentgenderlistbox.addItem("Male");
		parentgenderlistbox.addItem("Female");
		parentgenderlistbox.setSelectedIndex(0);
		flexTable_3.setWidget(3, 1, parentgenderlistbox);
		parentgenderlistbox.setSize("210px", "30px");
		flexTable_3.getCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_3.getCellFormatter().setVerticalAlignment(3, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_3.getCellFormatter().setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_3.getCellFormatter().setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_TOP);
		flexTable_3.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_3.getCellFormatter().setVerticalAlignment(1, 2, HasVerticalAlignment.ALIGN_TOP);
		flexTable_3.getCellFormatter().setHorizontalAlignment(1, 2, HasHorizontalAlignment.ALIGN_LEFT);
		
		
		flexTable_3.setWidget(3, 2, parenteducationtextBox);
		parenteducationtextBox.setWidth("200px");
		flexTable_3.getCellFormatter().setVerticalAlignment(3, 2, HasVerticalAlignment.ALIGN_TOP);
		flexTable_3.getCellFormatter().setHorizontalAlignment(3, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_3.getCellFormatter().setHorizontalAlignment(2, 2, HasHorizontalAlignment.ALIGN_LEFT);
		
		Label lblOccupation = new Label("Occupation");
		lblOccupation.setStyleName("fontstylecss");
		flexTable_3.setWidget(4, 0, lblOccupation);
		lblOccupation.setWidth("200px");
		
		Label lblIncom = new Label("Income");
		lblIncom.setStyleName("fontstylecss");
		flexTable_3.setWidget(4, 1, lblIncom);
		lblIncom.setWidth("200px");
		
		
		flexTable_3.setWidget(5, 0, parentoccupationtextBox);
		flexTable_3.getCellFormatter().setVerticalAlignment(4, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_3.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_3.getCellFormatter().setVerticalAlignment(5, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_3.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_3.getCellFormatter().setHorizontalAlignment(4, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		
		flexTable_3.setWidget(5, 1, parentincometextBox);
		flexTable_3.getCellFormatter().setHorizontalAlignment(5, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_3.getCellFormatter().setVerticalAlignment(3, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		FlexTable flexTable3 = new FlexTable();
		flexTable3.setStyleName("addboarder");
		mainfrlextable.setWidget(3, 0, flexTable3);
		flexTable3.setWidth("800px");
		
		Label lblContactDetails = new Label("Current Place Details");
		lblContactDetails.setStyleName("subheadingfontcss");
		flexTable3.setWidget(0, 0, lblContactDetails);
		lblContactDetails.setHeight("21px");
		
		FlexTable contactdetailsflexTable = new FlexTable();
		flexTable3.setWidget(1, 0, contactdetailsflexTable);
		contactdetailsflexTable.setWidth("750px");
		
		HTML lblAddressLine = new HTML("Address Line1 <font color='red'>*</font>");
		lblAddressLine.setStyleName("fontstylecss");
		contactdetailsflexTable.setWidget(0, 0, lblAddressLine);
		lblAddressLine.setWidth("200px");
		
		HTML lblAddressLine_1 = new HTML("Address Line2");
		lblAddressLine_1.setStyleName("fontstylecss");
		contactdetailsflexTable.setWidget(0, 1, lblAddressLine_1);
		lblAddressLine_1.setWidth("200px");
		
		HTML lblCity = new HTML("City <font color='red'>*</font>");
		lblCity.setStyleName("fontstylecss");
		contactdetailsflexTable.setWidget(0, 2, lblCity);
		lblCity.setWidth("200px");
		contactdetailsflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		contactdetailsflexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_BOTTOM);
		contactdetailsflexTable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		contactdetailsflexTable.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_BOTTOM);
		contactdetailsflexTable.getCellFormatter().setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_BOTTOM);
		contactdetailsflexTable.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_LEFT);
		
		contactdetailsflexTable.setWidget(1, 0, addressline1textBox);
		addressline1textBox.setSize("200px", "20px");
		contactdetailsflexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		contactdetailsflexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_LEFT);
		
		
		contactdetailsflexTable.setWidget(1, 1, addressline2textBox);
		addressline2textBox.setSize("200px", "20px");
		contactdetailsflexTable.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		
		contactdetailsflexTable.setWidget(1, 2, citytextBox);
		citytextBox.setSize("200px", "20px");
		contactdetailsflexTable.getCellFormatter().setVerticalAlignment(1, 2, HasVerticalAlignment.ALIGN_TOP);
		contactdetailsflexTable.getCellFormatter().setHorizontalAlignment(1, 2, HasHorizontalAlignment.ALIGN_LEFT);
		
		HTML lblState = new HTML("State <font color='red'>*</font>");
		lblState.setStyleName("fontstylecss");
		contactdetailsflexTable.setWidget(2, 0, lblState);
		lblState.setWidth("200px");
		
		HTML lblPinCode = new HTML("PinCode ");
		lblPinCode.setStyleName("fontstylecss");
		contactdetailsflexTable.setWidget(2, 1, lblPinCode);
		lblPinCode.setWidth("200px");
		
		HTML lblCountry = new HTML("Country <font color='red'>*</font>");
		lblCountry.setStyleName("fontstylecss");
		contactdetailsflexTable.setWidget(2, 2, lblCountry);
		lblCountry.setWidth("200px");
		contactdetailsflexTable.getCellFormatter().setVerticalAlignment(2, 0, HasVerticalAlignment.ALIGN_BOTTOM);
		contactdetailsflexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT);
		contactdetailsflexTable.getCellFormatter().setVerticalAlignment(2, 1, HasVerticalAlignment.ALIGN_BOTTOM);
		contactdetailsflexTable.getCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_LEFT);
		contactdetailsflexTable.getCellFormatter().setVerticalAlignment(2, 2, HasVerticalAlignment.ALIGN_BOTTOM);
		contactdetailsflexTable.getCellFormatter().setHorizontalAlignment(2, 2, HasHorizontalAlignment.ALIGN_LEFT);
		
		contactdetailsflexTable.setWidget(3, 0, statetextBox);
		statetextBox.setSize("200px", "20px");
		contactdetailsflexTable.getCellFormatter().setVerticalAlignment(3, 0, HasVerticalAlignment.ALIGN_TOP);
		contactdetailsflexTable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_LEFT);
		
		
		contactdetailsflexTable.setWidget(3, 1, pincodetextBox);
		pincodetextBox.setSize("200px", "20px");
		contactdetailsflexTable.getCellFormatter().setVerticalAlignment(3, 1, HasVerticalAlignment.ALIGN_TOP);
		contactdetailsflexTable.getCellFormatter().setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		countrytextBox.setText("India");
		contactdetailsflexTable.setWidget(3, 2, countrytextBox);
		countrytextBox.setSize("200px", "20px");
		contactdetailsflexTable.getCellFormatter().setVerticalAlignment(3, 2, HasVerticalAlignment.ALIGN_TOP);
		contactdetailsflexTable.getCellFormatter().setHorizontalAlignment(3, 2, HasHorizontalAlignment.ALIGN_LEFT);
		
		HTML lblPhone = new HTML("Phone 1 <font color='red'>*</font>");
		lblPhone.setStyleName("fontstylecss");
		contactdetailsflexTable.setWidget(4, 0, lblPhone);
		lblPhone.setWidth("200px");
		
		Label lblPhone_1 = new Label("Phone 2");
		lblPhone_1.setStyleName("fontstylecss");
		contactdetailsflexTable.setWidget(4, 1, lblPhone_1);
		lblPhone_1.setWidth("200px");
		
		HTML lblEmail = new HTML("Email");
		lblEmail.setStyleName("fontstylecss");
		contactdetailsflexTable.setWidget(4, 2, lblEmail);
		lblEmail.setSize("200px", "14px");
		contactdetailsflexTable.getCellFormatter().setVerticalAlignment(4, 0, HasVerticalAlignment.ALIGN_BOTTOM);
		contactdetailsflexTable.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_LEFT);
		contactdetailsflexTable.getCellFormatter().setVerticalAlignment(4, 1, HasVerticalAlignment.ALIGN_BOTTOM);
		contactdetailsflexTable.getCellFormatter().setHorizontalAlignment(4, 1, HasHorizontalAlignment.ALIGN_LEFT);
		contactdetailsflexTable.getCellFormatter().setVerticalAlignment(4, 2, HasVerticalAlignment.ALIGN_BOTTOM);
		contactdetailsflexTable.getCellFormatter().setHorizontalAlignment(4, 2, HasHorizontalAlignment.ALIGN_LEFT);
		
		
		contactdetailsflexTable.setWidget(5, 0, phone1textBox);
		phone1textBox.setSize("200px", "20px");
		contactdetailsflexTable.getCellFormatter().setVerticalAlignment(5, 0, HasVerticalAlignment.ALIGN_TOP);
		contactdetailsflexTable.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_LEFT);
		
		
		contactdetailsflexTable.setWidget(5, 1, phone2textBox);
		phone2textBox.setSize("200px", "20px");
		contactdetailsflexTable.getCellFormatter().setVerticalAlignment(5, 1, HasVerticalAlignment.ALIGN_TOP);
		contactdetailsflexTable.getCellFormatter().setHorizontalAlignment(5, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		
		contactdetailsflexTable.setWidget(5, 2, emailtextBox);
		emailtextBox.setSize("200px", "20px");
		contactdetailsflexTable.getCellFormatter().setVerticalAlignment(5, 2, HasVerticalAlignment.ALIGN_TOP);
		contactdetailsflexTable.getCellFormatter().setHorizontalAlignment(5, 2, HasHorizontalAlignment.ALIGN_LEFT);
		contactdetailsflexTable.getCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable3.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable3.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		
		FlexTable flexTable_1 = new FlexTable();
		flexTable_1.setStyleName("addboarder");
		mainfrlextable.setWidget(4, 0, flexTable_1);
		flexTable_1.setWidth("800px");
		
		Label label = new Label("Permanent Place Details");
		label.setStyleName("subheadingfontcss");
		flexTable_1.setWidget(0, 0, label);
		label.setHeight("21px");
		
		FlexTable flexTable_4 = new FlexTable();
		flexTable_1.setWidget(1, 0, flexTable_4);
		flexTable_4.setWidth("750px");
		flexTable_4.setWidget(0, 0, chckbxSameAsCurrentDetails);
		
		
		chckbxSameAsCurrentDetails.setStyleName("redcolorcss");
		
		HTML html_2 = new HTML("Address Line1 <font color='red'>*</font>");
		html_2.setStyleName("fontstylecss");
		flexTable_4.setWidget(1, 0, html_2);
		html_2.setWidth("200px");
		
		HTML html_3 = new HTML("Address Line2");
		html_3.setStyleName("fontstylecss");
		flexTable_4.setWidget(1, 1, html_3);
		html_3.setWidth("200px");
		
		HTML html_4 = new HTML("City <font color='red'>*</font>");
		html_4.setStyleName("fontstylecss");
		flexTable_4.setWidget(1, 2, html_4);
		html_4.setWidth("200px");
		
		
		parentaddress1textArea.setText("");
		flexTable_4.setWidget(2, 0, parentaddress1textArea);
		parentaddress1textArea.setSize("200px", "20px");
		
		
		parentaddress2textArea.setText("");
		flexTable_4.setWidget(2, 1, parentaddress2textArea);
		parentaddress2textArea.setSize("200px", "20px");
		
		
		parentcitytextBox.setText("");
		flexTable_4.setWidget(2, 2, parentcitytextBox);
		parentcitytextBox.setSize("200px", "20px");
		
		HTML html_6 = new HTML("State <font color='red'>*</font>");
		html_6.setStyleName("fontstylecss");
		flexTable_4.setWidget(3, 0, html_6);
		html_6.setWidth("200px");
		
		HTML htmlPincode = new HTML("PinCode");
		htmlPincode.setStyleName("fontstylecss");
		flexTable_4.setWidget(3, 1, htmlPincode);
		htmlPincode.setWidth("200px");
		
		HTML html_8 = new HTML("Country <font color='red'>*</font>");
		html_8.setStyleName("fontstylecss");
		flexTable_4.setWidget(3, 2, html_8);
		html_8.setWidth("200px");
		
		
		parentstatetextBox.setText("");
		flexTable_4.setWidget(4, 0, parentstatetextBox);
		parentstatetextBox.setSize("200px", "20px");
		
		
		parentpincodetextBox.setText("");
		flexTable_4.setWidget(4, 1, parentpincodetextBox);
		parentpincodetextBox.setSize("200px", "20px");
		
		parentcountrytextBox.setText("India");
		parentcountrytextBox.setText("");
		flexTable_4.setWidget(4, 2, parentcountrytextBox);
		parentcountrytextBox.setSize("200px", "20px");
		
		HTML html_9 = new HTML("Phone 1 <font color='red'>*</font>");
		html_9.setStyleName("fontstylecss");
		flexTable_4.setWidget(5, 0, html_9);
		html_9.setWidth("200px");
		
		Label label_1 = new Label("Phone 2");
		label_1.setStyleName("fontstylecss");
		flexTable_4.setWidget(5, 1, label_1);
		label_1.setWidth("200px");
		
		HTML htmlEmail = new HTML("Email");
		htmlEmail.setStyleName("fontstylecss");
		flexTable_4.setWidget(5, 2, htmlEmail);
		htmlEmail.setSize("200px", "14px");
		
	
		parentphone1textBox.setText("");
		flexTable_4.setWidget(6, 0, parentphone1textBox);
		parentphone1textBox.setSize("200px", "20px");
		
		
		
		parentphone2textBox.setText("");
		flexTable_4.setWidget(6, 1, parentphone2textBox);
		parentphone2textBox.setSize("200px", "20px");
		
	
		parentemailidtextBox.setText("");
		flexTable_4.setWidget(6, 2, parentemailidtextBox);
		parentemailidtextBox.setSize("200px", "20px");
		flexTable_4.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_4.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_4.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_4.getCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_4.getCellFormatter().setHorizontalAlignment(1, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_4.getCellFormatter().setVerticalAlignment(1, 2, HasVerticalAlignment.ALIGN_TOP);
		flexTable_4.getCellFormatter().setVerticalAlignment(2, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_4.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_4.getCellFormatter().setVerticalAlignment(2, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_4.getCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_4.getCellFormatter().setVerticalAlignment(2, 2, HasVerticalAlignment.ALIGN_TOP);
		flexTable_4.getCellFormatter().setHorizontalAlignment(2, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_4.getCellFormatter().setVerticalAlignment(3, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_4.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_4.getCellFormatter().setVerticalAlignment(4, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_4.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_4.getCellFormatter().setVerticalAlignment(4, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_4.getCellFormatter().setHorizontalAlignment(4, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_4.getCellFormatter().setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_4.getCellFormatter().setVerticalAlignment(3, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_4.getCellFormatter().setHorizontalAlignment(4, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_4.getCellFormatter().setVerticalAlignment(4, 2, HasVerticalAlignment.ALIGN_TOP);
		flexTable_4.getCellFormatter().setVerticalAlignment(5, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_4.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_4.getCellFormatter().setVerticalAlignment(5, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_4.getCellFormatter().setHorizontalAlignment(5, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_4.getCellFormatter().setVerticalAlignment(5, 2, HasVerticalAlignment.ALIGN_TOP);
		flexTable_4.getCellFormatter().setHorizontalAlignment(5, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_4.getCellFormatter().setVerticalAlignment(6, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_4.getCellFormatter().setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_4.getCellFormatter().setVerticalAlignment(6, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_4.getCellFormatter().setHorizontalAlignment(6, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_4.getCellFormatter().setVerticalAlignment(6, 2, HasVerticalAlignment.ALIGN_TOP);
		flexTable_4.getCellFormatter().setHorizontalAlignment(6, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_1.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_1.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		lblErrormessage.setStyleName("error-Label");
		
		lblErrormessage.setText("");
		mainfrlextable.setWidget(5, 0, lblErrormessage);
		mainfrlextable.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainfrlextable.getCellFormatter().setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_LEFT);
		
		FlexTable flexTable5 = new FlexTable();
		mainfrlextable.setWidget(6, 0, flexTable5);
		flexTable5.setSize("150px", "55px");
		
		
		btnParentDetails.setType(ButtonType.SUCCESS);
		flexTable5.setWidget(0, 0, btnParentDetails);
		btnParentDetails.setWidth("90px");
		flexTable5.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		btnParentDetails.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				validationStudentInfo();
			}
		});
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
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
		
		sectioncomboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {

				if(sectioncomboBox.getSelectedIndex()!=0)
				{
					getFeeDetails();
				}
			}
		});
		
		chckbxSameAsCurrentDetails.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				setDataSameAsCurrentDetails();
			}
		});
		
		
		
		
		resetData();
		DateUtil.getSeverDate();
	}
		
	
	public void validationStudentInfo()
	{
	
		
		/*if(admissiondateBox==null || DateUtil.checkCurrentDate(admissiondateBox)<0)
		{
			lblErrormessage.setText("Please Check the Admission Date");
		}*/
		if(admissionnumbertextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please Enter AdmissionNumber");
		}
		else if(firstnametextBox==null|| firstnametextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please enter Student FirstName");
		}
		else if(lastNametextBox==null||lastNametextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please enter Student Surname");
		}
		else if(coursecomboBox==null || coursecomboBox.getSelectedIndex()==0) 
		{
			lblErrormessage.setText("Please Select Course");
		}
		else if(sectioncomboBox==null || sectioncomboBox.getSelectedIndex()==0)
		{
			lblErrormessage.setText("Please Select Section");
		}
		else if(feetextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please Enter Fee");
		}
		else if(!feetextBox.getText().trim().matches("^[0-9]{1,30}$") || Integer.parseInt(feetextBox.getText().trim())<=0)
		{
			lblErrormessage.setText("Please Check Fee");
		}
		else if(nationalitytextBox==null||nationalitytextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please enter Student Nationality");
		}
		else if(datofbirthdateBox==null|| DateUtil.checkCurrentDate(datofbirthdateBox)>0)
		{
			lblErrormessage.setText("Please enter Student Date of Birth");
		}
		else if(gendercomboBox==null || gendercomboBox.getSelectedIndex()==0)
		{
			lblErrormessage.setText("Please Select Student Gender");
		}
		else if(parentfirstnametextBox==null|| parentfirstnametextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please enter Parent FirstName");
		}
		else if(parentlastnametextBox==null||parentlastnametextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please enter Parent Surname");
		}
		else if(relationlistbox.getSelectedIndex()==0)
		{
			lblErrormessage.setText("Please Select Relationship");
		}
		else if(parentgenderlistbox==null || parentgenderlistbox.getSelectedIndex()==0)
		{
			lblErrormessage.setText("Please Select Parent Gender");
		}
		else if(addressline1textBox==null || addressline1textBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please enter Student Addrees Line1");
		}
		/*else if(addressline2textBox==null || addressline2textBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please enter Address Line2");
		}*/
		else if(citytextBox==null || citytextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please enter Student City Name");
		}
		else if(statetextBox==null || statetextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please enter Student State Name");
		}
		/*else if(pincodetextBox==null || pincodetextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please enter Pincode");
		}*/
		else if(pincodetextBox.getText().length()!=0&&!pincodetextBox.getText().matches("^[0-9]{1,10}$"))
		{
			lblErrormessage.setText("Please enter Numbers Only in Current Pincode");
		}
		else if(countrytextBox==null || countrytextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please enter Country Name");
		}
		else if(phone1textBox==null || phone1textBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please enter Phone Number");
		}
		else if(!phone1textBox.getText().matches("^[0-9]{1,10}$"))
		{
			lblErrormessage.setText("Please enter Valid Phone Number ");
		}
		else if(emailtextBox.getText().length()!=0&&!emailtextBox.getText().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
		{
			lblErrormessage.setText("Please enter valid Email ID");
		}
		else if(parentaddress1textArea==null || parentaddress1textArea.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please enter Permanent Addrees Line1");
		}
		/*else if(addressline2textBox==null || addressline2textBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please enter Address Line2");
		}*/
		else if(parentcitytextBox==null || parentcitytextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please enter Permanent City Name");
		}
		else if(parentstatetextBox==null || parentstatetextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please enter Permanent State Name");
		}
		/*else if(pincodetextBox==null || pincodetextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please enter Pincode");
		}*/
		else if(parentpincodetextBox.getText().length()!=0&&!parentpincodetextBox.getText().matches("^[0-9]{1,10}$"))
		{
			lblErrormessage.setText("Please enter Numbers Only in Permanent Pincode");
		}
		else if(parentcountrytextBox==null || parentcountrytextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please enter Permanent Country Name");
		}
		else if(parentphone1textBox==null || parentphone1textBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please enter Permanent Phone Number");
		}
		else if(!parentphone1textBox.getText().matches("^[0-9]{1,10}$"))
		{
			lblErrormessage.setText("Please enter Valid Permanent Phone Number ");
		}
		else if(parentemailidtextBox.getText().length()!=0&&!parentemailidtextBox.getText().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
		{
			lblErrormessage.setText("Please enter valid Email ID");
		}
		else 
		{
			lblErrormessage.setText("");
			JSONObject studentinfo=new JSONObject();
			
			studentinfo.put("admissionnumber",new JSONString(admissionnumbertextBox.getText()));
			
			studentinfo.put("admisiondate",new JSONString(com.product.home.shared.DateUtil.convertdates(admissiondateBox.getValue())));
			
			studentinfo.put("fname",new JSONString(firstnametextBox.getText()));

			if(middlenametextBox.getText().trim().length()==0)
				studentinfo.put("mname",new JSONString("-"));
			else
				studentinfo.put("mname",new JSONString(middlenametextBox.getText()));

			studentinfo.put("lname",new JSONString(lastNametextBox.getText()));

			studentinfo.put("courseid",new JSONString(coursedetailshmap.get(coursecomboBox.getItemText(coursecomboBox.getSelectedIndex()))));

			String dateofbirth=com.product.home.shared.DateUtil.convertdates(datofbirthdateBox.getValue());
			studentinfo.put("dob",new JSONString(dateofbirth.split(" ")[0]));

			if(gendercomboBox.getItemText(gendercomboBox.getSelectedIndex()).equalsIgnoreCase("female"))
				studentinfo.put("gender",new JSONString("0"));
			else
				studentinfo.put("gender",new JSONString("1"));

			if(bloodgroupcomboBox.getSelectedIndex()==0)
			studentinfo.put("bloodgroup",new JSONString("-"));
			else
				studentinfo.put("bloodgroup",new JSONString(bloodgroupcomboBox.getItemText(bloodgroupcomboBox.getSelectedIndex())));

			if(birthplacetextBox.getText().trim().length()==0)
				studentinfo.put("birthplace",new JSONString("-"));
			else
				studentinfo.put("birthplace",new JSONString(birthplacetextBox.getText().trim()));

			studentinfo.put("nationality",new JSONString(nationalitytextBox.getText().trim()));


			if(languagetextBox.getText().trim().length()==0)
				studentinfo.put("language",new JSONString("-"));
			else
				studentinfo.put("language",new JSONString(languagetextBox.getText().trim()));

			if(religiontextBox.getText().trim().length()==0)
				studentinfo.put("religion",new JSONString("-"));
			else
				studentinfo.put("religion",new JSONString(religiontextBox.getText().trim()));


			studentinfo.put("sectionid",new JSONString(sectionnameandid.get(sectioncomboBox.getItemText(sectioncomboBox.getSelectedIndex()))));


			studentinfo.put("address1",new JSONString(addressline1textBox.getText().trim()));
			if(addressline2textBox.getText().trim().length()==0)
				studentinfo.put("address2",new JSONString("-"));
			else
				studentinfo.put("address2",new JSONString(addressline2textBox.getText().trim()));
			studentinfo.put("city",new JSONString(citytextBox.getText().trim()));
			studentinfo.put("state",new JSONString(statetextBox.getText().trim()));
			if(pincodetextBox.getText().length()==0)
				studentinfo.put("pincode",new JSONString("000000"));
			else
				studentinfo.put("pincode",new JSONString(pincodetextBox.getText().trim()));
			studentinfo.put("country",new JSONString(countrytextBox.getText().trim()));
			studentinfo.put("phone1",new JSONString(phone1textBox.getText().trim()));
			studentinfo.put("fee",new JSONString(feetextBox.getText().trim()));
			studentinfo.put("photoid",new JSONString("-"));
			if(phone2textBox.getText().trim().length()==0)
				studentinfo.put("phone2",new JSONString("-"));
			else
				studentinfo.put("phone2",new JSONString(phone2textBox.getText().trim()));
			if(emailtextBox.getText().length()==0)
				studentinfo.put("email",new JSONString("-"));
			else
				studentinfo.put("email",new JSONString(emailtextBox.getText().trim()));
			
			studentinfo.put("studentid",new JSONString("0"));
			
			/*
			 * For parent information
			 */
			studentinfo.put("parentfname",new JSONString(parentfirstnametextBox.getText()));
			studentinfo.put("parentlname",new JSONString(parentlastnametextBox.getText()));
			studentinfo.put("parentrelation",new JSONString(relationlistbox.getValue(relationlistbox.getSelectedIndex())));
			dateofbirth=com.product.home.shared.DateUtil.convertdates(parentdateofbirthdateBoxAppended.getValue());
			studentinfo.put("parentdob",new JSONString(dateofbirth.split(" ")[0]));
			if(parentgenderlistbox.getItemText(parentgenderlistbox.getSelectedIndex()).equalsIgnoreCase("female"))
				studentinfo.put("parentgender",new JSONString("0"));
			else
				studentinfo.put("parentgender",new JSONString("1"));
			if(parenteducationtextBox.getText().length()==0)
				studentinfo.put("parenteducation",new JSONString("-"));
			else
				studentinfo.put("parenteducation",new JSONString(parenteducationtextBox.getText()));
			if(parentoccupationtextBox.getText().length()==0)
				studentinfo.put("parentoccupation",new JSONString("-"));
			else
				studentinfo.put("parentoccupation",new JSONString(parentoccupationtextBox.getText()));
			if(parentincometextBox.getText().length()==0)
				studentinfo.put("parentincome",new JSONString("-"));
			else
				studentinfo.put("parentincome",new JSONString(parentincometextBox.getText()));
			
			studentinfo.put("parentaddress1",new JSONString(parentaddress1textArea.getText().trim()));
			if(parentaddress2textArea.getText().trim().length()==0)
				studentinfo.put("parentaddress2",new JSONString("-"));
			else
				studentinfo.put("parentaddress2",new JSONString(parentaddress2textArea.getText().trim()));
			studentinfo.put("parentcity",new JSONString(parentcitytextBox.getText().trim()));
			studentinfo.put("parentstate",new JSONString(parentstatetextBox.getText().trim()));
			if(parentpincodetextBox.getText().length()==0)
				studentinfo.put("parentpincode",new JSONString("000000"));
			else
				studentinfo.put("parentpincode",new JSONString(parentpincodetextBox.getText().trim()));
			studentinfo.put("parentcountry",new JSONString(parentcountrytextBox.getText().trim()));
			studentinfo.put("parentphone1",new JSONString(parentphone1textBox.getText().trim()));
			if(parentphone2textBox.getText().trim().length()==0)
				studentinfo.put("parentphone2",new JSONString("-"));
			else
				studentinfo.put("parentphone2",new JSONString(parentphone2textBox.getText().trim()));
			if(parentemailidtextBox.getText().length()==0)
				studentinfo.put("parentemail",new JSONString("-"));
			else
				studentinfo.put("parentemail",new JSONString(parentemailidtextBox.getText().trim()));
			
			boolean check=Window.confirm("Do you want Proceed");
			if(check==true)
			{
				CreateStudentInfo(studentinfo);
			}
		}
	}
	
	public void setDataSameAsCurrentDetails()
	{
		if(chckbxSameAsCurrentDetails.getValue()==true)
		{
			parentaddress1textArea.setText(addressline1textBox.getText());
			parentaddress2textArea.setText(addressline2textBox.getText());
			parentcitytextBox.setText(citytextBox.getText());
			parentstatetextBox.setText(statetextBox.getText());
			parentpincodetextBox.setText(pincodetextBox.getText());
			parentcountrytextBox.setText(countrytextBox.getText());
			parentphone1textBox.setText(phone1textBox.getText());
			parentphone2textBox.setText(phone2textBox.getText());
			parentemailidtextBox.setText(emailtextBox.getText());
		}
		else
		{
			parentaddress1textArea.setText("");
			parentaddress2textArea.setText("");
			parentcitytextBox.setText("");
			parentstatetextBox.setText("");
			parentpincodetextBox.setText("");
			parentcountrytextBox.setText("");
			parentcountrytextBox.setText("India");
			parentphone1textBox.setText("");
			parentphone2textBox.setText("");
			parentemailidtextBox.setText("");
		}

	}
	
	
	public void CreateStudentInfo(JSONObject jsonobj)
	{
		mainservice.insetNewStudentInfo(jsonobj.toString(),new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}
			@Override
			public void onSuccess(String result) {
				
				JSONObject responsejson=JSONParser.parseStrict(result).isObject();
				if(responsejson.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					Window.alert("Student Information Inserted Successfully");
					resetData();
				}
				else
				{
					Window.alert("Student Information Insertion Failed");
				}
			}
		});
	}
	
	public void resetData()
	{
		getSections();
		getCourses();
		
		firstnametextBox.setText("");
		middlenametextBox.setText("");
	    lastNametextBox.setText("");
	    
	    datofbirthdateBox.setValue(new Date());
	    gendercomboBox.setSelectedIndex(0);
	    bloodgroupcomboBox.setSelectedIndex(0);
	    birthplacetextBox.setText("");
	    nationalitytextBox.setText("");
	    nationalitytextBox.setText("Indian");
	    languagetextBox.setText("");
	    religiontextBox.setText("");
	   
	    addressline1textBox.setText("");
	    addressline2textBox.setText("");
	    citytextBox.setText("");
	    statetextBox.setText("");
	    pincodetextBox.setText("");
	    countrytextBox.setText("");
	    countrytextBox.setText("India");
	    phone1textBox.setText("");
	    phone2textBox.setText("");
	    emailtextBox.setText("");
	    feetextBox.setText("");
	    
	    parentfirstnametextBox.setText("");
		parentlastnametextBox.setText("");
		relationlistbox.setSelectedIndex(0);
		parentdateofbirthdateBoxAppended.setValue(new Date());
		parentgenderlistbox.setSelectedIndex(0);
		parenteducationtextBox.setText("");
		parentoccupationtextBox.setText("");
		parentincometextBox.setText("");
	    
	    
	    parentaddress1textArea.setText("");
		parentaddress2textArea.setText("");
		parentcitytextBox.setText("");
		parentstatetextBox.setText("");
		parentpincodetextBox.setText("");
		parentcountrytextBox.setText("");
		parentcountrytextBox.setText("India");
		parentphone1textBox.setText("");
		parentphone2textBox.setText("");
		parentemailidtextBox.setText("");
		
	    sectioncomboBox.clear();
	    sectioncomboBox.addItem("Select Section");
	    
	    chckbxSameAsCurrentDetails.setValue(false);
	    
	    lblErrormessage.setText("");
	    admissionnumbertextBox.setText("");
	    getAdmissionNumber();
	    
	  }
	
	private void getAdmissionNumber() {
		
		mainservice.getAdmissionNumber(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(String result) {
				
				JSONObject response=JSONParser.parseStrict(result).isObject();
				admissionnumbertextBox.setText("");
				if(response!=null)
				{
					if(response.get("status").isString().stringValue().equalsIgnoreCase("success"))
					{
						admissionnumbertextBox.setText(response.get(Jsonkeys.admisionno).isString().stringValue());
					}
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
	
	
	public void getFeeDetails()
	{
		String courseid=coursedetailshmap.get(coursecomboBox.getValue(coursecomboBox.getSelectedIndex()));
		String sectionid=sectionnameandid.get(sectioncomboBox.getValue(sectioncomboBox.getSelectedIndex()));
		mainservice.getFeeDetails(courseid,sectionid,new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				
				JSONObject response=JSONParser.parseStrict(result).isObject();
				feetextBox.setText("");
				if(response!=null && response.size()!=0)
				{
					if(response.get("status").isString().stringValue().equalsIgnoreCase("success"))
					{
						JSONObject dataobj=response.get("data").isArray().get(0).isObject();
						feetextBox.setText(dataobj.get("fee").isString().stringValue());
					}
				}
			}
		});
	}

	@Override
	public void reset() {
		resetData();
	}
}
