package gameDevelopmentInterface.spriteCreator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import exception.UnsupportedTypeException;
import gameDevelopmentInterface.developerdata.ComponentSetterView;
import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import newengine.sprite.component.Component;

public class SimpleComponentSetter<T extends Component> extends ComponentSetterView<T>{	
	private List<SimpleVariableSetter> fieldSetters;
	private Constructor<T> ctor;
	
	public SimpleComponentSetter(Class<? extends Component> myComponent) throws UnsupportedTypeException{
		fieldSetters=new ArrayList<>();
		Constructor<T>[] ctors=(Constructor<T>[]) myComponent.getConstructors();
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
		this.getChildren().add(new Label(myComponent.getSimpleName()));
		fieldSetters.forEach((fieldSetter)->this.getChildren().add(fieldSetter));
	}

	@Override
	public T produceComponent() throws Exception{
		List<Object> parameters=new ArrayList<>();
		for(SimpleVariableSetter setter:fieldSetters){
			parameters.add(setter.getValue());
		} 
		return ctor.newInstance(parameters.toArray());
	}
}
