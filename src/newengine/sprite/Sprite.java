package newengine.sprite;

import java.util.HashMap;
import java.util.Map;

public class Sprite {
	
	

	Map<AttributeType<? extends Attribute>, Attribute> map = new HashMap<>();
	
	public void addAttribute(Attribute attribute) {
		map.put(attribute.getType(), attribute);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Attribute> T getAttribute(AttributeType<T> type) {
		return (T) map.get(type);
	}
	
	public <T extends Attribute> void removeAttribute(AttributeType<T> type) {
		map.remove(type);
	}
	
	public <T extends Attribute> boolean hasAttribute(AttributeType<T> type) {
		return map.containsKey(type);
	}
	
	
	
	public void update() {
		
	}
	
}
