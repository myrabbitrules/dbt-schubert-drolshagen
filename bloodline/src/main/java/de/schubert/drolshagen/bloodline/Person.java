package de.schubert.drolshagen.bloodline;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name = "person")
@Entity
public class Person {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "sirname", nullable = false)
	private String sirname;
	
	@Column(name = "forename", nullable = false)
	private String forename;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date", nullable = false)
	private Date birthDate;
	
	@Column(name = "is_male", nullable = false)
	private boolean isMale;
		
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "father")
	private Person father;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mother")
	private Person mother;
	
	
	public Person(String sirname, String forename, Date birthDate,
			boolean isMale, Person father, Person mother) {
		super();
		this.sirname = sirname;
		this.forename = forename;
		this.birthDate = birthDate;
		this.isMale = isMale;
		this.father = father;
		this.mother = mother;
	}

	public Person() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSirname() {
		return sirname;
	}

	public void setSirname(String sirname) {
		this.sirname = sirname;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public Person getFather() {
		return father;
	}

	public void setFather(Person father) {
		this.father = father;
	}

	public Person getMother() {
		return mother;
	}

	public void setMother(Person mother) {
		this.mother = mother;
	}

	
	
}