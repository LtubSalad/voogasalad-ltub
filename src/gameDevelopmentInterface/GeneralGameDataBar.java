package gameDevelopmentInterface;

import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class GeneralGameDataBar extends HBox {
	private static final int SPACING = 50;
	private static final String NUM_BONUSES = "Number of Bonuses : ";
	private static final String NUM_GOLD = "Number of Gold: ";
	private static final String NUM_LEVEL = "Level: ";
	private static final String NUM_LIVES = "Lives: ";
	private static final String bonusKey = "Number of Starting Bonuses";
	private static final String goldKey = "Number of Starting Gold";
	private static final String levelKey = "Number of Levels";
	private static final String livesKey = "Number of Lives";
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
		bonuses = new Text(NUM_BONUSES + data.get(bonusKey));
		gold = new Text(NUM_GOLD + data.get(goldKey));
		levels = new Text(NUM_LEVEL + data.get(levelKey));
		lives = new Text(NUM_LIVES + data.get(livesKey));
		this.getChildren().addAll(lives, levels, gold, bonuses);
	}

}