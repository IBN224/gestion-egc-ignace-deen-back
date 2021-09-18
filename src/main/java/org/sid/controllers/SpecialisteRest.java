package org.sid.controllers;

import java.util.List;

import org.sid.entities.Specialiste;
import org.sid.repositories.SpecialisteRepository;
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
public class SpecialisteRest {

	@Autowired
	private SpecialisteRepository specialisteRepository;
	
	
	@PostMapping(value = "/specialiste")
	public Specialiste addSpecialiste(@RequestBody Specialiste specialiste) {
		return specialisteRepository.save(specialiste);
	}
	
	@PutMapping(value = "/specialiste/{id}")
	public Specialiste updateSpecialiste(@PathVariable(value = "id")Long id , @RequestBody Specialiste specialiste) {
		specialiste.setId(id);
		return specialisteRepository.save(specialiste);
	}
	
	@GetMapping(value = "/specialiste")
	public List<Specialiste> specialistes(){
		return specialisteRepository.findByIsDeletedFalse();
	}
	
	@GetMapping(value = "/specialiste/{id}")
	public Specialiste specialiste(@PathVariable(value = "id")Long id){
		return specialisteRepository.findById(id).get();
	}
	
	@GetMapping(value = "/specialisteByDepartement")
	public List<Specialiste> specialistesBy(@RequestParam(name = "id")Long id){
		return specialisteRepository.findByIsDeletedFalseAndDepartement_Id(id);
	}
	
	@GetMapping(value = "/entite")
	public List<Specialiste> entite(){
		return specialisteRepository.findByIsDeletedFalseAndIsDoctorTrue();
	}
	
	@GetMapping(value = "/personnelNoCompte")
	public List<Specialiste> getAllPersonnelNoCompte(){
		return specialisteRepository.getAllPersonnelNoCompte();
	}
	
	@PutMapping("/removeSpecialiste/{id}")
	public void deleteSpecialiste(@PathVariable(value = "id")Long id) { 
															 
		Specialiste specialiste = specialisteRepository.findById(id).get();
		specialiste.setIsDeleted(true);
		specialisteRepository.save(specialiste);
		
	}
}
