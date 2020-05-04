package ua.nure.chernev.FinalTask.web.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.chernev.FinalTask.Path;
import ua.nure.chernev.FinalTask.db.DAO.DoctorDao;
import ua.nure.chernev.FinalTask.db.DAO.PatientDao;
import ua.nure.chernev.FinalTask.db.DAO.UserDao;
import ua.nure.chernev.FinalTask.db.entity.Doctor;
import ua.nure.chernev.FinalTask.db.entity.Patient;
import ua.nure.chernev.FinalTask.exception.AppException;
import ua.nure.chernev.FinalTask.web.command.Command;

public class DeletePatientCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5873715829460342566L;

	private static final Logger LOG = Logger.getLogger(DeletePatientCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");

		int patientId = Integer.parseInt(request.getParameter("patientId"));
		LOG.trace("Get request parameter: doctorId --> " + patientId);

		Patient patient = new PatientDao().findPatientById(patientId);
		LOG.trace("Get from DB: patient --> " + patient);
		
		Doctor doctor = new DoctorDao().findDoctorForPatient(patient);
		LOG.trace("Get from DB: doctor --> " + doctor);
		
		if(doctor!= null) {
			int patientCount = doctor.getPatientCount();
			doctor.setPatientCount(patientCount -1);
			new DoctorDao().updateDoctor(doctor);
			LOG.trace("Update in DB: doctor --> " + doctor);
		}
		
		new UserDao().deleteUser(new UserDao().findUser((long) patient.getUserId()));
		LOG.trace("Delete from DB: patient --> " + patient);

		request.setAttribute("redirect", Path.REDIRECT_PATIENTS_LIST);

		LOG.debug("Command finished");
		String forward = Path.PAGE_ERROR_PAGE;
		return forward;
	}

}
