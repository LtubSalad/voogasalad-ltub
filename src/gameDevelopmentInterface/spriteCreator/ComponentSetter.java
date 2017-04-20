package gameDevelopmentInterface.spriteCreator;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import exception.UnsupportedTypeException;
import gameDevelopmentInterface.spriteCreator.helperAnnotations.ConstructorForDeveloper;
import gameDevelopmentInterface.spriteCreator.helperAnnotations.VariableName;
import javafx.scene.layout.VBox;
import newengine.sprite.component.Component;

public class ComponentSetter<T extends Component> extends VBox{	
	private List<PrimitiveVariableSetter> fieldSetters;
	private Constructor<T> ctor;
	
	public ComponentSetter(Class<T> clazz) throws UnsupportedTypeException{
		fieldSetters=new ArrayList<>();
		Constructor<T>[] ctors=(Constructor<T>[]) clazz.getConstructors();
		System.out.println("length: "+ctors.length);
		for(Constructor<T> constructor:ctors){
			System.out.println("annotations: "+constructor.getParameterAnnotations().length);
			if(constructor.isAnnotationPresent(ConstructorForDeveloper.class)){
				ctor=constructor;
				System.out.println("recognized annotation");
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
			fieldSetters.add(new PrimitiveVariableSetter(types[i],name));
		}
		fieldSetters.forEach((fieldSetter)->this.getChildren().add(fieldSetter));
	}
	
	public T makeComponent() throws UnsupportedTypeException{
		List<Object> parameters=new ArrayList<>();
		for(PrimitiveVariableSetter setter:fieldSetters){
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
