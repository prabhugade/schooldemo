package com.product.home.client.client.admin.fee;

import java.util.HashMap;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.product.home.client.Jsonkeys;
import com.product.home.client.MainService;
import com.product.home.client.MainServiceAsync;
import com.product.home.client.client.admin.AdminBody;
import com.product.home.client.client.admin.student.marks.CreateExamsNamesScreen;
import com.product.home.shared.DataUtilities;

public class ShowStudentFeeDetails extends AdminBody {

	MainServiceAsync mainservice=GWT.create(MainService.class); 
	ListBox coursecomboBox = new ListBox();
	ListBox sectioncomboBox = new ListBox();
	ListBox studentnamecomboBox = new ListBox();
	
	Button btnSearch = new Button();
	Label lblStudentnamevalue = new Label("");
	Label lblFeevalue = new Label("");
	
	Label lblTotalpaidamount = new Label("0.0");
	TextBox feepaytextBox = new TextBox();
	Button btnSumbit = new Button("Sumbit");
	
	
	FlexTable feedataflexTable = new FlexTable();
	
	HashMap<String,String> sectionnameandid=new HashMap<String,String>();
	HashMap<String,String> coursedetailshmap=new HashMap<String,String>();
	HashMap<String,String> studentNameAndIdhashmap=new HashMap<String,String>();
	
	FlexTable studentdatalistflexTable = new FlexTable();
	
	String studentId=null;
	public ShowStudentFeeDetails() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("100%", "92px");
		
		FlexTable mainflextable = new FlexTable();
		mainflextable.setStyleName("addboarder");
		flexTable.setWidget(0, 0, mainflextable);
		mainflextable.setWidth("800px");
		
		FlexTable listboxflexTable = new FlexTable();
		FlexTable studentlistflexTable = new FlexTable();
		studentlistflexTable.setStyleName("addboarder");
		
		if(Cookies.getCookie("rid").equals("4") || Cookies.getCookie("rid").equals("3"))
		{
			listboxflexTable.setVisible(false);
			studentlistflexTable.setVisible(true);
		}
		else
		{
			studentlistflexTable.setVisible(false);
			listboxflexTable.setVisible(true);
		}
		
		mainflextable.setWidget(0, 1, listboxflexTable);
		listboxflexTable.setSize("770px", "57px");
		
		listboxflexTable.setWidget(0, 0, coursecomboBox);
		
		sectioncomboBox.clear();
		sectioncomboBox.addItem("Select Section");
		listboxflexTable.setWidget(0, 1, sectioncomboBox);
		
		studentnamecomboBox.clear();
		studentnamecomboBox.addItem("Select StudentName");
		listboxflexTable.setWidget(0, 2, studentnamecomboBox);
		
		btnSearch.setIcon(IconType.SEARCH);
		listboxflexTable.setWidget(0, 3, btnSearch);
		mainflextable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		
		mainflextable.setWidget(1, 0, studentlistflexTable);
		studentlistflexTable.setSize("181px", "443px");
		
		Label lblStudentnames = new Label("StudentNames");
		lblStudentnames.setStylePrimaryName("googlevizivals");
		studentlistflexTable.setWidget(0, 0, lblStudentnames);
		lblStudentnames.setHeight("39px");
		studentlistflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		ScrollPanel studentlistscrollPanel = new ScrollPanel();
		studentlistflexTable.setWidget(1, 0, studentlistscrollPanel);
		studentlistscrollPanel.setHeight("425px");
		
		
		studentlistscrollPanel.setWidget(studentdatalistflexTable);
		studentdatalistflexTable.setSize("100%", "55px");
		
		Label lblStudentnamevalue_1 = new Label("studentnamevalue");
		studentdatalistflexTable.setWidget(0, 0, lblStudentnamevalue_1);
		studentlistflexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		FlexTable middleflexTable = new FlexTable();
		mainflextable.setWidget(1, 1, middleflexTable);
		middleflexTable.setSize("770px", "142px");
		
		FlexTable studentdataflexTable = new FlexTable();
		studentdataflexTable.setStyleName("addboarder");
		middleflexTable.setWidget(0, 0, studentdataflexTable);
		studentdataflexTable.setSize("500px", "97px");
		
		Label lblStudentName = new Label("Student Name:");
		studentdataflexTable.setWidget(0, 0, lblStudentName);
		lblStudentName.setWidth("104px");
		
		
		lblStudentnamevalue.setStyleName("subheadingfontcss");
		studentdataflexTable.setWidget(0, 1, lblStudentnamevalue);
		lblStudentnamevalue.setWidth("263px");
		
