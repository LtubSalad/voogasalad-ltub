package newengine.sprite.components;

import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Cost extends Component {
	
	public static final ComponentType<Cost> TYPE = new ComponentType<>(Cost.class.getName());
	private int cost;
	
	@ConstructorForDeveloper
	public Cost(@VariableName(name = "Cost") int cost){
		this.cost = cost;
	}
	
	public int getCost(){
		return cost;
	}

	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	@Override
	public Component clone() {
		return new Cost(cost);
	}

	@Override
	public Object[] getGUIParameters() {
		// TODO Auto-generated method stub
		Object[] parameters=new Object[1];
		parameters[0]=cost;
		return parameters;
	}

}
