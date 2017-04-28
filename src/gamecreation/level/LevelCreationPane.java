package gamecreation.level;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LevelCreationPane extends BorderPane {
	
	public LevelCreationPane(){
		super();
		this.setTop(createTitle());
		this.setCenter(new LevelCreatorHolder());
		
	}
	
	private Text createTitle(){
		Text title = new Text("Level Creation");
		title.setFont(new Font(50));
		return title;
	}

}
