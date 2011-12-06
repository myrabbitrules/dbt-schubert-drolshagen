package de.schubert.drolshagen.bloodline;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "gene_info")
@Entity
@NamedQueries({
	@NamedQuery(name = GeneInfo.QUERY_GET_ALL_BY_PERSON, query = "select gi from GeneInfo gi where gi.id.person = :person")
})
public class GeneInfo {
	
	public static final String QUERY_GET_ALL_BY_PERSON = "GeneInfo.getAllByPerson" ;

	@EmbeddedId
	private GeneInfoId id;
	
	@Column(name = "x_intact", nullable = false)
	private boolean xIntact;
	
	@Column(name = "xy_intact", nullable = false)
	private boolean xyIntact;
	
	public GeneInfo() {
		
	}
	
	public GeneInfo(Person person, Disease disease, boolean xintact, boolean xyIntact) {
		GeneInfoId id = new GeneInfoId();
		id.setPerson(person);
		id.setDisease(disease);
		this.id = id;
		this.xIntact = xintact;
		this.xyIntact = xyIntact;
	}

	public GeneInfoId getId() {
		return id;
	}

	public void setId(GeneInfoId id) {
		this.id = id;
	}

	public boolean isxIntact() {
		return xIntact;
	}

	public void setxIntact(boolean xIntact) {
		this.xIntact = xIntact;
	}

	public boolean isXyIntact() {
		return xyIntact;
	}

	public void setXyIntact(boolean xyIntact) {
		this.xyIntact = xyIntact;
	}
	
	
}
