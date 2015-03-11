package com.product.home.client.client;

import java.util.Collection;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;

public class LoginHeader extends Composite {

	static String name="Name";
	static Image afterloginimage = new Image("images/logo.png");
	
	public static String ADMIN_ROLEID="1";
	public static String TEACHER_ROLEID="2";
	public static String STUDENT_ROLEID="3";
	public static String PARENT_ROLEID="4";
	
	public LoginHeader() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("663px", "69px");
		
		FlexTable mainflexatble = new FlexTable();
		flexTable.setWidget(0, 0, mainflexatble);
		mainflexatble.setSize("100%", "67px");
		
		if(Cookies.getCookie("image")!=null)
		{
			afterloginimage.setUrl(Cookies.getCookie("image"));
		}
		mainflexatble.setWidget(0, 0, afterloginimage);
		afterloginimage.setSize("150px", "80px");
		
		afterloginimage.addErrorHandler(new ErrorHandler() {
			
			@Override
			public void onError(ErrorEvent event) {
				
				Cookies.setCookie("image","images/logo.png");
				afterloginimage.setUrl(Cookies.getCookie("image"));
			}
		});
		
		if(Cookies.getCookie("username")!=null)
		{
			name=Cookies.getCookie("username");
		}
		
		FlexTable dataflextable = new FlexTable();
		mainflexatble.setWidget(0, 1, dataflextable);
		dataflextable.setSize("100%", "81px");
		
		FlexTable logindetailsflextable = new FlexTable();
		dataflextable.setWidget(0, 0, logindetailsflextable);
		logindetailsflextable.setWidth("");
		
		Label lblUsername = new Label();
		lblUsername.setText("Welcome "+name+" "+Cookies.getCookie("rid").replaceAll("1","(Admin)").replaceAll("2","(Employee)").replaceAll("3","(Student)").replaceAll("4","(Parent)"));
		
		lblUsername.setStyleName("heardrolecss");
		logindetailsflextable.setWidget(0, 0, lblUsername);
		lblUsername.setWidth("217px");
		logindetailsflextable.getCellFormatter().setStyleName(0, 0, "middlelineflextable");
		
		Label lblSignout = new Label("SignOut");
		lblSignout.setStyleName("logout");
		lblSignout.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				clearCookies();
				Window.Location.replace(GWT.getHostPageBaseURL());
			}
		});
		lblSignout.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		logindetailsflextable.setWidget(0, 1, lblSignout);
		lblSignout.setWidth("136px");
		logindetailsflextable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		logindetailsflextable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		dataflextable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		dataflextable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		mainflexatble.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
	}
	public void clearCookies()
	{
		Collection<String> cookies = Cookies.getCookieNames();
	    for (String cookie : cookies)
	    {
	    	if(!cookie.equalsIgnoreCase("img"))
	    		Cookies.removeCookie(cookie);
	    }
	}
}
