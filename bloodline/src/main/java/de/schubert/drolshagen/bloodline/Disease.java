package de.schubert.drolshagen.bloodline;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "disease")
@Entity
@NamedQueries({
	@NamedQuery(name = Disease.QUERY_GET_BY_ID, query = "select d from Disease d where d.id = :id"),
	@NamedQuery(name = Disease.QUERY_GET_ALL, query = "select d from Disease d")
})
public class Disease {
	
	public static final String QUERY_GET_BY_ID = "Disease.getById";
	public static final String QUERY_GET_ALL = "Disease.getAll";
	
	/**
	 * @uml.property  name="id"
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	/**
	 * @uml.property  name="name"
	 */
	@Column(name = "name", nullable = false)
	private String name;
	
	/**
	 * @uml.property  name="dominant"
	 */
	@Column(name = "dominant", nullable = false)
	private boolean dominant;
		
	public Disease(String name, boolean dominant) {
		super();
		this.name = name;
		this.dominant = dominant;
	}
	
	public Disease() {
		
	}
	
	/**
	 * @return
	 * @uml.property  name="id"
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 * @uml.property  name="id"
	 */
	public void setId(int id) {
		this.id = id;
	}

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
	
	@Override
	public boolean equals(Object other) {

		if (!(other instanceof Disease)) {
			return false;
		}
		Disease otherDisease = (Disease) other;
		return otherDisease.name.equals(name);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	
}
