package newengine.sprite.components;

import newengine.events.SpriteModelEvent;
import newengine.events.collision.CollisionEvent;
import newengine.events.sprite.ChangeHealthEvent;
import newengine.events.sprite.WeaponCollisionEvent;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.variable.Var;

public class DamageStrength extends Component {
	
	public static final ComponentType<DamageStrength> TYPE = new ComponentType<>(DamageStrength.class.getName());
	private int strength;


	public DamageStrength(int strength){
		this.strength = strength;
	}
	
	@Override
	public void afterAdded() {
		sprite.on(CollisionEvent.ANY, (e) -> {
			sprite.getComponent(GameBus.TYPE).ifPresent((gameBus) -> {
				gameBus.getGameBus().emit(new SpriteModelEvent(SpriteModelEvent.REMOVE, sprite));
			});
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
