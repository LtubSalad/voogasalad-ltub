package newengine.trigger;

import newengine.gamevariable.GameVariableComparisonOperator;
import newengine.gamevariable.GameVariableKey;
import newengine.gamevariable.GameVariableMap;
import newengine.gamevariable.GameVariableValue;

public class Condition {

	private GameVariableKey key;
	private GameVariableComparisonOperator operator;
	private GameVariableValue value;
	
	public Condition(GameVariableKey key, GameVariableComparisonOperator operator, GameVariableValue value) {
		this.key = key;
		this.operator = operator;
		this.value = value;
	}
	
	public boolean isTrue(GameVariableMap map) {
		return map.get(key).isTrue(operator, value);
	}
	
}
