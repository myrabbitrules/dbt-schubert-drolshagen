package de.schubert.drolshagen.bloodline;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DiseaseInsertion {
	
	/**
	 * @uml.property  name="dataManager"
	 * @uml.associationEnd  readOnly="true"
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
	
	public void insert() {
		Disease disease = new Disease(name, dominant);
		dataManager.addDisease(disease);
	}
}
