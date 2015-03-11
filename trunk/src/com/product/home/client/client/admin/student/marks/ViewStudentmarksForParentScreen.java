package com.product.home.client.client.admin.student.marks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.HasDirection.Direction;
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
import com.product.home.client.Jsonkeys;
import com.product.home.client.MainService;
import com.product.home.client.MainServiceAsync;
import com.product.home.client.client.admin.AdminBody;
public class ViewStudentmarksForParentScreen extends AdminBody {

	MainServiceAsync mainservice=GWT.create(MainService.class);

	ListBox examcomboBox = new ListBox();
	HashMap<String,String> examnameAndIdhmap=new HashMap<String, String>();
	FlexTable sudentdataflextable = new FlexTable();

	HashMap<String,String> coursenameAndIdhmap=new HashMap<String, String>();
	HashMap<String,String> tempsectionnamesAndIdhmap=new HashMap<String, String>();

	FlexTable marksdataflexTable = new FlexTable();

	Label lblStudentnamevalue = new Label("");
	
	HashMap<String,JSONObject> studentdatahashmap=new HashMap<String,JSONObject>();
	
	public ViewStudentmarksForParentScreen() {

		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("100%", "492px");

		FlexTable mainflexTable = new FlexTable();
		flexTable.setWidget(0, 0, mainflexTable);
		mainflexTable.setSize("942px", "485px");

		FlexTable studentlistflexTable = new FlexTable();
		mainflexTable.setWidget(0, 0, studentlistflexTable);
		mainflexTable.getCellFormatter().setStyleName(0, 0, "addboarder");
		studentlistflexTable.setSize("222px", "370px");

		Label lblStudentname = new Label("StudentName");
		lblStudentname.setStyleName("googlevizivals");
		studentlistflexTable.setWidget(0, 0, lblStudentname);
		studentlistflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		studentlistflexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);

		ScrollPanel scrollPanel = new ScrollPanel();
		studentlistflexTable.setWidget(1, 0, scrollPanel);
		scrollPanel.setHeight("441px");


