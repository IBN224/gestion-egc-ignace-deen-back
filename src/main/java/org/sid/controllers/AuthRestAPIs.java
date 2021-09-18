package org.sid.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.sid.entities.Role;
import org.sid.entities.RoleName;
import org.sid.entities.User;
import org.sid.message.request.LoginForm;
import org.sid.message.request.SignUpForm;
import org.sid.message.response.JwtResponse;
import org.sid.repositories.RoleRepository;
import org.sid.repositories.UserRepository;
import org.sid.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {
 
    @Autowired
    AuthenticationManager authenticationManager;
 
    @Autowired
    UserRepository userRepository;
 
    @Autowired
    RoleRepository roleRepository;
 
    @Autowired
    PasswordEncoder encoder;
 
    @Autowired
    JwtProvider jwtProvider;
 
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
 
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
 
        SecurityContextHolder.getContext().setAuthentication(authentication);
 
        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }
 
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("-> login existe déja!",
                    HttpStatus.BAD_REQUEST);
        }
 
        // Creating user's account
        User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()), 
        		             signUpRequest.getPatient(), signUpRequest.getPersonnel(),
        		             signUpRequest.getEntite(), signUpRequest.getIsActive());
 
        
        Set<String> strRoles = new HashSet<>();
        strRoles.add(signUpRequest.getRole());
        Set<Role> roles = new HashSet<>();
 
        strRoles.forEach(role -> {
          switch(role) {
          case "admin":
            Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                  .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
            roles.add(adminRole);
            
            break;
          case "pm":
                Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                  .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(pmRole);
                
            break;
          case "user":
              Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                  .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
              roles.add(userRole);
            break;
          case "patient":
              Role patientRole = roleRepository.findByName(RoleName.ROLE_PATIENT)
                  .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
              roles.add(patientRole);
              break;
          case "super":
              Role superRole = roleRepository.findByName(RoleName.ROLE_SUPER_ADMIN)
                  .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
              roles.add(superRole);
          }
        });
        
        user.setRoles(roles);
        userRepository.save(user);
 
        return ResponseEntity.ok().body("User registered successfully!");
    }
    
    @PutMapping(value = "/status/{id}")
	public User updateStatus(@PathVariable(value = "id")Long id , @RequestBody UserStatusForm form) {
    	User user = userRepository.findById(id).get();
    	user.setIsActive(form.getIsActive());
		return userRepository.save(user);
	}
    
    @GetMapping(value = "/user")
	public User user(@RequestParam(name = "username")String username){
		return userRepository.findByUsername(username).get();
	}
    
    @GetMapping(value = "/userByEntite")
	public List<User> userByEntite(@RequestParam(name = "id")Long id){
		return userRepository.findByEntite_Id(id);
	}
    
    @GetMapping(value = "/users")
	public List<User> users(){
		return userRepository.findAll();
	}
    
    @GetMapping(value = "/userAllBy")
	public List<User> userBy(@RequestParam(name = "personnelId")Long personnelId,
			                 @RequestParam(name = "patientId")Long patientId,
			                 @RequestParam(name = "categorie")String categorie){
		
        if (categorie.equals("1")){
        	return userRepository.getUserBy(personnelId, 0L);   
        }else {
        	return userRepository.getUserBy(0L, patientId);
        }
		
	}
    
    @GetMapping(value = "/role")
	public List<Role> roles(){
		return roleRepository.findAll();
	}
    
    
}

@Data
class UserStatusForm{
	private Long id;
	private Boolean isActive;
}
