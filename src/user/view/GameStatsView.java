package user.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import user.GameHistory;

public class GameStatsView extends VBox implements Observer {
	private VBox myStats; 
	private HBox myInteractiveElements; 
	
	private String myName; 
	private GameHistory myHistory; 
	
	private final List<String> possibleRatings = Arrays.asList(new String[]{"1", "2", "3", "4", "5"});
	
	public GameStatsView(String gameName, GameHistory history){
		this.myName = gameName; 
		this.myHistory = history;
		makeStatsPane(history.getStats());
		configInteractives();
		configure(gameName); 
	}

	private void configure(String name) {
		Text gameName = new Text(name);
		TextFlow wrapper = new TextFlow(gameName);
		wrapper.setTextAlignment(TextAlignment.CENTER);
		this.getChildren().addAll(wrapper, myStats, myInteractiveElements);
		
	}

	
	private void configInteractives(){
		myInteractiveElements = new HBox(5); 
		ComboBox<String> ratingsBox = new ComboBox<String>(); 
		ratingsBox.getItems().addAll(possibleRatings);	
		// start code from http://stackoverflow.com/questions/32329547/return-the-choice-of-a-combobox-javafx/32335084
		ratingsBox.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				String selection = ratingsBox.getSelectionModel().getSelectedItem();
				myHistory.addRating(Integer.parseInt(selection));
			}
		});
		
		//end code from http://stackoverflow.com/questions/32329547/return-the-choice-of-a-combobox-javafx/32335084
	
		Button likeButton = new Button ("Like");
		likeButton.setOnAction(e ->{
			myHistory.incrementLikes();
		});
		
		myInteractiveElements.getChildren().addAll(ratingsBox, likeButton);
		
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

	@Override
	public void update(Observable o, Object arg) {
		GameHistory newHistory = (GameHistory) o; 
		makeStatsPane(newHistory.getStats());
		reconfigure();
		//FIXME: FINISH ME FIRST

	}

	private void reconfigure() {
		this.getChildren().clear();
		configure(myName);
	}
}
