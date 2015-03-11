package com.product.home.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.product.home.client.client.ClientMainHome;
import com.product.home.client.client.HomeMain;
import com.product.home.client.client.admin.HomeMainAdmin;
import com.product.home.client.client.parent.HomeMainParent;
import com.product.home.client.client.student.HomeMainStudent;
import com.product.home.client.client.teacher.HomeMainTeacher;
import com.product.home.client.demo.DemoMainHome;

public class School_Web implements EntryPoint 
	{
	MainServiceAsync mainservice=GWT.create(MainService.class);
	//VerticalPanel vpanel=new VerticalPanel();
	FlexTable vpanel=new FlexTable();
	public void onModuleLoad() 
	{
		RootPanel rootpanel=RootPanel.get();
		rootpanel.setStyleName("none");
		String url=GWT.getHostPageBaseURL();
		rootpanel.setSize("100%", "100%");
		//vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		rootpanel.add(vpanel,0,0);
		vpanel.setSize("100%", "100%");
		mainservice.getUrlData(url,new AsyncCallback<String>() 
		{
			@Override
			public void onFailure(Throwable caught) 
			{
			}	

			@Override
			public void onSuccess(String result) 
			{
				JSONValue jsonvalue = JSONParser.parseStrict(result);
				JSONObject resObj = jsonvalue.isObject();
				if(resObj.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					if(resObj.get("type").isString().stringValue().equalsIgnoreCase("0"))
					{
						Cookies.setCookie("type","0");
						DemoMainHome home=new DemoMainHome();
						home.setSize("100%", "100%");
						vpanel.setWidget(0, 0,home);
					}else
					{
						Cookies.setCookie("type","1");
						Cookies.setCookie("image",resObj.get("image").isString().stringValue());
						Cookies.setCookie("schoolname",resObj.get("schoolname").isString().stringValue());
						if(Cookies.getCookie("lid")==null)
						{
							ClientMainHome home=new ClientMainHome();
							home.setSize("100%", "100%");
							vpanel.setWidget(0, 0,home);
						}else
						{
							HomeMain mainpanel = null;
							if(Cookies.getCookie("rid").equalsIgnoreCase("1"))
							{
								mainpanel=new HomeMainAdmin();
							}else if(Cookies.getCookie("rid").equalsIgnoreCase("2"))
							{
								mainpanel=new HomeMainTeacher();
							}else if(Cookies.getCookie("rid").equalsIgnoreCase("3"))
							{
								mainpanel=new HomeMainStudent();
							}else if(Cookies.getCookie("rid").equalsIgnoreCase("4"))
							{
								mainpanel=new HomeMainParent();
							}
							if(mainpanel!=null)
							{
								ClientMainHome home=new ClientMainHome();
								home.setSize("100%", "100%");
								ClientMainHome.vpanel.clear();
								ClientMainHome.vpanel.add(mainpanel);
								vpanel.setWidget(0, 0,home);
							}
						}
					}
					vpanel.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
					vpanel.getCellFormatter().setHeight(0, 0, "100%");
					vpanel.getCellFormatter().setWidth(0, 0, "100%");
				}else
					Window.alert(resObj.get("status").isString().stringValue());
			}
		});
	}
}
