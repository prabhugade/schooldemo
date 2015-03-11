package com.product.home.client.client.parent;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.product.home.client.client.HomeFooter;
import com.product.home.client.client.HomeMain;
import com.product.home.client.client.LoginHeader;

public class HomeMainParent extends HomeMain {
	VerticalPanel vpanel=new VerticalPanel();
	public HomeMainParent() {
		
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
		ParentMenu parentmenu=new ParentMenu();
		parentmenu.setSize("100%", "20px");
		verticalPanel.add(parentmenu);
		loginheader.setSize("100%", "15%");
		
		HomeFooter homeFooter = new HomeFooter();
		verticalPanel.add(homeFooter);
		homeFooter.setSize("100%", "5%");
		
	}

}
