package response;

// TODO: Auto-generated Javadoc
//I don't know if we actually need this class. I wrote it anyways

/**
 * The Class EliminatePlayerResponse.
 */
public class EliminatePlayerResponse extends Response {

	/** The id. */
	private String ID;
	
	/**
	 * Instantiates a new eliminate player response.
	 *
	 * @param id the id
	 */
	public EliminatePlayerResponse(String id) {
		super("eliminatePlayer");
		this.ID = id;
	}

	/**
	 * Gets the user ID.
	 *
	 * @return the user ID
	 */
	public String getUserID() {
		return this.ID;
	}
}
