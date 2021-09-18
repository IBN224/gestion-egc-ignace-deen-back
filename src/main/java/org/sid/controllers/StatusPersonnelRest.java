package org.sid.controllers;

import java.util.List;

import org.sid.entities.StatusPersonnel;
import org.sid.repositories.StatusPersonnelRepository;
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
public class StatusPersonnelRest {
	
	@Autowired
	private StatusPersonnelRepository statusPersonnelRepository;
	
	@PostMapping(value = "/statusPersonnel")
	public StatusPersonnel addStatusPersonnel(@RequestBody StatusPersonnel statusPersonnel) {
		return statusPersonnelRepository.save(statusPersonnel);
	}
	
	@PutMapping(value = "/statusPersonnel/{id}")
	public StatusPersonnel updateStatusPersonnel(@PathVariable(value = "id")Long id , 
										       @RequestBody StatusPersonnel statusPersonnel) {
		statusPersonnel.setId(id);
		return statusPersonnelRepository.save(statusPersonnel);
	}
	
	@GetMapping(value = "/statusPersonnel/{id}")
	public StatusPersonnel statusPersonnel(@PathVariable(value = "id")Long id){
		return statusPersonnelRepository.findById(id).get();
	}
	
	@GetMapping(value = "/statusPersonnel")
	public List<StatusPersonnel> statusPersonnels(){
		return statusPersonnelRepository.findAll();
	}

}
