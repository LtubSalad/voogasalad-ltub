package newengine.sprite.components;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.player.Player;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Owner extends Component {

	public static final ComponentType<Owner> TYPE = new ComponentType<>(Owner.class.getName());
	private Player owner;
	private TeamType myType;
	
	public enum TeamType{
		TEAM_1, TEAM_2, TEAM_3, TEAM_4, TEAM_5, TEAM_6
	}
	
	@ConstructorForDeveloper
	public Owner(@VariableName(name= "Team")TeamType team){
		this(new Player(team.toString()));
		myType=team;
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
		return new Owner(myType);
	}

	@Override
	public Object[] getParameters() {
		Object[] parameters=new Object[1];
		parameters[0]=myType;
		// TODO Auto-generated method stub
		return parameters;
	}
	
}
