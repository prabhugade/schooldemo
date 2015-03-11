package com.product.home.client.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;


public class ClientMainHome extends Composite 
{

	public static VerticalPanel vpanel=new VerticalPanel();
	public ClientMainHome() 
	{
		vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(vpanel);
		vpanel.setSize("100%", "100%");
		vpanel.clear();
		VerticalPanel verticalPanel = new VerticalPanel(); 
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vpanel.add(verticalPanel);
		vpanel.setCellHeight(verticalPanel, "100%");
		vpanel.setCellWidth(verticalPanel, "100%");
		verticalPanel.setWidth("75%");
		HomeHeader homeHeader = new HomeHeader();
		verticalPanel.add(homeHeader);
		verticalPanel.setCellHeight(homeHeader, "10%");
		verticalPanel.setCellWidth(homeHeader, "100%");
		homeHeader.setSize("100%", "15%");
		HomeBody homeBody = new HomeBody();
		verticalPanel.add(homeBody);
		verticalPanel.setCellHeight(homeBody, "80%");
		verticalPanel.setCellWidth(homeBody, "100%");
		homeBody.setSize("100%", "750px");
		HomeFooter homeFooter = new HomeFooter();
		verticalPanel.add(homeFooter);
		verticalPanel.setCellHeight(homeFooter, "10%");
		verticalPanel.setCellWidth(homeFooter, "100%");
		homeFooter.setSize("100%", "10%");
		
		
	}

}