		Label lblTatalFee = new Label("Tatal Fee:");
		studentdataflexTable.setWidget(1, 0, lblTatalFee);
		lblTatalFee.setWidth("93px");
		
		
		lblFeevalue.setStyleName("subheadingfontcss");
		studentdataflexTable.setWidget(1, 1, lblFeevalue);
		lblFeevalue.setWidth("264px");
		
		Label lblFeeDetails = new Label("Fee Details");
		lblFeeDetails.setStyleName("googlevizivals");
		middleflexTable.setWidget(1, 0, lblFeeDetails);
		lblFeeDetails.setWidth("500px");
		
		FlexTable bannerflexTable = new FlexTable();
		bannerflexTable.setStyleName("googlevizivals");
		middleflexTable.setWidget(2, 0, bannerflexTable);
		bannerflexTable.setSize("500px", "27px");
		
		Label lblDatetime = new Label("DateTime");
		bannerflexTable.setWidget(0, 0, lblDatetime);
		lblDatetime.setWidth("222px");
		
		Label lblFee = new Label("Fee");
		bannerflexTable.setWidget(0, 1, lblFee);
		lblFee.setWidth("180px");
		bannerflexTable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		bannerflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		middleflexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.setStyleName("addboarder");
		middleflexTable.setWidget(3, 0, scrollPanel);
		scrollPanel.setSize("500px", "250px");
		
		
		scrollPanel.setWidget(feedataflexTable);
		feedataflexTable.setSize("99%", "39px");
		middleflexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		middleflexTable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		middleflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		
		mainflextable.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		FlexTable bottomflexTable = new FlexTable();
		mainflextable.setWidget(2, 1, bottomflexTable);
		bottomflexTable.setWidth("500px");
		
		FlexTable flexTable_5 = new FlexTable();
		bottomflexTable.setWidget(0, 0, flexTable_5);
		flexTable_5.setSize("225px", "28px");
		
		Label lblTotalPaidFee = new Label("Total Paid Fee :");
		lblTotalPaidFee.setStyleName("subheadingfontcss");
		flexTable_5.setWidget(0, 0, lblTotalPaidFee);
		lblTotalPaidFee.setWidth("126px");
		flexTable_5.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		
		lblTotalpaidamount.setStyleName("subheadingfontcss");
		flexTable_5.setWidget(0, 1, lblTotalpaidamount);
		bottomflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		
		FlexTable btnflexTable = new FlexTable();
		if(Cookies.getCookie("rid").equalsIgnoreCase("1"))
			bottomflexTable.setWidget(1, 0, btnflexTable);
		btnflexTable.setWidth("288px");
		
		Label lblFeePay = new Label("Fee Pay:");
		lblFeePay.setStyleName("subheadingfontcss");
		btnflexTable.setWidget(0, 0, lblFeePay);
		lblFeePay.setWidth("60px");
		feepaytextBox.addBlurHandler(new BlurHandler() {
			public void onBlur(BlurEvent event) {
				String amt=feepaytextBox.getText();
				if(amt.trim().length()==0 || !amt.trim().matches("^[0-9-]{1,50}$") || Double.parseDouble(amt)==0)
				{
					Window.alert("Invalid Entry");
					feepaytextBox.setText("");
				}
			}
		});
		
		
		btnflexTable.setWidget(0, 1, feepaytextBox);
		feepaytextBox.setWidth("132px");
		
