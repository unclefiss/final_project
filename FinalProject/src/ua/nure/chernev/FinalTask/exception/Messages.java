package ua.nure.chernev.FinalTask.exception;

/**
 * Holder for messages of exceptions.
 * 
 * @author K.Chernev
 *
 */
public class Messages {

	private Messages() {
		// no op
	}
	
	public static final String ERR_CANNOT_OBTAIN_USER_ORDER_BEANS = "Cannot obtain user order beans";

	public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";

	public static final String ERR_CANNOT_OBTAIN_CATEGORIES = "Cannot obtain categories";

	public static final String ERR_CANNOT_OBTAIN_TYPE = "Cannot obtain type";

	public static final String ERR_CANNOT_OBTAIN_MENU_ITEMS_BY_ORDER = "Cannot obtain menu items by order";

	public static final String ERR_CANNOT_OBTAIN_MENU_ITEMS_BY_IDENTIFIERS = "Cannot obtain menu items by its identifiers";
	
	public static final String ERR_CANNOT_OBTAIN_DOCTOR_BY_USER_ID= "Cannot obtain doctor by user id";
	
	public static final String ERR_CANNOT_OBTAIN_PATIENT_BY_USER_ID= "Cannot obtain patient by user id";
	
	public static final String ERR_CANNOT_OBTAIN_DIAGNOSIS_BY_CARD_ID= "Cannot obtain diagnosis by card id";
	
	public static final String ERR_CANNOT_OBTAIN_PRESCROPTION_BY_CARD_ID = "Cannot obtain prescription by card id";
	
	public static final String ERR_CANNOT_OBTAIN_PRESCROPTION_BY_ID = "Cannot obtain prescription by id";
	
	public static final String ERR_CANNOT_OBTAIN_PRESCROPTION_FOR_NURSE = "Cannot obtain prescription for nurse";
	
	public static final String ERR_CANNOT_OBTAIN_PRESCROPTION_FOR_DOCTOR = "Cannot obtain prescription for doctor";
	
	public static final String ERR_CANNOT_OBTAIN_PRESCROPTION_BY_DIAGNOSIS_ID = "Cannot obtain prescription by diagnosis id";
	
	public static final String ERR_CANNOT_OBTAIN_CARD_BY_PATIENT_ID= "Cannot obtain card by patient id";
	
	public static final String ERR_CANNOT_OBTAIN_DOCTOR_BY_CATEGORY_ID= "Cannot obtain doctor by category id";
	
	public static final String ERR_CANNOT_OBTAIN_DOCTOR_BY_ID= "Cannot obtain doctor by id";
	
	public static final String ERR_CANNOT_OBTAIN_PATIENT_BY_ID= "Cannot obtain patient by id";

	public static final String ERR_CANNOT_OBTAIN_ORDERS = "Cannot obtain orders";
	
	public static final String ERR_CANNOT_OBTAIN_DOCTORS = "Cannot obtain doctors";
	
	public static final String ERR_CANNOT_OBTAIN_NURSES = "Cannot obtain nurses";
	
	public static final String ERR_CANNOT_OBTAIN_PATIENTS = "Cannot obtain patients";
	
	public static final String ERR_CANNOT_OBTAIN_PATIENTS_FOR_DOCTOR = "Cannot obtain patients for doctor";

	public static final String ERR_CANNOT_OBTAIN_ORDERS_BY_STATUS_ID = "Cannot obtain orders by status id";

	public static final String ERR_CANNOT_OBTAIN_ORDERS_BY_IDENTIFIERS = "Cannot obtain orders by its identifiers";

	public static final String ERR_CANNOT_OBTAIN_ORDERS_BY_USER_AND_STATUS_ID = "Cannot obtain orders by user and status id";

	public static final String ERR_CANNOT_OBTAIN_USER_BY_ID = "Cannot obtain a user by its id";

	public static final String ERR_CANNOT_OBTAIN_USER_BY_LOGIN = "Cannot obtain a user by its login";

	public static final String ERR_CANNOT_UPDATE_USER = "Cannot update a user";
	
	public static final String ERR_CANNOT_UPDATE_DOCTOR = "Cannot update a doctor";
	
	public static final String ERR_CANNOT_UPDATE_PATIENT = "Cannot update a patient";
	
	public static final String ERR_CANNOT_INSERT_USER = "Cannot insert a user";
	
	public static final String ERR_CANNOT_INSERT_DOCTOR = "Cannot insert a doctor";
	
	public static final String ERR_CANNOT_INSERT_NURSE = "Cannot insert a nurse";
	
	public static final String ERR_CANNOT_INSERT_PATIENT = "Cannot insert a patient";
	
	public static final String ERR_CANNOT_INSERT_CARD = "Cannot insert a hospital card";
	
	public static final String ERR_CANNOT_INSERT_PRESCRIPTION = "Cannot insert a prescription";

	public static final String ERR_CANNOT_APPOINT_DOCTOR = "Cannot appoint a doctor to a patient";

	public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";

	public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";

	public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";

	public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";
	
	public static final String ERR_CANNOT_DELETE_APPOINT_DOCTOR = "Cannot delete appoint doctor";
	
	
}