package newengine.utils.variable;

import newengine.sprite.SpriteID;

public class VarValue {

	Object val;
	SpriteID spriteID;
	
	public VarValue(Object val) {
		this.val = val;
	}
	
	public boolean isTrue(VarComparisonOperator oper, VarValue other) {
		// TODO different types
		return false;
	}
	
	// TODO: generic type?
	public Object get() {
		return val;
	}
	
}
