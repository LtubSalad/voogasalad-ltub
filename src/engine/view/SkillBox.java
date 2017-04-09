package engine.view;

import bus.EventBus;
import engine.playerstate.PlayerSelectionState;
import engine.playerstate.PlayerSelectionState.SelectionType;
import engine.skill.FireProjectileSkill;
import engine.skill.events.SelectSkillEvent;
import engine.sprite.Sprite;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class SkillBox {

	private EventBus bus;
	private HBox box;
	private Button btn1 = new Button("Build");
	private Button btn2 = new Button("Fire");
	private boolean rendered = false;
		
	public SkillBox(EventBus bus) { 
		this.bus = bus;
		box = new HBox();
		box.getChildren().add(btn1);
		// TODO: add btn2 to the box
	}
	
	public void render(PlayerSelectionState playerSelectionState) {
		//		if (rendered) { return; }
		// TODO refresh the skill box only when the availableSkills change
		btn1.setOnAction((e) -> {
			bus.emit(new SelectSkillEvent(SelectSkillEvent.SELECT, playerSelectionState.getAvailableSkills().get(0)));
		});
		if (playerSelectionState.getSelectionType() == SelectionType.SINGLE) {
			Sprite source = playerSelectionState.getSelectedSprite();
			btn2.setOnAction((e) -> {
				FireProjectileSkill fire = new FireProjectileSkill(bus);
				fire.getSource();
				bus.emit(new SelectSkillEvent(SelectSkillEvent.SELECT, fire));
			});	
		} else {
			btn2.setOnAction((e) -> { });
		}
		
//		rendered = true;
	}
	
	public Node getBox() {
		return box;
	}
	
}
