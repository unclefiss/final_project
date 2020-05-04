
package ua.nure.chernev.FinalTask.web.command.patient;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

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
 * Download card command.
 * 
 */
public class DownloadCardCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(DownloadCardCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		response.setContentType("text/plain");
		LOG.debug("Set responce content type: text/plain ");

		HttpSession session = request.getSession();

		int patientId = (Integer) session.getAttribute("currentPatientIdSession");

		Patient patient = new PatientDao().findPatientById(patientId);
		LOG.trace("Found in DB: patient --> " + patient);

		String lang = (String) session.getAttribute("lang");

		response.setHeader("Content-disposition",
				"attachment; filename=Patient #" + patient.getId() + "_" + lang + ".txt");

		HospitalCard card = new HospitalCardDao().findCardByPatientId(patient);
		LOG.trace("Found in DB: card --> " + card);

		List<Prescription> prescriptions = new PrescriptionDao().findPrescriptionsByCardId(card);
		LOG.trace("Found in DB: prescriptions --> " + prescriptions);

		List<Diagnosis> diagnoses = new DiagnosisDao().findDiagnosisByCardId(card);
		LOG.trace("Found in DB: diagnoses --> " + diagnoses);

		for (int i = 0; i < prescriptions.size(); i++) {
			prescriptions.get(i).setDiagnosisComment(diagnoses.get(i).getComment());
		}

		Diagnosis finalDiagnosis = new DiagnosisDao().findFinalDiagnosis(card);

		List<Status> types = new StatusDao().findTypes();
		

		Doctor doctor = new DoctorDao().findDoctorForPatient(patient);
		
		if(doctor == null) {
			doctor = new Doctor();
			doctor.setFirstName("-");
			doctor.setLastName("-");
		}

		Properties property = new Properties();
		try {
			property.load(request.getServletContext()
					.getResourceAsStream("/WEB-INF/properties/resources_" + lang + ".properties"));

			OutputStream outputStream = response.getOutputStream();
			StringBuilder str = new StringBuilder(property.getProperty("card_jsp.patient") + " # " + patient.getId());
			str.append(System.lineSeparator());
			str.append(patient.getFirstName() + " " + patient.getLastName());
			str.append(System.lineSeparator());
			str.append(property.getProperty("card_jsp.birthday") + ": " + patient.getBirthday());
			str.append(System.lineSeparator());
			str.append(property.getProperty("card_jsp.phonenumber") + ": " + patient.getPhonenumber());
			str.append(System.lineSeparator());
			str.append(property.getProperty("card_jsp.attendingDoctor") + ": " + doctor.getFirstName() + " " + doctor.getLastName());
			str.append(System.lineSeparator());
			str.append(property.getProperty("card_jsp.dateRegistration") + ": " + patient.getDateRegistration());
			str.append(System.lineSeparator());
			str.append(property.getProperty("card_jsp.dateDischarge") + ": " + patient.getDateDischarge());
			str.append(System.lineSeparator());
			str.append("--" + property.getProperty("card_jsp.therapy") + "--");
			for (int i = 0; i < prescriptions.size(); i++) {
				str.append(System.lineSeparator());
				str.append(System.lineSeparator());
				str.append(property.getProperty("card_jsp.table.diagnosis") + ": "
						+ prescriptions.get(i).getDiagnosisComment());
				str.append(System.lineSeparator());
				str.append(
						property.getProperty("card_jsp.table.prescription") + ": " + prescriptions.get(i).getComment());
				str.append(System.lineSeparator());
				for (int t = 0; t < types.size(); t++) {
					if (prescriptions.get(i).getTypeId() == types.get(t).getId()) {
						str.append(property.getProperty("card_jsp.table.type") + ": ");
						if (lang.equals("en")) {
							str.append(types.get(t).getName());
						} else {
							str.append(types.get(t).getNameRu());
						}
					}
				}
			}
			str.append(System.lineSeparator());
			str.append(System.lineSeparator());
			str.append(property.getProperty("card_jsp.final_diagnosis") + ": " + finalDiagnosis.getComment());
			str.append(System.lineSeparator());
			outputStream.write(str.toString().getBytes());
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("redirect", Path.REDIRECT_VIEW_PATIENT_CARD);
		LOG.trace("Set request attribute redirect: " + Path.REDIRECT_VIEW_PATIENT_CARD);

		LOG.debug("Command finished");
		return Path.PAGE_PATIENT_CARD;
	}

}