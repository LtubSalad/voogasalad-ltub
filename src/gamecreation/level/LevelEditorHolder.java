package gamecreation.level;

import java.util.ArrayList;
import java.util.List;

import data.DeveloperData;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class LevelEditorHolder extends ScrollPane {
	private ObservableList<LevelData> levelData;
	private VBox content;
	
	public LevelEditorHolder(ObservableList<LevelData> data){
		this.content = new VBox();
		this.levelData = data;
		this.setContent(content);
		levelData.addListener(new ListChangeListener<LevelData>(){
			@Override
			public void onChanged(Change<? extends LevelData> c) {
				render();
			}
		});
		
	}

	private void render(){
		List<LevelEditor> creators = new ArrayList<LevelEditor>();
		content.getChildren().clear();
		levelData.stream().forEach(level -> {
			creators.add(new SpawnerLevelEditor(level));
		});
		content.getChildren().addAll(creators);
	}
}
