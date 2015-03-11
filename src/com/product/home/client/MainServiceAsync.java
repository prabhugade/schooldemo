package com.product.home.client;

import java.util.HashMap;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface MainServiceAsync {

	void getUrlData(String url, AsyncCallback<String> asyncCallback);

	void loginCheck(String loginjson, AsyncCallback<String> asyncCallback);

	void getCourses(AsyncCallback<String> asyncCallback);

	void getCourseDetails(String courseId, AsyncCallback<String> asyncCallback);

	void getSectionDetails(String sectionid, AsyncCallback<String> asyncCallback);
	
	void getSections(AsyncCallback<String> asyncCallback);

	void insert_updateSection(String sectionjsondata, AsyncCallback<String> asyncCallback);

	void insert_updateCourse(String coursedata, AsyncCallback<String> asyncCallback);

	void insetNewStudentInfo(String string, AsyncCallback<String> asyncCallback);

	void getStudentInformation(String searchdata,AsyncCallback<String> asyncCallback);

	void getStudentData(String studentId, AsyncCallback<String> asyncCallback);

	void getDaysAndStartingDayName(int year, int month,	AsyncCallback<HashMap<String, Integer>> asyncCallback);

	void getStudentAttendanceData(String attendacedata,AsyncCallback<String> asyncCallback);

	void getSubjects(AsyncCallback<String> asyncCallback);

	void saveeditattendance(String string, AsyncCallback<String> asyncCallback);

	void delectattendancedata(String string, AsyncCallback<String> asyncCallback);

	void getExamNames(AsyncCallback<String> asyncCallback);

	void insertAndUpdationExams(String examdata,AsyncCallback<String> asyncCallback);

	void getExamDetails(String examId, AsyncCallback<String> asyncCallback);

	void getMarksDetails(String string, AsyncCallback<String> asyncCallback);

	void insertAndUpdateMarks(String jsonarray, AsyncCallback<String> asyncCallback);

	void getMarksReport(String jsonobject, AsyncCallback<String> asyncCallback);

	void insertAndUpdateSubject(String jsonobject,AsyncCallback<String> asyncCallback);

	void getSubjectDetails(String subectId, AsyncCallback<String> asyncCallback);

	void getFeeDetails(String courseid, String sectionid,AsyncCallback<String> asyncCallback);

	void getServerDate(AsyncCallback<String> asyncCallback);

	void insert_updateFee(String jsonarray, AsyncCallback<String> asyncCallback);

	void studentsNamesBasedOnCourseidAndSectionId(String courseId,String sectionId, AsyncCallback<String> asyncCallback);

	void getStdentFeeDetails(String studentId,AsyncCallback<String> asyncCallback);

	void insertFee(String feeamt, String studentId,	AsyncCallback<String> asyncCallback);

	void getDesignation(AsyncCallback<String> asyncCallback);

	void addEmployeeData(String datajson, AsyncCallback<String> asyncCallback);

	void forgetPasswordBasedOnEmailid(String forgetjsonobj,AsyncCallback<String> asyncCallback);

	void ChangePasswordBasedOnLoginId(String changepassworddata,AsyncCallback<String> asyncCallback);

	void getAdmissionNumber(AsyncCallback<String> asyncCallback);

	void getEmplooyees_Adminsters(String jsonobject,AsyncCallback<String> asyncCallback);

	void storeMails(String mailsdata, AsyncCallback<String> asyncCallback);

	void getMailslist(String jsondata, AsyncCallback<String> asyncCallback);

	void getEventTypes(AsyncCallback<String> asyncCallback);

	void getEventTypeDetails(String eventTypeId,AsyncCallback<String> asyncCallback);

	void insert_updateEventType(String eventjson,AsyncCallback<String> asyncCallback);

	void messageReading(String jsonobject, AsyncCallback<String> asyncCallback);

	void deleteMessages(String deletejsonobj, AsyncCallback<String> asyncCallback);

	void getEventsData(int year, int month, String roleid,AsyncCallback<String> asyncCallback);

	void getEmployeeIdBasedDetails(String employeeid,AsyncCallback<String> asyncCallback);

}
