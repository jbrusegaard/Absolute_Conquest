package client.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import client.ClientNetwork;
import player.PlayerProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class BoardImage.
 */
public class BoardImage {
	
	/** The board image. */
	private BufferedImage boardImage;



	/** The tile images. */
	private TileImage[][] tileImages;
	
	/**
	 * Instantiates a new board image.
	 */
	public BoardImage() {
		tileImages = new TileImage[12][13];

		for(int row = 0; row < 12; row++) {
			for(int column = 0; column < 13; column++) {
				tileImages[row][column] = new TileImage();
			}
		}
	}

	/**
	 * Gets the tile images.
	 *
	 * @return the tile images
	 */
	public TileImage[][] getTileImages() {
		return tileImages;
	}

	/**
	 * Sets the tile images.
	 *
	 * @param tileImages the new tile images
	 */
	public void setTileImages(TileImage[][] tileImages) {
		this.tileImages = tileImages;
	}
	
	/**
	 * Draw.
	 *
	 * @param g the g
	 * @param players the players
	 * @param player the player
	 */
	public void draw(Graphics g, ArrayList<PlayerProfile> players, PlayerProfile player) {
		for(int row = 0; row < 12; row++) {
			for(int column = 0; column < 13; column++) {
				if(tileImages[row][column] != null) {
						tileImages[row][column].setUpdateToggle(false);
						BufferedImage image = tileImages[row][column].getImage(ClientNetwork.game.getGame().getGameboard()[row][column], players, player);
						//Image scaledImage = image.getScaledInstance((int)(image.getWidth() / 1),(int) (image.getHeight() / 1), Image.SCALE_DEFAULT);
						int imageWidth = image.getWidth(null);
						int imageHeight = image.getHeight(null);
							if(column % 2 != 0)
								g.drawImage(image, (int) (.75 * imageWidth * column),row * imageHeight, null);
							else
								g.drawImage(image, (int) (.75 * imageWidth * column),row * imageHeight  + (imageHeight / 2), null);

				}
			}
		}
	}
	

}
