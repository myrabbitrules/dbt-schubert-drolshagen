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
	
	public class DiseaseResult {
		private Disease disease;
		private double malePropability;
		private double femalePropability;
		
		public DiseaseResult() {
			
		}
		
		public Disease getDisease() {
			return disease;
		}
		public void setDisease(Disease disease) {
			this.disease = disease;
		}

		public double getMalePropability() {
			return malePropability;
		}

		public void setMalePropability(double malePropability) {
			this.malePropability = malePropability;
		}

		public double getFemalePropability() {
			return femalePropability;
		}

		public void setFemalePropability(double femalePropability) {
			this.femalePropability = femalePropability;
		}
		
	}
	
	@Inject
	DataManager dataManager;
	
	private List<DiseaseResult> diseaseResults;

	private int fatherId;
	private int motherId;
	
	@PostConstruct
	public void init() {
		diseaseResults = new ArrayList<DiseaseCalc.DiseaseResult>();
	}
	
	public int getFatherId() {
		return fatherId;
	}
	public void setFatherId(int fatherId) {
		this.fatherId = fatherId;
	}
	public int getMotherId() {
		return motherId;
	}
	public void setMotherId(int motherId) {
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
				if (motherInfo.isxIntact()) {
					if (motherInfo.isXyIntact()) {
						diseaseRes.malePropability = 0;
						if (fatherInfo.isxIntact()) {
							diseaseRes.femalePropability = 0;
						}
						else {
							diseaseRes.femalePropability = 1;
						}
					}
					else {
						diseaseRes.malePropability = 0.5;
						if (fatherInfo.isxIntact()) {
							diseaseRes.femalePropability = 0.5;
						}
						else {
							diseaseRes.femalePropability = 1;
						}
					}
				}
				else {
					if (motherInfo.isXyIntact()) {
						diseaseRes.malePropability = 0.5;
						if (fatherInfo.isxIntact()) {
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
				if (fatherInfo.isxIntact()) {
					diseaseRes.femalePropability = 0;
				}
				else {
					if (motherInfo.isxIntact()) {						
						if (motherInfo.isXyIntact()) {
							diseaseRes.femalePropability = 0;
						}
						else {
							diseaseRes.femalePropability = 0.5;
						}
					}
					else {
						if (motherInfo.isXyIntact()) {
							diseaseRes.femalePropability = 0.5;
						}
						else {
							diseaseRes.femalePropability = 1;
						}
					}
				}
			}
		}
		
		
		
	}
	
	
}
