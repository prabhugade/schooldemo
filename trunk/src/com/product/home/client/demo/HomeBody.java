package com.product.home.client.demo;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;

public class HomeBody extends Composite {

	public HomeBody() 
	{
		
		VerticalPanel mainverticalPanel = new VerticalPanel();
		mainverticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(mainverticalPanel);
		mainverticalPanel.setSize("100%", "100%");
		
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		verticalPanel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainverticalPanel.add(verticalPanel_1);
		verticalPanel_1.setSize("100%", "100%");
		
		HTML htmlNewHtml = new HTML("<iframe width=\"90%\" height=\"50%\"\r\nsrc=\"http://www.youtube.com/embed/o2h-YFv9Wxk\">\r\n</iframe>\r\n", true);
		verticalPanel_1.add(htmlNewHtml);
		
		FlexTable flexTable = new FlexTable();
		flexTable.setCellSpacing(5);
		flexTable.setCellPadding(5);
		flexTable.setStyleName("flextable");
		verticalPanel_1.add(flexTable);
		flexTable.setSize("100%", "100%");
		
		Image image_2 = new Image("images/classroom-staff-teacher.jpg");
		flexTable.setWidget(0, 0, image_2);
		image_2.setSize("100%", "100%");
		
		Image image_1 = new Image("images/directory.jpg");
		flexTable.setWidget(0, 1, image_1);
		image_1.setSize("100%", "100%");
		
		Image image = new Image("images/family.jpg");
		flexTable.setWidget(0, 2, image);
		image.setSize("100%", "100%");
		
		Image image_3 = new Image("images/Handbooks.jpg");
		flexTable.setWidget(1, 0, image_3);
		image_3.setSize("100%", "100%");
		
		Image image_4 = new Image("images/schoolboard.jpg");
		flexTable.setWidget(1, 1, image_4);
		image_4.setSize("100%", "100%");
		
		Image image_5 = new Image("images/school-n-teacher.jpg");
		flexTable.setWidget(1, 2, image_5);
		image_5.setSize("100%", "100%");
		
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
	}

}
