package org.sid.repositories;

import java.util.List;

import org.sid.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	public List<Appointment> findByIsDeletedFalseOrderByDateDesc();
	
	public List<Appointment> findByIsDeletedFalseAndSpecialiste_IdOrderByDateDesc(Long id);
	
	
	/*
	 * @Query("select a from Appointment a left join a.timeAppointments t where t.specialiste.id =:specialisteId "
	 * + "order by a.date desc") public List<Appointment>
	 * getAppointmentBySpecialiste(@Param("specialisteId") Long id);
	 */
	 
	
	
}
