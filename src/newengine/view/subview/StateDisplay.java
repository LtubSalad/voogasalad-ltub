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
		makeIDLabel();
		makePlayerLabel();
		makeAttackerLabel();
		makeCooldownLabel();
		makeDamageLabel();
		makeHealthLabel();
		makeXPosLabel();
		makeYPosLabel();
		makeRangeLabel();
		makeSpeedLabel();
	}

	private void makeIDLabel() {
		SingleStat ID = new SingleStat("Sprite ID", sprite.getState().getID(), sprite);
		box.getChildren().add(ID);
	}

	private void makePlayerLabel() {
		SingleStat player = new SingleStat("Player", sprite.getState().getPlayer(), sprite);
		box.getChildren().add(player);
	}

	private void makeAttackerLabel() {
		SingleStat attacker = new SingleStat("Is Attacker?", sprite.getState().isAttacker(), sprite);
		box.getChildren().add(attacker);
	}

	private void makeCooldownLabel() {
		SingleStat cooldown = new SingleStat("Cooldown time", sprite.getState().getCooldown(), sprite);
		box.getChildren().add(cooldown);
	}

	private void makeDamageLabel() {
		SingleStat damage = new SingleStat("Damage Strength", sprite.getState().getDamageStrength(), sprite);
		damage.addUpgradeBtn();
		box.getChildren().add(damage);
	}

	private void makeHealthLabel() {
		SingleStat health = new SingleStat("Health", sprite.getState().getHealth(), sprite);
		box.getChildren().add(health);
	}

	private void makeXPosLabel() {
		SingleStat pos = new SingleStat("X Position", sprite.getState().getXPos(), sprite);
		box.getChildren().add(pos);
	}
	
	private void makeYPosLabel() {
		SingleStat pos = new SingleStat("Y Position", sprite.getState().getYPos(), sprite);
		box.getChildren().add(pos);
	}

	private void makeRangeLabel() {
		SingleStat range = new SingleStat("Range", sprite.getState().getRange(), sprite);
		box.getChildren().add(range);
	}

	private void makeSpeedLabel() {
		SingleStat speed = new SingleStat("Speed", sprite.getState().getSpeed(), sprite);
		box.getChildren().add(speed);
	}
	
	public VBox getBox() {
		return box;
	}
	
	
}
