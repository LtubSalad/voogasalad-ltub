package newengine.sprite.components;

import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.sprite.player.Player;

public class Owner extends Component {

	public static final ComponentType<Owner> TYPE = new ComponentType<>(Owner.class.getName());
	private final Player owner;
	
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

	
}
