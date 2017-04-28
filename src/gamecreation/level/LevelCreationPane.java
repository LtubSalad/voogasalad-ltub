package gamecreation.level;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LevelCreationPane extends BorderPane {
	
	private static final double DEFAULT_PREF_HEIGHT = 600.0;

	public LevelCreationPane(double prefHeight){
		super();
		this.setTop(createTitle());
		this.setCenter(new LevelCreatorHolder(prefHeight));
	}
	
	public LevelCreationPane(){
		this(DEFAULT_PREF_HEIGHT);
	}
	
	private Text createTitle(){
		Text title = new Text("Level Creation");
		title.setFont(new Font(50));
		return title;
	}

}
