package ua.nure.chernev.FinalTask.web.command.admin;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.chernev.FinalTask.Path;
import ua.nure.chernev.FinalTask.db.DAO.DoctorDao;
import ua.nure.chernev.FinalTask.db.entity.Doctor;
import ua.nure.chernev.FinalTask.exception.AppException;
import ua.nure.chernev.FinalTask.web.command.Command;

/**
 * Lists doctors .
 * 
 * @author K. Chernev
 * 
 */
public class ListNursesCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger.getLogger(ListNursesCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");

		List<Doctor> nurses = new DoctorDao().findNurses();
		LOG.trace("Found in DB: doctorsList --> " + nurses);

		Collections.sort(nurses, new Comparator<Doctor>() {
			public int compare(Doctor o1, Doctor o2) {
				return (int) (o1.getCategoryId() - o2.getCategoryId());
			}
		});
		
		request.setAttribute("nurses", nurses);
		LOG.trace("Set the request attribute: nurses" + " --> " + nurses);

		LOG.debug("Command finished");
		return Path.PAGE_LIST_NURSES;
	}

}