package com.product.home.client.client.admin.student;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.product.home.client.MainService;
import com.product.home.client.MainServiceAsync;
import com.product.home.client.client.admin.AdminBody;

public class StudentDetails extends AdminBody {

	MainServiceAsync mainservice=GWT.create(MainService.class);
	Label lblAdmissiondatevalue = new Label("");
	Label lblCallroolnovalue = new Label("");
	Label lblDateofbirthvalue = new Label("");
	Label lblBirthpalcevalue = new Label("");
	Label lblBloodgroupvalue = new Label("");
	Label lblStatevalue = new Label("");
	Label lblCountryvalue = new Label("");
	Label lblNationalityvalue = new Label("");
	Label lblGendervalue = new Label("");
	Label lblPincodevalue = new Label("");
	Label lblReligionvalue = new Label("");
	Label lblAddressvalue = new Label("");
	Label lblAddress2value= new Label("");
	Label lblPhonevalue = new Label("");
	Label lblPhonevalue_1 = new Label("");
	Label lblLanguagevalue = new Label("");
	Label lblEmailvalue = new Label("");
	Label lblCoursevalue = new Label("");
	Label lblSectionvalue = new Label("");
	Label lblCityvalue = new Label("");
	
	Label lblStudentName = new Label("");
	
	JSONObject studentdatajsonobj=new JSONObject();
	
	public StudentDetails() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setWidth("100%");
		
		FlexTable mainflextable = new FlexTable();
		flexTable.setWidget(1, 0, mainflextable);
		mainflextable.setSize("800px", "");
		
		FlexTable studentflextable = new FlexTable();
		mainflextable.setWidget(0, 0, studentflextable);
		studentflextable.setWidth("795px");
		
		FlexTable pdfimagflextable = new FlexTable();
		studentflextable.setWidget(0, 0, pdfimagflextable);
		pdfimagflextable.setWidth("100%");
		
		Image image = new Image();
		pdfimagflextable.setWidget(0, 1, image);
		pdfimagflextable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		
		FlexTable flexTable_2 = new FlexTable();
		flexTable_2.setStyleName("none");
		studentflextable.setWidget(0, 0, flexTable_2);
		flexTable_2.setSize("741px", "327px");
		
		Label lblAdmissionDate = new Label("Admission Date");
		lblAdmissionDate.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(0, 0, lblAdmissionDate);
		lblAdmissionDate.setWidth("150px");
		
		
		flexTable_2.setWidget(0, 1, lblAdmissiondatevalue);
		lblAdmissiondatevalue.setWidth("200px");
		
		Label lblCity = new Label("City");
		lblCity.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(0, 2, lblCity);
		lblCity.setWidth("150px");
		
		
		flexTable_2.setWidget(0, 3, lblCityvalue);
		lblCityvalue.setWidth("200px");
		
		Label lblClassRollNo = new Label("Class Roll No");
		lblClassRollNo.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(1, 0, lblClassRollNo);
		lblClassRollNo.setWidth("150px");
		
		flexTable_2.setWidget(1, 1, lblCallroolnovalue);
		lblCallroolnovalue.setWidth("200px");
		
		Label lblDateOfBirth = new Label("Date of Birth");
		lblDateOfBirth.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(1, 2, lblDateOfBirth);
		lblDateOfBirth.setWidth("150px");
		
		
		flexTable_2.setWidget(1, 3, lblDateofbirthvalue);
		lblDateofbirthvalue.setWidth("200px");
		
		Label lblBirthPlace = new Label("Birth Place");
		lblBirthPlace.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(2, 0, lblBirthPlace);
		lblBirthPlace.setWidth("150px");
		
		
		flexTable_2.setWidget(2, 1, lblBirthpalcevalue);
		lblBirthpalcevalue.setWidth("200px");
		
		Label lblBloodGroup = new Label("Blood Group");
		lblBloodGroup.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(2, 2, lblBloodGroup);
		lblBloodGroup.setWidth("150px");
		
		
		flexTable_2.setWidget(2, 3, lblBloodgroupvalue);
		lblBloodgroupvalue.setWidth("200px");
		
