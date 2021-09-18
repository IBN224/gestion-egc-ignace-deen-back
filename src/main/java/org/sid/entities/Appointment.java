package org.sid.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.sid.utils.StateAppointment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate date;
	private StateAppointment stateAppointment;
	private int max;
	@ManyToOne 
	private Specialiste specialiste;
	@OneToMany(mappedBy = "appointment")
	@JsonIgnoreProperties("appointment")
	private List<TimeAppointment> timeAppointments;
	private Boolean isDeleted;
}
