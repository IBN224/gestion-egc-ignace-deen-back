package org.sid.repositories;

import java.time.LocalDate;
import java.util.List;

import org.sid.entities.TimeAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeAppointmentRepository extends JpaRepository<TimeAppointment, Long> {

	public List<TimeAppointment> findByAppointment_Date(LocalDate date);
	
	public List<TimeAppointment> findByAppointment_DateAndAppointment_Specialiste_Id(LocalDate date, Long id);
	
	
}
