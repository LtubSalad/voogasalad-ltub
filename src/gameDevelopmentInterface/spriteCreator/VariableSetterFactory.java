package gameDevelopmentInterface.spriteCreator;

import java.io.File;
import java.lang.reflect.Parameter;
import java.util.HashMap;

import data.DeveloperData;
import exception.UnsupportedTypeException;
import gameDevelopmentInterface.Path;
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
			return new ImageVariableSetter(name+"e");
		}
		else if(parameter.getType().isAssignableFrom(File.class)){
			return new FileVariableSetter(name);
		}
		else if(name.equals("Skills")){
			return new SkillMapSetter(name, data);
		}
		else{
			throw new UnsupportedTypeException(parameter.getType());
		}
		
	}

}
