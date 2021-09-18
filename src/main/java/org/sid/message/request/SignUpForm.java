package org.sid.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.sid.entities.Patient;
import org.sid.entities.Specialiste;

public class SignUpForm {
 
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;
    
    private String role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
 
    private Patient patient;
    private Specialiste personnel;
    private Specialiste entite;
    private Boolean isActive;
    
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
      return this.role;
    }
    
    public void setRole(String role) {
      this.role = role;
    }

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Specialiste getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Specialiste personnel) {
		this.personnel = personnel;
	}

	public Specialiste getEntite() {
		return entite;
	}

	public void setEntite(Specialiste entite) {
		this.entite = entite;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
    
}
