package com.product.home.client.client;

import java.util.ArrayList;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;

public class MultiSelectView extends Composite 
{
	FlexTable flexTable = new FlexTable();
	ScrollPanel MainscrollPanel = new ScrollPanel();
	public MultiSelectView() 
	{
		MainscrollPanel.setStyleName("multiselectview");
		initWidget(MainscrollPanel);
		MainscrollPanel.setSize("150px", "150px");
		
		MainscrollPanel.setWidget(flexTable);
		//flexTable.setSize("99%", "100%");
	}
	/**
	 *  this method is used to set the size
	 */
	public void setSize(String width,String height)
	{
		MainscrollPanel.setSize(width, height);
	}
	/**
	 * This method is used to remove all the rows
	 */
	public void removeRows()
	{
		flexTable.removeAllRows();
	}
	/**
	 *  This method is used to set the checkboxes
	 *  
	 * @param Dataarray jsonarray contians the jsonobjects
	 * @param name jsonobject contanins the key
	 * @param id   jsonobject contains the key
	 */
	public void setCheckboxes(JSONArray Dataarray,String name,String id)
	{
		CheckBox checkbox;
		JSONObject tempjson;
		flexTable.removeAllRows();
		Label label;
		for(int p=0;p<Dataarray.size();p++)
		{
			tempjson=Dataarray.get(p).isObject();
			checkbox=new CheckBox();
			label=new Label(tempjson.get(name).isString().stringValue());
			label.setWidth("100%");
			checkbox.setTitle(tempjson.get(name).isString().stringValue());
			checkbox.getElement().setId(tempjson.get(id).isString().stringValue());
			flexTable.setWidget(p, 0, checkbox);
			flexTable.setWidget(p, 1, label);
		}
	}
	/**
	 * This method is used to check the checkboxes based on ids
	 * 
	 * @param dataarray Arraylist with strings with ids
	 */
	public void setSelectedCheckboxes(ArrayList<String> dataarray)
	{
		CheckBox tempcheckbox;
		for(int p=0;p<flexTable.getRowCount();p++)
		{
			tempcheckbox=(CheckBox) flexTable.getWidget(p, 0);
			if(dataarray.contains(tempcheckbox.getElement().getId()))
			{
				tempcheckbox.setValue(true);
			}
		}
	}
	/**
	 * this method is used to get selected jsonarray
	 * @param name 
	 * @param id
	 * @return return jsonarray with jsonobjects with key as name and key as id
	 */
	
	public JSONArray getSelectedCheckboxes(String name,String id)
	{
		JSONArray resultjson=new JSONArray();
		JSONObject tempjson;
		CheckBox tempcheckbox;
		int k=0;
		for(int p=0;p<flexTable.getRowCount();p++)
		{
			tempcheckbox=(CheckBox) flexTable.getWidget(p, 0);
			if(tempcheckbox.getValue())
			{
				tempjson=new JSONObject();
				tempjson.put(name, new JSONString(tempcheckbox.getTitle()));
				tempjson.put(id, new JSONString(tempcheckbox.getElement().getId()));
				resultjson.set(k, tempjson);
				k++;
			}
		}
		return resultjson;
	}
	public void deselectCheckbox()
	{
		CheckBox tempcheckbox;
		for(int p=0;p<flexTable.getRowCount();p++)
		{
			tempcheckbox=(CheckBox) flexTable.getWidget(p, 0);
			tempcheckbox.setValue(false);
		}
	}
}
