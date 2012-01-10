package de.schubert.drolshagen.bloodline;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Handles the complete Registration of Users
 * 
 * @author Rene Drolshagen and Richard Schubert
 */
@RequestScoped
@Named
public class Registration {
	@Inject
	DataManager dataManager;
	
	@Inject
	Logger logger;

	/**
	 * The Username which will represent the Users Login Name
	 */
	@NotNull(message = "Please enter username.")
	@Size(max = 20, message = "max size is 20")
	@Pattern(regexp = "[a-zA-Z]+.*", message = "must start with word character")
	private String username;
	
	/**
	 * The password of a specific User
	 */
	@NotNull(message = "Please enter password.")
	@Size(min = 5, max = 20, message = "size must be between 5 and 20")
	private String password;
	
	/**
	 * A Confirmation field to confirm, that the Information was given right
	 */
	private String passwordConfirm;
	
	/**
	 * This method adds the User into the Database and checks all Information
	 * @return The next Page where Information will be found
	 */
	public String register() {
		if (password.equals(passwordConfirm)) {
			User newUser = new User(username, password);
			
			if (dataManager.addUser(newUser)) {
				FacesMessage message = new FacesMessage("User has been registered successfully.");
				FacesContext.getCurrentInstance().addMessage(null, message);
				logger.info("New user has been registered.");
				return "login.xhtml";
				
			}			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "A user with the same username already exists.");
			FacesContext.getCurrentInstance().addMessage("registration_form:username", message);			
		}
		else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Password confirmation is incorrect.");
			FacesContext.getCurrentInstance().addMessage("registration_form:password_confirm", message);	
		}
		return null;
	}

	/**
	 * Returns the Username which is currently stored in this class
	 * @return The stored UserName
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Overrides the currently Username with a new one
	 * @param username The new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the current Password of a User
	 * @return The current Password 
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the Password of the User
	 * @param password The new Password for a User
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the confirmation Field of this Class
	 * @return The Password Confirmation which is stored in this class
	 */
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	/**
	 * Sets the Password Confirmation 
	 * @param passwordConfirm The Password Confirmation which will be set
	 */
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	
	
}
