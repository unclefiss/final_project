package ua.nure.chernev.FinalTask.web.command.doctor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.chernev.FinalTask.Path;
import ua.nure.chernev.FinalTask.db.DAO.DiagnosisDao;
import ua.nure.chernev.FinalTask.db.DAO.HospitalCardDao;
import ua.nure.chernev.FinalTask.db.DAO.PatientDao;
import ua.nure.chernev.FinalTask.db.DAO.PrescriptionDao;
import ua.nure.chernev.FinalTask.db.entity.Diagnosis;
import ua.nure.chernev.FinalTask.db.entity.HospitalCard;
import ua.nure.chernev.FinalTask.db.entity.Prescription;
import ua.nure.chernev.FinalTask.exception.AppException;
import ua.nure.chernev.FinalTask.web.command.Command;

public class AddInterimDiagnosisCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1230154286441386985L;

	private static final Logger LOG = Logger.getLogger(AddInterimDiagnosisCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");

		HttpSession session = request.getSession();

		int patientId = (Integer) session.getAttribute("currentPatientIdSession");
		HospitalCardDao cardDao = new HospitalCardDao();
		HospitalCard card = cardDao.findCardByPatientId(new PatientDao().findPatientById(patientId));
		DiagnosisDao diagnosisDao = new DiagnosisDao();
		Diagnosis diagnosis = new Diagnosis();
		
		String diagnosisText = request.getParameter("interimDiagnosis");
		LOG.debug("Get the parameter from request" + diagnosisText);
		
		diagnosis.setComment(diagnosisText);
		diagnosis.setCardId(card.getId().intValue());
		
		diagnosisDao.insertInterimDiagnosis(diagnosis);
		LOG.debug("Inert into DB: diagnosis" + diagnosis);
		
		PrescriptionDao prescriptionDao = new PrescriptionDao();
		Prescription prescription = new Prescription();
		
		String prescriptionComment = request.getParameter("prescriptionComment");
		LOG.debug("Get the parameter from request" + prescriptionComment);
		
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		LOG.debug("Get the parameter from request" + typeId);
		
		prescription.setComment(prescriptionComment);
		prescription.setTypeId(typeId);
		prescription.setDiagnosisId(diagnosisDao.findLastDiagnosisId());
		prescription.setDiagnosisComment(diagnosisText);
		LOG.debug("Initiliazation prescription --> " + prescription);
		
		prescriptionDao.insertPrescription(prescription);
		LOG.debug("Inert into DB: prescription" + prescription);
		
		request.setAttribute("redirect", Path.REDIRECT_PATIENT_CARD);
		

		LOG.debug("Command finished");
		return Path.COMMAND_VIEW_PATIENT_CARD;
	}

}
