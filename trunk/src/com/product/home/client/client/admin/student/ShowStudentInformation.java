package com.product.home.client.client.admin.student;


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
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.product.home.client.Jsonkeys;
import com.product.home.client.MainService;
import com.product.home.client.MainServiceAsync;
import com.product.home.client.client.admin.AdminBody;

public class ShowStudentInformation extends AdminBody {
	
	MainServiceAsync mainservice=GWT.create(MainService.class); 
	ScrollPanel studentscrollPanel = new ScrollPanel();
	
	//displaying the Student Data
	FlexTable sudentdataflextable = new FlexTable();
	
	Label[] studentname;
	
	FlexTable studentdataflextable = new FlexTable();
	public ShowStudentInformation() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("100%", "500px");
		
		FlexTable mainflextable = new FlexTable();
		flexTable.setWidget(0, 0, mainflextable);
		mainflextable.setSize("100%", "31px");
		
		FlexTable searchflextable = new FlexTable();
		mainflextable.setWidget(0, 0, searchflextable);
		searchflextable.setSize("100%", "61px");
		
		FlexTable scrollpanelflextable = new FlexTable();
		scrollpanelflextable.setSize("260px", "484px");
		
		ScrollPanel studentscrollPanel_1 = new ScrollPanel();
		searchflextable.setWidget(0, 0, scrollpanelflextable);
		
		Label lblNewLabel = new Label("Student Name");
		lblNewLabel.setStyleName("googlevizivals");
		scrollpanelflextable.setWidget(0, 0, lblNewLabel);
		scrollpanelflextable.setWidget(1, 0,studentscrollPanel_1);
		
		searchflextable.getCellFormatter().setStyleName(0, 0, "addboarder");
		studentscrollPanel_1.setStyleName("none");
		studentscrollPanel_1.setSize("250px", "450px");
		sudentdataflextable.setStyleName("none");
		
		studentdataflextable.removeAllRows();
		studentscrollPanel_1.setWidget(sudentdataflextable);
		sudentdataflextable.setSize("90%", "32px");
		scrollpanelflextable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		scrollpanelflextable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		scrollpanelflextable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		scrollpanelflextable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		
		searchflextable.setWidget(0, 1, studentdataflextable);
		searchflextable.getCellFormatter().setStyleName(0, 1, "addboarder");
		studentdataflextable.setSize("800px", "474px");
		searchflextable.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
		searchflextable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		searchflextable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		searchflextable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		mainflextable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		getSudentInformation("All");
	}
	public void getSudentInformation(String searchValue)
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
		sudentdataflextable.removeAllRows();
		if(studentarray.size()!=0)
		{
			studentname=new Label[studentarray.size()];
			for(int i=0;i<studentarray.size();i++)
			{
				JSONObject studentsobj=studentarray.get(i).isObject();
				
				Label lblStudentNamevalue = new Label(studentsobj.get("lname").isString().stringValue()+" "+
						studentsobj.get("fname").isString().stringValue()+" "+
						studentsobj.get("mname").isString().stringValue().substring(0,20));

				studentname[i]=lblStudentNamevalue;
				studentname[i].getElement().setId(studentsobj.get("studentid").isString().stringValue());
				studentname[i].setStyleName("labelfontstyle");
				sudentdataflextable.setWidget(i,0, studentname[i]);
				lblStudentNamevalue.setSize("90%","35px");
			}
			
			for(int i=0;i<studentname.length;i++)
			{
				studentname[i].addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						
						Label studentname=(Label)event.getSource();
						String studentId=studentname.getElement().getId();
						StudentDetails studentdata=new StudentDetails();
						studentdataflextable.removeAllRows();
						studentdataflextable.setWidget(0,0, studentdata);
						studentdata.studentdatajsonobj=new JSONObject();
						studentdata.studentdatajsonobj.put("studentname", new JSONString(studentname.getText()));
						studentdata.studentdatajsonobj.put("studentid", new JSONString(studentId));
						studentdata.getStudentData();
					}
				});
			}
		}
		else
		{
			Label recordsnotfoundalert=new Label();
			recordsnotfoundalert.setText("Sorry Records Not Found");
			recordsnotfoundalert.setStyleName("subheadingfontcss");
			sudentdataflextable.setWidget(0, 0,recordsnotfoundalert);
			sudentdataflextable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		}
	}
	@Override
	public void reset() {
		getSudentInformation("All");
		studentdataflextable.removeAllRows();
	}
}
