package newengine.model;

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
	
	public PlayerStatsModel(EventBus bus, String name) {
		this.bus = bus;
		this.name = name;
		initHandlers();
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

	public int getWealth(String wealthType) {
		return wealthVar.get().get(wealthType);
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
