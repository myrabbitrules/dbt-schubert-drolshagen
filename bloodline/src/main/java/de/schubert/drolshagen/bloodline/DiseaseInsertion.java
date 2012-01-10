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
	
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * @uml.property  name="dominant"
	 */
	private boolean dominant;
	
	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return
	 * @uml.property  name="dominant"
	 */
	public boolean isDominant() {
		return dominant;
	}
	/**
	 * @param dominant
	 * @uml.property  name="dominant"
	 */
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
