package engine.view;

import java.util.List;

import bus.EventBus;
import engine.skill.Skill;
import engine.skill.events.SelectSkillEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class SkillBox {

	private EventBus bus;
	private HBox box;
		
	public SkillBox(EventBus bus) {
		this.bus = bus;
		box = new HBox();
	}
	
	public void render(List<Skill> availableSkills) {
		// TODO refresh the skill box only when the availableSkills change
		box.getChildren().clear();
		for (Skill skill : availableSkills) {
			// TODO show the skill icon
			Button btn = new Button("build");
			btn.setOnAction((e) -> {
				bus.emit(new SelectSkillEvent(SelectSkillEvent.SELECT, skill));
			});
			box.getChildren().add(btn);
		}
	}
	
	public Node getBox() {
		return box;
	}
	
}
