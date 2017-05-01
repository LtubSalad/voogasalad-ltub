package newengine.managers.conditions;

import bus.EventBus;
import newengine.events.conditions.EndConditionTriggeredEvent;
import newengine.events.conditions.SetEndConditionEvent;
import newengine.model.PlayerRelationModel;
import newengine.model.PlayerStatsModel;
import newengine.model.SpriteModel;

public class ConditionManager {
	private EventBus bus;
	private SpriteModel spriteModel;
	private PlayerStatsModel playerStatsModel;
	private PlayerRelationModel playerRelationModel;
	private ICondition winning;
	private ICondition losing;

	public ConditionManager(EventBus bus, SpriteModel spriteModel, PlayerStatsModel playerStatsModel, PlayerRelationModel playerRelationModel){
		this.bus = bus;
		this.spriteModel = spriteModel;
		this.playerStatsModel = playerStatsModel;
		this.playerRelationModel = playerRelationModel;
		initHandlers();
	} 
	
	private void initHandlers() {
		bus.on(SetEndConditionEvent.SETWIN, (e) -> {this.winning = setModels(e.getCondition());});
		bus.on(SetEndConditionEvent.SETLOSE, (e) -> {this.losing = setModels(e.getCondition());});
	}

	public void checkConditions() {
		checkWinningCondition();
		checkLosingCondition();
	}
	
	private void checkWinningCondition(){
		if(winning != null && winning.check()){
			System.out.println("WINNING TRIGGERED");
			bus.emit(new EndConditionTriggeredEvent(EndConditionTriggeredEvent.WIN));
		}
	}
	
	private void checkLosingCondition(){
		if(losing != null && losing.check()){
			bus.emit(new EndConditionTriggeredEvent(EndConditionTriggeredEvent.LOSE));
		}
	}
	
	private ICondition setModels(ICondition condition) {
		condition.setPlayerStatsModel(playerStatsModel);
		condition.setPlayerRelationModel(playerRelationModel);
		condition.setSpriteModel(spriteModel);
		return condition;
	}
	
}
