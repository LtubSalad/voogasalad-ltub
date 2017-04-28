package gamecreation.level;

import java.util.ArrayList;
import java.util.List;

import data.DeveloperData;
import javafx.collections.ListChangeListener;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class LevelEditorHolder extends ScrollPane {
	private DeveloperData modelData;
	private VBox content;
	
	public LevelEditorHolder(DeveloperData modelData){
		this.modelData = modelData;
		this.content = new VBox();
		this.setContent(content);
		modelData.getLevelData().addListener(new ListChangeListener<LevelData>(){
			@Override
			public void onChanged(Change<? extends LevelData> c) {
				render();
			}
		});
		
	}

	private void render(){
		List<LevelEditor> creators = new ArrayList<LevelEditor>();
		content.getChildren().clear();
		modelData.getLevelData().stream().forEach(e -> {
			creators.add(new LevelEditor(e));
		});
		content.getChildren().addAll(creators);
	}
}
