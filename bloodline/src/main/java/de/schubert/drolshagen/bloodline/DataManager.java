package de.schubert.drolshagen.bloodline;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Stateless
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
