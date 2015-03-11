package com.product.home.server.databaseDAO;

public abstract class DatabaseQueries 
{
	public static String getUrl="SELECT `Url_ID`, `Url`, `Database`, `Status`, `Token1`, `Token2`, `Type`,IMAGE_URL,SCHOOL_NAME FROM "+DatabaseTable.Databaseurls;
	
	public static String loginCheck="SELECT ROLE_ID, STUDENT_PARENT_ID,LOGIN_ID FROM "+DatabaseTable.LoginTable+" WHERE STATUS='1' AND USERNAME=? AND PASSWORD=?";
	
	//Student Data
	public static String StudentLoginData="SELECT FIRST_NAME, MIDDLE_NAME, LAST_NAME,COURSE_ID,SECTION_ID,S.STUDENT_ID FROM LOGIN_DATA AS L JOIN STUDENT_DATA AS S ON L.STUDENT_PARENT_ID=S.STUDENT_ID JOIN PERSON_DATA AS PD ON S.PERSON_ID=PD.PERSON_ID WHERE L.LOGIN_ID=?";
	
	//Student Data of parent
	public static String ParentLoginData="SELECT FIRST_NAME, MIDDLE_NAME, LAST_NAME,COURSE_ID,SECTION_ID,S.STUDENT_ID FROM LOGIN_DATA AS L JOIN STUDENT_DATA AS S ON L.STUDENT_PARENT_ID=S.PARENT_ID JOIN PERSON_DATA AS PD ON S.PARENT_ID=PD.PERSON_ID WHERE L.LOGIN_ID=?";
	
	public static String Employee_admin_LoginData="SELECT FIRST_NAME, MIDDLE_NAME, LAST_NAME FROM LOGIN_DATA AS L JOIN EMPLOYEE_DATA AS E ON L.STUDENT_PARENT_ID=E.EMPLOYEE_ID JOIN PERSON_DATA AS PD ON E.PERSON_ID=PD.PERSON_ID WHERE L.LOGIN_ID=?";
	/**
	 * SELECT `PASSWORD` FROM "+DatabaseTable.LoginTable+" WHERE `LOGIN_ID`=?
	 */
	//oldpasswordChecking
	public static String oldpasswordChecking="SELECT `PASSWORD` FROM "+DatabaseTable.LoginTable+" WHERE `LOGIN_ID`=?";
	
	
	/**
	 * UPDATE "+DatabaseTable.LoginTable+" SET `PASSWORD`=?  WHERE `LOGIN_ID`=?
	 */
	public static String passwordChangeBasedonLoginId="UPDATE "+DatabaseTable.LoginTable+" SET `PASSWORD`=?  WHERE `LOGIN_ID`=?";
}
