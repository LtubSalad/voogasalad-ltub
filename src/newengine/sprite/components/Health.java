package newengine.sprite.components;

import java.util.ArrayList;
import java.util.List;

import commons.point.GamePoint;
import newengine.events.SpriteModelEvent;
import newengine.events.sprite.ChangeHealthEvent;
import newengine.events.sprite.WeaponCollisionEvent;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.variable.Var;

public class Health extends Component {

	public static final ComponentType<Health> TYPE = new ComponentType<>(Health.class.getName());
	private final Var<Integer> healthVar = new Var<>();


	public Health(int initial){
		healthVar.set(initial);
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}
	
	public int getHealth(){
		return healthVar.get();
	}
	
	@Override
	public void afterAdded(){
		sprite.on(ChangeHealthEvent.ANY, e -> {
			healthVar.set(healthVar.get() + e.getValue());
			if (healthVar.get() <= 0) {
				List<Sprite> remove = new ArrayList<Sprite>(); //TODO CHANGE THIS!!!!! ADD A METHOD FOR JUST ONE SPRITE
				remove.add(sprite);
				System.out.println("DEATH TO SPRITE " + sprite.getID());
				sprite.getComponent(GameBus.TYPE).get().getGameBus().emit(new SpriteModelEvent(SpriteModelEvent.REMOVE, remove));
			}
		});
		
	}

}
