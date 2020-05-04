package ua.nure.chernev.FinalTask.web.command.patient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.chernev.FinalTask.Path;
import ua.nure.chernev.FinalTask.web.command.Command;

/**
 * View settings command.
 * 
 */
public class ViewPatientCardCommand extends Command {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2465023813664890714L;
	private static final Logger LOG = Logger.getLogger(ViewPatientCardCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {		
		LOG.debug("Command starts");

		LOG.debug("Command finished");
		return Path.PAGE_PATIENT_CARD;
	}

}
