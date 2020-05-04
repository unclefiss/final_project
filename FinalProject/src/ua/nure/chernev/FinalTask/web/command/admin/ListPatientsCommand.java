package ua.nure.chernev.FinalTask.web.command.admin;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.chernev.FinalTask.Path;
import ua.nure.chernev.FinalTask.db.DAO.DoctorDao;
import ua.nure.chernev.FinalTask.db.DAO.PatientDao;
import ua.nure.chernev.FinalTask.db.DAO.StatusDao;
import ua.nure.chernev.FinalTask.db.entity.Doctor;
import ua.nure.chernev.FinalTask.db.entity.Patient;
import ua.nure.chernev.FinalTask.db.entity.Status;
import ua.nure.chernev.FinalTask.exception.AppException;
import ua.nure.chernev.FinalTask.web.command.Command;

/**
 * Lists doctors .
 * 
 * @author K. Chernev
 * 
 */
public class ListPatientsCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger.getLogger(ListPatientsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");

		List<Doctor> doctorsBean = new DoctorDao().findDoctors();
		LOG.trace("Found in DB: doctorsBean --> " + doctorsBean);

		request.setAttribute("doctorsBean", doctorsBean);
		LOG.trace("Set the request attribute: doctorsBean" + " --> " + doctorsBean);

		List<Patient> patientsBean = new PatientDao().findNoDoctorPatients();
		LOG.trace("Found in DB: patientsBean --> " + patientsBean);

		request.setAttribute("patientsBean", patientsBean);
		LOG.trace("Set the request attribute: patientsBean" + " --> " + patientsBean);

		HttpSession session = request.getSession();

		List<Patient> patients = null;

		if (session.getAttribute("doctorId") != null) {
			Long doctorId = (Long) session.getAttribute("doctorId");
			LOG.trace("Get session atrtribute: doctorId --> " + doctorId);

			Doctor doctor = new DoctorDao().findDoctorById(doctorId.intValue());
			LOG.trace("Found in DB: doctor --> " + doctor);

			patients = new PatientDao().findPatientsForDoctor(doctor);
			LOG.trace("Found in DB: patientsList --> " + patients);
		} else {
			patients = new PatientDao().findPatients();
			LOG.trace("Found in DB: patientsList --> " + patients);
		}

		if (request.getParameter("sortDirection") != null && request.getParameter("sortId") != null) {
			LOG.trace("Sorting starts");

			int direction = Integer.parseInt(request.getParameter("sortDirection"));
			LOG.trace("Request parameter: sortDirection --> " + direction);

			String sortId = request.getParameter("sortId");
			LOG.trace("Request parameter: sortId --> " + sortId);

			switch (sortId) {
			case "0":
				Collections.sort(patients, new Comparator<Patient>() {
					public int compare(Patient o1, Patient o2) {
						int result = 0;
						if (!o1.getFirstName().equals(o2.getFirstName())) {
							result = o1.getFirstName().compareTo(o2.getFirstName());
						} else {
							result = o1.getLastName().compareTo(o2.getLastName());
						}
						return result * direction;
					}
				});
				LOG.trace("Sorting by alphabet");
				break;
			case "1":
				Collections.sort(patients, new Comparator<Patient>() {
					public int compare(Patient o1, Patient o2) {
						return (o1.getBirthday().compareTo(o2.getBirthday()) * direction);
					}
				});
				LOG.trace("Sorting by birthday");
				break;
			default:
				break;
			}
			request.setAttribute("redirect", Path.REDIRECT_PATIENTS_SORT);
			LOG.trace("Set the request attribute: redirect" + " --> " + Path.REDIRECT_PATIENTS_SORT);
			LOG.trace("Sorting end");
		}
		
		List<Status> patientStatuses = new StatusDao().findPatientStatuses();
		session.setAttribute("patientStatuses", patientStatuses);
		LOG.trace("Set the request attribute: patientStatuses" + " --> " + patientStatuses);

		// put menu items list to the request
		session.setAttribute("patients", patients);
		LOG.trace("Set the request attribute: patients" + " --> " + patients);

		LOG.debug("Command finished");
		return Path.PAGE_LIST_PATIENTS;
	}

}