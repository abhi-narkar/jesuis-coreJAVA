package fr.epita.abhishek.test.services;
/**
 * @author abhisheknarkar
 */
import java.sql.SQLException;
import java.util.List;

import fr.epita.abhishek.datamodel.Identity;
import fr.epita.abhishek.exceptions.DAOCreateException;
import fr.epita.abhishek.exceptions.DAOFindException;
import fr.epita.abhishek.services.IdentityDAO;
import fr.epita.abhishek.services.JDBCIdentityDAO;

public class TestJDBCIdentityDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
	            IdentityDAO identityDAO = new JDBCIdentityDAO();
	            Identity identity = new Identity("thomas", "thomasbroussard@ymail.com", null);

	            try {
	                identityDAO.create(identity);
	            } catch (DAOCreateException e) {
	                e.printStackTrace();
	            }

	            try {
	                List<Identity> results = identityDAO.find(new Identity(null, null, null));
	                for (Identity result : results) {
	                    System.out.println(result);
	                }
	            } catch (DAOFindException e) {
	                e.printStackTrace();
	            }

	            identityDAO.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
