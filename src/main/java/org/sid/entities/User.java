package org.sid.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
 
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        })
})
public class User{
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank
    @Size(min=3, max = 50)
    private String username;
 
    @NotBlank
    @Size(min=6, max = 100)
    private String password;
 
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", 
      joinColumns = @JoinColumn(name = "user_id"), 
      inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    
    @OneToOne
    private Patient patient;
    @OneToOne
    private Specialiste personnel;
    @ManyToOne
    private Specialiste entite;
    private Boolean isActive;
 
    public User() {}
 
    public User(String username, String password, Patient patient, Specialiste personnel, 
    		    Specialiste entite, Boolean isActive) {
		this.username = username;
		this.password = password;
		this.patient = patient;
		this.personnel = personnel;
		this.entite = entite;
		this.isActive = isActive;
	}

	public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
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
 
    public Set<Role> getRoles() {
        return roles;
    }
 
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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