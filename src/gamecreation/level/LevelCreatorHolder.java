package gamecreation.level;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LevelCreatorHolder {
	private VBox holder;
	private int numLevels;
	
	public LevelCreatorHolder(){
		holder = new VBox();
		Stage testStage = new Stage();
		Scene testScene = new Scene(holder, 400, 400);
		testStage.setScene(testScene);
		testStage.show();
		numLevels = 0;
		setUpBox();
	}
	
	private void setUpBox(){
		holder.setAlignment(Pos.CENTER);
		HBox addButtons = new HBox();
		Button addDefaultButton = new Button("+ Default Level");
		Button addAdvancedButton = new Button("+ Custom Level");
		//TODO put into resource file
		addAdvancedButton.setOnAction(e -> addAdvancedLevelCreator());
		addDefaultButton.setOnAction(e -> addDefaultLevelCreator());
		addButtons.getChildren().addAll(addDefaultButton, addAdvancedButton);
		holder.getChildren().add(addButtons);
	}
	
	private  void addDefaultLevelCreator(){
		addLevelCreator(new SimpleLevelCreator("Level " + (numLevels+1)));
	}
	private void addAdvancedLevelCreator(){
		addLevelCreator(new AdvancedLevelCreator("Level "+ (numLevels+1)));
	}
	
	private void addLevelCreator(LevelCreator level){
		numLevels++;
		if(holder.getChildren().size() > 0){
			holder.getChildren().add(holder.getChildren().size()-1, level);
		}
		else{
			holder.getChildren().add(level);
		}
	}
	
	public int getNumberLevels(){
		return numLevels;
	}
}