		Label lblState = new Label("State");
		lblState.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(3, 0, lblState);
		lblState.setWidth("150px");
		
		
		flexTable_2.setWidget(3, 1, lblStatevalue);
		lblStatevalue.setWidth("200px");
		
		Label lblCountry = new Label("Country");
		lblCountry.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(3, 2, lblCountry);
		lblCountry.setWidth("150px");
		
		
		flexTable_2.setWidget(3, 3, lblCountryvalue);
		lblCountryvalue.setWidth("200px");
		
		Label lblNationality = new Label("Nationality");
		lblNationality.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(4, 0, lblNationality);
		lblNationality.setWidth("150px");
		
		
		flexTable_2.setWidget(4, 1, lblNationalityvalue);
		lblNationalityvalue.setWidth("200px");
		
		Label lblGender = new Label("Gender");
		lblGender.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(4, 2, lblGender);
		lblGender.setWidth("150px");
		
		
		flexTable_2.setWidget(4, 3, lblGendervalue);
		lblGendervalue.setWidth("200px");
		
		Label lblPinCode = new Label("Pin Code");
		lblPinCode.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(5, 0, lblPinCode);
		lblPinCode.setWidth("150px");
		
		
		flexTable_2.setWidget(5, 1, lblPincodevalue);
		lblPincodevalue.setWidth("200px");
		
		Label lblReligion = new Label("Religion");
		lblReligion.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(5, 2, lblReligion);
		lblReligion.setWidth("150px");
		
		
		flexTable_2.setWidget(5, 3, lblReligionvalue);
		lblReligionvalue.setWidth("200px");
		
		Label lblAddressLine = new Label("Address Line1 ");
		lblAddressLine.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(6, 0, lblAddressLine);
		lblAddressLine.setWidth("150px");
		
		
		flexTable_2.setWidget(6, 1, lblAddressvalue);
		lblAddressvalue.setSize("200px", "40px");
		
		Label lblAddressLine_1 = new Label("Address Line 2");
		lblAddressLine_1.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(6, 2, lblAddressLine_1);
		lblAddressLine_1.setWidth("150px");
		
		
		flexTable_2.setWidget(6, 3, lblAddress2value);
		lblAddress2value.setSize("200px", "40px");
		
		Label lblPhone = new Label("Phone 1");
		lblPhone.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(7, 0, lblPhone);
		lblPhone.setWidth("150px");
		
		
		flexTable_2.setWidget(7, 1, lblPhonevalue);
		lblPhonevalue.setWidth("200px");
		
		Label lblPhone_1 = new Label("Phone 2");
		lblPhone_1.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(7, 2, lblPhone_1);
		lblPhone_1.setWidth("150px");
		
		flexTable_2.setWidget(7, 3, lblPhonevalue_1);
		lblPhonevalue_1.setWidth("200px");
		
		Label lblLanguage = new Label("Language");
		lblLanguage.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(8, 0, lblLanguage);
		lblLanguage.setWidth("150px");
		
		
		flexTable_2.setWidget(8, 1, lblLanguagevalue);
		lblLanguagevalue.setWidth("200px");
		
		Label lblEmail = new Label("Email");
		lblEmail.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(8, 2, lblEmail);
		lblEmail.setWidth("150px");
		
		
		flexTable_2.setWidget(8, 3, lblEmailvalue);
		lblEmailvalue.setWidth("200px");
		
		Label lblCourse = new Label("Course");
		lblCourse.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(9, 0, lblCourse);
		lblCourse.setWidth("150px");
		
		
		flexTable_2.setWidget(9, 1, lblCoursevalue);
		lblCoursevalue.setWidth("200px");
		
