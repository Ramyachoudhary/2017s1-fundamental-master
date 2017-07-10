/**
 * 
 */
package fr.epita.iam.exceptions;

/**
 * The Class DaoSaveException.
 *
 * @author tbrou
 */
public class DaoSaveException extends Exception{

	/** The fault object. */
	private Object faultObject;
	
	/**
	 * Sets the fault object.
	 *
	 * @param obj the new fault object
	 */
	public void setFaultObject(Object obj){
		this.faultObject = obj;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return super.getMessage() + String.valueOf(this.faultObject);
	}
	
	
}
