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

	/**
	 * @uml.property  name="id"
	 * @uml.associationEnd  
	 */
	@EmbeddedId
	private GeneInfoId id;
	
	/**
	 * @uml.property  name="xDefective"
	 */
	@Column(name = "x_defective", nullable = false)
	private boolean xDefective;
	
	/**
	 * @uml.property  name="xyDefective"
	 */
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

	/**
	 * @return
	 * @uml.property  name="id"
	 */
	public GeneInfoId getId() {
		return id;
	}

	/**
	 * @param id
	 * @uml.property  name="id"
	 */
	public void setId(GeneInfoId id) {
		this.id = id;
	}

	/**
	 * @return
	 * @uml.property  name="xDefective"
	 */
	public boolean isxDefective() {
		return xDefective;
	}

	/**
	 * @param xDefective
	 * @uml.property  name="xDefective"
	 */
	public void setxDefective(boolean xDefective) {
		this.xDefective = xDefective;
	}

	/**
	 * @return
	 * @uml.property  name="xyDefective"
	 */
	public boolean isXyDefective() {
		return xyDefective;
	}

	/**
	 * @param xyDefective
	 * @uml.property  name="xyDefective"
	 */
	public void setXyDefective(boolean xyDefective) {
		this.xyDefective = xyDefective;
	}
	
}
