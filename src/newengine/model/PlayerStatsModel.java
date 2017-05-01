package newengine.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import bus.EventBus;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import newengine.events.conditions.EndConditionTriggeredEvent;
import newengine.events.player.MainPlayerEvent;
import newengine.events.skill.CheckCostAndBuildEvent;
import newengine.events.sound.SoundEvent;
import newengine.events.stats.ChangeLivesEvent;
import newengine.events.stats.ChangeScoreEvent;
import newengine.events.stats.ChangeWealthEvent;
import newengine.events.stats.InsufficientGoldEvent;
import newengine.player.Player;

public class PlayerStatsModel {
	
	public enum WealthType {
		GOLD
	}

	private EventBus bus;
	private Map<Player, Map<WealthType, Integer>> wealth = FXCollections.observableMap(new HashMap<>());
	private Map<Player, Integer> lives = new HashMap<>();
	private Map<Player, Integer> scores = new HashMap<>();
	
	private Player mainPlayer;
	
	public PlayerStatsModel(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}

	private void initHandlers() {
		bus.on(MainPlayerEvent.ANY, e -> {
			mainPlayer = e.getPlayer();
		});
		bus.on(CheckCostAndBuildEvent.ANY, (e) -> {
			if (isNotMainPlayer(e.getPlayer())) return;
			int cost = e.getCost();
			if (wealth.get(mainPlayer).get(WealthType.GOLD) >= cost) {
				e.getBuildCallback().execute();
			} else {
				// TODO: send insufficient gold event, 
				// and use trigger to handle it and play sound (and show text).
				bus.emit(new SoundEvent(SoundEvent.SOUND_EFFECT, "data/sounds/alert_sound.mp3"));
			}
		});
		bus.on(ChangeWealthEvent.CHANGE, (e) ->{
			if (isNotMainPlayer(e.getPlayer())) return;
			WealthType type = e.getWealthType();
			if (wealth.containsKey(mainPlayer)) {
				Map<WealthType, Integer> wealths = wealth.get(mainPlayer);
				if (wealths.containsKey(type)) {
					//System.out.println(wealths.get(type));
					if (wealths.get(type) < 0 || wealths.get(type) < Math.abs(e.getAmountChanged())){
						System.out.println("insufficient gold warning popup");
						bus.emit(new InsufficientGoldEvent(InsufficientGoldEvent.ANY));
						Stage warning = new Stage();
						VBox root = new VBox();
						Scene scene = new Scene(root);
						Text  text = new Text("You don't have enough gold for this update. Sorry!");
						Button close = new Button("close");
						close.setOnAction(f -> {
							warning.close();
						});
						root.getChildren().addAll(text, close);
						warning.setScene(scene);
						warning.show();
						return;
					}
					wealths.put(type, wealths.get(type) + e.getAmountChanged());
				}
				else {
					wealths.put(type, e.getAmountChanged());
				}
			}
			else {
				Map<WealthType, Integer> wealths = new HashMap<>();
				wealths.put(e.getWealthType(), e.getAmountChanged());
				wealth.put(mainPlayer, wealths);
			}
		});
		bus.on(ChangeWealthEvent.REWARD, (e) ->{
			WealthType type = e.getWealthType();
			if (wealth.containsKey(mainPlayer)) {
				Map<WealthType, Integer> wealths = wealth.get(mainPlayer);
				if (wealths.containsKey(type)) {
					wealths.put(type, wealths.get(type) + e.getAmountChanged());
				}
				else {
					wealths.put(type, e.getAmountChanged());
				}
			}
			else {
				Map<WealthType, Integer> wealths = new HashMap<>();
				wealths.put(e.getWealthType(), e.getAmountChanged());
				wealth.put(mainPlayer, wealths);
			}
		});
		bus.on(ChangeLivesEvent.CHANGE, (e) ->{
			if (isNotMainPlayer(e.getPlayer())) return;
			System.out.println("LIVES CHANGED! from: " + lives.get(mainPlayer));
			if (lives.containsKey(mainPlayer)) {
				lives.put(mainPlayer, lives.get(mainPlayer) + e.getAmountChanged());
				if (lives.get(mainPlayer) == 0){
					System.out.println("OUT OF LIVES!");
				}
			}
			System.out.println("LIVES CHANGED! to: " + lives.get(mainPlayer));
		});
		bus.on(ChangeLivesEvent.SET, (e) ->{
			if (isNotMainPlayer(e.getPlayer())) return;
			lives.put(mainPlayer, e.getAmountChanged()); // TODO if this is an appropriate way
		});
		bus.on(ChangeScoreEvent.CHANGE, (e) ->{
			if (isNotMainPlayer(e.getPlayer())) return;
			if (scores.containsKey(mainPlayer)) {
				scores.put(mainPlayer, scores.get(mainPlayer) + e.getAmountChanged());
			}
		});
		bus.on(ChangeScoreEvent.SET, (e) ->{
			if (isNotMainPlayer(e.getPlayer())) return;
			Player player = e.getPlayer();
			scores.put(player, e.getAmountChanged());  // TODO
		});
	}

	private boolean isNotMainPlayer(Player player){
		return (!mainPlayer.getName().equals(player.getName()));
	}
	
	public Optional<Map<WealthType, Integer>> getWealth(Player player){
		return Optional.ofNullable(wealth.get(player));
	}
	public Optional<Integer> getLives(Player player) {
		return Optional.ofNullable(lives.get(player));
	}
	public Optional<Integer> getScore(Player player){
		return Optional.ofNullable(scores.get(player));
	}

}
