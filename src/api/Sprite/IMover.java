package api.Sprite;

	/**
	 * This is an interface used to set forward the methods needed for determining
	 * the moving capabilities of a sprite. The Mover is an essential component
	 * used in the composition of the sprite. Example implementations of this
	 * include: a stationary sprite and a flying sprite. 
	 * @author Matthew Tribby
	 *
	 */
public interface IMover {
	/**
	 * Moves the sprite to a certain location at a certain speed
	 * @param x desired final x location
	 * @param y desired final y location
	 */
	public void move(double x, double y);
	
	/**
	 * Sets the angle that the sprite will face at
	 * @param angleChange Relative angle change in degrees
	 */
	public void setAngle(double angleChange);
	
	/**
	 * Sets the speed at which the sprite will move
	 * @param speed
	 */
	public void setSpeed(double speed);
}
