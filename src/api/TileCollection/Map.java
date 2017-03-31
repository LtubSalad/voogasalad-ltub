package api.TileCollection;

import api.Tile.Tile;

public interface Map {
	
	/**
	 * for setting a tile at an x y location
	 */
	public void setTile(double x, double y, Tile t); 
	
	/**
	 * to see what kind of tile is at one of the x y locations
	 */
	public Tile getTile(double x, double y);
	
	/**
	 * gets the x location if given a tile
	 */
	public double getX(Tile t);
	
	/**
	 * gets the y location if given a tile
	 */
	public double getY(Tile t);

}
