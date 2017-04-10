package sprite;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer; 

public class ObserverAttribute implements Observer {
	

	@Override
	public void update(Observable o, Object arg) {
		CompositionMap CM = (CompositionMap) o; 
		Map<String, SpriteAttribute> attMap = CM.getMap();
		String className = this.getClass().getName();
		String interfaceType = getAttributeInterface(this.getClass().getInterfaces());
		System.out.println(className);
		// getAttributeInterfaceName
		// set object in Sprite = what map contains 
		// for String key in the map
		// if this instance of map.get(key) 
		// map.put(key, this) 
		
	}

	public String getAttributeInterface(Class<?>[] interfaces){ 
		for (Class<?> clazz :interfaces){
			System.out.println("interface is " + clazz.getName());
			System.out.println(this instanceof Observer);
		}
		
		return null; 
	}

}
