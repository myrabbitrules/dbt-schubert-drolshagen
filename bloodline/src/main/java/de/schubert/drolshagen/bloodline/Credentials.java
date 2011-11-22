package de.schubert.drolshagen.bloodline;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.*;

@RequestScoped
@Named
public class Credentials {
	
	@NotNull(message = "Please enter username.")
	private String username;
		
	@NotNull(message = "Please enter password.")
	private String password;
	
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
	
}
