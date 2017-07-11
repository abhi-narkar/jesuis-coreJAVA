package fr.epita.abhishek.services;
/**
 * @author abhisheknarkar
 */
public interface Authenticator {
	 /**
     * Authenticate with user name and password.
     */
    boolean authenticate(String username, String password);
}
