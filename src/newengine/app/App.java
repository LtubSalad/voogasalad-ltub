package newengine.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bus.EventBus;
import commons.point.GamePoint;
import javafx.application.Application;
import javafx.stage.Stage;
import newengine.events.GameInitializationEvent;
import newengine.events.SpriteModelEvent;
import newengine.events.sound.SoundEvent;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.skill.skills.FireProjectileSkill;
import newengine.skill.skills.MoveSkill;
import newengine.sprite.Sprite;
import newengine.sprite.components.Collidable;
import newengine.sprite.components.Collidable.CollisionBoundType;
import newengine.sprite.components.EventQueue;
import newengine.sprite.components.GameBus;
import newengine.sprite.components.Images;
import newengine.sprite.components.Owner;
import newengine.sprite.components.Position;
import newengine.sprite.components.Range;
import newengine.sprite.components.Selectable;
import newengine.sprite.components.Selectable.SelectionBoundType;
import newengine.sprite.components.SkillSet;
import newengine.sprite.components.SoundEffect;
import newengine.sprite.components.Speed;
import newengine.sprite.player.Player;
import newengine.utils.image.ImageSet;
import newengine.utils.image.LtubImage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		Game game = new Game();

		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		
		// sprite 1
		Sprite sprite1 = new Sprite();
		LtubImage image1 = new LtubImage("images/characters/bahamut_left.png");
		ImageSet imageSet1 = new ImageSet(image1);
		Map<SkillType<? extends Skill>, Skill> skillMap = new HashMap<>();
		skillMap.put(MoveSkill.TYPE, new MoveSkill());
		skillMap.put(FireProjectileSkill.TYPE, new FireProjectileSkill());
		sprite1.addComponent(new GameBus());
		sprite1.addComponent(new SkillSet(skillMap));
		sprite1.addComponent(new Owner(player1));
		sprite1.addComponent(new Position(new GamePoint(200, 100), 0));
		sprite1.addComponent(new SoundEffect("data/sounds/Psyessr4.wav"));
		sprite1.addComponent(new Images(imageSet1));
		sprite1.addComponent(new Speed(200));
		sprite1.addComponent(new Collidable(CollisionBoundType.IMAGE));
		sprite1.addComponent(new Selectable(SelectionBoundType.IMAGE));
		sprite1.addComponent(new Range(128));

		sprite1.addComponent(new EventQueue());
			
		// sprite 2
		Sprite sprite2 = new Sprite();
		LtubImage image2 = new LtubImage("images/characters/bahamut_right.png");
		ImageSet imageSet2 = new ImageSet(image2);
		Map<SkillType<? extends Skill>, Skill> skillMap2 = new HashMap<>();
		skillMap2.put(MoveSkill.TYPE, new MoveSkill());
		skillMap2.put(FireProjectileSkill.TYPE, new FireProjectileSkill());
		sprite2.addComponent(new GameBus());
		sprite2.addComponent(new SkillSet(skillMap2));
		sprite2.addComponent(new Owner(player2));
		sprite2.addComponent(new Position(new GamePoint(300, 250), 0));
		sprite2.addComponent(new SoundEffect("data/sounds/Psyessr4.wav"));
		sprite2.addComponent(new Images(imageSet2));
		sprite2.addComponent(new Speed(100));
		sprite2.addComponent(new Collidable(CollisionBoundType.IMAGE));
		sprite2.addComponent(new Selectable(SelectionBoundType.IMAGE));
		sprite2.addComponent(new EventQueue());
		
		List<Sprite> spritesToAdd = new ArrayList<>();
		spritesToAdd.add(sprite1);
		spritesToAdd.add(sprite2);
		
		EventBus bus = game.getBus();
		bus.on(GameInitializationEvent.ANY, (e) -> {
			bus.emit(new SoundEvent(SoundEvent.BACKGROUND_MUSIC, "data/sounds/01-dark-covenant.mp3"));
			bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, spritesToAdd));
			// TODO add other map elements to the game (like stats, buttons)
		});
		
		
		
		stage.setScene(game.getScene());
		game.start();
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
