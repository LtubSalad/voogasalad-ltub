package newengine.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import bus.EventBus;
import javafx.collections.FXCollections;
import newengine.events.stats.ChangeLivesEvent;
import newengine.events.stats.ChangeScoreEvent;
import newengine.events.stats.ChangeWealthEvent;
import newengine.player.Player;

public class PlayerStatsModel {
	
	public enum WealthType {
		GOLD
	}

	private EventBus bus;
	private Map<Player, Map<WealthType, Integer>> wealth = FXCollections.observableMap(new HashMap<>());
	private Map<Player, Integer> lives = new HashMap<>();
	private Map<Player, Integer> scores = new HashMap<>();
	
	public PlayerStatsModel(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(ChangeWealthEvent.CHANGE, (e) ->{
			System.out.println("change wealth!!");
			Player player = e.getPlayer();
			WealthType type = e.getWealthType();
			if (wealth.containsKey(player)) {
				Map<WealthType, Integer> wealths = wealth.get(player);
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
				wealth.put(player, wealths);
			}
		});
		bus.on(ChangeLivesEvent.CHANGE, (e) ->{
			Player player = e.getPlayer();
			if (lives.containsKey(player)) {
				lives.put(player, lives.get(player) + e.getAmountChanged());
			}
		});
		bus.on(ChangeLivesEvent.SET, (e) ->{
			Player player = e.getPlayer();
			lives.put(player, e.getAmountChanged()); // TODO if this is an appropriate way
		});
		bus.on(ChangeScoreEvent.CHANGE, (e) ->{
			Player player = e.getPlayer();
			if (scores.containsKey(player)) {
				scores.put(player, scores.get(player) + e.getAmountChanged());
			}
		});
		bus.on(ChangeScoreEvent.SET, (e) ->{
			Player player = e.getPlayer();
			scores.put(player, e.getAmountChanged());  // TODO
		});
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
