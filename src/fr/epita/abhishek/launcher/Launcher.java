package fr.epita.abhishek.launcher;
/**
 * @author abhisheknarkar
 */
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import fr.epita.abhishek.datamodel.Identity;
import fr.epita.abhishek.exceptions.DAOCreateException;
import fr.epita.abhishek.exceptions.DAODeleteException;
import fr.epita.abhishek.exceptions.DAOFindException;
import fr.epita.abhishek.exceptions.DAOUpdateException;
import fr.epita.abhishek.services.Authenticator;
import fr.epita.abhishek.services.IdentityDAO;
import fr.epita.abhishek.services.JDBCIdentityDAO;
import fr.epita.abhishek.services.Authorization;

public class Launcher {
	private static Scanner scan;
	
	public static void main(String[] args) throws Exception {
		 scan = new Scanner(System.in);

	        System.out.println("Bienvenue dans L'application.");

	        if (!authenticate()) {
	            System.out.println("Authorization failure.");
	            System.exit(0);
	        }

	        System.out.println("Authorized successfully.");

	        mainProgram();
	    }

	    private static boolean authenticate() {
	        Authenticator authenticator = new Authorization();

	        System.out.println("Please enter username and password to proceed:");
	        System.out.println("Username: ");
	        String username = scan.nextLine();
	        System.out.println("Password: ");
	        String password = scan.nextLine();

	        return authenticator.authenticate(username, password);
	    }

	    private static void mainProgram() throws Exception {
	        System.out.println("");
	        System.out.println("1) Create new Identity");
	        System.out.println("2) Search Identities");
	        System.out.println("3) Update Identity");
	        System.out.println("4) Delete Identity");
	        System.out.println("5) Exit");

	        System.out.println("Choose task: ");

	        String task = scan.nextLine().trim();

	        switch (task) {
	            case "1":
	                createIdentityMenu();
	                break;
	            case "2":
	                searchIdentityMenu();
	                break;
	            case "3":
	                updateIdentityMenu();
	                break;
	            case "4":
	                deleteIdentityMenu();
	                break;
	            case "5":
	             
	                System.out.println("au revoir, bonne journee...");
	                System.exit(0);
	                break;
	            default:
	                System.out.println("Unrecognised task.");
	                mainProgram();
	        }
	    }

	    private static void createIdentityMenu() throws Exception {
	        System.out.println("Create new identity: ");
	        System.out.println("Display Name: ");
	        String displayname = scan.nextLine();
	        System.out.println("Email: ");
	        String email = scan.nextLine();
	        Identity identity = new Identity(displayname, email, null);

	        createIdentity(identity);
	        mainProgram();
	    }

	    private static void searchIdentityMenu() throws Exception {
	        System.out.println("Search identities: ");
	        System.out.println("UID: ");
	        String uid = scan.nextLine();
	        System.out.println("Display Name: ");
	        String displayname = scan.nextLine();
	        System.out.println("Email: ");
	        String email = scan.nextLine();
	        Identity identity = new Identity(null, null, null);

	        if (uid.length() != 0) {
	            identity.setUid(Integer.parseInt(uid));
	        }

	        if (displayname.length() != 0) {
	            identity.setDisplayName(displayname);
	        }

	        if (email.length() != 0) {
	            identity.setEmail(email);
	        }

	        searchIdentity(identity);
	        mainProgram();
	    }

	    private static void updateIdentityMenu() throws Exception {
	        System.out.println("Update Identity:");
	        Identity identity = new Identity(null, null, null);

	        searchIdentity(identity);
	        System.out.println("Choose Identity by UID:");
	        int uid = Integer.parseInt(scan.nextLine());
	        identity.setUid(uid);
	        System.out.println("New Display Name:");
	        identity.setDisplayName(scan.nextLine());
	        System.out.println("New Email:");
	        identity.setEmail(scan.nextLine());

	        updateIdentity(identity);

	        mainProgram();
	    }

	    private static void deleteIdentityMenu() throws Exception {
	        System.out.println("Delete Identity:");
	        Identity identity = new Identity(null, null, null);

	        searchIdentity(identity);
	        System.out.println("Choose Identity by UID:");
	        int uid = Integer.parseInt(scan.nextLine());
	        identity.setUid(uid);

	        deleteIdentity(identity);

	        mainProgram();
	    }

	    private static void createIdentity(Identity identity) {
	        try {
	            IdentityDAO dao = new JDBCIdentityDAO();
	            try {
	                dao.create(identity);
	                System.out.println("Successfully created new identity!");
	            } catch (DAOCreateException e) {
	                e.printStackTrace();
	            } finally {
	                dao.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void searchIdentity(Identity criteria)throws Exception {
	        try {
	            IdentityDAO dao = new JDBCIdentityDAO();
	            try {
	                List<Identity> results = dao.find(criteria);
	                for (Identity result: results) {
	                    System.out.println(result);
	                }
	            } catch (DAOFindException e) {
	                e.printStackTrace();
	            } finally {
	                dao.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void updateIdentity(Identity identity) {
	        try {
	            IdentityDAO dao = new JDBCIdentityDAO();
	            try {
	                dao.update(identity);
	                System.out.println("Successfully update identity!");
	            } catch (DAOUpdateException e) {
	                e.printStackTrace();
	            } finally {
	                dao.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void deleteIdentity(Identity identity) {
	        try {
	            IdentityDAO dao = new JDBCIdentityDAO();
	            try {
	                dao.delete(identity);
	                System.out.println("Successfully delete identity!");
	            } catch (DAODeleteException e) {
	                e.printStackTrace();
	            } finally {
	                dao.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }



}
