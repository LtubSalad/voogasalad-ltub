package newengine.sprite.components;

import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Weapon extends Component {
	public static final ComponentType<Weapon> TYPE = new ComponentType<>(Weapon.class.getName());
	
	public Weapon(){
		
	}

	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	@Override
	public Component clone() {
		return new Weapon();
	}

	@Override
	public Object[] getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}
