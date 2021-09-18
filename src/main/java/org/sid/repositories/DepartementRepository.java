package org.sid.repositories;

import java.util.List;

import org.sid.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Long>{

	public List<Departement> findByIsDeletedFalse();
	
}
