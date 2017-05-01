package newengine.sprite.components;

import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.player.Player;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Owner extends Component {

	public static final ComponentType<Owner> TYPE = new ComponentType<>(Owner.class.getName());
	private Player owner;
	private TeamNumber teamNumber;
	
	public enum TeamNumber{
		TEAM_1, TEAM_2, TEAM_3, TEAM_4, TEAM_5
	}
	
	@ConstructorForDeveloper
	public Owner(@VariableName(name= "Team") TeamNumber team){
		this(team.toString());
		teamNumber=team;
	}

	public Owner(String player){
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
		for(TeamNumber team:TeamNumber.values()){
			if(owner.toString().equals(team.toString())){
				parameters[0]=teamNumber;
				return parameters;
			}
		}
		parameters[0]=TeamNumber.TEAM_1;
		return parameters;
	}
	
}
