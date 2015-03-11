package com.product.home.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONObject;

import com.product.home.client.Jsonkeys;
import com.product.home.server.databaseDAO.DatabaseConnection;

public class MailCalls {
	TimeZone tz=null;	
	public MailCalls()
	{
		tz=TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+5:30"));
	}
	/**
	 * @param driver
	 * @param url
	 * @param user
	 * @param pwd
	 * @param ipAddress
	 * @param filterjsondata
	 * @return
	 */
	public JSONObject getEmplooyees_Adminsters(String driver, String url,String user, String pwd, String ipAddress, JSONObject filterjsondata) 
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
				String sql=MailQueries.getEmplooyees_Adminsters;
				if(!filterjsondata.getString(Jsonkeys.type).equalsIgnoreCase("0"))
					sql+="WHERE D.DESIGNATION_ID='"+filterjsondata.getString(Jsonkeys.type)+"'";
				ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				JSONArray datajson=new JSONArray();
				JSONObject tempjson;
				String name;
				while(rs.next())
				{
					tempjson=new JSONObject();
					if(rs.getString("MIDDLE_NAME").equalsIgnoreCase("-"))
						name=rs.getString("LAST_NAME")+" "+rs.getString("FIRST_NAME");
					else
						name=rs.getString("LAST_NAME")+" "+rs.getString("FIRST_NAME")+" "+rs.getString("MIDDLE_NAME");
					tempjson.put(Jsonkeys.name, name);
					tempjson.put(Jsonkeys.id, rs.getString("EMPLOYEE_ID"));
					tempjson.put(Jsonkeys.role, rs.getString("DESIGNATION_ID").replace("3", "2"));
					datajson.put(tempjson);
				}
				response.put(Jsonkeys.data, datajson);
				response.put(Jsonkeys.status, "success");
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getEmplooyees_Adminsters "+e.getMessage();
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
						errorlogfinal=errorlog+" getEmplooyees_Adminsters ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getEmplooyees_Adminsters", errorlogfinal, "failed",ipAddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	
	public JSONObject getMailslist(String driver, String url,String user, String pwd, String ipAddress, JSONObject filterjsondata) 
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
				String sql=MailQueries.getReceiverMailsList+" WHERE RECEIVER_ID=? AND MUM.ROLE_ID=? AND PLACEHOLEDER_ID='"+filterjsondata.getString(Jsonkeys.placeholderid)+"'";
				if(filterjsondata.getString(Jsonkeys.placeholderid).equalsIgnoreCase("2"))
					sql=MailQueries.getSenderMailsList+" WHERE SENDER_ID=? AND ROLE_ID=? AND STATUS='1'";
				ps=con.prepareStatement(sql);
				ps.setString(1, filterjsondata.getString(Jsonkeys.id));
				ps.setString(2, filterjsondata.getString(Jsonkeys.role));
				ResultSet rs=ps.executeQuery();
				JSONArray datajson=new JSONArray();
				JSONObject tempjson;
				String name="";
				while(rs.next())
				{
					tempjson=new JSONObject();
					tempjson.put(Jsonkeys.messageid, rs.getString("MESSAGE_ID"));
					tempjson.put(Jsonkeys.messagesubject, rs.getString("MESSAGE_SUBJECT"));
					tempjson.put(Jsonkeys.messagedate, rs.getString("MESSAGE_DATE"));
					tempjson.put(Jsonkeys.senderid, rs.getString("SENDER_ID"));
					tempjson.put(Jsonkeys.role, rs.getString("ROLE_ID"));
					if(filterjsondata.getString(Jsonkeys.placeholderid).equalsIgnoreCase("1"))
						tempjson.put(Jsonkeys.isread, rs.getString("ISREAD"));
					else
						tempjson.put(Jsonkeys.isread, "1");
					if(rs.getString("ROLE_ID").equalsIgnoreCase("1")||rs.getString("ROLE_ID").equalsIgnoreCase("2"))
					{
						ps1=con.prepareStatement(MailQueries.getEmployeename);
					}else if(rs.getString("ROLE_ID").equalsIgnoreCase("3"))
					{
						ps1=con.prepareStatement(MailQueries.getStudentname);
					}else// if(rs.getString("ROLE_ID").equalsIgnoreCase("4"))
					{
						ps1=con.prepareStatement(MailQueries.getParentname);
					}
					ps1.setString(1, rs.getString("SENDER_ID"));
					ResultSet rs1=ps1.executeQuery();
					if(rs1.next())
					{
						if(rs1.getString("MIDDLE_NAME").equalsIgnoreCase("-"))
							name=rs1.getString("LAST_NAME")+" "+rs1.getString("FIRST_NAME");
						else
							name=rs1.getString("LAST_NAME")+" "+rs1.getString("FIRST_NAME")+" "+rs1.getString("MIDDLE_NAME");
					}
					tempjson.put(Jsonkeys.name, name);
					datajson.put(tempjson);
				}
				response.put(Jsonkeys.data, datajson);
				response.put(Jsonkeys.status, "success");
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getMailslist "+e.getMessage();
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
						errorlogfinal=errorlog+" getMailslist ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getMailslist", errorlogfinal, "failed",ipAddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	public JSONObject storeMails(String driver, String url,String user, String pwd, String ipAddress, JSONObject mailsdata) 
	{
		Connection con=null;
		boolean errcheck=false;
		PreparedStatement ps=null,ps1=null;
		JSONObject response=new JSONObject();
		String currentdate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance(tz).getTime());
		String errorlog="start";
		String errorlogfinal="";
		try
		{
			response.put("status", "failed");
			con=DatabaseConnection.getConnection(driver, url, user, pwd);
			if(con!=null)
			{
				con.setAutoCommit(false);
				String sql=MailQueries.storeMaildata;
				ps=con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, mailsdata.getString(Jsonkeys.mail_subject));
				ps.setString(2, mailsdata.getString(Jsonkeys.mail_body));
				ps.setString(3, currentdate);
				ps.setString(4, mailsdata.getString(Jsonkeys.mail_senderid));//lid
				ps.setString(5, mailsdata.getString(Jsonkeys.mail_senderrole));//rid
				ps.executeUpdate();
				ResultSet rs=ps.getGeneratedKeys();
				int autoIncValue = -1;
				if(rs.next())
				{
				    autoIncValue = rs.getInt(1);
				    if(autoIncValue!=-1)
				    {
				    	JSONArray datajson=null;
				    	if(mailsdata.has(Jsonkeys.receiverid)&&mailsdata.getString(Jsonkeys.receiverid).equalsIgnoreCase("all"))
				    	{
				    		JSONObject receiveridsjson=getAllData(driver, url, user, pwd,ipAddress);
				    		if(receiveridsjson.getString(Jsonkeys.status).equalsIgnoreCase("success"))
				    			datajson=receiveridsjson.getJSONArray(Jsonkeys.data);
				    	}
				    	else
				    		datajson=mailsdata.getJSONArray(Jsonkeys.data);
				    	if(datajson!=null&&datajson.length()>0)
				    	{
				    		ps1=con.prepareStatement(MailQueries.storeMailMapping);
				    		JSONObject tempjson;
				    		for(int p=0;p<datajson.length();p++)
				    		{
				    			tempjson=datajson.getJSONObject(p);
				    			ps1.setString(1, autoIncValue+"");
				    			ps1.setString(2, "1");
				    			ps1.setString(3, tempjson.getString(Jsonkeys.receiverid));
				    			ps1.setString(4, "0");
				    			ps1.setString(5, tempjson.getString(Jsonkeys.receiverrole));
				    			ps1.addBatch();
				    		}
				    		ps1.executeBatch();
				    		con.commit();
				    		response.put(Jsonkeys.status, "success");
				    	}else
				    		con.rollback();
				    }
				}
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" storeMails "+e.getMessage();
			try
			{
				if(con!=null)
					con.rollback();
			}catch (Exception e1) {
			}
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
						errorlogfinal=errorlog+" storeMails ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "storeMails", errorlogfinal, "failed",ipAddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	public JSONObject getAllData(String driver, String url,String user, String pwd, String ipAddress)
	{
		JSONObject finalresponse=new JSONObject();
		try {
			finalresponse.put("status", "failed");
			JSONObject response=new JSONObject();
			JSONObject tjson=new JSONObject();
			tjson.put(Jsonkeys.search, "");
			tjson.put(Jsonkeys.parentid, "0");
			response=new StudentCalls().getStudentsList(driver, url, user, pwd,ipAddress,tjson);
			if(response.getString(Jsonkeys.status).equalsIgnoreCase("success"))
			{
				JSONArray datajsonarray=new JSONArray();
				JSONArray temparray=response.getJSONArray("students");
				JSONObject tempjson,tempjson1;
				for(int p=0;p<temparray.length();p++)
				{
					tempjson=temparray.getJSONObject(p);
					tempjson1=new JSONObject();
					tempjson1.put(Jsonkeys.receiverid, tempjson.getString("studentid"));
					tempjson1.put(Jsonkeys.role, "3");
					datajsonarray.put(tempjson1);
					tempjson1=new JSONObject();
					tempjson1.put(Jsonkeys.receiverid, tempjson.getString(Jsonkeys.parentid));
					tempjson1.put(Jsonkeys.role, "4");
					datajsonarray.put(tempjson1);
				}
				tempjson=new JSONObject();
				tempjson.put(Jsonkeys.type, "0");
				response=new MailCalls().getEmplooyees_Adminsters(driver, url, user, pwd,ipAddress,tempjson);
				if(response.getString(Jsonkeys.status).equalsIgnoreCase("success"))
				{
					temparray=response.getJSONArray(Jsonkeys.data);
					for(int p=0;p<temparray.length();p++)
					{
						tempjson=temparray.getJSONObject(p);
						tempjson1=new JSONObject();
						tempjson1.put(Jsonkeys.receiverid, tempjson.getString(Jsonkeys.id));
						tempjson1.put(Jsonkeys.role, tempjson.getString(Jsonkeys.role));
						datajsonarray.put(tempjson1);
					}
					finalresponse.put(Jsonkeys.data, datajsonarray);
					finalresponse.put(Jsonkeys.status, "success");
				}

			}
		}catch (Exception e) 
		{
		}
		return finalresponse;
	}
	public JSONObject getMailDetails(String driver, String url,String user, String pwd, String ipAddress, JSONObject filterjsondata) 
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
				String updatereadsql=MailQueries.updateReadSql;
				ps1=con.prepareStatement(updatereadsql);
				ps1.setString(1, "1");
				ps1.setString(2, filterjsondata.getString(Jsonkeys.receiverid));
				ps1.setString(3, filterjsondata.getString(Jsonkeys.role));
				ps1.setString(4, filterjsondata.getString(Jsonkeys.messageid));
				errorlog=ps1.toString();
				ps1.executeUpdate();
				String sql=MailQueries.getMessageDetails+" WHERE MESSAGE_ID='"+filterjsondata.getString(Jsonkeys.messageid)+"'";
				ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				JSONObject tempjson=new JSONObject();
				String name="";
				if(rs.next())
				{
					tempjson.put(Jsonkeys.messageid, rs.getString("MESSAGE_ID"));
					tempjson.put(Jsonkeys.messagesubject, rs.getString("MESSAGE_SUBJECT"));
					tempjson.put(Jsonkeys.messagedate, rs.getString("MESSAGE_DATE"));
					tempjson.put(Jsonkeys.senderid, rs.getString("SENDER_ID"));
					tempjson.put(Jsonkeys.role, rs.getString("ROLE_ID"));
					tempjson.put(Jsonkeys.mail_body, rs.getString("MESSAGE_BODY"));
					if(rs.getString("ROLE_ID").equalsIgnoreCase("1")||rs.getString("ROLE_ID").equalsIgnoreCase("2"))
					{
						ps1=con.prepareStatement(MailQueries.getEmployeename);
					}else if(rs.getString("ROLE_ID").equalsIgnoreCase("3"))
					{
						ps1=con.prepareStatement(MailQueries.getStudentname);
					}else// if(rs.getString("ROLE_ID").equalsIgnoreCase("4"))
					{
						ps1=con.prepareStatement(MailQueries.getParentname);
					}
					ps1.setString(1, rs.getString("SENDER_ID"));
					ResultSet rs1=ps1.executeQuery();
					if(rs1.next())
					{
						if(rs1.getString("MIDDLE_NAME").equalsIgnoreCase("-"))
							name=rs1.getString("LAST_NAME")+" "+rs1.getString("FIRST_NAME");
						else
							name=rs1.getString("LAST_NAME")+" "+rs1.getString("FIRST_NAME")+" "+rs1.getString("MIDDLE_NAME");
					}
					tempjson.put(Jsonkeys.name, name);
					response.put(Jsonkeys.data, tempjson);
					response.put(Jsonkeys.status, "success");
				}
				
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getMailDetails "+e.getMessage();
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
						errorlogfinal=errorlog+" getMailDetails ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getMailDetails", errorlogfinal, "failed",ipAddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	public JSONObject DeleteMail(String driver, String url,String user, String pwd, String ipAddress, JSONObject filterjsondata)
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
				con.setAutoCommit(false);
				String updatereadsql=MailQueries.deleteInbox;
				if(filterjsondata.getString(Jsonkeys.placeholderid).equalsIgnoreCase("2"))
					 updatereadsql=MailQueries.deleteSentMail;
				ps1=con.prepareStatement(updatereadsql);
				JSONArray msgarray=filterjsondata.getJSONArray(Jsonkeys.messageid);
				String msgid;
				for(int p=0;p<msgarray.length();p++)
				{
					msgid=msgarray.getString(p);
					ps1.setString(1, filterjsondata.getString(Jsonkeys.receiverid));
					ps1.setString(2, filterjsondata.getString(Jsonkeys.role));
					ps1.setString(3, msgid);
					ps1.addBatch();
				}
				errorlog=ps1.toString();
				int[] result=ps1.executeBatch();
				con.commit();
				response.put(Jsonkeys.status, "success");
				
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			try
			{
				if(con!=null)
					con.rollback();
			}catch (Exception e1) {
			}
			errorlogfinal=errorlog+" DeleteMail "+e.getMessage();
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
						errorlogfinal=errorlog+" DeleteMail ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "DeleteMail", errorlogfinal, "failed",ipAddress);
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
