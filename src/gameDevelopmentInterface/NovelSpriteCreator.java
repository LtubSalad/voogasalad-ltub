package gameDevelopmentInterface;

import java.util.ResourceBundle;

import data.SpriteMakerModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NovelSpriteCreator extends VBox {
	private ComboBox<String> myComponents = new ComboBox<String>();
	private ComboBox<Button> mySkills = new ComboBox<Button>();
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "ComponentResource";
	private SpriteMakerModel SMM = new SpriteMakerModel(); 
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private Text title = new Text("Make a new sprite!");
	private Button saveSprite = new Button("Save");
	
	public NovelSpriteCreator() {
		makeComboBoxes();
		this.getChildren().addAll(title, myComponents, mySkills, saveSprite);
	}
	
	private void makeComboBoxes() {
		myResources.keySet().forEach(e -> {
			myComponents.getItems().add(e);
		});
		myComponents.valueProperty().addListener(new ChangeListener<String>(){
			@Override 
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				makePopup(newValue);
			}
		});
		//TODO skills
	}
	
	private void makePopup(String componentName) {
		Stage popup = new Stage(); 
		ComponentConstructorFactory CCF = new ComponentConstructorFactory(componentName, SMM);
		this.SMM = CCF.getUpdatedModel();
		
		popup.setScene(new Scene(CCF, 300, 300));
		popup.show(); 
		
	}



}
