package newengine.managers.conditions;

import newengine.model.PlayerStatsModel;
import newengine.model.SpriteModel; 
import newengine.model.PlayerRelationModel; 

/**
 * The goal of this condition class is to interact with the strategy design pattern with the ConditionManager
 * The most important method is the check method which will return true when the conidition is true
 * @author Matthew Tribby
 */
public abstract class Condition implements ICondition {
	private SpriteModel spriteModel;
	private PlayerStatsModel playerStatsModel;
	private PlayerRelationModel playerRelationModel;
	
	/**
	 * This blank constructor is built in to allow users to initialize a Condition without having to worry about the models
	 */
	public Condition(){
		
	}
	
	/**
	 * Constructor that initializes levels
	 * @param spriteModel
	 * @param playerStatsModel
	 * @param playerRelationModel
	 */
	public Condition(SpriteModel spriteModel, PlayerStatsModel playerStatsModel, PlayerRelationModel playerRelationModel){
		this.spriteModel = spriteModel;
		this.playerStatsModel = playerStatsModel;
		this.playerRelationModel = playerRelationModel;
	}
	
	/**
	 * Set Sprite Model
	 * @param SpriteModel 
	 */
	public void setSpriteModel(SpriteModel model){
		this.spriteModel = model;
	}
	
	/**
	 * Sets PlayerStatsModel
	 * @param PlayerStatsModel
	 */
	public void setPlayerStatsModel(PlayerStatsModel model){
		this.playerStatsModel = model;
	}
	
	/**
	 * Sets PlayerRelationModel
	 * @param PlayerRelationModel
	 */
	public void setPlayerRelationModel(PlayerRelationModel model) {
		this.playerRelationModel = model;
	}
	
	/**
	 * Returns the player stats model
	 * @return PlayerStatsModel
	 */
	public PlayerStatsModel getPlayerStatsModel(){
		return playerStatsModel;
	}
	
	/**
	 * Gets the current Sprite Model
	 * @return SpriteModel
	 */
	public SpriteModel getSpriteModel(){
		return spriteModel;
	}

	/**
	 * Gets the PlayerRelationModel
	 * @return PlayerRelationModel object
	 */
	public PlayerRelationModel getPlayerRelationModel() {
		return playerRelationModel;
	}
}
