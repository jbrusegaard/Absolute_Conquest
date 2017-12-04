package request;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Request.
 */
public abstract class Request implements Serializable{

		/** The id. */
		public String id;
		
		/**
		 * Instantiates a new request.
		 *
		 * @param id the id
		 */
		public Request(String id) {
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
