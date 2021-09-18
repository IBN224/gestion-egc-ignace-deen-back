package org.sid.controllers;

import java.util.List;

import org.sid.entities.Patient;
import org.sid.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class PatientRest {

	@Autowired
	private PatientRepository patientRepository;
	
	@GetMapping(value = "/checkPatientTelephone")
	public ResponseEntity<String> checkPatientPhone(@RequestParam(name = "tele")String tele) {
		if(patientRepository.existsByTelephone(tele)) {
            return new ResponseEntity<String>("-> Contact existe déja!",
                    HttpStatus.BAD_REQUEST);
        }
		
		return ResponseEntity.ok().body("Contact good!"); 
	}
	
	@PostMapping(value = "/patient")
	public ResponseEntity<String> addPatient(@RequestBody Patient patient) {
		if(patientRepository.existsByTelephone(patient.getTelephone())) {
            return new ResponseEntity<String>("-> Contact existe déja!",
                    HttpStatus.BAD_REQUEST);
        }
		
		patientRepository.save(patient);
		
		return ResponseEntity.ok().body("Patient registered successfully!"); 
	}
	
	@PutMapping(value = "/patient/{id}")
	public ResponseEntity<String> updatePatient(@PathVariable(value = "id")Long id , @RequestBody Patient patient) {
		
		if(!patient.getTelephone().equals(patientRepository.findById(id).get().getTelephone())) {
			if(patientRepository.existsByTelephone(patient.getTelephone())) {
	            return new ResponseEntity<String>("-> Contact existe déja!",
	                    HttpStatus.BAD_REQUEST);
	        }
		}
		
		patient.setIdpatient(id);
		patientRepository.save(patient);
		return ResponseEntity.ok().body("Patient updated successfully!");
	}
	
	@GetMapping(value = "/patient")
	public List<Patient> patients(){
		return patientRepository.findByIsDeletedFalse();
	}
	
	@GetMapping(value = "/patientNoCompte")
	public List<Patient> getAllPatientNoCompte(){
		return patientRepository.getAllPatientNoCompte();
	}
	
	@PutMapping("/removePatient/{id}")
	public void deletePatient(@PathVariable(value = "id")Long id) { 
															 
		Patient patient = patientRepository.findById(id).get();
		patient.setIsDeleted(true);
		patientRepository.save(patient);
		
	}
}
