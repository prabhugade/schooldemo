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

public class ExamsCalls {

	public JSONObject getExams(String driver,String url,String user,String pwd,String ipaddress)
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
				String sql=ExamsQueries.getExams;
				ps=con.prepareStatement(sql);
				errorlog=ps.toString();
				//System.out.println(errorlog);
				ResultSet rs=ps.executeQuery();
				JSONArray coursesarray=new JSONArray();
				JSONObject courses;
				while(rs.next())
				{
					courses=new JSONObject();
					courses.put("examname", rs.getString("EXAM_NAME"));
					courses.put("examid", rs.getString("EXAM_ID"));
					coursesarray.put(courses);
				}
				response.put("exams", coursesarray);
				response.put("status", "success");
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getExams "+e.getMessage();
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
						errorlogfinal=errorlog+" getExams ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getExams", errorlogfinal, "failed",ipaddress);
				}
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return response;
	}
	public JSONObject getExamDetails(String driver,String url,String user,String pwd,String examid,String ipaddress)
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
				String sql=ExamsQueries.getExamDetails+" where EXAM_ID='"+examid+"'";
				ps=con.prepareStatement(sql);
				errorlog=ps.toString();
				//System.out.println(errorlog);
				ResultSet rs=ps.executeQuery();
				JSONObject coursedata=new JSONObject();
				if(rs.next())
				{
					coursedata.put("examname", rs.getString("EXAM_NAME"));
					coursedata.put("examid", rs.getString("EXAM_ID"));
					coursedata.put("desc", rs.getString("DESCR"));
					coursedata.put("courseid", rs.getString("COURSE_ID"));
					coursedata.put("sectionid", rs.getString("SECTION_ID"));
					coursedata.put("status", rs.getString("STATUS"));
				}
				response.put("examdata", coursedata);
				response.put("status", "success");
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getExamDetails "+e.getMessage();
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
						errorlogfinal=errorlog+" getExamDetails ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getExamDetails", errorlogfinal, "failed",ipaddress);
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
	 * 		 		coursedata.put("examname", EXAM_NAME);
	 * 				coursedata.put("examid", EXAM_ID);
					coursedata.put("courseid", COURSE_ID);
					coursedata.put("desc", DESC);
					coursedata.put("sectionid", SECTION_ID);
					coursedata.put("status", STATUS);
					coursedata.put("type", 1-insertion/0-updation);
	 * @param ipaddress
	 * @return
	 */
	public JSONObject insert_updateExam(String driver,String url,String user,String pwd,JSONObject examdata,String ipaddress)
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
				if(examdata.getString("type").equalsIgnoreCase("0"))
					sql=ExamsQueries.insertExamDetails;
				else
					sql=ExamsQueries.updateExamDetails;
				ps=con.prepareStatement(sql);
				ps.setString(1,examdata.getString("examname"));
				ps.setString(2,examdata.getString("sectionid"));
				ps.setString(3,examdata.getString("courseid"));
				ps.setString(4,examdata.getString("desc"));
				ps.setInt(5,Integer.parseInt(examdata.getString("status")));
				if(examdata.getString("type").equalsIgnoreCase("1"))
					ps.setString(6,examdata.getString("examid"));
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
			errorlogfinal=errorlog+" insert_updateExam "+e.getMessage();
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
						errorlogfinal=errorlog+" insert_updateExam ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "insert_updateExam", errorlogfinal, "failed",ipaddress);
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
	 *  This method is used to get one subject marks
	 * @param driver
	 * @param url
	 * @param user
	 * @param pwd
	 * @param ipaddress
	 * @param jsondata jsonobject with 
	 * 			courseid,sectionid,subjectid,examid,courseid,sectionid 
	 * @return
	 */
	public JSONObject getMarks(String driver,String url,String user,String pwd,String ipaddress,JSONObject jsondata)
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
				String sql=ExamsQueries.getMarks;
				ps=con.prepareStatement(sql);
				ps.setString(1, jsondata.getString("courseid"));
				ps.setString(2, jsondata.getString("sectionid"));
				ps.setString(3, jsondata.getString("subjectid"));
				ps.setString(4, jsondata.getString("examid"));
				ps.setString(5, jsondata.getString("courseid"));
				ps.setString(6, jsondata.getString("sectionid"));
				ps.setString(7, jsondata.getString("courseid"));
				ps.setString(8, jsondata.getString("sectionid"));
				ps.setString(9, jsondata.getString("subjectid"));
				ps.setString(10, jsondata.getString("examid"));
				errorlog=ps.toString();
				//System.out.println(ps.toString());
				ResultSet rs=ps.executeQuery();
				JSONArray marksarray=new JSONArray();
				JSONObject marks;
				while(rs.next())
				{
					marks=new JSONObject();
					marks.put("fname", rs.getString("FIRST_NAME"));
					if(rs.getString("MIDDLE_NAME").equalsIgnoreCase("-"))
						marks.put("mname", "");
					else
						marks.put("mname", rs.getString("MIDDLE_NAME"));
					marks.put("lname", rs.getString("LAST_NAME"));
					marks.put("studentid", rs.getString("STUDENT_ID"));
					marks.put("marks", rs.getString("MARKS"));
					marksarray.put(marks);
				}
				response.put("exams", marksarray);
				response.put("status", "success");
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getMarks "+e.getMessage();
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
						errorlogfinal=errorlog+" getMarks ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getMarks", errorlogfinal, "failed",ipaddress);
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
	 * 		 		coursedata.put("studentid", studentid);
	 * 				coursedata.put("subjectid", subjectid);
					coursedata.put("examid", examid);
					coursedata.put("marks", marks);
	 * @param ipaddress
	 * @return
	 */
	public JSONObject insert_updateMarks(String driver,String url,String user,String pwd,JSONArray marksarray,String ipaddress)
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
				String sql=ExamsQueries.insertMarks;
				ps=con.prepareStatement(sql);
				for(int i=0;i<marksarray.length();i++)
				{
					JSONObject marksdata=(JSONObject)marksarray.get(i);
					ps.setString(1,marksdata.getString("studentid"));
					ps.setString(2,marksdata.getString("subjectid"));
					ps.setString(3,marksdata.getString("examid"));
					ps.setString(4,marksdata.getString("marks"));
					ps.setString(5,marksdata.getString("studentid"));
					ps.setString(6,marksdata.getString("subjectid"));
					ps.setString(7,marksdata.getString("examid"));
					ps.setString(8,marksdata.getString("marks"));
					errorlog=ps.toString();
					ps.addBatch();
				}
				//System.out.println(errorlog);
				int[] count = ps.executeBatch();
				con.commit();
				//int i=ps.executeUpdate();
				if(count.length>0)
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
			errorlogfinal=errorlog+" insert_updateMarks "+e.getMessage();
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
						errorlogfinal=errorlog+" insert_updateMarks ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "insert_updateMarks", errorlogfinal, "failed",ipaddress);
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
	 * @param ipaddress
	 * @param jsondata as jsonobject with 
	 * 						courseid, sectionid , examid are keys
	 * @return response is jsonobject
	 *                     1->status---success/failed
	 *                     2->subjects as jsonobject
	 *                     			contains subjectnames as keys and subjectid as value
	 *                     3->marks as jsonarray
	 *                     			contains jsonobjects
	 *                     					 jsonobject contains 
	 *                        				 fname,mname,lname, marksdata as jsonarray
	 *                        												subjectid,subjectname,marks
	 */
	public JSONObject getMarkReport(String driver,String url,String user,String pwd,String ipaddress,JSONObject jsondata)
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
				ps=con.prepareStatement(CourseQueries.getSections+" where SECTION_ID=?");
				ps.setString(1, jsondata.getString("sectionid"));
				errorlog=ps.toString();
				//System.out.println(ps.toString());
				ResultSet rs=ps.executeQuery();
				String subjectids="";
				if(rs.next())
				{
					subjectids=rs.getString("SUBJECT_ID").replace(",", "','");
				}
				ps=con.prepareStatement(CourseQueries.getSubjects+" where SUBJECT_ID IN('"+subjectids+"')");
				errorlog=ps.toString();
				//System.out.println(ps.toString());
				rs=ps.executeQuery();
				JSONObject subjectsjson=new JSONObject();
				while(rs.next())
				{
					subjectsjson.put(rs.getString("SUBJECT_NAME"), rs.getString("SUBJECT_ID"));
				}
				String studentidcondition="";
				if(!jsondata.getString("studentid").equalsIgnoreCase("all"))
					studentidcondition=" AND M.STUDENT_ID='"+jsondata.getString("studentid")+"'";
				String sql=ExamsQueries.getMarksReport+studentidcondition;
				ps=con.prepareStatement(sql);
				ps.setString(1, jsondata.getString("courseid"));
				ps.setString(2, jsondata.getString("sectionid"));
				ps.setString(3, jsondata.getString("examid"));
				errorlog=ps.toString();
				//System.out.println(ps.toString());
				rs=ps.executeQuery();
				HashMap<String, JSONObject> datajsonmap=new HashMap<String, JSONObject>();
				JSONObject marks,tempjson;
				JSONArray temparray;
				while(rs.next())
				{
					String stuid=rs.getString("STUDENT_ID");
					if(!datajsonmap.containsKey(stuid))
					{
						marks=new JSONObject();
						marks.put("fname", rs.getString("FIRST_NAME"));
						if(rs.getString("MIDDLE_NAME").equalsIgnoreCase("-"))
							marks.put("mname", "");
						else
							marks.put("mname", rs.getString("MIDDLE_NAME"));;
						marks.put("lname", rs.getString("LAST_NAME"));
						//System.out.println("step1 "+marks);
						tempjson=new JSONObject();
						tempjson.put("subjectid", rs.getString("SUBJECT_ID"));
						tempjson.put("subjectname", rs.getString("SUBJECT_NAME"));
						tempjson.put("marks", rs.getString("MARKS"));
						marks.put("marksdata", new JSONArray().put(tempjson));
					}else
					{
						marks=datajsonmap.get(stuid);
						temparray=marks.getJSONArray("marksdata");
						tempjson=new JSONObject();
						tempjson.put("subjectid", rs.getString("SUBJECT_ID"));
						tempjson.put("subjectname", rs.getString("SUBJECT_NAME"));
						tempjson.put("marks", rs.getString("MARKS"));
						marks.put("marksdata", temparray.put(tempjson));
					}
					datajsonmap.put(stuid, marks);
				}
				JSONArray marksarray=new JSONArray();
				Iterator<Entry<String, JSONObject>> it=datajsonmap.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String, JSONObject> entry=it.next();
					marksarray.put(entry.getValue());
				}
				response.put("subjects", subjectsjson);
				response.put("marks", marksarray);
				response.put("status", "success");
			}else
			{
				errcheck=true;
				errorlog="failed to connect the database";
			}
		}catch (Exception e)
		{
			errorlogfinal=errorlog+" getMarkReport "+e.getMessage();
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
						errorlogfinal=errorlog+" getMarkReport ";
					new DatabaseConnection().storeErrorLog(driver,url,user,pwd, "-", "getMarkReport", errorlogfinal, "failed",ipaddress);
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
