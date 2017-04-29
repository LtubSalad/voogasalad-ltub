package newengine.skill.skills;

import java.util.ArrayList;
import java.util.List;

import bus.EventBus;
import data.SpriteMakerModel;
import gamedata.AuthDataTranslator;
import newengine.events.SpriteModelEvent;
import newengine.events.skill.CheckCostAndBuildEvent;
import newengine.events.sprite.ChangeHealthEvent;
import newengine.events.stats.ChangeWealthEvent;
import newengine.model.PlayerStatsModel.WealthType;
import newengine.player.Player;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.sprite.Sprite;
import newengine.sprite.components.Cost;
import newengine.sprite.components.GameBus;
import newengine.sprite.components.Images;
import newengine.sprite.components.Owner;
import newengine.sprite.components.Position;
import newengine.utils.Target;

public class BuildSkill extends Skill {

	public static final SkillType<BuildSkill> TYPE = new SkillType<>(BuildSkill.class.getName());
	//private Sprite spriteToCreate;
	private SpriteMakerModel mySpriteModel;


	public BuildSkill(SpriteMakerModel spriteModel) {
		this.mySpriteModel = spriteModel;
		if (spriteModel.getComponentByType(Images.TYPE) != null) {
			Images imageComponent = (Images) spriteModel.getComponentByType(Images.TYPE);
			this.icon = imageComponent.image();
		}
	}

	
	
	public SpriteMakerModel getModel(){
		return mySpriteModel;
	}
	
	public void setModel(SpriteMakerModel model){
		this.mySpriteModel=model;
	}
	
	
	@Override
	public void trigger() {
		AuthDataTranslator translator = new AuthDataTranslator(mySpriteModel);
		Sprite spriteToCreate = translator.getSprite();
		if (spriteToCreate.getComponent(Cost.TYPE).isPresent()) {
			int cost = spriteToCreate.getComponent(Cost.TYPE).get().getCost();
			Player player = getSource().get().getComponent(Owner.TYPE).get().player();
			getSource().get().getComponent(GameBus.TYPE).ifPresent((gameBusComponent) -> {
				gameBusComponent.getGameBus().emit(
						new CheckCostAndBuildEvent(cost, player, () -> {
							buildSprite(spriteToCreate, player, cost);
						}));
			});
		}
	}
	
	private void buildSprite(Sprite spriteToCreate, Player player, int cost) {
		Target target = this.getTarget().get();
		// can override previous Position component
		spriteToCreate.addComponent(new Position(target.getLocation(), 0));
		if (this.getSource().get().getComponent(GameBus.TYPE).isPresent()) {
			List<Sprite> spritesToCreate = new ArrayList<>();
			spritesToCreate.add(spriteToCreate.clone());
			EventBus bus = this.getSource().get().getComponent(GameBus.TYPE).get().getGameBus();
			// emit change wealth event besides check cost and build event, 
			// only to utilize the already existing change wealth event handling 
			bus.emit(new ChangeWealthEvent(ChangeWealthEvent.CHANGE, player, WealthType.GOLD, cost));
			bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, spritesToCreate));
		}
	}


	@Override
	public SkillType<? extends Skill> getType() {
		return TYPE;
	}

}
