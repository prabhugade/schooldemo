package com.product.home.server;

import com.product.home.server.databaseDAO.DatabaseTable;

public class CourseQueries 
{
	public static final String getCourses="SELECT COURSE_ID, COURSE_NAME, COURSE_DESC, SECTION_ID, STATUS FROM "+DatabaseTable.COURSES;
	
	public static final String getCourses_sections_maps="SELECT `COURSE_ID`, `SECTION_ID` FROM "+DatabaseTable.COURSES_SECTIONS_MAP;
	
	public static final String insertCourses="INSERT INTO "+DatabaseTable.COURSES+"(COURSE_NAME, COURSE_DESC, SECTION_ID, STATUS) VALUES (?,?,?,?)";
	
	public static final String updateCourses="UPDATE "+DatabaseTable.COURSES+" SET COURSE_NAME=?,COURSE_DESC=?,SECTION_ID=?,STATUS=? where COURSE_ID=?";

	public static final String getSections="SELECT SECTION_ID, SECTION_NAME, SECTION_DESC, STATUS,SUBJECT_ID FROM "+DatabaseTable.SECTIONS;
	
	public static final String insertSections="INSERT INTO "+DatabaseTable.SECTIONS+"(SECTION_NAME, SECTION_DESC, STATUS,SUBJECT_ID) VALUES (?,?,?,?)";
	
	public static final String updateSections="UPDATE "+DatabaseTable.SECTIONS+" SET SECTION_NAME=?,SECTION_DESC=?,STATUS=?,SUBJECT_ID=? where SECTION_ID=?";

	public static final String getSubjects="SELECT SUBJECT_ID, SUBJECT_NAME, SUBJECT_DESC, STATUS FROM "+DatabaseTable.SUBJECTS;
	
	public static final String insertSubjects="INSERT INTO "+DatabaseTable.SUBJECTS+"(SUBJECT_NAME, SUBJECT_DESC, STATUS) VALUES (?,?,?)";
	
	public static final String updateSubjects="UPDATE "+DatabaseTable.SUBJECTS+" SET SUBJECT_NAME=?,SUBJECT_DESC=?,STATUS=? where SUBJECT_ID=?";

}
