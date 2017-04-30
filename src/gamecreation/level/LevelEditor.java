package gamecreation.level;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.TitledPane;

public abstract class LevelEditor extends TitledPane implements Observer{
	private LevelData data;

	public LevelEditor(LevelData data){
		super();
		data.subscribe(this);
		this.setText(data.getName());
		this.data = data;
	}

	public abstract void createContent();
	
	public LevelData getData(){
		return data;
	}

	@Override
	public void update(Observable o, Object arg) {
		String name = arg.toString();
		this.setText(name);
	}
}
