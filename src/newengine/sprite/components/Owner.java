package newengine.sprite.components;

import newengine.player.Player;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Owner extends Component {

	public static final ComponentType<Owner> TYPE = new ComponentType<>(Owner.class.getName());
	private Player owner;
	
	public Owner(Player player) {
		this.owner = player;
	}
	
	public Player player() {
		return owner;
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	@Override
	public Owner clone() {
		return new Owner(owner);
	}
	
}
