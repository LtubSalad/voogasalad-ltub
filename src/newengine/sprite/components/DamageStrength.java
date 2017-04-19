package newengine.sprite.components;

import newengine.events.sprite.ChangeHealthEvent;
import newengine.events.sprite.WeaponCollisionEvent;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.variable.Var;

public class DamageStrength extends Component {
	
	public static final ComponentType<DamageStrength> TYPE = new ComponentType<>(DamageStrength.class.getName());
	private final Var<Integer> strengthVar = new Var<>();


	public DamageStrength(int value){
		strengthVar.set(value);
	}
	
	@Override
	public void afterAdded() {
		
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}
	
	public int getStrength(){
		return strengthVar.get();
	}

}