		sudentdataflextable.removeAllRows();
		scrollPanel.setWidget(sudentdataflextable);
		sudentdataflextable.setSize("100%", "36px");
		studentlistflexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		studentlistflexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainflexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);

		FlexTable studentmarksflexTable = new FlexTable();
		mainflexTable.setWidget(0, 1, studentmarksflexTable);
		mainflexTable.getCellFormatter().setStyleName(0, 1, "addboarder");
		studentmarksflexTable.setSize("582px", "473px");

		FlexTable flexTable_1 = new FlexTable();
		studentmarksflexTable.setWidget(0, 0, flexTable_1);
		flexTable_1.setWidth("598px");
		flexTable_1.setWidget(0, 0, lblStudentnamevalue);

		lblStudentnamevalue.setText("");
		lblStudentnamevalue.setStyleName("subheadingfontcss");
		lblStudentnamevalue.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		lblStudentnamevalue.setDirection(Direction.LTR);
		lblStudentnamevalue.setWidth("233px");
		flexTable_1.setWidget(0, 1, examcomboBox);
		examcomboBox.setWidth("203px");

		examcomboBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				if(examcomboBox.getSelectedIndex()==0)
				{
					marksdataflexTable.removeAllRows();
				}
				else
				{
					if(studentdatahashmap.size()==0)
					{
						Window.alert("Please Select Student Name");
						marksdataflexTable.removeAllRows();
					}
					else
					{
						if(!studentdatahashmap.containsKey(lblStudentnamevalue.getText().trim()))
						{
							Window.alert("Please Select Student Name");
							marksdataflexTable.removeAllRows();
						}
						else
						{
							JSONObject dataobj=studentdatahashmap.get(lblStudentnamevalue.getText().trim());
							showStudentData(dataobj);
						}
					}
				}
			}
		});
		studentmarksflexTable.getCellFormatter().setStyleName(1, 0, "none");
		studentmarksflexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		studentmarksflexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_BOTTOM);
		studentmarksflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);

		ScrollPanel studentscrollPanel = new ScrollPanel();
		studentmarksflexTable.setWidget(1, 0, studentscrollPanel);
		studentscrollPanel.setSize("655px", "422px");


		studentscrollPanel.setWidget(marksdataflexTable);
		marksdataflexTable.setSize("100%", "56px");
		mainflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		mainflexTable.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
		mainflexTable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}

	public void getExamNames()
	{
		mainservice.getExamNames(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {

				JSONObject response=JSONParser.parseStrict(result).isObject();
				examcomboBox.clear();
				examnameAndIdhmap.clear();
				if(response!=null && response.size()!=0 && response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					examcomboBox.addItem("Select ExamName");
					JSONArray examsarray=response.get("exams").isArray();
					if(examsarray.size()!=0)
					{
						for(int i=0;i<examsarray.size();i++)
						{
							JSONObject examdate=examsarray.get(i).isObject();
							examcomboBox.addItem(examdate.get("examname").isString().stringValue());
							examnameAndIdhmap.put(examdate.get("examname").isString().stringValue(), examdate.get("examid").isString().stringValue());
						}
						examcomboBox.setSelectedIndex(1);
					}
				}
			}
		});
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
		studentdatahashmap.clear();
		sudentdataflextable.removeAllRows();
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
				
				final JSONObject studentdataobj=new JSONObject();
				studentdataobj.put("studentid", new JSONString(studentsobj.get("studentid").isString().stringValue()));
				studentdataobj.put("coursename", new JSONString(studentsobj.get("course").isString().stringValue()));
				studentdataobj.put("section", new JSONString(studentsobj.get("section").isString().stringValue()));
				studentdataobj.put("parentid", new JSONString(studentsobj.get("parentid").isString().stringValue()));
				lblStudentNamevalue.getElement().setId(""+studentdataobj);
				
				lblStudentNamevalue.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						lblStudentnamevalue.setText(""+lblStudentNamevalue.getText());
						studentdatahashmap.clear();
						JSONObject studentdata=JSONParser.parseStrict(lblStudentNamevalue.getElement().getId()).isObject();
						studentdatahashmap.put(lblStudentNamevalue.getText().trim(), studentdata);
						showStudentData(studentdata);
					}
				});
				
				sudentdataflextable.setWidget(i, 0,lblStudentNamevalue);
				sudentdataflextable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
			}
		}
		else
		{
			studentdatahashmap.clear();
			Label recordsnotfoundalert=new Label();
			recordsnotfoundalert.setText("Sorry Records Not Found");
			recordsnotfoundalert.setStyleName("subheadingfontcss");
			sudentdataflextable.setWidget(0, 0,recordsnotfoundalert);
			sudentdataflextable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		}
	}

	public void showStudentData(JSONObject studentdata)
	{

		JSONObject getmarksdata=new JSONObject();
		getmarksdata.put("courseid", new JSONString(coursenameAndIdhmap.get(studentdata.get("coursename").isString().stringValue())));
		getmarksdata.put("sectionid", new JSONString(tempsectionnamesAndIdhmap.get(studentdata.get("section").isString().stringValue())));
		getmarksdata.put("examid", new JSONString(examnameAndIdhmap.get(examcomboBox.getValue(examcomboBox.getSelectedIndex()))));

		getmarksdata.put("studentid", new JSONString("all"));
		if(Cookies.getCookie("rid").equals("3"))
		{
			getmarksdata.put("studentid", new JSONString(Cookies.getCookie("stid")));
		}

		getMarksReport(getmarksdata);
	}
	
	
	
	public void getMarksReport(JSONObject jsonobject)
	{
		mainservice.getMarksReport(jsonobject.toString(),new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {

				JSONObject response=JSONParser.parseStrict(result).isObject();
				if(response!=null && response.size()!=0 && response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					showMarkReport(response.get("subjects").isObject(),response.get("marks").isArray());
				}
			}
		});
	}
	
	public void showMarkReport(JSONObject subjectsobj,JSONArray marksarray)
	{
		marksdataflexTable.removeAllRows();
		if(subjectsobj.size()!=0)
		{
			Label lblStudentName = new Label("Student Name");
			lblStudentName.setStyleName("subheadingfontcss");
			marksdataflexTable.setWidget(0, 0, lblStudentName);

			Set<String>  set=subjectsobj.keySet();
			Iterator<String> it=set.iterator();
			int i=1;
			while(it.hasNext())
			{
				String subjectName=it.next();
				Label lblsubjectname = new Label(subjectName);
				lblsubjectname.setStyleName("subheadingfontcss");
				marksdataflexTable.setWidget(0, i, lblsubjectname);
				i++;
			}

			if(marksarray.size()!=0)
			{
				for(int j=0;j<marksarray.size();j++)
				{
					JSONObject studentobject=marksarray.get(j).isObject();
					String fullname=studentobject.get("lname").isString().stringValue()+" "+studentobject.get("fname").isString().stringValue()+" "+studentobject.get("mname").isString().stringValue();

					Label lblStudentNamedata = new Label(fullname);
					lblStudentName.setStyleName("subheadingfontcss");
					marksdataflexTable.setWidget(j+1,0, lblStudentNamedata);

					JSONArray markdataarray=studentobject.get("marksdata").isArray();
					if(markdataarray.size()!=0)
					{
						for(int z=0;z<markdataarray.size();z++)
						{
							JSONObject subjectobj=markdataarray.get(z).isObject();
							Label subjectmarks = new Label(subjectobj.get("marks").isString().stringValue());
							marksdataflexTable.setWidget(j+1,z+1,subjectmarks);
						}
					}
				}
			}
		}	
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
				coursenameAndIdhmap.clear();
				if(responseobj.size()!=0 && responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray coursesarray=responseobj.get("courses").isArray();
					if(coursesarray.size()!=0)
					{
						for(int i=0;i<coursesarray.size();i++)
						{
							JSONObject coursedetails=coursesarray.get(i).isObject();
							coursenameAndIdhmap.put(coursedetails.get("coursename").isString().stringValue(), coursedetails.get("courseid").isString().stringValue());
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

	public void getSections()
	{
		mainservice.getSections(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String jsonobj) {
				JSONValue value=JSONParser.parseStrict(jsonobj);
				JSONObject responseobj=value.isObject();
				tempsectionnamesAndIdhmap.clear();
				if(responseobj.size()!=0 && responseobj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					JSONArray sectiondetailsarry=responseobj.get("sections").isArray();
					for(int i=0;i<sectiondetailsarry.size();i++)
					{
						JSONObject sectionjsonobj=sectiondetailsarry.get(i).isObject();
						tempsectionnamesAndIdhmap.put(sectionjsonobj.get("sectionname").isString().stringValue(), sectionjsonobj.get("sectionid").isString().stringValue());
					}
				}
			}
		});
	}

	@Override
	public void reset() {

		getCourses();
		getSections();
		getExamNames();
		getSudentInformation();
		sudentdataflextable.removeAllRows();
	}
}
