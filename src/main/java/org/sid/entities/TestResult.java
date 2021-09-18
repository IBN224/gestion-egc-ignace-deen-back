package org.sid.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean categorie;
	private String description;
	private LocalDate date;
	@ManyToOne
	private Specialiste specialiste;
	@ManyToOne
	private Specialiste personnel;
	@ManyToOne
	@JsonIgnoreProperties("testResults")
	private Patient patient;
	@OneToMany(mappedBy = "testResult")
	@JsonIgnoreProperties("testResult")
	private List<Document> documents;
	private Boolean isDeleted;
	
}
