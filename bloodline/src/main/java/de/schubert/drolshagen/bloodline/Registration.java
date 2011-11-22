package de.schubert.drolshagen.bloodline;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class Registration {
	
	@Inject
	DataManager dataManager;
	
	@Inject
	Logger logger;

	private String username;
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
			FacesMessage message = new FacesMessage("A user with the same username already exists.");
			FacesContext.getCurrentInstance().addMessage(null, message);			
		}
		else {
			FacesMessage message = new FacesMessage("Password confirmation is incorrect.");
			FacesContext.getCurrentInstance().addMessage(null, message);	
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
