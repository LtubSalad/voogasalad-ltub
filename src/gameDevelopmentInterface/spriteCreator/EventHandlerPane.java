package gameDevelopmentInterface.spriteCreator;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import bus.BusEvent;
import data.SpriteMakerModel;
import helperAnnotations.DeveloperMethod;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

/**
 * 
 * @author Daniel Uses reflection and annotation to create scripts to handle the
 *         events listened to by a given sprite
 */
public class EventHandlerPane extends ScrollPane {
	private VBox myContents;
	private ListView<SingleEventHandler> handlerSetters;
	private final double PREF_WIDTH=300;

	public EventHandlerPane(SpriteMakerModel sprite) {
		myContents = new VBox();
		handlerSetters = new ListView<SingleEventHandler>();
		handlerSetters.setPrefWidth(PREF_WIDTH);
		instantiateNodes(sprite);
		this.setContent(myContents);
		this.setPrefWidth(PREF_WIDTH);
	}
	
	public void setSprite(SpriteMakerModel sprite){
		instantiateNodes(sprite);
	}

	private void instantiateNodes(SpriteMakerModel mySprite) {
		handlerSetters.getItems().clear();
		for (BusEvent event : mySprite.getListenedEvents()) {
			handlerSetters.getItems().add(new SingleEventHandler(event));
		}
		myContents.getChildren().addAll(new Label("Event Handlers"),handlerSetters);
	}

	public void updateSprite(SpriteMakerModel model) {
		handlerSetters.getItems().forEach((handlerSetter) -> {
			handlerSetter.updateSprite(model);
		});
	}

	private class SingleEventHandler extends VBox {
		private BusEvent myEvent;
		private TextArea scriptField;
		private final double SCRIPT_HEIGHT=100;

		private SingleEventHandler(BusEvent event, String currentScript) {
			Label eventLabel = new Label(event.getClass().getSimpleName());
			scriptField = new TextArea();
			scriptField.setPrefHeight(SCRIPT_HEIGHT);
			this.getChildren().addAll(eventLabel, scriptField, new MethodDisplay());
		}

		private SingleEventHandler(BusEvent event) {
			this(event, "");
		}

		private void addScript(String script) {
			scriptField.setText(scriptField.getText()+script+";"+"\n");
		}

		private void updateSprite(SpriteMakerModel sprite) {
			sprite.getScriptMap().put(myEvent, scriptField.getText());
		}

		private class MethodDisplay extends ComboBox<String> {
			private ObservableList<String> methods;
			private final int ROW_COUNT = 5;

			private MethodDisplay() {
				this.setVisibleRowCount(ROW_COUNT);
				this.setPromptText("Available commands");

				List<String> dumMethods = new ArrayList<>();
				methods = FXCollections.observableList(dumMethods);
				//updateMethods();
				this.setItems(methods);
				this.getSelectionModel().selectedIndexProperty().addListener((observableValue,oldVal,newVal)->{
						SingleEventHandler.this.addScript(getSelectionModel().getSelectedItem());	
					});
			}

			private void updateMethods(List<Class<? extends Component>> classList) {
				methods.clear();
				for (Method method : Sprite.class.getDeclaredMethods()) {
					if(method.isAnnotationPresent(DeveloperMethod.class)){
						methods.add(methodToString(method));
					}
				}
				classList.forEach((clazz) -> {
					for (Method method : clazz.getDeclaredMethods()) {
						if(method.isAnnotationPresent(DeveloperMethod.class)){
							methods.add(methodToString(method));
						}
					}
				});
			}

			private String methodToString(Method method) {
				String s = method.getName();
				Parameter[] parameters = method.getParameters();
				if (parameters.length == 0) {
					return s + "()";
				}
				s += "(";
				for (int i = 0; i < parameters.length; i++) {
					s += "?";
					if (i < parameters.length - 1) {
						s += ",";
					}
				}
				s += ")";
				return s;
			}
		}
	}
}
