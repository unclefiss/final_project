package ua.nure.chernev.FinalTask.web.command.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.chernev.FinalTask.Path;
import ua.nure.chernev.FinalTask.db.DAO.HospitalCardDao;
import ua.nure.chernev.FinalTask.db.DAO.PatientDao;
import ua.nure.chernev.FinalTask.db.DAO.UserDao;
import ua.nure.chernev.FinalTask.db.entity.HospitalCard;
import ua.nure.chernev.FinalTask.db.entity.Patient;
import ua.nure.chernev.FinalTask.db.entity.User;
import ua.nure.chernev.FinalTask.exception.AppException;
import ua.nure.chernev.FinalTask.web.command.Command;

public class AddPatientCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5873715829460342566L;

	private static final Logger LOG = Logger.getLogger(AddPatientCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");

		PatientDao patientDao = new PatientDao();
		UserDao userDao = new UserDao();
		HospitalCardDao cardDao = new HospitalCardDao();
		Patient patient = new Patient();
		User user = new User();

		user.setRoleId(1);

		String newLogin = request.getParameter("newLogin");
		LOG.trace("Request parameter: newLogin --> " + newLogin);

		if (newLogin == null || newLogin.isEmpty()) {
			LOG.error("Login cannot be empty");
			throw new AppException("Login cannot be empty");
		}

		if (userDao.findExistLogin(newLogin)) {
			LOG.error("Login is already taken");
			throw new AppException("Login is already taken");
		}
		user.setLogin(newLogin);

		String newPassword = request.getParameter("newPassword");
		LOG.trace("Request parameter: newPassword --> " + newPassword);
		String newPasswordRepeat = request.getParameter("newPasswordRepeat");
		LOG.trace("Request parameter: newPasswordRepeat --> " + newPasswordRepeat);

		if (newPassword == null || newPassword.isEmpty() || !(newPassword.equals(newPasswordRepeat))) {
			LOG.error("Password was written wrong");
			throw new AppException("Password was written wrong");
		}
		user.setPassword(newPassword);

		String firstName = request.getParameter("newFirstName");
		String lastName = request.getParameter("newLastName");

		if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
			LOG.error("Name cannot be empty");
			throw new AppException("Name cannot be empty");
		}

		user.setFirstName(firstName);
		user.setLastName(lastName);
		LOG.trace("Initilazation user -->" + user);

		userDao.insertUser(user);
		LOG.trace("Insert into DB.users: user --> " + user);

		int userId = userDao.findLastUserId();

		patient.setUserId(userId);

		String phonenumber = request.getParameter("newPhonenumber");
		LOG.trace("Request parameter: newPhonenumber --> " + phonenumber);
		patient.setPhonenumber(phonenumber);

		String birthday = request.getParameter("newBirthday");
		LOG.trace("Request parameter: newBirthday --> " + birthday);
		patient.setBirthday(birthday);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(new Date());
		patient.setDateRegistration(date);
		patient.setDateDischarge("2000-01-01");
		LOG.trace("Request parameter: date --> " + date);

		patient.setRoleId(1);

		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		patient.setPassword(newPassword);
		patient.setLogin(newLogin);
		LOG.trace("Initilazation patient -->" + patient);

		patientDao.insertPatient(patient);
		LOG.trace("Insert into DB.patients : patient --> " + patient);

		HospitalCard card = new HospitalCard();

		int patientId = patientDao.findLastPatientId();

		card.setPatientId(patientId);
		card.setStatusId(0);

		cardDao.insertCard(card);
		LOG.trace("Insert into DB.hospital_cards : card --> " + card);

		String forward = Path.COMMAND_LIST_PATIENTS;

		LOG.debug("Command finished");
		return forward;
	}

}
