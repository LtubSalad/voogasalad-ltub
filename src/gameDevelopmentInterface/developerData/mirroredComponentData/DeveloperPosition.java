package gameDevelopmentInterface.developerData.mirroredComponentData;

import java.util.Collection;

import bus.BusEvent;
import gameDevelopmentInterface.spriteCreator.ComponentSetter;
import newengine.sprite.components.Position;

public class DeveloperPosition extends Position implements DeveloperComponent{

	public DeveloperPosition(double xPos, double yPos, double heading) {
		super(xPos, yPos, heading);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ComponentSetter createSetter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<BusEvent> getListenedEvents() {
		// TODO Auto-generated method stub
		return null;
	}

}
