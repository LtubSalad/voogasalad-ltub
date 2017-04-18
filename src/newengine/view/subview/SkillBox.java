package newengine.view.subview;

import java.util.List;

import bus.EventBus;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import newengine.events.selection.SelectSkillEvent;
import newengine.skill.Skill;

public class SkillBox {

	private EventBus bus;
	private HBox box;
	private List<Skill> oldSkills;
		
	public SkillBox(EventBus bus) { 
		this.bus = bus;
		box = new HBox();
	}
	
	public void render(List<Skill> newSkills) {
		if (sameSkills(oldSkills, newSkills)) {return;}
		oldSkills = newSkills;
		box.getChildren().clear();
		for (Skill skill: newSkills) {
			if (skill.getIcon().isPresent()) {
				ImageView skillImageView = new ImageView(skill.getIcon().get().getFXImage());
				skillImageView.setOnMouseClicked(e -> {
					bus.emit(new SelectSkillEvent(SelectSkillEvent.SELECT, skill));
				});
				box.getChildren().add(skillImageView);
			}			
		}
	}
	
	public Node getBox() {
		return box;
	}
	
	private boolean sameSkills(List<Skill> oldSkills, List<Skill> newSkills) {
		if (oldSkills == null && newSkills == null) {return true;}
		if (oldSkills == null || newSkills == null) {return false;}
		if (oldSkills.size() != newSkills.size()) {return false;}
		for (int i = 0; i < oldSkills.size(); i++) {
			if (!oldSkills.get(i).getType().equals(newSkills.get(i).getType())) {
				return false;
			}
		}
		return true;
	}
}
