package gamecreation.level;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LevelCreatorHolder {
	private VBox holder;
	private int numLevels;
	private ArrayList<LevelCreator> levels;
	
	public LevelCreatorHolder(){
		ScrollPane root = new ScrollPane();
		holder = new VBox();
		root.setContent(holder);
		Stage testStage = new Stage();
		Scene testScene = new Scene(root, 400, 400);
		testStage.setScene(testScene);
		testStage.show();
		numLevels = 0;
		levels = new ArrayList<LevelCreator>();
		setUpBox();
	}
	
	private void setUpBox(){
		holder.setAlignment(Pos.CENTER);
		HBox addButtons = new HBox();
		//TODO resource file
		Button addDefaultButton = new Button("+ Default Level");
		Button addAdvancedButton = new Button("+ Custom Level");
		//TODO put into resource file
		addAdvancedButton.setOnAction(e -> addAdvancedLevelCreator());
		addDefaultButton.setOnAction(e -> addDefaultLevelCreator());
		addButtons.getChildren().addAll(addDefaultButton, addAdvancedButton);
		holder.getChildren().add(addButtons);
	}
	
	private  void addDefaultLevelCreator(){
		addLevelCreator(new SimpleLevelCreator("Level " + (numLevels+1), this));
	}
	private void addAdvancedLevelCreator(){
		addLevelCreator(new AdvancedLevelCreator("Level "+ (numLevels+1), this));
	}
	
	private void addLevelCreator(LevelCreator level){
		numLevels++;
		if(holder.getChildren().size() > 0){
			holder.getChildren().add(holder.getChildren().size()-1, level);
		}
		else{
			holder.getChildren().add(level);
		}
		levels.add(level);
	}
	
	public int getNumberLevels(){
		return numLevels;
	}

	public void remove(LevelCreator level) {
		for(int i = 0; i < levels.size(); i++){
			if(level.equals(levels.get(i))){
				numLevels--;
				levels.remove(i);
				//The ArrayList and the holder will always be parallel
				holder.getChildren().remove(i);
			}
		}
	}
}
