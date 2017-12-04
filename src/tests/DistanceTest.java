package tests;

import game.Game;

// TODO: Auto-generated Javadoc
/**
 * The Class DistanceTest.
 */
public class DistanceTest {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]) {
		
		System.out.println(Game.getDistance(4,4,5,7));
		System.out.println(Game.getDistance(8,4,8,5));
		System.out.println(Game.getDistance(8,5,7,6));
		System.out.println(Game.getDistance(5,4,6,3));
		System.out.println(Game.getDistance(5,4,7,4));
	}
}
