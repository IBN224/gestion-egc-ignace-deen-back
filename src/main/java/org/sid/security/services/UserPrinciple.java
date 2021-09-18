package org.sid.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.sid.entities.Patient;
import org.sid.entities.Specialiste;
import org.sid.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
 
public class UserPrinciple implements UserDetails {
  private static final long serialVersionUID = 1L;
 
    private Long id;
 
    private String username;
 
    @JsonIgnore
    private String password;
 
    private Collection<? extends GrantedAuthority> authorities;
    
    private Patient patient;
    
    private Specialiste personnel;
    
    private Specialiste entite;
    
    private Boolean isActive;
 
    public UserPrinciple(Long id, String username, String password,Collection<? extends GrantedAuthority> authorities,
    		             Patient patient, Specialiste personnel, Specialiste entite, Boolean isActive) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.patient = patient;
		this.personnel = personnel;
		this.entite = entite;
		this.isActive = isActive;
    }
 
    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());
 
        return new UserPrinciple(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                authorities,
                user.getPatient(),
                user.getPersonnel(),
                user.getEntite(),
                user.getIsActive()
        );
    }
 
    public Long getId() {
        return id;
    }
 
    @Override
    public String getUsername() {
        return username;
    }
 
    @Override
    public String getPassword() {
        return password;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
 
    public Patient getPatient() {
		return patient;
	}

	public Specialiste getPersonnel() {
		return personnel;
	}

	public Specialiste getEntite() {
		return entite;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	@Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }
}
