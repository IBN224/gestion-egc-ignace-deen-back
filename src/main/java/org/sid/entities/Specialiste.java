package org.sid.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
public class Specialiste {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	@ManyToOne
	@JsonIgnoreProperties("specialistes")
	private StatusPersonnel statusPersonnel;
	private Boolean isDoctor;
	@ManyToOne
	@JsonIgnoreProperties("specialistes")
	private Departement departement;
	private Boolean isDeleted;
}
