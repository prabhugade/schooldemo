package com.product.home.client;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Close;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.NavHeader;
import com.github.gwtbootstrap.client.ui.constants.BackdropType;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.product.home.client.client.ClientMainHome;
import com.product.home.client.client.HomeHeader;
import com.product.home.client.client.HomeMain;
import com.product.home.client.client.admin.HomeMainAdmin;
import com.product.home.client.client.parent.HomeMainParent;
import com.product.home.client.client.student.HomeMainStudent;
import com.product.home.client.client.teacher.HomeMainTeacher;

public class LoginForm extends Composite 
{
	public TextBox usernametextBox = new TextBox();
	public PasswordTextBox passwordtextBox = new PasswordTextBox();
	Label lblEnterUsername = new Label("Enter UserName");
	Label lblEnterPassword = new Label("Enter Password");
	Label lblUsernamepasswordIsWrong = new Label("UserName/Password is wrong");
	MainServiceAsync mainservice=GWT.create(MainService.class);
	Button btnLogin = new Button("Login");
	
	Label forgetPassword = new Label("Forget Password");
	public LoginForm() 
	{
		FlexTable flexTable = new FlexTable();
		flexTable.setStyleName("none");
		initWidget(flexTable);
		flexTable.setSize("250px", "228px");
		
		Label lblUserName = new Label("User Name");
		lblUserName.setStyleName("loginLabel");
		flexTable.setWidget(0, 0, lblUserName);
		lblUserName.setHeight("22px");
		
		flexTable.setWidget(1, 0, usernametextBox);
		usernametextBox.setSize("90%", "25px");
		flexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		lblEnterUsername.setStyleName("error-Label");
		lblEnterUsername.setVisible(false);
		flexTable.setWidget(2, 0, lblEnterUsername);
		
		Label lblPassword = new Label("Password");
		lblPassword.setStyleName("loginLabel");
		flexTable.setWidget(3, 0, lblPassword);
		
		flexTable.setWidget(4, 0, passwordtextBox);
		passwordtextBox.setSize("90%", "25px");
		flexTable.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		lblEnterPassword.setStyleName("error-Label");
		flexTable.setWidget(5, 0, lblEnterPassword);
		lblEnterPassword.setVisible(false);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget(6, 0, horizontalPanel);
		horizontalPanel.setSize("99%", "53px");
		
		
		//btnLogin.setStyleName("btnbgcolor");
		btnLogin.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				validation();
			}
		});
		horizontalPanel.add(btnLogin);
		horizontalPanel.setCellVerticalAlignment(btnLogin, HasVerticalAlignment.ALIGN_MIDDLE);
		btnLogin.setSize("90%", "25px");
		btnLogin.setType(ButtonType.PRIMARY);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		flexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setVerticalAlignment(4, 0, HasVerticalAlignment.ALIGN_TOP);
		
		lblUsernamepasswordIsWrong.setStyleName("error-Label");
		flexTable.setWidget(7, 0, lblUsernamepasswordIsWrong);
		lblUsernamepasswordIsWrong.setVisible(false);
		forgetPassword.setStyleName("forgetPassword");
		
		
		flexTable.setWidget(8, 0, forgetPassword);
		forgetPassword.setHeight("19px");
		flexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(7, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(7, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable.getCellFormatter().setHorizontalAlignment(8, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable.getCellFormatter().setVerticalAlignment(6, 0, HasVerticalAlignment.ALIGN_TOP);
		
		forgetPassword.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				HomeHeader.loginformpopup.hide();
				HomeHeader.loginformpopup=new Modal();
				HomeHeader.loginformpopup.setWidth(500);
				Close close=new Close();
				HomeHeader.loginformpopup.add(close);
				
				close.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						HomeHeader.loginformpopup.hide();
					}
				});
				NavHeader sectionheader=new NavHeader("Forget Password");
				HomeHeader.loginformpopup.add(sectionheader);
				ForgetPassword forget=new ForgetPassword();
				FlexTable forgetfletable=new FlexTable();
				forgetfletable.setWidth("99%");
				forgetfletable.setWidget(0, 0,forget);
				forgetfletable.getCellFormatter().setHorizontalAlignment(0, 0,HasHorizontalAlignment.ALIGN_CENTER);
				HomeHeader.loginformpopup.add(forgetfletable);
				HomeHeader.loginformpopup.setBackdrop(BackdropType.NORMAL);
				HomeHeader.loginformpopup.show();
			}
		});
		
		usernametextBox.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				
				if(event.getNativeKeyCode()==KeyCodes.KEY_ENTER)
				{
					validation();
				}
			}
		});
		
		passwordtextBox.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				
				if(event.getNativeKeyCode()==KeyCodes.KEY_ENTER)
				{
					validation();
				}
			}
		});
	}
	
	public void validation()
	{
		if(usernametextBox.getText().length()==0)
		{
			lblEnterUsername.setVisible(true);
			lblUsernamepasswordIsWrong.setVisible(false);
			usernametextBox.setFocus(true);
		}else if(passwordtextBox.getText().length()==0)
		{
			lblEnterUsername.setVisible(false);
			lblEnterPassword.setVisible(true);
			lblUsernamepasswordIsWrong.setVisible(false);
			passwordtextBox.setFocus(true);
		}else
		{
			lblEnterUsername.setVisible(false);
			lblEnterPassword.setVisible(false);
			loginCheck();
		}
	}
	
	public void loginCheck()
	{
		JSONObject loginjson=new JSONObject();
		loginjson.put("username", new JSONString(usernametextBox.getText()));
		loginjson.put("password", new JSONString(passwordtextBox.getText()));
		loginjson.put("url", new JSONString(GWT.getHostPageBaseURL()));
		mainservice.loginCheck(loginjson.toString(),new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {				
			}
			@Override
			public void onSuccess(String result) 
			{
				lblUsernamepasswordIsWrong.setVisible(false);
				JSONObject resultjson=JSONParser.parseStrict(result).isObject();
				if(resultjson.containsKey("status")&&resultjson.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					HomeHeader.loginformpopup.hide();
					//lid is loginid
					//rid is role
					//type is demo/client
					//cid   is courseid 
					//sid   is sectionid
					//stid  is studentid/parentid/adminid/teacherid
					
					Cookies.setCookie("lid",resultjson.get("loginid").isString().stringValue());
					Cookies.setCookie("rid",resultjson.get("role").isString().stringValue());
					Cookies.setCookie("stid",resultjson.get("id").isString().stringValue());
					
					//name
					Cookies.setCookie("username",resultjson.get("name").isString().stringValue());
					
					if(Cookies.getCookie("type").equalsIgnoreCase("1"))
					{
						HomeMain mainpanel = null;
						if(resultjson.get("role").isString().stringValue().equalsIgnoreCase("1"))
						{
							mainpanel=new HomeMainAdmin();
						}else if(resultjson.get("role").isString().stringValue().equalsIgnoreCase("2"))
						{
							mainpanel=new HomeMainTeacher();
						}else if(resultjson.get("role").isString().stringValue().equalsIgnoreCase("3"))
						{
							JSONArray array=resultjson.get(Jsonkeys.studentdata).isArray();
							if(array.size()>0)
							{
								JSONObject tempjson=array.get(0).isObject();
								Cookies.setCookie("cid",tempjson.get("courseid").isString().stringValue());
								Cookies.setCookie("sid",tempjson.get("sectionid").isString().stringValue());
								mainpanel=new HomeMainStudent();
							}else
							{
								Window.alert("Failed to get Student Data");
							}
							
						}else if(resultjson.get("role").isString().stringValue().equalsIgnoreCase("4"))
						{
							JSONArray array=resultjson.get(Jsonkeys.studentdata).isArray();
							if(array.size()>0)
							{
								Cookies.setCookie(Jsonkeys.studentdata,array.toString());
								mainpanel=new HomeMainParent();
							}else
							{
								Window.alert("Failed to get Student Data");
							}
						}
						if(mainpanel!=null)
						{
							ClientMainHome.vpanel.clear();
							ClientMainHome.vpanel.add(mainpanel);
						}
					}
				}else
				{
					lblUsernamepasswordIsWrong.setVisible(true);
				}
			}
		});
	}

}
