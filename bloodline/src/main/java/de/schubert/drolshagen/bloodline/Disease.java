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
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "dominant", nullable = false)
	private boolean dominant;
		
	public Disease(String name, boolean dominant) {
		super();
		this.name = name;
		this.dominant = dominant;
	}
	
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
