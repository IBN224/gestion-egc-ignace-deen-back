package org.sid.repositories;

import java.util.List;

import org.sid.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	public List<Patient> findByIsDeletedFalse();
	
	@Query("select p from Patient p where p.isDeleted=false and "
		 + "p.id not in(select s.patient.id from User s where s.patient.id!=null)")
	public List<Patient> getAllPatientNoCompte();
	
	public Boolean existsByTelephone(String telephone);
	
}
