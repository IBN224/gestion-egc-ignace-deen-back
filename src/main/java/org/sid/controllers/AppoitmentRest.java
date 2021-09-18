package org.sid.controllers;

import java.util.List;

import org.sid.entities.Appointment;
import org.sid.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AppoitmentRest {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	
	@PostMapping(value = "/appointment")
	public Appointment addAppointment(@RequestBody Appointment appointment) {
		return appointmentRepository.save(appointment);
	}
	
	@PutMapping(value = "/appointment/{id}")
	public Appointment updateAppointment(@PathVariable(value = "id")Long id , @RequestBody Appointment appointment) {
		appointment.setId(id);
		return appointmentRepository.save(appointment);
	}
	
	@GetMapping(value = "/appointmentBy")
	public List<Appointment> appointmentsBy(@RequestParam(name = "id")Long id){
		return appointmentRepository.findByIsDeletedFalseAndSpecialiste_IdOrderByDateDesc(id);
	}
	
	@GetMapping(value = "/appointment")
	public List<Appointment> appointments(){
		return appointmentRepository.findByIsDeletedFalseOrderByDateDesc();
	}
	
	@GetMapping(value = "/appointment/{id}")
	public Appointment appointment(@PathVariable(name = "id")Long id){
		return appointmentRepository.findById(id).get();
	}
	
	@PutMapping("/removeAppointment/{id}")
	public void deleteAppointment(@PathVariable(value = "id")Long id) { 
															 
		Appointment appointment = appointmentRepository.findById(id).get();
		appointment.setIsDeleted(true);
		appointmentRepository.save(appointment);
		
	}
	
}
