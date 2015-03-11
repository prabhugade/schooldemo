package com.product.home.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;

import com.product.home.client.Jsonkeys;
import com.product.home.server.databaseDAO.DatabaseConnection;

public class FeeCalls {
	
	public JSONObject getCourse_Section_Fee(String driver,String url,String user,String pwd,String Courseid,String Sectionid,String ipaddress)
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
				String sqlcondition="";
				if(!Sectionid.equalsIgnoreCase("all"))
					sqlcondition=" AND S.SECTION_ID='"+Sectionid+"'";
				String sql=FeeQueries.getCourse_Section_Fee+" where COURSE_ID=? "+sqlcondition+" UNION SELECT '0' AS COURSE_SECTION_FEE_ID,C.COURSE_ID,S.SECTION_ID,'0' AS FEE,S.SECTION_NAME FROM COURSES AS C JOIN SECTIONS AS S ON FIND_IN_SET(S.SECTION_ID, C.SECTION_ID) > 0 WHERE C.COURSE_ID = ? AND S.SECTION_ID NOT IN(SELECT CSE.`SECTION_ID` FROM `COURSE_SECTION_FEE` as CSE join SECTIONS as S on S.SECTION_ID=CSE.SECTION_ID WHERE CSE.COURSE_ID = ? "+sqlcondition+") "+sqlcondition;
				ps=con.prepareStatement(sql);
				ps.setString(1, Courseid);
				ps.setString(2, Courseid);	
				ps.setString(3, Courseid);	
				errorlog=ps.toString();
				ResultSet rs=ps.executeQuery();
				JSONArray datajsonarray=new JSONArray();
				JSONObject tempjson;
				while(rs.next())
				{
					tempjson=new JSONObject();
					tempjson.put("id", rs.getString("COURSE_SECTION_FEE_ID"));
					tempjson.put("courseid", rs.getString("COURSE_ID"));
					tempjson.put("sectionid", rs.getString("SECTION_ID"));
					tempjson.put("sectionname", rs.getString("SECTION_NAME"));
					tempjson.put("fee", rs.getString("FEE"));
					datajsonarray.put(tempjson);
				}
				response.put("data", datajsonarray);
				response.put("status", "success");
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getCourse_Section_Fee "+e.getMessage();
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
						errorlogfinal=errorlog+" getCourse_Section_Fee ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getCourse_Section_Fee", errorlogfinal, "failed",ipaddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	/**
	 * 
	 * @param driver
	 * @param url
	 * @param user
	 * @param pwd
	 * @param feeArray
	 * @param ipaddress
	 * @return
	 */
	public JSONObject insert_updateFee(String driver,String url,String user,String pwd,JSONArray feeArray,String ipaddress)
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
				con.setAutoCommit(false);
				ps=con.prepareStatement(FeeQueries.Insert_Update_Fee);
				JSONObject tempjson;
				for(int p=0;p<feeArray.length();p++)
				{
					tempjson=feeArray.getJSONObject(p);
					ps.setString(1,tempjson.getString("courseid"));
					ps.setString(2,tempjson.getString("sectionid"));
					ps.setString(3,tempjson.getString("fee"));
					ps.setString(4,tempjson.getString("fee"));
					ps.toString();
					ps.addBatch();
				}
				ps.executeBatch();
				con.commit();
				response.put("status", "success");	
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			try
			{
				con.rollback();
			}catch (Exception e1) {
			}
			errorlogfinal=errorlog+" insert_updateFee "+e.getMessage();
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
						errorlogfinal=errorlog+" insert_updateFee ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "insert_updateFee", errorlogfinal, "failed",ipaddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	public JSONObject getStudentFeeDetails(String driver,String url,String user,String pwd,String Studentid,String ipaddress)
	{
		Connection con=null;
		boolean errcheck=false;
		PreparedStatement ps=null,ps1=null;
		JSONObject response=new JSONObject();
		String errorlog="start";
		String errorlogfinal="";
		try
		{
			response.put("status", "failed");
			con=DatabaseConnection.getConnection(driver, url, user, pwd);
			if(con!=null)
			{
				String sql=FeeQueries.getStudentFee;
				ps=con.prepareStatement(sql);
				ps.setString(1, Studentid);
				errorlog=ps.toString();
				ResultSet rs=ps.executeQuery();
				JSONObject datajson;
				if(rs.next())
				{
					datajson=new JSONObject();
					String middlename="";
					if(!rs.getString("MIDDLE_NAME").equalsIgnoreCase("-"))
						middlename=rs.getString("MIDDLE_NAME");
					datajson.put("name", rs.getString("LAST_NAME")+" "+rs.getString("FIRST_NAME")+" "+middlename);
					datajson.put("fee", rs.getString("FEE"));
					JSONArray datajsonarray=new JSONArray();
					sql=FeeQueries.getStudentPayFee;
					ps1=con.prepareStatement(sql);
					ps1.setString(1, Studentid);
					errorlog=ps1.toString();
					ResultSet reset=ps1.executeQuery();
					JSONObject tempjson;
					while(reset.next())
					{
						tempjson=new JSONObject();
						tempjson.put("datetime", reset.getString("DATETIME"));
						tempjson.put("fee", reset.getString("FEE"));
						datajsonarray.put(tempjson);
					}
					datajson.put("payedfeedata", datajsonarray);
					response.put("data", datajson);
					response.put("status", "success");
				}
				
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getStudentFeeDetails "+e.getMessage();
		}
		finally
		{
			try 
			{
				if(ps!=null)
					ps.close();
				if(ps1!=null)
					ps1.close();
				if(con!=null)
					con.close();
				if(!errorlogfinal.equals("")||errcheck)
				{
					if(errorlogfinal.equals(""))
						errorlogfinal=errorlog+" getStudentFeeDetails ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getStudentFeeDetails", errorlogfinal, "failed",ipaddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	/**
	 * 
	 * @param driver
	 * @param url
	 * @param user
	 * @param pwd
	 * @param studentid
	 * @param fee
	 * @param loginid
	 * @param ipaddress
	 * @return
	 */
	public JSONObject insertStudentPayedFee(String driver,String url,String user,String pwd,String studentid,String fee,String loginid,String ipaddress)
	{
		Connection con=null;
		boolean errcheck=false;
		PreparedStatement ps=null;
		JSONObject response=new JSONObject();
		String errorlog="start";
		String errorlogfinal="";
		String curdate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance(new DatabaseConnection().tz).getTime());
		try
		{
			response.put("status", "failed");
			con=DatabaseConnection.getConnection(driver, url, user, pwd);
			if(con!=null)
			{
				ps=con.prepareStatement(FeeQueries.insertStudentpayFee);
				ps.setString(1, studentid);
				ps.setString(2, curdate);
				ps.setString(3, fee);
				ps.setString(4, loginid);
				errorlog=ps.toString();
				int i=ps.executeUpdate();
				if(i>0)
					response.put("status", "success");	
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			try
			{
				con.rollback();
			}catch (Exception e1) {
			}
			errorlogfinal=errorlog+" insertStudentPayedFee "+e.getMessage();
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
						errorlogfinal=errorlog+" insertStudentPayedFee ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "insertStudentPayedFee", errorlogfinal, "failed",ipaddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	public JSONObject getStudentsBasedOnCourse_Section(String driver,String url,String user,String pwd,String Courseid,String Sectionid,String ipaddress)
	{
		Connection con=null;
		boolean errcheck=false;
		PreparedStatement ps=null,ps1=null;
		JSONObject response=new JSONObject();
		String errorlog="start";
		String errorlogfinal="";
		try
		{
			response.put("status", "failed");
			con=DatabaseConnection.getConnection(driver, url, user, pwd);
			if(con!=null)
			{
				String sql=FeeQueries.getStudentsBasedOnCourse_Sections;
				if(!Courseid.equalsIgnoreCase("0")&&!Sectionid.equalsIgnoreCase("0"))
					sql+=" WHERE S.COURSE_ID='"+Courseid+"' AND S.SECTION_ID='"+Sectionid+"'";
				else if(Courseid.equalsIgnoreCase("0")&&!Sectionid.equalsIgnoreCase("0"))
					sql+=" WHERE S.SECTION_ID='"+Sectionid+"'";
				else if(!Courseid.equalsIgnoreCase("0")&&Sectionid.equalsIgnoreCase("0"))
					sql+=" WHERE S.COURSE_ID='"+Courseid+"'";
				ps=con.prepareStatement(sql);
				errorlog=ps.toString();
				ResultSet rs=ps.executeQuery();
				JSONObject datajson;
				JSONArray dataarray=new JSONArray();
				while(rs.next())
				{
					datajson=new JSONObject();
					String middlename="";
					if(!rs.getString("MIDDLE_NAME").equalsIgnoreCase("-"))
						middlename=rs.getString("MIDDLE_NAME");
					datajson.put("name", rs.getString("LAST_NAME")+" "+rs.getString("FIRST_NAME")+" "+middlename);
					datajson.put("studentid", rs.getString("STUDENT_ID"));
					datajson.put(Jsonkeys.parentid, rs.getString("PARENT_ID"));
					dataarray.put(datajson);
				}
				response.put("data", dataarray);
				response.put("status", "success");
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getStudentFeeDetails "+e.getMessage();
		}
		finally
		{
			try 
			{
				if(ps!=null)
					ps.close();
				if(ps1!=null)
					ps1.close();
				if(con!=null)
					con.close();
				if(!errorlogfinal.equals("")||errcheck)
				{
					if(errorlogfinal.equals(""))
						errorlogfinal=errorlog+" getStudentFeeDetails ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getStudentFeeDetails", errorlogfinal, "failed",ipaddress);
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
