package org.sid.controllers;

import java.util.List;

import org.sid.entities.Departement;
import org.sid.repositories.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class DepartementRest {

	@Autowired
	private DepartementRepository departementRepository;
	
	
	@PostMapping(value = "/departement")
	public Departement addDepartement(@RequestBody Departement departement) {
		return departementRepository.save(departement);
	}
	
	@PutMapping(value = "/departement/{id}")
	public Departement updateDepartement(@PathVariable(value = "id")Long id , @RequestBody Departement departement) {
		departement.setId(id);
		return departementRepository.save(departement);
	}
	
	@GetMapping(value = "/departement/{id}")
	public Departement departement(@PathVariable(value = "id")Long id){
		return departementRepository.findById(id).get();
	}
	
	@GetMapping(value = "/departement")
	public List<Departement> departements(){
		return departementRepository.findByIsDeletedFalse();
	}
	
	@GetMapping(value = "/departementSchedule")
	public List<Departement> departementsForSchedule(){
		return departementRepository.findByIsDeletedFalse();
	}
	
	@PutMapping("/removeDepartement/{id}")
	public void deleteDepartement(@PathVariable(value = "id")Long id) { 
															 
		Departement departement = departementRepository.findById(id).get();
		departement.setIsDeleted(true);
		departementRepository.save(departement);
		
	}
}
