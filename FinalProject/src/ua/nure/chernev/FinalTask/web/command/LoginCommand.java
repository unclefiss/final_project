package ua.nure.chernev.FinalTask.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.chernev.FinalTask.Path;
import ua.nure.chernev.FinalTask.db.Position;
import ua.nure.chernev.FinalTask.db.Role;
import ua.nure.chernev.FinalTask.db.DAO.DoctorDao;
import ua.nure.chernev.FinalTask.db.DAO.PatientDao;
import ua.nure.chernev.FinalTask.db.DAO.StatusDao;
import ua.nure.chernev.FinalTask.db.DAO.UserDao;
import ua.nure.chernev.FinalTask.db.entity.Doctor;
import ua.nure.chernev.FinalTask.db.entity.Patient;
import ua.nure.chernev.FinalTask.db.entity.Status;
import ua.nure.chernev.FinalTask.db.entity.User;
import ua.nure.chernev.FinalTask.exception.AppException;

/**
 * Login command.
 * 
 * @author D.Kolesnikov
 * 
 */
public class LoginCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession();

		// obtain login and password from a request
		String login = request.getParameter("login");
		LOG.trace("Request parameter: login --> " + login);

		String password = request.getParameter("password");
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			throw new AppException("Login/password cannot be empty");
		}
//

		// error handler
		String errorMessage = null;		
		String forward = Path.PAGE_ERROR_PAGE;
		
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			errorMessage = "Login/password cannot be empty";
			request.setAttribute("errorMessage", errorMessage);
			LOG.error("errorMessage --> " + errorMessage);
			return forward;
		}
		
		User user = new UserDao().findUserByLogin(login);
		LOG.trace("Found in DB: user --> " + user);
			
		if (user == null || !password.equals(user.getPassword())) {
			errorMessage = "Cannot find user with such login/password";
			request.setAttribute("errorMessage", errorMessage);
			LOG.error("errorMessage --> " + errorMessage);
			return forward;
		} else {
			Role userRole = Role.getRole(user);
			LOG.trace("userRole --> " + userRole);
			if (userRole == Role.ADMIN) {
				request.setAttribute("redirect", Path.REDIRECT_DOCTORS_LIST);
			}
	
			if (userRole == Role.PATIENT) {
				Patient patient = new PatientDao().findPatientByUserId(user);
				
				session.setAttribute("patient", patient);
				LOG.trace("Set the session attribute: patient --> " + patient);
	
				session.setAttribute("currentPatientIdSession", patient.getId().intValue());
				LOG.trace("Set the session attribute: currentPatientIdSession --> " + patient.getId().intValue());
				
				request.setAttribute("redirect", Path.REDIRECT_PATIENT_CARD);
				
				forward = Path.COMMAND_VIEW_PATIENT_CARD;
			}
			
			if (userRole == Role.DOCTOR) {
				
				Doctor doctor = new DoctorDao().findDoctorByUserId(user);
				LOG.trace("Found in DB: doctor --> " + doctor);
				
				Position doctorPosition = Position.getPosition(doctor);
				LOG.trace("doctorPosition --> " + doctorPosition);
				
				if(doctorPosition == Position.DOCTOR) {
					request.setAttribute("redirect", Path.REDIRECT_PATIENTS_LIST);
					LOG.trace("Set request attribute redirect --> " + Path.REDIRECT_PATIENTS_LIST);
					
				} else {
					request.setAttribute("redirect", Path.REDIRECT_PRESCRIPTIONS_LIST);
					LOG.trace("Set request attribute redirect --> " + Path.REDIRECT_PRESCRIPTIONS_LIST);
					
					forward = Path.COMMAND_LIST_PRESCRIPTIONS;
				}
				
				session.setAttribute("doctor", doctor);
				LOG.trace("Set the session attribute: doctor --> " + doctor);
				
				session.setAttribute("doctorId", doctor.getId());
				LOG.trace("Set the session attribute: doctorId --> " + doctor.getId());
	
				session.setAttribute("doctorPosition", doctorPosition);
				LOG.trace("Set the session attribute: doctorPosition --> " + doctorPosition);
			}
			
			String userLocaleName = user.getLocaleName();
			LOG.trace("userLocalName --> " + userLocaleName);
			
			if (userLocaleName != null && !userLocaleName.isEmpty()) {
				session.setAttribute("lang", userLocaleName);
				LOG.trace("Set the session attribute: defaultLocaleName --> " + userLocaleName);
				
				LOG.info("Locale for user: defaultLocale --> " + userLocaleName);
			}
			
			List<Status> roles = new StatusDao().findRoles();
			session.setAttribute("roles", roles);
			LOG.trace("Set the session attribute: roles --> " + roles);
			
			List<Status> positions = new StatusDao().findPositions();
			session.setAttribute("positions", positions);
			LOG.trace("Set the session attribute: positions --> " + positions);
			
			session.setAttribute("user", user);
			LOG.trace("Set the session attribute: user --> " + user);
				
			session.setAttribute("userRole", userRole);				
			LOG.trace("Set the session attribute: userRole --> " + userRole);
				
			LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());
			
		}
		
		LOG.debug("Command finished");
		return forward;
	}
		
}