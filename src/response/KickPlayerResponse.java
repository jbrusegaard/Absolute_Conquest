package response;

// TODO: Auto-generated Javadoc
//I don't know if we actually need this class. I wrote it anyways

/**
 * The Class KickPlayerResponse.
 */
public class KickPlayerResponse extends Response {

	/** The id. */
	private String ID;
	
	/**
	 * Instantiates a new kick player response.
	 *
	 * @param id the id
	 */
	public KickPlayerResponse(String id) {
		super("kickPlayer");
		this.ID = id;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getID() {
		return this.ID;
	}
}
