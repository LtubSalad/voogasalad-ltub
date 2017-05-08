package gameDevelopmentInterface;
/**
 * This class is the top of the spawner creation screen and will
 * allow the user to load in a monster from an XML file and also
 * tell the model how many of the monster it has selected to put
 * into the spawner
 * Jake
 */
import data.SpriteMakerModel;
import gameauthorgui.inputhelpers.IntegerInputText;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import utilities.XStreamHandler;

public class MonsterAdder extends HBox {
	private static final String CREATE_A_SPAWNER = "Create a spawner";
	private static final String LOAD_A_MONSTER_FROM_FILE = "Load a monster from file";
	private static final String HOW_MANY_MONSTERS = "How many monsters?";
	private Button loadMonster;
	private Button refresh;
	private AllPossibleMonsters myPossibleMonsters;
	private IntegerInputText numberOfMonsters = new IntegerInputText(HOW_MANY_MONSTERS);

	public MonsterAdder(AllPossibleMonsters possibleMonsters) {
		myPossibleMonsters = possibleMonsters;
		loadMonster = new Button(LOAD_A_MONSTER_FROM_FILE);
		loadMonster.setOnAction(click -> {
			XStreamHandler xstream = new XStreamHandler();
			SpriteMakerModel monster = (SpriteMakerModel) xstream.getAttributeFromFile();
			myPossibleMonsters.loadFromFile(monster);
		});
		
		refresh = new Button("Refresh");
		refresh.setOnAction(click -> {
			myPossibleMonsters.getMonstersOnScreen();
		});
		
		this.getChildren().addAll(new Text(CREATE_A_SPAWNER), loadMonster, //refresh, 
				numberOfMonsters);		
	}
	
	public int getNumMonsters() {
		return Integer.parseInt(numberOfMonsters.getValue());
	}

}
