package fr.epita.abhishek.services;
import fr.epita.abhishek.configuration.Configuration;

public class Authorization implements Authenticator {
	 private String username;
	    private String password;

	    /**Get a default username and password.
	     */
	    public Authorization() {
	        this.username = Configuration.authenticateUsername;
	        this.password = Configuration.authenticatePassword;
	    }

	    /**
	     * @param username 
	     * @param password 
	     */
	    public boolean authenticate(String username, String password) {
	        return username.equals(this.username) && password.equals(this.password);
	    }

}
