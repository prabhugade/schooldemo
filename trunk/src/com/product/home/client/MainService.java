package com.product.home.client;

import java.util.HashMap;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface MainService extends RemoteService {

	String getUrlData(String url);

	String loginCheck(String loginjson);

	String getCourses();
	
	String getCourseDetails(String courseId);

	String getSectionDetails(String sectionid);

	String getSections();

	String insert_updateSection(String sectionjsondata);

	String insert_updateCourse(String coursedata);

	String insetNewStudentInfo(String string);

	String getStudentInformation(String searchdata);

	String getStudentData(String studentId);

	HashMap<String, Integer> getDaysAndStartingDayName(int year, int month);

	String getStudentAttendanceData(String attendacedata);

	String getSubjects();

	String saveeditattendance(String string);

	String delectattendancedata(String string);

	String getExamNames();

	String insertAndUpdationExams(String examdata);

	String getExamDetails(String examId);

	String getMarksDetails(String string);

	String insertAndUpdateMarks(String jsonarray);

	String getMarksReport(String jsonobject);

	String insertAndUpdateSubject(String jsonobject);

	String getSubjectDetails(String subectId);

	String getFeeDetails(String courseid, String sectionid);

	String getServerDate();

	String insert_updateFee(String jsonarray);

	String studentsNamesBasedOnCourseidAndSectionId(String courseId,String sectionId);

	String getStdentFeeDetails(String studentId);

	String insertFee(String feeamt, String studentId);

	String getDesignation();

	String addEmployeeData(String datajson);

	String forgetPasswordBasedOnEmailid(String forgetjsonobj);

	String ChangePasswordBasedOnLoginId(String changepassworddata);

	String getAdmissionNumber();

	String getEmplooyees_Adminsters(String jsonobject);

	String storeMails(String mailsdata);

	String getMailslist(String jsondata);

	String getEventTypes();

	String getEventTypeDetails(String eventTypeId);

	String insert_updateEventType(String eventjson);

	String messageReading(String jsonobject);

	String deleteMessages(String deletejsonobj);

	String getEventsData(int year, int month, String roleid);

	String getEmployeeIdBasedDetails(String employeeid);

}
