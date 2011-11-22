package de.schubert.drolshagen.bloodline;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "gene_info")
@Entity
public class GeneInfo {

	@EmbeddedId
	private GeneInfoId id;
	
	@Column(name = "x_intact", nullable = false)
	private boolean xIntact;
	
	@Column(name = "xy_intact", nullable = false)
	private boolean xyIntact;
	
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
