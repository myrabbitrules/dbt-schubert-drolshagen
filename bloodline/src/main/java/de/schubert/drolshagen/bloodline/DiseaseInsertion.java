package de.schubert.drolshagen.bloodline;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DiseaseInsertion {
	
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
	
	public void insert() {
		Disease disease = new Disease(name, dominant);
		dataManager.addDisease(disease);
	}
}
