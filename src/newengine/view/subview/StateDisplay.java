package newengine.view.subview;

import javafx.scene.layout.VBox;
import newengine.sprite.Sprite;

public class StateDisplay {
	
	private Sprite sprite;
	private VBox box;
	
	public StateDisplay() {
		this.box = new VBox();
	}
	
	public StateDisplay(Sprite sprite){
		this.sprite = sprite;
		this.box = new VBox();
	}
	
	public void clear() {
		box.getChildren().clear();
	}
	
	public void render(Sprite sprite){
		this.sprite = sprite;
		box.getChildren().clear();
		makeSingleStat("Sprite ID", sprite.getState().getID(), sprite);
		makeSingleStat("Player", sprite.getState().getPlayer(), sprite);
		makeSingleStat("Is Attacker?", sprite.getState().isAttacker(), sprite);
		makeSingleStat("Cooldown time", sprite.getState().getCooldown(), sprite);
		makeSingleStat("Damage Strength", sprite.getState().getDamageStrength(), sprite);
		makeSingleStat("Health", sprite.getState().getHealth(), sprite);
		makeSingleStat("X Position", sprite.getState().getXPos(), sprite);
		makeSingleStat("Y Position", sprite.getState().getYPos(), sprite);
		makeSingleStat("Range", sprite.getState().getRange(), sprite);
		makeSingleStat("Speed", sprite.getState().getSpeed(), sprite);
	}
	
	private void makeSingleStat(String label, Object value, Sprite sprite) {
		SingleStat singleStat = new SingleStat(label, value, sprite);
		box.getChildren().add(singleStat);
	}
	
	public VBox getBox() {
		return box;
	}
	
	
}
