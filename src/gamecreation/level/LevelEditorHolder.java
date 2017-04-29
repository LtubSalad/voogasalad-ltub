package gamecreation.level;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class LevelEditorHolder extends ScrollPane {
	public static final double PREF_HEIGHT = 600;
	private ObservableList<LevelData> levelData;
	private VBox content;
	
	public LevelEditorHolder(ObservableList<LevelData> data, double prefHeight){
		this.content = new VBox();
		this.levelData = data;
		this.setContent(content);
		content.setPrefHeight(prefHeight);
		levelData.addListener(new ListChangeListener<LevelData>(){
			@Override
			public void onChanged(Change<? extends LevelData> c) {
				render();
			}
		});
	}
	
	public LevelEditorHolder(ObservableList<LevelData> data){
		this(data, PREF_HEIGHT);
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
