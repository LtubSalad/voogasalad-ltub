package newengine.model;

import java.util.ArrayList;
import java.util.List;

import bus.EventBus;
import newengine.events.SpriteModelEvent;
import newengine.sprite.Sprite;

public class ItemModel {
	
	private List<EventBus> buses;
	private List<Sprite> items;
	
	public ItemModel(){
		buses = new ArrayList<EventBus>();
		items = new ArrayList<Sprite>();
	}
	
	
	public void addBus(EventBus bus){
		bus.on(SpriteModelEvent.ADDITEM, e -> {
			items.add(e.getSprite());
		});
		bus.on(SpriteModelEvent.REMOVEITEM, e -> {
			items.remove(e.getSprite());
		});
		buses.add(bus);
	}

	public List<Sprite> getSprites() {
		return items;
	}

}
