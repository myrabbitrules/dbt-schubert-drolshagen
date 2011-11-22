package de.schubert.drolshagen.bloodline;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.webapp.FacesServlet;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RequestScoped
@Named
public class Registration {
	
	@Inject
	DataManager dataManager;
	
	@Inject
	Logger logger;

	@NotNull(message = "Please enter username.")
	@Size(max = 20, message = "max size is 20")
	@Pattern(regexp = "[a-zA-Z]+.*", message = "must start with word character")
	private String username;
	
	@NotNull(message = "Please enter password.")
	@Size(min = 5, max = 20, message = "size must be between 5 and 20")
	private String password;
	
	private String passwordConfirm;
	
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

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	
	
}
