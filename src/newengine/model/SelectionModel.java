package newengine.model;

import java.util.Optional;

import bus.EventBus;
import newengine.events.selection.SelectSkillEvent;
import newengine.events.selection.SelectSpriteEvent;
import newengine.skill.Skill;
import newengine.sprite.Sprite;
import newengine.utils.variable.Var;

public class SelectionModel {

	private EventBus bus;
	private final Var<Sprite> selectedSpriteVar = new Var<>();
	private final Var<Skill> selectedSkillVar = new Var<>();
	
	public SelectionModel(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(SelectSpriteEvent.SINGLE, (e) -> {
			selectSprite(e.getSprite());
		});
		bus.on(SelectSkillEvent.SELECT, (e) -> {
			selectSkill(e.getSkill());
		});
		bus.on(SelectSkillEvent.CANCEL, (e) -> {
			cancelSkill(e.getSkill());
		});
	}
	
	private void selectSprite(Sprite sprite) {
		selectedSpriteVar.set(sprite);
	}
	private void selectSkill(Skill skill) {
		selectedSkillVar.set(skill);
	}
	private void cancelSkill(Skill skill) {
		selectedSkillVar.set(null);
	}
	
	public Optional<Sprite> getSelectedSprite() {
		return Optional.ofNullable(selectedSpriteVar.get());
	}
	public Optional<Skill> getSelectedSkill() {
		return Optional.ofNullable(selectedSkillVar.get());
	}
	
	
}
