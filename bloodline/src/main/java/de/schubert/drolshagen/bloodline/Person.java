package de.schubert.drolshagen.bloodline;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Represents a Person in the Database
 * 
 * @author Rene Drolshagen and Richard Schubert
 */
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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "father")
	private Person father;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mother")
	private Person mother;

	/**
	 * Generates a new Person which will be stored in the Database with the following Information
	 * @param sirname The Surname of a Person
	 * @param forename The Forname of a Person
	 * @param birthDate The Birthdate of a Person
	 * @param isMale If a Person is Male (boolean)
	 * @param father The PersonID of the Father
	 * @param mother The PersonID of the Mother
	 */
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

	/**
	 * @return If the Person has a father
	 */
	public boolean hasFather() {
		return father != null;
	}
	
	/**
	 * @return If the Person has a mother
	 */
	public boolean hasMother() {
		return mother != null;
	}

	@Override
	public String toString() {
		return "(" + forename + " " + sirname + ")";
	}

	/**
	 * Checks if a Person equals another Person
	 * @return Boolean value with the Value of the equal
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Person)) {
			return false;
		}
		Person otherPerson = (Person) other;
		return otherPerson.forename.equals(forename)
				&& otherPerson.sirname.equals(sirname)
				&& otherPerson.birthDate.equals(birthDate);
	}
	
	@Override
	public int hashCode() {
		return forename.hashCode() + sirname.hashCode() + birthDate.hashCode();
	}

	/**
	 * Calculates the depth of the Tree (for the .jpeg way)
	 * @return The depth in px
	 */
	public int calcDepth() {
		// Nimmt die Personen welche verwandt sind einfach als folgende an
		int lDepth = 0;
		int rDepth = 0;

		// Vater == Links
		if (this.getFather() != null) {
			lDepth = this.getFather().calcDepth();
		}

		// Mutter == Rechts
		if (this.getMother() != null) {
			rDepth = this.getMother().calcDepth();
		}
		if (lDepth >= rDepth) {
			return lDepth + 1;
		} else {
			return rDepth + 1;
		}
	}

}