		btnSumbit.setType(ButtonType.SUCCESS);
		btnflexTable.setWidget(0, 2, btnSumbit);
		btnSumbit.setWidth("98px");
		btnflexTable.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		btnflexTable.getCellFormatter().setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_TOP);
		bottomflexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		mainflextable.getCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		coursecomboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				
				if(coursecomboBox.getSelectedIndex()!=0)
				{
					getSectionBasedOnCourseId();
				}
				else
				{
					sectioncomboBox.clear();
					sectioncomboBox.addItem("Select Section");
					
					studentnamecomboBox.clear();
					studentnamecomboBox.addItem("Select StudentName");
					
					lblStudentnamevalue.setText("");
					lblFeevalue.setText("");
					
					feedataflexTable.removeAllRows();
					lblTotalpaidamount.setText("0.0");
					feepaytextBox.setText("");
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
					studentnamecomboBox.clear();
					studentnamecomboBox.addItem("Select StudentName");
					
					lblStudentnamevalue.setText("");
					lblFeevalue.setText("");
					
					feedataflexTable.removeAllRows();
					lblTotalpaidamount.setText("0.0");
					feepaytextBox.setText("");
				}
			}
		});
		
		studentnamecomboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				
					lblStudentnamevalue.setText("");
					lblFeevalue.setText("");
					
					feedataflexTable.removeAllRows();
					lblTotalpaidamount.setText("0.0");
					feepaytextBox.setText("");
			}
		});
		
		btnSearch.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				if(coursecomboBox.getSelectedIndex()!=0)
				{
					if(sectioncomboBox.getSelectedIndex()!=0)
					{
						if(studentnamecomboBox.getSelectedIndex()!=0)
						{
							getStdentFeeDetails();
						}
						else
						{
							Window.alert("Please Select StudentName");
						}
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
		
		btnSumbit.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String amt=feepaytextBox.getText();
				if(studentnamecomboBox.getSelectedIndex()==0)
				{
					Window.alert("Please Select StudentName");
				}else if(amt.trim().length()!=0 && amt.trim().matches("^[0-9-]{1,50}$")&&Double.parseDouble(amt)!=0)
				{
					insertFee();
				}else
				{
					Window.alert("Invalid Entry");
					feepaytextBox.setText("");
				}
			}
		});
		
		resfresh();
		getCourses();
		getSections();
	}
	
	public void insertFee()
	{
		String feeamt=feepaytextBox.getText();
		String studentId=studentNameAndIdhashmap.get(studentnamecomboBox.getValue(studentnamecomboBox.getSelectedIndex()));
		
		mainservice.insertFee(feeamt,studentId,new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(String result) {
				JSONObject response=JSONParser.parseStrict(result).isObject();
				if(response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					Window.alert("Successfully inserted");
					getStdentFeeDetails();
				}else
					Window.alert(response.get("status").isString().stringValue());
			}
		});
	}
	
	public void getStdentFeeDetails()
	{
		String studentIdvalue=null;
		if(Cookies.getCookie("rid").equalsIgnoreCase("4") || Cookies.getCookie("rid").equalsIgnoreCase("3"))
		{
			studentIdvalue=studentId;
		}
		else
		{
			studentIdvalue=studentNameAndIdhashmap.get(studentnamecomboBox.getValue(studentnamecomboBox.getSelectedIndex()));
		}
		
		mainservice.getStdentFeeDetails(studentIdvalue,new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(String result) {
				
				JSONObject response=JSONParser.parseStrict(result).isObject();
				if(response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					showFeeHistory(response);
				}
			}
		});
		
	}
	
	public void showFeeHistory(JSONObject dataobject)
	{
		
		JSONObject studentdetails=dataobject.get("data").isObject();
		lblStudentnamevalue.setText(studentdetails.get("name").isString().stringValue());
		lblFeevalue.setText(studentdetails.get("fee").isString().stringValue());
		
		feedataflexTable.removeAllRows();
		lblTotalpaidamount.setText("0.0");
		feepaytextBox.setText("");
		
		JSONArray dataarray=studentdetails.get("payedfeedata").isArray();
		double paidfee=0.0;
		for(int i=0;i<dataarray.size();i++)
		{
			JSONObject data=dataarray.get(i).isObject();
			Label lblDatetimevalue = new Label(data.get("datetime").isString().stringValue());
			feedataflexTable.setWidget(i, 0, lblDatetimevalue);
			lblDatetimevalue.setSize("222px","20px");
			
			Label lblFeevalues = new Label(data.get("fee").isString().stringValue());
			feedataflexTable.setWidget(i, 1, lblFeevalues);
			lblFeevalues.setSize("222px","20px");
			paidfee=paidfee+Double.parseDouble(data.get("fee").isString().stringValue());
			
			feedataflexTable.getRowFormatter().setStyleName(i,"googlevizivalss");
			feedataflexTable.getCellFormatter().setHorizontalAlignment(i, 1, HasHorizontalAlignment.ALIGN_CENTER);
			feedataflexTable.getCellFormatter().setHorizontalAlignment(i, 0, HasHorizontalAlignment.ALIGN_CENTER);
		}
		
		lblTotalpaidamount.setText(""+paidfee);
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
		if(Cookies.getCookie("rid").equals("3") || Cookies.getCookie("rid").equals("4"))
		{
			getSectionBasedOnCourseId();
		}
		else
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
	}
	public void getSectionBasedOnCourseId()
	{
		if(Cookies.getCookie("rid").equals("3") || Cookies.getCookie("rid").equals("4"))
		{
			studentsNamesBasedOnCourseidAndSectionId();
		}
		else
		{
			String courseid=coursedetailshmap.get(coursecomboBox.getValue(coursecomboBox.getSelectedIndex()));
			mainservice.getCourseDetails(courseid,new AsyncCallback<String>() {

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
		
	}
	
	public void studentsNamesBasedOnCourseidAndSectionId()
	{
		if(Cookies.getCookie("rid").equals("3") || Cookies.getCookie("rid").equals("4"))
		{
			getSudentInformation();
		}
		else
		{
			String courseId=coursedetailshmap.get(coursecomboBox.getValue(coursecomboBox.getSelectedIndex()));
			String sectionId=sectionnameandid.get(sectioncomboBox.getValue(sectioncomboBox.getSelectedIndex()));
			mainservice.studentsNamesBasedOnCourseidAndSectionId(courseId,sectionId,new AsyncCallback<String>() {

				@Override
				public void onFailure(Throwable caught) {
				}
				@Override
				public void onSuccess(String result) {

					JSONObject response=JSONParser.parseStrict(result).isObject();
					studentnamecomboBox.clear();
					studentnamecomboBox.addItem("Select StudentName");
					studentNameAndIdhashmap.clear();
					if(response.get("status").isString().stringValue().equalsIgnoreCase("success"))
					{
						JSONArray dataarray=response.get("data").isArray();
						for(int i=0;i<dataarray.size();i++)
						{
							JSONObject dataobj=dataarray.get(i).isObject();
							studentnamecomboBox.addItem(dataobj.get("name").isString().stringValue());
							studentNameAndIdhashmap.put(dataobj.get("name").isString().stringValue(), dataobj.get("studentid").isString().stringValue());
						}
					}
				}

			});
		}
	}
	
	public void getSudentInformation()
	{
		String parentid="0";
		String studentid="0";
		JSONObject conditionjson=new JSONObject();
		if(Cookies.getCookie("rid").equalsIgnoreCase("4"))
		{
			parentid=Cookies.getCookie("stid");
			conditionjson.put(Jsonkeys.parentid, new JSONString(parentid));
		}
		if(Cookies.getCookie("rid").equalsIgnoreCase("3"))
		{
			conditionjson.put(Jsonkeys.studentid, new JSONString(Cookies.getCookie("stid")));
		}else
			conditionjson.put(Jsonkeys.parentid, new JSONString(parentid));
		mainservice.getStudentInformation(conditionjson.toString(),new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String jsonobjdata) {

				JSONValue value=JSONParser.parseStrict(jsonobjdata);
				JSONObject jsonobj=value.isObject();
				if(jsonobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					showStudentData(jsonobj.get("students").isArray());
				}
			}
		});
	}
	/**
	 * @author Venkateswararao
	 * @param studentarray  Show the Student Information JSONArray
	 */
	public void showStudentData(JSONArray studentarray)
	{
		
		studentdatalistflexTable.removeAllRows();
		lblStudentnamevalue.setText("");
		if(studentarray.size()!=0)
		{
			for(int i=0;i<studentarray.size();i++)
			{
				JSONObject studentsobj=studentarray.get(i).isObject();

				final Label lblStudentNamevalue = new Label(studentsobj.get("lname").isString().stringValue()+" "+
						studentsobj.get("fname").isString().stringValue()+" "+
						studentsobj.get("mname").isString().stringValue().substring(0,20));

				lblStudentNamevalue.setStyleName("labelfontstyle");
				lblStudentNamevalue.getElement().setId(""+studentsobj.get("studentid").isString().stringValue());
				
				lblStudentNamevalue.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						
						studentId=lblStudentNamevalue.getElement().getId();
						getStdentFeeDetails();
					}
				});
				studentdatalistflexTable.setWidget(i, 0,lblStudentNamevalue);
				studentdatalistflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
			}
		}
		else
		{
			Label recordsnotfoundalert=new Label();
			recordsnotfoundalert.setText("Sorry Records Not Found");
			recordsnotfoundalert.setStyleName("subheadingfontcss");
			studentdatalistflexTable.setWidget(0, 0,recordsnotfoundalert);
			studentdatalistflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		}
	}

	public void resfresh()
	{
		coursecomboBox.setSelectedIndex(0);
		sectioncomboBox.clear();
		sectioncomboBox.addItem("Select Section");
		studentnamecomboBox.clear();
		studentnamecomboBox.addItem("Select StudentName");
	}

	@Override
	public void reset() {
		resfresh();
		getCourses();
		getSections();
	}
}
