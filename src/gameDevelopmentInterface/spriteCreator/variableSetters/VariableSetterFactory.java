package gameDevelopmentInterface.spriteCreator.variableSetters;

import java.io.File;
import java.lang.reflect.Parameter;
import java.util.HashMap;

import data.DeveloperData;
import data.SpriteMakerModel;
import exception.UnsupportedTypeException;
import gameDevelopmentInterface.Path;
import gameDevelopmentInterface.spriteCreator.variableSetters.EnumSetter;
import gameDevelopmentInterface.spriteCreator.variableSetters.FileVariableSetter;
import gameDevelopmentInterface.spriteCreator.variableSetters.ImageVariableSetter;
import gameDevelopmentInterface.spriteCreator.variableSetters.PathSetter;
import gameDevelopmentInterface.spriteCreator.variableSetters.SimpleVariableSetter;
import gameDevelopmentInterface.spriteCreator.variableSetters.SkillMapSetter;
import gameDevelopmentInterface.spriteCreator.variableSetters.SpriteVariableSelector;
import gameDevelopmentInterface.spriteCreator.variableSetters.VariableSetter;
import helperAnnotations.VariableName;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.utils.image.LtubImage;

public class VariableSetterFactory {
	private DeveloperData data;
	
	public VariableSetterFactory(DeveloperData data){
		this.data=data;
	}
	
	public VariableSetter setterFromParameter(Parameter parameter) throws UnsupportedTypeException{
		String name=parameter.getAnnotation(VariableName.class).name();
		if(parameter.getType().isPrimitive()||parameter.getType().equals(String.class)){
			return new SimpleVariableSetter(parameter.getType(),name);
		}
		else if(parameter.getType().isAssignableFrom(Path.class)){
			return new PathSetter(data.getPaths(),name);
		}
		else if(parameter.getType().isEnum()){
			return new EnumSetter(parameter.getType(),name);
		}
		else if(parameter.getType().isAssignableFrom(LtubImage.class)){
			return new ImageVariableSetter(name);
		}
		else if(parameter.getType().isAssignableFrom(File.class)){
			return new FileVariableSetter(name);
		}
		else if(parameter.getType().isAssignableFrom(SpriteMakerModel.class)){
			return new SpriteVariableSelector(name,data);
		}
		else if(name.equals("Skills")){
			return new SkillMapSetter(name, data);
		}
		else{
			throw new UnsupportedTypeException(parameter.getType());
		}
		
	}

}
