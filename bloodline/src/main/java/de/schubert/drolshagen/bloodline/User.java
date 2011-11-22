package de.schubert.drolshagen.bloodline;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name = User.QUERY_GET_PASSWORD, query = "select u.password from User u where u.username = :username")
@Table(name = "user")
@Entity
public class User {
	
	public static final String QUERY_GET_PASSWORD = "User.getPassword";
	
	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "password", nullable = false)
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
