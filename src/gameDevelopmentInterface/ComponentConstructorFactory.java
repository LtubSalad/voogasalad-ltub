package gameDevelopmentInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import data.SpriteMakerModel;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Look through the resource file for any component and return the parameters that this
 * component will take in its constructor and then make a pop up box corresponding to 
 * these fields
 * 
 * @author Jake
 *
 */

public class ComponentConstructorFactory extends VBox {
	private String myComponentName;
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "ComponentConstructorResource";
	private static final String SAVE = "Save";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private SpriteMakerModel mySpriteModel;
	private ParamVBox myParamVBox = new ParamVBox();
	private Text title = new Text();
	
	public ComponentConstructorFactory(String componentName, SpriteMakerModel spriteModel) {
		title.setText(componentName);
		mySpriteModel = spriteModel;
		myComponentName = componentName;
		createPopUpBox();
		this.getChildren().addAll(title,myParamVBox);
	}
	
	private void createPopUpBox() {
		String constructorParams = myResources.getString(myComponentName);
		String[] constructorParamsList = constructorParams.split(",");
		if (!(constructorParamsList.length == 0)) {
			for (String s : constructorParamsList) {
				ConstructorNameAndEntry nameAndEntry = new ConstructorNameAndEntry(s);
				myParamVBox.getChildren().add(nameAndEntry);
				myParamVBox.addConstructorInfo(nameAndEntry);
			} 
		}
		Button addComponent = new Button(SAVE);
		addComponent.setOnAction(e -> {
			List<String> myParams = new ArrayList<String>();
			for (ConstructorNameAndEntry cInfo : myParamVBox.getConstructorInfo()) {
				myParams.add(cInfo.getEntry());
			}
			System.out.println("Adding to model!!");
			mySpriteModel.addComponent(myComponentName, myParams);
		});
		this.getChildren().add(addComponent);
	}
	
	public SpriteMakerModel getUpdatedModel(){
		return mySpriteModel;
	}
	
}