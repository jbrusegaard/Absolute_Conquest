package response;


import player.PlayerProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class SendEndGameScoresResponse.
 */
public class SendEndGameScoresResponse extends Response {

	/** The loser 3. */
	private PlayerProfile winner,loser1,loser2,loser3;
	
	/**
	 * Instantiates a new send end game scores response.
	 *
	 * @param winner the winner
	 * @param loser1 the loser 1
	 * @param loser2 the loser 2
	 * @param loser3 the loser 3
	 */
	public SendEndGameScoresResponse(PlayerProfile winner,PlayerProfile loser1,PlayerProfile loser2,PlayerProfile loser3) {
		super("sendScores");
		this.winner = winner;
		this.loser1 = loser1;
		this.loser2 = loser2;
		this.loser3 = loser3;
	}

	/**
	 * Gets the score winner.
	 *
	 * @return the score winner
	 */
	public int getScoreWinner() {
		return this.winner.getPoints();
	}
	
	/**
	 * Gets the score loser 1.
	 *
	 * @return the score loser 1
	 */
	public int getScoreLoser1() {
		return this.loser1.getPoints();
	}
	
	/**
	 * Gets the score loser 2.
	 *
	 * @return the score loser 2
	 */
	public int getScoreLoser2() {
		return this.loser2.getPoints();
	}
	
	/**
	 * Gets the score loser 3.
	 *
	 * @return the score loser 3
	 */
	public int getScoreLoser3() {
		return this.loser3.getPoints();
	}
}
