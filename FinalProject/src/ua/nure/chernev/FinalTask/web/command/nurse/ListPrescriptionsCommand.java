package ua.nure.chernev.FinalTask.web.command.nurse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.chernev.FinalTask.Path;
import ua.nure.chernev.FinalTask.db.Position;
import ua.nure.chernev.FinalTask.db.DAO.DoctorDao;
import ua.nure.chernev.FinalTask.db.DAO.PatientPrescriptionBeanDao;
import ua.nure.chernev.FinalTask.db.DAO.StatusDao;
import ua.nure.chernev.FinalTask.db.bean.PatientPrescriptionBean;
import ua.nure.chernev.FinalTask.db.entity.Doctor;
import ua.nure.chernev.FinalTask.db.entity.Status;
import ua.nure.chernev.FinalTask.exception.AppException;
import ua.nure.chernev.FinalTask.web.command.Command;

/**
 * Lists doctors .
 * 
 * @author K. Chernev
 * 
 */
public class ListPrescriptionsCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger.getLogger(ListPrescriptionsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");

		HttpSession session = request.getSession();
		List<PatientPrescriptionBean> patientPrescriptionBeans;

		Position doctorPosition = (Position) session.getAttribute("doctorPosition");
		LOG.trace("Get session atrtribute: doctorPosition --> " + doctorPosition);

		if (doctorPosition == Position.DOCTOR) {
			Long doctorId = (Long) session.getAttribute("doctorId");
			LOG.trace("Get session atrtribute: doctorId --> " + doctorId);

			Doctor doctor = new DoctorDao().findDoctorById(doctorId.intValue());
			LOG.trace("Found in DB: doctor --> " + doctor);

			patientPrescriptionBeans = new PatientPrescriptionBeanDao().findPatientPrescriptionBeansForDoctor(doctor);
		} else {
			patientPrescriptionBeans = new PatientPrescriptionBeanDao().findPatientPrescriptionBeansForNurse();
		}

		List<PatientPrescriptionBean> patientPrescriptionBeansUndone = new ArrayList<>();
		List<PatientPrescriptionBean> patientPrescriptionBeansDone = new ArrayList<>();

		for (PatientPrescriptionBean item : patientPrescriptionBeans) {
			if (item.getStatusId() == 1) {
				patientPrescriptionBeansUndone.add(item);
			} else {
				patientPrescriptionBeansDone.add(item);
			}
		}

		List<Status> types = new StatusDao().findTypes();
		request.setAttribute("types", types);
		LOG.trace("Set the request attribute: types" + " --> " + types);

		List<Status> prescriptionStatuses = new StatusDao().findPrescriptionStatuses();
		request.setAttribute("prescriptionStatuses", prescriptionStatuses);
		LOG.trace("Set the request attribute: prescriptionStatuses" + " --> " + prescriptionStatuses);

		request.setAttribute("patientPrescriptionBeansUndone", patientPrescriptionBeansUndone);
		LOG.trace(
				"Set the request attribute: patientPrescriptionBeansUndone" + " --> " + patientPrescriptionBeansUndone);

		request.setAttribute("patientPrescriptionBeansDone", patientPrescriptionBeansDone);
		LOG.trace("Set the request attribute: patientPrescriptionBeansDone" + " --> " + patientPrescriptionBeansDone);

		LOG.debug("Command finished");
		return Path.PAGE_LIST_PRESCRIPTIONS;
	}

}