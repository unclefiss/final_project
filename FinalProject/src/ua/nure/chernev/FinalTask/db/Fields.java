package ua.nure.chernev.FinalTask.db;


/**
 * Holder for DB fields 
 * 
 * @author K. Chernev
 * 
 */
public final class Fields {
	
	// entities
		public static final String ENTITY_ID = "id";
		
		public static final String USER_LOGIN = "login";
		public static final String USER_PASSWORD = "password";
		public static final String USER_FIRST_NAME = "first_name";
		public static final String USER_LAST_NAME = "last_name";
		public static final String USER_ROLE_ID = "role_id";
		public static final String USER_LOCALE_NAME = "locale_name";
		
		public static final String ROLES_NAME = "name";
		
		public static final String PATIENT_BIRTHDAY = "birthday";
		public static final String PATIENT_PHONENUMBER = "phonenumber";
		public static final String PATIENT_USER_ID = "user_id";
		public static final String PATIENT_STATUS_ID = "status_id";
		public static final String PATIENT_DATE_REGISTRATION = "date_registration";
		public static final String PATIENT_DATE_DISCHARGE = "date_discharge";
		
		
		public static final String DOCTOR_POSITION_ID = "position_id";
		public static final String DOCTOR_CATEGORY_ID = "category_id";
		public static final String DOCTOR_PATIENT_COUNT = "patients_count";
		public static final String DOCTOR_USER_ID = "user_id";
		
		public static final String CATEGORY_NAME = "name";
		public static final String CATEGORY_NAME_RU = "name_ru";
		
		public static final String POSITION_NAME = "name";
		
		public static final String HOSPITAL_CARD_PATIENT_ID = "patient_id";
		public static final String HOSPITAL_CARD_STATUS_ID = "status_id";
		
		
		public static final String DIAGNOSIS_COMMENT = "text";
		public static final String DIAGNOSIS_CARD_ID = "card_id";
		public static final String DIAGNOSIS_STATUS_ID = "status_id";

		public static final String PRESCRIPTION_COMMENT = "comment";
		public static final String PRESCRIPTION_STATUS_ID = "status_id";
		public static final String PRESCRIPTION_TYPE_ID = "type_id";
		public static final String PRESCRIPTION_DIAGNOSIS_ID = "diagnosis_id";
		
		public static final String STATUS_NAME = "name";
		public static final String STATUS_NAME_RU = "name_ru";
		
		public static final String TYPE_NAME = "name";
		
		// beans
		public static final String USER_ORDER_BEAN_ORDER_ID = "id";	
		public static final String USER_ORDER_BEAN_USER_FIRST_NAME = "first_name";	
		public static final String USER_ORDER_BEAN_USER_LAST_NAME = "last_name";	
		public static final String USER_ORDER_BEAN_ORDER_BILL = "bill";	
		public static final String USER_ORDER_BEAN_STATUS_NAME = "name";
}
