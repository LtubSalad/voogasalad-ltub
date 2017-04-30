package newengine.skill.skills;

import data.SpriteMakerModel;
import newengine.skill.SkillType;

public class BuildSkillFactory {

	private static int buildSkillID = 0; 
	
	public static BuildSkill createBuildSkill(SpriteMakerModel spriteMakerModel) {
		buildSkillID += 1;
		return new BuildSkill(
				spriteMakerModel, 
				new SkillType<BuildSkill>(BuildSkill.class.getName()+"_FACTORY_BUILT"+buildSkillID));
	}

	
}
