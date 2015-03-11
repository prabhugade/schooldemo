package com.product.home.client.demo;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.product.home.client.LoginForm;

public class HomeHeader extends Composite 
{
	VerticalPanel mainpanel=new VerticalPanel();
	PopupPanel popup;
	Label lblDemoLogin = new Label("Demo Login");
	public HomeHeader() 
	{
		initWidget(mainpanel);
		mainpanel.setWidth("100%");
		mainpanel.setStyleName("header");
		
		HorizontalPanel linkshorizontalPanel = new HorizontalPanel();
		linkshorizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		mainpanel.add(linkshorizontalPanel);
		mainpanel.setCellHorizontalAlignment(linkshorizontalPanel, HasHorizontalAlignment.ALIGN_RIGHT);
		linkshorizontalPanel.setWidth("20%");
		
		
		lblDemoLogin.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				popup=new PopupPanel();
				popup.add(new LoginForm());
				popup.setAutoHideEnabled(true);
				popup.setPopupPositionAndShow(new PopupPanel.PositionCallback(){
		               public void setPosition(int offsetWidth, int offsetHeight) {
		            	   popup.showRelativeTo(lblDemoLogin);
		               }
		            });
				popup.show();
			}
		});
		lblDemoLogin.setWordWrap(false);
		lblDemoLogin.setStyleName("hyperlink");
		linkshorizontalPanel.add(lblDemoLogin);
		
		Label lblClientLogin = new Label("Client Login");
		lblClientLogin.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				popup=new PopupPanel();
				popup.add(new LoginForm());
				popup.setAutoHideEnabled(true);
				popup.setPopupPositionAndShow(new PopupPanel.PositionCallback(){
		               public void setPosition(int offsetWidth, int offsetHeight) {
		            	   popup.showRelativeTo(lblDemoLogin);
		               }
		            });
				popup.show();
			}
		});
		lblClientLogin.setWordWrap(false);
		lblClientLogin.setStyleName("hyperlink");
		linkshorizontalPanel.add(lblClientLogin);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		mainpanel.add(horizontalPanel);
		horizontalPanel.setWidth("100%");
		
		Image image = new Image("images/logo.jpg");
		horizontalPanel.add(image);
		image.setHeight("49px");
		
		HorizontalPanel menuhorizontalPanel = new HorizontalPanel();
		menuhorizontalPanel.setSpacing(5);
		horizontalPanel.add(menuhorizontalPanel);
		menuhorizontalPanel.setHeight("100%");
		horizontalPanel.setCellHorizontalAlignment(menuhorizontalPanel, HasHorizontalAlignment.ALIGN_RIGHT);
		
		Button btnNewButton = new Button("New button");
		btnNewButton.setText("About");
		menuhorizontalPanel.add(btnNewButton);
		btnNewButton.setWidth("100px");
		
		Button btnNewButton_1 = new Button("New button");
		btnNewButton_1.setText("Buy");
		menuhorizontalPanel.add(btnNewButton_1);
		btnNewButton_1.setWidth("100px");
		
		Button btnNewButton_2 = new Button("New button");
		btnNewButton_2.setText("Contact");
		menuhorizontalPanel.add(btnNewButton_2);
		btnNewButton_2.setWidth("100px");
	}
}
