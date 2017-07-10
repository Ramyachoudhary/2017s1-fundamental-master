/**
 * 
 */
package fr.epita.iam.services;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.DaoDeleteException;
import fr.epita.iam.exceptions.DaoSaveException;
import fr.epita.iam.exceptions.DaoSearchException;
import fr.epita.iam.exceptions.DaoUpdateException;
import fr.epita.logging.LogConfiguration;
import fr.epita.logging.Logger;

/**
 * 
 * This is a class that contains all the database operations for the class
 * Identity
 * 
 * Here change the database properties based on your configurations which i have given in identities.sql file
 * 
 * <pre>
 *  JDBCIdentityDAO dao = new JDBCIdentityDAO();
 *  // save an identity
 *  dao.save(new Identity(...));
 *  
 *  //search with an example criteria (qbe)  
 *  dao.search(new Identity(...);
 * </pre>
 * 
 * <b>warning</b> this class is dealing with database connections, so beware to
 * release it through the {@link #releaseResources()}
 * 
 * @author Ramya
 *
 */
public class JDBCIdentityDAO implements IdentityDAO {

	/** The connection. */
	Connection connection;
	
	/** The connection string. */
	String connectionString = "jdbc:derby://localhost:1527/identity";
	
	/** The user. */
	String user = "RAMYA";
	
	/** The pwd. */
	String pwd = "RAMYA";
	
	/** The conf. */
	LogConfiguration conf;
	
	/** The logger. */
	Logger logger;

	/**
	 * Instantiates a new JDBC identity DAO.
	 *
	 * @throws SQLException the SQL exception
	 * @throws FileNotFoundException the file not found exception
	 */
	public JDBCIdentityDAO() throws SQLException, FileNotFoundException {
		this.connection = DriverManager.getConnection(connectionString, user, pwd);
		conf = new LogConfiguration("application.log");
		logger = new Logger(conf);
	}

	/**
	 * This method is to save the identity details into the database.
	 * here we have used prepared statemet to save the record.	 * 
	 *
	 * @param identity the identity
	 * @throws DaoSaveException the dao save exception
	 */	
	public void save(Identity identity) throws DaoSaveException {
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(
					"insert into IDENTITIES (IDENTITY_DISPLAYNAME, IDENTITY_EMAIL) values(?, ?)");

			preparedStatement.setString(1, identity.getDisplayName());
			preparedStatement.setString(2, identity.getEmail());
			preparedStatement.execute();
			preparedStatement.close();
			
		} catch (SQLException sqle) {
			logger.log(sqle.getMessage());
			DaoSaveException exception = new DaoSaveException();
			exception.initCause(sqle);
			exception.setFaultObject(identity);
			throw exception;
		}
	}

	
	/**
	 * *
	 * 
	 * This method is to search the database based on the given id.
	 *
	 * @param criteria the criteria
	 * @return the list
	 * @throws DaoSearchException the dao search exception
	 */	
	public List<Identity> search(Identity criteria) throws DaoSearchException {
		List<Identity> returnedList = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = this.connection
					.prepareStatement("SELECT * from IDENTITIES where  IDENTITY_UID=?");
			preparedStatement.setString(1, criteria.getUid());
			ResultSet results = preparedStatement.executeQuery();

			while (results.next()) {
				String displayName = results.getString("IDENTITY_DISPLAYNAME");
				String email = results.getString("IDENTITY_EMAIL");
				returnedList.add(new Identity(displayName, null, email));

			}
		} catch (SQLException sqle) {
			DaoSearchException daose = new DaoSearchException();
			daose.initCause(sqle);
			throw daose;
		}

		return returnedList;
	}

	/**
	 * this is releasing the database connection, so you should not use this
	 * instance of DAO anymore.
	 */
	public void releaseResources() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			logger.log(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.epita.iam.services.IdentityDAO#update(fr.epita.iam.datamodel.Identity)
	 */
	
	/**
	 * This method is to update the identity information based on the given id
	 * 
	 * Here first we try to search weather there is a record in the database exists with the given ID or not
	 * If exists then we will try to update otherwise we will throw an exception.
	 *
	 * @param identity the identity
	 * @throws DaoUpdateException the dao update exception
	 */
	@Override
	public void update(Identity identity) throws DaoUpdateException {

		try {
			//code for search
			List<Identity> searchResult = search(identity);
			if(searchResult==null || searchResult.isEmpty()){
				throw new DaoUpdateException("Unable to find the given Identity Id");
			}
			//code for update
			PreparedStatement preparedStatement = this.connection.prepareStatement(
					"update IDENTITIES set IDENTITY_DISPLAYNAME = ?, IDENTITY_EMAIL = ? where IDENTITY_UID=?");

			preparedStatement.setString(1, identity.getDisplayName());
			preparedStatement.setString(2, identity.getEmail());
			preparedStatement.setString(3, identity.getUid());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
		} catch (SQLException | DaoSearchException sqle) {
			logger.log(sqle.getMessage());
			DaoUpdateException exception = new DaoUpdateException();
			exception.initCause(sqle);
			throw exception;
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.epita.iam.services.IdentityDAO#delete(fr.epita.iam.datamodel.Identity)
	 */
	
	/**
	 * This method is to delete the idetity record based on the given ID
	 * 
	 * Here first we try to search weather there is a record in the database exists with the given ID or not
	 * If exists then we will try to delete otherwise we will throw an exception.
	 *
	 * @param identity the identity
	 * @throws DaoDeleteException the dao delete exception
	 */
	@Override
	public void delete(Identity identity) throws DaoDeleteException {

		try {
			//code for search 
			List<Identity> searchResult = search(identity);
			if(searchResult==null || searchResult.isEmpty()){
				throw new DaoDeleteException("Unable to find the given Identity Id");
			}
			//code for delete
			PreparedStatement preparedStatement = this.connection.prepareStatement(
					"delete from IDENTITIES where IDENTITY_UID=?");

			preparedStatement.setString(1, identity.getUid());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
		} catch (SQLException | DaoSearchException sqle) {
			logger.log(sqle.getMessage());
			DaoDeleteException exception = new DaoDeleteException();
			exception.initCause(sqle);
			throw exception;
		}
		
	}
}
