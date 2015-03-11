package com.product.home.client.client.admin;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.product.home.client.client.HomeFooter;
import com.product.home.client.client.HomeMain;
import com.product.home.client.client.LoginHeader;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

public class HomeMainAdmin extends HomeMain 
{
	FlexTable flexTable=new FlexTable();
	public HomeMainAdmin() 
	{
		initWidget(flexTable);
		flexTable.setSize("100%", "100%");

		FlexTable mainflexTable = new FlexTable();
		
		flexTable.setWidget(0,0,mainflexTable);
		mainflexTable.setWidth("87%");
		
		//HomeHeader homeHeader = new HomeHeader();
		LoginHeader loginheader=new LoginHeader();
		mainflexTable.setWidget(0,0,loginheader);
		mainflexTable.getCellFormatter().setHeight(0, 0, "10%");
		mainflexTable.getCellFormatter().setWidth(0, 0, "100%");
		
		
		AdminMenu adminmenu=new AdminMenu();
		adminmenu.setSize("100%", "750px");
		mainflexTable.setWidget(1,0,adminmenu);
		mainflexTable.getCellFormatter().setHeight(1, 0, "80%");
		mainflexTable.getCellFormatter().setWidth(1, 0, "100%");
		loginheader.setSize("100%", "100%");
		
		HomeFooter homeFooter = new HomeFooter();
		mainflexTable.setWidget(2,0,homeFooter);
		mainflexTable.getCellFormatter().setStyleName(2, 0, "main");
		mainflexTable.getCellFormatter().setHeight(2, 0, "10%");
		mainflexTable.getCellFormatter().setWidth(2, 0, "100%");
		homeFooter.setSize("100%", "100%");
		
		
		mainflexTable.getCellFormatter().setVerticalAlignment(2, 0, HasVerticalAlignment.ALIGN_BOTTOM);
		mainflexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		mainflexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
	}

}
