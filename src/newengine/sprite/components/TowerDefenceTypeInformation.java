package newengine.sprite.components;

import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class TowerDefenceTypeInformation extends Component {
	
	public enum SpriteType{
		TOWER,MONSTER,SPAWNER,WEAPON,OTHER
	}
	
	private SpriteType myType;
	
	public final ComponentType<Selectable> TYPE = new ComponentType<>(TowerDefenceTypeInformation.class.getName());

	@ConstructorForDeveloper
	public TowerDefenceTypeInformation(@VariableName(name="Type")SpriteType type){
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
		return new TowerDefenceTypeInformation(myType);
	}

	@Override
	public Object[] getGUIParameters() {
		Object[] parameters=new Object[1];
		parameters[0]=myType;
		return parameters;
	}
}
