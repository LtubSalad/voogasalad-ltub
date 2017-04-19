package gameDevelopmentInterface;

import java.util.ResourceBundle;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * A simple status bar (that could also function as an HUD) to show the user
 * their general game data such as number of lives left, etc.
 * 
 * @author Jake
 *
 */
public class GeneralGameDataBar extends HBox {
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private static final int SPACING = 50;
	private static final String NUM_BONUSES = "NUM_BONUSES";
	private static final String NUM_GOLD = "NUM_GOLD";
	private static final String NUM_LEVEL = "NUM_LEVEL";
	private static final String NUM_LIVES = "NUM_LIVES";
	private static final String bonusKey = "bonusKey";
	private static final String goldKey = "goldKey";
	private static final String levelKey = "levelKey";
	private static final String livesKey = "livesKey";
	private Text bonuses, gold, levels, lives;

	public GeneralGameDataBar(ObservableMap<String, String> data) {
		this.setSpacing(SPACING);
		data.addListener(new MapChangeListener<String, String>() {
			@Override
			public void onChanged(@SuppressWarnings("rawtypes") MapChangeListener.Change change) {
				redraw(data);
			}
		});
		redraw(data);
	}

	private void redraw(ObservableMap<String, String> data) {
		this.getChildren().clear();
		bonuses = new Text(myResources.getString(NUM_BONUSES) + data.get(myResources.getString(bonusKey)));
		gold = new Text(myResources.getString(NUM_GOLD) + data.get(myResources.getString(goldKey)));
		levels = new Text(myResources.getString(NUM_LEVEL) + data.get(myResources.getString(levelKey)));
		lives = new Text(myResources.getString(NUM_LIVES) + data.get(myResources.getString(livesKey)));
		this.getChildren().addAll(lives, levels, gold, bonuses);
	}
}