package gameDevelopmentInterface.spriteCreator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;


import javafx.scene.layout.VBox;
import newengine.sprite.component.Component;

public class ComponentSetter<T extends Component> extends VBox{	
	private List<PrimitiveVariableSetter> fieldSetters;
	private Constructor<T> ctor;
	
	public ComponentSetter(Class<T> clazz){
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
		String[] names=((ConstructorForDeveloper) ctor.getAnnotation(ConstructorForDeveloper.class)).variableNames();
		for(int i=0;i<types.length;i++){
			fieldSetters.add(new PrimitiveVariableSetter(types[i],names[i]));
		}
		fieldSetters.forEach((fieldSetter)->this.getChildren().add(fieldSetter));
	}
	
	public T makeComponent(){
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
