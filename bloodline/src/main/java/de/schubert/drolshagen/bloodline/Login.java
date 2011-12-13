package de.schubert.drolshagen.bloodline;


import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


@SessionScoped
@Named
public class Login implements Serializable {

	private static final long serialVersionUID = -5294160758110334070L;
	
	/**
	 * key for logged in user data in session map
	 */
	public static final String LOGGED_IN_USER_KEY = "de.schubert.drolshagen.bloodline.loggedin";
	
	@Inject	
	private DataManager dataManager;
	
	@Inject
	private Credentials credentials;

	/**
	 * The logged in user of the session this object belongs to or null if user
	 * has not logged in.
	 */
	private User loggedInUser;

	/**
	 * Attempt to log in using request parameters.
	 * 
	 * @return the view id for which to render the http response or null if
	 * user stays on same view
	 */
	public String login() {
		if (isLoggedIn()) {
			return null;
		}
				
		// fetch user from database
		User user = dataManager.getUser(credentials.getUsername());
		if (user != null) {
			// user exists. now check password
			if (dataManager.passwordIsCorrect(credentials.getUsername(), credentials.getPassword())) {
				loggedInUser = user;
				FacesContext context = FacesContext.getCurrentInstance();	
				// save user in session data
				context.getExternalContext().getSessionMap().put(LOGGED_IN_USER_KEY, user);
				FacesMessage message = new FacesMessage("Hello " + user.getUserName());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return "home.xhtml";
			}
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Incorrect password.");
			FacesContext.getCurrentInstance().addMessage("login_form:password", message);
		}
		else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "User does not exist.");
			FacesContext.getCurrentInstance().addMessage("login_form:username", message);
		}		
		return null;
	}
	
	/**
	 * Log out user if user is logged in.
	 */
	public String logout() {	
		if (loggedInUser != null) {
			FacesMessage message = new FacesMessage("Goodbye " + loggedInUser.getUserName());
			FacesContext.getCurrentInstance().addMessage(null, message);
			loggedInUser = null;
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(LOGGED_IN_USER_KEY);
			return "login.xhtml";
		}		
		return null;
	}
	
	/**
	 * Returns the logged in user or null if user is not logged in.
	 * 
	 * @return the logged in user or null if user is not logged in
	 */
	public User getUser() {
		return loggedInUser;
	}
	
	/**
	 * Checks whether user of the session this object is bound to is logged in.
	 * 
	 * @return true if the user of the session this object is bound to is logged in, otherwise false
	 */
	public boolean isLoggedIn() {
		return loggedInUser != null;
	}
}
