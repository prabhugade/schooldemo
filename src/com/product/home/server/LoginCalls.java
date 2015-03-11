package com.product.home.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.product.home.client.Jsonkeys;
import com.product.home.server.databaseDAO.DatabaseConnection;
import com.product.home.server.databaseDAO.DatabaseQueries;
import com.product.home.server.util.Encription;

public class LoginCalls 
{
	public JSONObject loginCheck(String driver,String url,String user,String pwd,JSONObject loginjson)
	{
		JSONObject resultjson=new JSONObject();
		Connection con=null;
		PreparedStatement ps=null,ps1=null;
		try
		{
			resultjson.put("status", "failed");
			con=DatabaseConnection.getConnection(driver, url, user, pwd);
			if(con!=null)
			{
				String sql=DatabaseQueries.loginCheck;
				ps=con.prepareStatement(sql);
				ps.setString(1, loginjson.getString("username"));
				ps.setString(2, new Encription().computeHash(loginjson.getString("password")));
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					resultjson.put("role", rs.getString("ROLE_ID"));
					resultjson.put("loginid", rs.getString("LOGIN_ID"));
					resultjson.put("id", rs.getString("STUDENT_PARENT_ID"));
					if(rs.getString("ROLE_ID").equalsIgnoreCase("3"))
					{
						ps1=con.prepareStatement(DatabaseQueries.StudentLoginData);
						ps1.setString(1, rs.getString("LOGIN_ID"));
						ResultSet rs1=ps1.executeQuery();
						String name="";
						if(rs1.next())
						{
							JSONArray array=new JSONArray();
							JSONObject tempjson=new JSONObject();
							String middlename="";
							if(!rs1.getString("MIDDLE_NAME").equalsIgnoreCase("-"))
								middlename=" "+rs1.getString("MIDDLE_NAME");
							name=rs1.getString("LAST_NAME")+" "+rs1.getString("FIRST_NAME")+middlename;
							tempjson.put(Jsonkeys.studentid, rs1.getString("S.STUDENT_ID"));
							tempjson.put("courseid", rs1.getString("COURSE_ID"));
							tempjson.put("sectionid", rs1.getString("SECTION_ID"));
							array.put(tempjson);
							resultjson.put(Jsonkeys.name, name);
							resultjson.put(Jsonkeys.studentdata, array);
							resultjson.put("status", "success");
						}
					}else if(rs.getString("ROLE_ID").equalsIgnoreCase("4"))
					{
						ps1=con.prepareStatement(DatabaseQueries.ParentLoginData);
						ps1.setString(1, rs.getString("LOGIN_ID"));
						ResultSet rs1=ps1.executeQuery();
						JSONArray array=new JSONArray();
						JSONObject tempjson;
						String name="";
						while(rs1.next())
						{
							tempjson=new JSONObject();
							String middlename="";
							if(!rs1.getString("MIDDLE_NAME").equalsIgnoreCase("-"))
								middlename=" "+rs1.getString("MIDDLE_NAME");
							name=rs1.getString("LAST_NAME")+" "+rs1.getString("FIRST_NAME")+middlename;
							tempjson.put("courseid", rs1.getString("COURSE_ID"));
							tempjson.put("sectionid", rs1.getString("SECTION_ID"));
							tempjson.put(Jsonkeys.studentid, rs1.getString("S.STUDENT_ID"));
							array.put(tempjson);
						}
						resultjson.put(Jsonkeys.name, name);
						resultjson.put(Jsonkeys.studentdata, array);
						resultjson.put("status", "success");
					}else
					{
						ps1=con.prepareStatement(DatabaseQueries.Employee_admin_LoginData);
						ps1.setString(1, rs.getString("LOGIN_ID"));
						ResultSet rs1=ps1.executeQuery();
						JSONArray array=new JSONArray();
						JSONObject tempjson;
						String name="";
						while(rs1.next())
						{
							tempjson=new JSONObject();
							String middlename="";
							if(!rs1.getString("MIDDLE_NAME").equalsIgnoreCase("-"))
								middlename=" "+rs1.getString("MIDDLE_NAME");
							name=rs1.getString("LAST_NAME")+" "+rs1.getString("FIRST_NAME")+middlename;
						}
						resultjson.put(Jsonkeys.name, name);
						resultjson.put("status", "success");
					}
						
				}else
				{
					resultjson.put("status", "login failed");
				}
			}else
			{
				System.out.println("failed to connect the database"+url);
			}
			
		}catch (Exception e) 
		{
		}finally
		{
			
			try 
			{
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				}
			con=null;
		}
		return resultjson;
	}
	public JSONObject urlCheck(String driver,String url,String user,String pwd,String requrl)
	{
		JSONObject resultjson=new JSONObject();
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
			resultjson.put("status", "failed");
			con=DatabaseConnection.getConnection(driver, url, user, pwd);
			if(con!=null)
			{
				String sql=DatabaseQueries.getUrl;
				ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				boolean check=true;
				while(rs.next())
				{
					String dburl=rs.getString("Url");
					if(requrl.contains(dburl))
					{
						check=false;
						resultjson.put("type", rs.getString("Type"));
						resultjson.put("schoolname", rs.getString("SCHOOL_NAME"));
						resultjson.put("image", rs.getString("IMAGE_URL"));
						resultjson.put("status", "success");
					}
				}
				if(check)
					resultjson.put("status", "not Configure");
			}else
			{
			}
			
		}catch (Exception e) 
		{
		}finally
		{
			
			try 
			{
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				}
			con=null;
		}
		return resultjson;
	}
	
	public JSONObject changePassword(String driver,String url,String user,String pwd,JSONObject changepss)
	{
		JSONObject resultjson=new JSONObject();
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
			resultjson.put("status", "failed");
			con=DatabaseConnection.getConnection(driver, url, user, pwd);
			if(con!=null)
			{
				String sql=DatabaseQueries.oldpasswordChecking;
				ps=con.prepareStatement(sql);
				ps.setString(1,changepss.getString("loginid"));
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					if(new Encription().computeHash(changepss.getString("oldpassword")).equals(rs.getString("PASSWORD")))
					{
						String sql1=DatabaseQueries.passwordChangeBasedonLoginId;
						ps=con.prepareStatement(sql1);
						ps.setString(1,new Encription().computeHash(changepss.getString("newpassword")));
						ps.setString(2,changepss.getString("loginid"));
						int i=ps.executeUpdate();
						if(i>0)
						{
							resultjson.put("status", "success");
						}
					}
					else
					{
						resultjson.put("status", "notsame");
					}
				}
			}else
			{
			}

		}catch (Exception e) 
		{
		}finally
		{

			try 
			{
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
			}
			con=null;
		}
		return resultjson;
	}
}
