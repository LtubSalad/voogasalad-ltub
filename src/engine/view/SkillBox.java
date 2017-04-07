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
	private Button btn1 = new Button("Build");
	private boolean rendered = false;
		
	public SkillBox(EventBus bus) {
		this.bus = bus;
		box = new HBox();
		box.getChildren().add(btn1);
	}
	
	public void render(List<Skill> availableSkills) {
//		if (rendered) { return; }
		// TODO refresh the skill box only when the availableSkills change
		btn1.setOnAction((e) -> {
			bus.emit(new SelectSkillEvent(SelectSkillEvent.SELECT, availableSkills.get(0)));
		});
//		rendered = true;
	}
	
	public Node getBox() {
		return box;
	}
	
}
