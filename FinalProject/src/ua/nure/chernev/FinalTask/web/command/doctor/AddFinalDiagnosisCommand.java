package ua.nure.chernev.FinalTask.web.command.doctor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import ua.nure.chernev.FinalTask.db.entity.Diagnosis;
import ua.nure.chernev.FinalTask.db.entity.Doctor;
import ua.nure.chernev.FinalTask.db.entity.HospitalCard;
import ua.nure.chernev.FinalTask.db.entity.Patient;
import ua.nure.chernev.FinalTask.exception.AppException;
import ua.nure.chernev.FinalTask.web.command.Command;

public class AddFinalDiagnosisCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1230154286441386985L;

	private static final Logger LOG = Logger.getLogger(AddFinalDiagnosisCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");

		HttpSession session = request.getSession();

		int patientId = (Integer) session.getAttribute("currentPatientIdSession");
		PatientDao patientDao = new PatientDao();
		Patient patient = patientDao.findPatientById(patientId);
		HospitalCardDao cardDao = new HospitalCardDao();
		
		HospitalCard card = cardDao.findCardByPatientId(patient);
		card.setStatusId(1);
		DiagnosisDao diagnosisDao = new DiagnosisDao();
		Diagnosis diagnosis = new Diagnosis();
		
		String diagnosisText = request.getParameter("finalDiagnosis");
		LOG.debug("Get the parameter from request" + diagnosisText);
		
		diagnosis.setComment(diagnosisText);
		diagnosis.setCardId(card.getId().intValue());
		LOG.debug("Intilialization diagnosis --> " + diagnosis);
		
		diagnosisDao.insertFinalDiagnosis(diagnosis);
		LOG.debug("Insert into DB: diagnosis " + diagnosis);
		
		DoctorDao doctorDao = new DoctorDao();
		Doctor doctor = doctorDao.findDoctorForPatient(patient);
		
		int patientsCount = doctor.getPatientCount();
		doctor.setPatientCount(patientsCount-1);
		
		doctorDao.updateDoctor(doctor);
		LOG.debug("Update in the DB: doctor" + doctor);
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(new Date());
		patient.setDateDischarge(date);
		LOG.trace("Request parameter: date --> " + date);
		patient.setStatusId(2);
		
		patientDao.updatePatient(patient);
		LOG.debug("Update in the DB: patient" + patient);
		
		request.setAttribute("redirect", Path.REDIRECT_PATIENT_CARD);
		LOG.debug("Set the request parameter " + Path.REDIRECT_PATIENT_CARD);

		LOG.debug("Command finished");
		return Path.COMMAND_VIEW_PATIENT_CARD;
	}

}
