package de.schubert.drolshagen.bloodline;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
/** 
 * @author Richard Schubert and Rene Drolshagen
 *
 * This backing bean provides methods for calculating the propabilities with which people
 * inherit specified diseases from their parents. The two parents have to be specified and the calculations
 * are done for the specified parents within one http request.
 */
public class DiseaseCalc {
	
	/**
	 * An object of this class contains the propabilities with which a person has a specified
	 * disease if the person is male or female.
	 */
	public class DiseaseResult {
		/**
		 * @uml.property  name="disease"
		 * @uml.associationEnd  
		 */
		private Disease disease;
		
		/**
		 * The propability with which the person has the specified disease if he is male.
		 */
		private double malePropability;
		
		/**
		 * The propability with which the person has the specified disease if she is female.
		 */
		private double femalePropability;
		
		public DiseaseResult() {
			
		}
		
		/**
		 * @return
		 * @uml.property  name="disease"
		 */
		public Disease getDisease() {
			return disease;
		}
		/**
		 * @param disease
		 * @uml.property  name="disease"
		 */
		public void setDisease(Disease disease) {
			this.disease = disease;
		}

		/**
		 * @return
		 * @uml.property  name="malePropability"
		 */
		public double getMalePropability() {
			return malePropability;
		}

		/**
		 * @param malePropability
		 * @uml.property  name="malePropability"
		 */
		public void setMalePropability(double malePropability) {
			this.malePropability = malePropability;
		}

		/**
		 * @return
		 * @uml.property  name="femalePropability"
		 */
		public double getFemalePropability() {
			return femalePropability;
		}

		/**
		 * @param femalePropability
		 * @uml.property  name="femalePropability"
		 */
		public void setFemalePropability(double femalePropability) {
			this.femalePropability = femalePropability;
		}
		
	}
	
	/**
	 * @uml.property  name="dataManager"
	 * @uml.associationEnd  readOnly="true"
	 */
	@Inject
	DataManager dataManager;
	
	/**
	 * @uml.property  name="diseaseResults"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="this$0:de.schubert.drolshagen.bloodline.DiseaseCalc$DiseaseResult"
	 */
	private List<DiseaseResult> diseaseResults;

	/**
	 * The id of the father of the person for which diseases are to be calculated.
	 */
	private Integer fatherId;
	
	/**
	 * The id of the mother of the person for which diseases are to be calculated.
	 */
	private Integer motherId;
	
	/**
	 * Initializes this backing bean.
	 */
	@PostConstruct
	public void init() {
		diseaseResults = new ArrayList<DiseaseCalc.DiseaseResult>();
	}
	
	/**
	 * @return
	 * @uml.property  name="fatherId"
	 */
	public Integer getFatherId() {
		return fatherId;
	}
	/**
	 * @param fatherId
	 * @uml.property  name="fatherId"
	 */
	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}
	/**
	 * @return
	 * @uml.property  name="motherId"
	 */
	public Integer getMotherId() {
		return motherId;
	}
	/**
	 * @param motherId
	 * @uml.property  name="motherId"
	 */
	public void setMotherId(Integer motherId) {
		this.motherId = motherId;
	}
	
	public List<DiseaseResult> getDiseaseResults() {
		return diseaseResults;
	}

	public void setDiseaseResults(List<DiseaseResult> diseaseResults) {
		this.diseaseResults = diseaseResults;
	}

	/**
	 * Fills the list describing the possibilities for which the child of the two specified
	 * parents inherits diseases.
	 */
	public void calcDiseases() {
		Person father = dataManager.getPerson(fatherId);
		Person mother = dataManager.getPerson(motherId);
		
		List<GeneInfo> fatherGeneInfos = dataManager.getGeneInfos(father);
		List<GeneInfo> motherGeneInfos = dataManager.getGeneInfos(mother);
		
		for (int i = 0; i < fatherGeneInfos.size(); i++) {
			GeneInfo fatherInfo = fatherGeneInfos.get(i);
			GeneInfo motherInfo = motherGeneInfos.get(i);
			Disease disease = fatherInfo.getId().getDisease();
			DiseaseResult diseaseRes = new DiseaseResult();
			diseaseRes.setDisease(disease);
			diseaseResults.add(diseaseRes);
			
			if (disease.isDominant()) {				
				if (!motherInfo.isxDefective()) {
					if (!motherInfo.isXyDefective()) {
						diseaseRes.malePropability = 0;
						if (!fatherInfo.isxDefective()) {
							diseaseRes.femalePropability = 0;
						}
						else {
							diseaseRes.femalePropability = 1;
						}
					}
					else {
						diseaseRes.malePropability = 0.5;
						if (!fatherInfo.isxDefective()) {
							diseaseRes.femalePropability = 0.5;
						}
						else {
							diseaseRes.femalePropability = 1;
						}
					}
				}
				else {
					if (!motherInfo.isXyDefective()) {
						diseaseRes.malePropability = 0.5;
						if (!fatherInfo.isxDefective()) {
							diseaseRes.femalePropability = 0.5;
						}
						else {
							diseaseRes.femalePropability = 1;
						}
					}
					else {
						diseaseRes.malePropability = 1;
						diseaseRes.femalePropability = 1;
					}
				}
			}
			else { // recessive
				diseaseRes.malePropability = 0;
				if (!fatherInfo.isxDefective()) {
					diseaseRes.femalePropability = 0;
				}
				else {
					if (!motherInfo.isxDefective()) {						
						if (!motherInfo.isXyDefective()) {
							diseaseRes.femalePropability = 0;
						}
						else {
							diseaseRes.femalePropability = 0.5;
						}
					}
					else {
						if (!motherInfo.isXyDefective()) {
							diseaseRes.femalePropability = 0.5;
						}
						else {
							diseaseRes.femalePropability = 1;
						}
					}
				}
			}
		}
		fatherId = null;
		motherId = null;
		
	}	
	
}