		Label lblSection = new Label("Section");
		lblSection.setStyleName("subheadingfontcss");
		flexTable_2.setWidget(9, 2, lblSection);
		lblSection.setWidth("150px");
		
		
		flexTable_2.setWidget(9, 3, lblSectionvalue);
		lblSectionvalue.setWidth("200px");
		flexTable_2.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(7, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(8, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(3, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(4, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(5, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(7, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(4, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(5, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(6, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(7, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(8, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(9, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(0, 3, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(1, 3, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(2, 3, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(3, 3, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(4, 3, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(5, 3, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(6, 3, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(7, 3, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(8, 3, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(9, 3, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setVerticalAlignment(6, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setVerticalAlignment(6, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setVerticalAlignment(6, 2, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setVerticalAlignment(6, 3, HasVerticalAlignment.ALIGN_TOP);
		flexTable_2.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(2, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(6, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(8, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(9, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setHorizontalAlignment(1, 2, HasHorizontalAlignment.ALIGN_LEFT);
		studentflextable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		studentflextable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		studentflextable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainflextable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		
		
		lblStudentName.setStyleName("subheadingfontcss");
		lblStudentName.setSize("90%","35px");
		flexTable.setWidget(0, 0, lblStudentName);
		flexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		//getStudentData();
	}

	public void getStudentData()
	{
		String studentId=studentdatajsonobj.get("studentid").isString().stringValue();
		mainservice.getStudentData(studentId,new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				
				JSONValue value=JSONParser.parseStrict(result);
				JSONObject responseobj=value.isObject();
				if(responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					showData(responseobj.get("studentdata").isObject());
				}
			}
		});
	}
	
	public void showData(JSONObject jsobject)
	{
		if(jsobject.size()!=0)
		{	
			resetData();		
			lblStudentName.setText(""+jsobject.get("lname").isString().stringValue()+" "+
			jsobject.get("fname").isString().stringValue()+""+jsobject.get("mname").isString().stringValue());
			lblAdmissiondatevalue.setText(jsobject.get("admisiondate").isString().stringValue());
			lblCallroolnovalue.setText("-");
			lblDateofbirthvalue.setText(jsobject.get("dob").isString().stringValue());
			lblBirthpalcevalue.setText(jsobject.get("birthplace").isString().stringValue());
			lblBloodgroupvalue.setText(jsobject.get("bloodgroup").isString().stringValue());
			lblStatevalue.setText(jsobject.get("state").isString().stringValue());
			lblCountryvalue.setText(jsobject.get("country").isString().stringValue());
			lblNationalityvalue.setText(jsobject.get("nationality").isString().stringValue());
			lblGendervalue.setText(jsobject.get("gender").isString().stringValue());
			lblPincodevalue.setText(jsobject.get("pincode").isString().stringValue());
			lblReligionvalue.setText(jsobject.get("religion").isString().stringValue());
			lblAddressvalue.setText(jsobject.get("address1").isString().stringValue());
			lblAddress2value.setText(jsobject.get("address2").isString().stringValue());
			lblPhonevalue.setText(jsobject.get("phone1").isString().stringValue());
			lblPhonevalue_1.setText(jsobject.get("phone2").isString().stringValue());
			lblLanguagevalue.setText(jsobject.get("language").isString().stringValue());
			lblEmailvalue.setText(jsobject.get("email").isString().stringValue());
			lblCoursevalue.setText(jsobject.get("course").isString().stringValue());
			lblSectionvalue.setText(jsobject.get("section").isString().stringValue());
			lblCityvalue.setText(jsobject.get("city").isString().stringValue());
		}
	}
	
	public void resetData()
	{
		lblStudentName.setText("");
		lblAdmissiondatevalue.setText("");
		lblCallroolnovalue.setText("");
		lblDateofbirthvalue.setText("");
		lblBirthpalcevalue.setText("");
		lblBloodgroupvalue.setText("");
		lblBloodgroupvalue.setText("");
		lblStatevalue.setText("");
		lblCountryvalue.setText("");
		lblNationalityvalue.setText("");
		lblGendervalue.setText("");
		lblPincodevalue.setText("");
		lblReligionvalue.setText("");
		lblAddressvalue.setText("");
		lblAddress2value.setText("");
		lblPhonevalue.setText("");
		lblPhonevalue_1.setText("");
		lblLanguagevalue.setText("");
		lblLanguagevalue.setText("");
		lblEmailvalue.setText("");
		lblCoursevalue.setText("");
		lblSectionvalue.setText("");
		lblCityvalue.setText("");
	}

	@Override
	public void reset() {
		getStudentData();
	}
}
