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
		sprite.getComponent(Speed.TYPE).ifPresent((speed) -> {
			makeSingleStat("Speed", Math.round(speed.speed()) / 4, sprite);
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
