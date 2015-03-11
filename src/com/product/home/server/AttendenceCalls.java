package com.product.home.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;

import com.product.home.server.databaseDAO.DatabaseConnection;

public class AttendenceCalls {
	/**
	 * 
	 * @param driver
	 * @param url
	 * @param user
	 * @param pwd
	 * @param ipaddress
	 * @param datajson is jsonobject contains
	 * 					    studentid all/studentid
	 * 					    fromdate formdate in format of yyyy-MM-dd
	 * 						todate todate in format of yyyy-MM-dd
	 * 						courseid as courseid
	 * 						sectionid as sectionid
	 * 						subjectid as subjectid
	 * @return
	 */
	public JSONObject getAttendance(String driver,String url,String user,String pwd,String ipaddress,JSONObject datajson)
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
				HashMap<String, JSONObject> studentdata=new HashMap<String, JSONObject>();
				HashMap<String, JSONArray> studentattendance=new HashMap<String, JSONArray>();
				String studentidcondition="";
				if(!datajson.getString("studentid").equalsIgnoreCase("all"))
				{
					studentidcondition=" AND S.`STUDENT_ID`="+datajson.getString("studentid");
				}
				String sql=AttendenceQueries.getAttendence+studentidcondition+" UNION ALL "+AttendenceQueries.getStudentsForAttendence+studentidcondition;
				ps=con.prepareStatement(sql);
				ps.setString(1, datajson.getString("fromdate"));
				ps.setString(2, datajson.getString("todate"));
				ps.setString(3, datajson.getString("courseid"));
				ps.setString(4, datajson.getString("sectionid"));
				ps.setString(5, datajson.getString("subjectid"));
				ps.setString(6, datajson.getString("courseid"));
				ps.setString(7, datajson.getString("sectionid"));
				ResultSet rs=ps.executeQuery();
				JSONArray attendancearray=new JSONArray();
				JSONObject tempjson,tempjson1;
				JSONArray tempjsonarray;
				while(rs.next())
				{
					String studentid=rs.getString("STUDENT_ID");
					if(!studentdata.containsKey(studentid))
					{
						tempjson=new JSONObject();
						String middlename="";
						if(!rs.getString("MIDDLE_NAME").equalsIgnoreCase("-"))
							middlename=rs.getString("MIDDLE_NAME");
						tempjson.put("studentname", rs.getString("LAST_NAME")+" "+rs.getString("FIRST_NAME")+" "+middlename);
						tempjson.put("studentid", rs.getString("STUDENT_ID"));
						studentdata.put(studentid, tempjson);
					}
					if(!studentattendance.containsKey(studentid))
					{
						tempjsonarray=new JSONArray();
					}else
					{
						tempjsonarray=studentattendance.get(studentid);
					}
					if(!rs.getString("DATETIME").equalsIgnoreCase("-"))
					{
						tempjson1=new JSONObject();
						tempjson1.put("datetime", rs.getString("DATETIME"));
						tempjson1.put("reason", rs.getString("REASON"));
						tempjson1.put("type", rs.getString("TYPE"));
						tempjson1.put("attendenceid", rs.getString("ATTENDANCE_ID"));
						tempjsonarray.put(tempjson1);
					}
					studentattendance.put(studentid, tempjsonarray);
				}
				Iterator<Entry<String, JSONObject>> it=studentdata.entrySet().iterator();
				while(it.hasNext())
				{
					Entry<String, JSONObject> entry=it.next();
					String studentid=entry.getKey();
					tempjson=entry.getValue();
					tempjson.put("absentdates", studentattendance.get(studentid));
					attendancearray.put(tempjson);
				}
				response.put("data", attendancearray);
				response.put("status", "success");
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getAttendance "+e.getMessage();
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
						errorlogfinal=errorlog+" getAttendance ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getAttendance", errorlogfinal, "failed",ipaddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	public JSONObject insert_updateStudentsAttendence(String driver,String url,String user,String pwd,JSONObject absentdata,String ipaddress)
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
				String sql;
				if(absentdata.getString("attendenceid").equalsIgnoreCase("0"))
				{
					sql=AttendenceQueries.insertStudentAttendence;
					ps=con.prepareStatement(sql);
					ps.setString(1,absentdata.getString("studentid"));
					ps.setString(2,absentdata.getString("reason"));
					ps.setString(3,absentdata.getString("type"));
					ps.setString(4,absentdata.getString("subjectid"));
					ps.setString(5, absentdata.getString("datetime"));
				}
				else
				{
					sql=AttendenceQueries.updateStudentAttendence;
					ps=con.prepareStatement(sql);
					ps.setString(1,absentdata.getString("reason"));
					ps.setString(2,absentdata.getString("type"));
					ps.setString(3,absentdata.getString("attendenceid"));
				}
				errorlog=ps.toString();
				//System.out.println(errorlog);
				int i=ps.executeUpdate();
				if(i>0)
				{
					response.put("status", "success");
				}
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" insert_updateStudentsAttendence "+e.getMessage();
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
						errorlogfinal=errorlog+" insert_updateStudentsAttendence ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "insert_updateStudentsAttendence", errorlogfinal, "failed",ipaddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	public JSONObject deleteStudentsAttendence(String driver,String url,String user,String pwd,JSONObject absentdata,String ipaddress)
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
				String sql;
				sql=AttendenceQueries.deleteStudentAttendence;
				ps=con.prepareStatement(sql);
				ps.setString(1,absentdata.getString("attendenceid"));
				errorlog=ps.toString();
				//System.out.println(errorlog);
				int i=ps.executeUpdate();
				if(i>0)
				{
					response.put("status", "success");
				}
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" insert_updateStudentsAttendence "+e.getMessage();
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
						errorlogfinal=errorlog+" insert_updateStudentsAttendence ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "insert_updateStudentsAttendence", errorlogfinal, "failed",ipaddress);
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
