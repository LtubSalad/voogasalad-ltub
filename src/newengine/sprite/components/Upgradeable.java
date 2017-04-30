package newengine.sprite.components;

import data.SpriteMakerModel;
import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Upgradeable extends Component {
	private int cost;
	private SpriteMakerModel upgrade;

	@ConstructorForDeveloper
	public Upgradeable(@VariableName(name = "Cost") int cost,
			@VariableName(name = "Upgrade") SpriteMakerModel upgrade) {
		this.cost=cost;
		this.upgrade=upgrade;
	}

	@Override
	public ComponentType<? extends Component> getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}
