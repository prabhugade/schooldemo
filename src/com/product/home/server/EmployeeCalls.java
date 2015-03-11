package com.product.home.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;

import com.product.home.client.Jsonkeys;
import com.product.home.server.databaseDAO.DatabaseConnection;
import com.product.home.server.util.Encription;

public class EmployeeCalls {

	public JSONObject getDesignations(String driver,String url,String user,String pwd,String ipaddress)
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
				String sql=EmployeeQueries.getDesignation+" where STATUS='1'";
				ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				JSONObject designationsjson=new JSONObject();
				while(rs.next())
				{
					designationsjson.put(rs.getString("DESIGNATION_NAME") , rs.getString("DESIGNATION_ID"));
				}
				response.put("data", designationsjson);
				response.put("status", "success");
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getDesignations "+e.getMessage();
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
						errorlogfinal=errorlog+" getDesignations ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getDesignations", errorlogfinal, "failed",ipaddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	public JSONObject insert_EmployeeData(String driver,String url,String user,String pwd,JSONObject emplyeedata,String ipaddress)
	{
		Connection con=null;
		boolean errcheck=false;
		PreparedStatement ps=null,ps1=null,ps3=null;
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
				try
				{
					String sql;
					if(emplyeedata.getString("employeeid").equalsIgnoreCase("0"))
					{
						sql=StudentQueries.insertPersonData;
						ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
						String personid=emplyeedata.getString("employeeid");
						ps.setString(1,emplyeedata.getString("fname"));
						ps.setString(2,emplyeedata.getString("mname"));
						ps.setString(3,emplyeedata.getString("lname"));
						ps.setString(4,emplyeedata.getString("dob"));
						ps.setInt(5,Integer.parseInt(emplyeedata.getString("gender")));
						ps.setString(6,"-");
						ps.setString(7,emplyeedata.getString("address1"));
						ps.setString(8,emplyeedata.getString("address2"));
						ps.setString(9,emplyeedata.getString("city"));
						ps.setString(10,emplyeedata.getString("state"));
						ps.setString(11,emplyeedata.getString("pincode"));
						ps.setString(12,emplyeedata.getString("country"));
						ps.setString(13,emplyeedata.getString("phone1"));
						ps.setString(14,emplyeedata.getString("phone2"));
						ps.setString(15,emplyeedata.getString("bloodgroup"));
						ps.setString(16,"-");
						ps.setString(17,emplyeedata.getString("nationality"));
						ps.setString(18,"-");
						ps.setString(19,emplyeedata.getString("email"));
						errorlog=ps.toString();
						int i=ps.executeUpdate();
						if(i>0)
						{
							if(emplyeedata.getString("employeeid").equalsIgnoreCase("0"))
							{
								ResultSet keys = ps.getGeneratedKeys();    
								if(keys.next())
								{
									personid=keys.getInt(1)+"";
								}
								ps1=con.prepareStatement(EmployeeQueries.insertEmployeedata,Statement.RETURN_GENERATED_KEYS);
								ps1.setInt(1,Integer.parseInt(personid));
								ps1.setString(2,emplyeedata.getString("salary"));
								ps1.setString(3,emplyeedata.getString("qualification"));
								ps1.setString(4,emplyeedata.getString("experiance"));
								ps1.setString(5,emplyeedata.getString("employeeno"));
								ps1.setString(6,emplyeedata.getString("joiningdate"));
								ps1.setString(7,emplyeedata.getString("photoid"));
								ps1.setString(8,emplyeedata.getString("designationid"));
								ps1.setString(9,emplyeedata.getString("subjectid"));
								ps1.setString(10,emplyeedata.getString("expdesc"));
								errorlog=ps1.toString();
								int p=ps1.executeUpdate();
								if(p>0)
								{
									String employeeid=null;
									keys = ps1.getGeneratedKeys();    
									if(keys.next())
									{
										employeeid=keys.getInt(1)+"";
									}
									if(employeeid!=null)
									{
										ps3=con.prepareStatement(StudentQueries.insertLoginData);
										ps3.setString(1, emplyeedata.getString("phone1"));
										ps3.setString(2, new Encription().computeHash(emplyeedata.getString("phone1")));
										String role=emplyeedata.getString("designationid");
										if(!role.equalsIgnoreCase("1"))
											role="2";
										ps3.setString(3, role);
										ps3.setString(4, "1");
										ps3.setString(5, employeeid);
										errorlog=ps3.toString();
										p=ps3.executeUpdate();
										if(p>0)
										{
											con.commit();
											response.put("status", "success");	
										}else
											con.rollback();
									}else
									{
										con.rollback();
									}
								}else
								{
									System.out.println("else step1");
									con.rollback();
								}
							}else					
								response.put("status", "success");
						}
					}else
						System.out.println("else");
				}catch (SQLException se) {
					//System.out.println("se "+se.getMessage());
					//System.out.println("errorcode="+se.getErrorCode());
					if(se.getErrorCode()==1062)
					{
						try
						{
							response.put("status", "Phone number or Employee number already exist");
						}catch (Exception e) {
						}
					}
				}catch (Exception e) {
					con.rollback();
				}
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" insert_EmployeeData "+e.getMessage();
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
						errorlogfinal=errorlog+" insert_EmployeeData ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "insert_EmployeeData", errorlogfinal, "failed",ipaddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	public JSONObject getEmployeeDetails(String driver,String url,String user,String pwd,String EmployeeId,String ipaddress)
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
				String sql=EmployeeQueries.getEmployeedata;
				ps=con.prepareStatement(sql);
				ps.setString(1, EmployeeId);
				errorlog=ps.toString();
				ResultSet rs=ps.executeQuery();
				JSONObject employeedata=new JSONObject();
				if(rs.next())
				{
					employeedata.put("fname", rs.getString("FIRST_NAME"));
					if(rs.getString("MIDDLE_NAME").equalsIgnoreCase("-"))
						employeedata.put("mname", "");
					else
						employeedata.put("mname", rs.getString("MIDDLE_NAME"));
					employeedata.put("lname", rs.getString("LAST_NAME"));
					String middlename="";
					if(!rs.getString("MIDDLE_NAME").equalsIgnoreCase("-"))
						middlename=rs.getString("MIDDLE_NAME");
					employeedata.put("name", rs.getString("LAST_NAME")+" "+rs.getString("FIRST_NAME")+" "+middlename);
					employeedata.put("dob", rs.getString("DOB"));
					employeedata.put("gender", rs.getString("GENDER").replace("1", "Male").replace("0", "Female"));
					employeedata.put("religion", rs.getString("RELIGION"));
					employeedata.put("address1", rs.getString("ADDRESS1"));
					employeedata.put("address2", rs.getString("ADDRESS2"));
					employeedata.put("city", rs.getString("CITY"));
					employeedata.put("state", rs.getString("STATE"));
					employeedata.put("pincode", rs.getString("PINCODE"));
					employeedata.put("country", rs.getString("COUNTRY"));
					employeedata.put("phone1", rs.getString("PHONE1"));
					employeedata.put("phone2", rs.getString("PHONE2"));
					employeedata.put("bloodgroup", rs.getString("BLOOD_GROUP"));
					employeedata.put("birthplace", rs.getString("BIRTH_PLACE"));
					employeedata.put("nationality", rs.getString("NATIONALITY"));
					employeedata.put("language", rs.getString("LANGUAGE"));
					employeedata.put("email", rs.getString("EMAIL"));
					employeedata.put("personid", rs.getString("PERSON_ID"));
					employeedata.put("salary", rs.getString("SALARY"));
					employeedata.put("qualification", rs.getString("QUALIFICATION"));
					employeedata.put("experience", rs.getString("EXPERIENCE"));
					employeedata.put("employeeno", rs.getString("EMPLOYEE_NO"));
					employeedata.put("employeeid", rs.getString("EMPLOYEE_ID"));
					employeedata.put("employeedate", rs.getString("EMPLOYEE_DATE"));
					employeedata.put("photo", rs.getString("PHOTO"));
					employeedata.put("designationid", rs.getString("DESIGNATION_ID"));
					employeedata.put("subjectid", rs.getString("SUBJECT_ID"));
					employeedata.put("subjectname", rs.getString("SUBJECT"));
					employeedata.put("employeedesc", rs.getString("EMPLOYEE_DESC"));
					employeedata.put("status", rs.getString("STATUS"));
					employeedata.put("designationname", rs.getString("DESIGNATION_NAME"));
				}
				response.put(Jsonkeys.employeedata, employeedata);
				response.put("status", "success");
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getEmployeeDetails "+e.getMessage();
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
						errorlogfinal=errorlog+" getEmployeeDetails ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getEmployeeDetails", errorlogfinal, "failed",ipaddress);
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
