/**
 * 
 */
package fr.epita.iam.datamodel;

/**
 * The Class Identity.
 *
 * @author tbrou
 */
public class Identity {
	
	/** The display name. */
	private String displayName;
	
	/** The uid. */
	private String uid;
	
	/** The email. */
	private String email;
	
	
	/**
	 * Instantiates a new identity.
	 *
	 * @param displayName the display name
	 * @param uid the uid
	 * @param email the email
	 */
	public Identity(String displayName, String uid, String email) {
		this.displayName = displayName;
		this.uid = uid;
		this.email = email;
	}
	
	
	/**
	 * Gets the display name.
	 *
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	/**
	 * Sets the display name.
	 *
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	/**
	 * Gets the uid.
	 *
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	
	/**
	 * Sets the uid.
	 *
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Identity [displayName=" + displayName + ", uid=" + uid + ", email=" + email + "]";
	}
	

	
	

}
