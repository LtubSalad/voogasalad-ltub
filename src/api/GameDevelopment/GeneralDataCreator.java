package api.GameDevelopment;

import api.Data.GeneralModelData;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 * @author Daniel, Jake Provides a text input box allowing the user to set
 *         variables that are tracked regardless of which screen we are on, such
 *         as lives, score etc. No public methods, because its actions are
 *         event-based. Instantiates new GeneralModelData classes when the save
 *         button is pressed on the text input box.
 */
public class GeneralDataCreator extends GridPane {
	private TextArea myNumLivesInput = new TextArea();
	private TextArea myNumLevelsInput = new TextArea();
	private TextArea myStartingGoldInput = new TextArea();
	private TextArea myStartingBonusesInput = new TextArea();
	private Button sendNumLivesInput = new Button("Send");
	private Button sendNumLevelsInput = new Button("Send");
	private Button sendStartingGoldInput = new Button("Send");
	private Button sendStartingBonusesInput = new Button("Send");
	private Button saveAll = new Button("Save All Values");
	private GeneralModelData myGeneralModel = new GeneralModelData();
	
	public GeneralDataCreator() {
		this.setMaxSize(600, 600);
		setupButtons();
		this.add(myNumLivesInput, 0, 0);
		this.add(myNumLevelsInput, 1, 0);
		this.add(myStartingGoldInput, 2, 0);
		this.add(myStartingBonusesInput, 3, 0);
		this.add(sendNumLivesInput, 0, 1);
		this.add(sendNumLevelsInput, 1, 1);
		this.add(sendStartingGoldInput, 2, 1);
		this.add(sendStartingBonusesInput, 3, 1);
		this.add(saveAll, 4, 0);
	}
	
	private void setupButtons() {
		sendNumLivesInput.setOnAction(e -> sendNumLives());
		sendNumLevelsInput.setOnAction(e -> sendNumLevels());
		sendStartingGoldInput.setOnAction(e -> sendStartingGold());
		sendStartingBonusesInput.setOnAction(e -> sendStartingBonuses());
		saveAll.setOnAction(e -> {
			sendNumLives();
			sendNumLevels();
			sendStartingGold();
			sendStartingBonuses();
		});
		saveAll.setMinSize(150, 30);
	}
	
	private void sendNumLives() {
		myGeneralModel.addData(new Pair<String, String>("NumLives", myNumLivesInput.getText()));
	}
	
	private void sendNumLevels() {
		myGeneralModel.addData(new Pair<String, String>("NumLevels", myNumLevelsInput.getText()));
	}
	
	private void sendStartingGold() {
		myGeneralModel.addData(new Pair<String, String>("NumStartingGold", myStartingGoldInput.getText()));
	}
	
	private void sendStartingBonuses() {
		myGeneralModel.addData(new Pair<String, String>("NumStartingBonuses", myStartingBonusesInput.getText()));
	}
}
