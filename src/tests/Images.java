package tests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import client.ui.TileImage;
import game.Tile;

// TODO: Auto-generated Javadoc
/**
 * The Class Images.
 */
public class Images {

	
	/** The Volcano image. */
	public static BufferedImage VolcanoImage;
	
	/** The Helicopter image. */
	public static BufferedImage HelicopterImage;
	
	/** The Dragon image. */
	public static BufferedImage DragonImage;
	
	/** The Big daddy image. */
	public static BufferedImage BigDaddyImage;
	
	/** The Yellow default terrain image. */
	public static BufferedImage YellowDefaultTerrainImage;
	
	/** The Blue default terrain image. */
	public static BufferedImage BlueDefaultTerrainImage;
	
	/** The Red default terrain image. */
	public static BufferedImage RedDefaultTerrainImage;
	
	/** The Green default terrain image. */
	public static BufferedImage GreenDefaultTerrainImage;
	
	/** The Noob image. */
	public static BufferedImage NoobImage;
	
	/** The Tank image. */
	public static BufferedImage TankImage;
	
	/** The Pitfall land image. */
	public static BufferedImage PitfallLandImage;
	
	/** The Forest pitfall land image. */
	public static BufferedImage ForestPitfallLandImage;;
	
	/** The Arctic pitfall land image. */
	public static BufferedImage ArcticPitfallLandImage;
	
	/** The Roads pitfall land image. */
	public static BufferedImage RoadsPitfallLandImage;
	
	/** The Avalanche image. */
	public static BufferedImage AvalancheImage;
	
	/** The Forest image. */
	public static BufferedImage ForestImage;
	
	/** The Town image. */
	public static BufferedImage TownImage;
	
	/** The Capital image. */
	public static BufferedImage CapitalImage;
	
	/** The Swamp image. */
	public static BufferedImage SwampImage;
	
	/** The Mountains image. */
	public static BufferedImage MountainsImage;
	
	/** The Desert image. */
	public static BufferedImage DesertImage;
	
	/** The Roads image. */
	public static BufferedImage RoadsImage;
	
	/** The River image. */
	public static BufferedImage RiverImage;
	
	/** The Lake image. */
	public static BufferedImage LakeImage;
	
	/** The Arctic image. */
	public static BufferedImage ArcticImage;
	
	/** The Farms image. */
	public static BufferedImage FarmsImage;
	
	/** The River flood image. */
	public static BufferedImage RiverFloodImage;
	
	/** The Swamp flood image. */
	public static BufferedImage SwampFloodImage;
	
	/** The Ship image. */
	public static BufferedImage ShipImage;
	
	/** The Mine image. */
	public static BufferedImage MineImage;
	
	/** The Locusts image. */
	public static BufferedImage LocustsImage;
	
	/** The Eruption image. */
	public static BufferedImage EruptionImage;
	
	/** The Wild fire image. */
	public static BufferedImage WildFireImage;
	
	/** The Plague image. */
	public static BufferedImage PlagueImage;
	
	/** The Invalid terrain image. */
	public static BufferedImage InvalidTerrainImage; 
	
	/** The Blue highlights image. */
	public static BufferedImage BlueHighlightsImage;
	
	/** The Yellow highlights image. */
	public static BufferedImage YellowHighlightsImage;
	
	/** The Red highlights image. */
	public static BufferedImage RedHighlightsImage;
	
	/** The Green highlights image. */
	public static BufferedImage GreenHighlightsImage;
	
	/** The Top black border image. */
	public static BufferedImage TopBlackBorderImage;
	
	/** The Top right black border image. */
	public static BufferedImage TopRightBlackBorderImage;
	
	/** The Top left black border image. */
	public static BufferedImage TopLeftBlackBorderImage;
	
	/** The Bottom black border image. */
	public static BufferedImage BottomBlackBorderImage;
	
	/** The Bottom right black border image. */
	public static BufferedImage BottomRightBlackBorderImage;
	
	/** The Bottom left black border image. */
	public static BufferedImage BottomLeftBlackBorderImage;
	
	/** The Red unit highlight tile. */
	public static BufferedImage RedUnitHighlightTile;
	
	/** The Green unit highlight tile. */
	public static BufferedImage GreenUnitHighlightTile;
	
	/** The Blue unit highlight tile. */
	public static BufferedImage BlueUnitHighlightTile;
	
	/** The Yellow unit highlight tile. */
	public static BufferedImage YellowUnitHighlightTile;
	
