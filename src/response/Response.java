package response;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Response.
 */
public abstract class Response implements Serializable {
	
	/** The id. */
	private String id;
	
	/**
	 * Instantiates a new response.
	 *
	 * @param id the id
	 */
	public Response(String id) {
		this.id = id;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

}
