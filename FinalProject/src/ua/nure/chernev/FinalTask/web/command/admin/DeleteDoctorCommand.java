package ua.nure.chernev.FinalTask.web.command.admin;

import java.io.IOException;
import java.util.List;

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

public class DeleteDoctorCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5873715829460342566L;

	private static final Logger LOG = Logger.getLogger(DeleteDoctorCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");

		int doctorId = Integer.parseInt(request.getParameter("doctorId"));
		LOG.trace("Get request parameter: doctorId --> " + doctorId);

		Doctor doctor = new DoctorDao().findDoctorById(doctorId);
		LOG.trace("Get from DB: doctor --> " + doctor);
		
		new UserDao().deleteUser(new UserDao().findUser((long) doctor.getUserId()));
		LOG.trace("Delete from DB: doctor --> " + doctor);

		List<Patient> patients = new PatientDao().findPatients();
		LOG.trace("Get from DB: patients --> " + patients);

		for (Patient p : patients) {
			if (!(new PatientDao().findPatientWithDoctor(p)) && p.getStatusId() == 1) {
				p.setStatusId(0);
				new PatientDao().updatePatient(p);
				LOG.trace("Update patient in DB: patient --> " + p);
			}
		}
		
		request.setAttribute("redirect", Path.REDIRECT_DOCTORS_LIST);

		LOG.debug("Command finished");
		String forward = Path.PAGE_ERROR_PAGE;
		return forward;
	}

}
