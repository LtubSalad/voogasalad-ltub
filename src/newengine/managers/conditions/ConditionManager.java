package newengine.managers.conditions;

import bus.EventBus;
import newengine.model.PlayerStatsModel;
import newengine.model.SpriteModel;

public class ConditionManager {
	private EventBus bus;
	private SpriteModel spriteModel;
	private PlayerStatsModel playerStatsModel;
	private ICondition winning;
	private ICondition losing;

	public ConditionManager(EventBus bus, SpriteModel spriteModel,PlayerStatsModel playerStatsModel){
		this.bus = bus;
		this.spriteModel = spriteModel;
		this.playerStatsModel = playerStatsModel;
	}
	
	public void checkConditions() {
		checkWinningCondition();
		checkLosingCondition();
	}
	
	private void checkWinningCondition(){
		if(winning.check()){

		}
	}
	
	private void checkLosingCondition(){
		if(losing.check()){
			
		}
	}
	
	public void setWinningCondition(ICondition condition){
		winning = condition;
	}
	
	public void setLosingCondition(ICondition condition){
		losing = condition;
	}
	
}
