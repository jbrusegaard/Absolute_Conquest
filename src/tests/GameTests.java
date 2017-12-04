package tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import client.ClientNetwork;
import game.Game;
import game.Tile;
import player.Card;
import player.PlayerProfile;
import server.ServerLauncher;

// TODO: Auto-generated Javadoc
/**
 * The Class GameTests.
 */
public class GameTests {
	private Game game;
	private PlayerProfile p1,p2,p3,p4;
	private HashMap<String, Card> cardLib;
	/**
	 * Inits the games.
	 */
	@Before
	public void initGames() {
//		ServerLauncher.main(null);
		ClientNetwork.connect();
		p1 = new PlayerProfile();
		p2 = new PlayerProfile();
		p3 = new PlayerProfile();
		p4 = new PlayerProfile();
		p1.setUserID("1");
		p2.setUserID("2");
		p3.setUserID("3");
		p4.setUserID("4");
		cardLib = ClientNetwork.getCardLibraryMap();
		game = new Game(12, 13, p1, p2, p3, p4, "69",cardLib);
	}
	
	/**
	 * Test 1.
	 */
	@Test
	public void testCombat1() {
		game.addCardToTile(7, 1, "Noob", p1.getUserID());
		game.addCardToTile(6, 2, "Tank", p2.getUserID());
		game.doCombatBetweenUnits(7, 1, 6, 2);
		Assert.assertNull(this.game.getGameboard()[7][1].getUnitCard());
		Assert.assertTrue(this.game.getGameboard()[6][2].getUnitCard().getHealth() == (cardLib.get("Tank").getHealth() - cardLib.get("Noob").getAttack()));
		
	}
	@Test
	public void rangeCombatTest() {
		game.addCardToTile(10, 5, "Noob", p1.getUserID());
		game.addCardToTile(9, 7, "Tank", p2.getUserID());
		game.moveUnitAndAttack(10, 5, 9, 7, p2.getUserID());
		Assert.assertNull(this.game.getGameboard()[10][5].getUnitCard());
		Assert.assertTrue(this.game.getGameboard()[9][7].getUnitCard().getHealth() == cardLib.get("Tank").getHealth());
	}
	@Test
	public void rangeCombatTest2() {
		game.addCardToTile(10, 5, "Tank", p1.getUserID());
		game.addCardToTile(9, 7, "Tank", p2.getUserID());
		game.moveUnitAndAttack(10, 5, 9, 7, p2.getUserID());
		Assert.assertNotNull(this.game.getGameboard()[10][5].getUnitCard());
		Assert.assertTrue(this.game.getGameboard()[9][7].getUnitCard().getHealth() == (cardLib.get("Tank").getHealth() - cardLib.get("Tank").getAttack()));
	}
	@Test
	public void trapCombat() {
		game.addCardToTile(7, 1, "Noob", p1.getUserID());
		game.addCardToTile(7, 1, "Mine", p1.getUserID());
		game.addCardToTile(6, 2, "Tank", p2.getUserID());
		game.moveUnitAndAttack(7, 1, 6, 2, p2.getUserID());
		Assert.assertNull(this.game.getGameboard()[6][2].getUnitCard());
		Assert.assertNull(this.game.getGameboard()[7][1].getTrapCard());
		Assert.assertTrue(this.game.getGameboard()[7][1].getUnitCard().getHealth() == cardLib.get("Noob").getMaxHP());
	}
}
