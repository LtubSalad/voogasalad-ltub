package newengine.sprite.components;

import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class SpriteTypeInformation extends Component {
	public enum SpriteType{
		TOWER,MONSTER,SPAWNER,OTHER
	}
	
	private SpriteType myType;
	
	public final ComponentType<Selectable> TYPE = new ComponentType<>(SpriteTypeInformation.class.getName());

	@ConstructorForDeveloper
	public SpriteTypeInformation(@VariableName(name="Type")SpriteType type){
		myType=type;
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}
	
	public SpriteType getSpriteType(){
		return myType;
	}

	@Override
	public Component clone() {
		return new SpriteTypeInformation(myType);
	}

	@Override
	public Object[] getParameters() {
		Object[] parameters=new Object[1];
		parameters[0]=myType;
		return parameters;
	}

}
