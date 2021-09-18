package org.sid.repositories;

import java.util.List;

import org.sid.entities.Specialiste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpecialisteRepository extends JpaRepository<Specialiste, Long> {

	public List<Specialiste> findByIsDeletedFalse();
	
	public List<Specialiste> findByIsDeletedFalseAndDepartement_Id(Long id);
	
	public List<Specialiste> findByIsDeletedFalseAndIsDoctorTrue();
	
	 @Query("select p from Specialiste p where p.isDeleted=false and "
	 	  + "p.id not in(select s.personnel.id from User s where s.personnel.id!=null)")
	 public List<Specialiste> getAllPersonnelNoCompte();
	 
	 //List<Specialiste> findByIdNotIn(List<Long> listPersonnelDansUser);
	
}
