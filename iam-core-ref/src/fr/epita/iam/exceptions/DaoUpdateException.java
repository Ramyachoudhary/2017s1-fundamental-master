/**
 * 
 */
package fr.epita.iam.exceptions;

/**
 * The Class DaoUpdateException.
 *
 * @author tbrou
 */
public class DaoUpdateException extends Exception{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The message. */
	private String message;	

	/**
	 * Instantiates a new dao update exception.
	 */
	public DaoUpdateException() {
		super();
	}
	
	/**
	 * Instantiates a new dao update exception.
	 *
	 * @param message the message
	 */
	public DaoUpdateException(String message) {
		super();
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return message+" \n "+super.getMessage();
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
