package com.product.home.server;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.product.home.client.MainService;
import com.product.home.server.databaseDAO.DatabaseConnection;
import com.product.home.server.util.DateUtils;

@SuppressWarnings("serial")
public class MainServiceImpl extends RemoteServiceServlet implements MainService 
{
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
	public String getHostURL()
	{
		String hosturl=null;
		hosturl= this.getThreadLocalRequest().getRequestURL().toString();
		hosturl=hosturl.substring(0,hosturl.lastIndexOf("/")).toString();
		return hosturl;
	}
	public String getIpAddress()
	{
		String ipAddress=null;
		HttpServletRequest request=getThreadLocalRequest();
		 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
		   if(ipAddress == null)  
		   {  
			   ipAddress = request.getRemoteAddr();  
		   }  
		return ipAddress;
	}
	public String getCookieName(String cookie)
	{
		String cookievalue = null;
		Cookie[] cookies=getThreadLocalRequest().getCookies();

		try
		{
			for(int z=0;z<cookies.length;z++)
			{
				if(cookies[z].getName().equals(cookie))
				{
					cookievalue=cookies[z].getValue();
					cookievalue=URLDecoder.decode(cookievalue,"UTF-8");
				}
			}
		}catch(Exception e)
		{
			//System.out.println("Cookievalue fail"+cookie+"   "+e.getMessage());
		}
		return cookievalue;
	}
	@Override
	public String getUrlData(String url) 
	{
		JSONObject response=new JSONObject();
		try
		{
			response.put("status", "failed");
			JSONObject jsonres=new LoginCalls().urlCheck(driver, dburl, user, pwd, url);
			response=jsonres;
		}catch (Exception e)
		{
		}
		return response.toString();
	}

