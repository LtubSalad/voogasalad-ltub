package newengine.managers.conditions;

import newengine.model.PlayerRelationModel;
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
	 * Allows the user of this class to switch between different sprite models if desired. Built to
	 * allow the ConditionManager to set the model for the user upon the condition being added.
	 * @param spriteModel
	 */
	public void setSpriteModel(SpriteModel spriteModel);
	
	/**
	 * Allows the user of this class to switch between different player stats models if desired. Built
	 * originally so ConditionManager can set the model for the user upon addition of the condition.
	 * @param playerStatsModel
	 */
	public void setPlayerStatsModel(PlayerStatsModel playerStatsModel);

	/**
	 * Allows the user of this class to switch between player relation model. Also allows the
	 * ConditionManager class to set the model for the user, 
	 * @param playerRelationModel
	 */
	public void setPlayerRelationModel(PlayerRelationModel playerRelationModel);
	
}
