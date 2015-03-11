package com.product.home.client.client.mailbox;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HTML;

public class MessageDisplay extends Composite {

	Label lblMessagesubjectvalue = new Label("");
	HTML htmlMessagebodyvalue = new HTML("", true);
	
	public MessageDisplay() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setWidth("100%");
		
		FlexTable mainflexTable = new FlexTable();
		flexTable.setWidget(0, 0, mainflexTable);
		mainflexTable.setHeight("43px");
		
		FlexTable subjectflexTable = new FlexTable();
		mainflexTable.setWidget(0, 0, subjectflexTable);
		subjectflexTable.setWidth("550px");
		
		Label lblSubject = new Label("Subject : ");
		lblSubject.setStyleName("greebsubheading");
		subjectflexTable.setWidget(0, 0, lblSubject);
		lblSubject.setWidth("62px");
		
		subjectflexTable.setWidget(0, 1, lblMessagesubjectvalue);
		lblMessagesubjectvalue.setSize("480px", "50px");
		subjectflexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		
		Label lblMessage = new Label("Message :");
		lblMessage.setStyleName("greebsubheading");
		mainflexTable.setWidget(1, 0, lblMessage);
		
		
		mainflexTable.setWidget(2, 0, htmlMessagebodyvalue);
		htmlMessagebodyvalue.setSize("550px", "255px");
		mainflexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		mainflexTable.getCellFormatter().setVerticalAlignment(2, 0, HasVerticalAlignment.ALIGN_BOTTOM);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}
}
