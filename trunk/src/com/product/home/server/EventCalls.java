package com.product.home.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.product.home.server.databaseDAO.DatabaseConnection;
import com.product.home.server.util.DateUtils;

public class EventCalls {

	public JSONObject getEventTypes(String driver, String url, String user,String pwd, String ipAddress) 
	{
		Connection con=null;
		boolean errcheck=false;
		PreparedStatement ps=null;
		JSONObject response=new JSONObject();
		String errorlog="start";
		String errorlogfinal="";
		try
		{
			response.put("status", "failed");
			con=DatabaseConnection.getConnection(driver, url, user, pwd);
			if(con!=null)
			{
				String sql=EventQueries.selectEventTypesQry;
				ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				JSONArray eventsarray=new JSONArray();
				JSONObject events;
				while(rs.next())
				{
					events=new JSONObject();
					events.put("eventname", rs.getString("EVENT_NAME"));
					events.put("eventtypeid", rs.getString("EVENT_TYPE_ID"));
					eventsarray.put(events);
				}
				response.put("events", eventsarray);
				response.put("status", "success");
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getEventTypes "+e.getMessage();
		}
		finally
		{
			try 
			{
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
				if(!errorlogfinal.equals("")||errcheck)
				{
					if(errorlogfinal.equals(""))
						errorlogfinal=errorlog+" getEventTypes ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getEventTypes", errorlogfinal, "failed",ipAddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}

	public JSONObject getEventTypeDetails(String driver, String url, String user, String pwd, String eventTypeId, String ipAddress) 
	{
		Connection con=null;
		boolean errcheck=false;
		PreparedStatement ps=null;
		JSONObject response=new JSONObject();
		String errorlog="start";
		String errorlogfinal="";
		try
		{
			response.put("status", "failed");
			con=DatabaseConnection.getConnection(driver, url, user, pwd);
			if(con!=null)
			{
				String sql=EventQueries.selectEventTypeDetailsQry;
				ps=con.prepareStatement(sql);
				ps.setString(1, eventTypeId);
				ResultSet rs=ps.executeQuery();
				JSONObject eventdata = new JSONObject();
				while(rs.next())
				{
					eventdata.put("eventname",rs.getString("EVENT_NAME"));
					eventdata.put("eventdesc",rs.getString("EVENT_DESCRIPTION"));
					eventdata.put("status",rs.getString("STATUS"));
				}
				response.put("eventdata", eventdata);
				response.put("status", "success");
			}
			else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getEventTypeDetails "+e.getMessage();
		}
		finally
		{
			try 
			{
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
				if(!errorlogfinal.equals("")||errcheck)
				{
					if(errorlogfinal.equals(""))
						errorlogfinal=errorlog+" getEventTypeDetails ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getEventTypeDetails", errorlogfinal, "failed",ipAddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}

	public JSONObject insert_updateEventType(String driver, String url,	String user, String pwd, String eventjson, String ipAddress) 
	{
		Connection con=null;
		boolean errcheck=false;
		PreparedStatement ps=null;
		JSONObject response=new JSONObject();
		String errorlog="start";
		String errorlogfinal="";
		try
		{
			response.put("status", "failed");
			con=DatabaseConnection.getConnection(driver, url, user, pwd);
			if(con!=null)
			{
				JSONObject eventdatajson = new JSONObject(eventjson);
				String sql="";
				if(eventdatajson.get("eventid").equals("0"))
				{
					sql=EventQueries.insertEventTypeDetailsQry;
					ps=con.prepareStatement(sql);
					ps.setString(1, eventdatajson.getString("eventname"));
					ps.setString(2, eventdatajson.getString("eventdesc"));
					ps.setString(3, eventdatajson.getString("status"));
				}
				else
				{
					sql=EventQueries.updateEventTypeDetailsQry;
					ps=con.prepareStatement(sql);
					ps.setString(1, eventdatajson.getString("eventname"));
					ps.setString(2, eventdatajson.getString("eventdesc"));
					ps.setString(3, eventdatajson.getString("status"));
					ps.setString(4, eventdatajson.getString("eventid"));
				}
				int i=ps.executeUpdate();
				if(i>0)
					response.put("status", "success");
			}
			else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" insert_updateEventType "+e.getMessage();
		}
		finally
		{
			try 
			{
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
				if(!errorlogfinal.equals("")||errcheck)
				{
					if(errorlogfinal.equals(""))
						errorlogfinal=errorlog+" insert_updateEventType ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "insert_updateEventType", errorlogfinal, "failed",ipAddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}

	public JSONObject getEventsData(String driver, String url,	String user, String pwd, String ipAddress,int year, int month, String roleid) 
	{
		Connection con=null;
		boolean errcheck=false;
		PreparedStatement ps=null;
		JSONObject response=new JSONObject();
		String errorlog="start";
		String errorlogfinal="";
		try
		{
			response.put("status", "failed");
			con=DatabaseConnection.getConnection(driver, url, user, pwd);
			if(con!=null)
			{
				HashMap<String, String> startEndDateHMap = DateUtils.getMonthStartEndDate(year, month);
				String startdate = startEndDateHMap.get("startdate");
				String enddate = startEndDateHMap.get("enddate");
				String days=startEndDateHMap.get("numberofday");
				String monthstartday=startEndDateHMap.get("monthstartday");
				String selectEventsDataQry="";
				if(roleid.equalsIgnoreCase("1"))
					selectEventsDataQry=EventQueries.selectAllEventsDataQry;
				else
					selectEventsDataQry=EventQueries.selectEventsDataQry.replace("@", "0','"+roleid);
				ps=con.prepareStatement(selectEventsDataQry);
				ps.setString(1, startdate);
				ps.setString(2, enddate);
				errorlog=ps.toString();
				ResultSet rs = ps.executeQuery();
				JSONObject eventdataObj = new JSONObject();
				while(rs.next())
				{
					if(eventdataObj.has(rs.getString("EVENT_DATE")))
					{
						JSONArray eventArray = eventdataObj.getJSONArray(rs.getString("EVENT_DATE"));
						JSONObject eventObj = new JSONObject();
						eventObj.put("eventid", rs.getString("EVENT_ID"));
						eventObj.put("eventtypeid", rs.getString("EVENT_TYPE_ID"));
						eventObj.put("eventtype",rs.getString("EVENT_NAME"));
						eventObj.put("message",rs.getString("EVENT_MESSAGE"));
						eventObj.put("desc",rs.getString("EVENT_DESCRIPTION"));
						eventObj.put("date",rs.getString("EVENT_DATE"));
						eventObj.put("starttime",rs.getString("START_TIME"));
						eventObj.put("endtime",rs.getString("END_TIME"));
						eventArray.put(eventObj);
						eventdataObj.put(rs.getString("EVENT_DATE"), eventArray);
					}
					else
					{
						JSONArray eventArray = new JSONArray();
						JSONObject eventObj = new JSONObject();
						eventObj.put("eventid", rs.getString("EVENT_ID"));
						eventObj.put("eventtypeid", rs.getString("EVENT_TYPE_ID"));
						eventObj.put("eventtype",rs.getString("EVENT_NAME"));
						eventObj.put("message",rs.getString("EVENT_MESSAGE"));
						eventObj.put("desc",rs.getString("EVENT_DESCRIPTION"));
						eventObj.put("date",rs.getString("EVENT_DATE"));
						eventObj.put("starttime",rs.getString("START_TIME"));
						eventObj.put("endtime",rs.getString("END_TIME"));
						eventArray.put(eventObj);
						eventdataObj.put(rs.getString("EVENT_DATE"), eventArray);
					}
				}
				response.put("eventdata", eventdataObj);
				response.put("monthstartday", monthstartday);
				response.put("numberofday", days);
				response.put("status", "success");
			}
			else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getEventsData "+e.getMessage();
		}
		finally
		{
			try 
			{
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
				if(!errorlogfinal.equals("")||errcheck)
				{
					if(errorlogfinal.equals(""))
						errorlogfinal=errorlog+" getEventsData ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getEventsData", errorlogfinal, "failed",ipAddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}

}
