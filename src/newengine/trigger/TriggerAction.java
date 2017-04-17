package newengine.trigger;

public class TriggerAction {
	public enum TriggerActionType {
		GAME, SPRITE_ALL, SPRITE_SPECIFIC
	}

	private TriggerActionType type;
	
	public TriggerAction(TriggerActionType type) {
		this.type = type;
	}

}
