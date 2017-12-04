package tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.ClientNetwork;
import game.Game;
import game.Tile;
import player.Card;
import player.PlayerProfile;

public class GameBoardTests {

	
	
	private Game game;
	
	public Tile[][] initGame(){
		ClientNetwork.connect();
		game = new Game(12, 13, new PlayerProfile(), new PlayerProfile(), new PlayerProfile(),
				new PlayerProfile(), "gameID", ClientNetwork.getCardLibraryMap()); 
		return game.getGameboard();
	}
	
	private Tile[][] gb = initGame();
	
	@Test
	public void initTest() {
		boolean test = true;
		for(int i = 0; i < 12; i++)
		{
			for(int j = 0; j< 13; j++)
			{
				if(gb[i][j].getTerrainCard() == null)
					test = false;
				if(gb[i][j].getTrapCard()!= null)
					test = false;
				if(gb[i][j].getUnitCard() != null)
					test = false;
			}
		}
		assertEquals(true, test);
	}
	@Test
	public void townTest()
	{
		boolean test = true;
		
		if(!(gb[3][5].getTerrainCard().getName().equals("Town")))
			test = false;
		if(!(gb[3][7].getTerrainCard().getName().equals("Town")))
			test = false;
		if(!(gb[5][9].getTerrainCard().getName().equals("Town")))
			test = false;
		if(!(gb[7][9].getTerrainCard().getName().equals("Town")))
			test = false;
		if(!(gb[3][5].getTerrainCard().getName().equals("Town")))
			test = false;
		if(!(gb[9][5].getTerrainCard().getName().equals("Town")))
			test = false;
		if(!(gb[9][7].getTerrainCard().getName().equals("Town")))
			test = false;
		if(!(gb[5][3].getTerrainCard().getName().equals("Town")))
			test = false;
		if(!(gb[7][3].getTerrainCard().getName().equals("Town")))
			test = false;
		assertEquals(true, test);
	}
}
