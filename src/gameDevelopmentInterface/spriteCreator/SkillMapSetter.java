package gameDevelopmentInterface.spriteCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.DeveloperData;
import data.SpriteMakerModel;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.skill.skills.BuildSkill;
import newengine.skill.skills.FireProjectileSkill;
import newengine.skill.skills.MoveSkill;

public class SkillMapSetter extends VariableSetter<Map<SkillType<? extends Skill>, Skill>> {
	private DeveloperData data;
	private VBox displayContents;
	private List<SkillSelector<? extends Skill>> skillSelectors;

	public SkillMapSetter(Class<Map<SkillType<? extends Skill>, Skill>> clazz, String variableName, DeveloperData data) {
		super(clazz, variableName);
		displayContents=new VBox();
		List<Class<? extends Skill>> availableSkills= new ArrayList<>();
		availableSkills.add(BuildSkill.class);
		availableSkills.add(FireProjectileSkill.class);
		availableSkills.add(MoveSkill.class);
		skillSelectors=skillsToSelectors(availableSkills);
		skillSelectors.forEach((selector)->{
			displayContents.getChildren().add(selector);
		});
		
		this.data=data;
	}
	
	public List<SkillSelector<? extends Skill>> skillsToSelectors(List<Class<? extends Skill>> classes){
		List<SkillSelector<? extends Skill>> selectors=new ArrayList<>();
		classes.forEach((clazz)->{
			if(clazz.isAssignableFrom(BuildSkill.class)){
				selectors.add(new SpriteCreatorSelector());
			}else{
				selectors.add(new SkillSelector(clazz));
			}
		});
		return selectors;
	}
	
	@Override
	public Map<SkillType<? extends Skill>, Skill> getValue() throws Exception {
		// TODO Auto-generated method stub
		Map<SkillType<? extends Skill>, Skill> skillMap=new HashMap<>();
		for(SkillSelector selector:skillSelectors){
			if(selector.getValue()!=null){
				skillMap.put(selector.getValue().getType(), selector.getValue());
			}
		}
		return skillMap;
	}

	@Override
	public void setInitialField(Map<SkillType<? extends Skill>, Skill> initialValue) {
		
	}
	
	private class SkillSelector<T extends Skill> extends VariableSetter<T>{
		private CheckBox checkbox;
		private Class<T> clazz;
		
		public SkillSelector(Class<T> skill){
			super(skill, skill.getSimpleName());
			checkbox=new CheckBox();
			checkbox.setAllowIndeterminate(false);	
			this.getChildren().add(checkbox);
		}
		
		protected boolean isSelected(){
			return checkbox.isSelected();
		}
		
		protected void setSelected(boolean selected){
			checkbox.setSelected(selected);
		}
		
		@Override
		public T getValue() throws Exception {
			if(isSelected()){
				return clazz.newInstance();
			}
			return null;
		}

		@Override
		public void setInitialField(T initialValue) {
			checkbox.setSelected(initialValue!=null);
		}
	}
	
	private class SpriteCreatorSelector extends SkillSelector<BuildSkill>{
		private ChoiceBox<SpriteMakerModel> availableSprites;
		public SpriteCreatorSelector() {
			super(BuildSkill.class);
			availableSprites=new ChoiceBox<>(data.getSprites());
		}
		
		@Override
		public BuildSkill getValue(){
			if(isSelected()){
				return new BuildSkill(availableSprites.getValue());
			}else{
				return null;
			}
		}
		
		@Override
		public void setInitialField(BuildSkill buildSkill){
			setSelected(buildSkill!=null);
			availableSprites.getItems().add(buildSkill.getModel());
			availableSprites.setValue(buildSkill.getModel());
			//await someone elses merge
		}
		
	}

}
