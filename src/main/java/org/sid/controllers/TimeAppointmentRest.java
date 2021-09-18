package org.sid.controllers;

import java.util.List;

import org.sid.entities.TimeAppointment;
import org.sid.repositories.TimeAppointmentRepository;
import org.sid.services.UtilService;
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


@RestController
@CrossOrigin
public class TimeAppointmentRest {

	@Autowired
	private TimeAppointmentRepository timeAppointmentRepository;
	
	
	@PostMapping(value = "/time-appointment")
	public TimeAppointment addTimeAppointment(@RequestBody TimeAppointment timeAppointment) {
		return timeAppointmentRepository.save(timeAppointment);
	}
	
	@PutMapping(value = "/time-appointment/{id}")
	public TimeAppointment updateTimeaAppointment(@PathVariable(value = "id")Long id , 
												  @RequestBody TimeAppointment timeAppointment) {
		timeAppointment.setId(id);
		return timeAppointmentRepository.save(timeAppointment);
	}
	
	@GetMapping(value = "/time-appointment")
	public List<TimeAppointment> timeAppointments(){
		return timeAppointmentRepository.findAll();
	}
	
	@GetMapping(value = "/time-appointmentBy")
	public List<TimeAppointment> timeAppointmentBy(@RequestParam(name = "date")String date){
		return timeAppointmentRepository.findByAppointment_Date(UtilService.formateDate(date));
	}

	@GetMapping(value = "/time-appointmentByspecialiste")
	public List<TimeAppointment> timeAppointmentBySpecialiste(@RequestParam(name = "date")String date,
															  @RequestParam(name = "id")Long id){
		return timeAppointmentRepository.findByAppointment_DateAndAppointment_Specialiste_Id(UtilService.formateDate(date), id);
	}
	
	@DeleteMapping("/time-appointment/{id}")
	public void deleteTimeappointment(@PathVariable(value = "id")Long id) { 							 
		TimeAppointment timeAppointment = timeAppointmentRepository.findById(id).get();
		timeAppointmentRepository.delete(timeAppointment);
		
	}
}
