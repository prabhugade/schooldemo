package com.product.home.client;
 
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.product.home.client.client.admin.AdminBody;

public class ChangePassword extends AdminBody  {
	
	MainServiceAsync mainservice=GWT.create(MainService.class);
	
	PasswordTextBox oldpasswordTextBox = new PasswordTextBox();
	PasswordTextBox newpasswordTextBox = new PasswordTextBox();
	PasswordTextBox confirmpasswordTextBox = new PasswordTextBox();
	
	Button btnSubmit = new Button("Submit");
	
	Label lblErrormessage = new Label("");
	
	public ChangePassword() {

		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("100%", "269px");

		FlexTable mainflexTable = new FlexTable();
		flexTable.setWidget(0, 0, mainflexTable);
		mainflexTable.setSize("99%", "223px");

		FlexTable dataflexTable = new FlexTable();
		mainflexTable.setWidget(0, 0, dataflexTable);
		dataflexTable.setSize("350px", "155px");

		Label lblOldPassword = new Label("Old Password");
		lblOldPassword.setStyleName("loginLabel");
		dataflexTable.setWidget(0, 0, lblOldPassword);

		dataflexTable.setWidget(0, 1, oldpasswordTextBox);

		Label lblNewPassword = new Label("New Password");
		lblNewPassword.setStyleName("loginLabel");
		dataflexTable.setWidget(1, 0, lblNewPassword);

		dataflexTable.setWidget(1, 1, newpasswordTextBox);

		Label lblConfirmPassword = new Label("Confirm Password");
		lblConfirmPassword.setStyleName("loginLabel");
		dataflexTable.setWidget(2, 0, lblConfirmPassword);

		dataflexTable.setWidget(2, 1, confirmpasswordTextBox);
		mainflexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		mainflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		lblErrormessage.setStyleName("error-Label");
		mainflexTable.setWidget(1, 0, lblErrormessage);

		FlexTable btnflexTable = new FlexTable();
		mainflexTable.setWidget(2, 0, btnflexTable);
		btnflexTable.setWidth("350px");

		btnSubmit.setType(ButtonType.PRIMARY);
		btnflexTable.setWidget(0, 0, btnSubmit);
		btnSubmit.setWidth("300px");
		btnflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainflexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainflexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		
		btnSubmit.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				validation();
			}
		});
	}

	public void validation()
	{
		lblErrormessage.setText("");
		if(oldpasswordTextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please Enter Old Password");
		}
		else if(newpasswordTextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please Enter New Password");
		}
		else if(confirmpasswordTextBox.getText().trim().length()==0)
		{
			lblErrormessage.setText("Please Enter Confirm Password");
		}
		else if(!newpasswordTextBox.getText().trim().equals(confirmpasswordTextBox.getText().trim()))
		{
			lblErrormessage.setText("NewPassword And ConfirmPassword Are Not Same");
		}
		else
		{
			lblErrormessage.setText("");


			JSONObject changepasswordjsonobj=new JSONObject();
			changepasswordjsonobj.put("oldpassword",new JSONString(oldpasswordTextBox.getText().trim()));
			changepasswordjsonobj.put("newpassword",new JSONString(newpasswordTextBox.getText().trim()));
			changepasswordjsonobj.put("loginid",new JSONString(Cookies.getCookie("lid")));
			ChangePasswordBasedOnLoginId(changepasswordjsonobj);
		}
	}
	
	public void ChangePasswordBasedOnLoginId(JSONObject dataobj)
	{
		mainservice.ChangePasswordBasedOnLoginId(dataobj.toString(),new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				
				JSONObject response=JSONParser.parseStrict(result).isObject();
				if(response!=null && response.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					Window.alert("Password Successfully Changed");
				}
				else if(response!=null && response.get("status").isString().stringValue().equalsIgnoreCase("notsame"))
				{
					Window.alert("Old Password is Not Correct");
				}
				else
				{
					Window.alert("faild");
				}
				resetfresh();
			}
		});
	}
	 
	public void resetfresh()
	{
		oldpasswordTextBox.setText("");
		newpasswordTextBox.setText("");
		confirmpasswordTextBox.setText("");
		lblErrormessage.setText("");
	}
	
	@Override
	public void reset() {
		resetfresh();
		lblErrormessage.setText("");
	}
	
}
