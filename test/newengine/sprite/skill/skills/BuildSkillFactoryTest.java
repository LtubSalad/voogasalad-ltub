package newengine.sprite.skill.skills;

import org.junit.Test;
import static org.junit.Assert.*;

import data.SpriteMakerModel;
import newengine.skill.skills.BuildSkill;
import newengine.skill.skills.BuildSkillFactory;

public class BuildSkillFactoryTest {

	@Test
	public void testBuildSkillFactory() {
		BuildSkill buildSkill1 = BuildSkillFactory.createBuildSkill(new SpriteMakerModel());
		BuildSkill buildSkill2 = BuildSkillFactory.createBuildSkill(new SpriteMakerModel());
		assertEquals(buildSkill1.getType(), buildSkill1.getType());
		assertNotEquals(buildSkill1.getType(), buildSkill2.getType());
	}
	
}
