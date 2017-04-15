package newSprite;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import bus.BusEvent;
import bus.BusEventHandler;
import bus.BusEventType;

/**
 * Takes in strings, and converts them to methods for components to call when their events are fired.
 * @author Daniel
 * @param <T>
 * @param <U>
 *
 */
public class ComponentFactory<T extends Component<U>, U extends BusEvent>{
	private T myComponent;
	private ScriptEngine engine;
	
	public ComponentFactory(T component){
		engine=new ScriptEngineManager().getEngineByName("groovy");
		myComponent= component;
	}
	
	public void setResponse(BusEventType<U> eventType, String script){
		try {
			BusEventHandler<U> eventHandler =(BusEventHandler<U>) engine.eval(script);
			myComponent.getBus().on(eventType, eventHandler);
		} catch (ScriptException e) {
			System.out.println("Couldn't recognize script");
			e.printStackTrace();//TODO: Remove later
		}
	}
	
	public T returnComponent() {
		return myComponent;
	}

}
