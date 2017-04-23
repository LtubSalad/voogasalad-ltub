package data;

import java.util.List;

import javafx.util.Pair;

public class EventHandleData {
	private String myEventType;
	private String myActionType;
	List<String> myEventParams;
	List<String> myActionParams;
	
	public EventHandleData(String eventType, String actionEventType, List<String> eventParams, List<String> actionParams) {
		myEventType = eventType; 
		myActionType = actionEventType; 
		myEventParams = eventParams; 
		myActionParams = actionParams; 
	}	
	
	
	public Pair<String, List<String>> getTriggeringData(){
		return new Pair<String, List<String>>(myEventType, myEventParams);
	}
	
	public Pair<String, List<String>> getResponseData(){
		return new Pair<String, List<String>>(myActionType, myActionParams);
	}
	

}
