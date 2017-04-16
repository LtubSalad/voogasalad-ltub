package newengine.gamevariable;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class GameVariableMap {

	private Map<GameVariableKey, GameVariableValue> map = new HashMap<>();
	
	public void addGameVariable(GameVariableKey key, GameVariableValue value) {
		map.put(key, value);
	}
	
	public GameVariableValue get(GameVariableKey key) {
		if (!map.containsKey(key)) {
			throw new NoSuchElementException("GameVariableKey "+key+" not found."); 
		} else {
			return map.get(key);
		}
	}
}
