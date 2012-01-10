package de.schubert.drolshagen.bloodline;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * Used for adding code before or after executing jsf lifecycle phases
 * 
 * @author Richard Schubert
 */
public class AuthenticationPhaseListener implements PhaseListener {

	private static final long serialVersionUID = -5930233807488871517L;
	
	public void afterPhase(PhaseEvent event) {
		// In case of forbidden request lifecycle is restore view -> render response.
		// We take earlier restore view, but check in afterPhase cause we need
		// component tree to be created for extracting view id
		if (event.getPhaseId().equals(PhaseId.RESTORE_VIEW)) {		
			verifyView(event);
		}	
	}

	public void beforePhase(PhaseEvent event) {		
			
	}
	
	/**
	 * Check whether user is authorized to access the view of current http request.
	 * If user is not authorized he will be redirected.
	 * 
	 * @param event contains the faces context for current request
	 */
	public void verifyView(PhaseEvent event) {
		// contains component tree
		FacesContext context = event.getFacesContext();
		
		// get identifier for root component in component tree
		String viewId = context.getViewRoot().getViewId();
		
		String fowardTo = null;
		
		if (isLoggedIn()) {
			if (viewId.equals("/login.xhtml")) {
				fowardTo = "/home.xhtml";
			}
		}
		else {
			if (isSecureView(viewId)) {				
				// user is not logged in
				fowardTo = "/login.xhtml";				
			}
		}		
		
		if (fowardTo != null) {
			// rendered response will contain foward to page
			context.getApplication().getNavigationHandler().handleNavigation(context, null, fowardTo);
		}		
		
	}
	
	public boolean isLoggedIn() {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Login.LOGGED_IN_USER_KEY) != null;			
	}
	
	/**
	 * Check whether a view may only be accessed by logged in users.
	 * 
	 * @param viewId last part in file path of view to be checked including leading slash
	 * (e.g. "/home.xhtml"
	 * 
	 * @return true if the view identified by the given view id may only be accessed by logged in users,
	 * otherwise false
	 */
	public boolean isSecureView(String viewId) {
		if (viewId == null) {
			return false;
		}
		return ! (viewId.equals("/login.xhtml") || viewId.equals("/registration.xhtml"));
	}

	/**
	 * Returns the ids of all request life cycle phases to be intercepted.
	 * 
	 * @return the ids of all request life cycle phases to be intercepted
	 */
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;// PhaseId.RESTORE_VIEW;
	}

}
