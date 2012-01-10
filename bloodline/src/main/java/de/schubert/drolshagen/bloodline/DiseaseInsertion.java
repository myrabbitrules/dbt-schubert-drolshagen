package de.schubert.drolshagen.bloodline;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Richard Schubert, Rene Drolshagen
 * 
 * Backing bean for one request with which the user can add a new disease to the database.
 */
@Named
@RequestScoped
public class DiseaseInsertion {
	
	/**
	 * Provides access to the database.
	 */
	@Inject
	DataManager dataManager;
	
	private String name;
	private boolean dominant;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDominant() {
		return dominant;
	}
	public void setDominant(boolean dominant) {
		this.dominant = dominant;
	}
	
	/**
	 * Inserts the disease specified by the set class attributes to the database if a disease
	 * with the same name does not already exist.
	 */
	public void insert() {
		Disease disease = new Disease(name, dominant);
		dataManager.addDisease(disease);
	}
}
