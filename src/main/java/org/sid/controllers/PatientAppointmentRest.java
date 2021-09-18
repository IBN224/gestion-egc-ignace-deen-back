package org.sid.controllers;

import java.util.List;

import org.sid.entities.Appointment;
import org.sid.entities.Patient;
import org.sid.entities.PatientAppointment;
import org.sid.entities.TimeAppointment;
import org.sid.repositories.AppointmentRepository;
import org.sid.repositories.PatientAppointmentRepository;
import org.sid.repositories.PatientRepository;
import org.sid.repositories.TimeAppointmentRepository;
import org.sid.services.UtilService;
import org.sid.utils.StateAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
@CrossOrigin("*")
public class PatientAppointmentRest {

	@Autowired
	private PatientAppointmentRepository patientAppointmentRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private TimeAppointmentRepository timeAppointmentRepository;
	
	
	@PostMapping(value = "/patient-appointment-schedule")
	public PatientAppointment addPatientAppointment(@RequestBody PatientAppointmentModel appointmentModel) {
		
		TimeAppointment timeAppointment = new TimeAppointment();
		timeAppointment = timeAppointmentRepository.findById(appointmentModel.getTimeAppId()).get();
		Patient patient = new Patient();
		patient = patientRepository
		  .save(new Patient(null, appointmentModel.getNom(), appointmentModel.getPrenom(), appointmentModel.getTelephone(), 
				appointmentModel.getAdresse(), appointmentModel.getAge(), appointmentModel.getGenre(), null, null, false));
		
		PatientAppointment patientAppointment2 = new PatientAppointment();
		patientAppointment2 = patientAppointmentRepository.save(new PatientAppointment(null, patient, timeAppointment));
		
		/*** check if appointment is full ***/
		if(patientAppointmentRepository.getSizeAppointmt(patientAppointment2.getTimeAppointment().getAppointment().getId()) == 
		   patientAppointment2.getTimeAppointment().getAppointment().getMax()) {
			
			Appointment appointment = new Appointment();
			appointment = patientAppointment2.getTimeAppointment().getAppointment();
			appointment.setStateAppointment(StateAppointment.FULL);
			appointmentRepository.save(appointment);
		}
		
		return patientAppointment2;
	}
	
	
	@PutMapping(value = "/patient-appointment/{id}")
	public PatientAppointment updatePatientAppointment(@PathVariable(value = "id")Long id , 
													   @RequestBody PatientAppointment patientAppointment) {
		patientAppointment.setId(id);
		return patientAppointmentRepository.save(patientAppointment);
	}
	
	@GetMapping(value = "/patient-appointment")
	public List<PatientAppointment> patientAppointments(@RequestParam(name = "date")String date,
														@RequestParam(name = "id")Long id){
		return patientAppointmentRepository.findByTimeAppointment_Appointment_DateAndTimeAppointment_Appointment_Specialiste_Id(UtilService.formateDate(date), id);
	}
	
	@DeleteMapping("/removePatientAppointment/{id}")
	public void deletePatientAppointment(@PathVariable(value = "id")Long id) { 							 
		PatientAppointment patientAppointment = patientAppointmentRepository.findById(id).get();
		patientAppointmentRepository.delete(patientAppointment);
		
	}
	
}

@Data
class PatientAppointmentModel{ 
	private String nom;
	private String prenom;
	private String telephone;
	private String adresse;
	private int age;
	private String genre;
	private Long timeAppId;

}

