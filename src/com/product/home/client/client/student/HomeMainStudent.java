package com.product.home.client.client.student;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.product.home.client.client.HomeFooter;
import com.product.home.client.client.HomeMain;
import com.product.home.client.client.LoginHeader;
import com.product.home.client.client.admin.AdminMenu;

public class HomeMainStudent extends HomeMain {
	VerticalPanel vpanel=new VerticalPanel();
	public HomeMainStudent() {
		
		vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(vpanel);
		vpanel.setSize("100%", "100%");

		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vpanel.add(verticalPanel);
		verticalPanel.setWidth("87%");
		//HomeHeader homeHeader = new HomeHeader();
		LoginHeader loginheader=new LoginHeader();
		verticalPanel.add(loginheader);
		StudentMenu studentmenu=new StudentMenu();
		studentmenu.setSize("100%", "20px");
		verticalPanel.add(studentmenu);
		loginheader.setSize("100%", "15%");
		
		HomeFooter homeFooter = new HomeFooter();
		verticalPanel.add(homeFooter);
		homeFooter.setSize("100%", "5%");
		
	}

}
