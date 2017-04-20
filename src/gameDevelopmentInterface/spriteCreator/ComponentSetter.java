package gameDevelopmentInterface.spriteCreator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import exception.UnsupportedTypeException;
import gameDevelopmentInterface.spriteCreator.helperAnnotations.ConstructorForDeveloper;
import gameDevelopmentInterface.spriteCreator.helperAnnotations.VariableName;
import javafx.scene.layout.VBox;
import newengine.sprite.component.Component;

public class ComponentSetter<T extends Component> extends VBox{	
	private List<SimpleVariableSetter> fieldSetters;
	private Constructor<T> ctor;
	
	public ComponentSetter(Class<T> clazz) throws UnsupportedTypeException{
		fieldSetters=new ArrayList<>();
		Constructor<T>[] ctors=(Constructor<T>[]) clazz.getConstructors();
		System.out.println("length: "+ctors.length);
		for(Constructor<T> constructor:ctors){
			if(constructor.isAnnotationPresent(ConstructorForDeveloper.class)){
				ctor=constructor;
				break;
			}
		}	
		if(ctor==null){
			return;
		}
		
		Class<?>[] types=ctor.getParameterTypes();
		Parameter[] parameters=ctor.getParameters();
		for(int i=0;i<parameters.length;i++){
			String name=parameters[i].getAnnotation(VariableName.class).name();
			System.out.println(types[i].getName());
			fieldSetters.add(new SimpleVariableSetter(types[i],name));
		}
		fieldSetters.forEach((fieldSetter)->this.getChildren().add(fieldSetter));
	}
	
	public T makeComponent() throws UnsupportedTypeException{
		List<Object> parameters=new ArrayList<>();
		for(SimpleVariableSetter setter:fieldSetters){
			parameters.add(setter.getValue());
		} 
		try {
			return ctor.newInstance(parameters.toArray());
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	

}
