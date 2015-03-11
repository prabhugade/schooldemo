package com.product.home.server;

import com.product.home.server.databaseDAO.DatabaseTable;

public class EventQueries 
{
	public static final String selectEventTypesQry="SELECT `EVENT_TYPE_ID`, `EVENT_NAME`,`EVENT_DESCRIPTION`, `STATUS` FROM "+DatabaseTable.EVENT_TYPE+"";
	
	public static final String selectEventTypeDetailsQry="SELECT `EVENT_NAME`,`EVENT_DESCRIPTION`, `STATUS` FROM "+DatabaseTable.EVENT_TYPE+" where `EVENT_TYPE_ID`=?";
	
	public static final String insertEventTypeDetailsQry="INSERT INTO "+DatabaseTable.EVENT_TYPE+" (`EVENT_NAME`, `EVENT_DESCRIPTION`, `STATUS`) VALUES (?,?,?)";
	
	public static final String updateEventTypeDetailsQry="UPDATE "+DatabaseTable.EVENT_TYPE+" SET `EVENT_NAME`=?,`EVENT_DESCRIPTION`=?, `STATUS`=? WHERE `EVENT_TYPE_ID`=?";
	
	public static final String selectAllEventsDataQry="SELECT `EVENT_ID`, `EVENT_MESSAGE`, ED.`EVENT_TYPE_ID`, `EVENT_PRIVACY_ID`, ED.`EVENT_DESCRIPTION`, `EVENT_DATE`, `START_TIME`, `END_TIME`, `EMPLOYEE_ID`, `DATE_TIME`,`EVENT_NAME` FROM "+DatabaseTable.EVENT_DETAILS+" AS ED join "+DatabaseTable.EVENT_TYPE+" AS ET ON ED.`EVENT_TYPE_ID`=ET.`EVENT_TYPE_ID`  WHERE `EVENT_DATE` between ? and ? order by `EVENT_DATE` asc";
	
	public static final String selectEventsDataQry="SELECT `EVENT_ID`, `EVENT_MESSAGE`, ED.`EVENT_TYPE_ID`, `EVENT_PRIVACY_ID`, ED.`EVENT_DESCRIPTION`, `EVENT_DATE`, `START_TIME`, `END_TIME`, `EMPLOYEE_ID`, `DATE_TIME`,`EVENT_NAME` FROM "+DatabaseTable.EVENT_DETAILS+" AS ED join "+DatabaseTable.EVENT_TYPE+" AS ET ON ED.`EVENT_TYPE_ID`=ET.`EVENT_TYPE_ID`  WHERE `EVENT_PRIVACY_ID` in ('@') AND `EVENT_DATE` between ? and ? order by `EVENT_DATE` asc";
}
