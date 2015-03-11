package com.product.home.client.client.admin;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;


public class ErrorPage extends AdminBody {

	public ErrorPage() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(verticalPanel);
		
		Label lblProcessing = new Label("Processing......");
		verticalPanel.add(lblProcessing);
	}

	@Override
	public void reset() {
	}

}
