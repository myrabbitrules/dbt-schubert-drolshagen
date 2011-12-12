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
	
	@Column(name = "x_defective", nullable = false)
	private boolean xDefective;
	
	@Column(name = "xy_defective", nullable = false)
	private boolean xyDefective;
	
	public GeneInfo() {
		
	}
	
	public GeneInfo(Person person, Disease disease, boolean xDefective, boolean xyDefective) {
		GeneInfoId id = new GeneInfoId();
		id.setPerson(person);
		id.setDisease(disease);
		this.id = id;
		this.xDefective = xDefective;
		this.xyDefective = xyDefective;
	}

	public GeneInfoId getId() {
		return id;
	}

	public void setId(GeneInfoId id) {
		this.id = id;
	}

	public boolean isxDefective() {
		return xDefective;
	}

	public void setxDefective(boolean xDefective) {
		this.xDefective = xDefective;
	}

	public boolean isXyDefective() {
		return xyDefective;
	}

	public void setXyDefective(boolean xyDefective) {
		this.xyDefective = xyDefective;
	}
	
}
