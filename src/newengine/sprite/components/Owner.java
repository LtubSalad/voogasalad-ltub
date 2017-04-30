package newengine.sprite.components;

import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.player.Player;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Owner extends Component {

	public static final ComponentType<Owner> TYPE = new ComponentType<>(Owner.class.getName());
	private Player owner;
	
	@ConstructorForDeveloper
	public Owner(@VariableName(name= "Team")String player){
		this(new Player(player));
	}
	
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

	@Override
	public Object[] getParameters() {
		Object[] parameters=new Object[1];
		parameters[0]=owner.toString();
		// TODO Auto-generated method stub
		return parameters;
	}
	
}
