package com.product.home.client.client;

import com.github.gwtbootstrap.client.ui.Close;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.NavHeader;
import com.github.gwtbootstrap.client.ui.constants.BackdropType;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.product.home.client.LoginForm;
import com.google.gwt.i18n.client.HasDirection.Direction;

public class HomeHeader extends Composite 
{
	FlexTable mainpanel=new FlexTable();
	public static PopupPanel popup;
	Label lblClientLogin = new Label("Client Login");
	public static Modal loginformpopup;
	Image image = new Image("images/logo.png");
	
	public HomeHeader() 
	{
		initWidget(mainpanel);
		mainpanel.setWidth("100%");
		mainpanel.setStyleName("main");
		
		FlexTable linkshorizontalPanel = new FlexTable();
		mainpanel.setWidget(0, 0,linkshorizontalPanel);
		linkshorizontalPanel.setWidth("100%");
		
		if(Cookies.getCookie("image")!=null)
		{
			image.setUrl(Cookies.getCookie("image"));
		}
		
		image.addErrorHandler(new ErrorHandler() {
			
			@Override
			public void onError(ErrorEvent event) {
				
				Cookies.setCookie("image", "images/logo.png");
				image.setUrl(Cookies.getCookie("image"));
			}
		});
		
		
		
		/*lblClientLogin.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				popup=new PopupPanel();
				popup.add(new LoginForm());
				popup.setAutoHideEnabled(true);
				popup.setPopupPositionAndShow(new PopupPanel.PositionCallback()
				{
		               public void setPosition(int offsetWidth, int offsetHeight) 
		               {
		            	   popup.showRelativeTo(lblClientLogin);
		               }
		            });
				popup.show();
			}
		});*/
		lblClientLogin.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		lblClientLogin.setDirection(Direction.RTL);
		lblClientLogin.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				loginformpopup=new Modal();
				loginformpopup.setWidth(400);
				Close close=new Close();
				loginformpopup.add(close);
				
				close.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						loginformpopup.hide();
					}
				});
				
				NavHeader sectionheader=new NavHeader("Login");
				loginformpopup.add(sectionheader);
				LoginForm loginform=new LoginForm();
				FlexTable loginfletable=new FlexTable();
				loginfletable.setWidth("99%");
				loginfletable.setWidget(0, 0,loginform);
				loginfletable.getCellFormatter().setHorizontalAlignment(0, 0,HasHorizontalAlignment.ALIGN_CENTER);
				loginformpopup.add(loginfletable);
				loginformpopup.setBackdrop(BackdropType.STATIC);
				loginformpopup.show();
				loginform.usernametextBox.setFocus(true);
			}
		});
		
		linkshorizontalPanel.setWidget(0, 0, image);
		linkshorizontalPanel.getCellFormatter().setHeight(0, 0, "90px");
		image.setSize("150px", "80px");
		
		lblClientLogin.setWordWrap(false);
		lblClientLogin.setStyleName("login");
		linkshorizontalPanel.setWidget(0, 1,lblClientLogin);
		lblClientLogin.setWidth("95%");
		linkshorizontalPanel.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		linkshorizontalPanel.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		linkshorizontalPanel.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		linkshorizontalPanel.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		mainpanel.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		
	
	}
}
