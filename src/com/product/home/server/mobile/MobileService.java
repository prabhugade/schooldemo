package com.product.home.server.mobile;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.TimeZone;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.product.home.server.AttendenceCalls;
import com.product.home.server.CourseCalls;
import com.product.home.server.EmployeeCalls;
import com.product.home.server.ExamsCalls;
import com.product.home.server.FeeCalls;
import com.product.home.server.LoginCalls;
import com.product.home.server.MailCalls;
import com.product.home.server.StudentCalls;
import com.product.home.server.databaseDAO.DatabaseConnection;
public class MobileService extends HttpServlet {

	private static final long serialVersionUID = 1L;


	String driver,url,user,pwd,error,dburl;
	TimeZone tz=null;	
	public void init() 
	{
		try
		{
			ServletContext cg=getServletContext();
			driver = cg.getInitParameter("driver");
			dburl = cg.getInitParameter("url");
			user = cg.getInitParameter("user");
			pwd = cg.getInitParameter("password");
			tz=TimeZone.getDefault();
			TimeZone.setDefault(TimeZone.getTimeZone("GMT+5:30"));

		}catch(Exception e)
		{
			error=e.getMessage();
		}
	} 	

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		try 
		{
			int id=Integer.parseInt(request.getParameter("id"));
			String ipAddress="mobile";
			
			if(id==1) //loginpurpose
			{
				String dataobj=URLDecoder.decode(request.getParameter("db"),"UTF-8");
				String url=URLDecoder.decode(request.getParameter("url"),"UTF-8");
				LoginCalls loginobj=new LoginCalls();
				JSONObject logincheck=loginobj.loginCheck(driver,getUrl(url), user, pwd, new JSONObject(dataobj));
				out.write(logincheck.toString());
			}else if(id==2)
			{
				/*
				 * For getting student details
				 */
				String studentid=URLDecoder.decode(request.getParameter("studentid"),"UTF-8");
				String url=URLDecoder.decode(request.getParameter("url"),"UTF-8");
				JSONObject data=new StudentCalls().getStudentDetails(driver, getUrl(url), user, pwd,studentid,ipAddress);
				out.write(data.toString());
			}else if(id==3)
			{
				/*
				 * For getting employee details
				 */
				String empid=URLDecoder.decode(request.getParameter("empid"),"UTF-8");
				String url=URLDecoder.decode(request.getParameter("url"),"UTF-8");
				JSONObject data=new EmployeeCalls().getEmployeeDetails(driver, getUrl(url), user, pwd,empid,ipAddress);
				out.write(data.toString());
			}else if(id==4)
			{
				/*
				 * For getting all courses
				 */
				String url=URLDecoder.decode(request.getParameter("url"),"UTF-8");
				JSONObject data=new CourseCalls().getCourses(driver, getUrl(url), user, pwd, ipAddress);
				out.write(data.toString());
			}else if(id==5)
			{
				/*
				 * For getting all sections
				 */
				String url=URLDecoder.decode(request.getParameter("url"),"UTF-8");
				JSONObject data=new CourseCalls().getSections(driver, getUrl(url), user, pwd, ipAddress);
				out.write(data.toString());
			}else if(id==6)
			{
				/*
				 * For getting course details
				 */
				String courseid=URLDecoder.decode(request.getParameter("courseid"),"UTF-8");
				String url=URLDecoder.decode(request.getParameter("url"),"UTF-8");
				JSONObject data=new CourseCalls().getCourseDetails(driver, getUrl(url), user, pwd,courseid, ipAddress);
				out.write(data.toString());
			}else if(id==7)
			{
				/*
				 * For getting studentids,parentids and student name based on courseid and sectionid
				 */
				String courseid=URLDecoder.decode(request.getParameter("courseid"),"UTF-8");
				String sectionid=URLDecoder.decode(request.getParameter("sectionid"),"UTF-8");
				String url=URLDecoder.decode(request.getParameter("url"),"UTF-8");
				JSONObject data=new FeeCalls().getStudentsBasedOnCourse_Section(driver, getUrl(url), user, pwd,courseid,sectionid, ipAddress);
				out.write(data.toString());
			}else if(id==8)
			{
				/*
				 * For getting Employees or admins
				 */
				String filterjson=URLDecoder.decode(request.getParameter("filterdata"),"UTF-8");
				String url=URLDecoder.decode(request.getParameter("url"),"UTF-8");
				JSONObject data=new MailCalls().getEmplooyees_Adminsters(driver, getUrl(url), user, pwd,ipAddress,new JSONObject(filterjson));
				out.write(data.toString());
			}else if(id==9)
			{
				/*
				 * For store mail
				 */
				String maildata=URLDecoder.decode(request.getParameter("maildata"),"UTF-8");
				String url=URLDecoder.decode(request.getParameter("url"),"UTF-8");
				JSONObject data=new MailCalls().storeMails(driver, getUrl(url), user, pwd,ipAddress,new JSONObject(maildata));
				out.write(data.toString());
			}else if(id==10)
			{
				/*
				 * For mails
				 */
				String maildata=URLDecoder.decode(request.getParameter("maildata"),"UTF-8");
				String url=URLDecoder.decode(request.getParameter("url"),"UTF-8");
				JSONObject data=new MailCalls().getMailslist(driver, getUrl(url), user, pwd,ipAddress,new JSONObject(maildata));
				out.write(data.toString());
			}else if(id==11)
			{
				/*
				 * For getting all subjects
				 */
				String url=URLDecoder.decode(request.getParameter("url"),"UTF-8");
				JSONObject data=new CourseCalls().getSubjects(driver, getUrl(url), user, pwd, ipAddress);
				out.write(data.toString());
			}else if(id==12)
			{
				/*
				 * For getting section details
				 */
				String url=URLDecoder.decode(request.getParameter("url"),"UTF-8");
				String sectionid=URLDecoder.decode(request.getParameter("sectionid"),"UTF-8");
				JSONObject data=new CourseCalls().getSectionsDetails(driver, getUrl(url), user, pwd,sectionid, ipAddress);
				out.write(data.toString());
			}else if(id==13)
			{
				/*
				 * For getting attendance
				 */
				String url=URLDecoder.decode(request.getParameter("url"),"UTF-8");
				String filterdata=URLDecoder.decode(request.getParameter("filterdata"),"UTF-8");
				JSONObject data=new AttendenceCalls().getAttendance(driver, getUrl(url), user, pwd, ipAddress, new JSONObject(filterdata));
				out.write(data.toString());
			}else if(id==14)
			{
				/*
				 * For getting all exams
				 */
				String url=URLDecoder.decode(request.getParameter("url"),"UTF-8");
				JSONObject data=new ExamsCalls().getExams(driver, getUrl(url), user, pwd, ipAddress);
				out.write(data.toString());
			}else if(id==15)
			{
				/*
				 * For getting all exams
				 */
				String url=URLDecoder.decode(request.getParameter("url"),"UTF-8");
				String filterdata=URLDecoder.decode(request.getParameter("filterdata"),"UTF-8");
				JSONObject data=new ExamsCalls().getMarkReport(driver, getUrl(url), user, pwd,ipAddress,new JSONObject(filterdata));
				out.write(data.toString());
			}

		} catch (Exception e) {
			out.print(e.getMessage());
		}
	}

	public String getUrl(String url) 
	{
		String connectionurl=null;
		try
		{
			connectionurl=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, url);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return connectionurl;
	}
}
