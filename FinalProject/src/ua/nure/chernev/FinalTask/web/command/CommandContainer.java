package ua.nure.chernev.FinalTask.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.chernev.FinalTask.web.command.admin.AddDoctorCommand;
import ua.nure.chernev.FinalTask.web.command.admin.AddNurseCommand;
import ua.nure.chernev.FinalTask.web.command.admin.AddPatientCommand;
import ua.nure.chernev.FinalTask.web.command.admin.AppointDoctorCommand;
import ua.nure.chernev.FinalTask.web.command.admin.DeleteDoctorCommand;
import ua.nure.chernev.FinalTask.web.command.admin.DeletePatientCommand;
import ua.nure.chernev.FinalTask.web.command.admin.ListDoctorCommand;
import ua.nure.chernev.FinalTask.web.command.admin.ListNursesCommand;
import ua.nure.chernev.FinalTask.web.command.admin.ListPatientsCommand;
import ua.nure.chernev.FinalTask.web.command.admin.SelectCategoryCommand;
import ua.nure.chernev.FinalTask.web.command.admin.SortDoctorsCommand;
import ua.nure.chernev.FinalTask.web.command.admin.SortPatientsCommand;
import ua.nure.chernev.FinalTask.web.command.admin.ViewAddDoctorCommand;
import ua.nure.chernev.FinalTask.web.command.admin.ViewAddNurseCommand;
import ua.nure.chernev.FinalTask.web.command.admin.ViewAddPatientCommand;
import ua.nure.chernev.FinalTask.web.command.doctor.AddFinalDiagnosisCommand;
import ua.nure.chernev.FinalTask.web.command.doctor.AddInterimDiagnosisCommand;
import ua.nure.chernev.FinalTask.web.command.doctor.ViewAddInterimDiagnosisCommand;
import ua.nure.chernev.FinalTask.web.command.nurse.DoPrescriptionCommand;
import ua.nure.chernev.FinalTask.web.command.nurse.ListPrescriptionsCommand;
import ua.nure.chernev.FinalTask.web.command.patient.DownloadCardCommand;
import ua.nure.chernev.FinalTask.web.command.patient.PatientCardCommand;
import ua.nure.chernev.FinalTask.web.command.patient.ViewPatientCardCommand;

/**
 * Holder for all commands.<br/>
 * 
 * @author D.Kolesnikov
 * 
 */
public class CommandContainer {
	
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	
	private static Map<String, Command> commands = new TreeMap<String, Command>();
	
	static {
		// common commands
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("viewSettings", new ViewSettingsCommand());
		commands.put("updateSettings", new UpdateSettingsCommand());
		commands.put("noCommand", new NoCommand());
		
		// admin commands
		commands.put("listDoctors", new ListDoctorCommand());
		commands.put("listNurses", new ListNursesCommand());
		commands.put("listPatients", new ListPatientsCommand());
		commands.put("viewAddDoctor", new ViewAddDoctorCommand());
		commands.put("addDoctor", new AddDoctorCommand());
		commands.put("viewAddNurse", new ViewAddNurseCommand());
		commands.put("addNurse", new AddNurseCommand());
		commands.put("viewAddPatient", new ViewAddPatientCommand());
		commands.put("addPatient", new AddPatientCommand());
		commands.put("appointDoctor", new AppointDoctorCommand());
		commands.put("viewPatientCard", new ViewPatientCardCommand());
		commands.put("selectCategory", new SelectCategoryCommand());
		commands.put("sortDoctors", new SortDoctorsCommand());
		commands.put("sortPatients", new SortPatientsCommand());
		commands.put("patientCard", new PatientCardCommand());
		commands.put("downloadCard", new DownloadCardCommand());
		commands.put("deleteDoctor", new DeleteDoctorCommand());
		commands.put("deletePatient", new DeletePatientCommand());
		
		//nurse commands
		commands.put("listPrescriptions", new ListPrescriptionsCommand());
		commands.put("doPrescription", new DoPrescriptionCommand());
		commands.put("viewPrescriptionList", new DoPrescriptionCommand());
		
		//doctor commands
		commands.put("viewAddInterimDiagnosis", new ViewAddInterimDiagnosisCommand());
		commands.put("addInterimDiagnosis", new AddInterimDiagnosisCommand());
		commands.put("addFinalDiagnosis", new AddFinalDiagnosisCommand());
		
		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}
	
}