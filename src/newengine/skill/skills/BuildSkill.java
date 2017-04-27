package newengine.skill.skills;

import java.util.ArrayList;
import java.util.List;

import data.SpriteMakerModel;
import newengine.events.SpriteModelEvent;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.sprite.Sprite;
import newengine.sprite.components.GameBus;
import newengine.sprite.components.Images;
import newengine.sprite.components.Position;
import newengine.utils.Target;

public class BuildSkill extends Skill{
	
	public static final SkillType<BuildSkill> TYPE = new SkillType<>(BuildSkill.class.getName());
	private Sprite spriteToCreate;
	private SpriteMakerModel model;
	
	public BuildSkill(Sprite sprite) {
		this.spriteToCreate = sprite;
		if (sprite.getComponent(Images.TYPE).isPresent()) {
			this.icon = sprite.getComponent(Images.TYPE).get().image();
		}
	}
	
	public BuildSkill(SpriteMakerModel model){
		this(model.produceSprite());
		this.model=model;
	}
	
	public SpriteMakerModel getModel(){
		return model;
	}
	
	public void setModel(SpriteMakerModel model){
		this.model=model;
	}
	
	@Override
	public void trigger() {
//		if (canControl()) {
			Target target = this.getTarget().get();
			// can override previous Position component
			spriteToCreate.addComponent(new Position(target.getLocation(), 0));
			if (this.getSource().get().getComponent(GameBus.TYPE).isPresent()) {
				List<Sprite> spritesToCreate = new ArrayList<>();
				spritesToCreate.add(spriteToCreate.clone());
				this.getSource().get().getComponent(GameBus.TYPE).get().getGameBus()
				.emit(new SpriteModelEvent(SpriteModelEvent.ADD, spritesToCreate));
			}
//		}
	}

	@Override
	public SkillType<? extends Skill> getType() {
		return TYPE;
	}

	
}
