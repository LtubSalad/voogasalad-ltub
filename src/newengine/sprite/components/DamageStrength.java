package newengine.sprite.components;

import java.util.ArrayList;
import java.util.List;

import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.events.SpriteModelEvent;
import newengine.events.sprite.MoveEvent;
import newengine.events.sprite.StateChangeEvent;
import newengine.player.Player;

import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class DamageStrength extends Component {
	
	public static final ComponentType<DamageStrength> TYPE = new ComponentType<>(DamageStrength.class.getName());
	private int strength;

	@ConstructorForDeveloper
	public DamageStrength(@VariableName(name = "Damage")int strength){
		this.strength = strength;
	}
	
	@Override
	public void afterAdded() {
		sprite.emit(new StateChangeEvent(StateChangeEvent.DAMAGESTRENGTH, sprite, strength));
		sprite.on(MoveEvent.STOP, (e) -> {
			sprite.getComponent(Position.TYPE).ifPresent((position) -> {
				if (position.isMoving() == false) {
					List<Sprite> spritesToRemove = new ArrayList<>();
					spritesToRemove.add(sprite);
					sprite.getComponent(GameBus.TYPE).ifPresent((gameBus) -> {
						gameBus.getGameBus().emit(new SpriteModelEvent(SpriteModelEvent.REMOVE, spritesToRemove));
					});		
				}
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
		DamageStrength clone = new DamageStrength(this.strength);
		return clone;
	}

	@Override
	public Object[] getParameters() {
		Object[] parameters= new Object[1];
		parameters[0]=strength;
		return parameters;
	}

}
