package commons.point;

/**
 * The class to represent absolute positions of {@code engine.sprite.Sprite}
 * on the game map.
 * The class extends {@code engine.camera.Point}.
 * @author Yilin Gao
 *
 */
public class GamePoint extends Point {

	/**
	 * Constructor of the {@code GamePoint} class.
	 * @param x the x coordinate of the {@code GamePoint}
	 * @param y the y coordinate of the {@code GamePoint}
	 */
	public GamePoint(double x, double y) {
		super(x, y);
	}

}
