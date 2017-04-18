package newengine.trigger;

import newengine.utils.variable.VarComparisonOperator;
import newengine.utils.variable.VarKey;
import newengine.utils.variable.VarMap;
import newengine.utils.variable.VarValue;

public class TriggerCondition {

	private VarKey key;
	private VarComparisonOperator operator;
	private VarValue value;
	
	public TriggerCondition(VarKey key, VarComparisonOperator operator, VarValue value) {
		this.key = key;
		this.operator = operator;
		this.value = value;
	}
	
	public boolean isTrue(VarMap map) {
		return map.get(key).isTrue(operator, value);
	}
	
}
