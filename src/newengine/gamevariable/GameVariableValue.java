package newengine.gamevariable;

import newengine.trigger.SpriteID;

public class GameVariableValue {

	Object val;
	SpriteID spriteID;
	
	public GameVariableValue(Object val) {
		this.val = val;
	}
	
	public boolean isTrue(GameVariableComparisonOperator oper, GameVariableValue other) {
		
		return false;
	}
	
	// TODO: generic type?
	public Object get() {
		return val;
	}
	
}
