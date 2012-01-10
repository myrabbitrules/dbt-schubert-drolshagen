package de.schubert.drolshagen.bloodline;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.*;

@RequestScoped
@Named
public class Credentials {
	
	/**
	 * @uml.property  name="username"
	 */
	@NotNull(message = "Please enter username.")
	private String username;
		
	/**
	 * @uml.property  name="password"
	 */
	@NotNull(message = "Please enter password.")
	private String password;
	
	/**
	 * @return
	 * @uml.property  name="username"
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username
	 * @uml.property  name="username"
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return
	 * @uml.property  name="password"
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password
	 * @uml.property  name="password"
	 */
	public void setPassword(String password) {
		this.password = password;
	}	
	
}
