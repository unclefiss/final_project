package ua.nure.chernev.FinalTask.web.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.chernev.FinalTask.Path;
import ua.nure.chernev.FinalTask.db.DAO.DoctorDao;
import ua.nure.chernev.FinalTask.db.DAO.PatientDao;
import ua.nure.chernev.FinalTask.db.entity.Doctor;
import ua.nure.chernev.FinalTask.db.entity.Patient;
import ua.nure.chernev.FinalTask.exception.AppException;
import ua.nure.chernev.FinalTask.web.command.Command;

public class AppointDoctorCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5873715829460342566L;

	private static final Logger LOG = Logger.getLogger(AppointDoctorCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");

		
		PatientDao patientDao = new PatientDao();

		int doctorId = Integer.parseInt(request.getParameter("doctorId"));
		LOG.trace("Request parameter: doctorId --> " + doctorId);

		int patientId = Integer.parseInt(request.getParameter("patientId"));
		LOG.trace("Request parameter: patientId --> " + patientId);

		
		Patient patient = new PatientDao().findPatientById(patientId);
		LOG.trace("Found in DB: patient --> " + patient);
		
		patient.setStatusId(1);
		
		patientDao.updatePatient(patient);
		LOG.trace("Update in DB: patient --> " + patient);
		
		Doctor doctor = new DoctorDao().findDoctorById(doctorId);
		LOG.trace("Found in DB: doctor --> " + doctor);
		
		int pc = doctor.getPatientCount();
		doctor.setPatientCount(pc+1);
		
		new DoctorDao().updateDoctor(doctor);
		LOG.trace("Update in DB: doctor --> " + doctor);
		
		new DoctorDao().appointDoctor(doctorId, patientId);
		LOG.trace("Insert into DB.patients_list");

		String page = request.getParameter("page");
		LOG.trace("Request parameter: page --> " + page);
		
		

		String forward = Path.PAGE_ERROR_PAGE;

		if ("doctor".equals(page)) {
			request.setAttribute("redirect", Path.REDIRECT_DOCTORS_LIST);
			
			forward = Path.COMMAND_LIST_DOCTORS;
		} else {
			
			request.setAttribute("redirect", Path.REDIRECT_PATIENTS_LIST);
			forward = Path.COMMAND_LIST_PATIENTS;
		}

		LOG.debug("Command finished");
		return forward;
	}

}
