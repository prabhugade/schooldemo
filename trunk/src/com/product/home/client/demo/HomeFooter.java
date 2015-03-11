package com.product.home.client.demo;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;

public class HomeFooter extends Composite {

	public HomeFooter() 
	{
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(verticalPanel);
		verticalPanel.setSize("100%", "100%");
		
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		verticalPanel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel_1.setStyleName("footer");
		verticalPanel.add(verticalPanel_1);
		verticalPanel_1.setWidth("100%");
		
		Label lblNewLabel = new Label("\u00A9 2013 Prabhu. All Rights Reserved.");
		verticalPanel_1.add(lblNewLabel);
		
	}

}
