package ua.nure.chernev.FinalTask.web.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.chernev.FinalTask.Path;
import ua.nure.chernev.FinalTask.db.DAO.DoctorDao;
import ua.nure.chernev.FinalTask.db.DAO.UserDao;
import ua.nure.chernev.FinalTask.db.entity.Doctor;
import ua.nure.chernev.FinalTask.db.entity.User;
import ua.nure.chernev.FinalTask.exception.AppException;
import ua.nure.chernev.FinalTask.web.command.Command;

public class AddDoctorCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5873715829460342566L;

	private static final Logger LOG = Logger.getLogger(AddDoctorCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");

		// obtain login and password from a request
		Doctor doctor = new Doctor();
		LOG.trace("Created the new doctor");

		UserDao userDao = new UserDao();

		User user = new User();
		LOG.trace("Created the new user");

		doctor.setPositionId(0);
		user.setRoleId(2);

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

		if (newPassword == null|| newPassword.isEmpty() || !(newPassword.equals(newPasswordRepeat))) {
			LOG.error("Password was written wrong");
			throw new AppException("Password was written wrong");
		}
		user.setPassword(newPassword);

		String firstName = request.getParameter("newFirstName");
		LOG.trace("Request parameter: newFirstName --> " + firstName);
		String lastName = request.getParameter("newLastName");
		LOG.trace("Request parameter: newLastName --> " + lastName);

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

		doctor.setUserId(userId);

		String newCategory = request.getParameter("newCategory");
		LOG.trace("Request parameter: newCategory --> " + newCategory);

		doctor.setRoleId(2);
		doctor.setCategoryId(Integer.parseInt(newCategory));

		doctor.setFirstName(firstName);
		doctor.setLastName(lastName);
		doctor.setPassword(newPassword);
		doctor.setLogin(newLogin);

		LOG.trace("Initilazation doctor -->" + doctor);

		new DoctorDao().insertDoctor(doctor);
		LOG.trace("Insert into DB.doctors : doctor --> " + doctor);

		request.setAttribute("redirect", Path.REDIRECT_DOCTORS_LIST);
		LOG.trace("Set atribute into request: redirect --> " + Path.REDIRECT_DOCTORS_LIST);

		String forward = Path.COMMAND_LIST_DOCTORS;

		LOG.debug("Command finished");
		return forward;
	}

}
