package com.product.home.client.demo;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DemoMainHome extends Composite {

	VerticalPanel vpanel=new VerticalPanel();
	public DemoMainHome() 
	{
		vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(vpanel);
		vpanel.setSize("100%", "100%");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vpanel.add(verticalPanel);
		verticalPanel.setWidth("75%");
		HomeHeader homeHeader = new HomeHeader();
		verticalPanel.add(homeHeader);
		homeHeader.setSize("100%", "15%");
		HomeBody homeBody = new HomeBody();
		verticalPanel.add(homeBody);
		homeBody.setSize("100%", "70%");
		HomeFooter homeFooter = new HomeFooter();
		verticalPanel.add(homeFooter);
		homeFooter.setSize("100%", "5%");
		
		
	}

}
