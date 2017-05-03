package newengine.managers.levels;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bus.EventBus;
import commons.point.GamePoint;
import data.SpriteMakerModel;
import gamecreation.level.ILevelData;
import gamedata.AuthDataTranslator;
import newengine.events.SpriteModelEvent;
import newengine.events.conditions.EndConditionTriggeredEvent;
import newengine.events.conditions.SetEndConditionEvent;
import newengine.events.game.GamePauseResumeEvent;
import newengine.events.levels.InitILevelsEvent;
import newengine.events.levels.SetLevelEvent;
import newengine.events.levels.WinGameEvent;
import newengine.player.Player;
import newengine.skill.skills.BuildSkill;
import newengine.sprite.Sprite;
import newengine.sprite.components.GameBus;
import newengine.sprite.components.Images;
import newengine.sprite.components.Owner;
import newengine.sprite.components.PathFollower;
import newengine.sprite.components.Position;
import newengine.sprite.components.SkillSet;
import newengine.utils.image.LtubImage;
import player.winnerorloser.LosePresentation;
import player.winnerorloser.ResultAccessor;
import player.winnerorloser.WinPresentation;

public class LevelManager{
	private EventBus bus;
	private List<ILevelData> data;
	private int numLevels;
	private int currentLevel;
	
	public LevelManager(EventBus bus){
		this.bus = bus;
		initHandlers();
	}
	
	private void initLevels(List<ILevelData> levelDataList) {
		this.data = levelDataList;
		this.currentLevel = 1;
		if(data != null){
			this.numLevels = data.size();
			loadLevel(data.get(0));
		}
	}
	
	private void initHandlers() {
		bus.on(InitILevelsEvent.ANY, e -> {
			initLevels(e.getLevelDataList());
		});
		bus.on(EndConditionTriggeredEvent.WIN, e -> {if(data!= null) nextLevel();});
		bus.on(EndConditionTriggeredEvent.LOSE, e -> {
			new LosePresentation().show(new ResultAccessor());
			bus.emit(new GamePauseResumeEvent());
			});
		bus.on(SetLevelEvent.SET, e -> {if(data!= null) setLevel(e.getLevelNumber());});
	}

	public void nextLevel(){
		if(!(currentLevel == numLevels)){
			System.out.println("next level loading");
			currentLevel++;
			System.out.println("Current level: " + currentLevel);
			loadLevel(data.get(currentLevel-1));
			return;
		}
			new WinPresentation().show(new ResultAccessor());;
			bus.emit(new WinGameEvent(WinGameEvent.WIN));
			bus.emit(new GamePauseResumeEvent());
	}
	
	public void resetLevel(){
		loadLevel(data.get(currentLevel-1));
	}
	
	public void setLevel(int levelNumber){
		if(levelNumber > 0 && levelNumber <= numLevels){
			loadLevel(data.get(levelNumber-1));
		}
	}
	
	private void loadLevel(ILevelData newLevel){
		bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETWIN, newLevel.getWinningCondition()));
		bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETLOSE, newLevel.getLosingCondition()));
		
		List<SpriteMakerModel> spriteMakerModels = newLevel.getSpawners();
		//System.out.println("Number of spawners: " + spriteMakerModels.size());
		List<Sprite> sprites = new ArrayList<>();
		sprites.addAll(spriteMakerModels.stream().map((spriteMakerModel) -> {
			return (new AuthDataTranslator(spriteMakerModel)).getSprite();
		}).collect(Collectors.toList()));
		

		bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, sprites));
		
		
		List<Sprite> pathSprites = new ArrayList<>();
		for (int m = 0; m < spriteMakerModels.size(); m++) {
			SkillSet skillSet = (SkillSet) spriteMakerModels.get(m).getComponentByType(SkillSet.TYPE);
			BuildSkill buildSkill = (BuildSkill) skillSet.getSkill(BuildSkill.TYPE);
			PathFollower pathFollowerComponent = (PathFollower) buildSkill.getSpriteMakerModel().getComponentByType(PathFollower.TYPE);
			List<GamePoint> points = new ArrayList<> (pathFollowerComponent.getPath().getPath());
			points.forEach(e -> {
				System.out.println(e.x() + " " + e.y());
			});
			for (int i = 0; i < points.size()-1; i++) {
				GamePoint point1 = points.get(i);
				GamePoint point2 = points.get(i+1);
				double dist = point1.distFrom(point2);
				double dx = point2.x() - point1.x();
				double dy = point2.y() - point1.y();
				double tileInterval = 30;
				for (int j = 0; j <= dist / tileInterval; j++) {
					GamePoint pathPoint = new GamePoint(
							point1.x() + tileInterval * dx / dist * j,
							point1.y() + tileInterval * dy / dist * j);
					Sprite step = new Sprite();
					step.addComponent(new Position(pathPoint));
					LtubImage ltubimage = new LtubImage("images/characters/Stone.jpg");
					step.addComponent(new Images(ltubimage));
					step.addComponent(new GameBus());
					step.addComponent(new Owner(Player.NATURE));
					pathSprites.add(step);
				}
			}	
		}
		System.out.println("Size of pathSprites: "+pathSprites.size());
		bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, pathSprites));
		
	}

}
