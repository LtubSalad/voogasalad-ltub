package newengine.sprite.components;

import java.util.ArrayList;
import java.util.List;

import newengine.events.SpriteModelEvent;
import newengine.events.collision.CollisionEvent;
import newengine.player.Player;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class DamageStrength extends Component {
	
	public static final ComponentType<DamageStrength> TYPE = new ComponentType<>(DamageStrength.class.getName());
	private int strength;


	public DamageStrength(int strength){
		this.strength = strength;
	}
	
	@Override
	public void afterAdded() {
		sprite.on(CollisionEvent.ANY, (e) -> {
			Player owner = sprite.getComponent(Owner.TYPE).get().player();
			Player anotherOwner = e.getSprite2().getComponent(Owner.TYPE).get().player();
			if (owner != anotherOwner) {
				List<Sprite> spritesToRemove = new ArrayList<>();
				spritesToRemove.add(sprite);
				sprite.getComponent(GameBus.TYPE).ifPresent((gameBus) -> {
					gameBus.getGameBus().emit(new SpriteModelEvent(SpriteModelEvent.REMOVE, spritesToRemove));
				});				
			}
		});
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}
	
	public int getStrength(){
		return strength;
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
