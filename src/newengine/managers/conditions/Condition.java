package newengine.managers.conditions;

import newengine.model.PlayerStatsModel;
import newengine.model.SpriteModel; 
import newengine.model.PlayerRelationModel; 

public abstract class Condition implements ICondition {
	private SpriteModel spriteModel;
	private PlayerStatsModel playerStatsModel;
	private PlayerRelationModel playerRelationModel;
	
	/**
	 * This blank constructor is built in to allow users to initialize a Condition without having to worry about the models
	 */
	public Condition(){
		
	}
	
	public Condition(SpriteModel spriteModel, PlayerStatsModel playerStatsModel, PlayerRelationModel playerRelationModel){
		this.spriteModel = spriteModel;
		this.playerStatsModel = playerStatsModel;
		this.playerRelationModel = playerRelationModel;
	}
	
	public void setSpriteModel(SpriteModel model){
		this.spriteModel = model;
	}
	
	public void setPlayerStatsModel(PlayerStatsModel model){
		this.playerStatsModel = model;
	}
	
	public void setPlayerRelationModel(PlayerRelationModel model) {
		this.playerRelationModel = model;
	}
	
	public PlayerStatsModel getPlayerStatsModel(){
		return playerStatsModel;
	}
	
	public SpriteModel getSpriteModel(){
		return spriteModel;
	}

	public PlayerRelationModel getPlayerRelationModel() {
		return playerRelationModel;
	}
}
