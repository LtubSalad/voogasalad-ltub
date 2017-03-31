package api.Model;

public interface StatsModel {
	/**
	 * @return the number of points earned by the user during the current game
	 */
	public double getPoints(); 
	/**
	 * @return the number of lives left 
	 */
	public int getLives(); 
	/**
	 * @return the health of the current object
	 */
	public double getHealth(); 
	/**
	 * @return the amount of time remaining, for timed games 
	 */
	public double getTimeRemaining(); 
	/**
	 * @return the amount of bonuses. 
	 */
	public double getBonuses(); 	
}
