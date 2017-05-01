package gameDevelopmentInterface.spriteCreator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import data.DeveloperData;
import exception.UnsupportedTypeException;
import gameDevelopmentInterface.developerdata.ObjectSetter;
import gameDevelopmentInterface.spriteCreator.variableSetters.VariableSetter;
import helperAnnotations.ConstructorForDeveloper;
import newengine.sprite.component.Component;
import newengine.sprite.component.GUISettableObject;
import utilities.AlertHandler;
/**
 * 
 * @author Daniel
 * A GUI component that allows the user to instantiate any object with the proper annotations.
 * Looks for a method annotated by ConstructorForDeveloper, and and creates 
 * GUI setters to instantiate a class using the constructor's parameters.
 * 
 * @param <T>
 */
public class DefaultObjectSetter<T extends GUISettableObject> extends ObjectSetter<T>{
	private Constructor<? extends T> ctor;
	private Parameter[] parameters;
	private DeveloperData data;
	private List<VariableSetter<?>> variableSetters;
	private VariableSetterFactory setterFactory;
	
	public DefaultObjectSetter(Class<? extends T> myComponent, DeveloperData data) throws UnsupportedTypeException{
		super(myComponent);
		addDefaultLabel();
		updateFactoryData(myComponent,data);
		
		for(int i=0;i<parameters.length;i++){
			VariableSetter<?> fieldSetter=setterFactory.setterFromParameter(parameters[i]);
			variableSetters.add(fieldSetter);
			this.getChildren().add(fieldSetter);
		}
	}

	public <U> DefaultObjectSetter(T component, DeveloperData data) throws UnsupportedTypeException{
		super((Class<? extends T>)component.getClass());
		addDefaultLabel();
		updateFactoryData(getObjectType(),data);
		Object[] currentFields=component.getGUIParameters();
		
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
			AlertHandler.showError("Constructor not found");//Throw exception
			return;
		}
		parameters=ctor.getParameters();
		setterFactory=new VariableSetterFactory(data);
	}

	@Override
	public T produceObject() throws Exception{
		List<Object> parameters=new ArrayList<>();
		for(VariableSetter<?> setter:variableSetters){
			if(setter.getValue()==null){
				AlertHandler.showError("Incomplete field");
			}
			parameters.add(setter.getValue());
		} 
		return ctor.newInstance(parameters.toArray());
	}
}
