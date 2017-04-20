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
	
	public PlayerStatsModel(EventBus bus, String name) {
		this.bus = bus;
		this.name = name;
		
		//Initial setting
		wealthVar.set(new HashMap<String, Integer>());
		livesVar.set(0);
		scoreVar.set(0);
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(ChangeWealthEvent.CHANGE, (e) ->{
			if(name.equals(e.getPlayerName())){
				String wealthType = e.getWealthType();
				if(wealthVar.get().containsKey(wealthType)){
					wealthVar.get().put(wealthType, wealthVar.get().get(wealthType) + e.getAmountChanged());}
				else{
					wealthVar.get().put(wealthType, e.getAmountChanged());
				}
			}
		});
		bus.on(ChangeLivesEvent.CHANGE, (e) ->{
			if(name.equals(e.getPlayerName()))
				updateVar(livesVar, livesVar.get(), e.getAmountChanged());
		});
		bus.on(ChangeLivesEvent.SET, (e) ->{
			if(name.equals(e.getPlayerName()))
				updateVar(livesVar, 0, e.getAmountChanged());
		});
		bus.on(ChangeScoreEvent.CHANGE, (e) ->{
			if(name.equals(e.getPlayerName()))
				updateVar(scoreVar, scoreVar.get(), e.getAmountChanged());
		});
		bus.on(ChangeScoreEvent.SET, (e) ->{
			if(name.equals(e.getPlayerName()))
				updateVar(scoreVar, 0, e.getAmountChanged());
		});
	}
	
	private void updateVar(Var<Integer> var, int initial, int change){
		var.set(initial + change);
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
