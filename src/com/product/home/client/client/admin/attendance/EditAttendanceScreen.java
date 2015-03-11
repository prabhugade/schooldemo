package com.product.home.client.client.admin.attendance;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class EditAttendanceScreen extends Composite {
	
	Label lblErrormessage = new Label("");
	Label lblDate = new Label("");
	
	TextBox leaveTypetextBox = new TextBox();
	TextBox reasontextBox = new TextBox();
	
	Button btnSave = new Button("Save");
	Button btnDelete = new Button("Delete");
	
	
	public EditAttendanceScreen() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("309px", "306px");
		
		FlexTable mainflextable = new FlexTable();
		flexTable.setWidget(0, 0, mainflextable);
		mainflextable.setSize("284px", "303px");
		
		FlexTable dataflexTable = new FlexTable();
		mainflextable.setWidget(0, 0, dataflexTable);
		dataflexTable.setSize("258px", "278px");
		
		HTML lblFieldsWith = new HTML("Fields with <font color='red'>*</font> are required.");
		dataflexTable.setWidget(0, 0, lblFieldsWith);
		lblFieldsWith.setHeight("28px");
		dataflexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		lblDate.setStyleName("greebsubheading");
		
		
		dataflexTable.setWidget(1, 0, lblDate);
		lblDate.setHeight("33px");
		
		HTML lblEmployeeLeaveType = new HTML("Employee Leave Type <font color='red'>*</font>");
		dataflexTable.setWidget(2, 0, lblEmployeeLeaveType);
		lblEmployeeLeaveType.setHeight("21px");
		dataflexTable.getCellFormatter().setVerticalAlignment(2, 0, HasVerticalAlignment.ALIGN_TOP);
		
		dataflexTable.setWidget(3, 0, leaveTypetextBox);
		dataflexTable.getCellFormatter().setVerticalAlignment(3, 0, HasVerticalAlignment.ALIGN_TOP);
		
		HTML lblReason = new HTML("Reason <font color='red'>*</font>");
		dataflexTable.setWidget(4, 0, lblReason);
		
		dataflexTable.setWidget(5, 0, reasontextBox);
		lblErrormessage.setStyleName("error-Label");
		
		
		dataflexTable.setWidget(6, 0, lblErrormessage);
		
		FlexTable btnflexTable = new FlexTable();
		dataflexTable.setWidget(7, 0, btnflexTable);
		btnflexTable.setWidth("243px");
		
		btnSave.setType(ButtonType.SUCCESS);
		btnflexTable.setWidget(0, 0, btnSave);
		btnSave.setWidth("70px");
		
		btnDelete.setType(ButtonType.WARNING);
		btnflexTable.setWidget(0, 1, btnDelete);
		btnDelete.setWidth("70px");
		btnflexTable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		dataflexTable.getCellFormatter().setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_CENTER);
		dataflexTable.getCellFormatter().setHorizontalAlignment(7, 0, HasHorizontalAlignment.ALIGN_LEFT);
		mainflextable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		mainflextable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}
}
