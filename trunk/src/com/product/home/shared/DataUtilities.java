package com.product.home.shared;

import java.util.HashMap;

import com.google.gwt.user.client.ui.ListBox;

public abstract class DataUtilities {
	
	public static  void selcomboBoxValue(ListBox object,String hvalue)
	{
		for(int s=0;s<object.getItemCount();s++)
		{
			if(hvalue.equals(object.getItemText(s)))
			{
				object.setSelectedIndex(s);
			}
		}
	}
	
	public static String getHashMapKey(HashMap<String,String> hmap,String value)
	{
		  for(String s : hmap.keySet()){
		        if(hmap.get(s).equals(value)) return s;
		    }
		 return null;
	}

}
