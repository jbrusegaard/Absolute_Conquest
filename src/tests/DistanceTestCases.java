package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Game;

public class DistanceTestCases {

	@Test
	public void test() {
		int i = Game.getDistance(4, 4, 5, 7);
		assertEquals(3,i);
	}
	@Test
	public void neighborTest()
	{
		int i = Game.getDistance(8, 1, 7, 1);
		assertEquals(1,i);
	}
	@Test
	public void diagonalNeighborTest()
	{
		int i = Game.getDistance(8, 1, 7, 0);
		assertEquals(1,i);
	}
	@Test
	public void sameTileDistance()
	{
		int i = Game.getDistance(8, 0, 8, 0);
		assertEquals(0,i);
	}
	@Test
	public void test2()
	{
		int i = Game.getDistance(6, 0, 5, 6);
		assertEquals(6,i);
	}
	@Test
	public void test3()
	{
		int i = Game.getDistance(6, 0, 0, 5);
		assertEquals(9, i);
	}
	
}
