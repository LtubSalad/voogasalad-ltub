package gameDevelopmentInterface.spriteCreator;

import java.util.Optional;

import data.DeveloperData;
import data.SpriteMakerModel;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import utilities.AlertHandler;
import utilities.XStreamHandler;

public class SavePanel extends HBox {
	private XStreamHandler dataHandler;
	private SpriteCreationScreen screen;
	private DeveloperData data;
	

	public SavePanel(SpriteCreationScreen screen, DeveloperData data) {
		this.getChildren().addAll(saveToFileButton(), saveToGameSpriteButton());
		this.data=data;
		this.screen=screen;
		dataHandler = new XStreamHandler();
	}

	private Button saveToFileButton(){
		AlertHandler alertHandler = new AlertHandler();
		Button saveButton = new Button("Save Sprite to File");
		saveButton.setOnMouseClicked((click) -> {
			Alert alert = alertHandler.confirmationPopUp("Are you sure you wish to save?");
			Optional<ButtonType> result = alert.showAndWait();
			 if (result.isPresent() && result.get() == ButtonType.CANCEL) {
			     return;
			 }
			
			SpriteMakerModel modelToSave = screen.produceNewModel();
			if(modelToSave!=null){
				XStreamHandler handler=new XStreamHandler();
				if(handler.saveToFile(modelToSave)){
					AlertHandler.showMessage("Saved successfully");
				}
			}
		});
		return saveButton;
	}
	
	private Button saveToGameSpriteButton(){
		AlertHandler alertHandler = new AlertHandler();
		Button listSaveButton = new Button("Save Sprite to this game's Sprites");
		listSaveButton.setOnMouseClicked((click) -> {
			Alert certaintyAlert = alertHandler.confirmationPopUp("Are you sure you wish to save?");
			Optional<ButtonType> result = certaintyAlert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.CANCEL) {
			     return;
			}

			SpriteMakerModel modelToSave = screen.produceNewModel();
			if(modelToSave==null){
				return;
			}
			for (SpriteMakerModel spriteModel : data.getSprites()) {
					if (spriteModel.getName().equals(modelToSave.getName())) {
						Alert sameNameAlert = alertHandler
								.confirmationPopUp("Another sprite in your game data has this name. Overwrite?");
						Optional<ButtonType> response = sameNameAlert.showAndWait();
						if (response.isPresent() && response.get() == ButtonType.CANCEL) {
						     return;
						}
						data.getSprites().remove(spriteModel);			
					}
			}
			
			data.getSprites().add(modelToSave);
			AlertHandler.showMessage("Saved successfully");
		});
		return listSaveButton;
	}
}
