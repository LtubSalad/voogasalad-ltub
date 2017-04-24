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
	private SpriteMakerModel mySprite;
	private List<SingleEventHandler> handlerSetters;
	private double prefWidth = 400;

	public EventHandlerPane(SpriteMakerModel sprite) {
		myContents = new VBox();
		mySprite = sprite;
		handlerSetters = new ArrayList<>();
		refreshNodes();
		this.setContent(myContents);
		this.setPrefWidth(prefWidth);
	}

	private void refreshNodes() {
		myContents.getChildren().clear();
		for (BusEvent event : mySprite.getListenedEvents()) {
			handlerSetters.add(new SingleEventHandler(event));
		}
		Button saveHandlers = new Button("Save handlers");
		saveHandlers.setOnAction((click) -> {
			updateSprite();
		});
		myContents.getChildren().add(new Label("Event Handlers"));
		myContents.getChildren().addAll(handlerSetters);
		myContents.getChildren().add(saveHandlers);
	}

	private void updateSprite() {
		handlerSetters.forEach((handlerSetter) -> {
			handlerSetter.updateSprite();
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
			scriptField.setPrefWidth(prefWidth);
			this.getChildren().addAll(eventLabel, scriptField, new MethodDisplay());
		}

		private SingleEventHandler(BusEvent event) {
			this(event, "");
		}

		private void addScript(String script) {
			scriptField.setText(scriptField.getText()+script+";"+"\n");
		}

		private void updateSprite() {
			mySprite.getScriptMap().put(myEvent, scriptField.getText());
		}

		private class MethodDisplay extends ComboBox<String> {
			private ObservableList<String> methods;
			private final int ROW_COUNT = 5;

			private MethodDisplay() {
				this.setVisibleRowCount(ROW_COUNT);
				this.setPromptText("Available commands");

				List<String> dumMethods = new ArrayList<>();
				methods = FXCollections.observableList(dumMethods);
				registerSprite();
				updateMethods();
				this.setItems(methods);
				this.getSelectionModel().selectedIndexProperty().addListener((observableValue,oldVal,newVal)->{
						SingleEventHandler.this.addScript(getSelectionModel().getSelectedItem());	
					});
			}

			private void registerSprite() {
				mySprite.getComponents().addListener(new MapChangeListener<ComponentType<?>, Component>() {
					@Override
					public void onChanged(Change<? extends ComponentType<?>, ? extends Component> change) {
						updateMethods();
					}
				});
			}

			private void updateMethods() {
				methods.clear();
				for (Method method : Sprite.class.getDeclaredMethods()) {
					if(method.isAnnotationPresent(DeveloperMethod.class)){
						methods.add(methodToString(method));
					}
				}
				mySprite.getComponents().forEach((type, component) -> {
					Class<?> clazz = component.getClass();
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
