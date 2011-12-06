package de.schubert.drolshagen.bloodline;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class GeneInfoId implements Serializable {

	private static final long serialVersionUID = 6781473968730496803L;

	@OneToOne
	@JoinColumn(name = "person", nullable = false)
	private Person person;
	
	@OneToOne
	@JoinColumn(name = "disease", nullable = false)
	private Disease disease;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease desease) {
		this.disease = desease;
	}
	
	
}