	/**
	 * Sets the border image.
	 *
	 * @param t the t
	 * @param image the image
	 */
	public static void setBorderImage(Tile t, BufferedImage image) {
		
		//Yellow Border
		if(t.getRow() == 0 && t.getColumn() == 6) {
			image.getGraphics().drawImage(YellowHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
		}
		else if((t.getRow() == 1 && (t.getColumn() == 5 || t.getColumn() == 4)) || (t.getRow() == 2 && t.getColumn() == 3)) {
			image.getGraphics().drawImage(YellowHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
		}
		else if(t.getRow() == 2 && t.getColumn() == 2) {
			image.getGraphics().drawImage(YellowHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
		}
		else if((t.getRow() == 3 && (t.getColumn() == 3 || t.getColumn() == 4))) {
			image.getGraphics().drawImage(YellowHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
		}
		else if(t.getRow() == 4 && t.getColumn() == 5) {
			image.getGraphics().drawImage(YellowHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
		}
		else if(t.getRow() == 5 && t.getColumn() == 5) {
			image.getGraphics().drawImage(YellowHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
		}
		else if(t.getRow() == 5 && t.getColumn() == 6) {
			image.getGraphics().drawImage(YellowHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
		}
		
		else if((t.getRow() == 1 && (t.getColumn() == 7 || t.getColumn() == 8)) || (t.getRow() == 2 && t.getColumn() == 9)) {
			image.getGraphics().drawImage(YellowHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
		}
		else if(t.getRow() == 2 && t.getColumn() == 10) {
			image.getGraphics().drawImage(YellowHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
		}
		else if((t.getRow() == 3 && (t.getColumn() == 9 || t.getColumn() == 8))) {
			image.getGraphics().drawImage(YellowHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
		}
		else if(t.getRow() == 4 && t.getColumn() == 7) {
			image.getGraphics().drawImage(YellowHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
		}
		else if(t.getRow() == 5 && t.getColumn() == 7) {
			image.getGraphics().drawImage(YellowHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
		}
		
		//Red Border
		if(t.getRow() == 11 && t.getColumn() == 6) {
			image.getGraphics().drawImage(RedHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
		}
		else if((t.getRow() == 11 && (t.getColumn() == 5)) || (t.getRow() == 10 && (t.getColumn() == 3|| t.getColumn() == 4))) {
			image.getGraphics().drawImage(RedHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
		}
		else if(t.getRow() == 9 && t.getColumn() == 2) {
			image.getGraphics().drawImage(RedHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
		}
		else if((t.getRow() == 9 && t.getColumn() == 3) || (t.getRow() == 8 && t.getColumn() == 4)) {
			image.getGraphics().drawImage(RedHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
		}
		else if(t.getRow() == 8 && t.getColumn() == 5) {
			image.getGraphics().drawImage(RedHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
		}
		else if(t.getRow() == 7 && t.getColumn() == 5) {
			image.getGraphics().drawImage(RedHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
		}
		else if(t.getRow() == 6 && t.getColumn() == 6) {
			image.getGraphics().drawImage(RedHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
		}
		
		else if((t.getRow() == 10 && (t.getColumn() == 8 || t.getColumn() == 9)) || (t.getRow() == 11 && (t.getColumn() == 7 ))) {
			image.getGraphics().drawImage(RedHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
		}
		else if(t.getRow() == 9 && t.getColumn() == 10) {
			image.getGraphics().drawImage(RedHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
		}
		else if((t.getRow() == 8 && (t.getColumn() == 8)) || (t.getRow() == 9 && (t.getColumn() == 9))) {
			image.getGraphics().drawImage(RedHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
		}
		else if(t.getRow() == 8 && t.getColumn() == 7) {
			image.getGraphics().drawImage(RedHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
		}
		else if(t.getRow() == 7 && t.getColumn() == 7) {
			image.getGraphics().drawImage(RedHighlightsImage, 0, 0, null);
			image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
			image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
		}
		
		//green boarder
				if(t.getColumn() ==0 && (t.getRow() == 3 || t.getRow() == 8))
				{
					image.getGraphics().drawImage(GreenHighlightsImage, 0, 0, null);
					image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
					if(t.getRow() == 3)
					{
						image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
					}
					else image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
				}
				else if(t.getColumn() ==0 && (t.getRow() > 3 && t.getRow() <8 ))
				{
					image.getGraphics().drawImage(GreenHighlightsImage, 0, 0, null);
					image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
				}
				else if((t.getColumn() == 1) && ((t.getRow() == 3)||(t.getRow() == 9)))
				{
					image.getGraphics().drawImage(GreenHighlightsImage, 0, 0, null);
					if(t.getRow() == 3) {
						image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
						image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
						image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
					}
					else {
						image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
						image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
						image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
					}
				}
				else if((t.getColumn() == 2 && t.getRow() == 3)||(t.getRow() == 4 && t.getColumn() == 3))
				{
					image.getGraphics().drawImage(GreenHighlightsImage, 0, 0, null);
					image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
				}
				else if((t.getRow() == 8 && t.getColumn() == 2)||(t.getRow() == 8 &&t.getColumn() == 3))
				{
					image.getGraphics().drawImage(GreenHighlightsImage, 0, 0, null);
					image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
					
				}
				else if(t.getRow() == 7 && t.getColumn() == 4)
				{
					image.getGraphics().drawImage(GreenHighlightsImage, 0, 0, null);
					image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
				}
				else if(t.getRow() == 4 && t.getColumn() == 4)
				{
					image.getGraphics().drawImage(GreenHighlightsImage, 0, 0, null);
					image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
				}
				else if(t.getColumn() == 4 &&(t.getRow() >5 && t.getRow() < 7 ))
				{
					image.getGraphics().drawImage(GreenHighlightsImage, 0, 0, null);
					image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
					
					if (t.getRow() == 5)
					{
						image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
					}
					else image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
				}
				else if(t.getRow() == 6 && t.getColumn() == 5)
				{
					image.getGraphics().drawImage(GreenHighlightsImage, 0, 0, null);
					image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
				}
				
				//Blue boarder
				if(t.getColumn() ==12 && (t.getRow() == 3 || t.getRow() == 8))
				{
					image.getGraphics().drawImage(BlueHighlightsImage, 0, 0, null);
					image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
					if(t.getRow() == 3)
					{
						image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
					}
					else image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
				}
				else if(t.getColumn() ==12 && (t.getRow() > 3 && t.getRow() <8 ))
				{
					image.getGraphics().drawImage(BlueHighlightsImage, 0, 0, null);
					image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
				}
				else if((t.getColumn() == 11) && ((t.getRow() == 3)||(t.getRow() == 9)))
				{
					image.getGraphics().drawImage(BlueHighlightsImage, 0, 0, null);
					if(t.getRow() == 3) {
						image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
						image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
						image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
					}
					else {
						image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
						image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
						image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
					}
				}
				else if((t.getColumn() == 10 && t.getRow() == 3)||(t.getRow() == 4 && t.getColumn() == 9))
				{
					image.getGraphics().drawImage(BlueHighlightsImage, 0, 0, null);
					image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
				}
				else if((t.getRow() == 8 && t.getColumn() == 10)||(t.getRow() == 8 &&t.getColumn() == 9))
				{
					image.getGraphics().drawImage(BlueHighlightsImage, 0, 0, null);
					image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
					
				}
				else if(t.getRow() == 4 && t.getColumn() == 8)
				{
					image.getGraphics().drawImage(BlueHighlightsImage, 0, 0, null);
					image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
				}
				else if(t.getRow() == 7 && t.getColumn() == 8)
				{
					image.getGraphics().drawImage(BlueHighlightsImage, 0, 0, null);
					image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
				}
				else if(t.getColumn() == 8 &&(t.getRow() >5 && t.getRow() < 7 ))
				{
					image.getGraphics().drawImage(BlueHighlightsImage, 0, 0, null);
					image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(BottomBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(TopBlackBorderImage, 0, 0, null);
					
					if (t.getRow() == 5)
					{
						image.getGraphics().drawImage(BottomLeftBlackBorderImage, 0, 0, null);
					}
					else image.getGraphics().drawImage(TopLeftBlackBorderImage, 0, 0, null);
				}
				else if(t.getRow() == 6 && t.getColumn() == 7)
				{
					image.getGraphics().drawImage(BlueHighlightsImage, 0, 0, null);
					image.getGraphics().drawImage(TopRightBlackBorderImage, 0, 0, null);
					image.getGraphics().drawImage(BottomRightBlackBorderImage, 0, 0, null);
				}
		
		//image.getGraphics().dispose();
	}
	
	/**
	 * Load images.
	 */
	public static void loadImages() {
		try {
		VolcanoImage = ImageIO.read(new File("pics/VolcanoTile.png"));
		HelicopterImage = ImageIO.read(new File("pics/HelicopterTile.png"));
		DragonImage = ImageIO.read(new File("pics/DragonTile.png"));
		BigDaddyImage = ImageIO.read(new File("pics/BigDaddyTile.png"));
		YellowDefaultTerrainImage = ImageIO.read(new File("pics/YellowDefaultTerrainTile.png"));
		BlueDefaultTerrainImage = ImageIO.read(new File("pics/BlueDefaultTerrainTile.png"));
		RedDefaultTerrainImage = ImageIO.read(new File("pics/RedDefaultTerrainTile.png"));
		GreenDefaultTerrainImage = ImageIO.read(new File("pics/GreenDefaultTerrainTile.png"));
		NoobImage = ImageIO.read(new File("pics/NoobTile.png"));
		TankImage = ImageIO.read(new File("pics/TankTile.png"));
		ForestPitfallLandImage = ImageIO.read(new File("pics/PitfallForestTile.png"));
		ArcticPitfallLandImage = ImageIO.read(new File("pics/PitfallArcticTile.png"));
		RoadsPitfallLandImage = ImageIO.read(new File("pics/PitfallRoadTile.png"));
		PitfallLandImage = ImageIO.read(new File("pics/PitfallTile.png"));
		AvalancheImage = ImageIO.read(new File("pics/AvalancheTile.png"));
		ForestImage = ImageIO.read(new File("pics/ForestTile.png"));
		TownImage = ImageIO.read(new File("pics/TownTile.png"));
		CapitalImage = ImageIO.read(new File("pics/CapitalTile.png"));
		SwampImage = ImageIO.read(new File("pics/SwampTile.png"));
		MountainsImage = ImageIO.read(new File("pics/MountainsTile.png"));
		DesertImage = ImageIO.read(new File("pics/DesertTile.png"));
		RoadsImage = ImageIO.read(new File("pics/RoadsTile.png"));
		RiverImage = ImageIO.read(new File("pics/RiverTile.png"));
		LakeImage = ImageIO.read(new File("pics/lakeTile.png"));
		ArcticImage = ImageIO.read(new File("pics/ArcticTile.png"));
		FarmsImage = ImageIO.read(new File("pics/FarmsTile.png"));
		RiverFloodImage = ImageIO.read(new File("pics/RiverFloodTile.png"));
		SwampFloodImage = ImageIO.read(new File("pics/SwampFloodTile.png"));
		ShipImage = ImageIO.read(new File("pics/ShipTile.png"));
		MineImage = ImageIO.read(new File("pics/MineTile.png"));
		LocustsImage = ImageIO.read(new File("pics/LocustsTile.png"));
		EruptionImage = ImageIO.read(new File("pics/EruptionTile.png"));
		WildFireImage = ImageIO.read(new File("pics/WildFireTile.png"));
		PlagueImage = ImageIO.read(new File("pics/PlagueTile.png"));
		InvalidTerrainImage = ImageIO.read(new File("pics/InvalidTerrainTile.png"));
		
		BlueHighlightsImage = ImageIO.read(new File("pics/BlueHighlights.png"));
		YellowHighlightsImage = ImageIO.read(new File("pics/YellowHighlights.png"));
		RedHighlightsImage = ImageIO.read(new File("pics/RedHighlights.png"));
		GreenHighlightsImage = ImageIO.read(new File("pics/GreenHighlights.png"));
		TopBlackBorderImage = ImageIO.read(new File("pics/TopBlackBorder.png"));
		TopRightBlackBorderImage = ImageIO.read(new File("pics/TopRightBlackBorder.png"));
		TopLeftBlackBorderImage = ImageIO.read(new File("pics/TopLeftBlackBorder.png"));
		BottomBlackBorderImage = ImageIO.read(new File("pics/BottomBlackBorder.png"));
		BottomRightBlackBorderImage = ImageIO.read(new File("pics/BottomRightBlackBorder.png"));
		BottomLeftBlackBorderImage = ImageIO.read(new File("pics/BottomLeftBlackBorder.png"));
		
		RedUnitHighlightTile = ImageIO.read(new File("pics/RedUnitHighlightTile.png"));
		GreenUnitHighlightTile = ImageIO.read(new File("pics/GreenUnitHighlightTile.png"));
		BlueUnitHighlightTile = ImageIO.read(new File("pics/BlueUnitHighlightTile.png"));
		YellowUnitHighlightTile = ImageIO.read(new File("pics/YellowUnitHighlightTile.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
