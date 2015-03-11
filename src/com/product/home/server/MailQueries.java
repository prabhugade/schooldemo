package com.product.home.server;

import com.product.home.server.databaseDAO.DatabaseTable;

public class MailQueries {

	public static String getEmplooyees_Adminsters="SELECT `FIRST_NAME`, `MIDDLE_NAME`, `LAST_NAME`, `EMPLOYEE_ID`,D.DESIGNATION_ID FROM "+DatabaseTable.EMPLOYEE_DATA+" AS E JOIN "+DatabaseTable.PERSON_DATA+" AS P JOIN "+DatabaseTable.DESIGNATIONS+" AS D ON E.PERSON_ID=P.PERSON_ID AND E.DESIGNATION_ID=D.DESIGNATION_ID ";
	
	public static String getReceiverMailsList="SELECT M.MESSAGE_ID,M.MESSAGE_SUBJECT, M.MESSAGE_DATE, M.SENDER_ID, M.ROLE_ID, MUM.ISREAD FROM "+DatabaseTable.MESSAGES+" AS M JOIN "+DatabaseTable.MESSAGES_USERS_MAPPED+" AS MUM ON MUM.MESSAGE_ID=M.MESSAGE_ID ";
	
	public static String getSenderMailsList="SELECT `MESSAGE_ID`, `MESSAGE_SUBJECT`, `MESSAGE_DATE`, `SENDER_ID`, `ROLE_ID` FROM "+DatabaseTable.MESSAGES;
	
	public static String getEmployeename="SELECT `FIRST_NAME`, `MIDDLE_NAME`, `LAST_NAME` FROM "+DatabaseTable.EMPLOYEE_DATA+" AS E JOIN "+DatabaseTable.PERSON_DATA+" AS P ON P.PERSON_ID=E.PERSON_ID WHERE E.EMPLOYEE_ID=?";
	
	public static String getStudentname="SELECT `FIRST_NAME`, `MIDDLE_NAME`, `LAST_NAME` FROM "+DatabaseTable.STUDENT_DATA+" AS S JOIN "+DatabaseTable.PERSON_DATA+" AS P ON P.PERSON_ID=S.PERSON_ID WHERE S.STUDENT_ID=?";

	public static String getParentname="SELECT `FIRST_NAME`, `MIDDLE_NAME`, `LAST_NAME` FROM "+DatabaseTable.PARENT_DATA+" AS PD JOIN "+DatabaseTable.PERSON_DATA+" AS P ON P.PERSON_ID=PD.PERSON_ID WHERE PD.PARENT_ID=?";
	
	public static String storeMaildata="INSERT INTO "+DatabaseTable.MESSAGES+"(MESSAGE_SUBJECT, MESSAGE_BODY, MESSAGE_DATE, SENDER_ID, ROLE_ID) VALUES (?,?,?,?,?)";
	
	public static String storeMailMapping="INSERT INTO "+DatabaseTable.MESSAGES_USERS_MAPPED+"(`MESSAGE_ID`, `PLACEHOLEDER_ID`, `RECEIVER_ID`, `ISREAD`, `ROLE_ID`) VALUES (?,?,?,?,?)";
	
	public static String getMessageDetails="SELECT `MESSAGE_ID`, `MESSAGE_SUBJECT`, `MESSAGE_BODY`, `MESSAGE_DATE`, `SENDER_ID`, `ROLE_ID` FROM "+DatabaseTable.MESSAGES;

	public static String updateReadSql="UPDATE `MESSAGES_USERS_MAPPED` SET ISREAD=? WHERE `RECEIVER_ID`=? and `ROLE_ID`=? and `MESSAGE_ID`=?";

	public static String deleteSentMail="UPDATE MESSAGES SET STATUS='0' WHERE SENDER_ID=? AND ROLE_ID=? AND MESSAGE_ID=?";

	public static String deleteInbox="UPDATE MESSAGES_USERS_MAPPED SET PLACEHOLEDER_ID='3' WHERE RECEIVER_ID=? AND ROLE_ID=? AND MESSAGE_ID=?";

}
