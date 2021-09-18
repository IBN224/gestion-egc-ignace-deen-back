package org.sid.repositories;

import java.time.LocalDate;
import java.util.List;

import org.sid.entities.PatientAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientAppointmentRepository extends JpaRepository<PatientAppointment, Long>{
	
	public List<PatientAppointment> findByTimeAppointment_Appointment_DateAndTimeAppointment_Appointment_Specialiste_Id(LocalDate date, Long id);
	
	@Query("select count(p.id) from PatientAppointment p left join p.timeAppointment.appointment a where a.id =:idApp ")
	public int getSizeAppointmt(@Param("idApp")Long id);
	
}