	@Override
	public String loginCheck(String loginjson) 
	{
		JSONObject resjsonobj=new JSONObject();
		try
		{
			resjsonobj.put("status", "failed");
			JSONObject loginjsonobj=new JSONObject(loginjson);
			String url=loginjsonobj.getString("url");
			url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, url);
			resjsonobj=new LoginCalls().loginCheck(driver, url, user, pwd, loginjsonobj);
		}catch (Exception e) 
		{
		}
		return resjsonobj.toString();
	}
	
	@Override
	public String getServerDate()
	{
		String getcurrentdate=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance(tz).getTime());
		return getcurrentdate;
	}
	
	@Override
	public String getCourses()
	{
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new CourseCalls().getCourses(driver, url, user, pwd, ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	
	@Override
	public String getCourseDetails(String courseid)
	{
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new CourseCalls().getCourseDetails(driver, url, user, pwd,courseid, ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	
	@Override
	public String insert_updateCourse(String jsoncoursedata)
	{
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new CourseCalls().insert_updateCourse(driver, url, user, pwd,new JSONObject(jsoncoursedata), ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	
	@Override
	public String getSections()
	{
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new CourseCalls().getSections(driver, url, user, pwd, ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String getSectionDetails(String sessionid)
	{
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new CourseCalls().getSectionsDetails(driver, url, user, pwd,sessionid, ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	
	public String insert_updateSection(String jsonsectiondata)
	{
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new CourseCalls().insert_updateSection(driver, url, user, pwd,new JSONObject(jsonsectiondata), ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String insetNewStudentInfo(String jsonobject) 
	{
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new StudentCalls().insert_StudentData(driver, url, user, pwd, new JSONObject(jsonobject), ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	
	@Override
	public String getStudentInformation(String searchdata) {
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new StudentCalls().getStudentsList(driver, url, user, pwd,ipAddress,new JSONObject(searchdata));
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String getStudentData(String studentId) {
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new StudentCalls().getStudentDetails(driver, url, user, pwd,studentId,ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	
	@Override
	public HashMap<String, Integer> getDaysAndStartingDayName(int year,int month) {
		return DateUtils.getMonthAndDay(year, month);
	}
	@Override
	public String getStudentAttendanceData(String attendacedata) {
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new AttendenceCalls().getAttendance(driver, url, user, pwd, ipAddress, new JSONObject(attendacedata));
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	
	@Override
	public String getSubjects() 
	{
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new CourseCalls().getSubjects(driver, url, user, pwd, ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	
	@Override
	public String saveeditattendance(String jsonobj) {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new AttendenceCalls().insert_updateStudentsAttendence(driver, url, user, pwd, new JSONObject(jsonobj),ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String delectattendancedata(String jsonobj) {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new AttendenceCalls().deleteStudentsAttendence(driver, url, user, pwd, new JSONObject(jsonobj),ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	
	@Override
	public String getExamNames() {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new ExamsCalls().getExams(driver, url, user, pwd, ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String insertAndUpdationExams(String examdata) {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new ExamsCalls().insert_updateExam(driver, url, user, pwd,new JSONObject(examdata), ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String getExamDetails(String examId) {

		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new ExamsCalls().getExamDetails(driver, url, user, pwd,examId, ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String getMarksDetails(String searchdata) {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new ExamsCalls().getMarks(driver, url, user, pwd,ipAddress,new JSONObject(searchdata));
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String insertAndUpdateMarks(String jsonarray) {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new ExamsCalls().insert_updateMarks(driver, url, user, pwd,new JSONArray(jsonarray),ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String getMarksReport(String jsonobject) {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			  response=new ExamsCalls().getMarkReport(driver, url, user, pwd,ipAddress,new JSONObject(jsonobject));
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String insertAndUpdateSubject(String jsonobject) {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new CourseCalls().insert_updateSubject(driver, url, user, pwd,new JSONObject(jsonobject), ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String getSubjectDetails(String subectId) {
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new CourseCalls().getSubjectsDetails(driver, url, user, pwd,subectId, ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String getFeeDetails(String courseid, String sectionid) {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new FeeCalls().getCourse_Section_Fee(driver, url, user, pwd,courseid,sectionid, ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String insert_updateFee(String jsonarray) {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new FeeCalls().insert_updateFee(driver, url, user, pwd,new JSONArray(jsonarray), ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String studentsNamesBasedOnCourseidAndSectionId(String courseId,String sectionId) {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new FeeCalls().getStudentsBasedOnCourse_Section(driver, url, user, pwd,courseId,sectionId, ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String getStdentFeeDetails(String studentId) {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new FeeCalls().getStudentFeeDetails(driver, url, user, pwd,studentId, ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String insertFee(String feeamt, String studentId) {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			String loginid=getCookieName("lid");
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new FeeCalls().insertStudentPayedFee(driver, url, user, pwd,studentId,feeamt,loginid, ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String getDesignation() {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new EmployeeCalls().getDesignations(driver, url, user, pwd, ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String addEmployeeData(String datajson) 
	{
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new EmployeeCalls().insert_EmployeeData(driver, url, user, pwd,new JSONObject(datajson), ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String forgetPasswordBasedOnEmailid(String forgetjsonobj) {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   //server response
			   
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String ChangePasswordBasedOnLoginId(String changepassworddata) {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new LoginCalls().changePassword(driver, url, user, pwd,new JSONObject(changepassworddata));
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	
	@Override
	public String getAdmissionNumber() {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new StudentCalls().getAdmissionNumber(driver, url, user, pwd,ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	
	@Override
	public String getEmplooyees_Adminsters(String filterjson)
	{
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   JSONObject filterjsondata=new JSONObject(filterjson);
			   response=new MailCalls().getEmplooyees_Adminsters(driver, url, user, pwd,ipAddress,filterjsondata);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String storeMails(String mailsdata) {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   JSONObject messagesjsondata=new JSONObject(mailsdata);
			   response=new MailCalls().storeMails(driver, url, user, pwd,ipAddress,messagesjsondata);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String getMailslist(String jsondata) {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   JSONObject inboxmessagesjsondata=new JSONObject(jsondata);
			   response=new MailCalls().getMailslist(driver, url, user, pwd,ipAddress,inboxmessagesjsondata);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	
	@Override
	public String getEventTypes() 
	{
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new EventCalls().getEventTypes(driver, url, user, pwd,ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();	
	}
	@Override
	public String getEventTypeDetails(String eventTypeId) 
	{
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new EventCalls().getEventTypeDetails(driver, url, user, pwd,eventTypeId,ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	@Override
	public String insert_updateEventType(String eventjson) 
	{
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new EventCalls().insert_updateEventType(driver, url, user, pwd,eventjson,ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}

	@Override
	public String messageReading(String jsonobject) {
		
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   JSONObject messagereadingdata=new JSONObject(jsonobject);
			   response=new MailCalls().getMailDetails(driver, url, user, pwd,ipAddress,messagereadingdata);
			}catch (Exception e) 
			{
			}
		return response.toString();
		
	}
	@Override
	public String deleteMessages(String deletejsonobj) {
	
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   JSONObject messagedeletedata=new JSONObject(deletejsonobj);
			   response=new MailCalls().DeleteMail(driver, url, user, pwd,ipAddress,messagedeletedata);
			   
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
	
	@Override
	public String getEventsData(int year, int month, String roleid) 
	{
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			if(ipAddress == null)  
			{  
				ipAddress = request.getRemoteAddr();  
			} 
			String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			response=new EventCalls().getEventsData(driver, url, user, pwd,ipAddress,year,month,roleid);
		}catch (Exception e) 
		{
		}
		return response.toString();
	}
	@Override
	public String getEmployeeIdBasedDetails(String employeeid) {
		String ipAddress =null;
		JSONObject response=new JSONObject();
		try {
			response.put("status", "failed");
			HttpServletRequest request=getThreadLocalRequest();
			 ipAddress  = request.getHeader("X-FORWARDED-FOR");  
			   if(ipAddress == null)  
			   {  
				   ipAddress = request.getRemoteAddr();  
			   } 
			   String url=new DatabaseConnection().getDBUrl(driver, dburl, user, pwd, getHostURL());
			   response=new EmployeeCalls().getEmployeeDetails(driver, url, user, pwd,employeeid,ipAddress);
			}catch (Exception e) 
			{
			}
		return response.toString();
	}
}
