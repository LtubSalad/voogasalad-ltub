package gameDevelopmentInterface.spriteCreator.variableSetters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.DeveloperData;
import exception.UnsupportedTypeException;
import gameDevelopmentInterface.spriteCreator.SkillSetter;
import javafx.scene.layout.VBox;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.skill.skills.BuildSkill;
import newengine.skill.skills.FireProjectileSkill;
import newengine.skill.skills.MoveSkill;
import utilities.AlertHandler;

public class SkillMapSetter extends VariableSetter<Map<SkillType<? extends Skill>, Skill>> {
	private DeveloperData data;
	private VBox displayContents;
	private List<SkillSetter<? extends Skill>> skillSelectors;
	List<Class<? extends Skill>> availableSkills;

	public SkillMapSetter(String variableName, DeveloperData data) {
		super(variableName);
		this.data=data;
		displayContents=new VBox();
		this.getChildren().add(displayContents);
		availableSkills= new ArrayList<>();
		availableSkills.add(BuildSkill.class);
		availableSkills.add(FireProjectileSkill.class);
		availableSkills.add(MoveSkill.class);
		
		skillSelectors=skillsToSelectors(availableSkills);
		skillSelectors.forEach((selector)->{
			displayContents.getChildren().add(selector);
		});
	}
	
	public List<SkillSetter<? extends Skill>> skillsToSelectors(Collection<Class<? extends Skill>> classes){
		List<SkillSetter<? extends Skill>> selectors=new ArrayList<>();
		classes.forEach((clazz)->{
			try {
				selectors.add(new SkillSetter(clazz, data));
			} catch (UnsupportedTypeException e) {
				AlertHandler.showError("Skill could not be set");
			}			
		});
		return selectors;
	}
	
	@Override
	public Map<SkillType<? extends Skill>, Skill> getValue() throws Exception {
		Map<SkillType<? extends Skill>, Skill> skillMap=new HashMap<>();
		for(SkillSetter<?> selector:skillSelectors){
			if(selector.produceObject()!=null){
				skillMap.put(selector.produceObject().getType(), selector.produceObject());
			}
		}
		return skillMap;
	}

	@Override
	public void setField(Map<SkillType<? extends Skill>, Skill> initialValue) {
		displayContents.getChildren().clear();
		skillSelectors.clear();
		for(Class<? extends Skill> clazz:availableSkills){
			boolean inMap=false;
			SkillSetter<?> setter;
			for(Skill skill:initialValue.values()){				
				if(skill.getClass().equals(clazz)){
					inMap=true;					
					try {
						setter= new SkillSetter(skill,data);
						skillSelectors.add(setter);
						displayContents.getChildren().add(setter);
						setter.setSelected(true);
					} catch (UnsupportedTypeException e) {
						AlertHandler.showError("Could not make skill setter");
					}
					break;
				}
			}
			if(!inMap){
				try {
					setter= new SkillSetter(clazz,data);
					skillSelectors.add(setter);
					displayContents.getChildren().add(setter);
					
				} catch (UnsupportedTypeException e) {
					AlertHandler.showError("Could not make skill setter");
				}
			}
		}
		
	}
	
}
