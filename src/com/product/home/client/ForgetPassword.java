package com.product.home.client;


import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.product.home.client.client.HomeHeader;

public class ForgetPassword extends Composite {

	MainServiceAsync mainservice=GWT.create(MainService.class);
	
	Button btnSubmit = new Button("Submit");
	TextBox emailidtextBox = new TextBox();
	Label errormessage = new Label();
	public ForgetPassword() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("100%", "100%");
		
		FlexTable mainflexTable = new FlexTable();
		flexTable.setWidget(0, 0, mainflexTable);
		mainflexTable.setSize("428px", "130px");
		errormessage.setStyleName("error-Label");
		
		errormessage.setText("");
		mainflexTable.setWidget(0, 0, errormessage);
		mainflexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		mainflexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		FlexTable dataflexTable = new FlexTable();
		mainflexTable.setWidget(1, 0, dataflexTable);
		dataflexTable.setSize("320px", "98px");
		
		Label EmailId = new Label("Emaild ");
		EmailId.setStyleName("headerlabelcsspopup");
		dataflexTable.setWidget(0, 0, EmailId);
		EmailId.setWidth("75px");
		
		
		dataflexTable.setWidget(0, 1, emailidtextBox);
		emailidtextBox.setHeight("");
		dataflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		dataflexTable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		dataflexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		
		btnSubmit.setType(ButtonType.SUCCESS);
		dataflexTable.setWidget(1, 1, btnSubmit);
		btnSubmit.setWidth("79px");
		dataflexTable.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_CENTER);
		dataflexTable.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		dataflexTable.getCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		btnSubmit.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			
				validation();
			}
		});
	}
	
	public void validation()
	{
		errormessage.setText("");
		if(emailidtextBox.getText().trim().length()==0)
		{
			errormessage.setText("Please Enter EmailId");
		}
		else
		{
			errormessage.setText("");
			JSONObject forgetdata=new JSONObject();
			forgetdata.put("emailid",new JSONString(emailidtextBox.getText().trim()));
			forgetPasswordBasedOnEmailid(forgetdata);
		}
	}
	
	public void forgetPasswordBasedOnEmailid(JSONObject forgetjsonobj)
	{
		mainservice.forgetPasswordBasedOnEmailid(forgetjsonobj.toString(),new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				
				
				JSONObject resultjson=JSONParser.parseStrict(result).isObject();
				if(resultjson.containsKey("status")&&resultjson.get("status").isString().stringValue().equalsIgnoreCase("success"))
				{
					Window.alert("Successfully Send To Your Email..");
					HomeHeader.loginformpopup.hide();
				}
				else
				{
					errormessage.setText("Invalid EmailId");
				}
			}
		});
	}
}
