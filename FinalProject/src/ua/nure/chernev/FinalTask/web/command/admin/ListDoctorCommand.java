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
 * @author K.Chernev
 * 
 */
public class ListDoctorCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger.getLogger(ListDoctorCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");

		HttpSession session = request.getSession();

		List<Doctor> doctorsBean = new DoctorDao().findDoctors();
		LOG.trace("Found in DB: doctorsBean --> " + doctorsBean);

		session.setAttribute("doctorsBean", doctorsBean);
		LOG.trace("Set the request attribute: doctorsBean" + " --> " + doctorsBean);

		List<Patient> patientsBean = new PatientDao().findNoDoctorPatients();
		LOG.trace("Found in DB: patientsBean --> " + patientsBean);

		session.setAttribute("patientsBean", patientsBean);
		LOG.trace("Set the request attribute: patientsBean" + " --> " + patientsBean);

		List<Status> categories = new StatusDao().findCategories();
		LOG.trace("Found in DB: categories --> " + categories);

		List<Doctor> doctors = null;

		if (request.getParameter("categoryId") != null) {
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			if (categoryId != -1) {
				doctors = new DoctorDao().findDoctorsByCategoryId(categoryId);
				LOG.trace("Found in DB: doctorsList --> " + doctors);
				session.setAttribute("doctors", doctors);
				LOG.trace("Set the session attribute: doctors" + " --> " + doctors);
				request.setAttribute("redirect", Path.REDIRECT_DOCTORS_SELECT);
			} else {
				doctors = new DoctorDao().findDoctors();
				LOG.trace("Found in DB: doctorsList --> " + doctors);
				request.setAttribute("redirect", Path.REDIRECT_DOCTORS_SELECT);
			}
		} else {
			doctors = new DoctorDao().findDoctors();
			LOG.trace("Found in DB: doctorsList --> " + doctors);
		}
		
		if (request.getParameter("sortDirection") != null && request.getParameter("sortId") != null) {
			LOG.debug("Sorting starts");

			doctors = (List<Doctor>) session.getAttribute("doctors");

			int direction = Integer.parseInt(request.getParameter("sortDirection"));
			LOG.trace("Request parameter: sortDirection --> " + direction);

			String sortId = request.getParameter("sortId");
			LOG.trace("Request parameter: sortId --> " + sortId);

			switch (sortId) {
			case "0":
				Collections.sort(doctors, new Comparator<Doctor>() {
					public int compare(Doctor o1, Doctor o2) {
						int result = 0;
						if (!o1.getFirstName().equals(o2.getFirstName())) {
							result = o1.getFirstName().compareTo(o2.getFirstName());
						} else {
							result = o1.getLastName().compareTo(o2.getLastName());
						}
						return result * direction;
					}
				});
				LOG.debug("Sorting by alphabet");
				break;
			case "1":
				Collections.sort(doctors, new Comparator<Doctor>() {
					public int compare(Doctor o1, Doctor o2) {
						if (o1.getPatientCount() == o2.getPatientCount()) {
							int result = 0;
							if (!o1.getFirstName().equals(o2.getFirstName())) {
								result = o1.getFirstName().compareTo(o2.getFirstName());
							} else {
								result = o1.getLastName().compareTo(o2.getLastName());
							}
							return result;
						} else {
							return ((o1.getPatientCount() - o2.getPatientCount()) * direction);

						}
					}
				});
				LOG.debug("Sorting by patients count");
				break;
			default:
				break;
			}
			LOG.debug("Sorting end");
			request.setAttribute("redirect", Path.REDIRECT_DOCTORS_SORT);
		}

		List<Doctor> nurses = new DoctorDao().findNurses();
		LOG.trace("Found in DB: doctorsList --> " + nurses);

		session.setAttribute("nursesList", true);
		LOG.trace("Set the request attribute: nursesList" + " --> " + true);
		
		if (request.getParameter("categoryId") != null && !request.getParameter("categoryId").equals("-1")) {
			session.setAttribute("nursesList", false);
			LOG.trace("Set the request attribute: nursesList" + " --> " + false);
		}

		session.setAttribute("nurses", nurses);
		LOG.trace("Set the request attribute: nurses" + " --> " + nurses);

		session.setAttribute("doctors", doctors);
		LOG.trace("Set the session attribute: doctors" + " --> " + doctors);

		session.setAttribute("categories", categories);
		LOG.trace("Set the request attribute: categories" + " --> " + categories);

		LOG.debug("Command finished");
		return Path.PAGE_LIST_DOCTOR;
	}

}