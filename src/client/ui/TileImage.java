package client.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.Tile;
import player.Card;
import player.PlayerProfile;
import tests.Images;

// TODO: Auto-generated Javadoc
/**
 * The Class TileImage.
 */
public class TileImage {
	
	/** The tile. */
	Tile tile;
	
	/** The update toggle. */
	boolean updateToggle = false;

	/**
	 * Instantiates a new tile image.
	 */
	TileImage() {
		if(Images.YellowDefaultTerrainImage == null) {
				Images.loadImages();
		}
	}
	
	/**
	 * Instantiates a new tile image.
	 *
	 * @param s the s
	 */
	TileImage(String s) {
		this();
	}
	
	/**
	 * Gets the image.
	 *
	 * @param t the t
	 * @param players the players
	 * @param player the player
	 * @return the image
	 */
	public BufferedImage getImage(Tile t, ArrayList<PlayerProfile> players, PlayerProfile player) {
		BufferedImage fullImage = new BufferedImage(64, 55, BufferedImage.TYPE_INT_ARGB);
		
		if(t.getTerrainCard() != null)
			fullImage.getGraphics().drawImage(cardToTileLookup(t.getTerrainCard().getName(), t.getTerrainCard().getName(), t.getLocation()), 0, 0, null);
		if(t.getTrapCard() != null) {
			if(t.getTrapCard().getOwnedBy().equals(player.getUserID()) || getPlayerZone(player.getUserID(), players).equals(""))
				fullImage.getGraphics().drawImage(cardToTileLookup(t.getTrapCard().getName(), t.getTerrainCard().getName(), t.getLocation()), 0, 0, null);
		}
		if(t.getUnitCard() != null) {
			String playerZone = getPlayerZone(t.getUnitCard().getOwnedBy(), players);
			switch(playerZone) {
			case "North":
				fullImage.getGraphics().drawImage(Images.YellowUnitHighlightTile, 0, 0, null);
				break;
			case "East":
				fullImage.getGraphics().drawImage(Images.BlueUnitHighlightTile, 0, 0, null);
				break;
			case "South":
				fullImage.getGraphics().drawImage(Images.RedUnitHighlightTile, 0, 0, null);
				break;
			case "West":
				fullImage.getGraphics().drawImage(Images.GreenUnitHighlightTile, 0, 0, null);
				break;
			}
			fullImage.getGraphics().drawImage(cardToTileLookup(t.getUnitCard().getName(), t.getTerrainCard().getName(), t.getLocation()), 0, 0, null);
		}

		
		//Images.setBorderImage(t, fullImage);

		
		return fullImage;
	}

	/**
	 * Gets the player zone.
	 *
	 * @param ownedBy the owned by
	 * @param players the players
	 * @return the player zone
	 */
	private String getPlayerZone(String ownedBy, ArrayList<PlayerProfile> players) {
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getUserID().equals(ownedBy))
				return players.get(i).getLocation();
		}
		return "";
	}

	/**
	 * Gets the update toggle.
	 *
	 * @return the update toggle
	 */
	public boolean getUpdateToggle() {
		return updateToggle;
	}
	
	/**
	 * Sets the update toggle.
	 *
	 * @param updateToggle the new update toggle
	 */
	public void setUpdateToggle(boolean updateToggle) {
		this.updateToggle = updateToggle;
	}
	
/*	public BufferedImage getTroopImage() {
		return troopImage;
	}

	public void setTroopImage(BufferedImage troopImage) {
		this.troopImage = troopImage;
	}

	public BufferedImage getTerrainImage() {
		return terrainImage;
	}

	public void setTerrainImage(BufferedImage terrainImage) {
		this.terrainImage = terrainImage;
	}*/


	
	/**
 * Card to tile lookup.
 *
 * @param cardName the card name
 * @param terrainName the terrain name
 * @param location the location
 * @return the buffered image
 */
public BufferedImage cardToTileLookup(String cardName, String terrainName, String location) {
		
		if(cardName.equals("Volcano"))
			return Images.VolcanoImage;
		if(cardName.equals("Helicopter"))
			return Images.HelicopterImage;
		if(cardName.equals("Dragon"))
			return Images.DragonImage;
		if(cardName.equals("BigDaddy"))
			return Images.BigDaddyImage;
		if(cardName.equals("DefaultTerrain")) {
			if(location == null)
				return Images.InvalidTerrainImage;
			if(location.equals("North"))
				return Images.YellowDefaultTerrainImage;
			if(location.equals("East"))
				return Images.BlueDefaultTerrainImage;
			if(location.equals("South"))
				return Images.RedDefaultTerrainImage;
			if(location.equals("West"))
				return Images.GreenDefaultTerrainImage;
		}
		if(cardName.equals("Noob"))
			return Images.NoobImage;
		if(cardName.equals("Tank"))
			return Images.TankImage;
		if(cardName.equals("PitfallLand")) {
			if(terrainName.equals("Arctic"))
				return Images.ArcticPitfallLandImage;
			if(terrainName.equals("Forest")) 
				return Images.ForestPitfallLandImage;
			if(terrainName.equals("Roads")) 
				return Images.RoadsPitfallLandImage;
			else
				return Images.PitfallLandImage;
			}
			
		if(cardName.equals("Avalanche"))
			return Images.AvalancheImage;
		if(cardName.equals("Forest"))
			return Images.ForestImage;
		if(cardName.equals("Town"))
			return Images.TownImage;
		if(cardName.equals("Capital"))
			return Images.CapitalImage;
		if(cardName.equals("Swamp"))
			return Images.SwampImage;
		if(cardName.equals("Mountains"))
			return Images.MountainsImage;
		if(cardName.equals("Desert"))
			return Images.DesertImage;
		if(cardName.equals("Roads"))
			return Images.RoadsImage;
		if(cardName.equals("River"))
			return Images.RiverImage;
		if(cardName.equals("Lake"))
			return Images.LakeImage;
		if(cardName.equals("Arctic"))
			return Images.ArcticImage;
		if(cardName.equals("Farms"))
			return Images.FarmsImage;
		if(cardName.equals("Flood")) {
			if(terrainName.equals("Swamp"))
				return Images.SwampFloodImage;
			if(terrainName.equals("River")) {
				return Images.RiverFloodImage;
			}
		}
		if(cardName.equals("Ship"))
			return Images.ShipImage;
		if(cardName.equals("Mine"))
			return Images.MineImage;
		if(cardName.equals("Locusts"))
			return Images.LocustsImage;
		if(cardName.equals("Eruption"))
			return Images.EruptionImage;
		if(cardName.equals("WildFire"))
			return Images.WildFireImage;
		if(cardName.equals("Plague"))
			return Images.PlagueImage;
		if(cardName.equals("InvalidTerrain"))
			return Images.InvalidTerrainImage;
	
		return Images.InvalidTerrainImage;
	}


}
