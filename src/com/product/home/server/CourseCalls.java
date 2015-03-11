package com.product.home.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

import com.product.home.server.databaseDAO.DatabaseConnection;

public class CourseCalls 
{
	public JSONObject getCourses(String driver,String url,String user,String pwd,String ipaddress)
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
				String sql=CourseQueries.getCourses;
				ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				JSONArray coursesarray=new JSONArray();
				JSONObject courses;
				while(rs.next())
				{
					courses=new JSONObject();
					courses.put("coursename", rs.getString("COURSE_NAME"));
					courses.put("courseid", rs.getString("COURSE_ID"));
					coursesarray.put(courses);
				}
				response.put("courses", coursesarray);
				response.put("status", "success");
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getCourses "+e.getMessage();
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
						errorlogfinal=errorlog+" getCourses ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getCourses", errorlogfinal, "failed",ipaddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	public JSONObject getCourseDetails(String driver,String url,String user,String pwd,String courseid,String ipaddress)
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
				String sql=CourseQueries.getCourses+" where COURSE_ID='"+courseid+"'";
				ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				JSONObject coursedata=new JSONObject();
				if(rs.next())
				{
					coursedata.put("coursename", rs.getString("COURSE_NAME"));
					coursedata.put("courseid", rs.getString("COURSE_ID"));
					coursedata.put("desc", rs.getString("COURSE_DESC"));
					JSONArray sectionsarray=new JSONArray();
					String[] array=rs.getString("SECTION_ID").split(",");
					for(int p=0;p<array.length;p++)
					{
						sectionsarray.put(array[p]);
					}
					coursedata.put("sectionid", sectionsarray);
					coursedata.put("status", rs.getString("STATUS"));
				}
				response.put("coursedata", coursedata);
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
						errorlogfinal=errorlog+" getCourseDetails ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getCourseDetails", errorlogfinal, "failed",ipaddress);
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
	 * @param coursedata in jsonobject
	 * 		 		coursedata.put("coursename", COURSE_NAME);
					coursedata.put("courseid", COURSE_ID);
					coursedata.put("desc", COURSE_DESC);
					coursedata.put("sectionid", jsonarray);
					coursedata.put("status", STATUS);
					coursedata.put("type", 1-insertion/0-updation);
	 * @param ipaddress
	 * @return
	 */
	public JSONObject insert_updateCourse(String driver,String url,String user,String pwd,JSONObject coursedata,String ipaddress)
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
				if(coursedata.getString("type").equalsIgnoreCase("0"))
					sql=CourseQueries.insertCourses;
				else
					sql=CourseQueries.updateCourses;
				ps=con.prepareStatement(sql);
				ps.setString(1,coursedata.getString("coursename"));
				ps.setString(2,coursedata.getString("desc"));
				JSONArray array=coursedata.getJSONArray("sectionid");
				StringBuilder sectionid=new StringBuilder();
				JSONObject tempjson;
				for(int p=0;p<array.length();p++)
				{
					tempjson=array.getJSONObject(p);
					sectionid.append(tempjson.getString("sectionid")+",");
				}				
				ps.setString(3,sectionid.substring(0, sectionid.length()-1));
				ps.setInt(4,Integer.parseInt(coursedata.getString("status")));
				if(coursedata.getString("type").equalsIgnoreCase("1"))
					ps.setString(5,coursedata.getString("courseid"));
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
			errorlogfinal=errorlog+" insert_updateCourse "+e.getMessage();
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
						errorlogfinal=errorlog+" insert_updateCourse ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "insert_updateCourse", errorlogfinal, "failed",ipaddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	

	public JSONObject getSections(String driver,String url,String user,String pwd,String ipaddress)
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
				String sql=CourseQueries.getSections;
				ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				JSONArray coursesarray=new JSONArray();
				JSONObject courses;
				while(rs.next())
				{
					courses=new JSONObject();
					courses.put("sectionname", rs.getString("SECTION_NAME"));
					courses.put("sectionid", rs.getString("SECTION_ID"));
					coursesarray.put(courses);
				}
				response.put("sections", coursesarray);
				response.put("status", "success");
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getSections "+e.getMessage();
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
						errorlogfinal=errorlog+" getSections ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getSections", errorlogfinal, "failed",ipaddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	public JSONObject getSectionsDetails(String driver,String url,String user,String pwd,String sectionid,String ipaddress)
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
				String sql=CourseQueries.getSections+" where SECTION_ID='"+sectionid+"'";
				ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				JSONObject coursedata=new JSONObject();
				if(rs.next())
				{
					coursedata.put("sectionname", rs.getString("SECTION_NAME"));
					coursedata.put("sectionid", rs.getString("SECTION_ID"));
					coursedata.put("desc", rs.getString("SECTION_DESC"));
					JSONArray array=new JSONArray();
					String[] subjectsarray=rs.getString("SUBJECT_ID").split(",");
					for(int p=0;p<subjectsarray.length;p++)
					{
						array.put(subjectsarray[p]);
					}
					coursedata.put("subjectid", array);
					coursedata.put("status", rs.getString("STATUS"));
				}
				response.put("sectiondata", coursedata);
				response.put("status", "success");
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getSectionsDetails "+e.getMessage();
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
						errorlogfinal=errorlog+" getSectionsDetails ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getSectionsDetails", errorlogfinal, "failed",ipaddress);
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
	 * @param sectiondata in jsonobject
	 * 		 		sectiondata.put("sectionname", SECTION_NAME);
					sectiondata.put("sectionid", SECTION_ID);
					sectiondata.put("desc", SECTION_DESC);
					sectiondata.put("status", STATUS);
					sectiondata.put("type", 1-insertion/0-updation);
	 * @param ipaddress
	 * @return
	 */
	public JSONObject insert_updateSection(String driver,String url,String user,String pwd,JSONObject sectiondata,String ipaddress)
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
				if(sectiondata.getString("type").equalsIgnoreCase("1"))
					sql=CourseQueries.insertSections;
				else
					sql=CourseQueries.updateSections;
				ps=con.prepareStatement(sql);
				ps.setString(1,sectiondata.getString("sectionname"));
				ps.setString(2,sectiondata.getString("desc"));
				ps.setInt(3,Integer.parseInt(sectiondata.getString("status")));
				JSONArray array=sectiondata.getJSONArray("subjects");
				StringBuilder subjectid=new StringBuilder();
				JSONObject tempjson;
				for(int p=0;p<array.length();p++)
				{
					tempjson=array.getJSONObject(p);
					subjectid.append(tempjson.getString("subjectid")+",");
				}				
				ps.setString(4,subjectid.substring(0, subjectid.length()-1));
				if(sectiondata.getString("type").equalsIgnoreCase("0"))
					ps.setString(5,sectiondata.getString("sectionid"));
				int i=ps.executeUpdate();
				if(i>0)
				{
					response.put("status", "success");
				}
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
				//System.out.println(errorlog);
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" insert_updateSection "+e.getMessage();
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
						errorlogfinal=errorlog+" insert_updateSection ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "insert_updateSection", errorlogfinal, "failed",ipaddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	

	public JSONObject getSubjects(String driver,String url,String user,String pwd,String ipaddress)
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
				String sql=CourseQueries.getSubjects;
				ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				JSONArray subjectsarray=new JSONArray();
				JSONObject subjects;
				while(rs.next())
				{
					subjects=new JSONObject();
					subjects.put("subjectname", rs.getString("SUBJECT_NAME"));
					subjects.put("subjectid", rs.getString("SUBJECT_ID"));
					subjectsarray.put(subjects);
				}
				response.put("subjects", subjectsarray);
				response.put("status", "success");
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getSubjects "+e.getMessage();
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
						errorlogfinal=errorlog+" getSubjects ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getSubjects", errorlogfinal, "failed",ipaddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	public JSONObject getSubjectsDetails(String driver,String url,String user,String pwd,String subjectid,String ipaddress)
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
				String sql=CourseQueries.getSubjects+" where SUBJECT_ID='"+subjectid+"'";
				ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				JSONObject subjectdata=new JSONObject();
				if(rs.next())
				{
					subjectdata.put("subjectname", rs.getString("SUBJECT_NAME"));
					subjectdata.put("subjectid", rs.getString("SUBJECT_ID"));
					subjectdata.put("desc", rs.getString("SUBJECT_DESC"));
					subjectdata.put("status", rs.getString("STATUS"));
				}
				response.put("subjectdata", subjectdata);
				response.put("status", "success");
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getSubjectsDetails "+e.getMessage();
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
						errorlogfinal=errorlog+" getSubjectsDetails ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getSubjectsDetails", errorlogfinal, "failed",ipaddress);
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
	 * @param subjectdata in jsonobject
	 * 		 		subjectdata.put("subjectname", SUBJECT_NAME);
					subjectdata.put("subjectid", SUBJECT_ID);
					subjectdata.put("desc", SUBJECT_DESC);
					subjectdata.put("status", STATUS);
					subjectdata.put("type", 1-insertion/0-updation);
	 * @param ipaddress
	 * @return
	 */
	public JSONObject insert_updateSubject(String driver,String url,String user,String pwd,JSONObject subjectdata,String ipaddress)
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
				if(subjectdata.getString("type").equalsIgnoreCase("1"))
					sql=CourseQueries.insertSubjects;
				else
					sql=CourseQueries.updateSubjects;
				ps=con.prepareStatement(sql);
				ps.setString(1,subjectdata.getString("subjectname"));
				ps.setString(2,subjectdata.getString("desc"));
				ps.setInt(3,Integer.parseInt(subjectdata.getString("status")));
				if(subjectdata.getString("type").equalsIgnoreCase("0"))
					ps.setString(4,subjectdata.getString("subjectid"));
				int i=ps.executeUpdate();
				if(i>0)
				{
					response.put("status", "success");
				}
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
				//System.out.println(errorlog);
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" insert_updateSubject "+e.getMessage();
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
						errorlogfinal=errorlog+" insert_updateSubject ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "insert_updateSubject", errorlogfinal, "failed",ipaddress);
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
