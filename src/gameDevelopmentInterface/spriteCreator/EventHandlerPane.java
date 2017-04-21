package gameDevelopmentInterface.spriteCreator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bus.BusEvent;
import data.SpriteMakerModel;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

/**
 * 
 * @author Daniel
 * Creates scripts to handle the events listened to by a given sprite
 */
public class EventHandlerPane extends ScrollPane{
	private VBox myContents;
	private SpriteMakerModel mySprite;
	private List<SingleEventHandler> handlerSetters;
	
	public EventHandlerPane(SpriteMakerModel sprite){
		myContents=new VBox();
		mySprite=sprite;
		handlerSetters=new ArrayList<>();
		refreshNodes();
		this.setContent(myContents);
	}
	
	private void refreshNodes(){
		myContents.getChildren().clear();
		for(BusEvent event:mySprite.getListenedEvents()){
			handlerSetters.add(new SingleEventHandler(event));
		}
		Button saveHandlers=new Button("Save handlers");
		saveHandlers.setOnAction((click)->{
			updateSprite();
		});
		
		myContents.getChildren().addAll(handlerSetters);
		myContents.getChildren().add(saveHandlers);
	}
	
	
	private void updateSprite(){
		handlerSetters.forEach((handlerSetter)->{
			handlerSetter.updateSprite();
		});
	}
	
	private class SingleEventHandler extends VBox{
		private BusEvent myEvent;
		private TextField scriptField;
		private SingleEventHandler(BusEvent event, String currentScript){
			Label eventLabel=new Label(event.getEventType().toString());
			scriptField=new TextField();
			this.getChildren().addAll(eventLabel,scriptField,new MethodDisplay());
		}
		
		private SingleEventHandler(BusEvent event){
			this(event, "");
		}
		
		private void updateSprite(){
			mySprite.getScriptMap().put(myEvent,scriptField.getText());
		}
		
		private class MethodDisplay extends ComboBox<Method>{
			private ObservableList<Method> methods;
			private MethodDisplay(){
				List<Method> dumMethods=new ArrayList<>();
				methods=FXCollections.observableList(dumMethods);
				registerSprite();
				updateMethods();
			}
			
			private void registerSprite(){
				mySprite.getComponents().addListener(new MapChangeListener<ComponentType<?>,Component>(){
					@Override
					public void onChanged(Change<? extends ComponentType<?>,? extends Component> change){
						updateMethods();
					}
				});
			}
			
			private void updateMethods(){
				methods.clear();
				for(Method method: Sprite.class.getMethods()){
					methods.add(method);
				}
				mySprite.getComponents().forEach((type,component)->{
					Class<?> clazz=component.getClass();
					for(Method method:clazz.getDeclaredMethods()){
						methods.add(method);
					}
				});
			}
		}
	}
}
