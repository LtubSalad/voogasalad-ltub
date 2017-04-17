package newengine.utils.variable;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import bus.EventBus;
import newengine.events.VarMapEvent;

/**
 * Hold all game objects (sprites, variables).
 * Key-Value pairs are added to the map whenever they are initialized 
 * and added into game models (i.e., {@code SpriteMode}, etc).
 * @author Yilin Gao
 *
 */
public class VarMap {

	private Map<VarKey, VarValue> map = new HashMap<>();
	private EventBus bus;
	
	public VarMap(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}

	private void initHandlers() {
		bus.on(VarMapEvent.ADD, (e) -> {
			map.put(e.getKey(), e.getValue());
		});
		bus.on(VarMapEvent.REMOVE, (e) -> {
			if (map.remove(e.getKey(), e.getValue())) {
				throw new NoSuchElementException("No Var with key " + e.getKey().toString() +
						"and with value" + e.getValue().toString());
			}
		});
	}

	public VarValue get(VarKey key) {
		if (!map.containsKey(key)) {
			throw new NoSuchElementException("GameVariableKey "+key+" not found."); 
		} else {
			return map.get(key);
		}
	}

	
	private void put(VarKey key, VarValue value) {
		map.put(key, value);
	}
	
	private void put(VarKey key, Var<?> var) {
		map.put(key, new VarValue(var.get()));
	}
}
