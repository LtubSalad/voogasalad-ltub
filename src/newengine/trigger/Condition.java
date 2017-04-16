package newengine.trigger;

import newengine.utils.variable.VarComparisonOperator;
import newengine.utils.variable.VarKey;
import newengine.utils.variable.VarMap;
import newengine.utils.variable.VarValue;

public class Condition {

	private VarKey key;
	private VarComparisonOperator operator;
	private VarValue value;
	
	public Condition(VarKey key, VarComparisonOperator operator, VarValue value) {
		this.key = key;
		this.operator = operator;
		this.value = value;
	}
	
	public boolean isTrue(VarMap map) {
		return map.get(key).isTrue(operator, value);
	}
	
}
