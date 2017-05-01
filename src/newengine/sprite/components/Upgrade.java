package newengine.sprite.components;

import data.SpriteMakerModel;
import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Upgrade extends Component {
	
	public static final ComponentType<Upgrade> TYPE = new ComponentType<>(Upgrade.class.getName());

	private SpriteMakerModel smm;
	private int cost;
	
	@ConstructorForDeveloper
	public Upgrade(@VariableName(name="SpriteUpgrade")SpriteMakerModel smm, @VariableName(name="Cost")int cost){
		this.smm = smm;
		this.cost = cost;
	}

	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	@Override
	public Component clone() {
		return new Upgrade(smm, cost);
	}

	@Override
	public Object[] getParameters() {
		return null;
	}
	
	public SpriteMakerModel getSMM() {
		return smm;
	}
	
	public int getCost() {
		return cost;
	}

}
