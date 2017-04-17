package newengine.sprite.components;

import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.sprite.player.Player;
import newengine.utils.variable.Var;

public class Owner extends Component {

	public static final ComponentType<Owner> TYPE = new ComponentType<>(Owner.class.getName());
	private final Var<Player> ownerVar = new Var<>();
	
	public Owner(Player player) {
		ownerVar.set(player);
	}
	
	public Player player() {
		return ownerVar.get();
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	
}
