package com.product.home.client.client.teacher;

import java.util.HashMap;

import com.github.gwtbootstrap.client.ui.Dropdown;
import com.github.gwtbootstrap.client.ui.Nav;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.Navbar;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.product.home.client.ChangePassword;
import com.product.home.client.client.admin.AdminBody;
import com.product.home.client.client.admin.attendance.StudentAttendancePage;
import com.product.home.client.client.admin.employee.EmployeeList;
import com.product.home.client.client.admin.student.ShowStudentInformation;
import com.product.home.client.client.admin.student.StudentDetails;
import com.product.home.client.client.admin.student.marks.CreateExamsNamesScreen;
import com.product.home.client.client.admin.student.marks.EnterMarksScreen;
import com.product.home.client.client.admin.student.marks.ViewMarksDetails;
import com.product.home.client.client.mailbox.MailBoxScreen;

public class TeacherMenu extends Composite {

	AdminBody body;
	VerticalPanel vpanel=new VerticalPanel();
	HashMap<String, AdminBody> menu=new HashMap<String, AdminBody>();
	public TeacherMenu() 
	{
		History.addValueChangeHandler(new HistoryHandler());
		Navbar navbar=new Navbar();
		navbar.setStyleName("navbar navbar-inverse navbar-fixed-static");
		
		initWidget(vpanel);
		vpanel.setSize("100%","100%");
		vpanel.add(navbar);

		
		//===========for home
		Nav navmailMenu=new Nav();
		navmailMenu.add(mailMenu());
		navbar.add(navmailMenu);
		
		//===========for student
		Nav navstudentMenu=new Nav();
		navstudentMenu.add(studentMenu());
		navbar.add(navstudentMenu);
		
		
		//===========for Employees
		Nav navemployeeMenu=new Nav();
		navemployeeMenu.add(employeeMenu());
		navbar.add(navemployeeMenu);
		
		
		//===========for Examination
		Nav navexamsMenu=new Nav();
		navexamsMenu.add(examsMenu());
		navbar.add(navexamsMenu);
		
		//===========for Attendance
		Nav navattendenceMenu=new Nav();
		navattendenceMenu.add(attendenceMenu());
		navbar.add(navattendenceMenu);
		
		//===========for TimeTable
		Nav navtimetableMenu=new Nav();
		navtimetableMenu.add(timetableMenu());
		navbar.add(navtimetableMenu);
			
		/*
		//===========for Reports
		Nav navreportsMenu=new Nav();
		navreportsMenu.add(reportsMenu());mainpanel
		navbar.add(navreportsMenu);
		*/
		//===========for Settings
		Nav navsettingsMenu=new Nav();
		navsettingsMenu.add(settingsMenu());
		navbar.add(navsettingsMenu);
		
		
		if(History.getToken().length()!=0)
		{
			if(menu.containsKey(History.getToken())||History.getToken().contains("studetails"))
			{
				if(History.getToken().contains("studetails"))
				{
					body=menu.get("studetails");
				}
				else
				{
					body=menu.get(History.getToken());
				}
				vpanel.add(body);
				body.reset();
			}
		}else
		{
			body=menu.get("stulist");
			vpanel.add(body);
		}
	}
	private class HistoryHandler implements ValueChangeHandler<String>
	{
		@Override
		public void onValueChange(ValueChangeEvent<String> event)
		{
			String target=event.getValue();
			if(menu.containsKey(target)||target.contains("studetails"))
			{
				vpanel.remove(1);
				if(target.contains("studetails"))
				{
					body=menu.get("studetails");
				}
				else
				{
					body=menu.get(target);
				}
				vpanel.add(body);
				body.reset();
			}
		}
	}
	public Dropdown employeeMenu()
	{
		Dropdown button=new Dropdown();
		button.setIcon(IconType.USER_MD);
		button.setText("Employees");
		
		/*NavLink createstudentLink=new NavLink("Create Employee");
		createstudentLink.setHref("#createemp");
		menu.put("createemp", new CreateEmployeeDetails());
		button.add(createstudentLink);*/
		
		NavLink employeelist=new NavLink("Employee List");
		employeelist.setHref("#emplist");
		menu.put("emplist", new EmployeeList());
		button.add(employeelist);
		
		return button;
	}
	public Dropdown mailMenu()
	{
		Dropdown button=new Dropdown();
		button.setIcon(IconType.HOME);
		button.setText("Home");
		
		NavLink mailLink=new NavLink("MailBox");
		button.add(mailLink);
		mailLink.setHref("#mail");
		menu.put("mail", new MailBoxScreen());
		
		return button;
	}
	public Dropdown studentMenu()
	{
		Dropdown button=new Dropdown();
		button.setIcon(IconType.USER);
		button.setText("Students");
		
		NavLink studentsListLink=new NavLink("Students List");
		studentsListLink.setHref("#stulist");
		menu.put("stulist", new ShowStudentInformation());
		button.add(studentsListLink);
		
		menu.put("studetails",new StudentDetails());
		
		return button;
	}
	
	public Dropdown examsMenu()
	{
		Dropdown button=new Dropdown();
		button.setIcon(IconType.EDIT);
		button.setText("Examination");
		
		NavLink createExamsLink=new NavLink("Create Exams");
		createExamsLink.setHref("#createexam");
		menu.put("createexam", new CreateExamsNamesScreen());
		button.add(createExamsLink);
		
		NavLink addMarksLink=new NavLink("Add Marks");
		addMarksLink.setHref("#addmarks");
		menu.put("addmarks", new EnterMarksScreen());
		button.add(addMarksLink);
		
		NavLink viewmqarksLink=new NavLink("View Marks");
		viewmqarksLink.setHref("#viewmarks");
		menu.put("viewmarks", new ViewMarksDetails());
		button.add(viewmqarksLink);
		
		return button;
	}
	public Dropdown attendenceMenu()
	{
		Dropdown button=new Dropdown();
		button.setIcon(IconType.CALENDAR);
		button.setText("Attendance");
		
		NavLink studentAttendanceLink=new NavLink("Student Attendance");
		studentAttendanceLink.setHref("#stuattendance");
		menu.put("stuattendance", new StudentAttendancePage());
		button.add(studentAttendanceLink);
		
		return button;
	}
	public Dropdown timetableMenu()
	{
		Dropdown button=new Dropdown();
		button.setIcon(IconType.TIME);
		button.setText("TimeTable");
		return button;
	}
	
	public Dropdown reportsMenu()
	{
		Dropdown button=new Dropdown();
		button.setIcon(IconType.FILE);
		button.setText("Reports");
		return button;
	}
	public Dropdown settingsMenu()
	{
		Dropdown button=new Dropdown();
		button.setIcon(IconType.COG);
		button.setText("Settings");
		
		NavLink changepasswordLink=new NavLink("Change Password");
		changepasswordLink.setHref("#changepass");
		menu.put("changepass", new ChangePassword());
		button.add(changepasswordLink);
		
		return button;
	}
}
