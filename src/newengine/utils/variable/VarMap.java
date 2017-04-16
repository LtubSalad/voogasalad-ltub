package newengine.utils.variable;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class VarMap {

	private Map<VarKey, VarValue> map = new HashMap<>();
	

	public VarValue get(VarKey key) {
		if (!map.containsKey(key)) {
			throw new NoSuchElementException("GameVariableKey "+key+" not found."); 
		} else {
			return map.get(key);
		}
	}

	
	public void put(VarKey key, VarValue value) {
		map.put(key, value);
	}
	
	public void put(VarKey key, Var<?> var) {
		map.put(key, new VarValue(var.get()));
	}
}
