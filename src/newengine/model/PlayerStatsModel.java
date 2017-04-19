package newengine.model;

import java.util.HashMap;
import java.util.Map;

import bus.EventBus;
import newengine.events.stats.ChangeWealthEvent;
import newengine.events.stats.ChangeLivesEvent;
import newengine.events.stats.ChangeScoreEvent;
import newengine.utils.variable.Var;

public class PlayerStatsModel {

	private EventBus bus;
	private String name;
	private final Var<Map<String, Integer>> wealthVar = new Var<>();
	private final Var<Integer> livesVar = new Var<>();
	private final Var<Integer> scoreVar = new Var<>();
	
	//TODO ALLOW INITIAL SETTING OF VALUES
	public PlayerStatsModel(EventBus bus, String name) {
		this.bus = bus;
		this.name = name;
		initHandlers();
		
		//FIX THESE BELOW
		livesVar.set(3);
		scoreVar.set(0);
		Map<String, Integer> wealthTester = new HashMap<>();
		wealthTester.put("gold", 100);
		wealthVar.set(wealthTester);
	}
	
	private void initHandlers() {
		bus.on(ChangeWealthEvent.SPECIFIC, (e) ->{
			if(name.equals(e.getPlayerName())){
				String wealthType = e.getWealthType();
				wealthVar.get().put(wealthType, wealthVar.get().get(wealthType) + e.getAmountChanged());
			}
		});
		bus.on(ChangeLivesEvent.SPECIFIC, (e) ->{
			if(name.equals(e.getPlayerName()))
			livesVar.set(livesVar.get() + e.getAmountChanged());
		});
		bus.on(ChangeScoreEvent.SPECIFIC, (e) ->{
			if(name.equals(e.getPlayerName()))
			scoreVar.set(scoreVar.get() + e.getAmountChanged());
		});
	}

	public int getWealthValue(String wealthType) {
		return wealthVar.get().get(wealthType);
	}
	public Map<String, Integer> getWealth(){
		return wealthVar.get();
	}
	public int getLives() {
		return livesVar.get();
	}
	public int getScore(){
		return scoreVar.get();
	}
	public String getName(){
		return name;
	}

}
