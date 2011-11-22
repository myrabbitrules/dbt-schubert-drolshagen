package de.schubert.drolshagen.bloodline;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Stateless // we want automatic transaction demarcation
public class DataManager {

	@Inject
	EntityManager em;

	@Inject
	Logger logger;

	public boolean addUser(User user) {
		if (!userExists(user.getUserName())) {
			em.persist(user);
			return true;
		}
		return false;
	}
	
	public boolean addDisease(Disease disease) {
		if (!diseaseExists(disease)) {
			em.persist(disease);
			return true;
		}
		return false;
	}
	
	public boolean addPerson(Person person) {
		if (!personExists(person)) {
			em.persist(person);
			return true;
		}
		return false;
	}
	
	public boolean addGeneInfo(GeneInfo geneInfo) {
		if (!geneInfoExists(geneInfo)) {
			em.persist(geneInfo);
			return true;
		}
		return false;
	}
	
	public boolean geneInfoExists(GeneInfo geneInfo) {
		return em.find(GeneInfo.class, geneInfo.getId()) != null;
	}
	
	public boolean personExists(Person person) {
		return em.find(Person.class, person.getId()) != null;
	}
	
	public boolean diseaseExists(Disease disease) {
		return em.find(Disease.class, disease.getId()) != null;		
	}
	
	public boolean userExists(String username) {
		return em.find(User.class, username) != null;
	}
	
	public boolean passwordIsCorrect(String username, String password) {
		Query query = em.createNamedQuery(User.QUERY_GET_PASSWORD);
		query.setParameter("username", username);
		String correctPassw = (String) query.getSingleResult();
		return correctPassw.equals(password);
	}
	
	public User getUser(String username) {
		return em.find(User.class, username);
	}
}
