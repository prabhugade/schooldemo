package com.product.home.client.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

public class HomeFooter extends Composite {

	public HomeFooter() 
	{
		
		FlexTable flextatble = new FlexTable();
		initWidget(flextatble);
		flextatble.setSize("100%", "100px");
			
		Label lblNewLabel = new Label("\u00A9 2013 . All Rights Reserved. Version:1.1");
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flextatble.setWidget(0,0,lblNewLabel);
		lblNewLabel.setHeight("50px");
		flextatble.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		flextatble.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}
}
