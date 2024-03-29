package com.product.home.server;

import com.product.home.server.databaseDAO.DatabaseTable;

public abstract class ExamsQueries {

	public static final String getExams="SELECT `EXAM_ID`, `EXAM_NAME` FROM "+DatabaseTable.EXAMS;
	
	public static final String getExamDetails="SELECT `EXAM_ID`, `EXAM_NAME`, `SECTION_ID`, `COURSE_ID`, `DESCR`, `STATUS` FROM "+DatabaseTable.EXAMS;
	
	public static final String insertExamDetails="INSERT INTO `EXAMS`(`EXAM_NAME`, `SECTION_ID`, `COURSE_ID`, `DESCR`, `STATUS`) VALUES (?,?,?,?,?)";
	
	public static final String updateExamDetails="UPDATE `EXAMS` SET EXAM_NAME=?,SECTION_ID=?,COURSE_ID=?,DESCR=?,STATUS=? WHERE EXAM_ID=?";
	
	public static final String getMarks="SELECT SD.`STUDENT_ID`, PD.`FIRST_NAME`, PD.`MIDDLE_NAME`, PD.`LAST_NAME`,M.MARKS FROM `STUDENT_DATA` AS SD JOIN PERSON_DATA AS PD JOIN MARKS AS M ON PD.PERSON_ID=SD.PERSON_ID AND SD.STUDENT_ID=M.STUDENT_ID WHERE SD.COURSE_ID=? AND SD.SECTION_ID=? AND M.SUBJECT_ID=? AND M.EXAM_ID=? UNION SELECT SD.`STUDENT_ID`, PD.`FIRST_NAME`, PD.`MIDDLE_NAME`, PD.`LAST_NAME`,'0' FROM `STUDENT_DATA` AS SD JOIN PERSON_DATA AS PD ON PD.PERSON_ID=SD.PERSON_ID WHERE  SD.COURSE_ID=? AND SD.SECTION_ID=? AND (SD.STUDENT_ID NOT IN(SELECT SD.`STUDENT_ID` FROM `STUDENT_DATA` AS SD JOIN PERSON_DATA AS PD JOIN MARKS AS M ON PD.PERSON_ID=SD.PERSON_ID AND SD.STUDENT_ID=M.STUDENT_ID WHERE SD.COURSE_ID=? AND SD.SECTION_ID=? AND M.SUBJECT_ID=? AND M.EXAM_ID=?))";
	
	public static final String insertMarks="INSERT INTO `MARKS`(`STUDENT_ID`, `SUBJECT_ID`, `EXAM_ID`, `MARKS`) VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE `STUDENT_ID`=?, `SUBJECT_ID`=?, `EXAM_ID`=?, `MARKS`=?";
	
	public static final String getMarksReport="SELECT PD.`FIRST_NAME`, PD.`MIDDLE_NAME`, PD.`LAST_NAME`,S.`SUBJECT_ID`,S.`SUBJECT_NAME`,M.`MARKS`,M.STUDENT_ID FROM "+DatabaseTable.PERSON_DATA+" AS PD JOIN "+DatabaseTable.STUDENT_DATA+" AS SD JOIN "+DatabaseTable.SUBJECTS+" AS S JOIN "+DatabaseTable.MARKS+" AS M ON M.STUDENT_ID=SD.STUDENT_ID AND M.SUBJECT_ID=S.SUBJECT_ID AND SD.PERSON_ID=PD.PERSON_ID WHERE SD.COURSE_ID=? AND SD.SECTION_ID=? AND M.EXAM_ID=?";
}
