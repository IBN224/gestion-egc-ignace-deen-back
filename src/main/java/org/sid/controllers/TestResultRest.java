package org.sid.controllers;

import java.util.List;

import org.sid.entities.TestResult;
import org.sid.repositories.TestResultRepository;
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
public class TestResultRest {

	@Autowired
	private TestResultRepository testResultRepository;
	
	
	@PostMapping(value = "/testResult")
	public TestResult addTestResult(@RequestBody TestResult testResult) {
		return testResultRepository.save(testResult);
	}
	
	@PutMapping(value = "/testResult/{id}")
	public TestResult updateTestResult(@PathVariable(value = "id")Long id , @RequestBody TestResult testResult) {
		testResult.setId(id);
		return testResultRepository.save(testResult);
	}
	
	@GetMapping(value = "/testResultBy")
	public List<TestResult> testResultsBy(@RequestParam(name = "specialisteId")Long specialisteId,
										  @RequestParam(name = "personnelId")Long personnelId,
							              @RequestParam(name = "patientId")Long patientId,
							              @RequestParam(name = "categorie")String categorie){
		
        if (categorie.equals("1")){
        	return testResultRepository.getTestResultBy(specialisteId, personnelId, 0L, true);   
        }else {
        	return testResultRepository.getTestResultBy(specialisteId, 0L, patientId, false);
        }
		
	}
	
	@GetMapping(value = "/testResultByPatient")
	public List<TestResult> testResultsByPatient(@RequestParam(name = "id")Long id){
		
        return testResultRepository.findByIsDeletedFalseAndPatient_Idpatient(id);
		
	}
	
	@GetMapping(value = "/testResultByPersonnel")
	public List<TestResult> testResultsByPersonnel(@RequestParam(name = "id")Long id){
		
        return testResultRepository.findByIsDeletedFalseAndPersonnel_Id(id);
		
	}
	
	@GetMapping(value = "/testResult")
	public List<TestResult> testResults(){
		return testResultRepository.findByIsDeletedFalse();
	}
	
	@PutMapping("/removeTestResult/{id}")
	public void deleteTestResult(@PathVariable(value = "id")Long id) { 
															 
		TestResult testResult = testResultRepository.findById(id).get();
		testResult.setIsDeleted(true);
		testResultRepository.save(testResult);
		
	}
	
}
