package com.product.home.server;

import com.product.home.server.databaseDAO.DatabaseTable;


public abstract class AttendenceQueries {

	public static final String getAttendence="SELECT S.STUDENT_ID,P.`FIRST_NAME`, P.`MIDDLE_NAME`, P.`LAST_NAME`,CASE WHEN A.ATTENDANCE_ID IS NOT NULL THEN A.DATETIME ELSE '-' END as DATETIME,CASE WHEN A.ATTENDANCE_ID IS NOT NULL THEN A.REASON ELSE '-' END as REASON,CASE WHEN A.ATTENDANCE_ID IS NOT NULL THEN A.TYPE ELSE '-' END as TYPE,CASE WHEN A.ATTENDANCE_ID IS NOT NULL THEN A.ATTENDANCE_ID ELSE '-' END as ATTENDANCE_ID FROM "+DatabaseTable.STUDENT_DATA+" AS S JOIN "+DatabaseTable.PERSON_DATA+" AS P ON S.PERSON_ID=P.PERSON_ID LEFT JOIN "+DatabaseTable.ATTENDANCE+" AS A ON A.STUDENT_ID=S.STUDENT_ID WHERE A.DATETIME BETWEEN ? AND ? AND S.`COURSE_ID`=? AND S.`SECTION_ID`=? AND A.`SUBJECT_ID`=?";
	
	public static final String getStudentsForAttendence="SELECT S.STUDENT_ID,P.`FIRST_NAME`, P.`MIDDLE_NAME`, P.`LAST_NAME`,'-' as DATETIME,'-' as REASON,'-' as TYPE,'-' as ATTENDANCE_ID FROM "+DatabaseTable.STUDENT_DATA+" AS S JOIN "+DatabaseTable.PERSON_DATA+" AS P ON S.PERSON_ID=P.PERSON_ID WHERE S.`COURSE_ID`=? AND S.`SECTION_ID`=?";

	public static final String insertStudentAttendence="INSERT INTO `ATTENDANCE`(`STUDENT_ID`, `REASON`, `TYPE`, `SUBJECT_ID`, `DATETIME`) VALUES (?,?,?,?,?)";

	public static String updateStudentAttendence="UPDATE `ATTENDANCE` SET `REASON`=?,`TYPE`=? WHERE `ATTENDANCE_ID`=?";

	public static String deleteStudentAttendence="DELETE FROM `ATTENDANCE` WHERE `ATTENDANCE_ID`=?";
	
	
}
