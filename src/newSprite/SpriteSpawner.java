package newSprite;

import java.util.Collection;
import java.util.Map;

import bus.BusEvent;
import bus.BusEventType;
import newSprite.ComponentSprite.Component;

public class SpriteSpawner extends Component<BusEvent> {

	public SpriteSpawner(ComponentSprite sprite) {
		sprite.super();
	}

	public void spawnSprite(double xDisplacement, double yDisplacement, String spriteType) {
		//Tell the environment bus to construct a sprite with the given string type
		eBus.emit(new BusEvent(null));
		System.out.println("tried to spawn sprite!");
	}

	@Override
	public Collection<BusEventType<BusEvent>> getListenedEvents() {
		// TODO Auto-generated method stub
		return null;
	}

}
