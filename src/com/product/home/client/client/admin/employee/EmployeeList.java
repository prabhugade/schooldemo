package com.product.home.client.client.admin.employee;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.product.home.client.Jsonkeys;
import com.product.home.client.MainService;
import com.product.home.client.MainServiceAsync;
import com.product.home.client.client.LoginHeader;
import com.product.home.client.client.admin.AdminBody;

public class EmployeeList extends AdminBody {

	 MainServiceAsync mainservice=GWT.create(MainService.class); 
	
	Label employeenameheadervalue = new Label("New label");
	Label Employeenamevaluee = new Label();
	Label employeegendervalue = new Label();
	Label phonenumbervalue = new Label();
	Label employeedesignationvalue = new Label();
	Label employeenationalityvalue = new Label();
	Label employeeexperincevalue = new Label();
	Label addressvalue = new Label();
	
	
	FlexTable dataflextable = new FlexTable();
	FlexTable showemployeedataflextable = new FlexTable();
	public EmployeeList() {

		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setWidth("100%");

		FlexTable mainflexTable = new FlexTable();
		flexTable.setWidget(0, 0, mainflexTable);
		flexTable.getCellFormatter().setStyleName(0, 0, "addboarder");
		mainflexTable.setSize("99%", "482px");

		Label lblNewLabel = new Label("Employee Names");
		lblNewLabel.setStyleName("googlevizivals");
		mainflexTable.setWidget(0, 0, lblNewLabel);
		lblNewLabel.setHeight("27px");

		ScrollPanel scrollPanel = new ScrollPanel();
		mainflexTable.setWidget(1, 0, scrollPanel);
		scrollPanel.setSize("99%", "449px");

		scrollPanel.setWidget(dataflextable);
		dataflextable.setSize("100%", "44px");

		mainflexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		mainflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainflexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);

		FlexTable flexTable_2 = new FlexTable();
		flexTable.setWidget(0, 1, flexTable_2);
		flexTable.getCellFormatter().setStyleName(0, 1, "addboarder");
		flexTable_2.setSize("611px", "486px");

		employeenameheadervalue.setStyleName("greebsubheading");
		employeenameheadervalue.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.setWidget(0, 0, employeenameheadervalue);
		employeenameheadervalue.setWidth("590px");

		
		flexTable_2.setWidget(1, 0, showemployeedataflextable);
		showemployeedataflextable.setSize("561px", "400px");

		Label lblNewLabel_1 = new Label("Name :");
		lblNewLabel_1.setStyleName("heardrolecss");
		showemployeedataflextable.setWidget(0, 0, lblNewLabel_1);
		lblNewLabel_1.setWidth("100px");

		showemployeedataflextable.setWidget(0, 1, Employeenamevaluee);
		Employeenamevaluee.setWidth("250px");

		Label lblNewLabel_6 = new Label("");
		showemployeedataflextable.setWidget(0, 2, lblNewLabel_6);

		Label lblNewLabel_2 = new Label("Gender :");
		lblNewLabel_2.setStyleName("heardrolecss");
		showemployeedataflextable.setWidget(1, 0, lblNewLabel_2);
		lblNewLabel_2.setWidth("100px");

		showemployeedataflextable.setWidget(1, 1, employeegendervalue);
		employeegendervalue.setWidth("250px");

		Label lblNewLabel_8 = new Label("Phone Number :");
		lblNewLabel_8.setStyleName("heardrolecss");
		showemployeedataflextable.setWidget(2, 0, lblNewLabel_8);
		lblNewLabel_8.setWidth("120px");

		showemployeedataflextable.setWidget(2, 1, phonenumbervalue);
		phonenumbervalue.setWidth("250px");

		Label lblNewLabel_3 = new Label("Designation :");
		lblNewLabel_3.setStyleName("heardrolecss");
		showemployeedataflextable.setWidget(3, 0, lblNewLabel_3);
		lblNewLabel_3.setWidth("100px");

		showemployeedataflextable.setWidget(3, 1, employeedesignationvalue);
		employeedesignationvalue.setWidth("250px");

		Label lblNewLabel_4 = new Label("\t\nNationality :");
		lblNewLabel_4.setStyleName("heardrolecss");
		showemployeedataflextable.setWidget(4, 0, lblNewLabel_4);
		lblNewLabel_4.setWidth("100px");

		showemployeedataflextable.setWidget(4, 1, employeenationalityvalue);
		employeenationalityvalue.setWidth("250px");

		Label lblNewLabel_5 = new Label("\t\nExperience Detail :");
		lblNewLabel_5.setStyleName("heardrolecss");
		showemployeedataflextable.setWidget(5, 0, lblNewLabel_5);
		lblNewLabel_5.setWidth("120px");

