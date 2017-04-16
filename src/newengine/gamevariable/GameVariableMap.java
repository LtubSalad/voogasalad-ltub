package newengine.gamevariable;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import newengine.observer.Var;

public class GameVariableMap {

	private Map<GameVariableKey, GameVariableValue> map = new HashMap<>();
	

	public GameVariableValue get(GameVariableKey key) {
		if (!map.containsKey(key)) {
			throw new NoSuchElementException("GameVariableKey "+key+" not found."); 
		} else {
			return map.get(key);
		}
	}

	
	public void put(GameVariableKey key, GameVariableValue value) {
		map.put(key, value);
	}
	
	public void put(GameVariableKey key, Var<?> var) {
		map.put(key, new GameVariableValue(var.get()));
	}
}
