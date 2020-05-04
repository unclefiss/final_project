package ua.nure.chernev.FinalTask.web.command;

import org.apache.log4j.Logger;

import ua.nure.chernev.FinalTask.Path;
import ua.nure.chernev.FinalTask.db.DAO.UserDao;
import ua.nure.chernev.FinalTask.db.entity.User;
import ua.nure.chernev.FinalTask.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Update settings items.
 * 
 * @author D.Kolesnikov
 * 
 */
public class UpdateSettingsCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger.getLogger(UpdateSettingsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");

		User user = (User) request.getSession().getAttribute("user");
		LOG.trace("Get from session user --> " + user);
		boolean updateUser = false;

		String firstName = request.getParameter("firstName");
		if (firstName != null && !firstName.isEmpty()) {
			LOG.trace("Get parameter from request firstName --> " + firstName);
			user.setFirstName(firstName);
			updateUser = true;
		}

		String lastName = request.getParameter("lastName");
		if (lastName != null && !lastName.isEmpty()) {
			LOG.trace("Get parameter from request lastName --> " + lastName);
			user.setLastName(lastName);
			updateUser = true;
		}

		String newLogin = request.getParameter("newLogin");
		if (new UserDao().findExistLogin(newLogin)) {
			LOG.trace("Get parameter from request newLogin --> " + newLogin);
			throw new AppException("Login is already taken");
		} else if (newLogin != null && !newLogin.isEmpty()) {
			user.setLogin(newLogin);
			updateUser = true;
		}

		String newPassword = request.getParameter("newPassword");
		String newPasswordRepeat = request.getParameter("newPasswordRepeat");
		if (newPassword != null && !newPassword.isEmpty()) {
			LOG.trace("Get parameter from request newPassword --> " + newPassword);
			if (newPassword.equals(newPasswordRepeat)) {
				user.setPassword(newPassword);
				updateUser = true;
			} else {
				throw new AppException("Password was written wrong");
			}
		}

		String sessionLocale = request.getParameter("sessionLocale");
		if (sessionLocale != null && !sessionLocale.isEmpty()) {
			LOG.trace("Get parameter from request newPassword --> " + newPassword);
			user.setLocaleName(sessionLocale);
			updateUser = true;
		}

		if (updateUser == true)
			new UserDao().updateUser(user);

		request.setAttribute("redirect", Path.REDIRECT_SETTINGS_PAGE);
		LOG.trace("Set request attribute redirect --> " + Path.REDIRECT_SETTINGS_PAGE);
		

		LOG.debug("Command finished");
		return Path.PAGE_SETTINGS;
	}

}