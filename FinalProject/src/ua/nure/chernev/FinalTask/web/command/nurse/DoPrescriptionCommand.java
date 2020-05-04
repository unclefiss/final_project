package ua.nure.chernev.FinalTask.web.command.nurse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.chernev.FinalTask.Path;
import ua.nure.chernev.FinalTask.db.DAO.PrescriptionDao;
import ua.nure.chernev.FinalTask.db.entity.Prescription;
import ua.nure.chernev.FinalTask.exception.DBException;
import ua.nure.chernev.FinalTask.web.command.Command;

/**
 * View settings command.
 * 
 * @author D.Kolesnikov
 * 
 */
public class DoPrescriptionCommand extends Command {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3848625355304087278L;
	
	private static final Logger LOG = Logger.getLogger(DoPrescriptionCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {		
		LOG.debug("Command starts");
		
		int prescriptionId = Integer.parseInt(request.getParameter("id"));
		
		PrescriptionDao prescriptionDao = new PrescriptionDao();
		Prescription prescription = prescriptionDao.findPrescriptionsById(prescriptionId);
		prescription.setStatusId(0);
		
		prescriptionDao.updatePrescription(prescription);
		LOG.trace("Update in DB: prescription --> " + prescription);
		
		request.setAttribute("redirect", "/FinalProject/controller?command=listPrescriptions");
		
		LOG.debug("Command finished");
		return Path.COMMAND_LIST_PRESCRIPTIONS;
	}

}