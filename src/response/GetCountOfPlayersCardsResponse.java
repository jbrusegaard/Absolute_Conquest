package response;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class GetCountOfPlayersCardsResponse.
 */
public class GetCountOfPlayersCardsResponse extends Response implements Serializable {

		/** The count. */
		private int count;
		
		/**
		 * Instantiates a new gets the count of players cards response.
		 *
		 * @param count the count
		 */
		public GetCountOfPlayersCardsResponse(int count){
			super("getCountOfPlayerCards");
			this.count = count;
		}

		/**
		 * Gets the count.
		 *
		 * @return the count
		 */
		public int getCount() {
			return count;
		}

		/**
		 * Sets the count.
		 *
		 * @param count the new count
		 */
		public void setCount(int count) {
			this.count = count;
		}
}
