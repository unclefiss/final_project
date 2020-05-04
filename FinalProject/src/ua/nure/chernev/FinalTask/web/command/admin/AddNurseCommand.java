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

public class AddNurseCommand  extends Command {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5873715829460342566L;
	
	
	private static final Logger LOG = Logger.getLogger(AddNurseCommand.class);


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		LOG.debug("Command starts");

		String forward = Path.COMMAND_LIST_NURSES;
		Doctor nurse = new Doctor();
		UserDao userDao = new UserDao();
		DoctorDao doctorDao = new DoctorDao();
		User user = new User();
		
		nurse.setPositionId(1);
		user.setRoleId(2);
		
		String newLogin = request.getParameter("newLogin");
		LOG.trace("Request parameter: newLogin --> " + newLogin);
		
		if(newLogin == null || newLogin.isEmpty()) {
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
		String lastName = request.getParameter("newLastName");
		
		if(firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
			LOG.error("Name cannot be empty");
			throw new AppException("Name cannot be empty");
		}
		
		user.setFirstName(firstName);
		user.setLastName(lastName);
		LOG.trace("Initilazation user -->" + user);
		
//		String category = request.getParameter("newCategory");
		
		userDao.insertUser(user);
		LOG.trace("Insert into DB.users: user --> " + user);
		
		int userId = userDao.findLastUserId();
		
		nurse.setUserId(userId);
		nurse.setRoleId(2);
		nurse.setCategoryId(0);
		
		nurse.setFirstName(firstName);
		nurse.setLastName(lastName);
		nurse.setPassword(newPassword);
		nurse.setLogin(newLogin);
		LOG.trace("Initilazation nurse -->" + nurse);
		
		doctorDao.insertNurse(nurse);
		LOG.trace("Insert into DB.doctors : nurse --> " + nurse);
		
		request.setAttribute("redirect", Path.REDIRECT_DOCTORS_LIST);
		LOG.trace("Set request attribute redirect:-->"+ Path.REDIRECT_DOCTORS_LIST);
		
		


		LOG.debug("Command finished");
		return forward;
	}

}
