package game;

import javax.swing.JOptionPane;

import player.Card;

// TODO: Auto-generated Javadoc
/**
 * The Class TerrainInfo.
 *
 * @author Harry Mitchell
 */

public class TerrainInfo {
	
	/**
	 * Gets the terrain info.
	 *
	 * @param unit the unit
	 * @param terrain the terrain
	 * @return the terrain info
	 */
	/* This method is only called if the current player has moved a unit onto a valid tile
	 * u is the unit that is moving
	 * c is the card stored in the tile the unit is moving onto
	 * 
	 * returns true if the unit lives
	 * returns false if the unit dies
	 */
	public static boolean getTerrainInfo(Card unit, Card terrain) {
		String Terrain = terrain.getName();
		int hp = unit.getHealth();
		String uType = unit.getType();
		switch(Terrain) {
		case "Volcano":
			unit.move(2);
			unit.setHealth(hp - 50);
			return (unit.getHealth()>0);
		case "DefaultTerrain":
			unit.move(1);
			break;
		case "Forest":
			unit.move(2);
			break;
		case "Town":
			unit.move(1);
			hp = healHalf(hp, unit.getMaxHP());
			break;
		case "Capital":
			unit.move(1);
			break;
		case "Swamp":
			if(uType.equals("land")) {
				unit.move(2);
				unit.setHealth(hp - 25);
			} else {
				unit.move(1);
			}
			break;
		case "Mountains":
			if(!uType.equals("Air")) {unit.endMove();}
			else {unit.move(1);}
			break;
		case "Desert":
			if(uType.equals("Aquatic")) {
				unit.move(1);
			}
			unit.move(1);
			break;
		case "Roads":
			if(!uType.equals("Land")) {unit.move(1);}
			break;
		case "River":
			if(!uType.equals("Aquatic")) {unit.move(1);}
			break;
		case "Lake":
			if(uType.equals("Land")) {
				unit.setHealth(0);
				//JOptionPane.showMessageDialog(null, unit.getName()+" sinks in the lake.\nWhy did they think moving a "+unit.getName()+" into a lake was a good idea?");
				return false;
			}
			else if(uType.equals("Air")) {unit.move(1);}
			break;
		case "Arctic":
			unit.move(2);
			hp -= 25;
			unit.setHealth(hp);
			if(hp<=0) {return false;}
			break;
		case "Farms":
			unit.move(1);
			hp = healHalf(hp, unit.getMaxHP());
			unit.setHealth(hp);
			break;
		}
		return true;
	}
	
	/*This method is only called if the current player has moved a unit onto a valid tile
	 * u is the unit that is moving
	 * c is the card stored in the tile the unit is moving onto
	 * 
	 * returns true if the unit lives
	 * returns false if the unit dies
	 */
	public static boolean getTrapInfo(Card unit, Card trap) {
		String Trap = trap.getName();
		int hp = unit.getHealth();
		switch(Trap){
		case "Locusts":
		case "PitfallLand":
			hp -= trap.getAttack();
			unit.endMove();
//			JOptionPane.showMessageDialog(null, "You set off a "+Trap+" and take 25 damage!");
			break;
		case "Avalanche":
		case "Flood":
		case "Mine":
		case "Eruption":
		case "WildFire":
		case "Plague":
			hp -= trap.getAttack();
//			JOptionPane.showMessageDialog(null, "You set off a "+Trap+" and take "+ trap.getAttack() + " damage!");
			break;
		}
		if(hp<=0) {return false;}
		return true;
	}
	
	/**
	 * Place trap val.
	 *
	 * @param Trap the trap
	 * @param Terrain the terrain
	 * @return true, if successful
	 */
	/* Trap is the card of type trap
	 * Terrain is a card of type Terrain
	 * 
	 * This methods validates if a trap card can be placed on a terrain card
	 */
	public static boolean placeTrapVal(Card Trap, Card Terrain) {
		String Trapn = Trap.getName();
		String TerrainN = Terrain.getName();
		switch(Trapn) {
		case "PitfallLand":
			if(TerrainN.equals("DefaultTerrain")||TerrainN.equals("Forest")||TerrainN.equals("Roads")||TerrainN.equals("Arctic")) {
				return true;
			}
			break;
		case "Avalanche":
			if(TerrainN.equals("Mountains")) {
				return true;
			}
			break;
		case "Flood":
			if(TerrainN.equals("River") || TerrainN.equals("Swamp") || TerrainN.equals("Lake")) {
				return true;
			}
			break;
		case "Mine":
			if(!(TerrainN.equals("Volcano") || TerrainN.equals("Town") || TerrainN.equals("Capital"))) {
				return true;
			}
			break;
		case "Locusts":
			if(TerrainN.equals("Farms")) {
				return true;
			}
			break;
		case "Eruption":
			if(TerrainN.equals("Volcano")) {
				return true;
			}
			break;
		case "WildFire":
			if(TerrainN.equals("Forest")) {
				return true;
			}
			break;
		case "Plague":
			if(TerrainN.equals("Town")) {
				return true;
			}
			break;
		}
		//JOptionPane.showMessageDialog(null, "You can't place a "+Trapn+" in a "+TerrainN);
		return false;
	}
	
	/**
	 * Heal half.
	 *
	 * @param h the h
	 * @param m the m
	 * @return the int
	 */
	//This method heals a unit for half of its max hp
	private static int healHalf(int h, int m) {
		h += m*.5;
		if(h>m) {h = m;}
		return h;
	}
}