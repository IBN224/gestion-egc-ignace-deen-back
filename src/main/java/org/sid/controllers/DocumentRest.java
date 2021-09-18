package org.sid.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.sid.entities.Document;
import org.sid.entities.TestResult;
import org.sid.repositories.DocumentRepository;
import org.sid.repositories.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import net.bytebuddy.utility.RandomString;


@RestController
@CrossOrigin("*")
public class DocumentRest {

	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private TestResultRepository testResultRepository;
	
	
	//@Value("${rootLocation}")
	//private String path;
	
	
	
	@PostMapping("/documentTestResult/{id}")
	public ResponseEntity<String> handleFileUploadTestResult(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {
		  
		  Path rootLocation = Paths.get("C:/Users/IBN224/Desktop/Documents"); //C:/Users/IBN224/Desktop/Documents
		   
		  String message;
	      String name = file.getOriginalFilename();
			name = name.substring(name.lastIndexOf('/')+1)
			.substring(name.lastIndexOf('\\')+1);
			
		  String nameSansEx = name.substring(0, name.indexOf(".")); //name without extension file
		  String randomName = nameSansEx+"-"+RandomString.make(20)+name.substring(name.lastIndexOf('.'));
	      TestResult testResult = testResultRepository.findById(id).get();
	      
	      
	      try {															
	         try {
	            Files.copy(file.getInputStream(), rootLocation.resolve(randomName));
	         } catch (Exception e) {
	            throw new RuntimeException("FAIL!");
	         } 
	         
	         
	         documentRepository.save(new Document(null, nameSansEx, randomName, testResult)); 
	         
	         message = "Successfully uploaded!";
	         return ResponseEntity.status(HttpStatus.OK).body(message);
	      } catch (Exception e) {
	         message = "Failed to upload!";
	         return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	      }
	      
	   }
	
	
	
}
