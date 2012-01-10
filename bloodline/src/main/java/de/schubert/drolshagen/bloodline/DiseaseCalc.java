package de.schubert.drolshagen.bloodline;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DiseaseCalc {
	
	/**
	 * @author  rene
	 */
	public class DiseaseResult {
		/**
		 * @uml.property  name="disease"
		 * @uml.associationEnd  
		 */
		private Disease disease;
		/**
		 * @uml.property  name="malePropability"
		 */
		private double malePropability;
		/**
		 * @uml.property  name="femalePropability"
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
	 * @uml.property  name="fatherId"
	 */
	private Integer fatherId;
	/**
	 * @uml.property  name="motherId"
	 */
	private Integer motherId;
	
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
