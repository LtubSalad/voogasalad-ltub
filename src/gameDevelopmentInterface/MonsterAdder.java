package gameDevelopmentInterface;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import utilities.XStreamHandler;

public class MonsterAdder extends HBox {
	private Button loadMonster;
	private Button refresh;
	private AllPossibleMonsters myPossibleMonsters;

	public MonsterAdder(AllPossibleMonsters possibleMonsters) {
		myPossibleMonsters = possibleMonsters;
		loadMonster = new Button("Load a monster from file");
		loadMonster.setOnAction(click -> {
			XStreamHandler xstream = new XStreamHandler();
			xstream.getAttributeFromFile();
		});
		
		refresh = new Button("Refresh");
		refresh.setOnAction(click -> {
			myPossibleMonsters.getMonstersOnScreen();
		});
		
		this.getChildren().addAll(new Text("Create a spawner"), loadMonster, refresh);		
	}

}
