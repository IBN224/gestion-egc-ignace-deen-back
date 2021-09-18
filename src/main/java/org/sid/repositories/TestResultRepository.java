package org.sid.repositories;

import java.util.List;

import org.sid.entities.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TestResultRepository extends JpaRepository<TestResult, Long>{
	
	public List<TestResult> findByIsDeletedFalse();
	
	
	@Query("select r from TestResult r where r.isDeleted = false and r.specialiste.id =:specialiste and r.categorie =:categorie and "
	     + "(:personnel is 0L or r.personnel.id =:personnel) and (:patient is 0L or r.patient.id =:patient)")
	public List<TestResult> getTestResultBy(@Param("specialiste")Long id,
		                                    @Param("personnel")Long personnelId,
		                                    @Param("patient")Long patientId,
		                                    @Param("categorie") Boolean categorie);
	
	public List<TestResult> findByIsDeletedFalseAndPatient_Idpatient(Long id);
	
	public List<TestResult> findByIsDeletedFalseAndPersonnel_Id(Long id);
	
}
