package newengine.model;

import java.util.Optional;

import bus.EventBus;
import newengine.events.SpriteModelEvent;
import newengine.events.selection.SelectSkillEvent;
import newengine.events.selection.SelectSpriteEvent;
import newengine.skill.Skill;
import newengine.sprite.Sprite;
import newengine.utils.variable.Var;

public class SelectionModel {

	private EventBus bus;
	private Sprite selectedSprite;
	private Skill selectedSkill;
	
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
			cancelSkill();
		});
		bus.on(SpriteModelEvent.REMOVE, (e) -> {
			if (e.getSprites().contains(selectedSprite)) {
				removeSprite();				
			}
		});
	}
	
	private void selectSprite(Sprite sprite) {
		selectedSprite = sprite;
	}
	private void removeSprite() {
		selectedSprite = null;
	}
	private void selectSkill(Skill skill) {
		selectedSkill = skill;
	}
	private void cancelSkill() {
		selectedSkill = null;
	}
	
	public Optional<Sprite> getSelectedSprite() {
		return Optional.ofNullable(selectedSprite);
	}
	public Optional<Skill> getSelectedSkill() {
		return Optional.ofNullable(selectedSkill);
	}
	
	
}
