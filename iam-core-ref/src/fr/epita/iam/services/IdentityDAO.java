/**
 * 
 */
package fr.epita.iam.services;

import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.DaoDeleteException;
import fr.epita.iam.exceptions.DaoSaveException;
import fr.epita.iam.exceptions.DaoSearchException;
import fr.epita.iam.exceptions.DaoUpdateException;

/**
 * The Interface IdentityDAO.
 *
 * @author Ramya
 */
public interface IdentityDAO {

	/**
	 * Save.
	 *
	 * @param identity the identity
	 * @throws DaoSaveException the dao save exception
	 */
	public void save(Identity identity) throws DaoSaveException;
	
	/**
	 * Update.
	 *
	 * @param identity the identity
	 * @throws DaoUpdateException the dao update exception
	 */
	public void update(Identity identity) throws DaoUpdateException; 
	
	/**
	 * Delete.
	 *
	 * @param identity the identity
	 * @throws DaoDeleteException the dao delete exception
	 */
	public void delete(Identity identity) throws DaoDeleteException;
	
	/**
	 * Search.
	 *
	 * @param criteria the criteria
	 * @return the list
	 * @throws DaoSearchException the dao search exception
	 */
	public List<Identity> search(Identity criteria) throws DaoSearchException;
	
	/**
	 * Release resources.
	 */
	public void releaseResources();
	
	
	
}
