package de.schubert.drolshagen.bloodline;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class Insertion {
	
	@Inject	
	private DataManager dataManager;
	
	private String surname;
	
	private String forename;
	
	private Date birthDate;
	
	private boolean isMale;
		
	private Integer fatherId;
	
	private Integer motherId;
	
	private Integer day;
	
	private Integer month;
	
	private Integer year;
	
	private List<Disease> diseases;
	
	public List<Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(List<Disease> diseases) {
		this.diseases = diseases;
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
	
	@PostConstruct
	public void init() {
		dataManager.addDisease(new Disease("pest", true));
		dataManager.addDisease(new Disease("grippe", false));
		diseases = dataManager.getDiseases();
	}

	public String createPerson() {
		Person person = new Person();
		person.setForename(forename);
		person.setSirname(surname);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		person.setBirthDate(calendar.getTime());
		Person father = dataManager.getPerson(fatherId);
		Person mother = dataManager.getPerson(motherId);
		person.setFather(father);
		person.setMother(mother);
		dataManager.addPerson(person);
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
