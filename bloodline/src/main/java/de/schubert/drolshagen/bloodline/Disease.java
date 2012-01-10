package de.schubert.drolshagen.bloodline;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * An entity representing a disease. A disease is either
 * dominant or recessive.
 * 
 * @author Richard Schubert and Rene Drolshagen
 */
@Table(name = "disease")
@Entity
@NamedQueries({
	@NamedQuery(name = Disease.QUERY_GET_BY_ID, query = "select d from Disease d where d.id = :id"),
	@NamedQuery(name = Disease.QUERY_GET_ALL, query = "select d from Disease d")
})
public class Disease {
	
	public static final String QUERY_GET_BY_ID = "Disease.getById";
	public static final String QUERY_GET_ALL = "Disease.getAll";
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	/**
	 * True if this disease is dominant, otherwise false.
	 */
	@Column(name = "dominant", nullable = false)
	private boolean dominant;
		
	/**
	 * Creates a new disease with a specified name that is either dominant or recessive.
	 * @param name the name of the disease
	 * @param dominant true if the disease is to be dominant, false if the disease is to be recessive
	 */
	public Disease(String name, boolean dominant) {
		super();
		this.name = name;
		this.dominant = dominant;
	}
	
	/**
	 * Needed by JPA for storing objects of this entity.
	 */
	public Disease() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
