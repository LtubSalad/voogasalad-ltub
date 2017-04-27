package gameDevelopmentInterface.spriteCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import exception.UnsupportedTypeException;
import gameDevelopmentInterface.developerdata.ComponentSetterView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.skill.skills.FireProjectileSkill;
import newengine.skill.skills.MoveSkill;
import newengine.sprite.components.SkillSet;

/**TODO: This is really bad code, but it's for a component that might be
 * refactored anyways. refactor later if necessary
 * @author Daniel
 * 
 *
 */
public class SkillSetSetter extends ComponentSetterView<SkillSet>{
	private List<SkillSelector> selectors;
	public SkillSetSetter() {
		super(SkillSet.class);
		selectors=new ArrayList<>();
		selectors.add(new SkillSelector<MoveSkill>(MoveSkill.class));
		selectors.add(new SkillSelector<FireProjectileSkill>(FireProjectileSkill.class));
		selectors.forEach((selector)->{
			this.getChildren().add(selector);
		});
		// TODO Auto-generated constructor stub
	}
	
	private class SkillSelector<T extends Skill> extends VariableSetter<T>{
		private CheckBox checkbox;
		private Class<T> clazz;
		private SkillSelector(Class<T> skill){
			checkbox=new CheckBox(skill.getSimpleName());
			checkbox.setAllowIndeterminate(false);	
			this.getChildren().add(checkbox);
		}
		
		private boolean isSelected(){
			return checkbox.isSelected();
		}
		
		@Override
		public T getValue() throws UnsupportedTypeException {
			if(clazz.equals(MoveSkill.class)){
				return (T) new MoveSkill();
			}
			if(clazz.equals(FireProjectileSkill.class)){
				return (T) new FireProjectileSkill();
			}
			return null;
		}
	}

	@Override
	public SkillSet produceComponent() throws UnsupportedTypeException {
		HashMap<SkillType<? extends Skill>, Skill> skillMap=new HashMap<>();
		for(SkillSelector<? extends Skill> selector:selectors){
			if(selector.isSelected()){
				Skill skill= selector.getValue();
				skillMap.put(skill.getType(), skill);
			}
		};
		SkillSet skill= new SkillSet(skillMap);
		
		return skill;
	}
}
