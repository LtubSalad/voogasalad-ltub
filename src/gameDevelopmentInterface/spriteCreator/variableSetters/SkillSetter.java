package gameDevelopmentInterface.spriteCreator.variableSetters;

import data.DeveloperData;
import exception.UnsupportedTypeException;
import gameDevelopmentInterface.developerdata.ObjectSetter;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import newengine.skill.Skill;

public class SkillSetter<T extends Skill> extends ObjectSetter<T>{
	private CheckBox checkbox;
	private HBox box;
	private DefaultObjectSetter<T> setter;
	
	public SkillSetter(Class<? extends T> skill, DeveloperData data) throws UnsupportedTypeException{
		super(skill);
		instantiate();
		setter=new DefaultObjectSetter(skill, data);
		box.getChildren().add(setter);
	}
	
	public SkillSetter(T skill, DeveloperData data) throws UnsupportedTypeException{
		super((Class<T>)skill.getClass());
		instantiate();
		setter=new DefaultObjectSetter(skill, data);
		box.getChildren().add(setter);
	}	
	
	private void instantiate(){
		box=new HBox();
		checkbox=new CheckBox();
		checkbox.setAllowIndeterminate(false);	
		box.getChildren().add(checkbox);
		this.getChildren().add(box);
	}
	
	protected boolean isSelected(){
		return checkbox.isSelected();
	}
	
	public void setSelected(boolean selected){
		checkbox.setSelected(selected);
	}
	
	@Override
	public T produceObject() throws Exception {
		if(!checkbox.isSelected()){
			return null;
		}else{
			return setter.produceObject();
		}
	}
}