package org.sid.repositories;

import java.util.List;
import java.util.Optional;

import org.sid.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    List<User> findByEntite_Id(Long id);

    @Query("select u from User u where (:personnel is 0L or u.personnel.id =:personnel) and "
   	     + "(:patient is 0L or u.patient.id =:patient)")
   	public List<User> getUserBy(@Param("personnel")Long personnelId,
                                @Param("patient")Long patientId);
}