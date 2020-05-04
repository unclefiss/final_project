package ua.nure.chernev.FinalTask;


public final class Path {
	
	// pages
	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
	public static final String PAGE_LIST_MENU = "/WEB-INF/jsp/client/list_menu.jsp";
	public static final String ORDER = "/WEB-INF/jsp/client/order.jsp";
	public static final String PAGE_LIST_ORDERS = "/WEB-INF/jsp/admin/list_orders.jsp";
	public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";
	public static final String PAGE_INDEX = "/index.jsp";
	public static final String PAGE_LIST_DOCTOR = "/WEB-INF/jsp/admin/list_doctor.jsp";
	public static final String PAGE_LIST_NURSES = "/WEB-INF/jsp/admin/list_nurses.jsp";
	public static final String PAGE_LIST_PATIENTS = "/WEB-INF/jsp/admin/list_patients.jsp";
	public static final String PAGE_ADD_DOCTOR = "/WEB-INF/jsp/admin/add_doctor.jsp";
	public static final String PAGE_ADD_NURSE = "/WEB-INF/jsp/admin/add_nurse.jsp";
	public static final String PAGE_ADD_PATIENT = "/WEB-INF/jsp/admin/add_patient.jsp";
	public static final String PAGE_PATIENT_CARD = "/WEB-INF/jsp/patient/card.jsp";
	public static final String PAGE_LIST_PRESCRIPTIONS = "/WEB-INF/jsp/doctor/nurse/list_prescriptions.jsp";
	public static final String PAGE_ADD_INTERIM_DIAGNOSIS = "/WEB-INF/jsp/doctor/add_interim_diagnosis.jsp";

	// commands
	public static final String COMMAND_LIST_ORDERS = "/controller?command=listOrders";
	public static final String COMMAND_LIST_MENU = "/controller?command=listMenu";
	public static final String COMMAND_LIST_DOCTORS = "/controller?command=listDoctors";
	public static final String COMMAND_LIST_NURSES = "/controller?command=listNurses";
	public static final String COMMAND_LIST_PATIENTS = "/controller?command=listPatients";
	public static final String COMMAND_VIEW_PATIENT_CARD = "/controller?command=viewPatientCard";
	public static final String COMMAND_LIST_PRESCRIPTIONS = "/controller?command=listPrescriptions";
	
	
	// redirects
	public static final String REDIRECT_PATIENTS_SORT = "/FinalProject/controller?command=sortPatients";
	public static final String REDIRECT_DOCTORS_SORT = "/FinalProject/controller?command=sortDoctors";
	public static final String REDIRECT_DOCTORS_SELECT = "/FinalProject/controller?command=selectCategory";
	public static final String REDIRECT_DOCTORS_LIST = "/FinalProject/controller?command=listDoctors";
	public static final String REDIRECT_NURSES_LIST = "/FinalProject/controller?command=listNurses";
	public static final String REDIRECT_PATIENTS_LIST = "/FinalProject/controller?command=listPatients";
	public static final String REDIRECT_PRESCRIPTIONS_LIST = "/FinalProject/controller?command=listPrescriptions";
	public static final String REDIRECT_VIEW_PATIENT_CARD = "/FinalProject/controller?command=viewPatientCard";
	public static final String REDIRECT_PATIENT_CARD = "/FinalProject/controller?command=patientCard";
	public static final String REDIRECT_SETTINGS_PAGE = "/FinalProject/controller?command=viewSettings";
	public static final String REDIRECT_ADD_NURSE_PAGE = "/FinalProject/controller?command=viewAddNurse";
	public static final String REDIRECT_ADD_DOCTOR_PAGE = "/FinalProject/controller?command=viewAddDoctor";
	public static final String REDIRECT_ADD_PATIENT_PAGE = "/FinalProject/controller?command=viewAddPatient";
	

}