package tests;

import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import client.ClientNetwork;
import game.TerrainInfo;
import player.Card;

public class TerrainTest {

	private static HashMap<String, Card> cards;
	
	@Before
	public void asdf() {
		ClientNetwork.connect();
		cards = ClientNetwork.getCardLibraryMap();
	}
	
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void testHealingTerrain() throws Exception{
		Card hn = cards.get("Noob");
		hn.setHealth(1);
		Card farm = cards.get("Farms");
		boolean flag = TerrainInfo.getTerrainInfo(hn, farm);
		if(!flag) {
			fail("Noob was killed when moving onto farm");
		}
		if(hn.getHealth() != (hn.getMaxHP()/2)+1) {
			System.out.println(hn.getHealth());
			System.out.println((hn.getMaxHP()/2)+1);
			fail("Noob doesn't have the correct amount of health");
		}
	}
	
	@Test
	public void testDamagingTerrain() throws Exception{
		Card hn = cards.get("Noob");
		Card vol = cards.get("Volcano");
		boolean flag = TerrainInfo.getTerrainInfo(hn, vol);
		if(flag) {fail("Volcano didn't kill the noob");}
		Card tank = cards.get("Tank");
		flag = TerrainInfo.getTerrainInfo(tank, vol);
		if(!flag) {fail("Volcano killed the tank");}
	}
	
	@Test
	public void testTrapDamage() throws Exception{
		Card hn = cards.get("Noob");
		Card mine = cards.get("Mine");
		boolean flag = TerrainInfo.getTrapInfo(hn, mine);
		if(flag) {fail("Mine didn't kill NOOb");}
		Card tank = cards.get("Tank");
		Card pf = cards.get("PitfallLand");
		flag = TerrainInfo.getTerrainInfo(tank, pf);
		if(!flag) {fail("Pitfall killed a full health tank");}
	}
	
	@Test
	public void testTrapPlacement() throws Exception{
		Card pf = cards.get("PitfallLand");
		Card dt = cards.get("DefaultTerrain");
		boolean flag = TerrainInfo.placeTrapVal(pf, dt);
		if(!flag) {fail("PitFall couldn't be placed on defualt Terrain");}
		Card vol = cards.get("Volcano");
		flag = TerrainInfo.placeTrapVal(pf, vol);
		if(flag) {fail("PitFall was placed in a volcano");}
	}

}
