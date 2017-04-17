package gameDevelopmentInterface;

import commons.point.GamePoint;
import javafx.util.Pair;
/**
 * A utility that allows the front end with a coordinate system to talk to the back
 * end with a simple double,double pair locating system for the sprites. For example,
 * in the front end, users can only place sprites in pre-defined locations, but in
 * back end, the sprites will then be allowed to move around on all points on the 
 * screen.
 * 
 * @author Jake
 *
 */
public class CoordinateConversion {
	
	public CoordinateConversion() {
		
	}
	/**
	 * Will turn the sprite's set position based on coordinates into a double,double pair of
	 * GamePoint so that the engine can correctly locate the sprite
	 * @param pairCoord
	 * @return a GamePoint object that tells the back end where to place the object on the screen
	 */
	public GamePoint fromPairToGamePoint(Pair<Integer, Integer> pairCoord) {
		return new GamePoint(pairCoord.getKey(), pairCoord.getValue());
	}
	/**
	 * Will turn the sprite's game position into its coordinate position.
	 * @param gameCoord
	 * @return the coordinates of the sprite on the grid in ScreenMap
	 */
	public Pair<Integer, Integer> fromGamePointToPair(GamePoint gameCoord) {
		Double dx = gameCoord.x();
		Double dy = gameCoord.y();
		Integer x = dx.intValue();
		Integer y = dy.intValue();
		return new Pair<Integer, Integer>(x, y);
	}

}