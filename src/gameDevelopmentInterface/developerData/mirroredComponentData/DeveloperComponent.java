package gameDevelopmentInterface.developerData.mirroredComponentData;



import java.util.Collection;

import bus.BusEvent;
import gameDevelopmentInterface.spriteCreator.ComponentSetter;

public interface DeveloperComponent {
	public ComponentSetter createSetter();
	
	public Collection<BusEvent> getListenedEvents();
}
