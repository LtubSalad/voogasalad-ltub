package utilities;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import bus.BusEvent;
import bus.BusEventHandler;

public class ScriptToHandlerFactory {
	private final ScriptEngine scriptHandler=new ScriptEngineManager().getEngineByName("groovy");
	public <T extends BusEvent> BusEventHandler<T> produceHandler(BusEvent T,String script){
		BusEventHandler<T> newHandler;
		try {
			newHandler = (BusEventHandler<T>) scriptHandler.eval(script);
			return newHandler;
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}

}
