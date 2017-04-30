package gameDevelopmentInterface.spriteCreator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import data.DeveloperData;
import exception.UnsupportedTypeException;
import gameDevelopmentInterface.developerdata.ComponentSetter;
import gameDevelopmentInterface.spriteCreator.variableSetters.VariableSetter;
import helperAnnotations.ConstructorForDeveloper;
import newengine.sprite.component.Component;
/**
 * 
 * @author Daniel
 * A GUI component that allows the user to instantiate any component.
 * Looks for a method marked ConstructorForDeveloper, and and creates 
 * GUI setters to instantiate a class using the constructor's parameters.
 * 
 * @param <T>
 */
public class DefaultComponentSetter<T extends Component> extends ComponentSetter<T>{
	private Constructor<? extends T> ctor;
	private Parameter[] parameters;
	private DeveloperData data;
	private List<VariableSetter<?>> variableSetters;
	private VariableSetterFactory setterFactory;
	
	public DefaultComponentSetter(Class<? extends T> myComponent, DeveloperData data) throws UnsupportedTypeException{
		super(myComponent);
		addDefaultLabel();
		updateFactoryData(myComponent,data);
		
		for(int i=0;i<parameters.length;i++){
			VariableSetter<?> fieldSetter=setterFactory.setterFromParameter(parameters[i]);
			variableSetters.add(fieldSetter);
			this.getChildren().add(fieldSetter);
		}
	}

	public <U> DefaultComponentSetter(T component, DeveloperData data) throws UnsupportedTypeException{
		super((Class<? extends T>)component.getClass());
		addDefaultLabel();
		updateFactoryData(getComponentType(),data);
		Object[] currentFields=component.getParameters();
		
		for(int i=0;i<parameters.length;i++){
			VariableSetter<U> fieldSetter = setterFactory.setterFromParameter(parameters[i]);
			variableSetters.add(fieldSetter);
			this.getChildren().add(fieldSetter);
			fieldSetter.setField((U)currentFields[i]);
			
			//fieldSetter.setField(currentFields[i]);
		}
	}
	
	
	private void updateFactoryData(Class<? extends T> clazz, DeveloperData data){
		this.data=data;
		variableSetters=new ArrayList<>();
		Constructor<? extends T> myCtor=null;
		Constructor<? extends T>[] ctors=(Constructor<? extends T>[]) clazz.getConstructors();
		for(Constructor<? extends T> constructor:ctors){
			if(constructor.isAnnotationPresent(ConstructorForDeveloper.class)){
				ctor=constructor;
				break;
			}
		}	
		if(ctor==null){
			//Throw exception
			return;
		}
		parameters=ctor.getParameters();
		setterFactory=new VariableSetterFactory(data);
	}

	@Override
	public T produceComponent() throws Exception{
		List<Object> parameters=new ArrayList<>();
		for(VariableSetter<?> setter:variableSetters){
			parameters.add(setter.getValue());
		} 
		return ctor.newInstance(parameters.toArray());
	}
}
