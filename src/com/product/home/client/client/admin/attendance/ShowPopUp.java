package com.product.home.client.client.admin.attendance;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

public class ShowPopUp extends PopupPanel {

	public FlexTable flexTable = new FlexTable();
	public Label closebutton=new Label("X");
	public Label lblHeadername = new Label("HeaderName");

	public ShowPopUp() {

		super(false);
		setStyleName("");
		setSize("0px","0px");
		flexTable.setStyleName("showpopupcss");
		flexTable.setSize("98%", "98%");

		setWidget(flexTable);
		FlexTable bannerflex = new FlexTable();
		flexTable.setWidget(0, 0, bannerflex);
		flexTable.getCellFormatter().setStyleName(0, 0, "none");
		bannerflex.setWidth("100%");
		lblHeadername.setStyleName("headerlabelcsspopup");

		bannerflex.setWidget(0, 0, lblHeadername);
		bannerflex.getCellFormatter().setWidth(0, 0, "90%");

		closebutton.setStyleName("closbuttonpopup");
		bannerflex.setWidget(0, 1, closebutton);
		bannerflex.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		bannerflex.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		closebutton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ConstantsVariables.showPopup.hide();
			}
		});
	}

}
