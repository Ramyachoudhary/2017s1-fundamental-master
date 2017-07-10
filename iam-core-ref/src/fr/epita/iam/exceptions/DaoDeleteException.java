/**
 * 
 */
package fr.epita.iam.exceptions;

/**
 * The Class DaoDeleteException.
 *
 * @author Ramya
 */
public class DaoDeleteException extends Exception{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The message. */
	private String message;		
	
	/**
	 * Instantiates a new dao delete exception.
	 */
	public DaoDeleteException() {
		super();
	}

	/**
	 * Instantiates a new dao delete exception.
	 *
	 * @param message the message
	 */
	public DaoDeleteException(String message) {
		super();
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return message;
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
