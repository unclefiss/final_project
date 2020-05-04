
package ua.nure.chernev.FinalTask.web.command.patient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.chernev.FinalTask.Path;
import ua.nure.chernev.FinalTask.db.DAO.DiagnosisDao;
import ua.nure.chernev.FinalTask.db.DAO.DoctorDao;
import ua.nure.chernev.FinalTask.db.DAO.HospitalCardDao;
import ua.nure.chernev.FinalTask.db.DAO.PatientDao;
import ua.nure.chernev.FinalTask.db.DAO.PrescriptionDao;
import ua.nure.chernev.FinalTask.db.DAO.StatusDao;
import ua.nure.chernev.FinalTask.db.entity.Diagnosis;
import ua.nure.chernev.FinalTask.db.entity.Doctor;
import ua.nure.chernev.FinalTask.db.entity.HospitalCard;
import ua.nure.chernev.FinalTask.db.entity.Patient;
import ua.nure.chernev.FinalTask.db.entity.Prescription;
import ua.nure.chernev.FinalTask.db.entity.Status;
import ua.nure.chernev.FinalTask.exception.AppException;
import ua.nure.chernev.FinalTask.web.command.Command;

/**
 * Patient card command.
 * 
 * 
 */
public class PatientCardCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(PatientCardCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession();

		int patientId = 0;

		if (request.getParameter("currentPatientId") != null) {
			patientId = Integer.parseInt(request.getParameter("currentPatientId"));
		} else {
			patientId = (Integer) session.getAttribute("currentPatientIdSession");
		}

		Patient patient = new PatientDao().findPatientById(patientId);
		LOG.trace("Found in DB: patient --> " + patient);

		HospitalCard card = new HospitalCardDao().findCardByPatientId(patient);
		LOG.trace("Found in DB: card --> " + card);

		List<Prescription> prescriptions = new PrescriptionDao().findPrescriptionsByCardId(card);
		LOG.trace("Found in DB: prescriptions --> " + prescriptions);

		if (prescriptions.isEmpty()) {
			session.setAttribute("prescriptionsEmpty", true);
			LOG.trace("Set the session attribute: prescriptionsEmpty --> " + true);
		} else {
			session.setAttribute("prescriptionsEmpty", false);
			LOG.trace("Set the session attribute: prescriptionsEmpty --> " + false);
		}

		List<Diagnosis> diagnoses = new DiagnosisDao().findDiagnosisByCardId(card);
		LOG.trace("Found in DB: diagnoses --> " + diagnoses);

		session.setAttribute("diagnoses", diagnoses);
		LOG.trace("Set the session attribute: diagnoses --> " + diagnoses);

		for (int i = 0; i < prescriptions.size(); i++) {
			prescriptions.get(i).setDiagnosisComment(diagnoses.get(i).getComment());
		}

		Diagnosis finalDiagnosis = new DiagnosisDao().findFinalDiagnosis(card);

		if (finalDiagnosis != null) {
			session.setAttribute("finalDiagnosis", finalDiagnosis);
			LOG.trace("Set the session attribute: finalDiagnosis --> " + finalDiagnosis);
		}
		
		Doctor doctor = new DoctorDao().findDoctorForPatient(patient);
		if(doctor == null) {
			doctor = new Doctor();
			doctor.setFirstName("-");
			doctor.setLastName("-");
		}
		session.setAttribute("attendingDoctor", doctor);
		LOG.trace("Set the session attribute: attendingDoctor --> " + doctor);
		
		List<Status> types = new StatusDao().findTypes();
		session.setAttribute("types", types);
		LOG.trace("Set the session attribute: types --> " + types);
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    
	    String date = dateFormat.format(new Date());
		session.setAttribute("date", date);
		LOG.trace("Set the session attribute: date --> " + date);
		
		List<Status> prescriptionStatuses = new StatusDao().findPrescriptionStatuses();
		session.setAttribute("prescriptionStatuses", prescriptionStatuses);
		LOG.trace("Set the session attribute: types --> " + prescriptionStatuses);

		session.setAttribute("prescriptions", prescriptions);
		LOG.trace("Set the session attribute: prescriptions --> " + prescriptions);

		session.setAttribute("card", card);
		LOG.trace("Set the session attribute: card --> " + card);

		session.setAttribute("currentPatient", patient);
		LOG.trace("Set the session attribute: patient --> " + patient);

		session.setAttribute("currentPatientIdSession", patient.getId().intValue());
		LOG.trace("Set the session attribute: currentPatientId --> " + patient.getId().intValue());

		request.setAttribute("redirect", Path.REDIRECT_VIEW_PATIENT_CARD);

		LOG.debug("Command finished");
		return Path.PAGE_PATIENT_CARD;
	}

}