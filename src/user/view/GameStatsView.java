package user.view;

import java.util.Map;

import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import user.GameHistory;

public class GameStatsView extends VBox {
	VBox myStats; 
	
	public GameStatsView(String gameName, GameHistory history){
		makeStatsPane(history.getStats());
		configure(gameName); 
	}

	private void configure(String name) {
		Text gameName = new Text(name);
		TextFlow wrapper = new TextFlow(gameName);
		wrapper.setTextAlignment(TextAlignment.CENTER);
		this.getChildren().addAll(wrapper, myStats);
		
	}

	private void makeStatsPane(Map<String, String> stats) {
		myStats = new VBox(5);
		for(String statName : stats.keySet()){
			Text nameAndValue = new Text(statName + ": " + stats.get(statName));
		
			//TODO: set text size 
			TextFlow wrapper = new TextFlow(nameAndValue);
			wrapper.setTextAlignment(TextAlignment.LEFT);
			wrapper.getStylesheets().add("resources/socialStyle.css");
			wrapper.getStyleClass().add("textfill");
			myStats.getChildren().add(wrapper);
		}
		myStats.getStylesheets().add("resources/socialStyle.css");
		myStats.getStyleClass().add("statsbox");
		
	}
}
