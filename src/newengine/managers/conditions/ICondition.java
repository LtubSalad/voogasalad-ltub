package newengine.managers.conditions;

import newengine.model.PlayerStatsModel;
import newengine.model.SpriteModel;

/**
 * This interface governs the expectation for a condition. A condition is used to determine whether a certain condition is met 
 * based on the data encapsulated in the condition.
 * @author Matthew Tribby
 */
public interface ICondition {
	
	/**
	 * Returns true if condition is met, false if not met
	 * @return boolean representing if condition is met
	 */
	public boolean check(); 
	
	/**
	 * Allows the user of this class to switch between different sprite models if desired
	 */
	public void setSpriteModel(SpriteModel spriteModel);
	
	/**
	 * Allows the user of this class to switch between different player stats models if desired
	 */
	public void setPlayerStatsModel(PlayerStatsModel playerStatsModel);
}
