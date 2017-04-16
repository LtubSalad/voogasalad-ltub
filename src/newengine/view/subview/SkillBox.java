package newengine.view.subview;

import bus.EventBus;
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
	
//	public void render(SpriteSelectionState spriteSelectionState) {
//		//		if (rendered) { return; }
//		// TODO refresh the skill box only when the availableSkills change
//		btn1.setOnAction((e) -> {
//			bus.emit(new SelectSkillEvent(SelectSkillEvent.SELECT, spriteSelectionState.getAvailableSkills().get(0)));
//		});
//		if (spriteSelectionState.getSelectionType() == SelectionType.SINGLE) {
//			Sprite source = spriteSelectionState.getSelectedSprite();
//			btn2.setOnAction((e) -> {
//				FireProjectileSkill fire = new FireProjectileSkill(bus);
//				fire.getSource();
//				bus.emit(new SelectSkillEvent(SelectSkillEvent.SELECT, fire));
//			});	
//		} else {
//			btn2.setOnAction((e) -> { });
//		}
//		
////		rendered = true;
//	}
//	
//	public Node getBox() {
//		return box;
//	}
//	
}
