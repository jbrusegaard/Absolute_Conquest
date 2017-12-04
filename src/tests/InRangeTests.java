package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Move;
import player.PlayerProfile;

public class InRangeTests {

	private PlayerProfile p = new PlayerProfile();
	private Move mover = new Move(null);
	
	@Test
	public void testEast1() {
		p.setLocation("East");
		assertEquals(true, mover.inRange(6,7,p));
		
	}
	@Test
	public void testEast2() {
		p.setLocation("East");
		assertEquals(false, mover.inRange(6,6,p));
		
	}
	@Test
	public void testEast3() {
		p.setLocation("East");
		assertEquals(false, mover.inRange(5,7,p));
		
	}
	@Test
	public void testEast4() {
		p.setLocation("East");
		assertEquals(false, mover.inRange(7,7,p));
		
	}
	
	@Test
	public void testNorth1() {
		p.setLocation("North");
		assertEquals(true, mover.inRange(5,6,p));
		
	}
	@Test
	public void testNorth2() {
		p.setLocation("North");
		assertEquals(false, mover.inRange(6,6,p));
		
	}
	@Test
	public void testNorth3() {
		p.setLocation("North");
		assertEquals(false, mover.inRange(6,5,p));
	}
	@Test
	public void testNorth4() {
		p.setLocation("North");
		assertEquals(false, mover.inRange(6,7,p));
		
	}
	@Test
	public void testSouth1() {
		p.setLocation("South");
		assertEquals(true, mover.inRange(6,6,p));
		
	}
	@Test
	public void testSouth2() {
		p.setLocation("South");
		assertEquals(false, mover.inRange(6,7,p));
		
	}
	@Test
	public void testSouth3() {
		p.setLocation("South");
		assertEquals(false, mover.inRange(5,6,p));
		
	}
	@Test
	public void testSouth4() {
		p.setLocation("South");
		assertEquals(false, mover.inRange(6,5,p));
		
	}
	@Test
	public void testWest1() {
		p.setLocation("West");
		assertEquals(true, mover.inRange(6,5,p));
		
	}
	@Test
	public void testWest2() {
		p.setLocation("West");
		assertEquals(false, mover.inRange(5,6,p));
		
	}
	@Test
	public void testWest3() {
		p.setLocation("West");
		assertEquals(false, mover.inRange(6,6,p));
		
	}
	@Test
	public void testWest4() {
		p.setLocation("West");
		assertEquals(false, mover.inRange(6,7,p));
		
	}
}
