package newengine.view.subview;

import javafx.scene.layout.VBox;
import newengine.skill.skills.BuildSkill;
import newengine.skill.skills.FireProjectileSkill;
import newengine.sprite.Sprite;
import newengine.sprite.components.Attacker;
import newengine.sprite.components.DamageStrength;
import newengine.sprite.components.Health;
import newengine.sprite.components.Position;
import newengine.sprite.components.Range;
import newengine.sprite.components.SkillSet;
import newengine.sprite.components.Speed;

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
		sprite.getComponent(SkillSet.TYPE).ifPresent((skillSet) -> {
			if (skillSet.getSkill(FireProjectileSkill.TYPE) != null) {
			makeSingleStat("Cooldown time", skillSet.getSkill(FireProjectileSkill.TYPE).getCooldown(), sprite);
			}
		});
		sprite.getComponent(Attacker.TYPE).ifPresent((attacker) -> {
			makeSingleStat("Damage Strength", attacker.getDamageStrength(), sprite);

		});
		sprite.getComponent(Health.TYPE).ifPresent((health) -> {
			makeSingleStat("Health", health.getHealth(), sprite);
		});
		sprite.getComponent(Position.TYPE).ifPresent((position) -> {
			makeSingleStat("X Position", Math.round(position.xPos()), sprite);
			makeSingleStat("Y Position", Math.round(position.yPos()), sprite);
		});
		sprite.getComponent(Range.TYPE).ifPresent((range) -> {
			makeSingleStat("Range", range.range(), sprite);
		});
		sprite.getComponent(Speed.TYPE).ifPresent((speed) -> {
			makeSingleStat("Speed", speed.speed(), sprite);
		});
	}
	
	private void makeSingleStat(String label, Object value, Sprite sprite) {
		SingleStat singleStat = new SingleStat(label, value, sprite);
		box.getChildren().add(singleStat);
	}
		
	public VBox getBox() {
		return box;
	}
	
	
}
