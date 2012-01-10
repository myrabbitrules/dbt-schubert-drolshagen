package de.schubert.drolshagen.bloodline;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name = User.QUERY_GET_PASSWORD, query = "select u.password from User u where u.username = :username")
@Table(name = "user")
@Entity

/**
 * @author Rene Drolshagen and Richard Schubert
 *
 * Class which represents a User. 
 */
public class User {
	
	public static final String QUERY_GET_PASSWORD = "User.getPassword";
	
	/**
	 * The Username of the User which is stored in the Database
	 */
	@Id
	@Column(name = "username")
	private String username;
	
	/**
	 * The Password for a specific User stored in the Database
	 */
	@Column(name = "password", nullable = false)
	private String password;
	
	/**
	 * Empty default Constructor. Needed by JPA! 
	 */
	public User() {
		
	}	
		
	/**
	 * This Constructor generates a new User!
	 * This User can be used at the Login Screen.
	 * Also it can be saved in the Database
	 * @param username The Login Name of the User
	 * @param password The defined Password of the User
	 */
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * This method returns the Username
	 * @return The Username to a User Instance
	 */
	public String getUserName() {
		return username;
	}
	
	/**
	 * This method sets the Username
	 * @param userName The new Username which will be set
	 */
	public void setUserName(String userName) {
		this.username = userName;
	}
	
	/**
	 * Returns the Password for a User (encrypted)
	 * @return The encrypted Password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets a new Password for a User
	 * @param password The password which will be saved
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return username;
	}
	
	
}
