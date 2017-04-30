package gamecreation.level;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class LevelEditorHolder extends ScrollPane {
	public static final double PREF_HEIGHT = 600;
	private ObservableList<LevelData> levelData;
	private VBox content;
	private VBox editors;
	private VBox otherNodes;
	
	public LevelEditorHolder(ObservableList<LevelData> data, double prefHeight){
		this.content = new VBox();
		this.editors = new VBox();
		this.otherNodes = new VBox();
		this.content.getChildren().addAll(otherNodes, editors);
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

	public void render(){
		List<LevelEditor> editorList = new ArrayList<LevelEditor>();
		editors.getChildren().clear();
		levelData.stream().forEach(level -> {
			editorList.add(new SpawnerLevelEditor(level));
		});
		editors.getChildren().addAll(editorList);
	}
	
	public void addNode(Node node){
		otherNodes.getChildren().add(node);
	}
	
	public ObservableList<LevelData> getLevelData(){
		return levelData;
	}
	
	public VBox getLevelEditors(){
		return editors;
	}
}
