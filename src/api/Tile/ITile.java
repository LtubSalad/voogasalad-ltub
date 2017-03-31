package api.Tile;

import javafx.scene.image.Image;

/**
 * Interface to be used by the Tile class. Tile class make up the background
 * or map of the game which is the lowest priority level to be shown on the
 * JavaFX canvas.
 * @author Matt Tribby
 */
public interface ITile {
	/**
	 * Sets image for the tile
	 * @param image
	 */
	public void setImage(Image image);
	
	/**
	 * Returns the x location on the grid of the given tile
	 * @return x component of the grid position
	 */
	public double getX();
	
	/**
	 * Returns the y location on the grid of the given tile
	 * @return y component of the grid position
	 */
	public double getY();
	
	/**
	 * Returns the image that is the tile
	 * @return Image
	 */
	public Image getImage();
}
