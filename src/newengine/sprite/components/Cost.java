package newengine.sprite.components;

import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Cost extends Component {
	
	public static final ComponentType<Cost> TYPE = new ComponentType<>(Cost.class.getName());
	private int cost;
	
	public Cost(int cost){
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

}
