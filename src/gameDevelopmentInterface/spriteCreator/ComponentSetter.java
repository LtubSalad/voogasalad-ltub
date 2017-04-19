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
	private List<SimpleFieldSetter> fieldSetters;
	private Constructor<T> ctor;
	
	public ComponentSetter(Class<T> clazz){
		Constructor<T>[] ctors=(Constructor<T>[]) clazz.getConstructors();
		for(Constructor<T> constructor:ctors){
			if(constructor.isAnnotationPresent(ConstructorForDeveloper.class)){
				ctor=constructor;
				break;
			}
		}
		
		Class<?>[] types=ctor.getParameterTypes();
		String[] names=((ConstructorForDeveloper) ctor.getAnnotation(ConstructorForDeveloper.class)).variableNames();
		for(int i=0;i<types.length;i++){
			fieldSetters.add(new SimpleFieldSetter(types[i],names[i]));
		}
		fieldSetters.forEach((fieldSetter)->this.getChildren().add(fieldSetter));
	}
	
	public T makeComponent(){
		List<Object> parameters=new ArrayList<>();
		for(SimpleFieldSetter setter:fieldSetters){
			parameters.add(setter.getValue());
		} 
		try {
			return ctor.newInstance(parameters.toArray());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	

}
