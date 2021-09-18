package org.sid.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
public class TimeAppointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String time;
	private int max;
	@ManyToOne
	@JsonIgnoreProperties("timeAppointments")
	private Appointment appointment;
	@OneToMany(mappedBy = "timeAppointment")
	@JsonIgnoreProperties("timeAppointment")
	private List<PatientAppointment> patientAppointments;
	
}
