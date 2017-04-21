package gameDevelopmentInterface.spriteCreator;

import java.util.List;
import java.util.Map;

import bus.BusEvent;
import javafx.scene.layout.VBox;
import newengine.skill.Skill;
import newengine.sprite.Sprite;

public class EventHandlerPane extends VBox{
	private Sprite mySprite;
	
	public void addEvent(BusEvent event){
		/**
		 * add a new gui component within the vbox
		 * with a scripting interface
		 */		
	}
	
	public Map<BusEvent,String> getHandlers(){
		return null;
	}
}
