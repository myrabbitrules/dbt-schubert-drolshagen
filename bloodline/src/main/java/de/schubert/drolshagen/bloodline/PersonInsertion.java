package de.schubert.drolshagen.bloodline;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Handels the PersonInsertion 
 * 
 * @author Rene Drolshagen and Richard Schubert
 */
@Named
@RequestScoped
public class PersonInsertion {
	public class DiseaseInfo {
		/**
		 * The Disease which is represented with the defect x and xy Parts
		 */
		private Disease disease;
		private boolean xDefective;
		private boolean xyDefective;
		
		public DiseaseInfo() {
			
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

		public Disease getDisease() {
			return disease;
		}

		public void setDisease(Disease disease) {
			this.disease = disease;
		}
		
	}
	
	@Inject	
	private DataManager dataManager;
	
	// The Data of a Person: Name, Surname etc.
	private String surname;
	private String forename;
	private Date birthDate;
	private boolean isMale;
	
	// The Information about the Parents of a Person
	private Integer fatherId;
	private Integer motherId;
	
	// The Birthdate of a Person
	private Integer day;
	private Integer month;
	private Integer year;
	
	// The List of Diseases
	private List<DiseaseInfo> diseaseInfos;

	public List<DiseaseInfo> getDiseaseInfos() {
		return diseaseInfos;
	}
	
	public void setDiseaseInfos(List<DiseaseInfo> diseaseInfos) {
		this.diseaseInfos = diseaseInfos;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	/**
	 * Initialises a new Disease List
	 */
	@PostConstruct
	public void init() {
		diseaseInfos = new ArrayList<PersonInsertion.DiseaseInfo>();
		for (Disease disease : dataManager.getDiseases()) {
			DiseaseInfo diseaseInfo = new DiseaseInfo();
			diseaseInfo.setDisease(disease);
			diseaseInfos.add(diseaseInfo);
		}
	}

	/**
	 * Creates a Person with the stored Information
	 * @return null
	 */
	public String createPerson() {
		Person person = new Person();
		person.setForename(forename);
		person.setSirname(surname);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		person.setBirthDate(calendar.getTime());
		person.setMale(isMale);
		Person father = dataManager.getPerson(fatherId);
		Person mother = dataManager.getPerson(motherId);
		person.setFather(father);
		person.setMother(mother);
		
		dataManager.addPerson(person);
		
		for (DiseaseInfo diseaseInfo : diseaseInfos) {
			GeneInfo geneInfo = new GeneInfo(person, diseaseInfo.disease, diseaseInfo.xDefective, diseaseInfo.xyDefective);
			dataManager.addGeneInfo(geneInfo);
		}
		
		setFatherId(null);
		setMotherId(null);
		
		return null;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String sirname) {
		this.surname = sirname;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public Integer getFatherId() {
		return fatherId;
	}

	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}

	public Integer getMotherId() {
		return motherId;
	}

	public void setMotherId(Integer motherId) {
		this.motherId = motherId;
	}
}
