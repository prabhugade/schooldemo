package com.product.home.client.client;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.product.home.client.client.admin.attendance.ConstantsVariables;
import com.google.gwt.user.client.ui.Label;

public class HomeBody extends Composite {

	public static Image imagesiliding = new Image("images/image1.png");
	int count=1;
	HTML contentlable = new HTML();
	
	HTML lblSchoolname = new HTML();
	public HomeBody() 
	{

		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("100%", "100%");

		FlexTable mainflextable = new FlexTable();
		flexTable.setWidget(0, 0, mainflextable);
		flexTable.getCellFormatter().setStyleName(0, 0, "showpopupcss");
		mainflextable.setWidth("100%");

		FlexTable imageflxtable = new FlexTable();
		mainflextable.setWidget(0, 0, imageflxtable);
		imageflxtable.setSize("600px", "300px");
		imagesiliding.setStyleName((String) null);


		imageflxtable.setWidget(0, 0, imagesiliding);
		imagesiliding.setSize("600px", "300px");
		imageflxtable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		mainflextable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		if(Cookies.getCookie("schoolname")!=null)
		{
			lblSchoolname.setHTML("<H4>"+Cookies.getCookie("schoolname")+"</H4>");
		}
		else
		{
			lblSchoolname.setHTML("<H4>"+Cookies.getCookie("Welcome rasterr")+"</H4>");
		}
		flexTable.setWidget(1, 0, lblSchoolname);
		lblSchoolname.setHeight("19px");

		contentlable.setHTML(ConstantsVariables.content);
		flexTable.setWidget(2, 0, contentlable);
		contentlable.setSize("100%", "100%");
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		
		siliding();
	}

	public void siliding()
	{
		Timer t = new Timer() {
			@Override
			public void run() {
				slidingImages();
			}
		};
		// Schedule the timer to run once in 5 seconds.
		t.scheduleRepeating(8000);
	}
	
	public void slidingImages()
	{
		
		if(count==1)
		{
			imagesiliding.setUrl("images/image1.png");
		}
		else if(count==2)
		{
			imagesiliding.setUrl("images/image2.png");
		}
		else if(count==3)
		{
			imagesiliding.setUrl("images/image3.png");
		}
		else if(count==4)
		{
			imagesiliding.setUrl("images/image4.png");
		}
		
		if(count==4)
		{
			count=1;
		}
		else
		{
			count++;
		}
	}
}
