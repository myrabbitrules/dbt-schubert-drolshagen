package de.schubert.drolshagen.bloodline;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/** 
 * @author Richard Schubert and Rene Drolshagen
 * 
 * This class provides methods for accessing the database.
 */
@Stateless // we want automatic transaction demarcation
public class DataManager {

	/**
	 * @uml.property  name="em"
	 * @uml.associationEnd  readOnly="true" multiplicity="(0 -1)" elementType="java.lang.String"
	 */
	@Inject
	EntityManager em;

	/**
	 * @uml.property  name="logger"
	 */
	@Inject
	Logger logger;

	/**
	 * Add new user to database if user does with same id does not already exist.
	 * 
	 * @param user the user to be added
	 * @return true if user was added, otherwise false
	 */
	public boolean addUser(User user) {
		if (!userExists(user.getUserName())) {
			em.persist(user);
			return true;
		}
		return false;
	}
	
	/**
	 * Add new disease to the database if disease with same id does not already exist.
	 * 
	 * @param disease the disease to be added to database
	 * @return true if the disease was added to the database, otherwise false
	 */
	public boolean addDisease(Disease disease) {
		if (!diseaseExists(disease)) {
			em.persist(disease);
			return true;
		}
		return false;
	}
	
	/**
	 * Add new person to the database if person with same id does not already exist.
	 * 
	 * @param person the person to be added to the database
	 * @return true if the person was added to the database, otherwise false
	 */
	public boolean addPerson(Person person) {
		if (!personExists(person)) {
			if (person.hasFather()) {
				person.setFather(em.merge(person.getFather()));
			}
			if (person.hasMother()) {
				person.setMother(em.merge(person.getMother()));
			}			
			em.persist(person);
			return true;
		}
		return false;
	}	
	
	/**
	 * Creates a list containing the gene infos for a specified person.
	 * 
	 * @param person the person for which gene infos are to be retrieved
	 * @return a list containing the gene infos for the specified person
	 */
	public List<GeneInfo> getGeneInfos(Person person) {
		em.merge(person);
		Query query = em.createNamedQuery(GeneInfo.QUERY_GET_ALL_BY_PERSON);
		query.setParameter("person", person);
		@SuppressWarnings("unchecked")
		List<GeneInfo> res = query.getResultList();
		
		return res;
	}
	
	/**
	 * Adds a new gene info object to the database if a gene info object with the
	 * same id does not already exist. 
	 * 
	 * @param geneInfo the gene info to be added
	 * @return true if the gene info was added to the database, otherwise false
	 */
	public boolean addGeneInfo(GeneInfo geneInfo) {
		if (!geneInfoExists(geneInfo)) {
			em.persist(geneInfo);
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if a specified gene info exists in the database.
	 * 
	 * @param geneInfo the gene info whose existance is to be checked
	 * @return true if the specified gene info exists in the database, otherwise false
	 */
	public boolean geneInfoExists(GeneInfo geneInfo) {
		return em.find(GeneInfo.class, geneInfo.getId()) != null;
	}

	/**
	 * Checks if a specified person exists in the database.
	 * 
	 * @param person the person whose existance is to be checked
	 * @return true if the specified person exists in the database, otherwise false
	 */
	public boolean personExists(Person person) {
		return em.find(Person.class, person.getId()) != null;
	}
	
	/**
	 * Checks if a specified disease exists in the database.
	 * 
	 * @param disease the disease whose existance is to be checked
	 * @return true if the specified disease exists in the database, otherwise false
	 */
	public boolean diseaseExists(Disease disease) {
		return em.find(Disease.class, disease.getId()) != null;		
	}
	
	/**
	 * Checks if a specified user exists in the database.
	 * 
	 * @param geneInfo the user whose existance is to be checked
	 * @return true if the specified user exists in the database, otherwise false
	 */
	public boolean userExists(String username) {
		return em.find(User.class, username) != null;
	}
	
	/**
	 * Checks whether a specified password is the correct password for
	 * a specified user.
	 * 
	 * @param username the user
	 * @param password the password
	 * @return true if the specified password is the correct password for
	 * the specified user
	 */
	public boolean passwordIsCorrect(String username, String password) {
		Query query = em.createNamedQuery(User.QUERY_GET_PASSWORD);
		query.setParameter("username", username);
		String correctPassw = (String) query.getSingleResult();
		return correctPassw.equals(password);
	}
	
	/**
	 * Retrieves the user for a specified username from the database if a user
	 * with this username exists.
	 * 
	 * @param username the username
	 * @return the user for the specified username from the database if a user
	 * with this username exists, otherwise null
	 */
	public User getUser(String username) {
		return em.find(User.class, username);
	}
	
	/**
	 * Retrieves the person for a specified id from the database if a person
	 * with this id exists.
	 * 
	 * @param id the id
	 * @return the person for the specified id from the database if a person
	 * with this id exists, otherwise null
	 */
	public Person getPerson(int id) {
		return em.find(Person.class, id);
	}
	
	/**
	 * Creates a list that contains all diseases stored in the database.
	 * 
	 * @return a list that contains all diseases stored in the database
	 */
	@SuppressWarnings("unchecked")
	public List<Disease> getDiseases() {
		Query query = em.createNamedQuery(Disease.QUERY_GET_ALL);
		return query.getResultList();
	}
}
