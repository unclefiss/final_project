package ua.nure.chernev.FinalTask.web.command.doctor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.chernev.FinalTask.Path;
import ua.nure.chernev.FinalTask.web.command.Command;

/**
 * View settings command.
 * 
 * @author D.Kolesnikov
 * 
 */
public class ViewAddInterimDiagnosisCommand extends Command {
	
	private static final long serialVersionUID = -3071536593627692473L;
	
	private static final Logger LOG = Logger.getLogger(ViewAddInterimDiagnosisCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {		
		LOG.debug("Command starts");

		LOG.debug("Command finished");
		return Path.PAGE_ADD_INTERIM_DIAGNOSIS;
	}

}