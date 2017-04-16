package newengine.sprite.components;

import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.variable.Var;

public class Selectable extends Component {
	public enum SelectionBoundType {
		POLYGON, IMAGE		
	}
	
	public static final ComponentType<Selectable> TYPE = new ComponentType<>();
	private final Var<SelectionBoundType> boundTypeVar = new Var<>();

	public Selectable(SelectionBoundType boundType) {
		boundTypeVar.set(boundType);
	}
	
	public SelectionBoundType boundType() {
		return boundTypeVar.get();
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

}
