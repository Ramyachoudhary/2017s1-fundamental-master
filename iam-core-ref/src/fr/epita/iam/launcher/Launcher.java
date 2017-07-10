/**
 * 
 */
package fr.epita.iam.launcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.Scanner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.DaoDeleteException;
import fr.epita.iam.exceptions.DaoSaveException;
import fr.epita.iam.exceptions.DaoUpdateException;
import fr.epita.iam.services.Authenticator;
import fr.epita.iam.services.FileIdentityDAO;
import fr.epita.iam.services.IdentityDAO;
import fr.epita.iam.services.JDBCIdentityDAO;
import fr.epita.logging.LogConfiguration;
import fr.epita.logging.Logger;

/**
 * The Class Launcher.
 *
 * @author Ramya
 * 
 * The Launcher class is to launch the application
 */
public class Launcher {

	/**
	 * Main program to start the application  
	 * 
	 * Here first the program will take the username and password as input to authenticate.
	 * 
	 * If successfully authenticated then it will display the menu with 4 options
	 * 
	 * 1.Create identity
	 * 2.Update identity
	 * 3.Delete identity
	 * 4.Quit
	 * 
	 *  Based on the option chooses by the user it will be navigated to appropriate function by using the Dao pattern.
	 *  Here we use IdentityDao interface to provide the separation of concerns.
	 *
	 * @param args the arguments
	 * @throws FileNotFoundException the file not found exception
	 * @throws SQLException the SQL exception
	 */
	public static void main(String[] args) throws FileNotFoundException, SQLException {

		//Assigning the JDBCIdentityDAO implementation to store the data into database
		IdentityDAO dao = new JDBCIdentityDAO();		
		
		LogConfiguration conf = new LogConfiguration("application.log");
		Logger logger = new Logger(conf);
		
	    logger.log("beginning of the program");
		Scanner scanner = new Scanner(System.in);
		
		//taking the username and password from the user
		System.out.println("User name :");
		String userName = scanner.nextLine();
		System.out.println("Password :");
		String password = scanner.nextLine();
		
		//checking the authentication 
		if (!Authenticator.authenticate(userName, password)) {
			logger.log("unable to authenticate "  + userName);
			scanner.close();
			return;
		} else {
			System.out.println("Successfully authenticated");
			// We are authenticated
			String answer = "";
			while (!"4".equals(answer)) {			
				System.out.println("1. Create Identity");
				System.out.println("2. Update Identity");
				System.out.println("3. Delete Identity");
				System.out.println("4. Quit");
				System.out.println("your choice : ");
				
				answer = scanner.nextLine();

				logger.log("User chose the " + answer + " choice");
				
				switch (answer) {
				case "1":
					//User opts to create the identity the following logic will be processed
					System.out.println("Identity Creation");
					logger.log("selected the identity creation");
					System.out.println("please input the identity display name :");
					String displayName  = scanner.nextLine();
					System.out.println("identity email :");
					String email = scanner.nextLine();
					Identity identity = new Identity(displayName, null, email);
					try {
						//calling the save method to persist the entered input data.
						dao.save(identity);
						System.out.println("the save operation completed successfully");
					} catch (DaoSaveException e) {
						System.out.println("the save operation is not able to complete, details :" + e.getMessage());
					}					
					break;
					
				case "2":
					// Update Identity based on the given ID
					System.out.println("Identity Update");
					
					System.out.println("please input the identity display name to update :");
					displayName  = scanner.nextLine();
					System.out.println("identity email :");
					email = scanner.nextLine();
					System.out.println("enter uid :");
					String id = scanner.nextLine();
					identity = new Identity(displayName, id, email);
					try {
						//calling the method to update the identity
						dao.update(identity);
						System.out.println("the update operation completed successfully");
					} catch (DaoUpdateException e) {
						System.out.println("the update operation is not able to complete, details :" + e.getMessage());
					}					
					break;
					
				case "3":

					// Delete Identity based on the ID matched
					System.out.println("Identity Deletion");
					
					System.out.println("please input the identity UID to Delete :");
					id = scanner.nextLine();
					identity = new Identity(null, id, null);					
					
					
					try {
						//calling the method to update the identity
						dao.delete(identity);
						System.out.println("the delete operation completed successfully");
					} catch (DaoDeleteException e) {
						System.out.println("the delete operation is not able to complete, details :" + e.getMessage());
					}		
					
					
									
					break;

				case "4":
					//exiting the program 
					System.out.println("you decided to quit, bye!");
					break;
				default:
					// if the user enters any wrong input other than from the menu
					System.out.println("unrecognized option : type 1,2,3 or 4 to quit");
					break;
				}

			}

		}
		
		scanner.close();

	}


}