		showemployeedataflextable.setWidget(5, 1, employeeexperincevalue);
		employeeexperincevalue.setWidth("250px");

		Label lblNewLabel_7 = new Label("Address :");
		lblNewLabel_7.setStyleName("heardrolecss");
		showemployeedataflextable.setWidget(6, 0, lblNewLabel_7);
		lblNewLabel_7.setWidth("100px");
		showemployeedataflextable.setVisible(false);
		showemployeedataflextable.setWidget(6, 1, addressvalue);
		addressvalue.setWidth("250px");
		showemployeedataflextable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		showemployeedataflextable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_LEFT);
		showemployeedataflextable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT);
		showemployeedataflextable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_LEFT);
		showemployeedataflextable.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_LEFT);
		showemployeedataflextable.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_LEFT);
		showemployeedataflextable.getCellFormatter().setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable_2.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		flexTable_2.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable_2.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}
	
	public void  getEmplooyees_Adminsters()
	{
		if(Cookies.getCookie("rid").equals(LoginHeader.ADMIN_ROLEID))
		{
			JSONObject jsonobj=new JSONObject();
			jsonobj.put("type",new JSONString("0"));

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
						getEmployessNames(dataarray);
					}
				}
			});
		}
		else
		{
			JSONArray dataarray=new JSONArray();
			JSONObject dataobj=new JSONObject();
			dataobj.put("name", new JSONString(Cookies.getCookie("username")));
			dataobj.put("id", new JSONString(Cookies.getCookie("stid")));
			dataarray.set(0,dataobj);
			getEmployessNames(dataarray);
		}
	}
	
	public void getEmployessNames(JSONArray dataarray)
	{
		dataflextable.removeAllRows();

		for(int i=0;i<dataarray.size();i++)
		{
			JSONObject dataobj=dataarray.get(i).isObject();
			
			final Label dataemployeename = new Label(dataobj.get("name").isString().stringValue());
			dataemployeename.getElement().setId(dataobj.get("id").isString().stringValue());
			dataemployeename.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
			dataemployeename.setStyleName("labelfontstyle");
			
			dataflextable.setWidget(i, 0, dataemployeename);
			dataemployeename.setSize("180px","35px");
			dataflextable.getCellFormatter().setHorizontalAlignment(i, 0, HasHorizontalAlignment.ALIGN_CENTER);
			
			dataemployeename.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {

					String employeeId=dataemployeename.getElement().getId();
					showEmployeeDetails(employeeId);
				}
			});
		}
	}
	
	
	public void showEmployeeDetails(String employeeid)
	{
		mainservice.getEmployeeIdBasedDetails(employeeid,new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(String result) {
				
				JSONObject response=JSONParser.parseStrict(result).isObject();
				if(response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONObject dataobj=response.get(Jsonkeys.employeedata).isObject();
					setEnployeeDetails(dataobj);
				}
				else
				{
					resetdata();
				}
			}
		});
		
	}

	public void setEnployeeDetails(JSONObject dataobj)
	{
		showemployeedataflextable.setVisible(true);
		resetdata();
		employeenameheadervalue.setText(dataobj.get("name").isString().stringValue());

		Employeenamevaluee.setText(dataobj.get("name").isString().stringValue());
		employeegendervalue.setText(dataobj.get("gender").isString().stringValue());
		phonenumbervalue.setText(dataobj.get("phone1").isString().stringValue());
		
		if(dataobj.get("subjectname").isString().stringValue().equalsIgnoreCase("0"))
		{
			employeedesignationvalue.setText(dataobj.get("designationname").isString().stringValue());
		}
		else
		{
			employeedesignationvalue.setText(dataobj.get("designationname").isString().stringValue()+" ( "+dataobj.get("subjectname").isString().stringValue()+" )");
		}
		
		employeenationalityvalue.setText(dataobj.get("nationality").isString().stringValue());
		employeeexperincevalue.setText(dataobj.get("employeedesc").isString().stringValue());
		addressvalue.setText(dataobj.get("address1").isString().stringValue());
	}
	
	public void resetdata()
	{
		employeenameheadervalue.setText("");
		
		Employeenamevaluee.setText("");
		employeegendervalue.setText("");
		phonenumbervalue.setText("");
		employeedesignationvalue.setText("");
		employeenationalityvalue.setText("");
		employeeexperincevalue.setText("");
		addressvalue.setText("");
	}
	
	@Override
	public void reset() {
		showemployeedataflextable.setVisible(false);
		getEmplooyees_Adminsters();
		resetdata();
	}
	
}
