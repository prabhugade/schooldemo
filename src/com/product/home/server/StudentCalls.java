package com.product.home.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import com.product.home.client.Jsonkeys;
import com.product.home.server.databaseDAO.DatabaseConnection;
import com.product.home.server.util.Encription;

public class StudentCalls 
{
	public JSONObject getStudentsList(String driver,String url,String user,String pwd,String ipaddress,JSONObject serachdata)
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
				String sql=StudentQueries.getStudentsList;
				if(serachdata.has(Jsonkeys.parentid)&&!serachdata.getString(Jsonkeys.parentid).equalsIgnoreCase("0"))
					sql+=" WHERE S.PARENT_ID='"+serachdata.getString(Jsonkeys.parentid)+"'";
				else if(serachdata.has(Jsonkeys.studentid)&&!serachdata.getString(Jsonkeys.studentid).equalsIgnoreCase("0"))
					sql+=" WHERE S.STUDENT_ID='"+serachdata.getString(Jsonkeys.studentid)+"'";
				ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				JSONArray studentsarray=new JSONArray();
				JSONObject studentdata;
				while(rs.next())
				{
					studentdata=new JSONObject();
					studentdata.put("admisiondate", rs.getString("ADMISION_DATE"));
					studentdata.put("fname", rs.getString("FIRST_NAME"));
					if(rs.getString("MIDDLE_NAME").equalsIgnoreCase("-"))
						studentdata.put("mname", "");
					else
						studentdata.put("mname", rs.getString("MIDDLE_NAME"));
					studentdata.put("lname", rs.getString("LAST_NAME"));
					studentdata.put("course", rs.getString("COURSE_NAME"));
					studentdata.put("section", rs.getString("SECTION_NAME"));
					studentdata.put("gender", rs.getString("GENDER").replace("1", "Male").replace("0", "Female"));
					studentdata.put("admisionno", rs.getString("ADMISION_NO"));
					studentdata.put("studentid", rs.getString("STUDENT_ID"));
					studentdata.put(Jsonkeys.parentid, rs.getString("PARENT_ID"));
					studentsarray.put(studentdata);
				}
				response.put("students", studentsarray);
				response.put("status", "success");
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getStudentsList "+e.getMessage();
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
						errorlogfinal=errorlog+" getStudentsList ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getStudentsList", errorlogfinal, "failed",ipaddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	public JSONObject getStudentDetails(String driver,String url,String user,String pwd,String StudentId,String ipaddress)
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
				String sql=StudentQueries.getStudentDetails;
				ps=con.prepareStatement(sql);
				ps.setString(1, StudentId);
				errorlog=ps.toString();
				ResultSet rs=ps.executeQuery();
				JSONObject studentdata=new JSONObject();
				JSONObject parentdata=new JSONObject();
				if(rs.next())
				{
					studentdata.put("fname", rs.getString("FIRST_NAME"));
					if(rs.getString("MIDDLE_NAME").equalsIgnoreCase("-"))
						studentdata.put("mname", "");
					else
						studentdata.put("mname", rs.getString("MIDDLE_NAME"));
					studentdata.put("lname", rs.getString("LAST_NAME"));
					studentdata.put("course", rs.getString("COURSE_NAME"));
					studentdata.put("courseid", rs.getInt("COURSE_ID")+"");
					studentdata.put("dob", rs.getString("DOB"));
					studentdata.put("gender", rs.getString("GENDER").replace("1", "Male").replace("0", "Female"));
					studentdata.put("bloodgroup", rs.getString("BLOOD_GROUP"));
					studentdata.put("birthplace", rs.getString("BIRTH_PLACE"));
					studentdata.put("nationality", rs.getString("NATIONALITY"));
					studentdata.put("language", rs.getString("LANGUAGE"));
					studentdata.put("religion", rs.getString("RELIGION"));
					studentdata.put("sectionid", rs.getInt("SECTION_ID")+"");
					studentdata.put("section", rs.getString("SECTION_NAME"));
					studentdata.put("address1", rs.getString("ADDRESS1"));
					studentdata.put("address2", rs.getString("ADDRESS2"));
					studentdata.put("city", rs.getString("CITY"));
					studentdata.put("state", rs.getString("STATE"));
					studentdata.put("pincode", rs.getString("PINCODE"));
					studentdata.put("country", rs.getString("COUNTRY"));
					studentdata.put("phone1", rs.getString("PHONE1"));
					studentdata.put("phone2", rs.getString("PHONE2"));
					studentdata.put("email", rs.getString("EMAIL"));
					studentdata.put("photo", rs.getString("PHOTO"));
					studentdata.put("admisionno", rs.getString("ADMISION_NO"));
					studentdata.put("admisiondate", rs.getString("ADMISION_DATE"));
					studentdata.put("parentid", rs.getString("PARENT_ID"));
					studentdata.put("fee", rs.getString("FEE"));
					studentdata.put("studentid", StudentId);
					ps1=con.prepareStatement(StudentQueries.getParentData);
					ps1.setString(1, rs.getString("PARENT_ID"));
					errorlog=ps1.toString();
					ResultSet reset=ps1.executeQuery();
					if(reset.next())
					{
						parentdata.put(Jsonkeys.fname, reset.getString("FIRST_NAME"));
						parentdata.put(Jsonkeys.lname, reset.getString("LAST_NAME"));
						parentdata.put(Jsonkeys.dob, reset.getString("DOB"));
						parentdata.put(Jsonkeys.gender, reset.getString("GENDER").replace("1", "Male").replace("0", "Female"));
						parentdata.put(Jsonkeys.address1, reset.getString("ADDRESS1"));
						parentdata.put(Jsonkeys.address2, reset.getString("ADDRESS2"));
						parentdata.put(Jsonkeys.city, reset.getString("CITY"));
						parentdata.put(Jsonkeys.state, reset.getString("STATE"));
						parentdata.put(Jsonkeys.pincode, reset.getString("PINCODE"));
						parentdata.put(Jsonkeys.country, reset.getString("COUNTRY"));
						parentdata.put(Jsonkeys.phone1, reset.getString("PHONE1"));
						parentdata.put(Jsonkeys.phone2, reset.getString("PHONE2"));
						parentdata.put(Jsonkeys.email, reset.getString("EMAIL"));
						parentdata.put(Jsonkeys.education, reset.getString("EDUCATION"));
						parentdata.put(Jsonkeys.occupation, reset.getString("OCCUPATION"));
						parentdata.put(Jsonkeys.relationtype, reset.getString("RELATIONTYPE"));
						parentdata.put(Jsonkeys.income, reset.getString("INCOME"));
						response.put(Jsonkeys.parentdata, parentdata);
					}
				}
				response.put(Jsonkeys.studentdata, studentdata);
				response.put("status", "success");
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getCourseDetails "+e.getMessage();
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
						errorlogfinal=errorlog+" getStudentDetails ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getStudentDetails", errorlogfinal, "failed",ipaddress);
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
	 * @param studentdata in jsonobject
	 * 		 		studentdata.put("admisiondate", admisiondate);
					studentdata.put("fname", fname);
					studentdata.put("mname", mname);
					studentdata.put("lname", lname);
					studentdata.put("courseid", courseid);
					studentdata.put("dob", dob);
					studentdata.put("gender", 0-female/1-male);
					studentdata.put("bloodgroup", bloodgroup);
					studentdata.put("birthplace", birthplace);
					studentdata.put("nationality", nationality);
					studentdata.put("language", language);
					studentdata.put("religion", religion);
					studentdata.put("sectionid", sectionid);
					studentdata.put("address1", address1);
					studentdata.put("address2", address2);
					studentdata.put("city", city);
					studentdata.put("state", state);
					studentdata.put("pincode", pincode);
					studentdata.put("country", country);
					studentdata.put("phone1", phone1);
					studentdata.put("phone2", phone2);
					studentdata.put("email", email);
					studentdata.put("studentid", 0-insertion/studentid);
	 * @param ipaddress
	 * @return
	 */
	public JSONObject insert_StudentData(String driver,String url,String user,String pwd,JSONObject studentdata,String ipaddress)
	{
		Connection con=null;
		boolean errcheck=false;
		PreparedStatement ps=null,ps1=null,ps2=null,ps3=null;
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
					if(studentdata.getString("studentid").equalsIgnoreCase("0"))
					{
						sql=StudentQueries.insertPersonData;
						ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					}
					else
					{
						sql=StudentQueries.insertStudentData;
						ps=con.prepareStatement(sql);
					}
					String personid=studentdata.getString("studentid");
					ps.setString(1,studentdata.getString("fname"));
					ps.setString(2,studentdata.getString("mname"));
					ps.setString(3,studentdata.getString("lname"));
					ps.setString(4,studentdata.getString("dob"));
					ps.setInt(5,Integer.parseInt(studentdata.getString("gender")));
					ps.setString(6,studentdata.getString("religion"));
					ps.setString(7,studentdata.getString("address1"));
					ps.setString(8,studentdata.getString("address2"));
					ps.setString(9,studentdata.getString("city"));
					ps.setString(10,studentdata.getString("state"));
					ps.setString(11,studentdata.getString("pincode"));
					ps.setString(12,studentdata.getString("country"));
					ps.setString(13,studentdata.getString("phone1"));
					ps.setString(14,studentdata.getString("phone2"));
					ps.setString(15,studentdata.getString("bloodgroup"));
					ps.setString(16,studentdata.getString("birthplace"));
					ps.setString(17,studentdata.getString("nationality"));
					ps.setString(18,studentdata.getString("language"));
					ps.setString(19,studentdata.getString("email"));
					errorlog=ps.toString();
					int i=ps.executeUpdate();
					if(i>0)
					{
						if(studentdata.getString("studentid").equalsIgnoreCase("0"))
						{
							ResultSet keys = ps.getGeneratedKeys();    
							if(keys.next())
							{
								personid=keys.getInt(1)+"";
							}
							ps1=con.prepareStatement(StudentQueries.insertStudentData,Statement.RETURN_GENERATED_KEYS);
							ps1.setInt(1,Integer.parseInt(personid));
							ps1.setString(2,studentdata.getString("admisiondate"));
							ps1.setInt(3,Integer.parseInt(studentdata.getString("courseid")));
							ps1.setInt(4,Integer.parseInt(studentdata.getString("sectionid")));
							ps1.setString(5,studentdata.getString("fee"));
							errorlog=ps1.toString();
							int p=ps1.executeUpdate();
							if(p>0)
							{
								String studentid=null;
								keys = ps1.getGeneratedKeys();    
								if(keys.next())
								{
									studentid=keys.getInt(1)+"";
								}
								if(studentid!=null)
								{
									studentdata.put("parentid", "0");
									studentdata.put("studentid", studentid);
									ps3=con.prepareStatement(StudentQueries.insetLoginData_student);
									ps3.setString(1, studentid);
									ps3.setString(2, studentid);
									errorlog=ps3.toString();
									p=ps3.executeUpdate();
									if(p>0)
									{
										JSONObject result=insert_ParentInfo(driver, url, user, pwd, studentdata, ipaddress);
										if(result.getString("status").equalsIgnoreCase("success"))
										{
											ps2=con.prepareStatement(StudentQueries.updateStudentParentid);
											ps2.setString(1, result.getString("parentid"));
											ps2.setString(2, studentid);
											errorlog=ps2.toString();
											p=ps2.executeUpdate();
											if(p>0)
											{
												con.commit();
												response.put("status", "success");	
											}
											else
												con.rollback();
										}else
											con.rollback();
									}else
										con.rollback();
								}else
								{
									con.rollback();
								}
							}else
							{
								con.rollback();
							}
						}else					
							response.put("status", "success");
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
			errorlogfinal=errorlog+" insert_StudentData "+e.getMessage();
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
						errorlogfinal=errorlog+" insert_StudentData ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "insert_StudentData", errorlogfinal, "failed",ipaddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	public JSONObject insert_ParentInfo(String driver,String url,String user,String pwd,JSONObject studentdata,String ipaddress)
	{
		Connection con=null;
		boolean errcheck=false;
		PreparedStatement ps=null,ps1=null,ps2=null;
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
					if(studentdata.getString("parentid").equalsIgnoreCase("0"))
					{
						sql=StudentQueries.insertPersonData;
						ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					}
					else
					{
						sql=StudentQueries.insertStudentData;
						ps=con.prepareStatement(sql);
					}
					String personid=studentdata.getString("studentid");
					ps.setString(1,studentdata.getString("parentfname"));
					ps.setString(2,"-");
					ps.setString(3,studentdata.getString("parentlname"));
					ps.setString(4,studentdata.getString("parentdob"));
					ps.setInt(5,Integer.parseInt(studentdata.getString("parentgender")));
					ps.setString(6,"-");
					ps.setString(7,studentdata.getString("parentaddress1"));
					ps.setString(8,studentdata.getString("parentaddress2"));
					ps.setString(9,studentdata.getString("parentcity"));
					ps.setString(10,studentdata.getString("parentstate"));
					ps.setString(11,studentdata.getString("parentpincode"));
					ps.setString(12,studentdata.getString("parentcountry"));
					ps.setString(13,studentdata.getString("parentphone1"));
					ps.setString(14,studentdata.getString("parentphone2"));
					ps.setString(15,"-");
					ps.setString(16,"-");
					ps.setString(17,"-");
					ps.setString(18,"-");
					ps.setString(19,studentdata.getString("parentemail"));
					int i=ps.executeUpdate();
					if(i>0)
					{
						if(studentdata.getString("parentid").equalsIgnoreCase("0"))
						{
							ResultSet keys = ps.getGeneratedKeys();    
							if(keys.next())
							{
								personid=keys.getInt(1)+"";
							}
							ps1=con.prepareStatement(StudentQueries.insertParentData,Statement.RETURN_GENERATED_KEYS);
							ps1.setInt(1,Integer.parseInt(personid));
							ps1.setString(2,studentdata.getString("parenteducation"));
							ps1.setString(3,studentdata.getString("parentoccupation"));
							ps1.setString(4,studentdata.getString("parentrelation"));
							ps1.setString(5,studentdata.getString("parentincome"));
							ps1.setString(6,studentdata.getString("studentid"));
							int p=ps1.executeUpdate();
							if(p>0)
							{
								String parentid=null;
								keys = ps1.getGeneratedKeys();    
								if(keys.next())
								{
									parentid=keys.getInt(1)+"";
								}
								if(parentid!=null)
								{
									ps2=con.prepareStatement(StudentQueries.insertLoginData);
									ps2.setString(1, studentdata.getString("parentphone1"));
									ps2.setString(2, new Encription().computeHash(studentdata.getString("parentphone1")));
									ps2.setString(3, "4");
									ps2.setString(4, "1");
									ps2.setString(5, parentid);
									p=ps2.executeUpdate();
									if(p>0)
									{
										response.put("parentid", parentid);	
										response.put("status", "success");	
										con.commit();
									}else
									{
										con.rollback();
									}
								}
							}
						}else					
							response.put("status", "success");
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
			errorlogfinal=errorlog+" insert_ParentInfo "+e.getMessage();
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
						errorlogfinal=errorlog+" insert_ParentInfo ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "insert_ParentInfo", errorlogfinal, "failed",ipaddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	public JSONObject getAdmissionNumber(String driver,String url,String user,String pwd,String ipaddress)
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
				String sql=StudentQueries.getHighestAdmisionnumber;
				ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					response.put(Jsonkeys.admisionno, rs.getString("ADMISION_NO"));
					response.put("status", "success");
				}
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getAdmissionNumber "+e.getMessage();
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
						errorlogfinal=errorlog+" getAdmissionNumber ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getAdmissionNumber", errorlogfinal, "failed",ipaddress);
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
