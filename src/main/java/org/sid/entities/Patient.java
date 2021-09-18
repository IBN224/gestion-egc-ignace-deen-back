package org.sid.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idpatient;
	private String nom;
	private String prenom;
	private String telephone;
	private String adresse;
	private int age;
	private String genre;
	@OneToMany(mappedBy = "patient")
    @JsonIgnoreProperties("patient")
	private List<PatientAppointment> patientAppointments;
	@OneToMany(mappedBy = "patient")
    @JsonIgnoreProperties("patient")
	private List<TestResult> testResults;
	private Boolean isDeleted;
}
