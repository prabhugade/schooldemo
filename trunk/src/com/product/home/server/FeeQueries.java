package com.product.home.server;

import com.product.home.server.databaseDAO.DatabaseTable;

public abstract class FeeQueries {
	public static final String getCourse_Section_Fee="SELECT CSE.`COURSE_SECTION_FEE_ID`, CSE.`COURSE_ID`, CSE.`SECTION_ID`, CSE.`FEE`,S.SECTION_NAME FROM "+DatabaseTable.COURSE_SECTION_FEE+" as CSE join "+DatabaseTable.SECTIONS+" as S on S.SECTION_ID=CSE.SECTION_ID ";
	
	public static final String Insert_Update_Fee="INSERT INTO "+DatabaseTable.COURSE_SECTION_FEE+"(`COURSE_ID`, `SECTION_ID`, `FEE`) VALUES (?,?,?) ON DUPLICATE KEY UPDATE `FEE`=?;";
	
	public static final String getStudentFee="SELECT P.`FIRST_NAME`, P.`MIDDLE_NAME`, P.`LAST_NAME`,S.`FEE` FROM "+DatabaseTable.PERSON_DATA+" AS P JOIN "+DatabaseTable.STUDENT_DATA+" AS S ON P.PERSON_ID=S.PERSON_ID WHERE S.STUDENT_ID=?";
	
	public static final String getStudentPayFee="SELECT `DATETIME`, `FEE` FROM "+DatabaseTable.COLLECTED_FEE+" WHERE STUDENT_ID=?";
	
	public static final String insertStudentpayFee="INSERT INTO "+DatabaseTable.COLLECTED_FEE+"(`STUDENT_ID`, `DATETIME`, `FEE`,LOGIN_ID) VALUES (?,?,?,?)";
	
	public static final String getStudentsBasedOnCourse_Sections="SELECT P.`FIRST_NAME`, P.`MIDDLE_NAME`, P.`LAST_NAME`,S.STUDENT_ID,S.PARENT_ID FROM "+DatabaseTable.PERSON_DATA+" AS P JOIN "+DatabaseTable.STUDENT_DATA+" AS S ON P.PERSON_ID=S.PERSON_ID ";
	
}
