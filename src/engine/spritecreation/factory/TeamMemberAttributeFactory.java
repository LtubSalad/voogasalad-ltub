package engine.spritecreation.factory;

import java.lang.reflect.Constructor;

import data.AttributeData;
import engine.sprite.teammember.TeamMember;
import engine.spritecreation.SpriteCreationException;

/**
 * Attribute Factory for TeamMember Attribute, creates TeamMember Attribute based on AttributeData
 * @author Matt Tribby
 */
public class TeamMemberAttributeFactory implements AttributeFactory{
	public static final String TEAM_VAR = "number";
	public static final String BASE_PATH = "engine.sprite.";
	
	@Override
	/**
	 * Creates TeamMember Attribute based on passed-in AttributeData class
	 * @param AttributeData
	 * @return TeamMember
	 */
	public TeamMember createAttribute(AttributeData data) {
		try{
			Class <?> clazz = Class.forName(BASE_PATH + data.getName().toLowerCase() + "." + data.getImplementation());
			Constructor ctor = clazz.getConstructor(int.class);
			return (TeamMember) ctor.newInstance(Integer.parseInt(data.getVariable(TEAM_VAR)));
		}
		catch(Exception e){
			throw new SpriteCreationException("TeamMember object not found " + data.getImplementation());
		}
	}

}
