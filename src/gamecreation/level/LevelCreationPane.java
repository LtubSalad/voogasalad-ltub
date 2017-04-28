package gamecreation.level;

import data.DeveloperData;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LevelCreationPane extends BorderPane {
	public static final double DEFAULT_PREF_HEIGHT = 600.0;
	
	public LevelCreationPane(DeveloperData modelData, double prefHeight){
		super();
		this.setTop(createTitle());
		this.setCenter(new LevelCreatorHolder(modelData, prefHeight, BasicLevelCreator.class));
	}
	
	public LevelCreationPane(DeveloperData modelData){
		this(modelData, DEFAULT_PREF_HEIGHT);
	}
	
	private Text createTitle(){
		Text title = new Text("Level Creation");
		title.setFont(new Font(50));
		return title;
	}

}
