package de.schubert.drolshagen.bloodline;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@NamedQuery(name = User.QUERY_GET_PASSWORD, query = "select u.password from User u where u.username = :username")
@Entity
public class User {
	
	public static final String QUERY_GET_PASSWORD = "User.getPassword";
	
	@Id
	private String username;
	private String password;
	
	// default constructor required by JPA
	public User() {
		
	}	
		
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUserName() {
		return username;
	}
	
	public void setUserName(String userName) {
		this.username = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return username;
	}
	
	
}
