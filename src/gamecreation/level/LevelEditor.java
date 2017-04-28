package gamecreation.level;

import java.util.Observable;
import java.util.Observer;

import gameauthorgui.inputhelpers.IntegerInputSlider;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class LevelEditor extends TitledPane implements Observer{
	private VBox content;
	private LevelData data;
	private IntegerInputSlider difficultyMod;

	public LevelEditor(LevelData data){
		super();
		data.subscribe(this);
		this.setText("Untitled Level");
		this.data = data;
		createContent();
	}

	public void createContent(){
		content = new VBox();
		difficultyMod = new IntegerInputSlider("Difficulty Modifier", 1, 10);
		content.getChildren().add(difficultyMod);
		this.setContent(content);
		
	}
	
	public LevelData getData(){
		return new LevelData();
	}

	@Override
	public void update(Observable o, Object arg) {
		String name = arg.toString();
		this.setText(name);
	}
}
